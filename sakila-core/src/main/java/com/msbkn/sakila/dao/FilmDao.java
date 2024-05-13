package com.msbkn.sakila.dao;

import com.msbkn.sakila.common.HibernateUtil;
import com.msbkn.sakila.domain.Film;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

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


}
