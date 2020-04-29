package usyd.comp5703.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usyd.comp5703.capstone.dao.GroupDao;
import usyd.comp5703.capstone.entity.GroupEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {
    @Autowired
    GroupDao groupDao;

    public List<GroupEntity> getAllgroup() {
        List<GroupEntity> groupEntityList;
        groupEntityList = groupDao.getAllgroup();
        return groupEntityList;
    }

    public GroupEntity getMygroup(String sid) {
        GroupEntity groupEntity = groupDao.getMygroup(sid);
        return groupEntity;
    }

}
