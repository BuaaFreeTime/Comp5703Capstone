package usyd.comp5703.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usyd.comp5703.capstone.dao.StudentDao;
import usyd.comp5703.capstone.dao.StudentPreferenceDao;
import usyd.comp5703.capstone.entity.StudentPreferenceEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StudentPreferenceService {
    @Autowired
    StudentPreferenceDao studentPreferenceDao;
    @Autowired
    StudentDao studentDao;

    public void addPreference(String sid, String p1, String p2, String p3, String semester){
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
        studentPreferenceDao.addStudentPreference(studentPreferenceEntity);
    }

    public List<StudentPreferenceEntity> getAllPreference(){
        List<StudentPreferenceEntity> studentPreferenceEntityList = studentPreferenceDao.getAllPreference();
        return studentPreferenceEntityList;
    }


}
