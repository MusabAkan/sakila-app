package com.msbkn.sakila.ui.common.pages;

import com.msbkn.sakila.ui.MyUI;
import com.msbkn.sakila.ui.common.components.*;
import com.msbkn.sakila.ui.pages.ActorListPage;
import com.msbkn.sakila.ui.pages.FilmListPage;
import com.msbkn.sakila.ui.pages.LanguageListPage;
import com.msbkn.sakila.ui.pages.windows.ActorCardWindow;
import com.msbkn.sakila.ui.pages.windows.FilmCardWindow;
import com.msbkn.sakila.ui.pages.windows.LanguageCardWindow;
import com.vaadin.ui.*;

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
                buildItemListPage(new ActorListPage());

            if (selectedItemChildren == actorAddStr)
                buildCardWindow(new ActorCardWindow()); ;

            if (selectedItemChildren == languageListStr)
                buildItemListPage(new LanguageListPage());

            if (selectedItemChildren == languageAddStr)
                buildCardWindow(new LanguageCardWindow());

            if (selectedItemChildren == flimListStr)
                buildItemListPage(new FilmListPage());

            if (selectedItemChildren == flimAddStr)
                buildCardWindow(new FilmCardWindow());
        });
    }
    private <T> void buildCardWindow(T cardWindowEntity) {
        Window cardWindow = (Window) cardWindowEntity;
        MyUI.getCurrent().addWindow(cardWindow);
        cardWindow.addCloseListener(closeEvent -> buildItemListPage(cardWindowEntity));
    }

    private <T> void buildItemListPage(T itemListPage) {
        content.removeAllComponents();
        content.addComponent((Component) itemListPage);
    }
}
