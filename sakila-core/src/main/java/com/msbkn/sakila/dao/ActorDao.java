package com.msbkn.sakila.dao;

import com.msbkn.sakila.common.BaseDao;
import com.msbkn.sakila.domain.Actor;

public class ActorDao extends BaseDao<Actor> {

    public ActorDao() {
        super(Actor.class);
    }
}
