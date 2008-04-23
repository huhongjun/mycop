package com.gever.sysman.level.model.impl;

import com.gever.sysman.level.model.Level;

public class DefaultLevel
    implements Level {

  private long Id;
  private String code;
  private String name;
  private String description;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public long getId() {
    return Id;
  }

  public void setId(long Id) {
    this.Id = Id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean equals(Level level) {
    boolean result = false;
    if (this.Id == level.getId()) {
      result = true;
    }
    return result;
  }

}
