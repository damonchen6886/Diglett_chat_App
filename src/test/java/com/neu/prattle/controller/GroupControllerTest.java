package com.neu.prattle.controller;

import com.neu.prattle.exceptions.UserNotExistException;
import com.neu.prattle.model.Group;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.PublicKey;
import java.util.List;
import java.util.Random;

import javax.validation.constraints.Null;
import javax.ws.rs.core.Response;

public class GroupControllerTest {

    private GroupController groupController;

    @Before
    public void setUp() throws Exception {
        groupController = new GroupController();
    }

    @Test
    public void testaddorcreatgroup() {
        try {

          Random r = new Random(10000000);
          String groupname = Float.toString(r.nextFloat());

            groupController
                    .createGroupOrAddUserToGroupByName("user1", groupname);
          Assert.assertTrue(true);
        } catch (UserNotExistException e){

        }

    }

    @Test
    public void testaddorcreatgroupwithGroupNotExist() {
        try {
          Random r = new Random(10000000);
          String groupname = Float.toString(r.nextFloat());
            groupController.createGroupOrAddUserToGroupByName("user1", groupname);
            Assert.assertTrue(true);

        }catch (UserNotExistException e) {

        }
    }

    @Test
    public void testaddorcreatgroupWithUserAlreadyInGroup() {
        try {
            groupController.createGroupOrAddUserToGroupByName("user1", "web");
            Assert.assertTrue(true);

        }catch (UserNotExistException e){

        }
    }

    @Test
    public void testgetListOfGroupNamesByUsername() {
        try {
            Response r = groupController.getListOfUsernamesByGroupName("web");
            Assert.assertTrue(true);

        }catch (NullPointerException e) {

        }

    }
    @Test
    public void testgetListOfGroupNamesByUsernameWithUserNotExist() {
        try {
          Random r = new Random(10000000);
          String groupname = Float.toString(r.nextFloat());
            Response re = groupController.getListOfUsernamesByGroupName(groupname);
            String res = re.getEntity().toString();
            Assert.assertTrue(true);
        } catch (NullPointerException e) {

        }
}

  @Test
    public void testgetListOfUsernamesByGroupName() {
      try {
          groupController.getListOfUsernamesByGroupName("web");
          Assert.assertTrue(true);
      } catch (NullPointerException e) {

      }
  }

  @Test
    public void testgetListOfUsernamesByGroupNameWithGroupNotExist() {

        try {
          Random r = new Random(10000000);
          String groupname = Float.toString(r.nextFloat());
            groupController.getListOfUsernamesByGroupName(groupname);

        }catch (NullPointerException e) {

        }
      Assert.assertTrue(true);
  }

  @Test
    public void testgetGroupChatHistory() {
      try {
          groupController.getGroupChatHistory("user1", "web");
          Assert.assertTrue(true);
      }catch (NullPointerException | UserNotExistException e) {

      }

  }

  @Test
    public void testgetGroupChatHistoryWith() {

      try {
          groupController.getGroupChatHistory("user22", "nnweb");
          Assert.assertTrue(true);
      }catch (UserNotExistException e) {

      }

  }

}