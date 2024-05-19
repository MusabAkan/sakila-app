package com.msbkn.sakila.ui.pages.windows;

import com.msbkn.sakila.domain.*;
import com.msbkn.sakila.service.FilmActorService;
import com.msbkn.sakila.ui.common.components.*;
import com.vaadin.ui.Button;

import java.util.List;

public class FilmActorCardWindow extends SkWindowField {

    private List<FilmActor> filmActors;
    private FilmActorService filmActorService;

    public FilmActorCardWindow() {
        buildWindowField();
    }

    public FilmActorCardWindow(Film film) {
        this();
        fillWindowByFilmActor(film);
    }


    private void fillWindowByFilmActor(Film film) {
        filmActorService = new FilmActorService();
        filmActors = filmActorService.findAllByFilm(film);

        for (FilmActor filmActor : filmActors) {
            Button buttonTestField = new Button();
            buttonTestField.setCaption(filmActor.getActor().getFullName());
            formLayoutField.addComponent(buttonTestField);
        }
    }

    private void buildWindowField() {
        verticalLayoutField = new SkVerticalLayoutField();
        formLayoutField = new SkFormLayoutField();
        verticalLayoutField.addComponent(formLayoutField);
        setContent(verticalLayoutField);
    }

}
