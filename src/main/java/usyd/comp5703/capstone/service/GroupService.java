package usyd.comp5703.capstone.service;

import org.springframework.stereotype.Service;
import usyd.comp5703.capstone.dao.GroupDao;
import usyd.comp5703.capstone.entity.GroupEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    public static List<GroupEntity> getAllgroup() {
        List<GroupEntity> groupEntityList;
        groupEntityList = GroupDao.getAllgroup();
        return groupEntityList;
    }

    public static GroupEntity getMygroup(int sid) {
        GroupEntity groupEntity = new GroupEntity("1");
        return groupEntity;
    }

}
