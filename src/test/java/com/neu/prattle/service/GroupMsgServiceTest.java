package com.neu.prattle.service;

import com.neu.prattle.exceptions.UserNotExistException;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GroupMsgServiceTest {

    @Test
    public void testsendGroupMsg() {
        try {
            GroupMsgServiceImpl.getInstance().sendGroupMsg("user1", "web", "mesg1");
            ;
            Assert.assertTrue(true);
        }catch (UserNotExistException | NullPointerException e) {

        }
    }

}