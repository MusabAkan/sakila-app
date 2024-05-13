package com.msbkn.sakila.ui.common.pages;

import com.msbkn.sakila.ui.common.components.SkLabelField;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.FontIcon;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Header extends HorizontalLayout {


    public Header() {
        setMargin(true);
        setSpacing(true);
        setSizeFull();
        builderHeaderLayout();
    }

    private void builderHeaderLayout() {

        SkLabelField headerLabelField = new SkLabelField();
        headerLabelField.setValue("Sakila Web Application");

        headerLabelField.setStyleName(ValoTheme.LABEL_H1);
        headerLabelField.addStyleName(ValoTheme.LABEL_COLORED);
        headerLabelField.addStyleName(ValoTheme.TEXTAREA_ALIGN_CENTER);

        addComponent(headerLabelField);

        setComponentAlignment(headerLabelField, Alignment.TOP_RIGHT);
    }


}