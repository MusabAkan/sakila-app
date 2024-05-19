package com.msbkn.sakila.ui.pages.windows;

import com.msbkn.sakila.domain.Language;
import com.msbkn.sakila.service.LanguageService;
import com.msbkn.sakila.ui.common.components.*;
import com.vaadin.ui.Notification;


import java.util.Date;

public class LanguageCardWindow extends SkWindowField {
    private SkLabelField languageIdTextField;
    private SkTextField languageNameTextField;
    private SkSaveButtonField languageSaveButtonField;
    private LanguageService languageService;
    private Language selectLanguageField;

    public LanguageCardWindow() {
        buildWindowField();
    }

    public LanguageCardWindow(Language language) {
        this();
        fillWindowByLanguage(language);
    }

    private void fillWindowByLanguage(Language language) {
        selectLanguageField = language;

        String idField = String.valueOf(language.getId());
        languageIdTextField.setValue(idField);

        String languageNameField = language.getName();
        languageNameTextField.setValue(languageNameField);

    }

    private void buildWindowField() {
        selectLanguageField = new Language();
        verticalLayoutField = new SkVerticalLayoutField();
        languageService = new LanguageService();
        formLayoutField = new SkFormLayoutField();

        verticalLayoutField.addComponent(formLayoutField);

        languageIdTextField = new SkLabelField();
        languageIdTextField.setCaption("Id :");
        formLayoutField.addComponent(languageIdTextField);

        languageNameTextField = new SkTextField();
        languageNameTextField.setCaption("Dilin Adı :");

        formLayoutField.addComponent(languageNameTextField);

        languageSaveButtonField = new SkSaveButtonField();
        formLayoutField.addComponent(languageSaveButtonField);
        buildSaveLanguageField();

        setContent(verticalLayoutField);
    }

    private void buildSaveLanguageField() {
        languageSaveButtonField.addClickListener(clickEvent -> {

            String languageNameField = languageNameTextField.getValue();
            selectLanguageField.setName(languageNameField);

            Date languageLastUpdateField = new Date();
            selectLanguageField.setLastUpdate(languageLastUpdateField);

            languageService.save(selectLanguageField);
            Notification.show("Dil tarafında kaydetme işlemi yapılmıştır.");

            quit();

        });
    }


}





