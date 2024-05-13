package com.msbkn.sakila.service;

import com.msbkn.sakila.domain.Language;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class LanguageServiceTest {
    LanguageService languageService;
    String text;

    @Test
    public void save() {
        languageService = new LanguageService();
        Language language = new Language("Japanese", new Date());
       languageService.saveLanguage(language);
    }

    @Test
    public void getLanguageById() {
        languageService = new LanguageService();
        Language language = languageService.findById(5);
        text = language.getId() + " " + language.getName() + " "   + language.getLastUpdate();
        System.out.println(text);
    }

    @Test
    public void getAllLanguages() {
        languageService = new LanguageService();
        List<Language> languages = languageService.findAll();
        for (Language language : languages) {
            text = language.getId() + " " + language.getName() + " "   + language.getLastUpdate();
            System.out.println(text);
        }

    }

}