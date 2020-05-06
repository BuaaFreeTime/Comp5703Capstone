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
    public String loginAdmin() { return "login-admin"; }

    @RequestMapping(value = {"/marksadmin"})
    public String MyProjects() { return "Marks-admin"; }

    @RequestMapping(value = {"typesofprojects"})
    public String AllGroup() { return "TypesofProjects"; }

    @RequestMapping(value = {"/projectsandclients"})
    public String projectsandclients() { return "ProjectsandClients"; }

    @RequestMapping(value = {"/1"})
    public String indexStudent() {
        return "index-student";
    }

    @RequestMapping(value = {"/applicantslist"})
    public String clientAppList() {
        return "ApplicantsList";
    }

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
