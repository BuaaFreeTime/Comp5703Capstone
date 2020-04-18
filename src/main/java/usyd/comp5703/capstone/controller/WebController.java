package usyd.comp5703.capstone.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping(value = {"", "/",  "/student"})
    public String loginStudent() {
        return "login-student";
    }

    @RequestMapping(value = {"/client"})
    public String loginClient() {
        return "login-client";
    }

    @RequestMapping(value = {"/admin"})
    public String loginAdmin() {
        return "login-admin";
    }

    @RequestMapping(value = {"/index-student"})
    public String index() { return "index-student"; }

    @RequestMapping(value = {"/AllGroup"})
    public String Allgroup() { return "AllGroup"; }
}
