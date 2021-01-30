package com.neu.prattle.controller;

import com.neu.prattle.exceptions.GroupNotExistException;
import com.neu.prattle.exceptions.UserAlreadyInGroupException;
import com.neu.prattle.exceptions.UserNotExistException;
import com.neu.prattle.service.GroupChatService;
import com.neu.prattle.service.GroupChatServiceImpl;

import com.neu.prattle.service.GroupService;
import com.neu.prattle.service.GroupServiceImpl;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path(value = "/group")
public class GroupController {

  private GroupService groupService = GroupServiceImpl.getInstance();
  private GroupChatService groupChatService = GroupChatServiceImpl.getInstance();

  @POST
  @Path("/addgroup")
  public Response createGroupOrAddUserToGroupByName(@QueryParam("username") String username,
                                                    @QueryParam("groupName") String groupName) {

    try {
      groupService.addUserToGroupByName(groupName, username);
      return Response.ok().entity(username + " is added to " + groupName).build();

    }catch (GroupNotExistException | UserAlreadyInGroupException e) {
      if (e instanceof GroupNotExistException) {
        groupService.createGroupByName(groupName, username);
        return Response.ok().entity(groupName + " does not exist, but it is just created ").build();
      } else {
        return Response.ok().entity(e.getMessage()).build();
      }
    }
  }


  /**
     * get names of groups that a user joined
     * @param username the username of a uer
     * @return get names of groups that a user joined
   * */
  @POST
  @Path("/getgroups")
  public Response  getListOfGroupNamesByUsername(@QueryParam("username") String  username) {
        try {
            List<String> groupNames = groupService.getGroupNamesByUsername(username);
            ObjectMapper objectMapper = new ObjectMapper();
            return Response.ok().entity(objectMapper.writeValueAsString(groupNames)).build();

        } catch (UserNotExistException | IOException e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
  }


  @POST
  @Path("/getgroupusers")
  public Response  getListOfUsernamesByGroupName(@QueryParam("groupname") String groupName) {
    try {
        List<String> usernames = groupService.getListOfUsernamesByGroupName(groupName);
        ObjectMapper objectMapper = new ObjectMapper();
        return Response.ok().entity(objectMapper.writeValueAsString(usernames)).build();

    } catch (GroupNotExistException | IOException e) {
        return Response.status(409).entity(e.getMessage()).build();
    }
}

  @POST
  @Path("/getchathistory")
  public Response getGroupChatHistory(@QueryParam("username") String username, @QueryParam("groupname") String groupName) {
    try {
      List<String> history = groupChatService.getGroupChatHistory(username, groupName);
      ObjectMapper objectMapper = new ObjectMapper();

      return Response.ok().entity(objectMapper.writeValueAsString(history)).build();
    }catch (IOException e) {
      return Response.status(409).entity(e.getMessage()).build();

    }
  }
}
