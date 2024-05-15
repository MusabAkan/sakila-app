package com.msbkn.sakila.ui.common.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;


public class SkDeleteButtonField extends Button {
    public SkDeleteButtonField() {
        setIcon(FontAwesome.TRASH);
        setStyleName(ValoTheme.BUTTON_DANGER);
        setHtmlContentAllowed(true);
    }
}
