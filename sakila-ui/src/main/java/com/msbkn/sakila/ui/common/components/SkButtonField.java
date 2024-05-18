package com.msbkn.sakila.ui.common.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

public class SkButtonField extends Button {
    public SkButtonField() {
        setIcon(FontAwesome.CHECK);
        setStyleName(ValoTheme.BUTTON_PRIMARY);
        setHtmlContentAllowed(true);
    }
}
