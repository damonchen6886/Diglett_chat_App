package com.neu.prattle.service;


import java.util.List;

public interface GroupChatService {
    /**
     * get chat history according to username and group name
     * @param username username
     * @param groupName grooupname
     * @return chat history according to username and group name
     */
    List<String> getGroupChatHistory(String username, String groupName);
}
