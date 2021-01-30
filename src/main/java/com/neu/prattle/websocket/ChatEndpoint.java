package com.neu.prattle.websocket;

import com.neu.prattle.model.ChatType;
import com.neu.prattle.model.Message;
import com.neu.prattle.model.User;

import com.neu.prattle.service.GroupMsgService;
import com.neu.prattle.service.GroupMsgServiceImpl;
import com.neu.prattle.service.GroupService;
import com.neu.prattle.service.GroupServiceImpl;
import com.neu.prattle.service.MsgService;
import com.neu.prattle.service.MsgServiceImpl;
import com.neu.prattle.service.UserService;
import com.neu.prattle.service.UserServiceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/app.html/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class ChatEndpoint {
  private Session session;
  /* user name as key, session as value*/
  private static ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();
  // key:username, value: User
  private static ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();



  /** The account service. */
  private UserService accountService = UserServiceImpl.getInstance();
  private MsgService msgService = MsgServiceImpl.getInstance();
  private GroupMsgService groupMsgService = GroupMsgServiceImpl.getInstance();
  private GroupService groupService = GroupServiceImpl.getInstance();




  /**
   * On open.
   *
   * Handles opening a new session (websocket connection). If the user is a known
   * user (user management), the session added to the pool of sessions and an
   * announcement to that pool is made informing them of the new user.
   *
   * If the user is not known, the pool is not augmented and an error is sent to
   * the originator.
   *
   * @param session  the web-socket (the connection)
   * @param username the name of the user (String) used to find the associated
   *                 UserService object
   * @throws IOException     Signals that an I/O exception has occurred.
   * @throws EncodeException the encode exception
   */
  @OnOpen
  public void onOpen(Session session, @PathParam("username") String username)
          throws IOException, EncodeException {
    Optional<User> user = accountService.findUserByName(username);
    if (!user.isPresent()) {
      Message error = Message.messageBuilder()
              .setMessageContent(String.format("User %s could not be found", username))
              .setTime(new Date())
              .build();


      session.getBasicRemote().sendObject(error);
      return;
    }

    users.put(username, user.get());
    addEndpoint(session, username);
    session.getBasicRemote().sendObject(createConnectedMessage(username));
  }


  /**
   * Adds a newly opened session to the pool of sessions.
   *
   * @param session    the newly opened session
   * @param username   the user who connected
   */
  private void addEndpoint(Session session, String username) {
    this.session = session;
    sessions.put(username, session);
  }


  /**
   * On message.
   *
   * When a message arrives, broadcast it to all connected users.
   *
   * @param session the session originating the message
   * @param message the text of the inbound messagea
   */
  @OnMessage
  public void onMessage(Session session, Message message, @PathParam("username") String username)
          throws IOException, EncodeException{
    if (message.getType().equals(ChatType.INDIVIDUAL)) {
      sendMsg(message, username);
    }else if (message.getType().equals(ChatType.GROUP)){
      sendGroupMsg(message, username);
    }
  }

  private void sendGroupMsg(Message message, String username) throws IOException, EncodeException{
    String groupname = message.getTo();
    String fromuser = users.get(username).getNickname();
    groupMsgService.sendGroupMsg(username, groupname, message.getContent());
    List<String> members = groupService.getListOfUsernamesByGroupName(groupname);
    message.setFrom(fromuser);
    message.setTime(new Date());
    session.getBasicRemote().sendObject(message);
    for (String name : members) {
      if (users.containsKey(name) && !name.equals(username)) {
        message.setTo(name);
        sendMessage(message);
      }
    }
  }

  private void sendMsg(Message message, String username)
          throws IOException, EncodeException {
    String toUsername = message.getTo();
    msgService.addIndMsg( message.getContent(), username, toUsername);
    String fromNickname = users.get(username).getNickname();
    message.setFrom(fromNickname);
    message.setTime(new Date());
    session.getBasicRemote().sendObject(message);
    if (!toUsername.equals(username) && users.containsKey(toUsername)) {
      sendMessage(message);
    }
  }

  private static void sendMessage(Message message) {
    try{
      Session destination = sessions.get(message.getTo());
      destination.getBasicRemote().sendObject(message);
    } catch (IOException | EncodeException e) {
      e.printStackTrace();
    }
  }










  /**
   * On close.
   *
   * Closes the session by removing it from the pool of sessions and
   * broadcasting the news to everyone else.
   *
   * @param session the session
   */
  @OnClose
  public void onClose(Session session, @PathParam("username") String username) {
    Message message = new Message();
    message.setFrom(username);
    message.setContent("Disconnected!");
    message.setTime(new Date());
    broadcast(message);
  }

  /**
   * On error.
   *
   * Handles situations when an error occurs.  Not implemented.
   *
   * @param session the session with the problem
   * @param throwable the action to be taken.
   */
  @OnError
  public void onError(Session session, Throwable throwable) throws IOException, EncodeException{
    Message error = Message.messageBuilder()
            .setMessageContent(throwable.getMessage())
            .setTime(new Date())
            .build();


    session.getBasicRemote().sendObject(error);
  }


  /**
   * Broadcast.
   *
   * Send a Message to each session in the pool of sessions.
   * The Message sending action is synchronized.  That is, if another
   * Message tries to be sent at the same time to the same endpoint,
   * it is blocked until this Message finishes being sent..
   *
   * @param message msg
   */
  private static void broadcast(Message message) {
    for(Map.Entry<String, Session> endpoint : sessions.entrySet()) {
      synchronized (endpoint) {
        try {
          endpoint.getValue().getBasicRemote()
                  .sendObject(message);
        } catch (IOException | EncodeException e) {
          /* note: in production, who exactly is looking at the console.  This exception's
           *       output should be moved to a logger.
           */
          e.printStackTrace();
        }
      }
    }
  }








  /**
   * setter for sessions
   */
  public void setSession(Session session){
    this.session = session;
  }

  /**
   * getter for sessions
   * @return session
   */
  public Session getSession( ){
    return this.session;
  }



  /**
   * Creates a Message that some user is now connected - that is, a Session was opened
   * successfully.
   *
   * @param username the username
   * @return Message
   */
  private Message createConnectedMessage(String username) {
    return Message.messageBuilder()
            .setFrom("System")
            .setMessageContent(username + ": You are connected!")
            .setTime(new Date())
            .build();
  }


}
