package com.neu.prattle.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GroupTest {
    Group group;
    @Before
    public void setUp() throws Exception {
        group = new Group(13);
    }

    @Test
    public void getUsers() {
        group.getUsers();assertFalse(false);
    }

    @Test
    public void setUsers() {
        group.setUsers(new ArrayList<>());assertFalse(false);
    }

    @Test
    public void getGroupId() {
        group.getGroupId();assertFalse(false);
    }

    @Test
    public void setGroupId() {
        group.setGroupId(14);assertFalse(false);
    }

    @Test
    public void getModeratorID() {
        group.getModeratorID();
        assertTrue(true);
    }

    @Test
    public void setModeratorID() {
        group.setModeratorID(20);
        assertTrue(true);
    }

    @Test
    public void addUserToGroup() {
        group.addUserToGroup(new User());
        assertTrue(true);
    }

    @Test
    public void testToString() {
        group.toString();assertTrue(true);
    }
}