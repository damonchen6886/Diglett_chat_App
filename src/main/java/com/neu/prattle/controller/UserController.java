package com.neu.prattle.controller;

import com.neu.prattle.exceptions.ChatNotExistException;
import com.neu.prattle.exceptions.UserAlreadyPresentException;
import com.neu.prattle.exceptions.UserNotExistException;
import com.neu.prattle.model.IndividualChat;
import com.neu.prattle.model.IndividualMsg;
import com.neu.prattle.model.SimpleChat;
import com.neu.prattle.model.SimpleUser;
import com.neu.prattle.model.User;
import com.neu.prattle.service.ChatService;
import com.neu.prattle.service.ChatServiceImpl;

import com.neu.prattle.service.GroupService;
import com.neu.prattle.service.GroupServiceImpl;
import com.neu.prattle.service.UserService;
import com.neu.prattle.service.UserServiceImpl;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import java.util.Optional;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



/***
 * A Resource class responsible for handling CRUD operations
 * on User objects.
 *
 * @author CS5500 Fall 2019 Teaching staff
 * @version dated 2019-10-06
 */
@Path(value = "/user")
public class UserController {

    // Usually Dependency injection will be used to inject the service at run-time
    private UserService accountService = UserServiceImpl.getInstance();

    private ChatService chatService = ChatServiceImpl.getInstance();



    /***
     * Handles a HTTP POST request for user creation
     *
     * @param user -> The User object decoded from the payload of POST request.
     * @return -> A Response indicating the outcome of the requested operation.
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUserAccount(User user) {
        try {
            accountService.addUser(user);
        } catch (UserAlreadyPresentException e) {
            String msg = user.getUsername() + " is already registered!";
            return Response.status(409).entity(msg).build();
        }

        String msg = user.getUsername() + " successfully registered!";
        return Response.ok().entity(msg).build();
    }

    @POST
    @Path("/validate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response valiDateUser(SimpleUser user) {
        String username = user.getUsername();
        Optional<User> u = accountService.findUserByName(username);
        String msg;
        if (!u.isPresent()) {
            msg = "no user";
            return Response.ok().entity(msg).build();
        } else if (!u.get().getPassword().equals(user.getPassword())) {
            msg = "incorrect";
            return Response.ok().entity(msg).build();
        } else {
            return Response.ok().entity("correct").build();
        }
    }

    @POST
    @Path("/history")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response chathistory(SimpleChat c) {
        try {
            Set<IndividualMsg> msgs = chatService.getMsgsByUsernames(c.getOwner(), c.getPeer());
            List<String> history = new ArrayList<>();
            for (IndividualMsg m : msgs) {
                String res = m.getFrom() + ": " + m.getContent();
                history.add(res);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return Response.ok().entity(objectMapper.writeValueAsString(history)).build();
        } catch (ChatNotExistException e) {
          String history = startIndChatHis(c);
          return Response.ok().entity(history).build();
        } catch (IOException e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }



    public String startIndChatHis(SimpleChat c) {
      try {
        Optional<User> fromUser = accountService.findUserByName(c.getOwner());
        Optional<User> toUser = accountService.findUserByName(c.getPeer());
        if (!fromUser.isPresent() || !toUser.isPresent()) {
          throw new UserNotExistException("user not exists.");
        } else {
          Optional<IndividualChat> c1 = Optional.ofNullable(chatService.getChat(fromUser.get(), toUser.get()));
          Optional<IndividualChat> c2 = Optional.ofNullable(chatService.getChat(toUser.get(), fromUser.get()));
          if (!c1.isPresent()) {
            chatService.addChat(fromUser.get(), toUser.get().getId());
          }
          if (!c2.isPresent()) {
            chatService.addChat(toUser.get(), fromUser.get().getId());
          }
          String msg = "Start New Chat...";
          List<String> history = new ArrayList<>();
          history.add(msg);
          ObjectMapper objectMapper = new ObjectMapper();
          return objectMapper.writeValueAsString(history);

        }
      } catch (IOException e) {
        return "";
      }
    }


}
