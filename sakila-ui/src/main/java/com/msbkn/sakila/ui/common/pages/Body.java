package com.msbkn.sakila.ui.common.pages;

import com.msbkn.sakila.ui.common.components.SkHorizontalLayoutField;
import com.vaadin.ui.HorizontalLayout;

public class Body extends SkHorizontalLayoutField {
    private Content content;
    private Sidebar sidebar;
    private Header header;

    public Body() {
        this.header = header;
        buidContentLayout();
    }

    private void buidContentLayout() {
        content = new Content();
        sidebar = new Sidebar(content);

        addComponent(sidebar);
        addComponent(content);

        setExpandRatio(sidebar, 0.2f);
        setExpandRatio(content, 0.8f);
    }
}
