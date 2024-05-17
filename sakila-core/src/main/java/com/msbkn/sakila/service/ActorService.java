package com.msbkn.sakila.service;

import com.msbkn.sakila.common.BaseService;
import com.msbkn.sakila.domain.Actor;

import java.util.List;

public class ActorService extends BaseService {

    public void saveActor(Actor actor) {
        super.save(actor);
    }

    public Actor findById(long id) {
        return super.findById(new Actor(), id);
    }

    public List<Actor> findAll() {
        return super.findAll(new Actor());
    }

    public void deleteActor(Actor actor) {
        super.delete(actor);
    }

    public void updateActor(Actor actor) {
        super.update(actor);
    }
}
