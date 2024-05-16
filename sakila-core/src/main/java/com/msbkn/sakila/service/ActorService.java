package com.msbkn.sakila.service;

import com.msbkn.sakila.common.CommonService;
import com.msbkn.sakila.dao.ActorDao;
import com.msbkn.sakila.domain.Actor;

import java.util.List;

public class ActorService implements CommonService<Actor> {
    ActorDao actorDao = new ActorDao();

    @Override
    public void save(Actor actor) {
        actorDao.saveActor(actor);
    }

    @Override
    public Actor findById(long id) {
        return actorDao.findById(id);
    }

    @Override
    public List<Actor> findAll() {
        return actorDao.findAll();
    }

    @Override
    public void delete(Actor actor) {
        actorDao.deleteActor(actor);
    }

    @Override
    public void update(Actor actor) {
        actorDao.updateActor(actor);
    }
}
