package usyd.comp5703.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import usyd.comp5703.capstone.entity.SemesterEntity;
import usyd.comp5703.capstone.service.SemesterService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SemesterController {

    @Autowired
    SemesterService semesterService;

    @RequestMapping(value = {"/projectsemester"})
    public String projectSemester(Model model) {
        List<SemesterEntity> semesterEntities = semesterService.getAllSemester();
        model.addAttribute("semesters", semesterEntities);
        return "projectsemester";
    }

    @RequestMapping(value = {"/projectsemester-client"})
    public String projectclientSemester(Model model) {
        List<SemesterEntity> semesterEntities = semesterService.getAllSemester();
        model.addAttribute("semesters", semesterEntities);
        return "projectsemester-client";
    }


    @PostMapping("/semester/login")
    public String loginSemester(@RequestParam("ST") String semester,
                                HttpSession session){
        session.setAttribute("semester",semester);
        return "redirect:/index-admin";
    }

    @PostMapping("/semester-client/login")
    public String loginClientSemester(@RequestParam("ST") String semester,
                                HttpSession session){
        session.setAttribute("semester",semester);
        return "redirect:/index-client";
    }


    @RequestMapping(value = {"/createsemester"})
    public String createSemester() { return "createsemester"; }

    @PostMapping(value = {"/sumitsemester"})
    public String sumitSemeste(@RequestParam("semester") String semester) {
        semesterService.addSemester(semester);
        return "redirect:/projectsemester";
    }
}
