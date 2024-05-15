package com.msbkn.sakila.common;

import javax.persistence.*;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    //todo:MappedSuperclass Bu classları genellikle ortak state ve mapping bilgisi olan entitylerimiz olduğunda kullanırız. Yukarıdaki entity sonucunda veritabanında sadece User tablosu oluşacak ve fieldları id, deleted, creation_time, deletion_time, username ve password şeklinde oluşacaktır.Sep 18
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String getDateString(Date date) {
        Format formatDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return formatDate.format(date);
    }
}
