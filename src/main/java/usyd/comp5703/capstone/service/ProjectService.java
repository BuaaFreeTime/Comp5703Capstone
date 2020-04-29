package usyd.comp5703.capstone.service;

import org.springframework.stereotype.Service;
import usyd.comp5703.capstone.entity.ProjectEntity;

import java.util.List;

@Service
public class ProjectService {

    public static ProjectEntity getMyproject(int sid) {
        ProjectEntity myprojectEntity = new ProjectEntity();
        myprojectEntity.setUnit("Comp5703");
        myprojectEntity.setType("Software Engineering");
        myprojectEntity.setName("CAPSTONE PM");
        myprojectEntity.setDescription("A capstone project management system");
        myprojectEntity.setClient("Dr.Wu xi");
        myprojectEntity.setTutor("Omid");
        return myprojectEntity;
    }
    /*
    public static List<ProjectEntity> getAllproject() {

    }*/
}
