package com.msbkn.sakila.ui.pages.component;

import com.msbkn.sakila.domain.Language;
import com.msbkn.sakila.service.LanguageService;
import com.vaadin.ui.ComboBox;

import java.util.List;

public class LanguageComboboxField extends ComboBox {
    public LanguageComboboxField() {
        LanguageService languageService = new LanguageService();
        List<Language> languages = languageService.findAll();
        for (Language language : languages) {
            addItem(language);
            setItemCaption(language, language.getName());
        }
    }
}
