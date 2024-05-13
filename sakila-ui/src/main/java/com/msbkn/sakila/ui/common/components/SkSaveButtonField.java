package com.msbkn.sakila.ui.common.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

public class SkSaveButtonField extends Button {
    public SkSaveButtonField() {
        setIcon(FontAwesome.SAVE);
        addStyleName(ValoTheme.BUTTON_FRIENDLY);
    }
}