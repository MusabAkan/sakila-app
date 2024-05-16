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
    private SkVerticalLayoutField verticalLayoutField;
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

        SkFormLayoutField formLayout = new SkFormLayoutField();
        verticalLayoutField.addComponent(formLayout);

        languageIdTextField = new SkLabelField();
        languageIdTextField.setCaption("Id :");
        formLayout.addComponent(languageIdTextField);

        languageNameTextField = new SkTextField();
        languageNameTextField.setCaption("Dilin Adı :");

        formLayout.addComponent(languageNameTextField);

        languageSaveButtonField = new SkSaveButtonField();
        formLayout.addComponent(languageSaveButtonField);
        buildSaveLanguageField();

        setContent(verticalLayoutField);
    }

    private void buildSaveLanguageField() {
        languageSaveButtonField.addClickListener(clickEvent -> {

            String languageNameField = languageNameTextField.getValue();
            selectLanguageField.setName(languageNameField);

            Date languageLastUpdateField = new Date();
            selectLanguageField.setLastUpdate(languageLastUpdateField);

            Long languageFieldId = selectLanguageField.getId();

            if (languageFieldId == null)
                addLanguageField();

            else
                uptadeLanguageField();

            quit();

        });
    }

    private void addLanguageField() {
        languageService.save(selectLanguageField);
        Notification.show("Dil tarafında ekleme işlemi yapılmıştır.");
    }

    private void uptadeLanguageField() {
        languageService.update(selectLanguageField);
        Notification.show("Dil tarafında günceleme işlemi yapılmıştır.");
    }


}





