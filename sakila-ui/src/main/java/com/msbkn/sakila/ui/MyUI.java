package com.msbkn.sakila.ui;

import javax.servlet.annotation.*;
import com.msbkn.sakila.ui.common.pages.*;
import com.vaadin.annotations.*;
import com.vaadin.server.*;
import com.vaadin.ui.*;


@Theme("mytheme")
@Widgetset("com.msbkn.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout layout = new VerticalLayout();

        layout.setSizeFull();

        Header header = new Header();
        layout.addComponent(header);

        Body body = new Body(header);
        layout.addComponent(body);

        Footer footer = new Footer();
        layout.addComponent(footer);

        layout.setExpandRatio(header, 0.5f);
        layout.setExpandRatio(body, 3f);
        layout.setExpandRatio(footer, 0.3f);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
