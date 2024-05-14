package com.msbkn.sakila.ui.pages.component;

import com.msbkn.sakila.service.FilmService;
import com.vaadin.ui.OptionGroup;

import java.util.Set;

public class FeatureOptionField extends OptionGroup {

    public FeatureOptionField() {
        FilmService filmService = new FilmService();
        Set<String> features = filmService.findFeatureList();
        for (String feature : features) {
            addItem(feature);
        }
        setMultiSelect(true);
    }

}
