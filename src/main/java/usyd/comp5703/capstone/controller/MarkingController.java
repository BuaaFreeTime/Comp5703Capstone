package usyd.comp5703.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import usyd.comp5703.capstone.entity.GroupEntity;
import usyd.comp5703.capstone.service.GroupService;
import usyd.comp5703.capstone.service.MarkingService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MarkingController {
    @Autowired
    GroupService groupService;
    @Autowired
    MarkingService markingService;

    @RequestMapping(value = {"/marks-client"})
    public String marksClient(Model model, HttpSession session) {
        String cid = session.getAttribute("user").toString();
        List<GroupEntity> groupEntityList;
        groupEntityList = groupService.getPresentationClient(cid);
        model.addAttribute("groups", groupEntityList);
        return "Marks-client";
    }

    @PostMapping(value = {"/clientmarking"})
    public String markingClient(@RequestParam("gid") String gid,
                                @RequestParam("Feedback") String feedback,
                                Model model, HttpSession session) {
        //String cid = session.getAttribute("user").toString();
        markingService.updateMarksClient(gid,  feedback);
        return "redirect:marks-client";
    }

    @PostMapping(value = {"/calculate"})
    public String studentMarking(@RequestParam("proposal") String r1,
                                 @RequestParam("progress") String r2,
                                 @RequestParam("final") String r3,
                                 @RequestParam("presentation") String r4,
                                 Model model, HttpSession session) {
        String result;
        result = Integer.toString((Integer.valueOf(r1) + Integer.valueOf(r2) + Integer.valueOf(r3) + Integer.valueOf(r4)) / 4);
        model.addAttribute("result", result);
        return "Marks";

    }
    @PostMapping(value = {"/setformulation"})
    public String setMarking(Model model, HttpSession session) {
         return "Marks-admin";

    }

}
