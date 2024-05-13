package com.msbkn.sakila.service;

import com.msbkn.sakila.domain.Film;
import org.junit.Test;

import java.util.List;

public class FilmServiceTest {

    FilmService filmService;
    String text;

    @Test
    public void save() {
        filmService = new FilmService();
    }

    @Test
    public void getFilmById() {
        filmService = new FilmService();
        Film Film = filmService.findById(26);
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
        List<String> strings = filmService.findRatingList();
        for (String string : strings) {
            System.out.println(string);
        }

    }

}