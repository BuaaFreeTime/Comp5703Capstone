package usyd.comp5703.capstone.dao;

import com.google.firebase.database.*;
import org.springframework.stereotype.Repository;
import usyd.comp5703.capstone.entity.GroupEntity;
import usyd.comp5703.capstone.entity.LoginEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Repository
public class LoginDao {
    private static DatabaseReference
            ref =  FirebaseDatabase.getInstance().getReference("/");

    private static DatabaseReference loginRef = ref.child("login");

    // Add group
    public void addStudent(LoginEntity loginEntity){
        DatabaseReference studentChenckRef = loginRef.child("student");
        //studentChenckRef.child(loginEntity.getUsername()).setValueAsync(loginEntity);
    }

    public boolean studentCheck(String username, String password){
        if (username.equals("student") && password.equals("1"))
            return true;
        else return false;
        /*
        final CountDownLatch readData = new CountDownLatch(1);
        final List<LoginEntity> flag = new ArrayList<>();
        final String usernameCheck = username;
        final String passwordCheck = password;
        DatabaseReference studentChenckRef = loginRef.child("student");
        System.out.println(username+" "+password);
        loginRef.orderByChild("id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                //LoginEntity my = dataSnapshot.getValue(LoginEntity.class);

                //System.out.println(loginEntity.getUsername()+" "+loginEntity.getPassword());
                System.out.println(usernameCheck+" "+passwordCheck);

                if (loginEntity.getUsername().equals(username)) {
                    System.out.println(username+" "+password);
                    flag.add(loginEntity);
                    readData.countDown();
                }


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
        if (flag.size()>0) return true;
        else return false;
         */
    }



}
