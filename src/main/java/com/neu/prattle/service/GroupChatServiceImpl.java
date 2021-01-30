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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;



public class GroupChatServiceImpl implements GroupChatService {

    private UserService userService = UserServiceImpl.getInstance();
    private GroupService groupService = GroupServiceImpl.getInstance();
    private static GroupChatService groupChatService;
    private  GroupChatServiceImpl() { }

    static {
       groupChatService = new GroupChatServiceImpl();
    }
    public static GroupChatService getInstance() {
        return groupChatService;
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
     * get chat history according to username and group name
     *
     * @return chat history according to username and group name
     */
    @Override
    public List<String> getGroupChatHistory(String username, String groupName) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        try {
            User user = userService.getUserByUsername(username);
            Group group = groupService.getGroupByGroupname(groupName);
            int groupId = group.getGroupId();

            //get all groupchats of the user
            Set<Groupchat> groupchats = user.getGroupchats();

            //get the groupchat whose groupId is the same as given groupId
            for (Groupchat groupchat : groupchats) {
                if (groupchat.getGroup().getGroupId() == groupId) {
                    session.close();

                    return groupchat.getMsgsString();
                }
            }

            session.close();
            return new ArrayList<>();
        }

        finally {
            session.close();
        }
    }

}