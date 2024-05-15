package com.msbkn.sakila.dao;

import com.msbkn.sakila.common.GenericDao;
import com.msbkn.sakila.domain.FilmActor;

import java.util.List;

public class FilmActorDao extends GenericDao {

    public void saveflimActor(FilmActor filmActor) {
        save(filmActor);
    }

    public FilmActor findById(int id) {
        return findById(new FilmActor(), id);
    }

    public List<FilmActor> findAll() {
        return findAll(new FilmActor());
    }
}
