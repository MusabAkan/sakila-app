package com.msbkn.sakila.domain;

import com.msbkn.sakila.common.BaseEntity;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@Table
@Entity
public class Film extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "LANGUAGE_ID")
    @ForeignKey(name = "FK_LANGUAGE_FLIM")
    private Language language;

    @Column(name = "rental_duration")
    private Long duration;

    @Column(name = "rental_rate")
    private Double rate;

    @Column(name = "length")
    private Long length;

    @Column(name = "replacement_cost")
    private Double cost;

    @Column(name = "rating")
    private String rating;

    @Column(name = "special_features")
    private String features;

    @Column(name = "last_update")
    private Date lastUpdate;

    @Column(name = "film_deleted")
    private Boolean deleted;


    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    public String[] getFeatureList() {
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
}