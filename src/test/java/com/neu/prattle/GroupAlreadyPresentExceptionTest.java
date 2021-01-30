package com.neu.prattle;

import com.neu.prattle.exceptions.GroupAlreadyPresentException;
import org.junit.Test;

import static org.junit.Assert.*;

public class GroupAlreadyPresentExceptionTest {


    @Test
    public void testGroupAlreadyPresent() {
        GroupAlreadyPresentException groupAlreadyPresentException =
                new GroupAlreadyPresentException("error");
    }

}