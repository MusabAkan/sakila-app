package com.msbkn.sakila.service;

import com.msbkn.sakila.common.BaseService;
import com.msbkn.sakila.domain.Actor;

public class ActorService extends BaseService<Actor> {

    public ActorService() {
        super(Actor.class);
    }
}