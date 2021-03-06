package usyd.comp5703.capstone.dao;

import com.google.firebase.database.*;
import org.springframework.stereotype.Repository;
import usyd.comp5703.capstone.entity.ProjectEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Repository
public class ProjectDao {

    private static DatabaseReference
            ref =  FirebaseDatabase.getInstance().getReference("/");

    private static DatabaseReference projectRef = ref.child("project");

    // Add project
    public void addProject(ProjectEntity projectEntity){
        projectRef.child(projectEntity.getId()).setValueAsync(projectEntity);
    }

    //delete project
    public void deleteProject(String pid){
        projectRef.child(pid).removeValueAsync();
    }

    // update
    public void updateGroupClient(String cid){
        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put("1/clientid", "client2");
        hopperUpdates.put("4/clientid", "client2");
        hopperUpdates.put("7/clientid", "client2");
        projectRef.updateChildrenAsync(hopperUpdates);
    }
    public void updateGroupNumber(String pid, String gnum){
        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put(pid+"/gnumber", gnum);
        projectRef.updateChildrenAsync(hopperUpdates);
    }
    public void updateApprove(String pid, String state){
        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put(pid+"/approve", state);
        projectRef.updateChildrenAsync(hopperUpdates);
    }
    public void updateUnit(String pid, String unit){
        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put(pid+"/unit", unit);
        projectRef.updateChildrenAsync(hopperUpdates);
    }
    public void updateName(String pid, String name){
        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put(pid+"/name", name);
        projectRef.updateChildrenAsync(hopperUpdates);
    }
    public void updateType(String pid, String type){
        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put(pid+"/type", type);
        projectRef.updateChildrenAsync(hopperUpdates);
    }
    public void updateDescription(String pid, String description){
        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put(pid+"/description", description);
        projectRef.updateChildrenAsync(hopperUpdates);
    }
    public void updateClientid(String pid, String clientid){
        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put(pid+"/clientid", clientid);
        projectRef.updateChildrenAsync(hopperUpdates);
    }
    public void updateTutor(String pid, String tutor){
        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put(pid+"/tutor", tutor);
        projectRef.updateChildrenAsync(hopperUpdates);
    }


    public ProjectEntity getMyproject(String id){
        final ProjectEntity projectEntity = new ProjectEntity();
        final String projectId = id;
        final CountDownLatch readData = new CountDownLatch(1);
        projectRef.orderByChild("id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                ProjectEntity myProject = dataSnapshot.getValue(ProjectEntity.class);
                if (myProject.getId().equals(projectId)) {
                    projectEntity.setId(myProject.getId());
                    projectEntity.setClient(myProject.getClient());
                    projectEntity.setDescription(myProject.getDescription());
                    projectEntity.setName(myProject.getName());
                    projectEntity.setTutor(myProject.getTutor());
                    projectEntity.setType(myProject.getType());
                    projectEntity.setUnit(myProject.getUnit());
                    projectEntity.setClientid(myProject.getClientid());
                    readData.countDown();
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        try {
            readData.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return projectEntity;
    }

    public List<ProjectEntity> getAllproject(){
        final List<ProjectEntity> projectEntityList = new ArrayList<>();
        final CountDownLatch readData = new CountDownLatch(10);
        projectRef.orderByChild("id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                ProjectEntity projectEntity = dataSnapshot.getValue(ProjectEntity.class);
                projectEntityList.add(projectEntity);
                readData.countDown();
                /*
                if (projectEntity.getGroup().equals(group)) {
                    projectEntityList.add(projectEntity);
                    readData.countDown();
                }*/
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        try {
            readData.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return projectEntityList;
    }

    public List<ProjectEntity> getAllprojectClient(String cid){
        final List<ProjectEntity> projectEntityList = new ArrayList<>();
        final String client = cid;
        final CountDownLatch readData = new CountDownLatch(4);
        projectRef.orderByChild("id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                ProjectEntity projectEntity = dataSnapshot.getValue(ProjectEntity.class);
                if (projectEntity.getClientid().equals(client)) {
                    projectEntityList.add(dataSnapshot.getValue(ProjectEntity.class));
                    readData.countDown();
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        try {
            readData.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return projectEntityList;
    }
}
