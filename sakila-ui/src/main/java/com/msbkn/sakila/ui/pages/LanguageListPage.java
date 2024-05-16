package com.msbkn.sakila.ui.pages;

import com.msbkn.sakila.domain.Language;
import com.msbkn.sakila.service.LanguageService;
import com.msbkn.sakila.ui.*;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.common.pages.Content;
import com.msbkn.sakila.ui.pages.windows.ActorCardWindow;
import com.msbkn.sakila.ui.pages.windows.DialogCardWinddow;
import com.msbkn.sakila.ui.pages.windows.LanguageCardWindow;

import java.util.Date;
import java.util.List;

public class LanguageListPage extends SkVerticalLayoutField {
    private LanguageService languageService;
    private Content content;
    private String languageNameStr = "Dil Adı";
    private String creationDateStr = "Oluşturma Tarihi";
    private String emptyStr = " ";

    private SkTableField tableDataField;
    private SkVerticalLayoutField verticalLayoutField;
    private SkFormLayoutField filterLayoutField;
    private SkDeleteButtonField deleteButtonField;

    public LanguageListPage() {
        verticalLayoutField = new SkVerticalLayoutField();
        languageService = new LanguageService();
        filterLayoutField = new SkFormLayoutField();
        tableDataField = new SkTableField();

        builFilterPanel();
        verticalLayoutField.addComponent(filterLayoutField);

        builTableField();
        verticalLayoutField.addComponent(tableDataField);

        addComponent(verticalLayoutField);

        verticalLayoutField.setExpandRatio(filterLayoutField, 0.2f);
        verticalLayoutField.setExpandRatio(tableDataField, 0.8f);
    }

    private void builFilterPanel() {
        SkTextField languageNameFilterField = new SkTextField();
        buildItemFilterPanel(languageNameFilterField, "Dil Adı Ara..", languageNameStr, tableDataField, filterLayoutField);
        filterLayoutField.addComponent(languageNameFilterField);
    }

    private void builTableField() {
        tableDataField.addContainerProperty(emptyStr, SkDeleteButtonField.class, null);
        tableDataField.addContainerProperty(languageNameStr, String.class, null);
        tableDataField.addContainerProperty(creationDateStr, String.class, null);
        fillDataField();
        doubleClickSelectItem();
    }

    private void doubleClickSelectItem() {
        tableDataField.addItemClickListener(event -> {
            boolean isDoubleClick = event.isDoubleClick();
            if (isDoubleClick) {
                Language selectItemField = (Language) event.getItemId();
                LanguageCardWindow languageCardWindow = new LanguageCardWindow(selectItemField);

                MyUI.getCurrent().addWindow(languageCardWindow);
                languageCardWindow.addCloseListener(closeEvent -> fillDataField());
            }
        });
    }

    public void fillDataField() {
        tableDataField.removeAllItems();
        List<Language> languageList = languageService.findAll();
        for (Language language : languageList)
            addLanguageToTable(language);
    }

    private void addLanguageToTable(Language language) {
        tableDataField.addItem(language);

        String languageNameField = language.getName();
        tableDataField.getContainerProperty(language, languageNameStr).setValue(languageNameField);

        Date lastUpdate = language.getLastUpdate();
        String creationDateField = language.getDateString(lastUpdate);
        tableDataField.getContainerProperty(language, creationDateStr).setValue(creationDateField);

        buildLanguageDeleteField(language);
    }

    private void buildLanguageDeleteField(Language language) {
        deleteButtonField = new SkDeleteButtonField();
        deleteButtonField.setData(language);
        tableDataField.getContainerProperty(language, emptyStr).setValue(deleteButtonField);
        deleteButtonField.addClickListener(event -> {

            Language selectLanguageField = (Language) event.getButton().getData();
            String question = selectLanguageField.getName() + " seçili dili silmek istediğinizden emin misiniz?";

            DialogCardWinddow dialogCardWinddow = new DialogCardWinddow(question);
            MyUI.getCurrent().addWindow(dialogCardWinddow);

            dialogCardWinddow.addCloseListener(closeEvent -> {
                boolean dialogCardWinddowResult = dialogCardWinddow.getResult();
                if (dialogCardWinddowResult) {
                    languageService.delete(selectLanguageField);
                    fillDataField();
                }
            });
        });
    }

}