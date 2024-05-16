package com.msbkn.sakila.ui.common.components;
import com.vaadin.ui.*;


public class SkVerticalLayoutField extends VerticalLayout {

    public SkVerticalLayoutField() {
        setSizeFull();
    }
    protected void buildItemFilterPanel(TextField textField,
                                        String caption,
                                        String searchStr,
                                        Table tableDataField,
                                        SkFormLayoutField formLayoutField) {
        textField.setCaption(caption);
        textField.addTextChangeListener(event -> {
            String searchIdField = event.getText();
            formLayoutField.filterSearch(searchIdField, searchStr, tableDataField);
        });
    }
}
