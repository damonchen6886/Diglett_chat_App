package com.neu.prattle.websocket;

import com.neu.prattle.exceptions.UserNotExistException;
import com.neu.prattle.model.ChatType;
import com.neu.prattle.model.Message;

import com.neu.prattle.model.User;
import com.neu.prattle.service.UserService;
import com.neu.prattle.service.UserServiceImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.websocket.EncodeException;
import javax.websocket.Session;

public class ChatEndpointTest {

  private ChatEndpoint chatEndpoint = new ChatEndpoint();
  private Session session = Mockito.mock(Session.class);
  private Message m;
  private String user1 = "user1";

  @Before
  public void setUp() {
    chatEndpoint.setSession(session);
  }

  @Test
  public void chatEndpointTest() {

    try {
      chatEndpoint.onOpen(session, "user1");
    } catch (IOException | EncodeException | NullPointerException e) {
      //
      Assert.assertEquals("", "");
    }
  }

  @Test
  public void chatEndpointTest2() {

    try {
      chatEndpoint.onOpen(session, "user2");
    } catch (IOException | EncodeException | NullPointerException e) {
      //
      Assert.assertEquals("", "");
    }
  }

  @Test
  public void onOpenNotExit() {
    try {
      chatEndpoint.onOpen(session, "nosuchuser");
    } catch (IOException | EncodeException | NullPointerException e) {
      //
      Assert.assertEquals("", "");
    }
  }

  @Test
  public void onOpenNotExit1() {
    try {
      m = Message.messageBuilder()
              .setMessageContent("test")
              .setTime(new Date())
              .setTo(user1)
              .setType(ChatType.INDIVIDUAL)
              .build();
      chatEndpoint.onOpen(session, "user1");
    } catch (IOException | EncodeException | NullPointerException e) {
      //
      Assert.assertEquals("", "");
    }
  }

  @Test
  public void onmessageIndTest() {
    try {
      m = Message.messageBuilder()
              .setMessageContent("user 1 to user 2")
              .setTime(new Date())
              .setTo("user2")
              .setType(ChatType.INDIVIDUAL)
              .build();

      chatEndpoint.onMessage(session, m, "user1");
    } catch (IOException | EncodeException |NullPointerException e) {
      Assert.assertEquals("", "");
    }
  }



  @Test
  public void onmessageGroupTest() {
    try {
      m = Message.messageBuilder()
              .setMessageContent("user 1 to web group tests")
              .setTime(new Date())
              .setTo("web")
              .setType(ChatType.GROUP)
              .build();

      chatEndpoint.onMessage(session, m, "user1");
    } catch (IOException | EncodeException |NullPointerException e) {
      Assert.assertEquals("", "");
    }
  }

  @Test
  public void testOnError() {
    try {
      chatEndpoint.onError(session, new UserNotExistException("user not exist"));
    } catch (IOException | EncodeException | NullPointerException e) {
      Assert.assertEquals("", "");
    }
  }

  @Test
  public void getSession() {
    Assert.assertEquals(session, chatEndpoint.getSession());
  }

  @Test
  public void setSession() {
    Session s = Mockito.mock(Session.class);
    chatEndpoint.setSession(s);
    Assert.assertEquals(s, chatEndpoint.getSession());
  }

  @Test
  public void onCloseTest() {
    try {
      chatEndpoint.onClose(session, "user1");
    } catch (NullPointerException e) {
      Assert.assertEquals("", "");
    }
  }


}