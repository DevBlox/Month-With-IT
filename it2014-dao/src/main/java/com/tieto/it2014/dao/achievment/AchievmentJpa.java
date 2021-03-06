package com.tieto.it2014.dao.achievment;

import com.tieto.it2014.dao.JpaEntity;
import com.tieto.it2014.domain.achievment.entity.Achievement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ACHIEVMENT")
public class AchievmentJpa implements JpaEntity<Achievement>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COMPLETE_MESSAGE")
    private String completeMessage;

    @Override
    public Achievement toDomain() {
        return new Achievement(this.name, this.description, this.completeMessage, this.id);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

}
