package usyd.comp5703.capstone.dao;

import com.google.firebase.database.*;
import org.springframework.stereotype.Repository;
import usyd.comp5703.capstone.entity.ClientEntity;
import usyd.comp5703.capstone.entity.StudentEntity;

import java.util.concurrent.CountDownLatch;

@Repository
public class ClientDao {

    private static DatabaseReference
            ref =  FirebaseDatabase.getInstance().getReference("/");

    private static DatabaseReference studentRef = ref.child("client");

    // Add client
    public void addClient(ClientEntity clientEntity){
        studentRef.child(clientEntity.getCid()).setValueAsync(clientEntity);
    }

    public ClientEntity getClient(String cid) {
        final ClientEntity clientEntity = new ClientEntity();
        final String clientId = cid;
        final CountDownLatch readData = new CountDownLatch(1);
        studentRef.orderByChild("cid").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                ClientEntity client = dataSnapshot.getValue(ClientEntity.class);
                if (client.getCid().equals(clientId)) {
                    clientEntity.setCid(client.getCid());
                    clientEntity.setName(client.getName());
                    clientEntity.setEmail(client.getEmail());
                    clientEntity.setAge(client.getAge());
                    clientEntity.setSemester(client.getSemester());
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
        return clientEntity;
    }

}
