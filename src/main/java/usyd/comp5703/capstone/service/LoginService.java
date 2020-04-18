package usyd.comp5703.capstone.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public static boolean studentCheck(String username, String password) {

        if ((username.equals("student")) && (password.equals("1"))) {
            return true;
        }
        else return false;
    }

    public static boolean clientCheck(String username, String password) {

        if ((username.equals("client")) && (password.equals("1"))) {
            return true;
        }
        else return false;
    }

    public static boolean adminCheck(String username, String password) {

        if ((username.equals("admin")) && (password.equals("1"))) {
            return true;
        }
        else return false;
    }

}
