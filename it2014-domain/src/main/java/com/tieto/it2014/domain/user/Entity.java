package com.tieto.it2014.domain.user;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class Entity implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  public Long id;

  public Entity(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
