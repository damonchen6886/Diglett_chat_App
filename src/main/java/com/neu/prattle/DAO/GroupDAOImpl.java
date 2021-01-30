package com.neu.prattle.DAO;

import com.neu.prattle.model.Group;
import com.neu.prattle.model.GroupMsg;
import com.neu.prattle.model.Groupchat;
import com.neu.prattle.model.IndividualChat;
import com.neu.prattle.model.IndividualMsg;
import com.neu.prattle.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GroupDAOImpl implements GroupDAO {
    @Override
    public void addGroup(Group group, int moderatorId) {
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Group.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(IndividualChat.class)
                .addAnnotatedClass(IndividualMsg.class)
                .addAnnotatedClass(Groupchat.class)
                .addAnnotatedClass(GroupMsg.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        //save group object

        try {

            //start a transaction
            session.beginTransaction();

            //get the moderator object
            User user = session.get(User.class, moderatorId);

            //add the user to the group
            group.addUserToGroup(user);


            //save the group object
            session.save(group);
            //commit transaction
            session.getTransaction().commit();
            session.close();
        }
        finally {
            {
                factory.close();
            }
        }
    }


    @Override
    public Group getGroup(int groupId) {
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Group.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(IndividualChat.class)
                .addAnnotatedClass(IndividualMsg.class)
                .addAnnotatedClass(Groupchat.class)
                .addAnnotatedClass(GroupMsg.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        //start a transaction
        session.beginTransaction();

        //query
        Group tempgroup = session.get(Group.class, groupId);
        //commit transaction
        session.getTransaction().commit();


        factory.close();

        return tempgroup;
    }
    


}
