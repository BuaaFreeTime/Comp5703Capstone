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


    @PostMapping(value = {"/updateproject"})
    public String changeGroupNumber(@RequestParam("unit") String unit,
                                    @RequestParam("type") String type,
                                    @RequestParam("name") String name,
                                    @RequestParam("description") String description,
                                    @RequestParam("gnumber") String groupMum,
                                    @RequestParam("pid") String pid) {
        projectService.updateProject(pid, unit, type, name, description, groupMum);
        return "redirect:/myprojects-client";
    }

    @PostMapping(value = {"/updateProjectAdmin"})
    public String updateProjectAdmin(@RequestParam("unit") String unit,
                                    @RequestParam("type") String type,
                                    @RequestParam("name") String name,
                                    @RequestParam("description") String description,
                                    @RequestParam("clientid") String clientid,
                                    @RequestParam("tutor") String tutor,
                                     @RequestParam("pid") String pid) {
        projectService.updateProjectAdmin(unit, type, name, description,clientid,tutor, pid);
        return "redirect:/projectinformation";
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
        String sid = session.getAttribute("user").toString();
        ProjectEntity myprojectEntity = projectService.getMyproject(sid);
        map.put("group", session.getAttribute("group"));
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
    public String studentAllproject(Model model, HttpSession session) {
        List<ProjectEntity> projectEntityList;
        projectEntityList = projectService.getAllproject();
        model.addAttribute("projects", projectEntityList);
        model.addAttribute("group", session.getAttribute("group"));
        return "ProjectList";

    }


    @RequestMapping(value = {"/projectpreference"})
    public String studentProjectPreference(Model model, HttpSession session) {
        String group = session.getAttribute("group").toString();
        List<ProjectEntity> projectEntityList;
        //projectEntityList = projectService.getAllproject(group);
        projectEntityList = projectService.getAllproject();
        model.addAttribute("group", group);
        model.addAttribute("project1s", projectEntityList);
        model.addAttribute("project2s", projectEntityList);
        model.addAttribute("project3s", projectEntityList);
        return "ProjectPreference";
    }

    @PostMapping(value = {"/preferencesubmit"})
    public String studentPreferenceSubmit(@RequestParam("first") String firstChoice,
                                          @RequestParam("second") String secondChoice,
                                          @RequestParam("third") String thirdChoice,
                                          Model model, HttpSession session) {
        List<ProjectEntity> projectEntityList;
        projectEntityList = projectService.getAllproject();
        model.addAttribute("project1s", projectEntityList);
        model.addAttribute("project2s", projectEntityList);
        model.addAttribute("project3s", projectEntityList);
        String userId = session.getAttribute("user").toString();
        //String userSem = session.getAttribute("semester").toString();
        String group = session.getAttribute("group").toString();
        studentPreferenceService.addPreference(userId, firstChoice,secondChoice,thirdChoice, "2020 Semester 1",group);
        return "redirect:/projectpreference";
    }

    @RequestMapping(value = {"/myprojects-client"})
    public String myProjectClient(Model model, HttpSession session) {
        List<ProjectEntity> projectEntityList;
        String cid = session.getAttribute("user").toString();
        projectEntityList = projectService.getAllProjectClient(cid);
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
                             @RequestParam("gnumber") String gnum,
                             HttpSession session) {
        String cid = session.getAttribute("user").toString();
        System.out.println(gnum);
        projectService.addProject(unit, type, name, description, cid, gnum);
        return "redirect:/myprojects-client";

    }
}
