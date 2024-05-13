package com.msbkn.sakila.dao;

import com.msbkn.sakila.common.HibernateUtil;
import com.msbkn.sakila.domain.Language;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class LanguageDao {

    public void saveLanguage(Language language) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(language);
        session.getTransaction().commit();
    }

    public Language findById(long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Language) session.get(Language.class, id);
    }

    public List<Language> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Language.class);
        return criteria.list();
    }


    public List<String> findLanguageList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sqlQuery = "select distinct(name) from sakila.language";
        Query query = session.createSQLQuery(sqlQuery);
        return query.list();
    }
}
