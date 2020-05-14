package usyd.comp5703.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import usyd.comp5703.capstone.entity.ProjectEntity;
import usyd.comp5703.capstone.entity.StudentPreferenceEntity;
import usyd.comp5703.capstone.service.GroupService;
import usyd.comp5703.capstone.service.StudentPreferenceService;

import java.util.List;

@Controller
public class PreferenceController {

    @Autowired
    StudentPreferenceService studentPreferenceService;
    @Autowired
    GroupService groupService;

    @PostMapping(value = {"/add"})
    public String addGroup(@RequestParam("id") String id,
                           @RequestParam("s1") String s1,
                           @RequestParam("s2") String s2,
                           @RequestParam("s3") String s3,
                           @RequestParam("s4") String s4,
                           @RequestParam("s5") String s5,
                           @RequestParam("cid") String clientId) {
        //groupService.addGroup(id,s1,s2,s3,s4,s5,clientId);
        return "redirect:/studentpreferences";
    }

    @RequestMapping(value = {"/studentpreferences"})
    public String allPreference(Model model) {
        List<StudentPreferenceEntity> studentPreferenceEntityList;
        studentPreferenceEntityList = studentPreferenceService.getAllPreference();
        model.addAttribute("preferences", studentPreferenceEntityList);
        return "StudentPreferences";
    }

    @RequestMapping(value = {"/grouping"})
    public String grouping(Model model) {
        groupService.grouping();
        return "redirect:/studentpreferences";
    }

}
