package com.msbkn.sakila.dao;

import com.msbkn.sakila.common.HibernateUtil;
import com.msbkn.sakila.domain.Film;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilmDao {
    public void saveFilm(Film Film) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(Film);
        session.getTransaction().commit();
    }

    public Film findById(long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Film) session.get(Film.class, id);
    }

    public List<Film> findAll() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Film.class);
        return criteria.list();
    }


    public List<String> findRatingList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sqlQuery = "select DISTINCT( rating) as rating from sakila.film";
        Query query = session.createSQLQuery(sqlQuery);
        return query.list();
    }

    public Set<String> findFeatureList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sqlQuery = "select distinct( special_features) as feature from sakila.film";
        Query query = session.createSQLQuery(sqlQuery);
        return fillFeature(query.list());
    }

    private Set fillFeature(List<String> features) {
        Set featureSet = new HashSet<>();
        for (String feature : features) {
            String[] splits = feature.split(",");
            for (String split : splits)
                featureSet.add(split);
        }
        return featureSet;
    }
}
