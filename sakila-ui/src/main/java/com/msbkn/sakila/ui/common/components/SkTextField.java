package com.msbkn.sakila.ui.common.components;

import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;


public class SkTextField extends TextField {
    public SkTextField() {
        addStyleName(ValoTheme.TEXTFIELD_ALIGN_CENTER);
    }
}
