package com.msbkn.sakila.dao;

import com.msbkn.sakila.common.GenericDao;
import com.msbkn.sakila.common.HibernateUtil;
import com.msbkn.sakila.domain.Film;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilmDao extends GenericDao {
    List<Criterion> criterionList;

    public void saveFilm(Film film) {
        save(film);
    }

    public Film findById(long id) {
        criterionList = new ArrayList<>();
        criterionList.add(Restrictions.eq("deleted", false));
        criterionList.add(Restrictions.eq("id", id));
        return  findByIdParams(new Film(), criterionList);
    }

    public List<Film> findAll() {
        criterionList = new ArrayList<>();
        criterionList.add(Restrictions.eq("deleted", false));
        return findAllParams(new Film(), criterionList);
    }

    public Set<String> findRatingList() {
        String sqlQuery = "select DISTINCT(rating) as rating from sakila.film";
        return fillSetList(executeReaderQuery(sqlQuery).list());
    }

    public Set<String> findFeatureList() {
        String sqlQuery = "select distinct( special_features) as feature from sakila.film";
        return fillSetList(executeReaderQuery(sqlQuery).list());
    }

    public void delete(Film film) {
        String sqlQuery = "update sakila.film set film_deleted = 1 where id =  " + film.getId();
        executeNonQuery(sqlQuery);
        //todo: Böyle yapmamın sebebi schema şeması göre  film_category tablosunda Foregin key var film_Id ile yapıyı bozmak yerine bu şekilde yaptım
    }

    public void updateFilm(Film film) {
        update(film);
    }

    private Set fillSetList(List<String> strings) {
        Set stringSet = new HashSet<>();
        for (String string : strings) {
            String[] splits = string.split(",");
            for (String split : splits)
                stringSet.add(split);
        }
        return stringSet;
    }
}
