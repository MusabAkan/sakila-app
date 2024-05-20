package com.msbkn.sakila.service;

import com.msbkn.sakila.domain.Actor;
import org.junit.Test;

import java.util.*;

public class ActorServiceTest {

    ActorService actorService;
    String text;
    Long actorId = 999L;

    @Test
    public void saveActor() {
        actorService = new ActorService();
        Actor actor = new Actor();
        actor.setFirstName("Musab");
        actor.setLastName("Akan");
        actor.setLastUpdate(new Date(98, 01, 01));
        actorService.save(actor);
    }
    @Test
    public void getActorById() {
        actorService = new ActorService();

        Actor actor = actorService.findById(actorId);
        text = actor.getFirstName() + " " + actor.getLastName();
        System.out.println(text);
    }

    @Test
    public void getAllActors() {
        actorService = new ActorService();
        List<Actor> actors = actorService.findAll();
        for (Actor actor : actors) {
            text = "No : " + actor.getId();
            text += " Adı Soyad : " + actor.getFirstName() + " " + actor.getLastName();
            text += " Tarih : " + actor.getLastUpdate();
            System.out.println(text);
        }
    }

    @Test
    public void deleteActorActor() {

        actorService = new ActorService();
        Actor actor = actorService.findById(actorId);
        actorService.delete(actor);
    }

    @Test
    public void updateActorActor() {
        actorService = new ActorService();
        Actor actor = actorService.findById(actorId);
        actor.setFirstName("Musab");
        actor.setLastName("Akan");
        actor.setLastUpdate(new Date(98, 01, 01));
        actorService.save(actor);


    }

    @Test
    public void findAllNotActorTest() {
        actorService = new ActorService();
        Set<Long> list = new HashSet<>();
        list.add(195L);
        list.add(185L);
        list.add(145L);
        list.add(142L);
        list.add(131L);
        list.add(123L);
        list.add(101L);
        list.add(40L);
        list.add(2L);
        List<Actor> actors = actorService.findAllNotActorId(list);
        for (Actor actor : actors) {
            text = actor.getId() + " " + actor.getFullName();
            System.out.println(text);
        }
    }
}