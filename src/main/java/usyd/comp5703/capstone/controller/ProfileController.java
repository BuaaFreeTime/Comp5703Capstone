package usyd.comp5703.capstone.controller;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.multipart.MultipartFile;
        import usyd.comp5703.capstone.entity.StudentEntity;
        import usyd.comp5703.capstone.service.ProfileService;

        import javax.servlet.http.HttpSession;
        import java.util.Map;

@Controller
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @RequestMapping(value = {"/profile"})
    public String profile(Map<String,Object> map, HttpSession session) {
        //String sid = session.getAttribute("user").toString();
        StudentEntity studentEntity = profileService.getStudent("student");
        map.put("name", studentEntity.getName());
        map.put("email", studentEntity.getEmail());
        return "profile";
    }

    @RequestMapping(value = {"/uploadstudentinfo"})
    public String uploadStudent(Map<String,Object> map, HttpSession session) {
        //String sid = session.getAttribute("user").toString();
        return "UploadStudentInfo";
    }

    @PostMapping(value = {"/addstudent"})
    public String addStudent(@RequestParam("sid") String sid,
                             @RequestParam("name") String name,
                             @RequestParam("email") String email,
                             HttpSession session) {
        String semester = session.getAttribute("semester").toString();
        if (semester.equals("2020 Semester 1")) {
            profileService.addStudent(sid, name, email, "", "");
        }
        return "redirect:/uploadstudentinfo";
    }

    @PostMapping(value = {"/addstudentlist"})
    public String addStudentList(@RequestParam("studentfile") MultipartFile file){
        System.out.println(file.getContentType());
        return "redirect:/uploadstudentinfo";
    }

}
