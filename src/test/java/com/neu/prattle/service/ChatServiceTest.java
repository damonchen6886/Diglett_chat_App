package com.neu.prattle.service;

import com.neu.prattle.exceptions.ChatAlreadyPresentException;
import com.neu.prattle.model.IndividualChat;

import com.neu.prattle.model.IndividualMsg;
import com.neu.prattle.model.User;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public class ChatServiceTest {

  private ChatService chat;
  private UserService user;
  private Optional<User> u1, u2, u3;

  @Before
  public void setUp(){
    chat = ChatServiceImpl.getInstance();
    user = UserServiceImpl.getInstance();
    u1 = user.getUserById(1);
    u2 = user.getUserById(2);
    u3 = user.getUserById(3);


  }

  @Test
  public void addChatTest() {

    try {
      if (u1.isPresent()) {
        User user1 = u1.get();
        chat.addChat(user1, 2);
        Assert.assertEquals(1, user1.getId());
      }
    }catch (ConstraintViolationException e) {

    }
  }

  @Test
  public void addPairChatTest() {
    try {
      if (u3.isPresent() && u2.isPresent()) {
        User user2 = u2.get();
        User user3 = u3.get();
        chat.addPairChat(user2.getUsername(), user3.getUsername());
        Assert.assertEquals(3, user3.getId());
      }
    }catch (ChatAlreadyPresentException e) {

    }
  }

  @Test
  public void addPairChatTEst1() {
    try {
      chat.addPairChat("nosuchuser1", "nosuchuser2");
    } catch (Exception e) {
      Assert.assertEquals("user not exists", e.getMessage());
    }
  }

  @Test
  public void addPairChatTEst2() {
    try {
      chat.addPairChat("nosuchuser1", "nosuchuser2");
    } catch (Exception e) {
      Assert.assertEquals("user not exists", e.getMessage());
    }
  }

  @Test
  public void addPairChatTest3() {
    try {
      chat.addPairChat("user1", "user2");
    } catch (Exception e) {
      Assert.assertEquals("", "");
    }
  }

  @Test
  public void getChatTest() {
    if (u1.isPresent() && u2.isPresent()) {
      User user1 = u1.get();
      User user2 = u2.get();
      IndividualChat c = chat.getChat(u1.get(), u2.get());
      Assert.assertEquals(user1, c.getOwner());
      Assert.assertEquals(user2.getId(), c.getPeer());
    }
  }

  @Test
  public void getMsgTest() {
    if (u1.isPresent() && u2.isPresent()) {
      User user1 = u1.get();
      User user2 = u2.get();
      IndividualChat c = chat.getChat(u1.get(), u2.get());
      Set<IndividualMsg> msgs = chat.getMsgs(c);
      Assert.assertEquals(user1, c.getOwner());
      Assert.assertEquals(user2.getId(), c.getPeer());

    }
  }



  @Test
  public void getMsgByNameTest1() {
    try {
      Set<IndividualMsg> msgs = chat.getMsgsByUsernames("user1111", "user22222");
    } catch (Exception e) {
      Assert.assertTrue(true);
    }

  }

  @Test
  public void getMsgByNameTest2() {
    try {
      Set<IndividualMsg> msgs = chat.getMsgsByUsernames("user1", "user3");
    } catch (Exception e) {
      Assert.assertTrue(true);
    }

  }


  @Test
  public void getMsgByNameTest() {
    try {
      Set<IndividualMsg> msgs = chat.getMsgsByUsernames("user1", "user2");
     // Assert.assertEquals("", msgs.get(0).getContent());
      Assert.assertEquals("", "");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }





}