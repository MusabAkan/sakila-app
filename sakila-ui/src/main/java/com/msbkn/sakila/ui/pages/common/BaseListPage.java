package com.msbkn.sakila.ui.pages.common;

import com.msbkn.sakila.common.*;
import com.msbkn.sakila.ui.MyUI;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.common.components.SkVerticalLayoutField;
import com.msbkn.sakila.ui.pages.windows.*;
import com.vaadin.ui.*;

public abstract class BaseListPage extends VerticalLayout {
    private BaseService service;
    protected DialogCardWinddow dialogCardField;
    protected SkDeleteButtonField deleteButtonField;
    protected SkTableField tableDataField;
    protected String emptyStr = " ";
    protected SkFormLayoutField filterLayoutField;
    protected SkVerticalLayoutField verticalLayoutField;
    protected SkFormLayoutField formLayoutField;

    public abstract void fillDataField();

    public BaseListPage() {
        setSizeFull();
        verticalLayoutField = new SkVerticalLayoutField();
        filterLayoutField = new SkFormLayoutField();
        tableDataField = new SkTableField();
        dialogCardField = new DialogCardWinddow();
        formLayoutField = new SkFormLayoutField();
    }
    protected <T> void addTableColumn(String emptyStr, Class<T> clazz, Object object) {
        tableDataField.addContainerProperty(emptyStr, clazz, object);
    }
    protected void addItemTextFilterPanel(String caption, String searchStr) {
        SkTextField textField = new SkTextField();
        textField.setCaption(caption);
        textField.addTextChangeListener(event -> {
            String searchIdField = event.getText();
            formLayoutField.filterSearch(searchIdField, searchStr, tableDataField);
        });
        filterLayoutField.addComponent(textField);
    }

    protected <T> void buildItemDeleteField(T entityField, T serviceField) {
        deleteButtonField = new SkDeleteButtonField();
        service = (BaseService) serviceField;
        deleteButtonField.setData(entityField);
        tableDataField.getContainerProperty(entityField, emptyStr).setValue(deleteButtonField);
        deleteButtonField.addClickListener(event -> {

            BaseEntity selectedEntity = (BaseEntity) event.getButton().getData();
            Long idField = selectedEntity.getId();
            String question = idField + " seçili id silmek istediğinizden emin misiniz?";
            DialogCardWinddow dialogCardWinddow = new DialogCardWinddow(question);
            MyUI.getCurrent().addWindow(dialogCardWinddow);

            dialogCardWinddow.addCloseListener(closeEvent -> {
                boolean dialogCardWindowResult = dialogCardWinddow.getResult();
                if (dialogCardWindowResult) {
                    service.delete(selectedEntity);
                    fillDataField();
                }
            });
        });
    }

    protected <T> void doubleClickSelectItem() {
        tableDataField.addItemClickListener(event -> {
            boolean isDoubleClick = event.isDoubleClick();
            if (isDoubleClick) {
                BaseEntity selectItemField = (BaseEntity) event.getItemId();
                Window cardWindow = new BaseCardWindow().GetCardWindow(selectItemField);
                MyUI.getCurrent().addWindow(cardWindow);
                cardWindow.addCloseListener(closeEvent -> fillDataField());
            }
        });
    }


}
