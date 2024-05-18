package com.msbkn.sakila.common;

import org.hibernate.criterion.Criterion;

import java.util.List;

public class BaseService<E extends BaseEntity> {

    private BaseDao<E> dao;
    public BaseService(Class entity) {
        dao = new BaseDao<E>(entity);
    }

    public E save(E entity) {
        return dao.save(entity);
    }

    public void delete(E entity) {
        dao.delete(entity);
    }

    public List<E> findAll() {
        return dao.findAll();
    }

    public E findById(Long id) {
        return dao.findById(id);
    }

    public List<E> findAllParams(Criterion... criterionCriteria) {
        return dao.findAllParams(criterionCriteria);
    }

    public BaseDao<E> getDao() {
        return dao ;
    }
}