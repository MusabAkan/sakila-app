package com.msbkn.sakila.ui.pages;

import com.msbkn.sakila.domain.Film;
import com.msbkn.sakila.service.FilmService;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.pages.common.BaseListPage;

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
        addTableData(emptyStr, SkDeleteButtonField.class, null);
        addTableData(titleStr, String.class, null);
        addTableData(descriptionStr, String.class, null);
        addTableData(languageStr, String.class, null);
        addTableData(creationDateStr, String.class, null);
        fillDataField();
        doubleClickSelectItem();
    }

    public void fillDataField() {
        removeTableAllField();
        List<Film> films = filmService.findAll();
        for (Film film : films) {
            getTableData(film, film.getTitle(), titleStr);
            getTableData(film, film.getDescription25Limt(), descriptionStr);
            getTableData(film, film.getLanguageName(), languageStr);
            getTableData(film, film.getDateString(), creationDateStr);
            buildItemDeleteField(film, filmService);
        }
    }
}






