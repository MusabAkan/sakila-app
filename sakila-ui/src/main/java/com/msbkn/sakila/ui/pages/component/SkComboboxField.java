package com.msbkn.sakila.ui.pages.component;
import com.vaadin.ui.ComboBox;

import java.util.List;

public class SkComboboxField extends ComboBox {
    public SkComboboxField(List<String> items) {
        for (String item : items) {
            addItem(item);
        }
    }
}
