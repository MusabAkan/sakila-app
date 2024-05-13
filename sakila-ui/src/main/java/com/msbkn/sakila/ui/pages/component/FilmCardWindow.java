package com.msbkn.sakila.ui.pages.component;

import com.msbkn.sakila.domain.Film;
import com.msbkn.sakila.service.FilmService;
import com.msbkn.sakila.service.LanguageService;
import com.msbkn.sakila.ui.common.components.*;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;

import java.util.List;

public class FilmCardWindow extends SkWindowField {
    SkLabelField idLabelField;
    SkTextField titleTextField;
    SkTextAreaField descriptionTextField;
    SkTextField yearTextField;
    SkTextField durationTextField;
    SkTextField rateTextField;
    SkTextField lengthTextField;
    SkTextField costTextField;
    SkComboboxField ratingComboboxField;
    SkComboboxField languageComboboxField;


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
        idLabelField.setValue(idField);

        String titleField = film.getTitle();
        titleTextField.setValue(titleField);

        String yearField = String.valueOf(film.getYear());
        yearTextField.setValue(yearField);

        String descriptionField = film.getDescription();
        descriptionTextField.setValue(descriptionField);

        String durationField = String.valueOf(film.getDuration());
        durationTextField.setValue(durationField);

        String rateField = String.valueOf(film.getRate());
        rateTextField.setValue(rateField);

        String lengthField = String.valueOf(film.getLength());
        lengthTextField.setValue(lengthField);

        String costField = String.valueOf(film.getCost());
        costTextField.setValue(costField);


    }


    private void buildWindow() {
        verticalLayout = new SkVerticalLayoutField();

        setHeight("90%");
        setWidth("40%");

        SkFormLayoutField formLayout = new SkFormLayoutField();

        verticalLayout.addComponent(formLayout);

        idLabelField = new SkLabelField();
        idLabelField.setCaption("Id :");
        formLayout.addComponent(idLabelField);

        titleTextField = new SkTextField();
        titleTextField.setCaption("Başlık :");
        formLayout.addComponent(titleTextField);

        buildLanguageCombobox();
        formLayout.addComponent(languageComboboxField);

        descriptionTextField = new SkTextAreaField();
        descriptionTextField.setCaption("Açıklama:");
        formLayout.addComponent(descriptionTextField);

        durationTextField = new SkTextField();
        durationTextField.setCaption("Süre : ");
        formLayout.addComponent(durationTextField);

        buildRatingCombobox();
        formLayout.addComponent(ratingComboboxField);

        yearTextField = new SkTextField();
        yearTextField.setCaption("Yıl : ");
        formLayout.addComponent(yearTextField);

        lengthTextField = new SkTextField();
        lengthTextField.setCaption("Uzunluk : ");
        formLayout.addComponent(lengthTextField);

        costTextField = new SkTextField();
        costTextField.setCaption("Maliyet : ");
        formLayout.addComponent(costTextField);

        rateTextField = new SkTextField();
        rateTextField.setCaption("Oran : ");
        formLayout.addComponent(rateTextField);


        saveButtonField = new SkSaveButtonField();
        formLayout.addComponent(saveButtonField);

        setContent(verticalLayout);


    }

    private void buildRatingCombobox() {
        FilmService filmService = new FilmService();
        List<String> ratingList = filmService.findRatingList();
        ratingComboboxField = new SkComboboxField(ratingList);
        ratingComboboxField.setCaption("Değerlendirme : ");
    }

    private void buildLanguageCombobox() {
        LanguageService languageService = new LanguageService();
        List<String> languageList = languageService.findLanguageList();
        languageComboboxField = new SkComboboxField(languageList);
        languageComboboxField. setCaption("Dil :");
    }
}
