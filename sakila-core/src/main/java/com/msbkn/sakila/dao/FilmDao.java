package com.msbkn.sakila.dao;

import com.msbkn.sakila.common.BaseDao;
import com.msbkn.sakila.domain.Film;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilmDao extends BaseDao {

    public Set<String> findRatingList() {
        String sqlQuery = "select DISTINCT(rating) as rating from sakila.film";
        return fillSetList(executeReaderQuery(sqlQuery).list());
    }

    public Set<String> findFeatureList() {
        String sqlQuery = "select distinct( special_features) as feature from sakila.film";
        return fillSetList(executeReaderQuery(sqlQuery).list());
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
