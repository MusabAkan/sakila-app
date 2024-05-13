package com.msbkn.sakila.ui.common.components;

import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Like;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Table;

public class SkFormLayoutField  extends FormLayout {

    public void filterSearch(String filterString, String columnName, Table table) {

        Container.Filterable filter = (Container.Filterable) (table.getContainerDataSource());
        filter.removeAllContainerFilters();
        if (filterString.length() > 0)
            filter.addContainerFilter(new Like(columnName, "%" + filterString + "%"));
    }
}
