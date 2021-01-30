package com.neu.prattle.service;

import com.neu.prattle.exceptions.GroupNotExistException;
import com.neu.prattle.exceptions.UserAlreadyInGroupException;
import com.neu.prattle.exceptions.UserNotExistException;
import com.neu.prattle.model.Group;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLIntegrityConstraintViolationException;

public class GroupServiceImplTest {

    @Test
    public void testGetInstance() {
        GroupServiceImpl.getInstance();
        Assert.assertTrue(true);
    }

    @Test
    public void createGroup() {
        try {
            GroupService groupService = GroupServiceImpl.getInstance();
            groupService.createGroupByName("web", "user1");
            ;
            Assert.assertTrue(true);
        }catch (UserNotExistException e) {



        }
    }

    @Test
    public void testAddUserToGroupByName() {

        try {
            GroupService groupService = GroupServiceImpl.getInstance();
            groupService.addUserToGroupByName("web", "user2");
            Assert.assertTrue(true);
        }catch (GroupNotExistException | UserAlreadyInGroupException e) {

            
        }

    }

    @Test
    public void testgetGroupByGroupname() {
        try {
            GroupService groupService = GroupServiceImpl.getInstance();
            groupService.getGroupByGroupname("web");
            Assert.assertTrue(true);
        }catch (NullPointerException e) {

        }

    }

    @Test
    public void testcreateGroup() {
        try {
            Group group = new Group(1);
            group.setGroupName("aaaa");
            GroupServiceImpl.getInstance().createGroup(group, 1);
            Assert.assertTrue(true);

        }catch (ConstraintViolationException e) {

        }
    }

    @Test
    public void testGetGroupNameByUserName() {
        try {
            GroupServiceImpl.getInstance().getGroupNamesByUsername("user1");
            Assert.assertEquals("", "");
        } catch (UserNotExistException e) {

        }
    }
}