package com.msbkn.sakila.ui.pages.windows;

import com.msbkn.sakila.domain.Actor;
import com.msbkn.sakila.service.ActorService;
import com.msbkn.sakila.ui.common.components.*;
import com.vaadin.ui.Notification;

import java.util.Date;

public class ActorCardWindow extends SkWindowField {
    private SkLabelField actorIdTextField;
    private SkTextField actorNameTextField;
    private SkTextField actorLastNameTextField;
    private SkSaveButtonField actorSaveButtonField;
    private SkVerticalLayoutField verticalLayoutField;
    private ActorService actorService;
    private Actor selectActorField;


    public ActorCardWindow() {
        buildWindowField();
    }

    public ActorCardWindow(Actor actor) {
        this();
        fillWindowByActor(actor);
    }

    private void fillWindowByActor(Actor actor) {
        selectActorField = actor;

        String idField = String.valueOf(actor.getId());
        actorIdTextField.setValue(idField);

        String nameField = actor.getFirstName();
        actorNameTextField.setValue(nameField);

        String lastNameField = actor.getLastName();
        actorLastNameTextField.setValue(lastNameField);
    }


    private void buildWindowField() {
        verticalLayoutField = new SkVerticalLayoutField();
        selectActorField = new Actor();
        actorService = new ActorService();

        SkFormLayoutField formLayout = new SkFormLayoutField();
        verticalLayoutField.addComponent(formLayout);

        actorIdTextField = new SkLabelField();
        actorIdTextField.setCaption("Id :");
        formLayout.addComponent(actorIdTextField);

        actorNameTextField = new SkTextField();
        actorNameTextField.setCaption("Adı:");
        formLayout.addComponent(actorNameTextField);

        actorLastNameTextField = new SkTextField();
        actorLastNameTextField.setCaption("Soyadı :");
        formLayout.addComponent(actorLastNameTextField);

        actorSaveButtonField = new SkSaveButtonField();
        formLayout.addComponent(actorSaveButtonField);
        buildSaveActorField();

        setContent(verticalLayoutField);

    }

    private void buildSaveActorField() {
        actorSaveButtonField.addClickListener(clickEvent -> {

            String actorNameField = actorNameTextField.getValue();
            selectActorField.setFirstName(actorNameField);

            String actorLastNameField = actorLastNameTextField.getValue();
            selectActorField.setLastName(actorLastNameField);

            Date actorLastUpdateField = new Date();
            selectActorField.setLastUpdate(actorLastUpdateField);

            Long actorFieldId = selectActorField.getId();

            if (actorFieldId == null)
                addItemField(selectActorField, actorService);

            else
                uptadeItemField(selectActorField, actorService);

            quit();

        });
    }
}
