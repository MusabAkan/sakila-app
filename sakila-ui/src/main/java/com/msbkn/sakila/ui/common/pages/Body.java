package com.msbkn.sakila.ui.common.pages;

import com.vaadin.ui.HorizontalLayout;

public class Body extends HorizontalLayout {
    private Content content;
    private Sidebar sidebar;
    private Header header;

    public Body(Header header) {
        this.header = header;
        setSizeFull();
        setSpacing(true);
        buidContentLayout();
    }

    private void buidContentLayout() {
        content = new Content();
        sidebar = new Sidebar(content, header);

        addComponent(sidebar);
        addComponent(content);

        setExpandRatio(sidebar, 0.2f);
        setExpandRatio(content, 0.8f);
    }
}
