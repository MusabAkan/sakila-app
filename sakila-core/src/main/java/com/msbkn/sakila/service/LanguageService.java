package com.msbkn.sakila.service;

import com.msbkn.sakila.common.BaseService;
import com.msbkn.sakila.domain.Language;

import java.util.List;

public class LanguageService extends BaseService {

    public void saveLanguage(Language language) {
        super.save(language);
    }

    public void updateLanguage(Language language) {
        super.update(language);
    }

    public Language findById(long id) {
        return super.findById(new Language(), id);
    }

    public List<Language> findAll() {
        return super.findAll(new Language());
    }

    public void deleteLanguage(Language language) {
        if (language == null) throw new NullPointerException("Dil boş..");
        super.delete(language);
    }

}
