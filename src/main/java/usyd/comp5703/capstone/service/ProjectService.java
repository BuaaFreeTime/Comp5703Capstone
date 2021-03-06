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

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectDao projectDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    ClientDao clientDao;

    public void deleteProject(String pid){
        projectDao.deleteProject(pid);
    }

    public void updateProject(String pid,String unit, String type, String name, String description, String gnum) {
        if (!unit.equals("")) projectDao.updateUnit(pid, unit);
        if (!type.equals("")) projectDao.updateType(pid, type);
        if (!name.equals("")) projectDao.updateName(pid, name);
        if (!description.equals("")) projectDao.updateDescription(pid, description);
        if (!gnum.equals("")) projectDao.updateGroupNumber(pid,gnum);
        System.out.println("Client update project:"+pid);
    }

    public void updateProject(String pid,String unit, String type, String name, String description, String gnum, String cid, String tutor) {
        if (!unit.equals("")) projectDao.updateUnit(pid, unit);
        if (!type.equals("")) projectDao.updateType(pid, type);
        if (!name.equals("")) projectDao.updateName(pid, name);
        if (!description.equals("")) projectDao.updateDescription(pid, description);
        if (!gnum.equals("")) projectDao.updateGroupNumber(pid,gnum);
        if (!cid.equals("")) projectDao.updateClientid(pid,cid);
        if (!tutor.equals("")) projectDao.updateTutor(pid,tutor);
        System.out.println("Admin update project:"+pid);
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

    public List<ProjectEntity> getAllApproveProjectClient(String cid) {
        //projectDao.updateGroupClient(cid);
        List<ProjectEntity> projectEntityList;
        projectEntityList = projectDao.getAllprojectClient(cid);
        List<ProjectEntity> projectEntityApproveList = new ArrayList<>();
        for (ProjectEntity i:projectEntityList) {
            if (i.getApprove().equals("yes")) projectEntityApproveList.add(i);
        }
        return projectEntityApproveList;
    }

    public void addProject(String unit, String type, String name, String description, String gunm, String clientid){
        List<ProjectEntity> projectEntityList;
        projectEntityList = projectDao.getAllproject();
        ClientEntity clientEntity = clientDao.getClient(clientid);
        ProjectEntity newProject = new ProjectEntity();
        newProject.setUnit(unit);
        newProject.setType(type);
        if (unit.equals("Comp5703")) newProject.setGroup("yes");
        else newProject.setGroup("no");
        newProject.setName(name);
        newProject.setDescription(description);
        newProject.setTutor("not yet set tutor");
        newProject.setClientid(clientid);
        newProject.setClient(clientEntity.getName());
        if (!gunm.equals("")) newProject.setGnumber(gunm);
        newProject.setId(String.valueOf(projectEntityList.size()+1));
        System.out.println("Client add project:"+newProject.getId());
        projectDao.addProject(newProject);
    }

    public void addProject(String unit, String type, String name, String description, String gunm, String clientid, String tutor){
        List<ProjectEntity> projectEntityList;
        projectEntityList = projectDao.getAllproject();
        ClientEntity clientEntity = clientDao.getClient(clientid);
        ProjectEntity newProject = new ProjectEntity();
        newProject.setUnit(unit);
        if (unit.equals("Comp5703")) newProject.setGroup("yes");
        else newProject.setGroup("no");
        newProject.setType(type);
        newProject.setName(name);
        newProject.setDescription(description);
        newProject.setTutor(tutor);
        newProject.setClientid(clientid);
        newProject.setClient(clientEntity.getName());
        if (!gunm.equals("")) newProject.setGnumber(gunm);
        newProject.setId(String.valueOf(projectEntityList.size()+1));
        System.out.println("admin add project:"+newProject.getId());
        projectDao.addProject(newProject);
    }

}
