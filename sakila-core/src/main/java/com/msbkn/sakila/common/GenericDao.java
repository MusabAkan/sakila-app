package com.msbkn.sakila.common;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

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

    public Query executeReaderQuery(String sql) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        session.getTransaction().commit();
        return query;
    }

    public void executeNonQuery(String sql) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public <T> List<T> findAllParams(T entity, List<Criterion> criterionList) {
        return (List<T>)  fillCriterionField(entity, criterionList);
    }

    public <T> T findByIdParams(T entity, List<Criterion> criterionList) {
        return (T) fillCriterionField(entity, criterionList).get(0);
        //todo:Burada dönen liste  olduğu için 1 tane seçim
    }

    private static <T> List fillCriterionField(T entity, List<Criterion> criterionList) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria sessionCriteria = session.createCriteria(entity.getClass());
        for (Criterion criterion : criterionList)
            sessionCriteria.add(criterion);
        return sessionCriteria.list();
    }

}
