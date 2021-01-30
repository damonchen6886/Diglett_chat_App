package com.neu.prattle.service;

public interface GroupMsgService {


    /**
     * A user sends a message to a group
     * @param username the username of a user
     * @param groupName the name of a group
     * @param content the content of the message
     */
    void sendGroupMsg(String username, String groupName, String content);
}
