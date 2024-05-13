package com.msbkn.sakila.domain;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

@Table(name = "film_actor")
@Entity
public class FilmActor {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "film_actor_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ACTOR_ID")
    @ForeignKey(name = "FK_FILM_ACTOR_ACTOR_2")
    private Actor actor;

    @ManyToOne
    @JoinColumn(name =  "FILM_ID")
    @ForeignKey(name = "FK_FILM_ACTOR_FILM_2")
    private Film filmId;

    @Column(name = "last_update")
    private Date lastUpdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Film getFilmId() {
        return filmId;
    }

    public void setFilmId(Film filmId) {
        this.filmId = filmId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}