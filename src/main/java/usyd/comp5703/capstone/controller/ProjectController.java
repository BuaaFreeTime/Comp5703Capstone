package usyd.comp5703.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import usyd.comp5703.capstone.entity.ProjectEntity;
import usyd.comp5703.capstone.service.ProjectService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {

    @Autowired  //与service层进行交互
    private ProjectService projectService;



    @RequestMapping(value = {"/myproject"})
    public String studentMyproject(HttpSession session, Map<String,Object> map) {
        //String sid = session.getAttribute("user").toString();
        ProjectEntity myprojectEntity = projectService.getMyproject("student");
        map.put("projectUnit", myprojectEntity.getUnit());
        map.put("projectType", myprojectEntity.getType());
        map.put("projectName", myprojectEntity.getName());
        map.put("projectDescription", myprojectEntity.getDescription());
        map.put("projectClient", myprojectEntity.getClient());
        map.put("projectTutor", myprojectEntity.getTutor());
        return "MyProjects";

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
        System.out.println(firstChoice);
        System.out.println(secondChoice);
        System.out.println(thirdChoice);
        return "ProjectPreference";
    }

    @RequestMapping(value = {"/myprojects-client"})
    public String myProjectClient(Model model) {
        List<ProjectEntity> projectEntityList;
        //String cid = session.getAttribute("user").toString();
        projectEntityList = projectService.getAllProjectClient("client");
        model.addAttribute("projects", projectEntityList);
        return "MyProjects-client";
    }


}
