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

import javax.servlet.http.HttpSession;
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
    public String studentDashboard(Map<String, Object> map, HttpSession session ) {
        String sid = session.getAttribute("user").toString();

        GroupEntity groupEntity = groupService.getMygroup(sid);
        System.out.println(sid);
        ProjectEntity projectEntity = projectService.getMyproject(sid);
        System.out.println(sid);

        if (projectEntity!=null){
            map.put("projectName", projectEntity.getName());
        }

        if (groupEntity!=null) {
            System.out.println(sid);
            String[] strArr = groupEntity.getPresentation().split("T");
            if (strArr.length==1) {map.put("presentTime", "Not yet set"); }
            else {map.put("presentTime", strArr[0] + " " + strArr[1]);}
        }
        //StudentEntity studentEntity = profileService.getStudent(sid);
        map.put("group", session.getAttribute("group"));
        return "index-student";
    }

    @RequestMapping(value = {"/index-client"})
    public String clientDashboard(Map<String, Object> map, HttpSession session) {
        String cid = session.getAttribute("user").toString();
        List<ProjectEntity> projectEntityList = projectService.getAllApproveProjectClient(cid);
        map.put("projects", projectEntityList);
        return "index-client";
    }

    @RequestMapping(value = {"/index-admin"})
    public String adminDashboard(Model model, HttpSession session) {
        String semester = session.getAttribute("semester").toString();
        if (semester.equals("2020 Semester 1")) {
            List<ProjectEntity> projectEntityList = projectService.getAllproject();
            List<StudentEntity> studentEntities = profileService.getAllStudent();
            model.addAttribute("studentNum", studentEntities.size());
            model.addAttribute("projectNum", projectEntityList.size());
        }else {
            model.addAttribute("studentNum", 0);
            model.addAttribute("projectNum", 0);
        }
        return "index-admin";
    }
}
