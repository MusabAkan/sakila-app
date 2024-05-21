package com.msbkn.sakila.service;

import com.msbkn.sakila.common.BaseService;
import com.msbkn.sakila.domain.Actor;
import org.hibernate.criterion.Restrictions;

import java.util.*;

public class ActorService extends BaseService<Actor> {

    public ActorService() {
        super(Actor.class);
    }

    public List<Actor> findAllNotActorId(Set<Long> ids) {
        if (ids.size() == 0) return findAll();
        return super.findAllParams(Restrictions.not(Restrictions.in("id", ids)));
    }
}