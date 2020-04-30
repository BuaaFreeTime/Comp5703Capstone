package usyd.comp5703.capstone.dao;

import com.google.firebase.database.*;
import org.springframework.stereotype.Repository;
import usyd.comp5703.capstone.entity.GroupEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Repository
public class GroupDao {

    private static DatabaseReference
            ref =  FirebaseDatabase.getInstance().getReference("/");

    private static DatabaseReference groupRef = ref.child("group");

    // Add group
    public void addGroup(GroupEntity groupEntity){
        groupRef.child(groupEntity.getId()).setValueAsync(groupEntity);
    }

    // Get all group
    public List<GroupEntity> getAllgroup(){
        final List<GroupEntity> groupEntityList = new ArrayList<>();
        final CountDownLatch readData = new CountDownLatch(1);
        groupRef.orderByChild("id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                readData.countDown();
                groupEntityList.add(dataSnapshot.getValue(GroupEntity.class));
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
