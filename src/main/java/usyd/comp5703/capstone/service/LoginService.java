package usyd.comp5703.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usyd.comp5703.capstone.dao.ClientDao;
import usyd.comp5703.capstone.dao.LoginDao;
import usyd.comp5703.capstone.dao.ProjectDao;
import usyd.comp5703.capstone.dao.StudentDao;
import usyd.comp5703.capstone.entity.ClientEntity;
import usyd.comp5703.capstone.entity.LoginEntity;
import usyd.comp5703.capstone.entity.ProjectEntity;
import usyd.comp5703.capstone.entity.StudentEntity;

@Service
public class LoginService {

    @Autowired
    LoginDao loginDao;
    @Autowired
    ClientDao clientDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    ProjectDao projectDao;
    @Autowired
    StudentPreferenceService studentPreferenceService;

    public boolean studentCheck(String username, String password) {
 /*
        // Add student account function
        LoginEntity loginEntity = new LoginEntity(username, password);
        loginDao.addStudent(loginEntity);

        System.out.println(username+" "+password);
 */
 /*
        // Add initial student function
        int i;
        for (i=1;i<=50; i++) {
            StudentEntity studentEntity = new StudentEntity("student"+i,
                                                            "student"+i,
                                                            i+"@gmail.com",
                                                            "2020 Semester 1", "no");
            studentDao.addStudent(studentEntity);
        }

 /*
        // Add student preference function
        int i;
        String p = "project";
        for (i=1;i<=50; i++) {
            int x = i % 10;
            int y = (x + 1) % 10;
            int z = (x + 2) % 10;
            if (x==0) x=10;
            if (y==0) y=10;
            if (z==0) z=10;
            studentPreferenceService.addPreference("student"+i, p+x, p+y, p+z, "2020 Semester 1", "no");
        }
 */
        if (loginDao.studentCheck(username, password)) return true;
        else return false;

    }

    public boolean clientCheck(String username, String password) {
        /*
        // Add client function
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setCid("client1");
        clientEntity.setAge("28");
        clientEntity.setEmail("client1@gmail.com");
        clientEntity.setName("Dr. Wu xi");
        clientDao.addClient(clientEntity);
        ClientEntity clientEntity1 = new ClientEntity();
        clientEntity1.setCid("client2");
        clientEntity1.setAge("29");
        clientEntity1.setEmail("client2@gmail.com");
        clientEntity1.setName("Dr. Wang");
        clientDao.addClient(clientEntity1);
         */
        if ((username.contains("client")) && (password.equals("1"))) {
            return true;
        }
        else return false;
    }

    public boolean adminCheck(String username, String password) {
/*
        // Add initial project function
        int i;
        for (i=1;i<=10; i++) {
            ProjectEntity projectEntity = new ProjectEntity(Integer.toString(i));
            projectDao.addProject(projectEntity);
        }
*/

        if ((username.equals("admin")) && (password.equals("1"))) {
            return true;
        }
        else return false;
    }

}
