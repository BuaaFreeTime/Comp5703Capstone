package usyd.comp5703.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import usyd.comp5703.capstone.service.LoginService;

import java.util.Map;

@Controller
public class LoginController {

    @Autowired  //与service层进行交互
    private LoginService loginService;

    @PostMapping("/student/login")
    public String loginStudent(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Map<String,Object> map) {
        boolean loginCheck;
        loginCheck = loginService.studentCheck(username, password);
        if (loginCheck) {
            return "index-student.html";
        }
        else {
            map.put("msg","账号或者密码出错");
            System.out.println("111111111");
            return "login-student.html";
        }
    }

    @PostMapping("/client/login")
    public String loginClient(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Map<String,Object> map) {
        boolean loginCheck;
        loginCheck = loginService.clientCheck(username, password);
        if (loginCheck) {
            return "index-client.html";
        }
        else {
            map.put("msg","账号或者密码出错");
            System.out.println("111111111");
            return "login-client.html";
        }
    }

    @PostMapping("/admin/login")
    public String loginAdmin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             Map<String,Object> map) {
        boolean loginCheck;
        loginCheck = loginService.adminCheck(username, password);
        if (loginCheck) {
            return "index-admin.html";
        }
        else {
            map.put("msg","账号或者密码出错");
            System.out.println("111111111");
            return "login-admin.html";
        }
    }
}
