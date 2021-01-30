package com.neu.prattle.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GroupchatTest {
    Group a = new Group(1);
    User b = new User("eba","123456");

    Groupchat groupchat;
    @Before
    public void setUp() throws Exception {
        groupchat = new Groupchat(a, b);
    }

    @Test
    public void getGroupChatID() {
        groupchat.getGroupChatID();
        assertTrue(true);
    }

    @Test
    public void setGroupChatID() {
        groupchat.setGroupChatID(1);
        assertTrue(true);
    }

    @Test
    public void getGroupID() {
        groupchat.getGroupChatID();
        assertTrue(true);
    }

    @Test
    public void setGroupID() {
        groupchat.setGroupChatID(100);
        assertTrue(true);
    }

    @Test
    public void getOwnerUser() {
        groupchat.getGroupOwner();
        assertTrue(true);
    }

    @Test
    public void setOwnerUserID() {
        groupchat.setGroupOwner(b);
        assertTrue(true);
    }

    @Test
    public void testToString() {
        groupchat.toString();
        assertTrue(true);
    }
}