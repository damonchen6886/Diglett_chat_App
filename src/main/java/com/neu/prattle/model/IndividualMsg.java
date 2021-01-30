package com.neu.prattle.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name= "individualMsg")
public class IndividualMsg {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="individualMsg_id")
  private int id;

  @Column(name="content")
  private String content;

  @Column(name = "time")
  private String time;

  @Column(name = "from_User")
  private String from;

  @Column(name = "to_User")
  private String to;


  @ManyToMany(mappedBy = "msgs", fetch = FetchType.EAGER)
  private Set<IndividualChat> chats = new HashSet<>();

  public IndividualMsg(){};

  public IndividualMsg(String content, String time, String from, String to) {
    this.content = content;
    this.time = time;
    this.from = from;
    this.to = to;
  }

  public void addChat(IndividualChat c) {
    chats.add(c);
  }

  /*Getters and setters*/

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Set<IndividualChat> getChats() {
    return chats;
  }

  public void setChats(Set<IndividualChat> chats) {
    this.chats = chats;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IndividualMsg that = (IndividualMsg) o;
    return id == that.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
