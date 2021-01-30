package com.neu.prattle.model;

import com.neu.prattle.service.ChatService;

import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * A Basic POJO for Message.
 *
 * @author CS5500 Fall 2019 Teaching staff
 * @version dated 2019-10-06
 */
public class Message {
  /***
   * The name of the user who sent this message.
   */
  private String from;
  /***
   * The name of the user to whom the message is sent.
   */
  private String to;
  /***
   * It represents the contents of the message.
   */
  private String content;

  /***
   * It represents the timestamp of the message.
   */
  private String time;

  private ChatType type;

  @Override
  public String toString() {
    return new StringBuilder()
            .append("From: ").append(from)
            .append("To: ").append(to)
            .append("Content: ").append(content)
            .append("Time: ").append(time)
            .toString();
  }

  /***
   * Convert time stamp to string
   */
  private String timeString(Date time) {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setTime(Date time) {
    this.time = timeString(time);
  }

  public String getTime() {
    return time;
  }

  public ChatType getType() {
    return type;
  }

  public void setType(ChatType type) {
    this.type = type;
  }

  public static MessageBuilder messageBuilder()   {
    return new MessageBuilder();
  }


  /***
   * A Builder helper class to create instances of {@link Message}
   */
  public static class MessageBuilder    {
    /***
     * Invoking the build method will return this message object.
     */
    Message message;

    public MessageBuilder()    {
      message = new Message();
      message.setFrom("Not set");
    }

    public MessageBuilder setFrom(String from)    {
      message.setFrom(from);
      return this;
    }

    public MessageBuilder setTo(String to)    {
      message.setTo(to);
      return this;
    }

    public MessageBuilder setMessageContent(String content)   {
      message.setContent(content);
      return this;
    }

    public MessageBuilder setTime(Date time) {
      message.setTime(time);
      return this;
    }

    public  MessageBuilder setType(ChatType type) {
      message.setType(type);
      return this;
    }


    public Message build()  {
      return message;
    }
  }
}