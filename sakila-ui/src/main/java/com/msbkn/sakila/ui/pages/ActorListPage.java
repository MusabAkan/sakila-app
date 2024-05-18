package com.msbkn.sakila.ui.pages;

import com.msbkn.sakila.domain.Actor;
import com.msbkn.sakila.service.ActorService;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.pages.common.BaseListPage;

import java.util.List;


public class ActorListPage extends BaseListPage {
    private ActorService actorService;

    private String fullNameStr = "Ad Soyad";
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
        addItemTextFilterPanel("Ad Soyad Ara..", fullNameStr);
    }

    private void builTableField() {
        addTableData(emptyStr, SkDeleteButtonField.class, null);
        addTableData(fullNameStr, String.class, null);
        addTableData(creationDateStr, String.class, null);
        fillDataField();
        doubleClickSelectItem();
    }

    public void fillDataField() {
        removeTableAllField();
        List<Actor> actors = actorService.findAll();
        for (Actor actor : actors) {
            getTableData(actor, actor.getFullName(), fullNameStr);
            getTableData(actor, actor.getDateString(), creationDateStr);
            buildItemDeleteField(actor, actorService);
        }
    }


}

