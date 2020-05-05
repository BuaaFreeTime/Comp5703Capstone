package usyd.comp5703.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usyd.comp5703.capstone.dao.GroupDao;
import usyd.comp5703.capstone.dao.StudentDao;
import usyd.comp5703.capstone.entity.GroupEntity;
import usyd.comp5703.capstone.entity.StudentEntity;

import javax.swing.*;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GroupService {
    @Autowired
    GroupDao groupDao;
    @Autowired
    StudentDao studentDao;

    public List<String> getScheduleTables(String sid) {
        List<String> time = new ArrayList<>();
        Date current = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = formatter.format(current);
        time.add(currentDate);
        StudentEntity studentEntity = studentDao.getStudent(sid);
        GroupEntity groupEntity = groupDao.getMygroup(studentEntity.getGroupId());
        String [] strArr = groupEntity.getPresentation().split("T");
        String presentTime = strArr[0];
        time.add(presentTime);
        ParsePosition pos = new ParsePosition(0);
        Date present = formatter.parse(presentTime, pos);
        long day = 0;
        try {
            day = (present.getTime() - current.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        time.add(String.valueOf(day));
        return time;
    }

    public String getMarks(String sid) {
        StudentEntity studentEntity = studentDao.getStudent(sid);
        GroupEntity groupEntity = groupDao.getMygroup(studentEntity.getGroupId());
        return groupEntity.getMarks();
    }


    public List<GroupEntity> getAllgroup() {
        List<GroupEntity> groupEntityList;
        groupEntityList = groupDao.getAllgroup();
        return groupEntityList;
    }

    public GroupEntity getMygroup(String sid) {
        StudentEntity studentEntity = studentDao.getStudent(sid);
        GroupEntity groupEntity = groupDao.getMygroup(studentEntity.getGroupId());
        return groupEntity;
    }

    public GroupEntity updatePresentation(String sid, String date) {
        StudentEntity studentEntity = studentDao.getStudent(sid);
        GroupEntity groupEntity = groupDao.getMygroup(studentEntity.getGroupId());
        groupDao.updatePresentation(groupEntity.getId(), date);
        return groupEntity;
    }

}
