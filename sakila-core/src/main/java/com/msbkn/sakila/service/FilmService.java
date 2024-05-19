package com.msbkn.sakila.service;

import com.msbkn.sakila.common.BaseService;
import com.msbkn.sakila.dao.FilmDao;
import com.msbkn.sakila.domain.Film;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Set;

public class FilmService extends BaseService<Film> {

    public FilmService() {
        super(Film.class);
    }

    @Override
    public List<Film> findAll() {
        return super.findAllParams(Restrictions.eq("deleted", false));
    }

//    public List<Film> findAllByNotDelete() {
//        return super.findAllParams(Restrictions.eq("deleted", false));
//    }

    @Override
    public void delete(Film entity) {
        entity.setDeleted(true);
        super.save(entity);
    }

    public Set<String> findRatingList() {
        //FilmDao filmDao = (FilmDao) getDao();
        FilmDao filmDao = new FilmDao();
        return filmDao.findRatingList();
    }
    public Set<String> findFeatureList() {
        //FilmDao filmDao = (FilmDao) getDao();
        FilmDao filmDao = new FilmDao();
        return filmDao.findFeatureList();
    }

}