package usyd.comp5703.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import usyd.comp5703.capstone.dao.StudentDao;
import usyd.comp5703.capstone.entity.StudentEntity;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    StudentDao studentDao;

    public void addStudent(StudentEntity studentEntity){
        StudentEntity demo  = new StudentEntity();
        /*
        demo.setName("Lenny Tan");
        demo.setAge("26");
        demo.setEmail("demo@gmail.com");
        demo.setGroupId("3");
        demo.setProjectId("4");
        demo.setSid("student");
         */
        studentDao.addStudent(studentEntity);
    }

    public void addStudent(String sid,String name,String email, String groupId, String projectId){
        StudentEntity demo  = new StudentEntity();
        demo.setName(name);
        demo.setEmail(email);
        demo.setGroupId(groupId);
        demo.setProjectId(projectId);
        demo.setSid(sid);
        studentDao.addStudent(demo);
    }

    public StudentEntity getStudent(String sid) {
        StudentEntity studentEntity = studentDao.getStudent(sid);
        return studentEntity;
    }

    public List<StudentEntity> getAllStudent() {
        List<StudentEntity> studentEntityList = studentDao.getAllStudent();
        return studentEntityList;
    }

}
