package com.neu.prattle.model;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table (name = "groupMsg")
public class GroupMsg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupMsg_id")
    int groupMsg_id;

    @Column(name = "content")
    String content;

    @Column(name = "from_user_id")
    String from_user_id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "groupChat_has_groupMsg",
            joinColumns = @JoinColumn(name = "groupMsg_id"),
            inverseJoinColumns = @JoinColumn(name = "groupChat_id")
    )
    private Set<Groupchat> groupchats;

    public GroupMsg() {
    }

    public GroupMsg(String content, String username) {
        this.content = content;
        this.from_user_id = username;
    }

    public int getGroupMsg_id() {
        return groupMsg_id;
    }

    public void setGroupMsg_id(int groupMsg_id) {
        this.groupMsg_id = groupMsg_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Set<Groupchat> getGroupchats() {
        return groupchats;
    }

    public void setGroupchats(Set<Groupchat> groupchats) {
        this.groupchats = groupchats;
    }

    public String getFrom_user_id() {
        return from_user_id;
    }

    public void setFrom_user_id(String from_user_id) {
        this.from_user_id = from_user_id;
    }
}
