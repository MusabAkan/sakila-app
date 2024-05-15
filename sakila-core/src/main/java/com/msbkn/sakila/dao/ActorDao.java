package com.msbkn.sakila.dao;

import com.msbkn.sakila.common.GenericDao;
import com.msbkn.sakila.domain.Actor;

import java.util.List;

public class ActorDao extends GenericDao {


    public void saveActor(Actor actor) {
        save(actor);
    }

    public void updateActor(Actor actor) {
        update(actor);
    }

    public void deleteActor(Actor actor) {
        delete(actor);
    }

    public Actor findById(long id) {
        return findById(new Actor(), id);
    }

    public List<Actor> findAll() {
        return findAll(new Actor());
    }
}
