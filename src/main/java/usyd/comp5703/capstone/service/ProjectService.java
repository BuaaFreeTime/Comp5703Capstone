package usyd.comp5703.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usyd.comp5703.capstone.dao.GroupDao;
import usyd.comp5703.capstone.dao.ProjectDao;
import usyd.comp5703.capstone.dao.StudentDao;
import usyd.comp5703.capstone.entity.GroupEntity;
import usyd.comp5703.capstone.entity.ProjectEntity;
import usyd.comp5703.capstone.entity.StudentEntity;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectDao projectDao;
    @Autowired
    StudentDao studentDao;

    public ProjectEntity getMyproject(String sid) {
        /*
        myprojectEntity.setUnit("Comp5703");
        myprojectEntity.setType("Software Engineering");
        myprojectEntity.setName("CAPSTONE PM");
        myprojectEntity.setDescription("A capstone project management system");
        myprojectEntity.setClient("Dr.Wu xi");
        myprojectEntity.setTutor("Omid");
         */
        StudentEntity studentEntity = studentDao.getStudent(sid);
        ProjectEntity myprojectEntity = projectDao.getMyproject(studentEntity.getProjectId());
        return myprojectEntity;
    }

    public List<ProjectEntity> getAllproject() {
        List<ProjectEntity> projectEntityList;
        projectEntityList = projectDao.getAllproject();
        return projectEntityList;
    }


}
