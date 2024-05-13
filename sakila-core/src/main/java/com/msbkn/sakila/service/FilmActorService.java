package com.msbkn.sakila.service;

import com.msbkn.sakila.dao.FilmActorDao;
import com.msbkn.sakila.domain.FilmActor;

import java.util.List;

public class FilmActorService
{
    FilmActorDao filmActorDao = new FilmActorDao();
    public void saveFlimActor(FilmActor filmActor)
    {
        filmActorDao.saveflimActor(filmActor);
    }

    public  FilmActor findById(int id){
        return filmActorDao.findById(id);
    }

    public List<FilmActor> findAll(){
        return filmActorDao.findAll();
    }
}
