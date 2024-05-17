package com.msbkn.sakila.service;

import com.msbkn.sakila.common.BaseDao;
import com.msbkn.sakila.dao.FilmDao;
import com.msbkn.sakila.domain.Film;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class FilmService extends BaseDao {

    private static final Log log = LogFactory.getLog(FilmService.class);

    public void saveFilm(Film film) {
        save(film);
    }

    public Film findById(long id) {
        return super.findAllParams(
                new Film(),
                Restrictions.eq("deleted", false),
                Restrictions.eq("id", id)
        ).get(0);
    }

    public List<Film> findAll() {
        return super.findAllParams(
                new Film(),
                Restrictions.eq("deleted", false)
        );
    }

    public Set<String> findRatingList() {
        FilmDao filmDao = new FilmDao();
        return filmDao.findRatingList();
    }

    public Set<String> findFeatureList() {
        FilmDao filmDao = new FilmDao();
        return filmDao.findFeatureList();
    }

    public void deleteFilm(Film film) {
        if (film == null) throw new NullPointerException("Film boş");
        film.setDeleted(true);
        super.update(film);
    }

    public void updateFilm(Film film) {
        if (film == null) throw new NullPointerException("Film boş..");
        super.update(film);
    }
}


