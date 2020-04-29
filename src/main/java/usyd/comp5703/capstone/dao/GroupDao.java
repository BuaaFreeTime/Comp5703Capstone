package usyd.comp5703.capstone.dao;

import com.google.firebase.database.*;
import usyd.comp5703.capstone.entity.GroupEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupDao {

    private static DatabaseReference
            ref =  FirebaseDatabase.getInstance().getReference("/");

    private static DatabaseReference groupRef = ref.child("group");

    // Add group
    public static void addGroup(GroupEntity groupEntity){
        groupRef.child(groupEntity.getId()).setValueAsync(groupEntity);
    }

    // Get all group
    public static List<GroupEntity> getAllgroup(){
        final List<GroupEntity> groupEntityList;
        groupEntityList = new ArrayList<>();
        groupRef.orderByChild("id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GroupEntity groupFromData = dataSnapshot.getValue(GroupEntity.class);
                System.out.println("Group "+dataSnapshot.getKey());
                GroupEntity groupEntity = new GroupEntity("2");
                groupEntityList.add(groupEntity);
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
        System.out.println("Group xxxxxx"+groupEntityList.get(1).getId());
        return groupEntityList;
    }

}
