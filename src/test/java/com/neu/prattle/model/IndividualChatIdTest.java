package com.neu.prattle.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class IndividualChatIdTest {
  IndividualChatId id1 =new IndividualChatId();
  IndividualChatId id2 =new IndividualChatId();
  IndividualChatId id3 =new IndividualChatId();
  IndividualChatId id4 =new IndividualChatId();



  @Test
  public void getFromId() {
    id1.setFromId(1);
    id3.setFromId(1);
    id2.setFromId(2);
    id4.setFromId(2);
    assertEquals(1, id1.getFromId());
    assertEquals(2, id2.getFromId());

    id1.setToId(2);
    id3.setToId(2);
    id2.setToId(1);
    id4.setToId(1);
    assertEquals(2,id1.getToId());
    assertEquals(1,id2.getToId());

    assertTrue( id3.equals(id1));
    assertTrue( id3.equals(id3));
    assertTrue( id4.equals(id2));
    assertFalse(id1.equals(null));

    assertEquals(994,id1.hashCode());
    assertEquals(1024,id2.hashCode());

  }

}