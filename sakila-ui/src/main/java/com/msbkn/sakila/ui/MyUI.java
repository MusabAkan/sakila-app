package com.msbkn.sakila.ui;

import javax.servlet.annotation.*;

import com.msbkn.sakila.ui.common.components.SkVerticalLayoutField;
import com.msbkn.sakila.ui.common.pages.*;
import com.vaadin.annotations.*;
import com.vaadin.server.*;
import com.vaadin.ui.*;


@Theme("mytheme")
@Widgetset("com.msbkn.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        SkVerticalLayoutField layoutField = new SkVerticalLayoutField();

        Header header = new Header();
        layoutField.addComponent(header);

        Body body = new Body();
        layoutField.addComponent(body);

        Footer footer = new Footer();
        layoutField.addComponent(footer);

        layoutField.setExpandRatio(header, 0.5f);
        layoutField.setExpandRatio(body, 3f);
        layoutField.setExpandRatio(footer, 0.3f);

        setContent(layoutField);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
