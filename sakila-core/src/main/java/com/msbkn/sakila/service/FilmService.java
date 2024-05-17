package com.msbkn.sakila.service;

import com.msbkn.sakila.common.BaseService;
import com.msbkn.sakila.dao.FilmDao;
import com.msbkn.sakila.domain.Film;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Set;

public class FilmService extends BaseService<Film, FilmDao> {

    public FilmService() {
        super(new FilmDao());
    }

    public Film save(Film film) {
        return getDaoClazz().save(film);
    }

    public Film findById(long id) {
        return getDaoClazz().findAllParams(
                Restrictions.eq("deleted", false),
                Restrictions.eq("id", id)
        ).get(0);
    }

    public List<Film> findAll() {
        return getDaoClazz().findAllParams(
                Restrictions.eq("deleted", false)
        );
    }

    public Set<String> findRatingList() {
        return ((FilmDao) getDaoClazz()).findRatingList();
    }

    public Set<String> findFeatureList() {
        return getDaoClazz().findFeatureList();
    }

    public void delete(Film film) {
        if (film == null) throw new NullPointerException("Film boş");
        film.setDeleted(true);
        getDaoClazz().save(film);
    }
}