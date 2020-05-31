package usyd.comp5703.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usyd.comp5703.capstone.dao.GroupDao;
import usyd.comp5703.capstone.dao.ProjectDao;
import usyd.comp5703.capstone.dao.StudentDao;
import usyd.comp5703.capstone.dao.StudentPreferenceDao;
import usyd.comp5703.capstone.entity.GroupEntity;
import usyd.comp5703.capstone.entity.ProjectEntity;
import usyd.comp5703.capstone.entity.StudentEntity;
import usyd.comp5703.capstone.entity.StudentPreferenceEntity;

import javax.swing.*;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GroupService {
    @Autowired
    GroupDao groupDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    StudentPreferenceDao studentPreferenceDao;
    @Autowired
    ProjectDao projectDao;

    public List<StudentEntity> getUngroupStudent(){
        List<StudentEntity> studentEntityList = studentDao.getAllStudent();
        List<StudentEntity> ungroup = new ArrayList<>();
        for (StudentEntity studentEntity:studentEntityList) {
            if (studentEntity.getGroupId().equals("")) {
                ungroup.add(studentEntity);
            }
        }
        return ungroup;
    }

    public void grouping(){
        List<StudentPreferenceEntity> studentPreferenceEntityList;
        studentPreferenceEntityList = studentPreferenceDao.getAllPreference();
        List<ProjectEntity> projectEntityList;
        projectEntityList = projectDao.getAllproject();
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, String> projectMapid = new HashMap<>();
        HashMap<String, Integer> projectMapGnum = new HashMap<>();
        HashMap<String, String> groupMap = new HashMap<>();
        //System.out.println(projectEntityList.size());
        for (ProjectEntity projectEntity:projectEntityList) {
            map.put(projectEntity.getName(), Integer.valueOf(projectEntity.getGnumber())*5);
            projectMapGnum.put(projectEntity.getName(), Integer.valueOf(projectEntity.getGnumber()));
            projectMapid.put(projectEntity.getName(), projectEntity.getId());
        }
        for (StudentPreferenceEntity sp:studentPreferenceEntityList) {
            String project;
            int x;
            x = map.get(sp.getFirst());
            if (x>0) {
               project = sp.getFirst();
            }else {
                x = map.get(sp.getSecond());
                if (x>0) {
                    project = sp.getSecond();
                }else {
                    x = map.get(sp.getThird());
                    project = sp.getThird();
                }
            }
            if (x>0) {
                x = x-1;
                String s = groupMap.get(project);
                s = s+","+sp.getSid();
                map.put(project, x);
                groupMap.put(project, s);
            }
        }
        for (Map.Entry<String, String> entry : groupMap.entrySet()) {
            String student = entry.getValue();
            String projectName = entry.getKey();
            String [] students = student.split(",");
            String pid = projectMapid.get(projectName);
            int gnum = projectMapGnum.get(projectName);
            int totalNum = students.length-1;
            int i=1;
            while (gnum>0 && totalNum>0) {
                if (totalNum>=5) {
                    totalNum-=5;
                    ProjectEntity projectEntity = projectDao.getMyproject(pid);
                    GroupEntity groupEntity = new GroupEntity(projectName+"-"+gnum, students[i],
                                                                                    students[i+1],
                                                                                    students[i+2],
                                                                                    students[i+3],
                                                                                    students[i+4],
                                                                                    pid, projectEntity.getClientid());
                    groupDao.addGroup(groupEntity);
                    int x = i+5;
                    for (;i<x;i++){
                        studentDao.updateStudentGid(students[i], projectName+"-"+gnum);
                        studentDao.updateStudentPid(students[i], pid);
                    }
                }
                gnum--;
            }
        }
    }

    public List<String> getScheduleTables(String sid) {
        List<String> time = new ArrayList<>();
        Date current = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = formatter.format(current);
        time.add(currentDate);
        StudentEntity studentEntity = studentDao.getStudent(sid);
        String gid = studentEntity.getGroupId();
        if (gid.equals("")) return null;
        GroupEntity groupEntity = groupDao.getMygroup(gid);
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
        String gid = studentEntity.getGroupId();
        if (gid.equals("")) return "0";
        GroupEntity groupEntity = groupDao.getMygroup(gid);
        return groupEntity.getMarks();
    }

    public String getFeedback(String sid) {
        StudentEntity studentEntity = studentDao.getStudent(sid);
        String gid = studentEntity.getGroupId();
        if (gid.equals("")) return "Not yet feedback";
        GroupEntity groupEntity = groupDao.getMygroup(gid);
        return groupEntity.getFeedback();
    }

    public List<GroupEntity> getAllgroup() {
        List<GroupEntity> groupEntityList;
        groupEntityList = groupDao.getAllgroup();
        return groupEntityList;
    }

    public GroupEntity getMygroup(String sid) {
        StudentEntity studentEntity = studentDao.getStudent(sid);
        String gid = studentEntity.getGroupId();
        if (gid.equals("")) return null;
        GroupEntity groupEntity = groupDao.getMygroup(gid);
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
        String ppid = "";
        List<ProjectEntity> projectEntityList = projectDao.getAllprojectClient(cid);
        for (ProjectEntity i:projectEntityList) {
            if (i.getApprove().equals("yes")) ppid = ppid + "," + i.getId();
        }
        List<String> students = groupDao.getStudentInProject(cid, ppid);
        return students;
    }


    public List<GroupEntity> getPresentationClient(String cid) {
        String ppid = "";
        List<ProjectEntity> projectEntityList = projectDao.getAllprojectClient(cid);
        for (ProjectEntity i:projectEntityList) {
            if (i.getApprove().equals("yes")) ppid = ppid + "," + i.getId();
        }
        List<GroupEntity> groups = groupDao.getPresentationClient(cid, ppid);
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
