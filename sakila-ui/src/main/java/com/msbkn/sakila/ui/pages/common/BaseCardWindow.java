package com.msbkn.sakila.ui.pages.common;

import com.msbkn.sakila.domain.*;
import com.msbkn.sakila.ui.pages.windows.*;
import com.vaadin.ui.*;

public class BaseCardWindow<T> extends Window {

    public <T> Window GetCardWindow(T selectItemField) {
        if (selectItemField instanceof Language)
            return new LanguageCardWindow((Language) selectItemField);
        if (selectItemField instanceof Actor)
            return new ActorCardWindow((Actor) selectItemField);
        if (selectItemField instanceof Film)
            return new FilmCardWindow((Film) selectItemField);
        return null;
    }
}
