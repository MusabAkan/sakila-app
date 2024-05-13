package com.msbkn.sakila.service;

import com.msbkn.sakila.domain.Film;
import com.msbkn.sakila.domain.FilmActor;
import org.junit.Test;

import java.util.List;

public class FilmActorServiceTest {
    FilmActorService filmActorService;
    String text;

    @Test
    public void save() {

        filmActorService = new FilmActorService();


    }

    @Test
    public void getFilmById() {
        filmActorService = new FilmActorService();
        FilmActor filmActor = filmActorService.findById(5);
        text = filmActor.getId() + " " + filmActor.getLastUpdate()  + " " +filmActor.getActor().getLastName() + " " +filmActor.getActor().getFirstName();
        System.out.println(text);
    }

    @Test
    public void getAllFilms() {
        filmActorService = new FilmActorService();
        List<FilmActor> filmActors = filmActorService.findAll();
        for (FilmActor filmActor : filmActors) {
            text = filmActor.getId() + " " + filmActor.getLastUpdate();
            System.out.println(text);
        }

    }
}