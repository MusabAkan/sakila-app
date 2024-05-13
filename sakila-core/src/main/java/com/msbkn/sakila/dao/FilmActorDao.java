package com.msbkn.sakila.dao;

import com.msbkn.sakila.common.HibernateUtil;
import com.msbkn.sakila.domain.FilmActor;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class FilmActorDao {

    public void saveflimActor(FilmActor filmActor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(session.save(filmActor));
        session.getTransaction().commit();
    }

    public FilmActor findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (FilmActor) session.byId(FilmActor.class).load(id);
    }

    public List<FilmActor> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(FilmActor.class);
        return (List<FilmActor>) criteria.list();
    }

}
