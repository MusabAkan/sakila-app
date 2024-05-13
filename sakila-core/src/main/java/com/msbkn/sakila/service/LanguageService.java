package com.msbkn.sakila.service;

import com.msbkn.sakila.dao.LanguageDao;
import com.msbkn.sakila.domain.Language;

import java.util.List;

public class LanguageService {

    LanguageDao languageDao = new LanguageDao();

    public void saveLanguage(Language language) {
        languageDao.saveLanguage(language);
    }

    public Language findById(long id) {
        return languageDao.findById(id);
    }

    public List<Language> findAll() {
        return languageDao.findAll();
    }
}
