package com.msbkn.sakila.ui.common.components;
import com.vaadin.ui.*;
public class SkWindowField extends Window {

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
