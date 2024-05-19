package com.msbkn.sakila.ui.pages;

import com.msbkn.sakila.domain.Film;
import com.msbkn.sakila.service.FilmService;
import com.msbkn.sakila.ui.MyUI;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.pages.common.BaseListPage;
import com.msbkn.sakila.ui.pages.windows.FilmActorCardWindow;
import com.vaadin.ui.Notification;

import java.util.List;

public class FilmListPage extends BaseListPage {
    private FilmService filmService;
    private String actorStr = "Aktörler";
    private SkButtonField filmActorButtonField;

    private String titleStr = "Başlık";
    private String descriptionStr = "Kısa Açıklama";
    private String languageStr = "Dil";
    private String creationDateStr = "Oluşturma Tarihi";

    public FilmListPage() {
        filmService = new FilmService();

        builFilterPanel();
        verticalLayoutField.addComponent(filterLayoutField);

        builTableField();
        verticalLayoutField.addComponent(tableDataField);

        addComponent(verticalLayoutField);

        verticalLayoutField.setExpandRatio(filterLayoutField, 0.3f);
        verticalLayoutField.setExpandRatio(tableDataField, 0.7f);
    }

    private void builFilterPanel() {
        addItemTextFilterPanel("Adı Ara..", titleStr);
        addItemTextFilterPanel("Açıklama Ara..", descriptionStr);
        addItemTextFilterPanel("Dil Ara..", languageStr);
    }

    private void builTableField() {
        addItemTableColumn(emptyStr, SkDeleteButtonField.class, null);
        addItemTableColumn(actorStr, SkButtonField.class, null);
        addItemTableColumn(titleStr, String.class, null);
        addItemTableColumn(descriptionStr, String.class, null);
        addItemTableColumn(languageStr, String.class, null);
        addItemTableColumn(creationDateStr, String.class, null);
        fillDataField();
        doubleClickSelectItem();
    }

    public void fillDataField() {
        removeTableAllField();
        List<Film> films = filmService.findAll();
        for (Film film : films) {
            addItemTableData(film, film.getTitle(), titleStr);
            addItemTableData(film, film.getDescription25Limt(), descriptionStr);
            addItemTableData(film, film.getLanguageName(), languageStr);
            addItemTableData(film, film.getDateString(), creationDateStr);
            buildItemFilmActorField(film);
            buildItemDeleteField(film, filmService);
        }
    }

    private void buildItemFilmActorField(Film filmField) {
        filmActorButtonField = new SkButtonField();
        filmActorButtonField.setData(filmField);
        addItemTableData(filmField, filmActorButtonField, actorStr);
        filmActorButtonField.addClickListener(clickEvent -> {
            Film selectFilmField = (Film) clickEvent.getButton().getData();
            FilmActorCardWindow actorCardWindow = new FilmActorCardWindow(selectFilmField);
            MyUI.getCurrent().addWindow(actorCardWindow);
        });
    }
}






