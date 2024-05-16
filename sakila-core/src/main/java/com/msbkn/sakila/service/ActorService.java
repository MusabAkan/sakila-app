package com.msbkn.sakila.service;

import com.msbkn.sakila.dao.ActorDao;
import com.msbkn.sakila.domain.Actor;

import java.util.List;

public class ActorService {
    ActorDao actorDao = new ActorDao();


    public void save(Actor actor) {
        actorDao.saveActor(actor);
    }


    public Actor findById(long id) {
        return actorDao.findById(id);
    }


    public List<Actor> findAll() {
        return actorDao.findAll();
    }


    public void delete(Actor actor) {
        actorDao.deleteActor(actor);
    }


    public void update(Actor actor) {
        actorDao.updateActor(actor);
    }
}
