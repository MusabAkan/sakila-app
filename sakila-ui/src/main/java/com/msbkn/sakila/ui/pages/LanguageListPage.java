package com.msbkn.sakila.ui.pages;

import com.msbkn.sakila.domain.Language;
import com.msbkn.sakila.service.LanguageService;
import com.msbkn.sakila.ui.*;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.pages.component.*;
import com.vaadin.ui.Notification;

import java.util.List;

public class LanguageListPage extends SkVerticalLayoutField {
    private LanguageService languageService;

    private String languageNameStr = "Dil Adı";
    private String creationDateStr = "Oluşturma Tarihi";
    private String emptyStr = " ";

    private SkTableField tableData;
    private SkVerticalLayoutField verticalLayoutField;
    private SkFormLayoutField filterLayoutField;
    private SkDeleteButtonField deleteButtonField;


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
        tableData.addContainerProperty(emptyStr, SkDeleteButtonField.class, null);
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
                languageCardWindow.addCloseListener(closeEvent -> fillData());
            }
        });
    }


    public void fillData() {
        languageService = new LanguageService();
        tableData.removeAllItems();

        Object result = languageService.findAll();

        if (result == null && result instanceof List) return;

        List<Language> languages = (List<Language>) result;

        for (Language language : languages) {
            addItemToTable(language);
        }
    }

    private void languageDeleteField(Language language) {
        LanguageService languageService = new LanguageService();
        String question = language.getName() + " seçili dili silmek istediğinizden emin misiniz?";

        DialogCardWinddow dialogCardWinddow = new DialogCardWinddow(question);
        MyUI.getCurrent().addWindow(dialogCardWinddow);

        dialogCardWinddow.addCloseListener(closeEvent -> {
            boolean dialogCardWinddowResult = dialogCardWinddow.getResult();
            if (dialogCardWinddowResult) {
                languageService.deleteLanguage(language);
                fillData();
            }
        });
    }


    private void addItemToTable(Language language) {
        tableData.addItem(language);

        String languageNameField = language.getName();
        tableData.getContainerProperty(language, languageNameStr).setValue(languageNameField);

        String creationDateField = language.toString();
        tableData.getContainerProperty(language, creationDateStr).setValue(creationDateField);

        deleteButtonField = new SkDeleteButtonField();
        deleteButtonField.setData(language);

        tableData.getContainerProperty(language, emptyStr).setValue(deleteButtonField);

        deleteButtonField.addClickListener(event -> {
            Language selectLanguageField = (Language) event.getButton().getData();
            languageDeleteField(selectLanguageField);
        });

    }


}
