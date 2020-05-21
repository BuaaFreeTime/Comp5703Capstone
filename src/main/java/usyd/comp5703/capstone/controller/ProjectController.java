package usyd.comp5703.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import usyd.comp5703.capstone.entity.ProjectEntity;
import usyd.comp5703.capstone.service.ProjectService;
import usyd.comp5703.capstone.service.StudentPreferenceService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {

    @Autowired  //与service层进行交互
    private ProjectService projectService;
    @Autowired
    StudentPreferenceService studentPreferenceService;


    @PostMapping(value = {"/changeGnum"})
    public String changeGroupNumber(@RequestParam("gnumber") String groupMum,
                                    @RequestParam("pid") String pid) {
        if (!groupMum.equals("") && !pid.equals("")) {
            projectService.updateGnum(pid,groupMum);
        }
        return "redirect:/myprojects-client";

    }

    @PostMapping(value = {"/changeApprove"})
    public String changeApprove(@RequestParam("pid") String pid) {
        if (!pid.equals("")) {
            projectService.updateApprove(pid);
        }
        return "redirect:/projectinformation";

    }

    @RequestMapping(value = {"/myproject"})
    public String studentMyproject(HttpSession session, Map<String,Object> map) {
        //String sid = session.getAttribute("user").toString();
        ProjectEntity myprojectEntity = projectService.getMyproject("student1");
        if (myprojectEntity!=null) {
            map.put("projectUnit", myprojectEntity.getUnit());
            map.put("projectType", myprojectEntity.getType());
            map.put("projectName", myprojectEntity.getName());
            map.put("projectDescription", myprojectEntity.getDescription());
            map.put("projectClient", myprojectEntity.getClient());
            map.put("projectTutor", myprojectEntity.getTutor());
        }
        return "MyProjects";

    }

    @RequestMapping(value = {"/projectinformation"})
    public String adminAllproject(Model model) {
        List<ProjectEntity> projectEntityList;
        projectEntityList = projectService.getAllproject();
        model.addAttribute("projects", projectEntityList);
        return "ProjectInformation";

    }

    @RequestMapping(value = {"/projectlist"})
    public String studentAllproject(Model model) {
        List<ProjectEntity> projectEntityList;
        projectEntityList = projectService.getAllproject();
        model.addAttribute("projects", projectEntityList);
        return "ProjectList";

    }


    @RequestMapping(value = {"/projectpreference"})
    public String studentProjectPreference(Model model) {
        List<ProjectEntity> projectEntityList;
        projectEntityList = projectService.getAllproject();
        model.addAttribute("project1s", projectEntityList);
        model.addAttribute("project2s", projectEntityList);
        model.addAttribute("project3s", projectEntityList);
        return "ProjectPreference";
    }

    @PostMapping(value = {"/preferencesubmit"})
    public String studentPreferenceSubmit(@RequestParam("first") String firstChoice,
                                          @RequestParam("second") String secondChoice,
                                          @RequestParam("third") String thirdChoice,
                                          Model model) {
        List<ProjectEntity> projectEntityList;
        projectEntityList = projectService.getAllproject();
        model.addAttribute("project1s", projectEntityList);
        model.addAttribute("project2s", projectEntityList);
        model.addAttribute("project3s", projectEntityList);
        //String userId = session.getAttribute("user").toString();
        //String userSem = session.getAttribute("semester").toString();
        studentPreferenceService.addPreference("student1", firstChoice,secondChoice,thirdChoice, "2020 Semester 1");
        return "/ProjectPreference";
    }

    @RequestMapping(value = {"/myprojects-client"})
    public String myProjectClient(Model model) {
        List<ProjectEntity> projectEntityList;
        //String cid = session.getAttribute("user").toString();
        projectEntityList = projectService.getAllProjectClient("client1");
        model.addAttribute("projects", projectEntityList);
        return "MyProjects-client";
    }

    @PostMapping(value = {"/addproject"})
    public String addProject(@RequestParam("unit") String unit,
                             @RequestParam("type") String type,
                             @RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("clientid") String clientid,
                             @RequestParam("tutor") String tutor,
                                          Model model) {
       projectService.addProject(unit, type, name, description, clientid, tutor);
       return "redirect:/projectinformation";
    }

    @PostMapping(value = {"/addproject-client"})
    public String addProjectClient(@RequestParam("unit") String unit,
                             @RequestParam("type") String type,
                             @RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("tutor") String tutor,
                             Model model) {
        //String cid = session.getAttribute("user").toString();
        projectService.addProject(unit, type, name, description, "client1", tutor);
        return "redirect:/myprojects-client";

    }
}
