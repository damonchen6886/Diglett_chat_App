package com.neu.prattle.model;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class MessageTest {

  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void messageTest() {
    Date time = new Date();
    Message message = new Message();
    message.setFrom("andy1");
    message.setTo("andy2");
    message.setContent("just for build");
    message.setTime(time);
    assertEquals(message.toString(), "From: andy1To: andy2Content: just for buildTime: "
            + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time));
    assertEquals(message.getFrom(), "andy1");
    assertEquals(message.getTo(), "andy2");
    assertEquals(message.getContent(), "just for build");
    Message.MessageBuilder messageBuilder = message.messageBuilder();



  }

  @Test
  public void messageBuilderTest() {
    Message.MessageBuilder messageBuilder = new Message.MessageBuilder();
    messageBuilder.setFrom("andy");
    messageBuilder.setTo("andy");
    messageBuilder.setMessageContent("andy");
    messageBuilder.setTime(new Date());
    messageBuilder.build();

  }

}