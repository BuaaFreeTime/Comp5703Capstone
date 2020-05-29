package usyd.comp5703.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import usyd.comp5703.capstone.entity.GroupEntity;
import usyd.comp5703.capstone.entity.StudentEntity;
import usyd.comp5703.capstone.service.GroupService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class GroupController {

    @Autowired  //与service层进行交互
    private GroupService groupService;

    @RequestMapping(value = {"/marks"})
    public String studentMarks(HttpSession session, Map<String, Object> map) {
        //String sid = session.getAttribute("user").toString();
        String marks = groupService.getMarks("student1");
        String feedback =  groupService.getFeedback("student1");
        map.put("score", marks);
        map.put("feedback", feedback);
        return "Marks";
    }

    @RequestMapping(value = {"/allgroup"})
    public String allgroupStudent(Model model) {
         List<GroupEntity> groupEntityList;
        GroupEntity groupEntity = groupService.getMygroup("student1");
        if (groupEntity!=null) {
            groupEntityList = groupService.getAllgroup();
            model.addAttribute("groups", groupEntityList);
        }
        return "AllGroup";
    }

    @RequestMapping(value = {"/mygroup"})
    public String mygroupStudent(Model model, HttpSession session) {
        //String sid = session.getAttribute("user").toString();
        GroupEntity groupEntity = groupService.getMygroup("student1");
        if (groupEntity!=null)  model.addAttribute("groups", groupEntity);
        return "MyGroup";
    }

    @RequestMapping(value = {"/presentationslot"})
    public String studentPresentationslot( Map<String,Object> map) {
        //String sid = session.getAttribute("user").toString();
        GroupEntity groupEntity = groupService.getMygroup("student1");
        if (groupEntity!=null) map.put("groupID", groupEntity.getId());
        if (groupEntity!=null) map.put("groupRoom", groupEntity.getRoom());
        return "PresentationSlot";

    }

    @PostMapping(value = {"/preferencestimesubmit"})
    public String studentPresentationsTime(@RequestParam("presentationTime") String date,
                                           Map<String,Object> map) {
        //String sid = session.getAttribute("user").toString();
        GroupEntity groupEntity = groupService.updatePresentation("student1", date);
        map.put("groupID", groupEntity.getId());
        map.put("groupRoom", groupEntity.getRoom());

        return "PresentationSlot";
    }

    @RequestMapping(value = {"/scheduletables"})
    public String scheduleTables(Map<String,Object> map){
        //String sid = session.getAttribute("user").toString();
        List<String> time = groupService.getScheduleTables("student1");
        if (time!=null) {
            map.put("currentDate", time.get(0));
            map.put("presentationDate", time.get(1));
            map.put("remainingDays", time.get(2));
        }
        return "ScheduleTables";
    }

    @RequestMapping(value = {"/studentsinprojects"})
    public String allStudentClient(Model model, HttpSession session) {
        //String cid = session.getAttribute("user").toString();
        List<String> studentList;
        studentList = groupService.getStudentsInProject("client1");
        model.addAttribute("students", studentList);
        return "StudentsinProjects";
    }

    @RequestMapping(value = {"/presentationslot-client"})
    public String presentationClient(Model model, HttpSession session) {
        //String cid = session.getAttribute("user").toString();
        List<GroupEntity> groupEntityList;
        groupEntityList = groupService.getPresentationClient("client1");
        model.addAttribute("groups", groupEntityList);
        return "PresentationSlot-client";
    }

    @RequestMapping(value = {"/studentgroup"})
    public String allgroupAdmin(Model model) {
        List<GroupEntity> groupEntityList;
        List<StudentEntity> studentEntityList;
        groupEntityList = groupService.getAllgroup();
        studentEntityList = groupService.getUngroupStudent();
        model.addAttribute("groups", groupEntityList);
        model.addAttribute("students", studentEntityList);
        return "StudentGroup";
    }

    @PostMapping(value = {"/addgroup"})
    public String addGroup(@RequestParam("id") String id,
                           @RequestParam("s1") String s1,
                           @RequestParam("s2") String s2,
                           @RequestParam("s3") String s3,
                           @RequestParam("s4") String s4,
                           @RequestParam("s5") String s5,
                           @RequestParam("cid") String clientId) {
        groupService.addGroup(id,s1,s2,s3,s4,s5,clientId);
        return "redirect:/studentgroup";
    }

}
