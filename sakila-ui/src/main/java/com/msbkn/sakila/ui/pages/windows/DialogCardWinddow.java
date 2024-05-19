package com.msbkn.sakila.ui.pages.windows;

import com.msbkn.sakila.ui.common.components.SkNoButtonField;
import com.msbkn.sakila.ui.common.components.SkHorizontalLayoutField;
import com.msbkn.sakila.ui.common.components.SkYesButtonField;
import com.vaadin.ui.*;

public class DialogCardWinddow extends Window {

    private boolean result;
    private SkYesButtonField yesButtonField;
    private SkNoButtonField noButtonField;
    private SkHorizontalLayoutField layoutField;
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


        layoutField = new SkHorizontalLayoutField();

        yesButtonField = new SkYesButtonField();
        builItemButtonField(yesButtonField, "Evet", true);

        noButtonField = new SkNoButtonField();
        builItemButtonField(noButtonField, "Hayır", false);

        layoutField.setComponentAlignment(yesButtonField, Alignment.MIDDLE_LEFT);
        layoutField.setComponentAlignment(noButtonField, Alignment.MIDDLE_RIGHT);

        setContent(layoutField);
    }

    private <T> void builItemButtonField(T entityButton, String caption, boolean result) {
        Button button = (Button) entityButton;
        button.setCaption(caption);
        layoutField.addComponent((Component) entityButton);
        button.addClickListener(clickEvent -> {
            this.result = result;
            close();
        });
    }

    public boolean getResult() {
        return result;
    }
}

