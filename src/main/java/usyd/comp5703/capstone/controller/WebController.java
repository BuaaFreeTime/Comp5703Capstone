package usyd.comp5703.capstone.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping(value = {"", "/",  "/student"})
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

    @RequestMapping(value = {"/index"})
    public String index() {
        return "index.html";
    }

    @RequestMapping(value = {"/1"})
    public String indexStudent() {
        return "index-student.html";
    }

}
