package com.neu.prattle.service;


import com.neu.prattle.model.User;


import java.util.Optional;

/***
 * Acts as an interface between the data layer and the
 * servlet controller.
 *
 * The controller is responsible for interfacing with this instance
 * to perform all the CRUD operations on user accounts.
 *
 * @author CS5500 Fall 2019 Teaching staff
 * @version dated 2019-10-06
 *
 */
public interface UserService {
    /***
     * Returns an optional object which might be empty or wraps an object
     * if the System contains a {@link User} object having the same name
     * as the parameter.
     *
     * @param name The username of the user
     * @return Optional object.
     */
    Optional<User> findUserByName(String name);

    /***
     * Tries to add a user in the system
     * @param user User object
     *
     */
    void addUser(User user);


  /**
   * Find and return user by user id
   * @param id user id
   * @return User object
   */
  Optional<User> getUserById(int id);





    /**
     * get user by user name
     * @param username
     * @return get user by user name
     */
    User getUserByUsername(String username);

//  /**
//   * Update user
//   * @param user new user
//   */
//  void updateUser(User user);
}
