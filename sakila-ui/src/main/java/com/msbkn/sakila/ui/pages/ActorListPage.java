package com.msbkn.sakila.ui.pages;

import com.msbkn.sakila.domain.Actor;
import com.msbkn.sakila.service.ActorService;
import com.msbkn.sakila.ui.*;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.pages.common.BaseListPage;
import com.msbkn.sakila.ui.pages.windows.DialogCardWinddow;

import java.util.Date;
import java.util.List;


public class ActorListPage extends BaseListPage {
    private ActorService actorService;

    private String nameStr = "Ad";
    private String lastNameStr = "Soyad";
    private String creationDateStr = "Oluşturma Tarihi";

    public ActorListPage() {
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
        addItemTextFilterPanel("Adı Ara..", nameStr);
        addItemTextFilterPanel("Soyadı Ara...", lastNameStr);
    }

    private void builTableField() {
        addTableColumn(emptyStr, SkDeleteButtonField.class, null);
        addTableColumn(nameStr, String.class, null);
        addTableColumn(lastNameStr, String.class, null);
        addTableColumn(creationDateStr, String.class, null);
        fillDataField();
        doubleClickSelectItem();
    }

    public void fillDataField() {
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

        buildItemDeleteField(actor, actorService);
    }

}

