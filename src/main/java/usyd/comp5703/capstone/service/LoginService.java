package usyd.comp5703.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usyd.comp5703.capstone.dao.LoginDao;
import usyd.comp5703.capstone.entity.LoginEntity;

@Service
public class LoginService {

    @Autowired
    LoginDao loginDao;

    public boolean studentCheck(String username, String password) {



        /* Add student account function
        LoginEntity loginEntity = new LoginEntity(username, password);
        loginDao.addStudent(loginEntity);

        System.out.println(username+" "+password);
         */
        if (loginDao.studentCheck(username, password)) return true;
        else return false;

    }

    public boolean clientCheck(String username, String password) {

        if ((username.equals("client")) && (password.equals("1"))) {
            return true;
        }
        else return false;
    }

    public boolean adminCheck(String username, String password) {

        if ((username.equals("admin")) && (password.equals("1"))) {
            return true;
        }
        else return false;
    }

}
