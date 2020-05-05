package usyd.comp5703.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usyd.comp5703.capstone.dao.StudentDao;
import usyd.comp5703.capstone.entity.StudentEntity;

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

    public StudentEntity getStudent(String sid) {
        StudentEntity studentEntity = studentDao.getStudent(sid);
        return studentEntity;
    }

}
