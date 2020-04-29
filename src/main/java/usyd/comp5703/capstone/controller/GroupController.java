package usyd.comp5703.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import usyd.comp5703.capstone.entity.GroupEntity;
import usyd.comp5703.capstone.service.GroupService;

import java.util.List;
import java.util.Map;

@Controller
public class GroupController {

    @Autowired  //与service层进行交互
    private GroupService groupService;

    @RequestMapping(value = {"/allgroup"})
    public String allgroupStudent(Model model) {
        List<GroupEntity> groupEntityList;
        groupEntityList = groupService.getAllgroup();
        model.addAttribute("groups", groupEntityList);
        return "AllGroup";
    }

    @RequestMapping(value = {"/mygroup"})
    public String mygroupStudent(Model model) {
        GroupEntity groupEntity = groupService.getMygroup(480);
        model.addAttribute("groups", groupEntity);
        return "MyGroup";
    }

    @RequestMapping(value = {"/presentationslot"})
    public String studentPresentationslot( Map<String,Object> map) {
        return "PresentationSlot";

    }

}
