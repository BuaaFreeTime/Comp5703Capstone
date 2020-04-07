package usyd.comp5703.capstone.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping(value = {"", "/", "/index", "/home", "/student"})
    public String loginStudent() {
        return "login-student.html";
    }

    @RequestMapping(value = {"/client"})
    public String loginClient() {
        return "login-client.html";
    }

    @RequestMapping(value = {"/admin"})
    public String loginAdmin() {
        return "login-admin.html";
    }

}
