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
    public String loginAdmin() { return "login-admin"; }

    @RequestMapping(value = {"/MyProjects", "/student/MyProjects"})
    public String MyProjects() { return "MyProjects"; }

    @RequestMapping(value = {"/AllGroup", "/student/AllGroup"})
    public String AllGroup() { return "AllGroup"; }

    @RequestMapping(value = {"/MyGroup", "/student/MyGroup"})
    public String MyGroup() { return "MyGroup"; }

    @RequestMapping(value = {"/1"})
    public String indexStudent() {
        return "index-student";
    }

    @RequestMapping(value = {"/ProjectList", "/student/ProjectList"})
    public String ProjectList() { return "ProjectList"; }

    @RequestMapping(value = {"/PresentationSlot", "/student/PresentationSlot"})
    public String PresentationSlot() { return "PresentationSlot"; }

    @RequestMapping(value = {"/Marks", "/student/Marks"})
    public String Marks() { return "Marks"; }

    @RequestMapping(value = {"/ScheduleTables", "/student/ScheduleTables"})
    public String ScheduleTables() { return "ScheduleTables"; }

    @RequestMapping(value = {"/index-admin"})
    public String indexAdmin() { return "index-admin"; }

    @RequestMapping(value = {"/ProposedProjects"})
    public String ProposedProjects() { return "ProposedProjects"; }

    @RequestMapping(value = {"/ProjectInformation"})
    public String ProjectInformation() { return "ProjectInformation"; }

    @RequestMapping(value = {"/UploadStudentInfo"})
    public String UploadStudentInfo() { return "UploadStudentInfo"; }

    @RequestMapping(value = {"/StudentGroup"})
    public String StudentGroup() { return "StudentGroup"; }

    @RequestMapping(value = {"/StudentPreferences"})
    public String StudentPreferences() { return "StudentPreferences"; }

    @RequestMapping(value = {"/Presentation"})
    public String Presentation() { return "Presentation"; }

    @RequestMapping(value = {"/ProjectsandClients"})
    public String ProjectsandClients() { return "ProjectsandClients"; }

    @RequestMapping(value = {"/TypesofProjects"})
    public String TypesofProjects() { return "TypesofProjects"; }

    @RequestMapping(value = {"/Marks-admin"})
    public String MarksAdmin() { return "Marks-admin"; }


}
