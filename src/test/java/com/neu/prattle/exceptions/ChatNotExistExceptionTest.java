package com.neu.prattle.exceptions;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChatNotExistExceptionTest {

  @Test
  public void test1() {
    ChatNotExistException e = new ChatNotExistException("not exist");
    Assert.assertEquals("not exist", e.getMessage());
  }

  @Test
  public void test2() {
    GroupNotExistException e = new GroupNotExistException(".");
    Assert.assertEquals(".", e.getMessage());
  }

  @Test
  public void test3() {
    UserAlreadyInGroupException e = new UserAlreadyInGroupException("..");
    Assert.assertEquals("..", e.getMessage());
  }
}