package com.neu.prattle.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "individualChat")
public class IndividualChat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="individualChat_id")
  private int id;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private User owner;

  @Column(name = "peer_id")
  private int peer;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "individualChat_has_individualMsg",
          joinColumns =@JoinColumn(name="individualChat_id"),
          inverseJoinColumns =@JoinColumn(name="individualMsg_id"))
  private Set<IndividualMsg> msgs = new HashSet<>();

  public IndividualChat(){};

  public IndividualChat(User owner, int peer) {
    this.owner = owner;
    this.peer = peer;
  }

  public void addMsg(IndividualMsg m) {
    msgs.add(m);
    System.out.print(m.toString());
  }

  /* Getters and Setters*/

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public int getPeer() {
    return peer;
  }

  public void setPeer(int peer) {
    this.peer = peer;
  }

  public Set<IndividualMsg> getMsgs() {
    return msgs;
  }

  public void setMsgs(Set<IndividualMsg> msgs) {
    this.msgs = msgs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IndividualChat that = (IndividualChat) o;
    return peer == that.peer &&
            owner.equals(that.owner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(owner, peer);
  }
}
