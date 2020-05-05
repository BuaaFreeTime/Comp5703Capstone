package usyd.comp5703.capstone.dao;

import com.google.firebase.database.*;
import org.springframework.stereotype.Repository;
import usyd.comp5703.capstone.entity.GroupEntity;
import usyd.comp5703.capstone.entity.ProjectEntity;
import usyd.comp5703.capstone.entity.StudentEntity;

import java.util.concurrent.CountDownLatch;

@Repository
public class StudentDao {

    private static DatabaseReference
            ref =  FirebaseDatabase.getInstance().getReference("/");

    private static DatabaseReference studentRef = ref.child("student");

    // Add student
    public void addStudent(StudentEntity studentEntity){
        studentRef.child(studentEntity.getSid()).setValueAsync(studentEntity);
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
                    studentEntity.setAge(student.getAge());
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
