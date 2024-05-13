package com.msbkn.sakila.ui.pages.component;

import com.msbkn.sakila.domain.Actor;
import com.msbkn.sakila.ui.common.components.*;
import com.vaadin.ui.*;

public class ActorCardWindow extends SkWindowField {
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

        String nameField = actor.getFirstName();
        nameTextField.setValue(nameField);

        String lastNameField = actor.getLastName();
        lastNameTextField.setValue(lastNameField);
    }


    private void buildWindow() {
        SkVerticalLayoutField verticalLayout = new SkVerticalLayoutField();

        SkFormLayoutField formLayout = new SkFormLayoutField();
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
