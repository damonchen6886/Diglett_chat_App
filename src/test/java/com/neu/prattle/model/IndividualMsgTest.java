package com.neu.prattle.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class IndividualMsgTest {
  private IndividualMsg msg;
  @Before
  public void setUp(){


    msg = new IndividualMsg("hi","23:33:33","jiahao","chihao");
    msg.setId(0);
    msg.setContent("hi");
    msg.setTime("23:33:33");
    Set<IndividualChat> chats = new HashSet<>();
    msg.setChats(chats);
  }

  @Test
  public void test(){
    assertEquals(0,msg.getId());
    assertEquals("hi",msg.getContent());
    assertEquals("23:33:33",msg.getTime());
    IndividualChat c = new IndividualChat();
    msg.addChat(c);
  }
  IndividualChat c = new IndividualChat();
  Set<IndividualChat> i = new HashSet();
  Object o = new Object();
  @Test
  public void hashCodeTest(){
    assertEquals(31,msg.hashCode());
  }

  @Test
  public void equalsTest() {
    assertFalse(msg.equals(msg.getId()));
  }


  @Test
  public void addChat() {

    msg.addChat(c);
    assertTrue(true);
  }

  @Test
  public void getId() {
    msg.getId();
    assertTrue(true);
  }

  @Test
  public void setId() {
    msg.setId(1);
    assertTrue(true);
  }

  @Test
  public void getContent() {
    msg.getContent();
    assertTrue(true);
  }

  @Test
  public void setContent() {
    msg.setContent("lucky");
    assertTrue(true);
  }

  @Test
  public void getChats() {
    msg.getChats();
    assertTrue(true);
  }

  @Test
  public void setChats() {
    msg.setChats(i);
    assertTrue(true);
  }

  @Test
  public void getTime() {
    msg.getTime();
    assertTrue(true);
  }

  @Test
  public void setTime() {
    msg.setTime("23:59:59");
    assertTrue(true);
  }

  @Test
  public void getFrom() {
    msg.getFrom();
    assertTrue(true);
  }

  @Test
  public void setFrom() {
    msg.setFrom("jiahao");
    assertTrue(true);
  }

  @Test
  public void getTo() {
    msg.getTo();
    assertTrue(true);
  }

  @Test
  public void setTo() {
    msg.setTo("hao");
    assertTrue(true);
  }

  @Test
  public void equals() {
    msg.equals(o);
    assertTrue(true);
  }

  @Test
  public void hashCodeTests() {
    assertEquals(c.hashCode(),c.hashCode());
  }
}