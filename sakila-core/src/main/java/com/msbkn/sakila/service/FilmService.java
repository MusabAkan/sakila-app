package com.msbkn.sakila.service;

import com.msbkn.sakila.dao.FilmDao;
import com.msbkn.sakila.domain.Film;

import java.util.List;

public class FilmService {
    FilmDao filmDao = new FilmDao();
    public void saveFilm(Film film) {
        filmDao.saveFilm(film);
    }

    public Film findById(long id) {
        return filmDao.findById(id);
    }
    public List<Film> findAll() {
        return filmDao.findAll();
    }
}
