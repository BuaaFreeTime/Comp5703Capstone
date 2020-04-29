package usyd.comp5703.capstone.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class StudentDao {

    DatabaseReference ref =  FirebaseDatabase.getInstance().getReference("server/saving-data/fireblog");
    DatabaseReference usersRef = ref.child("users");


}
