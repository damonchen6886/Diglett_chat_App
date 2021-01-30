package com.neu.prattle.DAO;

import com.neu.prattle.model.Group;

import org.junit.Before;
import org.junit.Test;

public class GroupDAOImplTest {
    private GroupDAO groupDAO;
    private Group group;
    @Before
    public void setUp() throws Exception {
        groupDAO = new GroupDAOImpl();
        group = new Group(1);
    }

    @Test
    public void addGroup() {
        groupDAO.addGroup(group, 1);
    }

    @Test
    public void getGroup() {
        groupDAO.getGroup(1);
    }
}