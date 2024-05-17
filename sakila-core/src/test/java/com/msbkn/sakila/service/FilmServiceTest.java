package com.msbkn.sakila.service;

import com.msbkn.sakila.domain.Film;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class FilmServiceTest {

    FilmService filmService;
    String text;
    Long filmID = 999L;

    @Test
    public void save() {
        filmService = new FilmService();
        Film film = filmService.findById(filmID);
        film.setId(null);
        filmService.save(film);
    }

    @Test
    public void update() {
        filmService = new FilmService();
        Film film = filmService.findById(filmID);
        film.setDescription("Banane");
        filmService.save(film);
    }

    @Test
    public void fillById() {
        filmService = new FilmService();
        Film Film = filmService.findById(filmID);
        if (Film == null) return;
        writter(Film);
        System.out.println(text);
    }

    private void writter(Film film) {
        text = film.getLanguageName();
    }

    @Test
    public void fillAll() {
        filmService = new FilmService();
        List<Film> films = filmService.findAll();
        for (Film Film : films) {
            writter(Film);
            System.out.println(text);
        }
    }


    @Test
    public void findRatingList() {
        filmService = new FilmService();
        Set<String> strings = filmService.findRatingList();
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void findFilmByTitle() {
        filmService = new FilmService();
        Set<String> strings = filmService.findFeatureList();
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void deleteTest() {
        filmService = new FilmService();
        Film film = filmService.findById(filmID);
        filmService.delete(film);
    }

}