package com.neu.prattle.service;

import com.neu.prattle.exceptions.UserAlreadyPresentException;
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
import org.hibernate.query.Query;


import java.util.List;
import java.util.Optional;





/***
 * Implementation of {@link UserService}
 *
 * It stores the user accounts in-memory, which means any user accounts
 * created will be deleted once the application has been restarted.
 *
 */
public class UserServiceImpl implements UserService {

    /***
     * UserServiceImpl is a Singleton class.
     */
    private UserServiceImpl() {

    }

    private static UserService accountService;
    private SessionFactory factory  = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(IndividualChat.class)
                .addAnnotatedClass(IndividualMsg.class)
                .addAnnotatedClass(Group.class)
                .addAnnotatedClass(Groupchat.class)
                .addAnnotatedClass(GroupMsg.class)
                .buildSessionFactory();

    static {
        accountService = new UserServiceImpl();
    }

    /**
     * Call this method to return an instance of this service.
     * @return this
     */
    public static UserService getInstance() {
        return accountService;
    }




    /***
     *
     * @param name -> The name of the user.
     * @return An optional wrapper supplying the user.
     */
    @Override
    public Optional<User> findUserByName(String name) {

        Optional<User> u = Optional.empty();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            u = Optional.ofNullable(session.bySimpleNaturalId(User.class).load(name));
            session.getTransaction().commit();
            session.close();
        }
        return u;
    }

    @Override
    public synchronized void addUser(User user) {

        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            session.save(user);
            session.get(User.class, user.getId());
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            throw new UserAlreadyPresentException("Username " + user.getUsername() + " already exists.");
        }

    }

    @Override
    public Optional<User> getUserById(int id) {

        Optional<User> u;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            u = Optional.ofNullable(session.get(User.class, id));
            session.getTransaction().commit();
        }
        catch (NullPointerException e) {
            throw new UserNotExistException("User ID: "+ id + " not exists.");
        }
        return u;
    }

    /**
     * get user by user name
     *
     * @return get user by user name
     */
    @Override
    public User getUserByUsername(String username) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        try {
            String strQuery = "from User u where u.username='" + username + "'";
            Query query = session.createQuery(strQuery);
            List<User> users = query.getResultList();

            if (users.isEmpty()) {

                session.close();
                throw new UserNotExistException(username + " does not exxist");
            }

            User tempUser = users.get(0);

            session.close();

            return tempUser;
        }
        catch (NullPointerException e) {
            session.close();
            throw new UserNotExistException("user " + username + " does not exist");

        }
    }


}
