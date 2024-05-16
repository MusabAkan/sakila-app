package com.msbkn.sakila.ui.pages.windows;

import com.msbkn.sakila.ui.common.components.SkNoButtonField;
import com.msbkn.sakila.ui.common.components.SkHorizontalLayoutField;
import com.msbkn.sakila.ui.common.components.SkYesButtonField;
import com.vaadin.ui.*;

public class DialogCardWinddow extends Window {

    private boolean result;
    private SkYesButtonField yesButtonField;
    private SkNoButtonField noButtonField;

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
        buildSuccesButtonField();
        layoutField.addComponent(yesButtonField);

        noButtonField = new SkNoButtonField();
        buildFailedButtonField();
        layoutField.addComponent(noButtonField);

        layoutField.setComponentAlignment(yesButtonField, Alignment.MIDDLE_LEFT);
        layoutField.setComponentAlignment(noButtonField, Alignment.MIDDLE_RIGHT);

        setContent(layoutField);
    }

    private void buildSuccesButtonField() {
        yesButtonField.setCaption("Evet");
        yesButtonField.addClickListener(clickEvent -> {
            result = true;
            close();
        });
    }

    private void buildFailedButtonField() {
        noButtonField.setCaption("Hayır");
        noButtonField.addClickListener(clickEvent -> {
            result = false;
            close();
        });
    }

    public boolean getResult() {
        return result;
    }
}

