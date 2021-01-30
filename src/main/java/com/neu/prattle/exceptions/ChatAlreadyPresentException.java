package com.neu.prattle.exceptions;

public class ChatAlreadyPresentException extends RuntimeException{

  public ChatAlreadyPresentException(String message)  {
    super(message);
  }
}
