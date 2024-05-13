package com.msbkn.sakila.ui.pages.component;

import com.msbkn.sakila.domain.Language;
import com.msbkn.sakila.ui.common.components.*;
import com.vaadin.ui.FormLayout;

import com.vaadin.ui.*;

public class LanguageCardWindow extends Window {
    SkLabelField idTextField;;
    SkTextField languageNameTextField;
    SkSaveButtonField saveButtonField;

    public LanguageCardWindow() {
        buildWindow();
    }

    public LanguageCardWindow(Language language) {
        this();
        fillWindowByLanguage(language);
    }

    private void fillWindowByLanguage(Language language) {

        String idField = String.valueOf(language.getId());
        idTextField.setValue(idField);

        String languageNameField = language.getName();
        languageNameTextField.setValue(languageNameField);

    }


    private void buildWindow() {
        SkVerticalLayoutField verticalLayout = new SkVerticalLayoutField();
        setModal(true);
        setWidth("30%");
        setWidth("35%");

        FormLayout formLayout = new FormLayout();
        verticalLayout.addComponent(formLayout);

        idTextField = new SkLabelField();
        idTextField.setCaption("Id :");
        formLayout.addComponent(idTextField);


        languageNameTextField = new SkTextField();
        languageNameTextField.setCaption("Dilin Adı :");
        formLayout.addComponent(languageNameTextField);

        saveButtonField = new SkSaveButtonField();
        formLayout.addComponent(saveButtonField);

        setContent(verticalLayout);


    }
}





