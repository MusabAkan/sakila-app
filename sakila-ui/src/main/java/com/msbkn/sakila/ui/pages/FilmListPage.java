package com.msbkn.sakila.ui.pages;

import com.msbkn.sakila.domain.Film;
import com.msbkn.sakila.service.FilmService;
import com.msbkn.sakila.ui.MyUI;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.pages.component.FilmCardWindow;

import java.util.List;

public class FilmListPage extends SkVerticalLayoutField {
    private FilmService filmService;

    private String titleStr = "Başlık";
    private String descriptionStr = "Kısa Açıklama";
    private String languageStr = "Dil";
    private String creationDateStr = "Oluşturma Tarihi";

    private SkTableField tableData;
    private SkVerticalLayoutField verticalLayoutField;
    private SkFormLayoutField filterLayoutField;


    public FilmListPage() {

        verticalLayoutField = new SkVerticalLayoutField();

        builFilterPanel();
        verticalLayoutField.addComponent(filterLayoutField);

        builTableField();
        verticalLayoutField.addComponent(tableData);

        addComponent(verticalLayoutField);

        verticalLayoutField.setExpandRatio(filterLayoutField, 0.3f);
        verticalLayoutField.setExpandRatio(tableData, 0.7f);

    }

    private void builFilterPanel() {
        filterLayoutField = new SkFormLayoutField();

        SkTextField titleFilterTextField = new SkTextField();
        titleFilterTextField.setCaption("Başlık Ara..");
        titleFilterTextField.addTextChangeListener(event -> {
            String searchIdField = event.getText();
            filterLayoutField.filterSearch(searchIdField, titleStr, tableData);
        });

        filterLayoutField.addComponents(titleFilterTextField);


        SkTextField descriptionFilterTextField = new SkTextField();
        descriptionFilterTextField.setCaption("Açıklama Ara..");
        descriptionFilterTextField.addTextChangeListener(event -> {
            String searchIdField = event.getText();
            filterLayoutField.filterSearch(searchIdField, descriptionStr, tableData);
        });

        filterLayoutField.addComponents(descriptionFilterTextField);


        SkTextField languageFilterTextField = new SkTextField();
        languageFilterTextField.setCaption("Dil Adı Ara..");
        languageFilterTextField.addTextChangeListener(event -> {
            String searchIdField = event.getText();
            filterLayoutField.filterSearch(searchIdField, languageStr, tableData);
        });

        filterLayoutField.addComponents(languageFilterTextField);

    }

    private void builTableField() {
        tableData = new SkTableField();

        tableData.addContainerProperty(titleStr, String.class, null);
        tableData.addContainerProperty(descriptionStr, String.class, null);
        tableData.addContainerProperty(languageStr, String.class, null);
        tableData.addContainerProperty(creationDateStr, String.class, null);

        doubleClickSelectItem();

        fillData();
    }

    private void doubleClickSelectItem() {
        tableData.addItemClickListener(event -> {

            boolean isDoubleClick = event.isDoubleClick();

            if (isDoubleClick) {
                Film selectFilm = (Film) event.getItemId();

               FilmCardWindow FilmCardWindow = new FilmCardWindow(selectFilm);
               MyUI.getCurrent().addWindow(FilmCardWindow);

            }
        });
    }


    private void fillData() {
        filmService = new FilmService();

        Object result = filmService.findAll();

        if (result == null && result instanceof List) return;

        List<Film> films = (List<Film>) result;

        for (Film film : films) {
            addItemToGrid(film);
        }
    }

    private void addItemToGrid(Film film) {
        tableData.addItem(film);

        String firstNameField = film.getTitle();
        tableData.getContainerProperty(film, titleStr).setValue(firstNameField);

        String lastNameField = film.getDescription25Limt();
        tableData.getContainerProperty(film, descriptionStr).setValue(lastNameField);

        String languageNameField = film.getLanguageName();
        tableData.getContainerProperty(film, languageStr).setValue(languageNameField);

        String creationDateField = film.toString();
        tableData.getContainerProperty(film, creationDateStr).setValue(creationDateField);

    }


}
