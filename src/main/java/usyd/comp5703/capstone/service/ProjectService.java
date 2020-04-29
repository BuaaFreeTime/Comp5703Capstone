package usyd.comp5703.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usyd.comp5703.capstone.dao.GroupDao;
import usyd.comp5703.capstone.dao.ProjectDao;
import usyd.comp5703.capstone.entity.GroupEntity;
import usyd.comp5703.capstone.entity.ProjectEntity;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectDao projectDao;

    public ProjectEntity getMyproject(String sid) {
        /*
        myprojectEntity.setUnit("Comp5703");
        myprojectEntity.setType("Software Engineering");
        myprojectEntity.setName("CAPSTONE PM");
        myprojectEntity.setDescription("A capstone project management system");
        myprojectEntity.setClient("Dr.Wu xi");
        myprojectEntity.setTutor("Omid");
         */
       ProjectEntity myprojectEntity = projectDao.getMyproject(sid);
       return myprojectEntity;
    }

    public List<ProjectEntity> getAllproject() {
        List<ProjectEntity> projectEntityList;
        projectEntityList = projectDao.getAllproject();
        return projectEntityList;
    }
}
