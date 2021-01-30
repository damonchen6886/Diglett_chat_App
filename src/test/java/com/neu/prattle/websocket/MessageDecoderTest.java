package com.neu.prattle.websocket;


import com.neu.prattle.model.Message;

import org.junit.Test;


import javax.websocket.ClientEndpointConfig;

import static org.junit.Assert.*;

public class MessageDecoderTest {


  MessageDecoder msg = new MessageDecoder();


  @Test
  public void testDecode() {
    Message m;
    assertNull(msg.decode(""));
    m = msg.decode("{\"from\":\"jiahao\",\"to\":\"chihao\",\"content\":\"hi!\"}");
    assertEquals("hi!",m.getContent());
    assertEquals("jiahao",m.getFrom());
    assertEquals("chihao",m.getTo());

  }

  @Test
  public void testWillDecode() {

    assertTrue(msg.willDecode("hello"));
    assertFalse(msg.willDecode(null));
    msg.destroy();

    ClientEndpointConfig.Builder builder =  ClientEndpointConfig.Builder.create();
    ClientEndpointConfig clientEndpointConfig = builder.build();
    msg.init(clientEndpointConfig);
  }



}