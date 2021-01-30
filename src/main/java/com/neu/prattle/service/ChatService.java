package com.neu.prattle.service;

import com.neu.prattle.model.IndividualChat;
import com.neu.prattle.model.IndividualMsg;
import com.neu.prattle.model.User;


import java.util.Set;

public interface ChatService {
  void addChat(User from, int peerId);
  void addPairChat(String user1, String user2);
  IndividualChat getChat(User owner, User peer);
  Set<IndividualMsg> getMsgs (IndividualChat c);
  Set<IndividualMsg> getMsgsByUsernames (String user1, String user2);
}
