package com.msbkn.sakila.service;

import com.msbkn.sakila.domain.Actor;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class ActorServiceTest {

    ActorService actorService;
    String text;

    @Test
    public void save() {;
        actorService = new ActorService();
        Actor actor = new Actor();
        actor.setFirstName("Musab");
        actor.setLastName("Akan");
        actor.setLastUpdate(new Date(98,01,01));
        actorService.saveActor(actor);
    }
    @Test
    public void getActorById() { ;
        actorService = new ActorService();
        Actor actor = actorService.findById(5);
        text = actor.getFirstName() + " " + actor.getLastName();
        System.out.println(text);
    }

    @Test
    public void getAllActors() {
 ;
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
    public void deleteActor() {
        actorService = new ActorService();
        Actor actor = actorService.findById(999);
        actorService.deleteActor(actor);
    }

    @Test
    public void updateActor() {
        actorService = new ActorService();
        Actor actor = actorService.findById(999);
        actor.setFirstName("Musab");
        actor.setLastName("Akan");
        actor.setLastUpdate(new Date(98,01,01));
        actorService.updateActor(actor);


    }

}