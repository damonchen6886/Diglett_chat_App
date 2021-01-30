package com.neu.prattle.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GroupMsgTest {
    GroupMsg groupMsg;

    @Before
    public void setUp() throws Exception {
        groupMsg = new GroupMsg();
    }

    @Test
    public void getGroupMsg_id() {
        groupMsg.getGroupMsg_id();
        assertFalse(false);
    }

    @Test
    public void setGroupMsg_id() {
        groupMsg.setGroupMsg_id(1);assertFalse(false);
    }

    @Test
    public void getContent() {
        groupMsg.getContent();assertFalse(false);
    }

    @Test
    public void setContent() {
        groupMsg.setContent("msg");assertFalse(false);
    }
}