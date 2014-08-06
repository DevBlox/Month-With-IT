package com.tieto.it2014.dao.user.command;

import com.tieto.it2014.dao.user.UserEntityRepository;
import com.tieto.it2014.domain.user.command.DeleteUserCommand;
import com.tieto.it2014.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteCommandDaoMem implements DeleteUserCommand.Dao {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserEntityRepository repository;

    @Override
    @Transactional
    public void execute(User user) {
//        repository.delete(user.id);
        
    }

}
