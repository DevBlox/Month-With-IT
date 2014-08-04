package com.tieto.it2014.dao.user.command;

import com.tieto.it2014.dao.DatabaseInMemory;
import com.tieto.it2014.domain.user.command.DeleteUserCommand;
import com.tieto.it2014.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DeleteCommandDaoMem implements DeleteUserCommand.Dao {

    private static final long serialVersionUID = 1L;

    @Override
    public void execute(User user) {
        synchronized (DatabaseInMemory.users) {
            for (int i = 0; i < DatabaseInMemory.users.size(); i++) {
                if (Objects.equals(DatabaseInMemory.users.get(i).id, user.id)) {
                    DatabaseInMemory.users.remove(i);
                    break;
                }
            }
        }
    }

}
