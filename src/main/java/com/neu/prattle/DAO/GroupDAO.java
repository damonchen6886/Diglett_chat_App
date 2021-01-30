package com.neu.prattle.DAO;

import com.neu.prattle.model.Group;

public interface GroupDAO {

    void addGroup(Group group, int moderatorId);


    Group getGroup(int groupId);



}
