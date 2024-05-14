package com.msbkn.sakila.ui.pages;

import com.msbkn.sakila.domain.Actor;
import com.msbkn.sakila.service.ActorService;
import com.msbkn.sakila.ui.MyUI;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.pages.component.*;
import com.vaadin.ui.*;

import java.util.List;


public class ActorListPage extends SkVerticalLayoutField {
    private ActorService actorService;

    private String nameStr = "Ad";
    private String lastNameStr = "Soyad";
    private String birthDateStr = "Oluşturma Tarihi";

    private SkTableField tableData;
    private SkVerticalLayoutField verticalLayoutField;
    private SkFormLayoutField filterLayoutField;


    public ActorListPage() {
        verticalLayoutField = new SkVerticalLayoutField();

        builFilterPanel();
        verticalLayoutField.addComponent(filterLayoutField);

        builTableField();
        verticalLayoutField.addComponent(tableData);

        addComponent(verticalLayoutField);

        verticalLayoutField.setExpandRatio(filterLayoutField, 0.2f);
        verticalLayoutField.setExpandRatio(tableData, 0.8f);


    }

    private void builFilterPanel() {
        filterLayoutField = new SkFormLayoutField();

        SkTextField nameFilterField = new SkTextField();
        nameFilterField.setCaption("Adı Ara..");
        nameFilterField.addTextChangeListener(event -> {
            String searchIdField = event.getText();
            filterLayoutField.filterSearch(searchIdField, nameStr, tableData);
        });

        filterLayoutField.addComponents(nameFilterField);

        SkTextField lastFilterField = new SkTextField();
        lastFilterField.setCaption("Soyadı Ara...");
        lastFilterField.addTextChangeListener(event -> {
            String searchNameField = event.getText();
            filterLayoutField.filterSearch(searchNameField, nameStr, tableData);
        });

        filterLayoutField.addComponent(lastFilterField);

    }

    private void builTableField() {
        tableData = new SkTableField();

        tableData.addContainerProperty(nameStr, String.class, null);
        tableData.addContainerProperty(lastNameStr, String.class, null);
        tableData.addContainerProperty(birthDateStr, String.class, null);

        doubleClickGetItem();

        fillData();
    }

    private void doubleClickGetItem() {
        tableData.addItemClickListener(event -> {

            boolean isDoubleClick = event.isDoubleClick();

            if (isDoubleClick) {
                Actor selectActor = (Actor) event.getItemId();

                ActorCardWindow actorCardWindow = new ActorCardWindow(selectActor);
                MyUI.getCurrent().addWindow(actorCardWindow);

                fillData();
            }
        });
    }


    private void fillData() {
        actorService = new ActorService();
        tableData.removeAllItems();

        Object result = actorService.findAll();

        if (result == null && result  instanceof  List) return;

        List<Actor> actors = (List<Actor>) result;

        for (Actor actor : actors) {
            addItemToGrid(actor);
        }
    }

    private void addItemToGrid(Actor actor) {
        tableData.addItem(actor);

        String firstNameField = actor.getFirstName();
        tableData.getContainerProperty(actor, nameStr).setValue(firstNameField);

        String lastNameField = actor.getLastName();
        tableData.getContainerProperty(actor, lastNameStr).setValue(lastNameField);

        String birthDateField = actor.toString();
        tableData.getContainerProperty(actor, birthDateStr).setValue(birthDateField);

    }


}

