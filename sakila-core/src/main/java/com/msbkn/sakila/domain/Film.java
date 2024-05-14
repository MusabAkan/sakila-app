package com.msbkn.sakila.domain;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Table
@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "film_id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "LANGUAGE_ID")
    @ForeignKey(name = "FK_LANGUAGE_FLIM")
    private Language language;

    @Column(name = "rental_duration")
    private long duration;

    @Column(name = "rental_rate")
    private double rate;

    @Column(name = "length")
    private long length;

    @Column(name = "replacement_cost")
    private double cost;

    @Column(name = "rating")
    private String rating;

    @Column(name = "special_features")
    private String features;

    @Column(name = "last_update")
    private Date lastUpdate;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getDescription25Limt() {
        return description.substring(0, 25) + "...";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Language getLanguage() {
        return language;
    }

    public String getLanguageName() {
        return language.getName();
    }

    public void setLanguage(Language language) {
        this.language = language;
    }


    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


    public String getRating() {
            return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String[] getFeatureList(){
        return features.split(",");
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        Format formatDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return formatDate.format(lastUpdate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id && year == film.year && duration == film.duration && Double.compare(rate, film.rate) == 0 && length == film.length && Double.compare(cost, film.cost) == 0 && Objects.equals(title, film.title) && Objects.equals(description, film.description) && Objects.equals(language, film.language) && Objects.equals(rating, film.rating) && Objects.equals(features, film.features) && Objects.equals(lastUpdate, film.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, year, language, duration, rate, length, cost, rating, features, lastUpdate);
    }

}