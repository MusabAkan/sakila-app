package com.msbkn.sakila.service;

import com.msbkn.sakila.domain.Film;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class FilmServiceTest {

    FilmService filmService;
    String text;

    @Test
    public void save() {
        filmService = new FilmService();
        Film film = filmService.findById(999);
        film.setId(null);
        filmService.save(film);
    }

    @Test
    public  void updateFilm(){
        filmService = new FilmService();
        Film film = filmService.findById(999);
        film.setDescription("Banane");
        filmService.updateFilm(film);;
    }

    @Test
    public void getFilmById() {
        filmService = new FilmService();
        Film Film = filmService.findById(5);
        if (Film == null) return;
        writter(Film);
        System.out.println(text);
    }

    private void writter(Film film) {
        text = film.getLanguageName();
    }

    @Test
    public void getAllFilms() {

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
        Film film = filmService.findById(700);
        filmService.delete(film);
    }

}