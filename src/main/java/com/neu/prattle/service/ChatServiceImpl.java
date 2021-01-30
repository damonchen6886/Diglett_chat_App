package com.neu.prattle.service;

import com.neu.prattle.exceptions.ChatAlreadyPresentException;
import com.neu.prattle.exceptions.ChatNotExistException;
import com.neu.prattle.exceptions.UserNotExistException;
import com.neu.prattle.model.Group;
import com.neu.prattle.model.GroupMsg;
import com.neu.prattle.model.Groupchat;
import com.neu.prattle.model.IndividualChat;

import com.neu.prattle.model.IndividualChatId;
import com.neu.prattle.model.IndividualMsg;
import com.neu.prattle.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public class ChatServiceImpl implements ChatService {

  private ChatServiceImpl() {

  }

  private static ChatService chatService;
  private UserService userService=UserServiceImpl.getInstance();
  private SessionFactory factory = new Configuration()
          .configure("hibernate.cfg.xml")
          .addAnnotatedClass(User.class)
          .addAnnotatedClass(IndividualChat.class)
          .addAnnotatedClass(IndividualChatId.class)
          .addAnnotatedClass(IndividualMsg.class)
          .addAnnotatedClass(Groupchat.class)
          .addAnnotatedClass(Group.class)
          .addAnnotatedClass(GroupMsg.class)
          .buildSessionFactory();

  static {
    chatService = new ChatServiceImpl();

  }

  /**
   * Call this method to return an instance of this service.
   * @return this
   */
  public static ChatService getInstance() {
    return chatService;
  }



  public void addChat(User from, int to) {
    try(Session session = factory.getCurrentSession()) {
      session.beginTransaction();
      IndividualChat c = new IndividualChat(from, to);
      from.addIndChat(c);
      session.save(c);
      session.getTransaction().commit();
    }
  }

  public void addPairChat(String user1, String user2) {
    try(Session session = factory.getCurrentSession()) {
      session.beginTransaction();
      Optional<User> uu1 = userService.findUserByName(user1);
      Optional<User> uu2 = userService.findUserByName(user2);
      if (!uu1.isPresent() || !uu2.isPresent()) {
        throw new UserNotExistException("user not exists");
      }
      User u1 = uu1.get();
      User u2 = uu2.get();
      IndividualChat c1 = new IndividualChat(u1,u2.getId());
      IndividualChat c2 = new IndividualChat(u2,u1.getId());
      u1.addIndChat(c1);
      u2.addIndChat(c2);
      session.save(c1);
      session.save(c2);
      session.getTransaction().commit();
    } catch (ConstraintViolationException e) {
      throw new ChatAlreadyPresentException("Chat already exists.");
    }
  }

  public IndividualChat getChat(User owner, User peer) {
    try (Session session = factory.getCurrentSession()) {
      session.beginTransaction();
      for (IndividualChat chat : owner.getIndChats()) {
        if (chat.getPeer() == peer.getId()) {
          return chat;
        }
      }
    }
    return null;
  }

  public Set<IndividualMsg> getMsgs (IndividualChat c) {
    Set<IndividualMsg> msgs = new HashSet<>();
    try (Session session = factory.getCurrentSession()) {
      session.beginTransaction();
      IndividualChat newC = session.get(IndividualChat.class, c.getId());
      msgs = newC.getMsgs();
      session.getTransaction().commit();
    }
    return msgs;
  }

  public Set<IndividualMsg> getMsgsByUsernames (String user1, String user2) {
    Optional<User> u1 = userService.findUserByName(user1);
    Optional<User> u2 = userService.findUserByName(user2);
    if (u1.isPresent() && u2.isPresent()) {
      Optional<IndividualChat> c = Optional.ofNullable(this.getChat(u1.get(), u2.get()));
      if (c.isPresent()) {
        return this.getMsgs(c.get());
      } else {
        throw new ChatNotExistException("chat not exists.");
      }
    }
    else {
      throw new UserNotExistException("User not exists");
    }
  }

}
