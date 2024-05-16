package com.msbkn.sakila.ui.common.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

public class SkNoButtonField extends Button {
    public SkNoButtonField() {
        setIcon(FontAwesome.CIRCLE);
        addStyleName(ValoTheme.BUTTON_DANGER);
        setHtmlContentAllowed(true);
    }

}