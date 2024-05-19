package com.msbkn.sakila.ui.pages.windows;

import com.msbkn.sakila.domain.*;
import com.msbkn.sakila.service.FilmService;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.pages.component.FeatureOptionField;
import com.msbkn.sakila.ui.pages.component.LanguageComboboxField;
import com.msbkn.sakila.ui.pages.component.RatingComboboxField;
import com.vaadin.ui.Notification;

import java.util.Date;
import java.util.Set;

public class FilmCardWindow extends SkWindowField {
    private SkLabelField filmIdTextField;
    private SkTextField filmTitleTextField;
    private SkTextAreaField filmDescriptionTextField;
    private SkTextField filmYearTextField;
    private SkTextField filmDurationTextField;
    private SkTextField filmRateTextField;
    private SkTextField filmLengthTextField;
    private SkTextField filmCostTextField;
    private RatingComboboxField filmRatingComboboxField;
    private LanguageComboboxField filmLanguageComboboxField;
    private FeatureOptionField featureOptionField;
    private Film selectFilmField;
    private FilmService filmService;
    SkSaveButtonField saveButtonField;


    public FilmCardWindow() {
        buildWindowField();
    }

    public FilmCardWindow(Film film) {
        this();
        fillWindowByFilm(film);
    }

    private void fillWindowByFilm(Film film) {

        selectFilmField = film;

        String idField = String.valueOf(film.getId());
        filmIdTextField.setValue(idField);

        Language language = film.getLanguage();
        filmLanguageComboboxField.setValue(language);

        String ratingField = String.valueOf(film.getRating());
        filmRatingComboboxField.setValue(ratingField);

        String titleField = film.getTitle();
        filmTitleTextField.setValue(titleField);

        fillFeatureField(film.getFeatureList());

        String yearField = String.valueOf(film.getYear());
        filmYearTextField.setValue(yearField);

        String descriptionField = film.getDescription();
        filmDescriptionTextField.setValue(descriptionField);

        String durationField = String.valueOf(film.getDuration());
        filmDurationTextField.setValue(durationField);

        String rateField = String.valueOf(film.getRate());
        filmRateTextField.setValue(rateField);

        String lengthField = String.valueOf(film.getLength());
        filmLengthTextField.setValue(lengthField);

        String costField = String.valueOf(film.getCost());
        filmCostTextField.setValue(costField);
    }

    private void buildWindowField() {
        verticalLayoutField = new SkVerticalLayoutField();
        formLayoutField = new SkFormLayoutField();
        filmService = new FilmService();
        selectFilmField = new Film();

        setHeight("90%");
        setWidth("40%");

        verticalLayoutField.addComponent(formLayoutField);

        filmIdTextField = new SkLabelField();
        filmIdTextField.setCaption("Id :");
        formLayoutField.addComponent(filmIdTextField);

        filmTitleTextField = new SkTextField();
        filmTitleTextField.setCaption("Başlık :");
        formLayoutField.addComponent(filmTitleTextField);

        filmLanguageComboboxField = new LanguageComboboxField();
        filmLanguageComboboxField.setCaption("Dil :");
        formLayoutField.addComponent(filmLanguageComboboxField);

        filmDescriptionTextField = new SkTextAreaField();
        filmDescriptionTextField.setCaption("Açıklama:");
        formLayoutField.addComponent(filmDescriptionTextField);

        filmRatingComboboxField = new RatingComboboxField();
        filmRatingComboboxField.setCaption("Değerlendirme : ");
        formLayoutField.addComponent(filmRatingComboboxField);

        featureOptionField = new FeatureOptionField();
        featureOptionField.setCaption("Özellikler : ");
        formLayoutField.addComponent(featureOptionField);

        filmDurationTextField = new SkTextField();
        filmDurationTextField.setCaption("Süre : ");
        formLayoutField.addComponent(filmDurationTextField);

        filmYearTextField = new SkTextField();
        filmYearTextField.setCaption("Yıl : ");
        formLayoutField.addComponent(filmYearTextField);

        filmLengthTextField = new SkTextField();
        filmLengthTextField.setCaption("Uzunluk : ");
        formLayoutField.addComponent(filmLengthTextField);

        filmCostTextField = new SkTextField();
        filmCostTextField.setCaption("Maliyet : ");
        formLayoutField.addComponent(filmCostTextField);

        filmRateTextField = new SkTextField();
        filmRateTextField.setCaption("Oran : ");
        formLayoutField.addComponent(filmRateTextField);

        saveButtonField = new SkSaveButtonField();
        formLayoutField.addComponent(saveButtonField);
        buildSaveFilmField();

        setContent(verticalLayoutField);
    }

    private void buildSaveFilmField() {
        saveButtonField.addClickListener(clickEvent -> {
            String titleField = filmTitleTextField.getValue();
            selectFilmField.setTitle(titleField);

            String descriptionField = filmDescriptionTextField.getValue();
            selectFilmField.setDescription(descriptionField);

            String durationField = filmDurationTextField.getValue();
            selectFilmField.setDuration(Integer.parseInt(durationField));

            String rateField = filmRateTextField.getValue();
            selectFilmField.setRate(Double.parseDouble(rateField));

            String lengthField = filmLengthTextField.getValue();
            selectFilmField.setLength(Long.parseLong(lengthField));

            String costField = filmCostTextField.getValue();
            selectFilmField.setCost(Double.parseDouble(costField));

            String yearField = filmYearTextField.getValue();
            selectFilmField.setYear(Integer.parseInt(yearField));

            Set<String> optionsFieldValue = (Set<String>) featureOptionField.getValue();
            selectFilmField.setFeatures(writeFeatureField(optionsFieldValue));

            Language languageField = (Language) filmLanguageComboboxField.getValue();
            selectFilmField.setLanguage(languageField);

            String ratingField = filmRatingComboboxField.getValue().toString();
            selectFilmField.setRating(ratingField);
            selectFilmField.setDeleted(false);

            Date filmLastUpdateField = new Date();
            selectFilmField.setLastUpdate(filmLastUpdateField);

            filmService.save(selectFilmField);
            Notification.show("Film kaydetme işlemi yapılmıştır");
            quit();

        });
    }


    private void fillFeatureField(String[] features) {
        for (String feature : features)
            featureOptionField.select(feature);
    }

    private String writeFeatureField(Set<String> features) {
        int size = features.size();
        if (size == 0) return "";
        String text = "";
        for (String feature : features)
            text += feature + ",";
        int endIndex = text.length() - 1;
        text = text.substring(0, endIndex);
        return text;
    }
}
