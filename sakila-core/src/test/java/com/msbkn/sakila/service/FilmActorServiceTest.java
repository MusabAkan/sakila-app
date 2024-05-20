package com.msbkn.sakila.service;

import com.msbkn.sakila.domain.Film;
import com.msbkn.sakila.domain.FilmActor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FilmActorServiceTest {
    FilmActorService filmActorService;
    String text;
    Long flimId = 998L;

    @Test
    public void saveTest() {
        filmActorService = new FilmActorService();
        FilmActor film = new FilmActor();
        filmActorService.findById(flimId);
    }


    @Test
    public void findAllByFlim() {
        filmActorService = new FilmActorService();
        FilmService filmService = new FilmService();
        Film film = filmService.findById(494L);
        List<FilmActor> filmActors = filmActorService.findAllByFilm(film);
        for (FilmActor filmActor : filmActors) {
            System.out.println(filmActor);
        }
    }

    @Test
    public void findByIdTest() {
        filmActorService = new FilmActorService();
        FilmActor filmActor = (FilmActor) filmActorService.findAll();
        text = filmActor.getId() + " " + filmActor.getLastUpdate() + " " + filmActor.getActor().getLastName() + " " + filmActor.getActor().getFirstName();
        System.out.println(text);
    }

    @Test
    public void fillAllTest() {
        filmActorService = new FilmActorService();
        List<FilmActor> filmActors = filmActorService.findAll();
        for (FilmActor filmActor : filmActors) {
            text = filmActor.getId() + " " + filmActor.getLastUpdate();
            System.out.println(text);
        }

    }
}