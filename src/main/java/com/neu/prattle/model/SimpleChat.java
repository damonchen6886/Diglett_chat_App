package com.neu.prattle.model;

public class SimpleChat {

  private String owner;
  private String peer;
  public SimpleChat(String owner, String peer) {
    this.owner = owner;
    this.peer = peer;
  }

  public SimpleChat() {};

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getPeer() {
    return peer;
  }

  public void setPeer(String peer) {
    this.peer = peer;
  }
}
