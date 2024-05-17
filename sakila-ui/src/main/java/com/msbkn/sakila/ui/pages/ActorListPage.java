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
        addTableItemColumn(emptyStr, SkDeleteButtonField.class, null);
        addTableItemColumn(fullNameStr, String.class, null);
        addTableItemColumn(creationDateStr, String.class, null);
        fillDataField();
        doubleClickSelectItem();
    }

    public void fillDataField() {
        removeTableAllField();
        List<Actor> actors = actorService.findAll();
        for (Actor actor : actors) {
            addTableItemRow(actor, actor.getFullName(), fullNameStr);
            addTableItemRow(actor, actor.getDateString(), creationDateStr);
            buildItemDeleteField(actor, actorService);
        }
    }


}

