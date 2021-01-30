package com.neu.prattle.model;

public class SimpleUser {

  private String username;
  private String password;

  public SimpleUser(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public SimpleUser(){};

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
