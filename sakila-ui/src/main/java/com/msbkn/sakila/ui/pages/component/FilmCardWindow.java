package com.msbkn.sakila.ui.pages.component;

import com.msbkn.sakila.domain.Film;
import com.msbkn.sakila.ui.common.components.*;
import com.vaadin.ui.FormLayout;

public class FilmCardWindow extends SkWindowField {
    SkLabelField idTextField;
    SkTextField languageNameTextField;
    SkTextField titleTextField;
    SkTextField descriptionTextField;
    SkTextField yearTextField;
    SkTextField durationTextField;
    SkTextField rateTextField;
    SkTextField lengthTextField;

    SkTextField costTextField;

    SkSaveButtonField saveButtonField;
    SkVerticalLayoutField verticalLayout;

    public FilmCardWindow() {
        buildWindow();
    }

    public FilmCardWindow(Film film) {
        this();
        fillWindowByFilm(film);
    }

    private void fillWindowByFilm(Film film) {

        String idField = String.valueOf(film.getId());
        idTextField.setValue(idField);

        String languageNameField = film.getLanguageName();
        languageNameTextField.setValue(languageNameField);



    }


    private void buildWindow() {
        verticalLayout = new SkVerticalLayoutField();

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
