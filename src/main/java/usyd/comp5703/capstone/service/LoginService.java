package usyd.comp5703.capstone.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public static boolean studentCheck(String username, String password) {

        if ((username.equals("tester")) && (password.equals("password"))) {
            return true;
        }
        else return false;
    }

}
