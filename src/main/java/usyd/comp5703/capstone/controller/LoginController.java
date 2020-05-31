package usyd.comp5703.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import usyd.comp5703.capstone.entity.StudentEntity;
import usyd.comp5703.capstone.service.LoginService;
import usyd.comp5703.capstone.service.ProfileService;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired  //与service层进行交互
    private LoginService loginService;
    @Autowired
    private ProfileService profileService;

    @PostMapping("/student/login")
    public String loginStudent(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Map<String,Object> map,
                               HttpSession session) {
        boolean loginCheck;
        loginCheck = loginService.studentCheck(username, password);
        if (loginCheck) {
            session.setAttribute("user", username);
            StudentEntity studentEntity = profileService.getStudent(username);
            //session.setAttribute("group", studentEntity.getGroup());
            session.setAttribute("group", studentEntity.getGroup());
            return "redirect:/index-student";
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
            return "redirect:/index-client";
        }
        else {
            map.put("msg","wrong clientID or password");
            return "login-client";
        }
    }

    @PostMapping("/admin/login")
    public String loginAdmin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             Map<String,Object> map,
                             HttpSession session) {
        boolean loginCheck;
        loginCheck = loginService.adminCheck(username, password);
        if (loginCheck) {
            session.setAttribute("user", username);
            return "redirect:/projectsemester";
        }
        else {
            map.put("msg","wrong adminID or password");
            return "login-admin";
        }
    }
}

