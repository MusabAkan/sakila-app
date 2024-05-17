package com.msbkn.sakila.ui.pages;

import com.msbkn.sakila.domain.Language;
import com.msbkn.sakila.service.LanguageService;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.pages.common.BaseListPage;

import java.util.Date;
import java.util.List;

public class LanguageListPage extends BaseListPage {

    private LanguageService languageService;
    private String languageNameStr = "Dil Adı";
    private String languageNaneSearchStr = "Dil Adı Ara..";
    private String creationDateStr = "Oluşturma Tarihi";

    public LanguageListPage() {

        languageService = new LanguageService();

        builFilterPanel();
        verticalLayoutField.addComponent(filterLayoutField);

        builTableField();
        verticalLayoutField.addComponent(tableDataField);

        addComponent(verticalLayoutField);

        verticalLayoutField.setExpandRatio(filterLayoutField, 0.2f);
        verticalLayoutField.setExpandRatio(tableDataField, 0.8f);
    }

    private void builFilterPanel() {
        addItemTextFilterPanel(languageNaneSearchStr, languageNameStr);
    }

    private void builTableField() {
        addTableColumn(emptyStr, SkDeleteButtonField.class, null);
        addTableColumn(languageNameStr, String.class, null);
        addTableColumn(creationDateStr, String.class, null);
        fillDataField();
        doubleClickSelectItem();
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

        buildItemDeleteField(language, languageService);
    }
}