package com.tieto.it2014.domain.workout.query;

import com.tieto.it2014.domain.user.entity.UserLoc;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkoutsQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    public interface Dao extends Serializable {
        List<UserLoc> result();
    }

    public List<UserLoc> result() {
        return dao.result();
    }

}
