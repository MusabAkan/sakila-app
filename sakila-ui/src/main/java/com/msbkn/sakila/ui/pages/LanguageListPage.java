package com.msbkn.sakila.ui.pages;

import com.msbkn.sakila.domain.Language;
import com.msbkn.sakila.service.LanguageService;
import com.msbkn.sakila.ui.*;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.common.pages.Content;
import com.msbkn.sakila.ui.pages.common.BaseListPage;
import com.msbkn.sakila.ui.pages.windows.*;

import java.util.Date;
import java.util.List;

public class LanguageListPage extends BaseListPage {
    private LanguageService languageService;
    private String languageNameStr = "Dil Adı";
    private String languageNaneSearchStr = "Dil Adı Ara..";
    private String creationDateStr = "Oluşturma Tarihi";
    private String emptyStr = " ";

    public LanguageListPage() {
        languageService = new LanguageService();
        verticalLayoutField = new SkVerticalLayoutField();
        filterLayoutField = new SkFormLayoutField();
        tableDataField = new SkTableField();
        dialogCardField = new DialogCardWinddow();

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
        buildItemFilterPanel(languageNameFilterField, languageNaneSearchStr, languageNameStr);
        filterLayoutField.addComponent(languageNameFilterField);
    }

    private void builTableField() {
        tableDataField.addContainerProperty(emptyStr, SkDeleteButtonField.class, null);
        tableDataField.addContainerProperty(languageNameStr, String.class, null);
        tableDataField.addContainerProperty(creationDateStr, String.class, null);
        fillDataField();
        doubleClickSelectItem();
        dialogCardField.addCloseListener(closeEvent -> fillDataField());
    }

    private void doubleClickSelectItem() {
        tableDataField.addItemClickListener(event -> {
            boolean isDoubleClick = event.isDoubleClick();
            if (isDoubleClick) {
                Language selectItemField = (Language) event.getItemId();
                LanguageCardWindow languageCardWindow = new LanguageCardWindow(selectItemField);
                MyUI.getCurrent().addWindow(languageCardWindow);

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

        buildItemDeleteField(language, languageService);
        dialogCardField.addCloseListener(closeEvent -> {
            fillDataField();
        });
    }


}