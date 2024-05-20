package com.msbkn.sakila.domain;

import com.msbkn.sakila.common.BaseEntity;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

@Table(name = "film_actor")
@Entity
public class FilmActor extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "ACTOR_ID")
    @ForeignKey(name = "FK_FILM_ACTOR_ACTOR_2")
    private Actor actor;

    @ManyToOne
    @JoinColumn(name = "FILM_ID")
    @ForeignKey(name = "FK_FILM_ACTOR_FILM_2")
    private Film film;

    @Column(name = "last_update")
    private Date lastUpdate;

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public String getActorFullName() {
        return actor.getFullName();
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}