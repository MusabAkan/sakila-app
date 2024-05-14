package com.msbkn.sakila.ui.common.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;



import static javax.swing.text.StyleConstants.setIcon;

public class SkDeleteButtonField  extends Button {
    public SkDeleteButtonField() {
        setIcon(FontAwesome.TRASH);
        setStyleName(ValoTheme.BUTTON_DANGER);
    }
}
