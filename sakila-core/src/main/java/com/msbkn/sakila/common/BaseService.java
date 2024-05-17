package com.msbkn.sakila.common;

import java.util.List;

public class BaseService extends BaseDao {

    public <T> void save(T entity) {
        super.save(entity);
    }

    public <T> void update(T entity) {
        super.update(entity);
    }

    public <T> void delete(T entity) {
        super.delete(entity);
    }

    public <T> List<T> findAll(T entity) {
        return super.findAll(entity);
    }

    public <T> T findById(T entity, long id) {
        return super.findById(entity, id);
    }
}
