package com.msbkn.sakila.common;

import org.hibernate.Session;

import java.util.List;

public class GenericDao {

    public <T> void save(T entity) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
    }

    public <T> void update(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    public <T> void delete(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    public <T> T findById(T entity, long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        return (T) session.get(entity.getClass(), id);
    }

    public <T> List<T> findAll(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        return (List<T>) session.createCriteria(entity.getClass()).list();
    }

}
