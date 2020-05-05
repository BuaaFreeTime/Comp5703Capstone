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
        //String cid = session.getAttribute("user").toString();
        List<GroupEntity> groupEntityList;
        groupEntityList = groupService.getPresentationClient("client");
        model.addAttribute("groups", groupEntityList);
        return "Marks-client";
    }

    @PostMapping(value = {"/clientmarking"})
    public String markingClient(@RequestParam("Proposal") String proposal,
                                @RequestParam("Progress") String progress,
                                @RequestParam("Final") String report,
                                @RequestParam("Presentation") String presentation,
                                Model model, HttpSession session) {
        //String cid = session.getAttribute("user").toString();
        markingService.updateMarksClient("1", proposal, progress, report, presentation);
        return "redirect:marks-client";
    }



}
