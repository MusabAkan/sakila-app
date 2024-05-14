package com.msbkn.sakila.ui.common.pages;

import com.msbkn.sakila.ui.MyUI;
import com.msbkn.sakila.ui.common.components.SkVerticalLayoutField;
import com.msbkn.sakila.ui.pages.*;
import com.msbkn.sakila.ui.pages.component.*;
import com.vaadin.ui.Tree;

public class Sidebar extends SkVerticalLayoutField {
    private Tree childrenListTree;
    private Content content;

    private String flimOperationStr = "Flim İşlemler";
    private String flimAddStr = "Flim Ekle";
    private String flimListStr = "Flim Görüntüleme";
    private String actorOperationStr = "Aktör İşlemmler";
    private String actorAddStr = "Aktör Ekle";
    private String actorListStr = "Aktör Görüntüleme";
    private String languageOperationStr = "Dil İşlemler";
    private String languageAddStr = "Dil Ekle";
    private String languageListStr = "Dil Görüntüleme";


    public Sidebar(Content content) {
        this.content = content;
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

        childrenListTree.setChildrenAllowed(languageAddStr, false);
        childrenListTree.setChildrenAllowed(languageListStr, false);
        childrenListTree.setChildrenAllowed(actorAddStr, false);
        childrenListTree.setChildrenAllowed(actorListStr, false);
        childrenListTree.setChildrenAllowed(flimAddStr, false);
        childrenListTree.setChildrenAllowed(flimListStr, false);

        selectedChildrenList();

        addComponent(childrenListTree);
    }

    private void selectedChildrenList() {
        childrenListTree.addItemClickListener(event -> {
            String selectedItemChildren = event.getItemId().toString();

            if (selectedItemChildren == actorListStr)
                buildActorListPage();

            if (selectedItemChildren == actorAddStr)
                buildActorCardWindow();

            if (selectedItemChildren == languageListStr)
                buildLanguageListPage();

            if (selectedItemChildren == languageAddStr)
                buildLanguageCardWindow();

            if (selectedItemChildren == flimListStr)
                buildFilmListPage();

            if (selectedItemChildren == flimAddStr)
                buildFilmCardWindow();
        });
    }


    private void buildLanguageListPage() {
        LanguageListPage languageListPage = new LanguageListPage();
        content.removeAllComponents();
        content.addComponent(languageListPage);
    }

    private void buildLanguageCardWindow() {
        LanguageCardWindow languageCardWindow = new LanguageCardWindow();
        MyUI.getCurrent().addWindow(languageCardWindow);
        languageCardWindow.addCloseListener(closeEvent -> buildLanguageListPage());
    }

    private void buildActorListPage() {
        ActorListPage actorListPage = new ActorListPage();
        content.removeAllComponents();
        content.addComponent(actorListPage);
    }

    private void buildActorCardWindow() {
        ActorCardWindow actorCardWindow = new ActorCardWindow();
        MyUI.getCurrent().addWindow(actorCardWindow);
        actorCardWindow.addCloseListener(closeEvent -> buildActorListPage());
    }

    private void buildFilmListPage() {
        FilmListPage filmListPage = new FilmListPage();
        content.removeAllComponents();
        content.addComponent(filmListPage);
    }

    private void buildFilmCardWindow() {
        FilmCardWindow filmCardWindow = new FilmCardWindow();
        MyUI.getCurrent().addWindow(filmCardWindow);
        filmCardWindow.addCloseListener(closeEvent -> buildFilmListPage());
    }


}
