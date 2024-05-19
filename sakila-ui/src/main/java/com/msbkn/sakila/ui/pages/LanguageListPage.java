package com.msbkn.sakila.ui.pages;

import com.msbkn.sakila.domain.Language;
import com.msbkn.sakila.service.LanguageService;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.pages.common.BaseListPage;

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
        addItemTableColumn(emptyStr, SkDeleteButtonField.class, null);
        addItemTableColumn(languageNameStr, String.class, null);
        addItemTableColumn(creationDateStr, String.class, null);
        fillDataField();
        doubleClickSelectItem();
    }

    public void fillDataField() {
        removeTableAllField();
        List<Language> languageList = languageService.findAll();
        for (Language language : languageList) {
            addItemTableData(language, language.getName(), languageNameStr);
            addItemTableData(language, language.getDateString(), creationDateStr);
            buildItemDeleteField(language, languageService);
        }
    }
}