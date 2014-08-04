package com.tieto.it2014.ui.user;

import com.tieto.it2014.dao.user.query.GetUserByIdQueryDaoMem;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class EditUserPage extends WebPage {
    private static final long serialVersionUID = 1L;
    public static final String USER_ID = "userId";

    @SpringBean
    private GetUserByIdQuery getUserByIdQuery;
    private final Long userId;

    public static PageParameters parametersWith(Long userId) {
        return new PageParameters().add(USER_ID, userId);
    }

    public EditUserPage(PageParameters params) {
        userId = params.get(USER_ID).toLong();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        User user = getUserByIdQuery.resultOrNull(userId);
        add(new EditUserPanel("editUser", user));
    }
}
