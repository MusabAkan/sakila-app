package com.msbkn.sakila.service;

import com.msbkn.sakila.common.CommonService;
import com.msbkn.sakila.dao.LanguageDao;
import com.msbkn.sakila.domain.Language;

import java.util.List;

public class LanguageService implements CommonService<Language> {

    LanguageDao languageDao = new LanguageDao();

    @Override
    public void save(Language language) {
        languageDao.saveLanguage(language);
    }

    @Override
    public void update(Language language) {
        languageDao.updateLanguage(language);
    }

    @Override
    public Language findById(long id) {
        return languageDao.findById(id);
    }

    @Override
    public List<Language> findAll() {
        return languageDao.findAll();
    }

    @Override
    public void delete(Language language) {
        if (language == null) throw new NullPointerException("Dil boş..");
        languageDao.deleteLanguage(language);
    }


}
