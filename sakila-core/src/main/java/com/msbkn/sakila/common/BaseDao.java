package com.msbkn.sakila.common;

import org.hibernate.*;
import org.hibernate.criterion.Criterion;

import java.util.List;

public class BaseDao<T extends BaseEntity> {

    public Class clazz;

    public BaseDao(Class clazz) {
        this.clazz = clazz;
    }

    public T save(T entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        return entity;
    }

    public void delete(T entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    public T findById(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return (T) session.get(clazz, id);
    }

    public List<T> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(clazz);
        return criteria.list();
    }

    public List<T> findAllParams(Criterion... criterionCriteria) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Criteria sessionCriteria = session.createCriteria(clazz);
        for (Criterion criterion : criterionCriteria) sessionCriteria.add(criterion);
        return sessionCriteria.list();
    }
}