package com.msbkn.sakila.ui.pages;

import com.msbkn.sakila.domain.Film;
import com.msbkn.sakila.service.FilmService;
import com.msbkn.sakila.ui.MyUI;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.pages.windows.DialogCardWinddow;
import com.msbkn.sakila.ui.pages.windows.FilmCardWindow;

import java.util.Date;
import java.util.List;

public class FilmListPage extends SkVerticalLayoutField {
    private FilmService filmService;

    private String titleStr = "Başlık";
    private String descriptionStr = "Kısa Açıklama";
    private String languageStr = "Dil";
    private String creationDateStr = "Oluşturma Tarihi";
    private String emptyStr = " ";

    private SkTableField tableDataField;
    private SkVerticalLayoutField verticalLayoutField;
    private SkFormLayoutField filterLayoutField;
    private SkDeleteButtonField deleteButtonField;


    public FilmListPage() {
        verticalLayoutField = new SkVerticalLayoutField();
        filmService = new FilmService();
        filterLayoutField = new SkFormLayoutField();
        tableDataField = new SkTableField();



        builFilterPanel();
        verticalLayoutField.addComponent(filterLayoutField);

        builTableField();
        verticalLayoutField.addComponent(tableDataField);

        addComponent(verticalLayoutField);

        verticalLayoutField.setExpandRatio(filterLayoutField, 0.3f);
        verticalLayoutField.setExpandRatio(tableDataField, 0.7f);

    }

    private void builFilterPanel() {
        SkTextField titleFilterTextField = new SkTextField();
        titleFilterTextField.setCaption("Başlık Ara..");
        titleFilterTextField.addTextChangeListener(event -> {
            String searchIdField = event.getText();
            filterLayoutField.filterSearch(searchIdField, titleStr, tableDataField);
        });

        filterLayoutField.addComponents(titleFilterTextField);


        SkTextField descriptionFilterTextField = new SkTextField();
        descriptionFilterTextField.setCaption("Açıklama Ara..");
        descriptionFilterTextField.addTextChangeListener(event -> {
            String searchIdField = event.getText();
            filterLayoutField.filterSearch(searchIdField, descriptionStr, tableDataField);
        });

        filterLayoutField.addComponents(descriptionFilterTextField);


        SkTextField languageFilterTextField = new SkTextField();
        languageFilterTextField.setCaption("Dil Adı Ara..");
        languageFilterTextField.addTextChangeListener(event -> {
            String searchIdField = event.getText();
            filterLayoutField.filterSearch(searchIdField, languageStr, tableDataField);
        });

        filterLayoutField.addComponents(languageFilterTextField);
    }

    private void builTableField() {
        tableDataField.addContainerProperty(emptyStr, SkDeleteButtonField.class, null);
        tableDataField.addContainerProperty(titleStr, String.class, null);
        tableDataField.addContainerProperty(descriptionStr, String.class, null);
        tableDataField.addContainerProperty(languageStr, String.class, null);
        tableDataField.addContainerProperty(creationDateStr, String.class, null);
        fillDataField();
        doubleClickSelectItem();
    }

    private void doubleClickSelectItem() {
        tableDataField.addItemClickListener(event -> {
            boolean isDoubleClick = event.isDoubleClick();
            if (isDoubleClick) {
                Film selectFilm = (Film) event.getItemId();
                FilmCardWindow filmCardWindow = new FilmCardWindow(selectFilm);
                MyUI.getCurrent().addWindow(filmCardWindow);
                filmCardWindow.addCloseListener(closeEvent -> fillDataField());
            }
        });
    }


    private void fillDataField() {
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

        buildDeleteField(film);
    }

    private void buildDeleteField(Film film) {
        deleteButtonField = new SkDeleteButtonField();
        deleteButtonField.setData(film);
        tableDataField.getContainerProperty(film, emptyStr).setValue(deleteButtonField);
        filmDeleteField();
    }

    private void filmDeleteField() {
        deleteButtonField.addClickListener(event -> {
            Film selectFilmField = (Film) event.getButton().getData();
            String question = selectFilmField.getTitle() + " seçili Flim silmek istediğinizden emin misiniz?";

            DialogCardWinddow dialogCardWinddow = new DialogCardWinddow(question);
            MyUI.getCurrent().addWindow(dialogCardWinddow);

            dialogCardWinddow.addCloseListener(closeEvent -> {
                boolean dialogCardWindowResult = dialogCardWinddow.getResult();
                if (dialogCardWindowResult) {
                    filmService.delete(selectFilmField);
                    fillDataField();
                }
            });
        });
    }


}
