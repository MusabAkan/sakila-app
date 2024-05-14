package com.msbkn.sakila.ui.pages;

import com.msbkn.sakila.domain.Actor;
import com.msbkn.sakila.service.ActorService;
import com.msbkn.sakila.ui.MyUI;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.pages.component.*;

import java.util.List;


public class ActorListPage extends SkVerticalLayoutField {
    private ActorService actorService;

    private String nameStr = "Ad";
    private String lastNameStr = "Soyad";
    private String creationDateStr = "Oluşturma Tarihi";
    private String emptyStr = " ";

    private SkTableField tableDataField;
    private SkVerticalLayoutField verticalLayoutField;
    private SkFormLayoutField filterLayoutField;
    private SkDeleteButtonField deleteButtonField;


    public ActorListPage() {
        verticalLayoutField = new SkVerticalLayoutField();

        builFilterPanel();
        verticalLayoutField.addComponent(filterLayoutField);

        builTableField();
        verticalLayoutField.addComponent(tableDataField);

        addComponent(verticalLayoutField);

        verticalLayoutField.setExpandRatio(filterLayoutField, 0.2f);
        verticalLayoutField.setExpandRatio(tableDataField, 0.8f);

    }

    private void builFilterPanel() {
        filterLayoutField = new SkFormLayoutField();       
        SkTextField nameFilterField = new SkTextField();
        nameFilterField.setCaption("Adı Ara..");
        nameFilterField.addTextChangeListener(event -> {
            String searchIdField = event.getText();
            filterLayoutField.filterSearch(searchIdField, nameStr, tableDataField);
        });

        filterLayoutField.addComponents(nameFilterField);

        SkTextField lastFilterField = new SkTextField();
        lastFilterField.setCaption("Soyadı Ara...");
        lastFilterField.addTextChangeListener(event -> {
            String searchNameField = event.getText();
            filterLayoutField.filterSearch(searchNameField, nameStr, tableDataField);
        });

        filterLayoutField.addComponent(lastFilterField);

    }

    private void builTableField() {
        tableDataField = new SkTableField();
        tableDataField.addContainerProperty(emptyStr, SkDeleteButtonField.class, null);
        tableDataField.addContainerProperty(nameStr, String.class, null);
        tableDataField.addContainerProperty(lastNameStr, String.class, null);
        tableDataField.addContainerProperty(creationDateStr, String.class, null);
        fillDataField();
        doubleClickGetItem();
    }

    private void doubleClickGetItem() {
        tableDataField.addItemClickListener(event -> {
            boolean isDoubleClick = event.isDoubleClick();
            if (isDoubleClick) {
                Actor selectActor = (Actor) event.getItemId();
                ActorCardWindow actorCardWindow = new ActorCardWindow(selectActor);
                MyUI.getCurrent().addWindow(actorCardWindow);
                actorCardWindow.addCloseListener(closeEvent -> fillDataField());
            }
        });
    }


    private void fillDataField() {
        actorService = new ActorService();
        tableDataField.removeAllItems();

        Object result = actorService.findAll();

        if (result == null && result instanceof List) return;

        List<Actor> actors = (List<Actor>) result;

        for (Actor actor : actors) {
            addItemToTable(actor);
        }
    }

    private void actorDeleteField(Actor actor) {
        ActorService actorService = new ActorService();
        String question = actor.getFullName() + " seçili Aktör silmek istediğinizden emin misiniz?";

        DialogCardWinddow dialogCardWinddow = new DialogCardWinddow(question);
        MyUI.getCurrent().addWindow(dialogCardWinddow);

        dialogCardWinddow.addCloseListener(closeEvent -> {
            boolean dialogCardWinddowResult = dialogCardWinddow.getResult();
            if (dialogCardWinddowResult) {
                actorService.deleteActor(actor);
                fillDataField();
            }
        });
    }


    private void addItemToTable(Actor actor) {
        tableDataField.addItem(actor);

        String firstNameField = actor.getFirstName();
        tableDataField.getContainerProperty(actor, nameStr).setValue(firstNameField);

        String lastNameField = actor.getLastName();
        tableDataField.getContainerProperty(actor, lastNameStr).setValue(lastNameField);

        String creationDateField = actor.toString();
        tableDataField.getContainerProperty(actor, creationDateStr).setValue(creationDateField);

        deleteButtonField = new SkDeleteButtonField();
        deleteButtonField.setData(actor);

        tableDataField.getContainerProperty(actor, emptyStr).setValue(deleteButtonField);

        deleteButtonField.addClickListener(event -> {
            Actor selectActorField = (Actor) event.getButton().getData();
            actorDeleteField(selectActorField);
        });

    }


}

