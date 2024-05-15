package com.msbkn.sakila.dao;

import com.msbkn.sakila.common.GenericDao;
import com.msbkn.sakila.common.HibernateUtil;
import com.msbkn.sakila.domain.Film;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilmDao extends GenericDao {
    public void saveFilm(Film film) {
        save(film);
    }

    public Film findById(long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Film film = (Film) session.createCriteria(Film.class)
                .add(Restrictions.eq("id", id))
                .add(Restrictions.eq("deleted", false))
                .uniqueResult();
        return film;
    }

    public List<Film> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Film> films = (List<Film>) session.createCriteria(Film.class)
                .add(Restrictions.eq("deleted", false)).list();
        return films;
    }

    public Set<String> findRatingList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sqlQuery = "select DISTINCT( rating) as rating from sakila.film";
        Query query = session.createSQLQuery(sqlQuery);
        return fillSetList(query.list());
    }

    public Set<String> findFeatureList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sqlQuery = "select distinct( special_features) as feature from sakila.film";
        Query query = session.createSQLQuery(sqlQuery);
        return fillSetList(query.list());
    }

    public void delete(Film film) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String sqlQuery = "update sakila.film set film_deleted = 1 where film_id =  " + film.getId();
        Query query = session.createSQLQuery(sqlQuery);
        query.executeUpdate();
        session.getTransaction().commit();

        //todo: Böyle yapmamın sebebi film_category tablosunda Foregin key var film_Id ile yapıyı bozmak yerine bu şekilde yaptım
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

    public void updateFilm(Film film) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(film);
        session.getTransaction().commit();

    }
}
