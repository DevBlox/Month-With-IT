package com.tieto.it2014.domain.user.command;

import com.tieto.it2014.domain.user.entity.User;
import java.io.Serializable;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommand implements Serializable {

    private static final long serialVersionUID = 1L;
    

    private User user = null;

    public CreateUserCommand() {        
        user = new User();
    }
    
    public User getUser() {        
        return user;
    }
    
    public void deleteUser() {
        user = null;
    }
}
