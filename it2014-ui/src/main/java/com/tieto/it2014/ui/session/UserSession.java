package com.tieto.it2014.ui.session;

import com.tieto.it2014.domain.user.entity.User;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class UserSession extends WebSession {

    private User user;

    public UserSession(Request request) {
        super(request);
    }

    public static UserSession get() {
        return (UserSession) WebSession.get();
    }

    public boolean isLoggedIn() {
        return hasUser();
    }

    public boolean hasUser() {
        return user != null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
