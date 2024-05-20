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
        Session session = sessionField();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        return entity;
    }

    public void delete(T entity) {
        Session session = sessionField();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    public T findById(Long id) {
        Session session = sessionField();
        session.beginTransaction();
        return (T) session.get(clazz, id);
    }

    public List<T> findAll() {
        Session session = sessionField();
        Criteria criteria = session.createCriteria(clazz);
        return criteria.list();
    }

    public List<T> findAllParams(Criterion... criterionCriteria) {
        Session session = sessionField();
        Criteria sessionCriteria = session.createCriteria(clazz);
        for (Criterion criterion : criterionCriteria) sessionCriteria.add(criterion);
        return sessionCriteria.list();
    }

    private  Session sessionField() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        if(sessionFactory.isClosed()) sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        return session;
    }
}