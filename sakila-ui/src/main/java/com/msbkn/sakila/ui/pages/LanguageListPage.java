package com.msbkn.sakila.ui.pages;

import com.msbkn.sakila.domain.Language;
import com.msbkn.sakila.service.LanguageService;
import com.msbkn.sakila.ui.*;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.pages.component.*;
import com.vaadin.ui.Table;

import java.util.List;

public class LanguageListPage extends SkVerticalLayoutField {
    private LanguageService languageService;

    private String languageNameStr = "Dil Adı";
    private String creationDateStr = "Oluşturma Tarihi";

    private SkTableField tableData;
    private SkVerticalLayoutField verticalLayoutField;
    private SkFormLayoutField filterLayoutField;


    public LanguageListPage() {
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

        SkTextField languageNameFilterField = new SkTextField();
        languageNameFilterField.setCaption("Dil Adı Ara..");
        languageNameFilterField.addTextChangeListener(event -> {
            String searchIdField = event.getText();
            filterLayoutField.filterSearch(searchIdField, languageNameStr, tableData);
        });

        filterLayoutField.addComponent(languageNameFilterField);

    }

    private void builTableField() {
        tableData = new SkTableField();

        tableData.addContainerProperty(languageNameStr, String.class, null);
        tableData.addContainerProperty(creationDateStr, String.class, null);

        fillData();

        doubleClickSelectItem();
    }

    private void doubleClickSelectItem() {
        tableData.addItemClickListener(event -> {

            boolean isDoubleClick = event.isDoubleClick();

            if (isDoubleClick) {
                Language selectItemField = (Language) event.getItemId();

                LanguageCardWindow languageCardWindow = new LanguageCardWindow(selectItemField);
                MyUI.getCurrent().addWindow(languageCardWindow);

            }
        });
    }


    private void fillData() {
        languageService = new LanguageService();

        Object result = languageService.findAll();

        if (result == null && result instanceof List) return;

        List<Language> languages = (List<Language>) result;

        for (Language language : languages) {
            addItemToTable(language);
        }
    }

    private void addItemToTable(Language language) {
        tableData.addItem(language);

        String languageNameField = language.getName();
        tableData.getContainerProperty(language, languageNameStr).setValue(languageNameField);


        String creationDateeField = language.toString();
        tableData.getContainerProperty(language, creationDateStr).setValue(creationDateeField);

    }


}
