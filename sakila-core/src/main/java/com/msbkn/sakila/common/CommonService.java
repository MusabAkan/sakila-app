package com.msbkn.sakila.common;

import java.util.List;

public interface CommonService<T> {
    void save(T actor);
    void delete(T actor);
    void update(T actor);
    List<T> findAll();
    T findById(long id);
}
