package usyd.comp5703.capstone.controller;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import usyd.comp5703.capstone.entity.StudentEntity;
        import usyd.comp5703.capstone.service.ProfileService;

        import javax.servlet.http.HttpSession;
        import java.util.Map;

@Controller
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @RequestMapping(value = {"/profile"})
    public String Profile(Map<String,Object> map, HttpSession session) {
        //String sid = session.getAttribute("user").toString();
        StudentEntity studentEntity = profileService.getStudent("student");
        map.put("name", studentEntity.getName());
        map.put("age", studentEntity.getAge());
        map.put("email", studentEntity.getEmail());
        return "profile";
    }
}
