package com.tieto.it2014.ui.user;

import com.tieto.it2014.dao.user.command.SaveUserCommandDaoMem;
import com.tieto.it2014.domain.user.command.SaveUserCommand;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.ui.HomePage;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;

public class EditUserPanel extends Panel {
    private static final long serialVersionUID = 1L;
    private final User user;

    @SpringBean
    private SaveUserCommand saveUser;

    public EditUserPanel(String id, User user) {
        super(id);
        this.user = user;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Form form = new Form("form");
        form.add(new FeedbackPanel("feedback"));
        form.add(new TextField("inputName", new PropertyModel(user, "name"))
                .setRequired(true));
        form.add(new TextField("inputYearOfBirth", new PropertyModel(user, "yearOfBirth"))
                .add(new RangeValidator(1900, 2100)));
        form.add(initSaveButton("save"));
        form.add(initCancelButton("cancel"));
        add(form);
    }

    private Component initSaveButton(String wicketId) {
        return new Button(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                saveUser.execute(user);
                setResponsePage(HomePage.class);
            }
        };
    }

    private Component initCancelButton(String wicketId) {
        return new Link(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }
        };
    }
}
