package com.msbkn.sakila.ui.pages.component;

import com.msbkn.sakila.domain.Actor;
import com.msbkn.sakila.ui.common.components.*;
import com.vaadin.ui.*;

import java.text.Format;

public class ActorCardWindow extends Window {
    SkLabelField idTextField;
    SkTextField nameTextField;
    SkTextField lastNameTextField;
    SkSaveButtonField saveButtonField;


    public ActorCardWindow() {
        buildWindow();
    }

    public ActorCardWindow(Actor actor) {
        this();
        fillWindowByActor(actor);
    }

    private void fillWindowByActor(Actor actor) {

        String idField = actor.getId().toString();
        idTextField.setValue(idField);

        String lastNameField = actor.getLastName().toString();
        lastNameTextField.setValue(lastNameField);

        String nameField = actor.getFirstName().toString();
        nameTextField.setValue(nameField);

    }


    private void buildWindow() {
        SkVerticalLayoutField verticalLayout = new SkVerticalLayoutField();

        setModal(true);
        setWidth("30%");
        setWidth("35%");

        FormLayout formLayout = new FormLayout();
        verticalLayout.addComponent(formLayout);

        idTextField = new SkLabelField();
        idTextField.setCaption("Id :");
        formLayout.addComponent(idTextField);

        nameTextField = new SkTextField();
        nameTextField.setCaption("Adı:");
        formLayout.addComponent(nameTextField);

        lastNameTextField = new SkTextField();
        lastNameTextField.setCaption("Soyadı :");
        formLayout.addComponent(lastNameTextField);

        saveButtonField = new SkSaveButtonField();
        formLayout.addComponent(saveButtonField);

        setContent(verticalLayout);


    }


}
