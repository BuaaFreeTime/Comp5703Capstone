package usyd.comp5703.capstone.dao;

import com.google.firebase.database.*;
import org.springframework.stereotype.Repository;
import usyd.comp5703.capstone.entity.GroupEntity;
import usyd.comp5703.capstone.entity.SemesterEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Repository
public class SemesterDao {
    private static DatabaseReference
            ref =  FirebaseDatabase.getInstance().getReference("/");

    private static DatabaseReference semesterRef = ref.child("semester");

    // Add semester
    public void addSemester(SemesterEntity semesterEntity){
        semesterRef.child(semesterEntity.getName()).setValueAsync(semesterEntity);
    }

    //Get All
    public List<SemesterEntity> getAllSemester(){
        final List<SemesterEntity> semesters = new ArrayList<>();
        final CountDownLatch readData = new CountDownLatch(3);
        semesterRef.orderByChild("name").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                SemesterEntity semesterEntity = dataSnapshot.getValue(SemesterEntity.class);
                semesters.add(semesterEntity);
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
        return semesters;
    }
}
