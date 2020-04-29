package usyd.comp5703.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import usyd.comp5703.capstone.entity.ProjectEntity;
import usyd.comp5703.capstone.service.ProjectService;

import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {

    @Autowired  //与service层进行交互
    private ProjectService projectService;

    @RequestMapping(value = {"/myproject"})
    public String studentMyproject( Map<String,Object> map) {
        ProjectEntity myprojectEntity = projectService.getMyproject("3");
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

    /*
    @RequestMapping(value = {"/projectpreference"})
    public String studentProjectPreference( Map<String,Object> map) {
        MyprojectEntity myprojectEntity;

        myprojectEntity = projectService.getMyproject(480);
        map.put("projectUnit", myprojectEntity.getUnit());
        map.put("projectType", myprojectEntity.getType());
        map.put("projectName", myprojectEntity.getName());
        map.put("projectDescription", myprojectEntity.getDescription());
        map.put("projectClient", myprojectEntity.getClient());
        map.put("projectTutor", myprojectEntity.getTutor());
        return "MyProjects";

    }*/


}
