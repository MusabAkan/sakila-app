package com.msbkn.sakila.dao;

import com.msbkn.sakila.common.GenericDao;
import com.msbkn.sakila.domain.Language;

import java.util.List;

public class LanguageDao extends GenericDao {

    public void saveLanguage(Language language) {
        save(language);
    }

    public void updateLanguage(Language language) {
        update(language);
    }

    public void deleteLanguage(Language language) {
        delete(language);
    }
    public Language findById(long id) {
         return findById(new Language(), id);
    }

    public List<Language> findAll() {
        return findAll(new Language());
    }
}
