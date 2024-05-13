package com.msbkn.sakila.dao;


import com.msbkn.sakila.common.HibernateUtil;
import com.msbkn.sakila.domain.Actor;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

public class ActorDao {


    public void saveActor(Actor actor) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(actor);
        session.getTransaction().commit();
    }

    public Actor findById(long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Actor) session.get(Actor.class, id);
    }

    public List<Actor> findAll() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Actor.class);
        return criteria.list();
    }
}
