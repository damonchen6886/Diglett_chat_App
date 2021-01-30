package com.neu.prattle.model;

import org.junit.Assert;
import org.junit.Test;


import java.util.Set;

import static org.junit.Assert.*;

public class UserTest {

  User u = new User("jiahao", "123","genius","1995-12-25");
  User s = new User("hapi", "123","genius","1995-12-25");
  Set<IndividualChat> indChatsAsSender;

  IndividualChat c;

  @Test
  public void hashCodeTest() {
    assertEquals(-1160314765,u.hashCode());

  }

  @Test
  public void getId() {
    u.setId(0);
    assertEquals(0,u.getId());
  }

  @Test
  public void setId() {
    u.setId(5);
    assertEquals(5,u.getId());
  }

  @Test
  public void getUsername() {
    assertEquals("jiahao",u.getUsername());

  }

  @Test
  public void setUsername() {
    u.setUsername("chihao");
    assertEquals("chihao",u.getUsername());
  }

  @Test
  public void getPassword() {
    assertEquals("123",u.getPassword());
  }

  @Test
  public void setPassword() {
    u.setPassword("456");
    assertEquals("456",u.getPassword());
  }

  @Test
  public void getNickname() {
    assertEquals("genius",u.getNickname());
  }

  @Test
  public void setNickname() {
    u.setNickname("stupid");
    assertEquals("stupid",u.getNickname());
  }

  @Test
  public void getDob() {
    assertEquals("1995-12-25",u.getDob());
  }

  @Test
  public void setDob() {
    u.setDob("1995-12-26");
    assertEquals("1995-12-26",u.getDob());
  }

  @Test
  public void getStatus() {
    u.setStatus("offline");
    assertEquals("offline",u.getStatus());
  }

  @Test
  public void setStatus() {
    u.setStatus("online");
    assertEquals("online",u.getStatus());
  }

  @Test
  public void getIndChats() {
    u.setIndChats(indChatsAsSender);
    assertEquals(indChatsAsSender,u.getIndChats());
  }

  @Test
  public void setIndChats() {
    u.setIndChats(indChatsAsSender);
    assertEquals(indChatsAsSender,u.getIndChats());
  }

  @Test
  public void addIndChat() {
    u.addIndChat(c);
    assertEquals(false,u.getIndChats().add(c));
  }

  @Test
  public void equals() {
    assertFalse(u.getUsername().equals(s.getUsername()));
  }
}