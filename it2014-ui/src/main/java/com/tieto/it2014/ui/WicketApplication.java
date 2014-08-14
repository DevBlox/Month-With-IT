package com.tieto.it2014.ui;

import com.tieto.it2014.ui.error.ErrorPage404;
import com.tieto.it2014.ui.session.UserSession;
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
    private static final String UTF = "UTF-8";

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, this.context, true));
        getRequestCycleSettings().setResponseRequestEncoding(UTF);
        // disable wicket ajax debug
        getDebugSettings().setAjaxDebugModeEnabled(false);
        getMarkupSettings().setDefaultMarkupEncoding(UTF);
        mountPage("error404", ErrorPage404.class);
        // http://apache-wicket.1842946.n4.nabble.com/How-Runtime-Exception-Handling-td1888907.html
        //getApplicationSettings().setPageExpiredErrorPage(PageExpiredErrorPage.class);
        //getApplicationSettings().setAccessDeniedPage(AccessDeniedPage.class);
        //getApplicationSettings().setInternalErrorPage(InternalErrorPage.class);
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
