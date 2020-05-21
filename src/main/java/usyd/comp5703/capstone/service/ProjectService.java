package usyd.comp5703.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import usyd.comp5703.capstone.dao.ClientDao;
import usyd.comp5703.capstone.dao.GroupDao;
import usyd.comp5703.capstone.dao.ProjectDao;
import usyd.comp5703.capstone.dao.StudentDao;
import usyd.comp5703.capstone.entity.ClientEntity;
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
    @Autowired
    ClientDao clientDao;

    public void updateGnum(String pid, String gnum) {
        projectDao.updateGroupNumber(pid,gnum);
    }
    public void updateApprove(String pid) {
        String state = "yes";
        projectDao.updateApprove(pid,state);
    }
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
        String pid = studentEntity.getProjectId();
        if (pid.equals("")) return null;
        ProjectEntity myprojectEntity = projectDao.getMyproject(pid);
        return myprojectEntity;
    }

    public List<ProjectEntity> getAllproject() {
        List<ProjectEntity> projectEntityList;
        projectEntityList = projectDao.getAllproject();
        return projectEntityList;
    }

    public List<ProjectEntity> getAllProjectClient(String cid) {
        //projectDao.updateGroupClient(cid);
        List<ProjectEntity> projectEntityList;
        projectEntityList = projectDao.getAllprojectClient(cid);
        return projectEntityList;
    }

    public void addProject(String unit, String type, String name, String description, String clientid, String tutor){
        List<ProjectEntity> projectEntityList;
        projectEntityList = projectDao.getAllproject();
        ClientEntity clientEntity = clientDao.getClient(clientid);
        ProjectEntity newProject = new ProjectEntity();
        newProject.setUnit(unit);
        newProject.setType(type);
        newProject.setName(name);
        newProject.setDescription(description);
        newProject.setTutor(tutor);
        newProject.setClientid(clientid);
        newProject.setClient(clientEntity.getName());
        newProject.setId(String.valueOf(projectEntityList.size()+1));
        projectDao.addProject(newProject);
    }


}
