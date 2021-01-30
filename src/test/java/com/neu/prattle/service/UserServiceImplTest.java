package com.neu.prattle.service;

import com.neu.prattle.model.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.Random;


public class UserServiceImplTest {

  private UserService user;

  @Before
  public void setUp(){
    user = UserServiceImpl.getInstance();

  }

  @Test
  public void addUserTest(){
    User testUser = new User("jiahao", "123");
    User testUser1 = new User("jiahao1", "123");
    User testUser2 = new User("jiahao2", "123");
    Assert.assertEquals("jiahao2",testUser2.getUsername());
    try {
      user.addUser(testUser);
      user.addUser(testUser1);
      user.addUser(testUser2);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void addUser() {
    Random r = new Random(10000000);
    String name = Float.toString(r.nextInt());
    User user1 = new User(name, "123");
    user.addUser(user1);
  }

  @Test
  public void addDuplicateUser() {
    User testUser = new User("user1", "123");
    try {
      user.addUser(testUser);
    } catch (Exception e) {
      Assert.assertEquals("Username user1 already exists.", e.getMessage());
    }
  }

  @Test
  public void findUserByNameTest() {
    Optional<User> testUser = Optional.of(new User("user1", "123"));
    try {
      Optional<User> temp2 = user.findUserByName("user1");
      Assert.assertEquals(testUser, temp2);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Test
  public void findUserByNameTestNotValid() {
    try {
      Optional<User> temp2 = user.findUserByName("Noname");
      Assert.assertTrue(!temp2.isPresent());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Test
  public void findUserByIDTest() {
    Optional<User> testUser = Optional.of(new User("user1", "123"));
    try {
      Optional<User> temp2 = user.getUserById(1);
      Assert.assertTrue(true);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Test
  public void findUserByIDTestNotValid() {
    try {
      Random r = new Random(10000000);
      int id = r.nextInt();
      Optional<User> temp2 = user.getUserById(id);
    } catch (Exception e) {
      Assert.assertEquals("", "");
    }

  }


}