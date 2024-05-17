package com.msbkn.sakila.common;

import com.msbkn.sakila.domain.Film;

import java.util.List;

public class BaseService<E extends BaseEntity, D extends BaseDao<Film>> {
    
    public Class<E> daoClazz;
    
    public BaseService(BaseDao<T> dao) {
        this.daoClazz = daoClazz;
    }

    public E save(E entity) {
        return daoClazz.save(entity);;
    }

    public void delete(E entity) {
        daoClazz.delete(entity);
    }

    public List<E> findAll() {
        return daoClazz.findAll();
    }

    public E findById(Long id) {
        return daoClazz.findById(id);
    }
    
    public BaseDao<E> getDaoClazz() {
        return daoClazz;
    }
}