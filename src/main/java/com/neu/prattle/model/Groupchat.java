package com.neu.prattle.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table (name = "groupChat")
public class Groupchat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "groupChat_id")
    private int groupChatID;

//    @Column(name = "to_group_id")
//    int groupID;

//    @Column(name = "owner_user_id")
//    int ownerUserID;



    @ManyToOne
    @JoinColumn(name = "owner_user_id")
    private User groupOwner;

    @ManyToOne
    @JoinColumn(name = "to_group_id")
    private Group group;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "groupChat_has_groupMsg",
            joinColumns=@JoinColumn(name="groupChat_id"),
            inverseJoinColumns=@JoinColumn(name = "groupMsg_id")
    )
    private Set<GroupMsg> msgs;

    public Groupchat() {
    }

    public Groupchat(Group group, User groupOwner) {
        this.group = group;
        this.groupOwner = groupOwner;
    }


    public int getGroupChatID() {
        return groupChatID;
    }

    public void setGroupChatID(int groupChatID) {
        this.groupChatID = groupChatID;
    }

    public User getGroupOwner() {
        return groupOwner;
    }

    public void setGroupOwner(User groupOwner) {
        this.groupOwner = groupOwner;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Set<GroupMsg> getMsgs() {
        return msgs;
    }

    public void setMsgs(Set<GroupMsg> msgs) {
        this.msgs = msgs;
    }

    public void addMsgToGroupChat(GroupMsg groupMsg) {
        this.msgs.add(groupMsg);
    }

    public List<String> getMsgsString() {
        List<String> strMsgs = new ArrayList<>();

        for (GroupMsg groupMsg : msgs) {
            String msg = groupMsg.getFrom_user_id() + ": " + groupMsg.getContent();
            strMsgs.add(msg);
        }
        return strMsgs;
    }

    @Override
    public String toString() {
        return "Groupchat{" +
                "groupChatID=" + groupChatID +
                ", groupId=" + group.getGroupId() +
                ", ownerUserID=" + groupOwner.getId() +
                '}';
    }
}