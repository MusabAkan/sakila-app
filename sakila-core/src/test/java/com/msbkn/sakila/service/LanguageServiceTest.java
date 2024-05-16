package com.msbkn.sakila.service;

import com.msbkn.sakila.domain.Language;
import org.junit.*;

import java.util.Date;
import java.util.List;

public class LanguageServiceTest {
    LanguageService languageService;
    String text;

    @Test
    public void saveLanguage() {
        languageService = new LanguageService();
        Language language = new Language();
        language.setName("Kenya");
        language.setLastUpdate(new Date());
        languageService.saveLanguage(language);
    }

    @Test
    public void getLanguageById() {
        languageService = new LanguageService();
        Language language = languageService.findById(5);
        text = language.getId() + " " + language.getName() + " " + language.getLastUpdate();
        System.out.println(text);
    }

    @Test
    public void getAllLanguages() {
        languageService = new LanguageService();
        List<Language> languages = languageService.findAll();
        for (Language language : languages) {
            text = language.getId() + " " + language.getName() + " " + language.getLastUpdate();
            System.out.println(text);
        }
    }

    @Test
    public void updateLanguage() {
        languageService = new LanguageService();
        Language language = languageService.findById(10);
        language.setLastUpdate(new Date());
        language.setName("New Language");
        languageService.updateLanguage(language);
    }

    @Test
    public void deleteLanguage() {
        languageService = new LanguageService();
        Language language = languageService.findById(10);
        languageService.deleteLanguage(language);

    }


}