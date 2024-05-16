package com.msbkn.sakila.service;

import com.msbkn.sakila.dao.FilmDao;
import com.msbkn.sakila.domain.Film;

import java.util.List;
import java.util.Set;

public class FilmService {
    FilmDao filmDao = new FilmDao();


    public void save(Film film) {
        if (film == null) throw new NullPointerException("Film boş..");
        filmDao.saveFilm(film);
    }


    public Film findById(long id) {
        return filmDao.findById(id);
    }


    public List<Film> findAll() {
        return filmDao.findAll();
    }

    public Set<String> findRatingList() {
        return filmDao.findRatingList();
    }

    public Set<String> findFeatureList() {
        return filmDao.findFeatureList();
    }


    public void delete(Film film) {
        if (film == null) throw new NullPointerException("Film boş");
        filmDao.delete(film);
    }


    public void update(Film film) {
        if (film == null) throw new NullPointerException("Film boş..");
        filmDao.updateFilm(film);
    }
}


