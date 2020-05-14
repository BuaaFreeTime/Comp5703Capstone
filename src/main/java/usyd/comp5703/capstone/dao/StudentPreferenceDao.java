package usyd.comp5703.capstone.dao;

import com.google.firebase.database.*;
import org.springframework.stereotype.Repository;
import usyd.comp5703.capstone.entity.GroupEntity;
import usyd.comp5703.capstone.entity.StudentPreferenceEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Repository
public class StudentPreferenceDao {
    private static DatabaseReference
            ref =  FirebaseDatabase.getInstance().getReference("/");

    private static DatabaseReference preferenceRef = ref.child("preference");

    // Add student preference
    public void addStudentPreference(StudentPreferenceEntity studentEntity){
        preferenceRef.child(studentEntity.getSid()).setValueAsync(studentEntity);
    }

    public List<StudentPreferenceEntity> getAllPreference(){
        final List<StudentPreferenceEntity> studentPreferenceEntityList = new ArrayList<>();
        final CountDownLatch readData = new CountDownLatch(50);
        preferenceRef.orderByChild("sid").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                studentPreferenceEntityList.add(dataSnapshot.getValue(StudentPreferenceEntity.class));
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
        return studentPreferenceEntityList;
    }
}
