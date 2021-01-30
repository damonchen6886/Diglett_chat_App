package com.neu.prattle.exceptions;

/***
 * An representation of an error which is thrown where a request has been made
 * for creation of a user object that does not exists in the system.
 * **/
public class UserNotExistException extends RuntimeException{

  public UserNotExistException(String message)  {
    super(message);
  }
}
