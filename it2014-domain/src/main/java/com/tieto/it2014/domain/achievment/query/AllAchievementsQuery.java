package com.tieto.it2014.domain.achievment.query;

import com.tieto.it2014.domain.achievment.entity.Achievement;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AllAchievementsQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    public interface Dao extends Serializable {

        List<Achievement> result();
    }

    public List<Achievement> result() {
        return dao.result();
    }

}
