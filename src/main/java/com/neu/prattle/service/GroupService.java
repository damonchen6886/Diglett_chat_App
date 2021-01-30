package com.neu.prattle.service;

import com.neu.prattle.model.Group;


import java.util.List;

public interface GroupService {

    /**
     * create a group and add the group to the database
     * @param group new group object
     */
    void createGroup(Group group, int moderatorID);
//
    /**
     * create a group by username and group name
     * @param groupName groupname
     * @param username username
     */
    void createGroupByName(String groupName, String username);
//
//    /**
//     *
//     * @param groupId
//     * @param userId
//     */
//    void addUserToGroupById(int groupId, int userId);
//
    /**
     * add a user to a group according to a group name and a username
     * @param groupName the name of a group
     * @param username user name
     */
    void addUserToGroupByName(String groupName, String username);
//
//
//    List<User> getUsersByGroupid(int groupId);
//
//
    /**
     * get names of groups that a user joined
     * @param username the username of a uer
     * @return get names of groups that a user joined
     */
    List<String> getGroupNamesByUsername(String  username);
//
//
//    /**
//     * get names of groups that a user joined
//     * @param userId the user_id of a user
//     * @return  names of groups that a user joined
//     */
//    List<String> getGroupNamesByUserid(int userId);

    /**
     * get the gruop according to groupname
     * @param groupName the name of a group
     * @return get the group according to groupname
     */
    Group getGroupByGroupname(String groupName);

    /**
     * get list of usernames by the group name
     * @param groupName the name of the group
     * @return list of usernames by the group name
     */
    List<String> getListOfUsernamesByGroupName(String groupName);


}
