package com.neu.prattle.service;

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


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;



public class MsgServiceImpl implements MsgService {

  private MsgServiceImpl() {

  }

  private static MsgService msgService;
  private UserService accountService = UserServiceImpl.getInstance();
  private ChatService chatService = ChatServiceImpl.getInstance();


  private SessionFactory factory = new Configuration()
          .configure("hibernate.cfg.xml")
          .addAnnotatedClass(User.class)
          .addAnnotatedClass(IndividualChat.class)
          .addAnnotatedClass(IndividualChatId.class)
          .addAnnotatedClass(IndividualMsg.class)
          .addAnnotatedClass(Group.class)
          .addAnnotatedClass(Groupchat.class)
          .addAnnotatedClass(GroupMsg.class)
          .buildSessionFactory();

  static {
    msgService = new MsgServiceImpl();

  }

  /**
   * Call this method to return an instance of this service.
   *
   * @return this
   */
  public static MsgService getInstance() {
    return msgService;
  }


  @Override
  public void addIndMsg(String m, String from, String to) {
    Optional<User> fromUser = accountService.findUserByName(from);
    Optional<User> toUser = accountService.findUserByName(to);
    if (fromUser.isPresent() && toUser.isPresent()) {
      Optional<IndividualChat> c1 = Optional.ofNullable(chatService.getChat(fromUser.get(), toUser.get()));
      Optional<IndividualChat> c2 = Optional.ofNullable(chatService.getChat(toUser.get(), fromUser.get()));
      if (c1.isPresent() && c2.isPresent()) {
        try (Session session = factory.getCurrentSession()) {
          session.beginTransaction();
          String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
          IndividualMsg msg = new IndividualMsg(m, time, from, to);
          msg.addChat(c1.get());
          msg.addChat(c2.get());
          c1.get().addMsg(msg);
          c2.get().addMsg(msg);
          session.save(msg);
          session.update(c1.get());
          session.update(c2.get());
          session.getTransaction().commit();
        }
      }
    }
  }



}

