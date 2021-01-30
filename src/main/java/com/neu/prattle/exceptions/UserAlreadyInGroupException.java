package com.neu.prattle.exceptions;

public class UserAlreadyInGroupException extends RuntimeException{
  public UserAlreadyInGroupException(String message)  {
    super(message);
  }
}
