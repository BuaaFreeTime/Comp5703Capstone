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

    public List<String> getStudentsInProject(String cid) {
        //groupDao.updateClient("x","x");
        List<String> students = groupDao.getStudentInProject(cid);
        return students;
    }


    public List<GroupEntity> getPresentationClient(String cid) {
        List<GroupEntity> groups = groupDao.getPresentationClient(cid);
        return groups;
    }

    public void addGroup(String id,String s1, String s2, String s3, String s4, String s5, String clientId) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setPresentation("2020-06-06T15:15");
        groupEntity.setMarks("0");
        groupEntity.setStudent1(s1);
        groupEntity.setStudent2(s2);
        groupEntity.setStudent3(s3);
        groupEntity.setStudent4(s4);
        groupEntity.setStudent5(s5);
        groupEntity.setClientid(clientId);
        groupEntity.setId(id);
        groupDao.addGroup(groupEntity);
    }

}
