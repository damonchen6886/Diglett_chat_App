package com.neu.prattle.model;



import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NaturalId;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="user_id")
  private int id;

  @Column(name="username", unique = true)
  @NaturalId (mutable = false)
  private String username;

  @Column(name="password")
  private String password;

  @Column(name="nickname")
  private String nickname;

  @Column(name="DOB")
  private String dob;

  @Column(name="status")
  private String status;

  @OneToMany(mappedBy = "owner",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<IndividualChat> indChats = new HashSet<>();

//  @OneToMany(fetch = FetchType.EAGER, mappedBy = "to")
//  private Set<IndividualChat> indChatsAsReceiver = new HashSet<>();



  @OneToMany(mappedBy = "groupOwner",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<Groupchat> groupchats = new HashSet<>();


  @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)

  private Set<Group> groups = new HashSet<>();


  public User() {
  }

  // user input for test purpose
  public User(String username, String password) {
    super();
    this.username = username;
    this.password = password;
    nickname = "Default";
    dob = "2019-11-13";
    status = "offline";
  }

  public User(String username, String password, String nickname, String dob) {
    super();
    this.username = username;
    this.password = password;
    this .nickname = nickname;
    this.dob = dob;
    this.status = "offline";
  }



  public void addIndChat(IndividualChat c) {
    indChats.add(c);
  }

//  public void addIndChatAsReceiver(IndividualChat c) {
//    indChatsAsReceiver.add(c);
//  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return username.equals(user.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username);
  }


  /**
  Getters and setters
   */
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Set<IndividualChat> getIndChats() {
    return indChats;
  }

  public void setIndChats(Set<IndividualChat> indChatsAsSender) {
    this.indChats = indChatsAsSender;
  }

  public Set<Group> getGroups() {
    return groups;
  }

  public void setGroups(Set<Group> groups) {
    this.groups = groups;
  }

  public Set<Groupchat> getGroupchats() {
    return groupchats;
  }

  public void setGroupchats(Set<Groupchat> groupchats) {
    this.groupchats = groupchats;
  }

  public void addNewGroupChat(Groupchat groupchat) {
    this.groupchats.add(groupchat);
  }

//  public Set<IndividualChat> getIndChatsAsReceiver() {
//    return indChatsAsReceiver;
//  }
//
//  public void setIndChatsAsReceiver(Set<IndividualChat> indChatsAsReceiver) {
//    this.indChatsAsReceiver = indChatsAsReceiver;
//  }
}



