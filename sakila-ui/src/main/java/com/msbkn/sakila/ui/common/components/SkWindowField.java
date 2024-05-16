package com.msbkn.sakila.ui.common.components;

import com.msbkn.sakila.common.CommonService;
import com.vaadin.ui.*;

public class SkWindowField extends Window {

    public SkWindowField() {
        center();
        setWidth("30%");
        setHeight("35%");
        setModal(true);
    }

    public void quit() {
        this.close();
    }

//    protected <T> void addItemField(T entityField, T entityServiceField) {
//        CommonService<T> service = (CommonService<T>) entityServiceField;
//        service.save(entityField);
//        String simpleName = entityField.getClass().getSimpleName();
//        Notification.show(fetchTurkishNamField(simpleName) + " tarafında ekleme işlemi yapılmıştır");
//    }
//
//
//    protected <T> void uptadeItemField(T entityField, T entityServiceField) {
//        CommonService<T> service = (CommonService<T>) entityServiceField;
//        service.update(entityField);
//        String simpleName = entityField.getClass().getSimpleName();
//        Notification.show(fetchTurkishNamField(simpleName) + " tarafında güncelleme işlemi yapılmıştır");
//    }
//
//    private String fetchTurkishNamField(String simpleName) {
//        if (simpleName.contains("Language"))
//            return "Dil";
//        else if (simpleName.contains("Actor"))
//            return "Aktör";
//        else if (simpleName.contains("Film"))
//            return "Flim";
//        return simpleName;
//    }

}
