package com.msbkn.sakila.ui.pages;

import com.msbkn.sakila.domain.Actor;
import com.msbkn.sakila.service.ActorService;
import com.msbkn.sakila.ui.*;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.pages.windows.ActorCardWindow;
import com.msbkn.sakila.ui.pages.windows.DialogCardWinddow;

import java.util.Date;
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
        filterLayoutField = new SkFormLayoutField();
        tableDataField = new SkTableField();
        actorService = new ActorService();

        builFilterPanel();
        verticalLayoutField.addComponent(filterLayoutField);

        builTableField();
        verticalLayoutField.addComponent(tableDataField);

        addComponent(verticalLayoutField);

        verticalLayoutField.setExpandRatio(filterLayoutField, 0.2f);
        verticalLayoutField.setExpandRatio(tableDataField, 0.8f);

    }

    private void builFilterPanel() {
        SkTextField nameFilterField = new SkTextField();
        buildItemFilterPanel(nameFilterField, "Adı Ara..", nameStr, tableDataField, filterLayoutField);
        filterLayoutField.addComponents(nameFilterField);

        SkTextField lastFilterField = new SkTextField();
        buildItemFilterPanel(lastFilterField, "Soyadı Ara...", lastNameStr, tableDataField, filterLayoutField);
        filterLayoutField.addComponent(lastFilterField);
    }

    private void builTableField() {
        tableDataField.addContainerProperty(emptyStr, SkDeleteButtonField.class, null);
        tableDataField.addContainerProperty(nameStr, String.class, null);
        tableDataField.addContainerProperty(lastNameStr, String.class, null);
        tableDataField.addContainerProperty(creationDateStr, String.class, null);
        fillDataField();
        doubleClickSelectItem();
    }

    private void doubleClickSelectItem() {
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
        tableDataField.removeAllItems();
        List<Actor> actors = actorService.findAll();
        for (Actor actor : actors)
            addActorToTable(actor);
    }

    private void addActorToTable(Actor actor) {
        tableDataField.addItem(actor);

        String firstNameField = actor.getFirstName();
        tableDataField.getContainerProperty(actor, nameStr).setValue(firstNameField);

        String lastNameField = actor.getLastName();
        tableDataField.getContainerProperty(actor, lastNameStr).setValue(lastNameField);

        Date lastUpdate = actor.getLastUpdate();
        String creationDateField = actor.getDateString(lastUpdate);
        tableDataField.getContainerProperty(actor, creationDateStr).setValue(creationDateField);

        buildActorDeleteField(actor);
    }

    private void buildActorDeleteField(Actor actor) {
        deleteButtonField = new SkDeleteButtonField();
        deleteButtonField.setData(actor);
        tableDataField.getContainerProperty(actor, emptyStr).setValue(deleteButtonField);
        actorDeleteField();

    }

    private void actorDeleteField() {
        deleteButtonField.addClickListener(event -> {
            Actor selectdActorField = (Actor) event.getButton().getData();
            String question = selectdActorField.getFullName() + " seçili Aktör silmek istediğinizden emin misiniz?";

            DialogCardWinddow dialogCardWinddow = new DialogCardWinddow(question);
            MyUI.getCurrent().addWindow(dialogCardWinddow);

            dialogCardWinddow.addCloseListener(closeEvent -> {
                boolean dialogCardWinddowResult = dialogCardWinddow.getResult();
                if (dialogCardWinddowResult) {
                    actorService.delete(selectdActorField);
                    fillDataField();
                }
            });
        });
    }


}

