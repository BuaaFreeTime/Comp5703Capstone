package usyd.comp5703.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import usyd.comp5703.capstone.entity.ApplicantslistEntity;
import usyd.comp5703.capstone.entity.ProjectEntity;
import usyd.comp5703.capstone.entity.StudentPreferenceEntity;
import usyd.comp5703.capstone.service.GroupService;
import usyd.comp5703.capstone.service.StudentPreferenceService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PreferenceController {

    @Autowired
    StudentPreferenceService studentPreferenceService;
    @Autowired
    GroupService groupService;



    @PostMapping(value = {"/adminaddgroup"})
    public String addGroup(@RequestParam("sid") String sid,
                           @RequestParam("first") String first,
                           @RequestParam("second") String second,
                           @RequestParam("third") String third,
                           HttpSession session) {
        String semester = session.getAttribute("semester").toString();
        if (semester.equals("2020 Semester 1")) {
            studentPreferenceService.addPreference(sid, first, second, third, "2020 Semester 1", "yes");
        }
        return "redirect:/studentpreferences";
    }

    @PostMapping(value = {"/deletePreferences"})
    public String deletePreference(@RequestParam("sid") String sid) {
        studentPreferenceService.deletePreference(sid);
        return "redirect:/studentpreferences";
    }

    @RequestMapping(value = {"/studentpreferences"})
    public String allPreference(Model model, HttpSession session) {
        String semester = session.getAttribute("semester").toString();
        if (semester.equals("2020 Semester 1")) {
            List<StudentPreferenceEntity> studentPreferenceEntityList;
            studentPreferenceEntityList = studentPreferenceService.getAllPreference();
            model.addAttribute("preferences", studentPreferenceEntityList);
        }
        return "StudentPreferences";
    }

    @RequestMapping(value = {"/grouping"})
    public String grouping(Model model) {
        groupService.grouping();
        return "redirect:/studentpreferences";
    }

    @RequestMapping(value = {"/applicantslist"})
    public String clientAppList(HttpSession session, Model model) {
        String cid = session.getAttribute("user").toString();
        List<ApplicantslistEntity> applicantslist = studentPreferenceService.getApplicantslist(cid);
        model.addAttribute("applicantslist", applicantslist);
        return "ApplicantsList";
    }

    @PostMapping(value = {"/applicantslistAccept"})
    public String clientAcStuent(@RequestParam("sid") String sid,
                                 @RequestParam("pid") String pid) {
        studentPreferenceService.updateAccept(sid,pid);
        return "redirect:/applicantslist";
    }

    @PostMapping(value = {"/applicantslistdeny"})
    public String clientDeStuent(@RequestParam("sid") String sid,
                                 @RequestParam("pname") String pname) {
        studentPreferenceService.updateDeny(sid, pname);
        return "redirect:/applicantslist";
    }

    @PostMapping(value = {"/updatePreferences"})
    public String updatePreferences(@RequestParam("sid") String sid,
                                 @RequestParam("first") String first,
                                    @RequestParam("second") String second,
                                    @RequestParam("third") String third) {
//       studentPreferenceService.updatePreferences(sid, first, second, third);
        return "redirect:/studentpreferences";
    }
}
