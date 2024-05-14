package com.msbkn.sakila.ui.pages.component;

import com.msbkn.sakila.domain.*;
import com.msbkn.sakila.ui.common.components.*;

import java.util.Set;

public class FilmCardWindow extends SkWindowField {
    private Film selectFilmField;

    private SkLabelField idLabelField;
    private SkTextField titleTextField;
    private SkTextAreaField descriptionTextField;
    private SkTextField yearTextField;
    private SkTextField durationTextField;
    private SkTextField rateTextField;
    private SkTextField lengthTextField;
    private SkTextField costTextField;
    private RatingComboboxField ratingComboboxField;
    private LanguageComboboxField languageComboboxField;
    private FeatureOptionField featureOptionField;


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

        selectFilmField = film;

        String idField = String.valueOf(film.getId());
        idLabelField.setValue(idField);

        Language language = film.getLanguage();
        languageComboboxField.setValue(language);


        String ratingField = String.valueOf(film.getRating());
        ratingComboboxField.setValue(ratingField);

        String titleField = film.getTitle();
        titleTextField.setValue(titleField);

        getFeatureField(film.getFeatureList());

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

        languageComboboxField = new LanguageComboboxField();
        languageComboboxField.setCaption("Dil :");
        formLayout.addComponent(languageComboboxField);

        descriptionTextField = new SkTextAreaField();
        descriptionTextField.setCaption("Açıklama:");
        formLayout.addComponent(descriptionTextField);

        ratingComboboxField = new RatingComboboxField();
        ratingComboboxField.setCaption("Değerlendirme : ");
        formLayout.addComponent(ratingComboboxField);

        featureOptionField = new FeatureOptionField();
        featureOptionField.setCaption("Özellikler : ");
        formLayout.addComponent(featureOptionField);

        durationTextField = new SkTextField();
        durationTextField.setCaption("Süre : ");
        formLayout.addComponent(durationTextField);

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
        saveFilmField();

        setContent(verticalLayout);


    }

    private void saveFilmField() {
        saveButtonField.addClickListener(clickEvent -> {
            Film film = new Film();

            String idField = String.valueOf(idLabelField.getValue());
            film.setId(Long.valueOf(idField));

            String titleField = titleTextField.getValue();
            film.setTitle(titleField);

            String descriptionField = descriptionTextField.getValue();
            film.setDescription(descriptionField);

            String durationField = durationTextField.getValue();
            film.setDuration(Integer.parseInt(durationField));

            String rateField = rateTextField.getValue();
            film.setRate(Double.parseDouble(rateField));

            String lengthField = lengthTextField.getValue();
            film.setLength(Long.parseLong(lengthField));

            String costField = costTextField.getValue();
            film.setCost(Double.parseDouble(costField));

            String yearField = yearTextField.getValue();
            film.setYear(Integer.parseInt(yearField));

            Set<String> optionsFieldValue = (Set<String>) featureOptionField.getValue();
            film.setFeatures(setFeatureField(optionsFieldValue));

            Language languageField = (Language) languageComboboxField.getValue();
            film.setLanguage(languageField);

            String ratingField = ratingComboboxField.getValue().toString();
            film.setRating(ratingField);

            if (selectFilmField == null) {

            }
            else
            {

            }


        });
    }

    private void getFeatureField(String[] features) {

        for (String feature : features) {
            featureOptionField.select(feature);
        }

    }

    private String setFeatureField(Set<String> features) {

        int size = features.size();
        if (size == 0) return "";

        String text = "";
        for (String feature : features) {
            text += feature + ",";
        }
        int endIndex = text.length() - 1;
        text = text.substring(0, endIndex);
        return text;
    }


}
