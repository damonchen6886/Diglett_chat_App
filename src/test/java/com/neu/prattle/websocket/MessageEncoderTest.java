package com.neu.prattle.websocket;



import com.neu.prattle.model.Message;

import org.junit.Test;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.EncodeException;

import static org.junit.Assert.*;

public class MessageEncoderTest {
  MessageEncoder msg = new MessageEncoder();
  Message m = new Message();

  @Test
  public void testEncode() throws EncodeException {
    msg.destroy();
    assertEquals("{\"from\":null,\"to\":null,\"content\":null,\"time\":null,\"type\":null}",msg.encode(m));
    try {
      msg.encode(null);
    }
    catch (EncodeException e) {


    }

    ClientEndpointConfig.Builder builder =  ClientEndpointConfig.Builder.create();
    ClientEndpointConfig clientEndpointConfig = builder.build();
    msg.init(clientEndpointConfig);
  }



}