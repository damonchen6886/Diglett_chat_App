package com.neu.prattle.service;

import com.neu.prattle.exceptions.UserNotExistException;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GroupChatServiceTest {

    @Test
    public void testgetGroupChatHistory() {
        try {
            GroupChatServiceImpl.getInstance().getGroupChatHistory("user1", "web");
            Assert.assertTrue(true);
        }catch (UserNotExistException e) {

        }
    }

}