package com.msbkn.sakila.service;

import com.msbkn.sakila.dao.ActorDao;
import com.msbkn.sakila.domain.Actor;

import java.util.List;

public class ActorService {
    ActorDao actorDao = new ActorDao();

    public void saveActor(Actor actor) {
        actorDao.saveActor(actor);
    }

    public Actor findById(long id) {
        return actorDao.findById(id);
    }
    public List<Actor> findAll() {
        return actorDao.findAll();
    }
}
