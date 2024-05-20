package com.msbkn.sakila.ui.pages.windows;

import com.msbkn.sakila.domain.*;
import com.msbkn.sakila.service.*;
import com.msbkn.sakila.ui.common.components.*;
import com.vaadin.ui.ListSelect;

import java.util.*;

public class FilmActorCardWindow extends SkWindowField {
    private Film selectFilmField;
    private ListSelect selectFilmActorList;
    private ListSelect allItemList;
    private SkFormLayoutField formLayout2Field;

    private SkDeleteButtonField deleteButtonField;
    private SkButtonField saveButtonField;

    private List<FilmActor> selectFilmActors;
    private List<Actor> actors;

    private FilmActorService filmActorService;
    private ActorService actorService;

    public FilmActorCardWindow() {
        buildWindowField();
    }

    public FilmActorCardWindow(Film film) {
        this();
        selectFilmField = film;
        fillWindowByData();
    }

    private void fillActorField() {
        Set<Long> ids = new HashSet<>();
        allItemList.removeAllItems();
        selectFilmActors.forEach(actor -> ids.add(actor.getActor().getId()));
        actors = actorService.findAllNotActorId(ids);
        if (actors.size() == 0) return;
        actors.sort(Comparator.comparing(Actor::getFullName));
        for (Actor actor : actors) {
            String fullName = actor.getFullName();
            allItemList.addItems(actor);
            allItemList.setItemCaption(actor, fullName);
        }

    }

    private void fillFilmActorField(Film film) {
        selectFilmActorList.removeAllItems();
        selectFilmActors = filmActorService.findAllByFilm(film);
        if (selectFilmActors.size() == 0) return;
        selectFilmActors.sort(Comparator.comparing(FilmActor::getActorFullName));
        for (FilmActor filmActor : selectFilmActors) {
            String fullName = filmActor.getActorFullName();
            selectFilmActorList.addItem(filmActor);
            selectFilmActorList.setItemCaption(filmActor, fullName);
        }
    }

    private void buildWindowField() {
        horizontalLayoutField = new SkHorizontalLayoutField();
        formLayoutField = new SkFormLayoutField();

        filmActorService = new FilmActorService();
        actorService = new ActorService();

        allItemList = new ListSelect();
        selectFilmActorList = new ListSelect();

        setWidth("50%");

        selectFilmActorList = new ListSelect();
        selectFilmActorList.setCaption("Seçilen Listeler");
        selectFilmActorList.setWidth("90%");
        selectFilmActorList.setMultiSelect(true);
        formLayoutField.addComponent(selectFilmActorList);

        deleteButtonField = new SkDeleteButtonField();
        deleteActorFilmField();
        formLayoutField.addComponent(deleteButtonField);

        formLayout2Field = new SkFormLayoutField();
        allItemList = new ListSelect();
        allItemList.setCaption("Seçilmeyen Listeler");
        allItemList.setWidth("90%");
        allItemList.setMultiSelect(true);
        formLayout2Field.addComponent(allItemList);

        saveButtonField = new SkButtonField();
        saveActorFilmField();
        formLayout2Field.addComponent(saveButtonField);

        horizontalLayoutField.addComponent(formLayoutField);
        horizontalLayoutField.addComponent(formLayout2Field);

        setContent(horizontalLayoutField);
    }

    private void deleteActorFilmField() {
        deleteButtonField.addClickListener(clickEvent -> {
            Object value = selectFilmActorList.getValue();
            if (value == null) return;
            Set<FilmActor> selectFilmActors = (Set<FilmActor>) value;
            for (FilmActor selectFilmActor : selectFilmActors) {
                filmActorService.delete(selectFilmActor);
                fillWindowByData();
            }
        });
    }

    private void saveActorFilmField() {
        saveButtonField.addClickListener(clickEvent -> {
            Date nowLastUptade = new Date();
            Object value = allItemList.getValue();
            if (value == null) return;
            Set<Actor> selectActors = (Set<Actor>) value;
            for (Actor selectActor : selectActors) {
                FilmActor filmActor = new FilmActor();
                filmActor.setActor(selectActor);
                filmActor.setFilm(selectFilmField);
                filmActor.setLastUpdate(nowLastUptade);
                filmActorService.save(filmActor);
                fillWindowByData();
            }
        });
    }

    private void fillWindowByData() {
        fillFilmActorField(selectFilmField);
        fillActorField();
    }

}
