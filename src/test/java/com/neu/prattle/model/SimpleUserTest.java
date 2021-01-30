package com.neu.prattle.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleUserTest {
  SimpleUser s1 = new SimpleUser();
  SimpleUser s2 = new SimpleUser();
  @Test
  public void getUsername() {
    s1.setUsername("chengu");
    s2.setUsername("+");
    s1.setPassword("chenguzhuanshu");
    s2.setPassword("+zhuanshu");
    assertEquals("chengu",s1.getUsername());
    assertEquals("+",s2.getUsername());
    assertEquals("chenguzhuanshu",s1.getPassword());
    assertEquals("+zhuanshu",s2.getPassword());
  }


}