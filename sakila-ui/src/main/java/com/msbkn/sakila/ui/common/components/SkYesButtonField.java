package com.msbkn.sakila.ui.common.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

public class SkYesButtonField extends Button {
    public SkYesButtonField() {
        setIcon(FontAwesome.CHEVRON_CIRCLE_DOWN);
        addStyleName(ValoTheme.BUTTON_PRIMARY);
        setHtmlContentAllowed(true);
    }
}
