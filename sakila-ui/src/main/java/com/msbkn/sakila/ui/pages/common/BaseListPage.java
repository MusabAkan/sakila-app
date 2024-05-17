package com.msbkn.sakila.ui.pages.common;

import com.msbkn.sakila.common.BaseEntity;
import com.msbkn.sakila.common.BaseService;
import com.msbkn.sakila.domain.Language;
import com.msbkn.sakila.ui.MyUI;
import com.msbkn.sakila.ui.common.components.SkDeleteButtonField;
import com.msbkn.sakila.ui.common.components.SkFormLayoutField;
import com.msbkn.sakila.ui.common.components.SkTableField;
import com.msbkn.sakila.ui.common.components.SkVerticalLayoutField;
import com.msbkn.sakila.ui.pages.windows.DialogCardWinddow;
import com.msbkn.sakila.ui.pages.windows.LanguageCardWindow;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.awt.*;

public class BaseListPage extends VerticalLayout {
    public BaseListPage() {

    }

    private BaseService service;
    protected DialogCardWinddow dialogCardField;
    protected SkDeleteButtonField deleteButtonField;
    protected SkTableField tableDataField;
    protected String emptyStr = " ";
    protected SkFormLayoutField filterLayoutField;
    protected SkVerticalLayoutField verticalLayoutField;
    protected SkFormLayoutField formLayoutField;

    protected <T> void buildItemFilterPanel(T entityTextField, String caption, String searchStr) {
        TextField textField = (TextField) entityTextField;
        textField.setCaption(caption);
        textField.addTextChangeListener(event -> {
            String searchIdField = event.getText();
            formLayoutField.filterSearch(searchIdField, searchStr, tableDataField);
        });
    }

    protected <T> void buildItemDeleteField(T entityField, T serviceField) {
        deleteButtonField = new SkDeleteButtonField();
        service = (BaseService) serviceField;
        deleteButtonField.setData(entityField);
        tableDataField.getContainerProperty(entityField, emptyStr).setValue(deleteButtonField);
        deleteButtonField.addClickListener(event -> {

            T selectFilmField = (T) event.getButton().getData();
            Long idField = ((BaseEntity) selectFilmField).getId();
            String question = idField + " seçili id silmek istediğinizden emin misiniz?";
            DialogCardWinddow dialogCardWinddow = new DialogCardWinddow(question);
            MyUI.getCurrent().addWindow(dialogCardWinddow);

            dialogCardWinddow.addCloseListener(closeEvent -> {
                boolean dialogCardWindowResult = dialogCardWinddow.getResult();
                if (dialogCardWindowResult) {
                    service.delete(selectFilmField);
                    //fillDataField();
                }
            });
        });
    }

//    protected <T> void doubleClickSelectItem(T entityField, T entityCardField) {
//        tableDataField.addItemClickListener(event -> {
//            boolean isDoubleClick = event.isDoubleClick();
//            if (isDoubleClick) {
//                T selectItemField = (T) event.getItemId();
//                Window windowField = (Window) entityCardField;
//                LanguageCardWindow languageCardWindow = new LanguageCardWindow(selectItemField);
//                MyUI.getCurrent().addWindow(languageCardWindow);
//
//            }
//        });
//    }


}
