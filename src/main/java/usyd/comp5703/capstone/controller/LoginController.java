package usyd.comp5703.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import usyd.comp5703.capstone.service.LoginService;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired  //与service层进行交互
    private LoginService loginService;

    @PostMapping("/student/login")
    public String loginStudent(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Map<String,Object> map,
                               HttpSession session) {
        boolean loginCheck;
        loginCheck = loginService.studentCheck(username, password);
        if (loginCheck) {
            session.setAttribute("user", username);
            return "index-student";
        }
        else {
            map.put("msg","wrong studentID or password");
            return "login-student";
        }
    }

    @PostMapping("/client/login")
    public String loginClient(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              Map<String,Object> map,
                              HttpSession session) {
        boolean loginCheck;
        loginCheck = loginService.clientCheck(username, password);
        if (loginCheck) {
            session.setAttribute("user", username);
            return "index-client";
        }
        else {
            map.put("msg","wrong clientID or password");
            return "login-client";
        }
    }

    @PostMapping("/admin/login")
    public String loginAdmin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             Map<String,Object> map) {
        boolean loginCheck;
        loginCheck = loginService.adminCheck(username, password);
        if (loginCheck) {
            return "index-admin";
        }
        else {
            map.put("msg","wrong adminID or password");
            return "login-admin";
        }
    }
}
