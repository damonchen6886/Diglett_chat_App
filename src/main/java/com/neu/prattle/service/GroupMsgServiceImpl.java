package com.neu.prattle.service;

import com.neu.prattle.model.Group;
import com.neu.prattle.model.GroupMsg;
import com.neu.prattle.model.Groupchat;
import com.neu.prattle.model.IndividualChat;
import com.neu.prattle.model.IndividualMsg;
import com.neu.prattle.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Set;

public class GroupMsgServiceImpl implements GroupMsgService {

    private GroupService groupService = GroupServiceImpl.getInstance();

    private static GroupMsgService groupMsgService;
    private  GroupMsgServiceImpl() { }

    static {
        groupMsgService = new GroupMsgServiceImpl();
    }
    public static GroupMsgService getInstance() {
        return groupMsgService;
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


    /**
     * A user sends a message to a group
     *
     * @param username  the username of a user
     * @param groupName the name of a group
     * @param content   the content of the message
     */
    @Override
    public void sendGroupMsg(String username, String groupName, String content) {

        Session session = factory.getCurrentSession();

        session.beginTransaction();

        //get the id of the user
//        int userId = userService.getUserByUsername(username).getId();

        //create a new GroupMsg
        GroupMsg groupMsg = new GroupMsg(content, username);


        //get the group
        Group group = groupService.getGroupByGroupname(groupName);

        //get the groupchats from the group
        Set<Groupchat> groupchats = group.getGroupchats();

        //add groupchats to the new message
        groupMsg.setGroupchats(groupchats);

        //save the message to DB
        session.save(groupMsg);

        session.getTransaction().commit();


    }

    public static void main(String[] args) {

    }
}
