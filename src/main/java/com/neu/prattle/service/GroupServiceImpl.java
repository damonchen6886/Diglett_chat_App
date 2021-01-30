package com.neu.prattle.service;

import com.neu.prattle.DAO.GroupDAO;
import com.neu.prattle.DAO.GroupDAOImpl;
import com.neu.prattle.exceptions.GroupNotExistException;
import com.neu.prattle.exceptions.UserAlreadyInGroupException;
import com.neu.prattle.exceptions.UserNotExistException;
import com.neu.prattle.model.Group;
import com.neu.prattle.model.GroupMsg;
import com.neu.prattle.model.Groupchat;
import com.neu.prattle.model.IndividualChat;
import com.neu.prattle.model.IndividualMsg;
import com.neu.prattle.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.TypedQuery;

public class GroupServiceImpl implements GroupService{

  private GroupDAO groupDAO = new GroupDAOImpl();
  private UserService userService = UserServiceImpl.getInstance();
  private static GroupService groupService;
  private GroupServiceImpl() { }
  static {
    groupService = new GroupServiceImpl();
  }

  public static GroupService getInstance() {
    return groupService;
  }

  private SessionFactory factory  = new Configuration()
          .configure("hibernate.cfg.xml")
          .addAnnotatedClass(User.class)
          .addAnnotatedClass(IndividualChat.class)
          .addAnnotatedClass(IndividualMsg.class)
          .addAnnotatedClass(Group.class)
          .addAnnotatedClass(Groupchat.class)
          .addAnnotatedClass(GroupMsg.class)
          .buildSessionFactory();


  @Override
  public void createGroupByName(String groupName, String username) {
    try (Session session = factory.getCurrentSession()) {

      session.beginTransaction();

      User user = userService.getUserByUsername(username);
      int userId = user.getId();
      Group group = new Group(userId);
      group.setGroupName(groupName);

      groupService.createGroup(group, userId);

      //add a groupchat for the user
      Groupchat groupchat = new Groupchat(group, user);
      session.save(groupchat);

      session.getTransaction().commit();
      session.close();
    }
  }

  @Override
  public void addUserToGroupByName(String groupName, String username) {
    Session session = factory.getCurrentSession();
      session.beginTransaction();

      try  {
      Group group = new GroupServiceImpl().getGroupByGroupname(groupName);
      Optional<User> ouser = userService.findUserByName(username);
      if(!ouser.isPresent()) {
        return;
      }
      User user = ouser.get();
      group.addUserToGroup(user);
      session.update(group);

      //add a new groupchat to the user
      Groupchat groupchat = new Groupchat(group, user);
      session.save(groupchat);

      session.getTransaction().commit();
      session.close();
    } catch (NullPointerException e) {
      session.getTransaction().rollback();
      session.close();
      throw new GroupNotExistException("group " + groupName + " does not exist");
    } catch (ConstraintViolationException e) {
      session.getTransaction().rollback();
      session.close();
      throw new UserAlreadyInGroupException("You already added in group: " + groupName);
    }

  }

      /**
     * get the id of a group according to groupname
     *
     * @param groupName the name of a group
     * @return get the id of a group according to groupname
     */
    @Override
    public Group getGroupByGroupname(String groupName) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        String strQuery ="from Group g where g.groupName='" + groupName + "'";
        TypedQuery<Group> query = session.createQuery(strQuery, Group.class);
        List<Group> groups = query.getResultList();

        if (groups.isEmpty()) {
            session.close();
            throw new NullPointerException();
        }

        Group tempGroup = groups.get(0);

        session.close();
        return tempGroup;

    }
    
      /**
     * create a group and add the group to the database
     *
     * @param group new group object
     */
    @Override
    public void createGroup(Group group, int moderatorID) {
        groupDAO.addGroup(group, moderatorID);
    }

    @Override
    public List<String> getGroupNamesByUsername(String  username) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
try {
    User tempUser = userService.getUserByUsername(username);

    List<String> nameList = new ArrayList<>();

    Set<Group> groups = tempUser.getGroups();
    for (Group group : groups) {
        nameList.add(group.getGroupName());
    }
    session.getTransaction().commit();

    session.close();
    return nameList;

}finally {
    session.close();
}
    }



      /**
     * get list of usernames by the group name
     *
     * @param groupName the name of the group
     * @return list of usernames by the group name
     */
    @Override
    public List<String> getListOfUsernamesByGroupName(String groupName) {
        Group group = this.getGroupByGroupname(groupName);
        List<String> usernames = new ArrayList<>();
        List<User> users = group.getUsers();
        for (User user : users) {
            usernames.add(user.getUsername());
        }
        return usernames;
    }
}
