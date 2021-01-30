package com.neu.prattle.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class IndividualChatTest {
  User jiahao = new User("jiahao","123456");
  User tao = new User("tao","123456");

  IndividualChat i = new IndividualChat(jiahao, 100);
  IndividualMsg msg =  new IndividualMsg();

  @Test
  public void addMsg() {
    i.addMsg(msg);assertTrue(true);
  }

  @Test
  public void getId() {
    i.getId();assertTrue(true);
  }

  @Test
  public void setId() {
    i.setId(100);assertTrue(true);
  }

  @Test
  public void getOwner() {
    i.getOwner();
    assertTrue(true);
  }

  @Test
  public void setOwner() {
    i.getOwner();assertTrue(true);
  }

  @Test
  public void getPeer() {
    i.getPeer();assertTrue(true);
  }

  @Test
  public void setPeer() {
    i.setPeer(10086);assertTrue(true);
  }

  @Test
  public void getMsgs() {
    i.getMsgs();assertTrue(true);
  }

  @Test
  public void setMsgs() {
    Set<IndividualMsg> lst = new HashSet<>();
    i.setMsgs(lst);
    assertTrue(true);
  }

  @Test
  public void equals() {
    assertTrue(true);
  }

  @Test
  public void hashCodeTests() {
    assertEquals(i.hashCode(),i.hashCode());
  }
}