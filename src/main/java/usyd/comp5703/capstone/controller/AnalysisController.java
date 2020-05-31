package usyd.comp5703.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import usyd.comp5703.capstone.entity.ProjectEntity;
import usyd.comp5703.capstone.entity.StudentPreferenceEntity;
import usyd.comp5703.capstone.entity.Top5Entity;
import usyd.comp5703.capstone.service.ProjectService;
import usyd.comp5703.capstone.service.StudentPreferenceService;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class AnalysisController {

    @Autowired
    ProjectService projectService;
    @Autowired
    StudentPreferenceService studentPreferenceService;

    @RequestMapping(value = {"/typesofprojects"})
    public String AllGroup(Model model, HttpSession session) {
        int softwarep = 0;
        int softwares = 0;
        int datap = 0;
        int datas = 0;
        int informationp = 0;
        int informations = 0;
        List<ProjectEntity> projectEntityList = projectService.getAllproject();
        List<StudentPreferenceEntity> studentPreferenceEntities = studentPreferenceService.getAllPreference();
        Map<String, String> student = new HashMap<>();
        Map<String, String> tops = new HashMap<>();
        for (ProjectEntity p:projectEntityList) {
            student.put(p.getName(), p.getType());
            if (p.getType().equals("Data Science")) datap++;
            if (p.getType().equals("Information System")) informationp++;
            if (p.getType().equals("Software Engineering")) softwarep++;
        }
        student.put("deny","");
        for (StudentPreferenceEntity s:studentPreferenceEntities) {
            String first = s.getFirst();
            String second = s.getSecond();
            String third = s.getThird();
            if (student.get(first).equals("Data Science")) datas++;
            if (student.get(second).equals("Data Science")) datas++;
            if (student.get(third).equals("Data Science")) datas++;
            if (student.get(first).equals("Information System")) informations++;
            if (student.get(second).equals("Information System")) informations++;
            if (student.get(third).equals("Information System")) informations++;
            if (student.get(first).equals("Software Engineering")) softwares++;
            if (student.get(second).equals("Software Engineering")) softwares++;
            if (student.get(third).equals("Software Engineering")) softwares++;
            String top1 = tops.get(first);
            if (top1==null) tops.put(first, "1");
            else {
                top1 = String.valueOf(Integer.valueOf(top1)+1);
                tops.put(first, top1);
            }
            String top2 = tops.get(second);
            if (top2==null) tops.put(second, "1");
            else {
                top2 = String.valueOf(Integer.valueOf(top2)+1);
                tops.put(second, top2);
            }
            String top3 = tops.get(third);
            if (top3==null) tops.put(third, "1");
            else {
                top3 = String.valueOf(Integer.valueOf(top3)+1);
                tops.put(third, top3);
            }
        }
        List<Map.Entry<String,String>> lstEntry=new ArrayList<>(tops.entrySet());
        Collections.sort(lstEntry,new Comparator<Map.Entry<String,String>>() {
            public int compare(Map.Entry<String, String> o1,
                               Map.Entry<String, String> o2) {
                return Integer.valueOf(o2.getValue()).compareTo(Integer.valueOf(o1.getValue()));
            }
        });
        List<Top5Entity> top5Entities = new ArrayList<>();
        int i = 0;
        for(Map.Entry<String,String> mapping:lstEntry){
            if (i>=5) break;
            if (!mapping.getKey().equals("deny")) {
                i++;
                top5Entities.add(new Top5Entity(mapping.getKey(), mapping.getValue()));
            }
        }
        model.addAttribute("softwareP", softwarep);
        model.addAttribute("softwareS", softwares);
        model.addAttribute("dataP", datap);
        model.addAttribute("dataS", datas);
        model.addAttribute("informationP", informationp);
        model.addAttribute("informationS", informations);
        model.addAttribute("top5", top5Entities);
        return "TypesofProjects";
    }
}
