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
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        entity = (T) session.save(entity);
        session.getTransaction().commit();
        return entity;
    }

    public void delete(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    public T findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        return (T) session.get(clazz, id);
    }

    public List<T> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(clazz);
        return (List<T>) criteria.list();
    }

    public List<T> findAllParams(Criterion... criterionCriteria) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria sessionCriteria = session.createCriteria(clazz);
        for (Criterion criterion : criterionCriteria) {
            sessionCriteria.add(criterion);
        }
        return (List<T>) sessionCriteria.list();
    }
}