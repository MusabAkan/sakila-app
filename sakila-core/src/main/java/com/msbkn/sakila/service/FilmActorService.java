package com.msbkn.sakila.service;

import com.msbkn.sakila.common.BaseService;
import com.msbkn.sakila.domain.FilmActor;

import java.util.List;

public class FilmActorService extends BaseService {

    public void saveFlimActor(FilmActor filmActor) {
        super.save(filmActor);
    }

    public FilmActor findById(int id) {
        return super.findById(new FilmActor(), id);
    }

    public List<FilmActor> findAll() {
        return super.findAll(new FilmActor());
    }
}
