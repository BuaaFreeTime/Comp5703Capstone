package usyd.comp5703.capstone.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Repository;
import usyd.comp5703.capstone.entity.ClientEntity;
import usyd.comp5703.capstone.entity.StudentEntity;

@Repository
public class ClientDao {

    private static DatabaseReference
            ref =  FirebaseDatabase.getInstance().getReference("/");

    private static DatabaseReference studentRef = ref.child("client");

        // Add client
        public void addClient(ClientEntity clientEntity){
            studentRef.child(clientEntity.getCid()).setValueAsync(clientEntity);
        }
}
