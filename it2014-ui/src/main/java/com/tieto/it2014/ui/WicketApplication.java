package com.tieto.it2014.ui;

import com.tieto.it2014.ui.achievements.AchievmentsPage;
import com.tieto.it2014.ui.error.ErrorPage404;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.user.*;
import com.tieto.it2014.ui.workout.details.Details;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.settings.IExceptionSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class WicketApplication extends WebApplication implements ApplicationContextAware {

    private ApplicationContext context;
    private static final String UTF = "UTF-8";

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();
        initSpring();
        getRequestCycleSettings().setResponseRequestEncoding(UTF);
        // disables wicket ajax debug
        getDebugSettings().setAjaxDebugModeEnabled(false);
        getMarkupSettings().setDefaultMarkupEncoding(UTF);

        getApplicationSettings().setPageExpiredErrorPage(ErrorPage404.class);
        getApplicationSettings().setAccessDeniedPage(ErrorPage404.class);
        getApplicationSettings().setInternalErrorPage(ErrorPage404.class);
        getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);

        mountPage("error404", ErrorPage404.class);
        mountPage("register", RegisterPage.class);
        mountPage("user/${userImei}/workout/${imei}/${id}", Details.class);
        mountPage("user/${userImei}", HomePage.class);
        mountPage("user/weight", WeightPage.class);
        mountPage("user/stats", StatisticsPage.class);
        mountPage("user/edit", ProfilePage.class);
        mountPage("top", TopPage.class);
        mountPage("user/achievments/", AchievmentsPage.class);
        mountPage("activate/${userMail}/${token}", Activation.class);
    }

    protected void initSpring() {
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, this.context, true));
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
