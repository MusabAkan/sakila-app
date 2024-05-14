package com.msbkn.sakila.domain;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Table(name = "film_actor")
@Entity
public class FilmActor   {


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmActor filmActor = (FilmActor) o;
        return id == filmActor.id && Objects.equals(actor, filmActor.actor) && Objects.equals(filmId, filmActor.filmId) && Objects.equals(lastUpdate, filmActor.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actor, filmId, lastUpdate);
    }
}