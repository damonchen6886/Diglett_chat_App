package com.neu.prattle.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IndividualChatId  implements Serializable {

  @Column(name = "from_id")
  private int fromId;

  @Column(name = "to_id")
  private int toId;

  public IndividualChatId() {
  }

  public IndividualChatId(int from, int to) {
    fromId = from;
    toId = to;
  }

  public int getFromId() {
    return fromId;
  }

  public void setFromId(int fromId) {
    this.fromId = fromId;
  }

  public int getToId() {
    return toId;
  }

  public void setToId(int toId) {
    this.toId = toId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IndividualChatId that = (IndividualChatId) o;
    return fromId == that.fromId &&
            toId == that.toId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(fromId, toId);
  }
}
