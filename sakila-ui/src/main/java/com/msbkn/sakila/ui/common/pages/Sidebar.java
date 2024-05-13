package com.msbkn.sakila.ui.common.pages;

import com.msbkn.sakila.ui.common.components.SkVerticalLayoutField;
import com.msbkn.sakila.ui.pages.ActorListPage;
import com.msbkn.sakila.ui.pages.FilmListPage;
import com.msbkn.sakila.ui.pages.LanguageListPage;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

public class Sidebar extends SkVerticalLayoutField {
    private Tree childrenListTree;
    private Content content;
    private Header header;

    private String flimOperationStr = "Flim İşlemleri";
    private String flimAddStr = "Flim Ekle";
    private String flimListStr = "Flim Listele";
    private String actorOperationStr = "Aktör İşlemmleri";
    private String actorAddStr = "Aktör Ekle";
    private String actorListStr = "Aktör Listele";
    private String languageOperationStr = "Dil İşlemleri";
    private String languageAddStr = "Dil Ekle";
    private String languageListStr = "Dil Listele";


    public Sidebar(Content content, Header header) {
        this.content = content;
        this.header = header;

        builSidebarLayout();
    }

    private void builSidebarLayout() {

        childrenListTree = new Tree();

        childrenListTree.addItem(flimOperationStr);
        childrenListTree.addItem(flimAddStr);
        childrenListTree.addItem(flimListStr);
        childrenListTree.addItem(actorOperationStr);
        childrenListTree.addItem(actorAddStr);
        childrenListTree.addItem(actorListStr);
        childrenListTree.addItem(languageOperationStr);
        childrenListTree.addItem(languageAddStr);
        childrenListTree.addItem(languageListStr);


        childrenListTree.setParent(flimListStr, flimOperationStr);
        childrenListTree.setParent(flimAddStr, flimOperationStr);
        childrenListTree.setParent(languageListStr, languageOperationStr);
        childrenListTree.setParent(languageAddStr, languageOperationStr);
        childrenListTree.setParent(actorListStr, actorOperationStr);
        childrenListTree.setParent(actorAddStr, actorOperationStr);


        //areChildrenAllowed = parentes başka var mı ?
        childrenListTree.setChildrenAllowed(languageAddStr, false);
        childrenListTree.setChildrenAllowed(languageListStr, false);
        childrenListTree.setChildrenAllowed(actorAddStr, false);
        childrenListTree.setChildrenAllowed(actorListStr, false);
        childrenListTree.setChildrenAllowed(flimListStr, false);
        childrenListTree.setChildrenAllowed(flimAddStr, false);


        selectedChildrenList();

        addComponent(childrenListTree);
    }

    private void selectedChildrenList() {
        childrenListTree.addItemClickListener(event -> {

            String selectedItemChildren = event.getItemId().toString();

            if (isItemEqual(selectedItemChildren, actorListStr)) {
                ActorListPage actorListPage = new ActorListPage();
                loadFormPage(actorListPage);
            }

            if (isItemEqual(selectedItemChildren, languageListStr)) {
                LanguageListPage languageListPage = new LanguageListPage();
                loadFormPage(languageListPage);
            }

            if (isItemEqual(selectedItemChildren, flimListStr)) {
                FilmListPage filmListPage = new FilmListPage();
                loadFormPage(filmListPage);
            }
        });
    }

    private boolean isItemEqual(String searchField, String searchedField) {
        return searchField.equals(searchedField);
    }

    private void loadFormPage(Component page) {
        content.removeAllComponents();
        content.addComponent(page);
    }


}
