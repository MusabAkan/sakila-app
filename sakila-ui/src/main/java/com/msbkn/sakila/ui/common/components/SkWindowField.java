package com.msbkn.sakila.ui.common.components;

import com.vaadin.ui.*;

public class SkWindowField extends Window {
    protected SkVerticalLayoutField verticalLayoutField;
    protected SkHorizontalLayoutField horizontalLayoutField;
    protected SkFormLayoutField formLayoutField;

    public SkWindowField() {
        center();
        setWidth("30%");
        setHeight("35%");
        setModal(true);
    }

    public void quit() {
        this.close();
    }
}
