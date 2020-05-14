package usyd.comp5703.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import usyd.comp5703.capstone.entity.GroupEntity;
import usyd.comp5703.capstone.entity.ProjectEntity;
import usyd.comp5703.capstone.entity.StudentEntity;
import usyd.comp5703.capstone.service.GroupService;
import usyd.comp5703.capstone.service.ProfileService;
import usyd.comp5703.capstone.service.ProjectService;

import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    @Autowired
    GroupService groupService;
    @Autowired
    ProjectService projectService;
    @Autowired
    ProfileService profileService;

    @RequestMapping(value = {"/index-student", "/student/index-student"})
    public String studentDashboard(Map<String, Object> map) {
        //String sid = session.getAttribute("user").toString();
        GroupEntity groupEntity = groupService.getMygroup("student1");
        ProjectEntity projectEntity = projectService.getMyproject("student1");

        if (groupEntity!=null && projectEntity!=null) {
            String[] strArr = groupEntity.getPresentation().split("T");
            map.put("presentTime", strArr[0] + " " + strArr[1]);
            map.put("projectName", projectEntity.getName());
        }
        return "index-student";
    }

    @RequestMapping(value = {"/index-client"})
    public String clientDashboard(Map<String, Object> map) {
        //String sid = session.getAttribute("user").toString();
        List<ProjectEntity> projectEntityList = projectService.getAllProjectClient("client1");
        map.put("projects", projectEntityList);
        return "index-client";
    }

    @RequestMapping(value = {"/index-admin"})
    public String adminDashboard(Model model) {
        List<ProjectEntity> projectEntityList = projectService.getAllproject();
        List<StudentEntity> studentEntities = profileService.getAllStudent();
        System.out.println(projectEntityList.size());
        model.addAttribute("studentNum", studentEntities.size());
        model.addAttribute("projectNum", projectEntityList.size());
        return "index-admin";
    }
}
