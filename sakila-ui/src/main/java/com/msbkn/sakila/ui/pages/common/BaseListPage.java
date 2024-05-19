package com.msbkn.sakila.ui.pages.common;

import com.msbkn.sakila.common.*;
import com.msbkn.sakila.domain.Actor;
import com.msbkn.sakila.domain.Film;
import com.msbkn.sakila.domain.Language;
import com.msbkn.sakila.ui.MyUI;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.common.components.SkVerticalLayoutField;
import com.msbkn.sakila.ui.pages.windows.*;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Like;
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

    protected void addItemTextFilterPanel(String caption, String searchStr) {
        SkTextField textField = new SkTextField();
        textField.setCaption(caption);
        textField.addTextChangeListener(event -> {
            String searchIdField = event.getText();
            filterSearch(searchIdField, searchStr, tableDataField);
        });
        filterLayoutField.addComponent(textField);
    }

    protected <T> void buildItemDeleteField(T entityField, T serviceField) {
        deleteButtonField = new SkDeleteButtonField();
        deleteButtonField.setData(entityField);
        addItemTableData(entityField, deleteButtonField, emptyStr);
        service = (BaseService) serviceField;
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


    protected void filterSearch(String filterString, String columnName, Table table) {
        Container.Filterable filter = (Container.Filterable) (table.getContainerDataSource());
        filter.removeAllContainerFilters();
        if (filterString.length() == 0) fillDataField();
        filter.addContainerFilter(new Like(columnName, "%" + filterString + "%"));
    }

    protected <T> void doubleClickSelectItem() {
        tableDataField.addItemClickListener(event -> {
            boolean isDoubleClick = event.isDoubleClick();
            if (isDoubleClick) {
                BaseEntity selectItemField = (BaseEntity) event.getItemId();
                Window cardWindow = getCardWindow(selectItemField);
                MyUI.getCurrent().addWindow(cardWindow);
                cardWindow.addCloseListener(closeEvent -> fillDataField());
            }
        });
    }

    private static <T> Window getCardWindow(T selectItemField) {
        if (selectItemField instanceof Language)
            return new LanguageCardWindow((Language) selectItemField);
        if (selectItemField instanceof Actor)
            return new ActorCardWindow((Actor) selectItemField);
        if (selectItemField instanceof Film)
            return new FilmCardWindow((Film) selectItemField);
        return null;
    }

    protected <T> void addItemTableColumn(String propertyId, Class<T> type, Object defayltObject) {
        tableDataField.addContainerProperty(propertyId, type, defayltObject);
    }

    protected <T> void addItemTableData(T itemId, Object setValue, Object propertyId) {
        tableDataField.addItem(itemId);
        tableDataField.getContainerProperty(itemId, propertyId).setValue(setValue);
    }

    protected void removeTableAllField() {
        tableDataField.removeAllItems();
    }
}
