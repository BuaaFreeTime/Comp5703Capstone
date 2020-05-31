package usyd.comp5703.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usyd.comp5703.capstone.dao.ProjectDao;
import usyd.comp5703.capstone.dao.StudentDao;
import usyd.comp5703.capstone.dao.StudentPreferenceDao;
import usyd.comp5703.capstone.entity.ApplicantslistEntity;
import usyd.comp5703.capstone.entity.ProjectEntity;
import usyd.comp5703.capstone.entity.StudentEntity;
import usyd.comp5703.capstone.entity.StudentPreferenceEntity;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StudentPreferenceService {
    @Autowired
    StudentPreferenceDao studentPreferenceDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    ProjectDao projectDao;

    public void updateDeny(String sid, String pname){
        StudentPreferenceEntity studentPreference = studentPreferenceDao.getStudentPreference(sid);
        if (studentPreference.getFirst().equals(pname))
            studentPreferenceDao.updateProjectPreference(sid,"first",  "deny");
        if (studentPreference.getSecond().equals(pname))
            studentPreferenceDao.updateProjectPreference(sid,"second",  "deny");
        if (studentPreference.getThird().equals(pname))
            studentPreferenceDao.updateProjectPreference(sid,"third",  "deny");
    }


    public void updateAccept(String sid, String pid){
        studentDao.updateStudentPid(sid,pid);
    }

    public List<ApplicantslistEntity> getApplicantslist(String cid){
        List<ProjectEntity> projectEntityList = projectDao.getAllprojectClient(cid);
        Map<String, ProjectEntity> projectApprove = new HashMap<>();
        List<ApplicantslistEntity> applicantsList = new ArrayList<>();
        System.out.println(projectEntityList.size());
        for (ProjectEntity i:projectEntityList) {
            if (i.getApprove().equals("yes")) projectApprove.put(i.getName(), i);
        }
        List<StudentPreferenceEntity> studentPreferenceEntityList = studentPreferenceDao.getAllPreference();
        for (StudentPreferenceEntity j:studentPreferenceEntityList) {
            if (j.getGroup().equals("no")){
                StudentEntity student = studentDao.getStudent(j.getSid());
                if (student.getProjectId().equals("")||student.getProjectId()==null) {
                    ProjectEntity p1 = projectApprove.get(j.getFirst());
                    if (p1 != null) applicantsList.add(new ApplicantslistEntity(p1, student));
                    ProjectEntity p2 = projectApprove.get(j.getSecond());
                    if (p2 != null) applicantsList.add(new ApplicantslistEntity(p2, student));
                    ProjectEntity p3 = projectApprove.get(j.getThird());
                    if (p3 != null) applicantsList.add(new ApplicantslistEntity(p3, student));
                }
            }
        }
        return applicantsList;
    }

    public void addPreference(String sid, String p1, String p2, String p3, String semester, String group){
        String name = studentDao.getStudent(sid).getName();
        StudentPreferenceEntity studentPreferenceEntity = new StudentPreferenceEntity();
        studentPreferenceEntity.setFirst(p1);
        studentPreferenceEntity.setSecond(p2);
        studentPreferenceEntity.setThird(p3);
        studentPreferenceEntity.setSid(sid);
        studentPreferenceEntity.setName(name);
        Date current = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        String currentDate = formatter.format(current);
        studentPreferenceEntity.setTime(currentDate);
        studentPreferenceEntity.setSemester(semester);
        studentPreferenceEntity.setGroup(group);
        studentPreferenceDao.addStudentPreference(studentPreferenceEntity);
    }

    public List<StudentPreferenceEntity> getAllPreference(){
        List<StudentPreferenceEntity> studentPreferenceEntityList = studentPreferenceDao.getAllPreference();
        return studentPreferenceEntityList;
    }

}
