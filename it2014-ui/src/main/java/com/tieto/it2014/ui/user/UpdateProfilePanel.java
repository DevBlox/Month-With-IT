package com.tieto.it2014.ui.user;

import com.google.common.base.Objects;
import com.tieto.it2014.domain.user.command.SaveUserCommand;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.util.Hash;
import com.tieto.it2014.ui.HomePage;
import com.tieto.it2014.ui.session.UserSession;
import static com.tieto.it2014.ui.utils.UIUtils.withInfoMsg;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

public class UpdateProfilePanel extends Panel {

    private static final long serialVersionUID = 1L;

    public UpdateProfilePanel(String wicketid) {
        super(wicketid);
    }

    private User user;
    private Form form;

    private String oldPasswordString;
    private String newPasswordString;
    private String repeatNewPasswordString;
    private static final int MIN_PASSWORD_LENGHT = 5;
    private static final int MAX_PASSWORD_LENGHT = 30;

    @SpringBean
    private SaveUserCommand saveUser;

    @Override
    protected void onInitialize() {

        super.onInitialize();

        if (!UserSession.get().hasUser()) {
            setResponsePage(HomePage.class);
        }

        user = UserSession.get().getUser();

        form = new Form("updateProfileForm");
        PasswordTextField newPassword = new PasswordTextField("inputNewPassword",
                new PropertyModel(this, "newPasswordString"));
        PasswordTextField repeatNewPassword = new PasswordTextField("repeatNewPassword",
                new PropertyModel(this, "repeatNewPasswordString"));
        PasswordTextField oldPassword = new PasswordTextField("inputOldPassword",
                new PropertyModel(this, "oldPasswordString"));

        form.add(new TextField("inputUserName",
                new PropertyModel(user, "username"))
                .setRequired(true)
                .add(new StringValidator(1, 30))
        );
        form.add(newPassword
                .setRequired(false));
        form.add(repeatNewPassword
                .setRequired(false));
        form.add(oldPassword
                .setRequired(false));
        form.add(new Label("email", new PropertyModel(user, "email")));
        form.add(new Label("imei", new PropertyModel(user, "imei")));
        form.add(initRegisterButton("updateButton"));
        form.add(initCancelButton("cancelButton"));

        form.add(new FeedbackPanel("updateFeedback",
                new ContainerFeedbackMessageFilter(form)));

        add(form);
    }

    @Override
    protected void onConfigure() {
        if (!UserSession.get().hasUser()) {
            setResponsePage(HomePage.class);
        }
    }

    private Link initCancelButton(String id) {
        return new Link(id) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }

        };
    }

    private Component initRegisterButton(String wicketId) {
        return new Button(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                actionUpdateUser();
            }

        };
    }

    private void actionUpdateUser() {

        if (StringUtils.isNotBlank(oldPasswordString)
                && StringUtils.isNotBlank(newPasswordString)
                && StringUtils.isNotBlank(repeatNewPasswordString)) {

            if (!checkLength(MIN_PASSWORD_LENGHT, MAX_PASSWORD_LENGHT, newPasswordString.length())) {
                form.error("Password must be between " + MIN_PASSWORD_LENGHT + " and "
                        + MAX_PASSWORD_LENGHT);
                return;
            }

            if (Objects.equal(oldPasswordString, newPasswordString)) {
                form.error("You can't set the same password!");
                return;
            }

            if (!Objects.equal(newPasswordString, repeatNewPasswordString)) {
                form.error("Passwords do not match!");
                return;
            } else if (Objects.equal(user.password, Hash.sha256(oldPasswordString))) {
                user.password = Hash.sha256(newPasswordString);
            } else {
                form.error("Incorrect old password.");
                return;
            }
        } else if (StringUtils.isNotBlank(oldPasswordString)
                || StringUtils.isNotBlank(newPasswordString)
                || StringUtils.isNotBlank(repeatNewPasswordString)) {
            form.error("Not all password fields are entered.");
            return;
        }

        saveUser.execute(user);
        UserSession.get().setUser(user);

        setResponsePage(withInfoMsg(new HomePage(), "You have successfuly updated your profile"));

    }

    private boolean checkLength(int low, int high, int msgLength) {
        return msgLength <= high && msgLength >= low;
    }
}
