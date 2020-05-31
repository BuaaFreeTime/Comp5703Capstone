package usyd.comp5703.capstone.dao;

import com.google.firebase.database.*;
import org.springframework.stereotype.Repository;
import usyd.comp5703.capstone.entity.StudentEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Repository
public class StudentDao {

    private static DatabaseReference
            ref =  FirebaseDatabase.getInstance().getReference("/");

    private static DatabaseReference studentRef = ref.child("student");

    // update item
    public void updateStudentGid(String id, String gid){
        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put(id+"/groupId", gid);
        studentRef.updateChildrenAsync(hopperUpdates);
    }

    // update item
    public void updateStudentPid(String id, String pid){
        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put(id+"/projectId", pid);
        studentRef.updateChildrenAsync(hopperUpdates);
    }

    // Add student
    public void addStudent(StudentEntity studentEntity){
        studentRef.child(studentEntity.getSid()).setValueAsync(studentEntity);
    }

    public List<StudentEntity> getAllStudent() {
        final List<StudentEntity> studentEntityList = new ArrayList<>();
        final CountDownLatch readData = new CountDownLatch(50);
        studentRef.orderByChild("sid").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                studentEntityList.add(dataSnapshot.getValue(StudentEntity.class));
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
        return studentEntityList;
    }

    public StudentEntity getStudent(String id) {
        final StudentEntity studentEntity = new StudentEntity();
        final String studentId = id;
        final CountDownLatch readData = new CountDownLatch(1);
        studentRef.orderByChild("sid").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                StudentEntity student = dataSnapshot.getValue(StudentEntity.class);
                if (student.getSid().equals(studentId)) {
                    studentEntity.setSid(student.getSid());
                    studentEntity.setProjectId(student.getProjectId());
                    studentEntity.setGroupId(student.getGroupId());
                    studentEntity.setName(student.getName());
                    studentEntity.setEmail(student.getEmail());
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
        return studentEntity;
    }
}
