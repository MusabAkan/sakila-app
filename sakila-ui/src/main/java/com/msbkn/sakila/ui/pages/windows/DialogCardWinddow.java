package com.msbkn.sakila.ui.pages.windows;

import com.msbkn.sakila.ui.common.components.SkFailedButtonField;
import com.msbkn.sakila.ui.common.components.SkHorizontalLayoutField;
import com.msbkn.sakila.ui.common.components.SkSuccessButtonField;
import com.vaadin.ui.*;

public class DialogCardWinddow extends Window {

    private boolean result;
    private SkSuccessButtonField successButtonField;
    private SkFailedButtonField failedButtonField;

    public DialogCardWinddow(String titleSubjectField) {
        super(titleSubjectField);

        center();
        setClosable(false);
        setModal(true);
        setResizable(false);
        setWidth("600px");
        setHeight("150px");

        SkHorizontalLayoutField layoutField = new SkHorizontalLayoutField();

        successButtonField = new SkSuccessButtonField();
        buildSuccesButtonField();
        layoutField.addComponent(successButtonField);


        failedButtonField = new SkFailedButtonField();
        buildFailedButtonField();
        layoutField.addComponent(failedButtonField);


        layoutField.setComponentAlignment(successButtonField, Alignment.MIDDLE_LEFT);
        layoutField.setComponentAlignment(failedButtonField, Alignment.MIDDLE_RIGHT);

        setContent(layoutField);
    }

    private void buildSuccesButtonField() {
        successButtonField.setCaption("Evet");
        successButtonField.addClickListener(clickEvent -> {
            result = true;
            close();
        });
    }

    private void buildFailedButtonField() {
        failedButtonField.setCaption("Hayır");
        failedButtonField.addClickListener(clickEvent -> {
            result = false;
            close();
        });
    }

    public boolean getResult() {
        return result;
    }
}

