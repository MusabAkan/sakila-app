package com.msbkn.sakila.ui.pages;

import com.msbkn.sakila.domain.Film;
import com.msbkn.sakila.service.FilmService;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.pages.common.BaseListPage;

import java.util.Date;
import java.util.List;

public class FilmListPage extends BaseListPage {
    private FilmService filmService;

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
        addTableItemColumn(emptyStr, SkDeleteButtonField.class, null);
        addTableItemColumn(titleStr, String.class, null);
        addTableItemColumn(descriptionStr, String.class, null);
        addTableItemColumn(languageStr, String.class, null);
        addTableItemColumn(creationDateStr, String.class, null);
        fillDataField();
        doubleClickSelectItem();
    }

    public void fillDataField() {
        removeTableAllField();
        List<Film> films = filmService.findAll();
        for (Film film : films) {
            addTableItemRow(film, film.getTitle(), titleStr);
            addTableItemRow(film, film.getDescription25Limt(), descriptionStr);
            addTableItemRow(film, film.getLanguageName(), languageStr);
            addTableItemRow(film, film.getDateString(), creationDateStr);
            buildItemDeleteField(film, filmService);
        }
    }
}






