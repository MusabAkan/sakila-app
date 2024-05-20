package com.msbkn.sakila.service;

import com.msbkn.sakila.common.BaseService;
import com.msbkn.sakila.domain.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class FilmActorService extends BaseService<FilmActor> {

    public FilmActorService() {
        super(FilmActor.class);
    }

    public List<FilmActor> findAllByFilm(Film film) {
        return super.findAllParams(Restrictions.eq("film", film));
    }
}