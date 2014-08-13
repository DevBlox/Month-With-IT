/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tieto.it2014.ui;

import com.tieto.it2014.ui.header.HeaderPanel;
import com.tieto.it2014.ui.register.RegisterPanel;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;

/**
 *
 * @author pc3
 */
public class RegisterPage extends BasePage {

    private static final long serialVersionUID = 1L;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        initContent(CONTENT_ID);
    }

    @Override
    protected Component initContent(String wicketId) {
        return new RegisterPanel(wicketId);
    }
}
