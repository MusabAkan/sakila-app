package com.msbkn.sakila.domain;

import javax.persistence.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "language")
public class Language   {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "language_id")
    private long id;

    private String name;

    @Column(name = "last_update")
    private Date lastUpdate;

    public long getId() {
        return id;
    }

    public Language() {
    }

    public Language(String name, Date date) {
        this();
        this.name = name;
        this.lastUpdate = date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
