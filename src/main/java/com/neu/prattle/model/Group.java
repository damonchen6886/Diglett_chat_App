package com.neu.prattle.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
@Table (name="cs5500.group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="group_id")
    int groupId;

    @Column(name="moderator")
    int moderatorID;

    @Column(name="group_name")
    String groupName;

    @OneToMany(mappedBy = "group",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Groupchat> groupchats = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cs5500.user_has_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();


    public Group() {
    }

    public Group(int moderatorID) {
        this.moderatorID = moderatorID;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getModeratorID() {
        return moderatorID;
    }

    public void setModeratorID(int moderatorID) {
        this.moderatorID = moderatorID;
    }

    public void addUserToGroup(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<Groupchat> getGroupchats() {
        return groupchats;
    }

    public void setGroupchats(Set<Groupchat> groupchats) {
        this.groupchats = groupchats;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", moderatorID=" + moderatorID +
                '}';
    }
}
