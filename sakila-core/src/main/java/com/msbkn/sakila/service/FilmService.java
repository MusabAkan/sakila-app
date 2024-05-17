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

//    public Film findById(long id) {
//        Criterion[] criterionCriteria = {
//                Restrictions.eq("deleted", false),
//                Restrictions.eq("id", id)
//        };
//        return super.findAllParams(criterionCriteria).get(0);
//    }

    @Override
    public List<Film> findAll() {
        return super.findAllParams(Restrictions.eq("deleted", false));
    }

    public Set<String> findRatingList() {
        FilmDao filmDao = (FilmDao) getDao();
        return filmDao.findRatingList();
    }

    public Set<String> findFeatureList() {
        FilmDao filmDao = (FilmDao) getDao();
        return filmDao.findFeatureList();
    }
}