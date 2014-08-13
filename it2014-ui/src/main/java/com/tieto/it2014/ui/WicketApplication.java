package com.tieto.it2014.ui;

import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.user.UserFriendsPage;
import com.tieto.it2014.ui.user.UserWorkoutsPage;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class WicketApplication extends WebApplication implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, this.context, true));
        getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
        getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
        mountPage("register", RegisterPage.class);
        mountPage("user/${userId}/workouts", UserWorkoutsPage.class);
        mountPage("user/${userId}/friends", UserFriendsPage.class);
        mountPage("main", BasePage.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new UserSession(request);
    }

}
