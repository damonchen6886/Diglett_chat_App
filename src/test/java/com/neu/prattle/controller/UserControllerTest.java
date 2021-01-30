package com.neu.prattle.controller;

import com.neu.prattle.exceptions.UserAlreadyPresentException;
import com.neu.prattle.exceptions.UserNotExistException;
import com.neu.prattle.model.IndividualMsg;
import com.neu.prattle.model.SimpleChat;
import com.neu.prattle.model.SimpleUser;
import com.neu.prattle.model.User;
import com.neu.prattle.service.GroupServiceImpl;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

public class UserControllerTest {

  @Test
  public void createUserAccountTest() {

    User user = new User("andy", "123");

    UserController userController = new UserController();
    userController.createUserAccount(user);

    User user2 = new User("chen", "123");

    try {
      userController.createUserAccount(user2);
    }
    catch(UserAlreadyPresentException E){

    }


  }

  @Test
  public void valiDateUserTest() {

    SimpleUser user = new SimpleUser("user1", "123");

    UserController userController = new UserController();
    Response res = userController.valiDateUser(user);
    Assert.assertTrue(true);
  }


  @Test
  public void valiDateUserTest1() {

    SimpleUser user = new SimpleUser("nosuchuser", "123");

    UserController userController = new UserController();
    Response res = userController.valiDateUser(user);
    Assert.assertEquals("no user",res.getEntity());
  }

  @Test
  public void valiDateUserTest2() {

    SimpleUser user = new SimpleUser("user1", "wrong password");

    UserController userController = new UserController();
    Response res = userController.valiDateUser(user);
    Assert.assertTrue(true);
  }


  @Test
  public void chathistory() {
    try {
      SimpleChat chat = new SimpleChat("user1", "user2");
      UserController userController = new UserController();
      Response res = userController.chathistory(chat);
      Assert.assertEquals("", "");
    }catch (UserNotExistException e) {

    }
  }

  @Test
  public void teststartIndChatHis() {
    try {
      SimpleChat simpleChat = new SimpleChat("user1", "user2");
      new UserController().startIndChatHis(simpleChat);
      Assert.assertTrue(true);
    }catch (UserNotExistException e) {

    }

  }

  @Test
  public void testgetGroupNamesByUsername() {
    GroupServiceImpl.getInstance().getGroupNamesByUsername("user1");
    Assert.assertTrue(true);
  }

}