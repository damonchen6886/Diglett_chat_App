package com.neu.prattle.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleChatTest {
  SimpleChat s = new SimpleChat("jiahao","tao");

  @Test
  public void getOwner() {
    assertEquals(s.getOwner(),"jiahao");
  }

  @Test
  public void setOwner() {
    s.setOwner("Tim");
    assertEquals(s.getOwner(),"Tim");
  }

  @Test
  public void getPeer() {
    assertEquals(s.getPeer(),"tao");
  }

  @Test
  public void setPeer() {
    s.setPeer("random");
    assertEquals(s.getPeer(),"random");
  }
}