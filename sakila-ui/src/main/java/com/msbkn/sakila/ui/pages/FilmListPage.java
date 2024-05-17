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
        addTableColumn(emptyStr, SkDeleteButtonField.class, null);
        addTableColumn(titleStr, String.class, null);
        addTableColumn(descriptionStr, String.class, null);
        addTableColumn(languageStr, String.class, null);
        addTableColumn(creationDateStr, String.class, null);
        fillDataField();
        doubleClickSelectItem();
    }

    public void fillDataField() {
        tableDataField.removeAllItems();
        List<Film> films = filmService.findAll();
        for (Film film : films) {
            addFlimToTable(film);
        }
    }

    private void addFlimToTable(Film film) {
        tableDataField.addItem(film);

        String firstNameField = film.getTitle();
        tableDataField.getContainerProperty(film, titleStr).setValue(firstNameField);

        String lastNameField = film.getDescription25Limt();
        tableDataField.getContainerProperty(film, descriptionStr).setValue(lastNameField);

        String languageNameField = film.getLanguageName();
        tableDataField.getContainerProperty(film, languageStr).setValue(languageNameField);

        Date lastUpdate = film.getLastUpdate();
        String creationDateField = film.getDateString(lastUpdate);
        tableDataField.getContainerProperty(film, creationDateStr).setValue(creationDateField);

        buildItemDeleteField(film, filmService);
    }
}






