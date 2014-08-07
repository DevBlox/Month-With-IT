package com.tieto.it2014.domain.user.command;

import com.tieto.it2014.domain.user.entity.User;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommand implements Serializable {

    private static final long serialVersionUID = 1L;
    

    public User create() {
        return new User();
    }
    
    public void execute(User user) {
        
    }

}
