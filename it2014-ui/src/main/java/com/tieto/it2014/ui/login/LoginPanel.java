package com.tieto.it2014.ui.login;

import com.tieto.it2014.domain.DomainException;
import com.tieto.it2014.domain.achievment.entity.UserAchievement;
import com.tieto.it2014.domain.achievment.query.UserAchievementsQuery;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.LoggedInUserQuery;
import com.tieto.it2014.ui.HomePage;
import com.tieto.it2014.ui.achievements.AchievementsChecker;
import com.tieto.it2014.ui.session.UserSession;
import static com.tieto.it2014.ui.utils.UIUtils.withInfoMsg;
import com.tieto.it2014.ui.weight.detail.RandomQuote;
import java.util.List;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jboss.logging.Logger;

public class LoginPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(LoginPanel.class);

    private String email;
    private String password;
    private Form form;
    private Link logoutButton;
    private Button loginButton;
    private PasswordTextField passwordTextField;
    private TextField textField;
    private Label loggedUserLabel;
    private Model<String> labelModel;
    private User loggedInUser;
    private List<UserAchievement> listOfAchievments;

    @SpringBean
    private LoggedInUserQuery loggedInUserQuery;

    @SpringBean
    private UserAchievementsQuery userAchievementsQuery;

    @SpringBean
    private AchievementsChecker achievementChecker;

    public LoginPanel(String wicketid) {
        super(wicketid);
    }

    @Override
    protected void onInitialize() {

        super.onInitialize();
        form = new Form("loginForm");

        passwordTextField = new PasswordTextField("inputPassword",
                new PropertyModel(this, "password"));

        textField = new TextField("inputEmail",
                new PropertyModel(this, "email"));

        labelModel = new Model("");

        loggedUserLabel = new Label("loggedUserLabel", labelModel);
        form.add(loggedUserLabel);

        form.add(passwordTextField);
        form.add(textField);

        loginButton = initLoginButton("loginButton");
        loginButton.setOutputMarkupId(true);
        form.add(loginButton);

        logoutButton = initLogoutButton("logoutButton");
        logoutButton.setOutputMarkupId(true);
        form.add(logoutButton);

        form.add(new FeedbackPanel("loginFeedback",
                new ContainerFeedbackMessageFilter(form)));

        add(form);

    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        logoutButton.setVisible(UserSession.get().hasUser());
        loginButton.setVisible(!UserSession.get().hasUser());
        passwordTextField.setVisible(!UserSession.get().hasUser());
        textField.setVisible(!UserSession.get().hasUser());
        loggedUserLabel.setVisible(UserSession.get().hasUser());

        if (UserSession.get().hasUser()) {
            String userNameAndEmail = RandomQuote.getHelloMsg()
                    + UserSession.get().getUser().username
                    + " (" + UserSession.get().getUser().email + ")";
            labelModel.setObject(userNameAndEmail);
        }

    }

    private Button initLoginButton(String wicketId) {
        return new Button(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                buttonAction();

            }

        };
    }

    private Link initLogoutButton(String wicketId) {
        return new Link(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                UserSession.get().invalidate();
                setResponsePage(HomePage.class);
            }

        };
    }

    private void buttonAction() {
        try {
            loggedInUser = loggedInUserQuery.result(email, password);
            UserSession.get().setUser(loggedInUser);
            listOfAchievments = userAchievementsQuery.result(UserSession.get().getUser().imei);
            logoutButton.setVisible(true);
            int achievCount = getNewUserAchievementsCount();
            String achievmentStr = "";
            if (achievCount > 0) {
                if (achievCount == 1) {
                    achievmentStr = "achievment";
                } else {
                    achievmentStr = "achievments";
                }

                setResponsePage(withInfoMsg(new HomePage(), "Congrats! You have " + achievCount + "  new " + achievmentStr));
            } else {
                setResponsePage(HomePage.class);
            }
        } catch (DomainException ex) {
            LOGGER.error(ex.getMessage(), ex);
            form.error(ex.getMessage());
        }
    }

    private int getNewUserAchievementsCount() {
        int achievCount = 0;
        for (UserAchievement achievement : listOfAchievments) {
            if (achievement.getDate() == null) {
                if (achievementChecker.checksAchievementById(achievement.getAchievmentId(), UserSession.get().getUser().imei)) {
                    achievCount++;
                }
            }
        }
        return achievCount;
    }

}
