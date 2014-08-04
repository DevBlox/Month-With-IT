package com.tieto.it2014.dao.user.command;

import com.tieto.it2014.dao.user.UserEntityRepository;
import com.tieto.it2014.dao.user.UserEntity;
import com.tieto.it2014.domain.user.command.SaveUserCommand;
import com.tieto.it2014.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SaveUserCommandDaoMem implements SaveUserCommand.Dao {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserEntityRepository repository;

    @Override
    @Transactional
    public Long execute(User user) {
        return repository.merge(new UserEntity(user)).getId();
    }

}
