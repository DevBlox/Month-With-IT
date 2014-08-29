package com.tieto.it2014.ui.user;

import com.tieto.it2014.domain.user.command.SaveUserCommand;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByEmailQuery;
import com.tieto.it2014.domain.util.MailSender;
import com.tieto.it2014.domain.util.Util;
import com.tieto.it2014.ui.BasePage;
import com.tieto.it2014.ui.HomePage;
import com.tieto.it2014.ui.session.UserSession;
import static com.tieto.it2014.ui.utils.UIUtils.withInfoMsg;
import java.util.Date;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.Strings;

/**
 * Created by mantas on 28/08/14.
 */
public final class Activation extends BasePage {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(Activation.class);

    @SpringBean
    private SaveUserCommand saveUser;

    @SpringBean
    private GetUserByEmailQuery getUserByEmailQuery;

    private String userMail;
    private String token;
    private Long timestamp;
    private String userToken;
    private User user;
    Long currentTimestamp = new Date().getTime();

    public Activation(final PageParameters params) {
        userMail = params.get("userMail").toString();
        token = params.get("token").toString();
        timestamp = Long.parseLong(token.substring(token.length() - 13));
        if (UserSession.get().isLoggedIn()) {
            UserSession.get().invalidate();
        }
        if (activateIfTimestampIsValid()) {
            saveUser.execute(user);
            setResponsePage(withInfoMsg(new HomePage(), "User activated successfully. Now you can log in!"));
        } else {
            user.setToken(UUID.randomUUID().toString() + "-" + currentTimestamp);
            saveUser.execute(user);
            try {
                MailSender.send(user.email, "Do not reply", user.username, user.getToken());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            setResponsePage(withInfoMsg(new HomePage(), "Link is invalid! Check your mailbox for new one"));
        }
    }

    public boolean activateIfTimestampIsValid() {
        user = getUserByEmailQuery.result(userMail);
        userToken = user.getToken();
        if (Strings.isEqual(userToken, token) && Util.calculateDuration(timestamp, currentTimestamp) <= 60) {
            user.setActivated(true);
            return true;
        }
        return false;
    }

}
