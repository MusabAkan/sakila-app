package com.msbkn.sakila.ui.pages.windows;

import com.msbkn.sakila.ui.common.components.SkNoButtonField;
import com.msbkn.sakila.ui.common.components.SkHorizontalLayoutField;
import com.msbkn.sakila.ui.common.components.SkYesButtonField;
import com.vaadin.ui.*;

public class DialogCardWinddow extends Window {

    private boolean result;
    private SkYesButtonField yesButtonField;
    private SkNoButtonField noButtonField;

    public  DialogCardWinddow() {

    }

    public DialogCardWinddow(String titleSubjectField) {
        super(titleSubjectField);
        center();
        setClosable(false);
        setModal(true);
        setResizable(false);
        setWidth("600px");
        setHeight("150px");

        SkHorizontalLayoutField layoutField = new SkHorizontalLayoutField();

        yesButtonField = new SkYesButtonField();
        builItemButtonField(yesButtonField, "Evet", true);
        layoutField.addComponent(yesButtonField);

        noButtonField = new SkNoButtonField();
        builItemButtonField(noButtonField, "Hayır", true);
        layoutField.addComponent(noButtonField);

        layoutField.setComponentAlignment(yesButtonField, Alignment.MIDDLE_LEFT);
        layoutField.setComponentAlignment(noButtonField, Alignment.MIDDLE_RIGHT);

        setContent(layoutField);
    }

    private <T> void builItemButtonField(T entityButton, String caption, boolean result) {
        Button button = (Button) entityButton;
        button.setCaption(caption);
        button.addClickListener(clickEvent -> {
            this.result = result;
            close();
        });
    }

    public boolean getResult() {
        return result;
    }
}

