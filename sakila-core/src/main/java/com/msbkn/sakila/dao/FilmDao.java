package com.msbkn.sakila.dao;

import com.msbkn.sakila.common.BaseDao;
import com.msbkn.sakila.common.HibernateUtil;
import com.msbkn.sakila.domain.Film;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilmDao extends BaseDao<Film> {

    public FilmDao() {
        super(Film.class);
    }


    public Set<String> findRatingList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("select distinct(rating) as rating from sakila.film");
        return fillSetList(query.list());
    }

    public Set<String> findFeatureList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("select distinct(special_features) as feature from sakila.film");
        return fillSetList(query.list());
    }

    private Set fillSetList(List<String> strings) {
        Set stringSet = new HashSet<>();
        for (String string : strings) {
            String[] splits = string.split(",");
            for (String split : splits)
                stringSet.add(split);
        }
        return stringSet;
    }
}
