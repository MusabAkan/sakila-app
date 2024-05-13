package com.msbkn.sakila.ui.common.pages;

import com.msbkn.sakila.ui.common.components.*;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Footer extends SkVerticalLayoutField {
    public Footer() {
        setSizeFull();
        buildFooterLayout();
    }

    private void buildFooterLayout() {
        Format formatYear = new SimpleDateFormat("YYYY");
        Date now = new Date();

        String year = formatYear.format(now);
        String subtitleField = "@Copyright " + year + " | By Musab Akan";

        SkLabelField lblFooterField = new SkLabelField();
        lblFooterField.addStyleName(ValoTheme.TEXTFIELD_ALIGN_CENTER);
        lblFooterField.addStyleName(ValoTheme.LABEL_H2);
        lblFooterField.addStyleName(ValoTheme.LABEL_LIGHT);
        lblFooterField.setValue(subtitleField);

        addComponent(lblFooterField);
    }
}
