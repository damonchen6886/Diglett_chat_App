package com.neu.prattle.exceptions;

public class ChatNotExistException extends RuntimeException{

  public ChatNotExistException(String message)  {
    super(message);
  }
}
