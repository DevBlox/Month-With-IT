package com.tieto.it2014.domain.weight;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class Entity implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    private int id;

    public Entity(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
