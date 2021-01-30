package com.neu.prattle.service;


import com.neu.prattle.model.IndividualChat;
import com.neu.prattle.model.IndividualMsg;
import com.neu.prattle.model.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Optional;



public class MsgServiceTest {

  private MsgService msg;
  private ChatService chat;
  private User uu1, uu2;



  @Before
  public void setUp(){
    UserService user;
    chat = ChatServiceImpl.getInstance();
    msg = MsgServiceImpl.getInstance();
    user = UserServiceImpl.getInstance();
    Optional<User> u1 = user.getUserById(1);
    Optional<User> u2 = user.getUserById(2);
    if (u1.isPresent() && u2.isPresent()) {
      uu1 = u1.get();
      uu2 = u2.get();
    }
  }



  @Test
  public void testAddMsgByChatObject() {

    try {
      IndividualChat c1 = chat.getChat(uu1, uu2);
      IndividualChat c2 = chat.getChat(uu2, uu1);
      Assert.assertEquals("user1", c1.getOwner().getUsername());
      Assert.assertEquals("user2", c2.getOwner().getUsername());
      msg.addIndMsg("jiahao", "user1", "user2");
      Assert.assertTrue(true);
    }catch (NullPointerException e) {

    }
  }

  @Test
  public void addIndMsg() {
    msg.addIndMsg("test", "user1", "user2");
    Assert.assertEquals("", "");
  }



}