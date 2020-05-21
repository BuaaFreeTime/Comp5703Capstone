package usyd.comp5703.capstone.dao;

import com.google.firebase.database.*;
/*import com.sun.xml.internal.ws.api.ha.StickyFeature;*/
import org.springframework.stereotype.Repository;
import usyd.comp5703.capstone.entity.GroupEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Repository
public class GroupDao {

    private static DatabaseReference
            ref =  FirebaseDatabase.getInstance().getReference("/");

    private static DatabaseReference groupRef = ref.child("group");

    // update time
    public void updatePresentation(String id, String date){
        Map<String, Object> hopperUpdates = new HashMap<>();

        hopperUpdates.put(id+"/presentation", date);
        groupRef.updateChildrenAsync(hopperUpdates);
    }

    // update client
    public void updateClient(String groupId, String cid){
        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put("2/clientid", "client1");
        hopperUpdates.put("4/clientid", "client1");
        hopperUpdates.put("6/clientid", "client2");
        groupRef.updateChildrenAsync(hopperUpdates);
    }

    // update client
    public void updateMarks(String groupId, String marks){
        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put(groupId+"/marks", marks);
        groupRef.updateChildrenAsync(hopperUpdates);
    }

    // Get all group's name
    public List<String> getMarkingName(String cid){
        final List<String> groups = new ArrayList<>();
        final String client = cid;
        final CountDownLatch readData = new CountDownLatch(1);
        groupRef.orderByChild("id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GroupEntity groupEntity = dataSnapshot.getValue(GroupEntity.class);
                if (groupEntity.getClientid().equals(client)) {
                    String name = groupEntity.getId();
                    groups.add(name);
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
        return groups;
    }

    // Get all group's time
    public List<GroupEntity> getPresentationClient(String cid){
        final List<GroupEntity> groups = new ArrayList<>();
        final String client = cid;
        final CountDownLatch readData = new CountDownLatch(1);
        groupRef.orderByChild("id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GroupEntity groupEntity = dataSnapshot.getValue(GroupEntity.class);
                if (groupEntity.getClientid().equals(client)) {
                    GroupEntity group = dataSnapshot.getValue(GroupEntity.class);
                    String [] time = group.getPresentation().split("T");
                    if (time.length==1) {group.setPresentation("Not yet set");}
                    else {group.setPresentation(time[0]+ " "+time[1]);}
                    groups.add(group);
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
        return groups;
    }

    // Get all student in project
    public List<String> getStudentInProject(String cid, final String ppid){
        final List<String> students = new ArrayList<>();
        final String client = cid;
        final String approve = ppid;
        final CountDownLatch readData = new CountDownLatch(1);
        groupRef.orderByChild("id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GroupEntity groupEntity = dataSnapshot.getValue(GroupEntity.class);
                if (groupEntity.getClientid().equals(client) && approve.contains(groupEntity.getProjectid())) {
                    students.add(groupEntity.getStudent1());
                    students.add(groupEntity.getStudent2());
                    students.add(groupEntity.getStudent3());
                    students.add(groupEntity.getStudent4());
                    students.add(groupEntity.getStudent5());
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
        return students;
    }

    // Add group
    public void addGroup(GroupEntity groupEntity){
        groupRef.child(groupEntity.getId()).setValueAsync(groupEntity);
    }

    // Get all group
    public List<GroupEntity> getAllgroup(){
        final List<GroupEntity> groupEntityList = new ArrayList<>();
        final CountDownLatch readData = new CountDownLatch(2);
        groupRef.orderByChild("id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                groupEntityList.add(dataSnapshot.getValue(GroupEntity.class));

                readData.countDown();
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
        return groupEntityList;
    }

    public GroupEntity getMygroup(String id){
        final GroupEntity groupEntity = new GroupEntity();
        final String groupId = id;
        System.out.println(id);
        final CountDownLatch readData = new CountDownLatch(1);
        groupRef.orderByChild("id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GroupEntity myGroup = dataSnapshot.getValue(GroupEntity.class);
                if (myGroup.getId().equals(groupId)) {
                    groupEntity.setId(myGroup.getId());
                    groupEntity.setStudent1(myGroup.getStudent1());
                    groupEntity.setStudent2(myGroup.getStudent2());
                    groupEntity.setStudent3(myGroup.getStudent3());
                    groupEntity.setStudent4(myGroup.getStudent4());
                    groupEntity.setStudent5(myGroup.getStudent5());
                    groupEntity.setMarks(myGroup.getMarks());
                    groupEntity.setPresentation(myGroup.getPresentation());
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
        return groupEntity;
    }

}
