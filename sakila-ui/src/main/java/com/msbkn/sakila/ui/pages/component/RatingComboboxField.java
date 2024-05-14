package com.msbkn.sakila.ui.pages.component;

import com.msbkn.sakila.service.FilmService;
import com.vaadin.ui.ComboBox;

import java.util.List;

public class RatingComboboxField extends ComboBox {
    public RatingComboboxField() {
        FilmService filmService = new FilmService();
        List<String> ratings = filmService.findRatingList();
        for (String rating : ratings) {
            addItem(rating);
        }
    }
}
