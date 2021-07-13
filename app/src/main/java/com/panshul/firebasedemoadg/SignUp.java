package com.panshul.firebasedemoadg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity {

    EditText name,email,phone,password;
    Button submit;
    DatabaseReference myref;
    FirebaseAuth mauth;
    List<UserData> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findViewByIds();
        list = new ArrayList<>();
        myref = FirebaseDatabase.getInstance().getReference("Users");
        mauth = FirebaseAuth.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mauth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            String uid =mauth.getCurrentUser().getUid();
                            UserData user = new UserData(email.getText().toString(),uid,phone.getText().toString(),name.getText().toString());
                            try {
                                myref.child(uid).setValue(user);
                            }
                            catch (Exception e){
                                Toast.makeText(SignUp.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(SignUp.this, "Signup failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        fetchData();
    }
    public void findViewByIds(){
        name=findViewById(R.id.signupName);
        email=findViewById(R.id.signupEmail);
        phone=findViewById(R.id.signupPhone);
        password=findViewById(R.id.signupPassword);
        submit=findViewById(R.id.signupSubmit);
    }
    public void fetchData(){
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                list.clear();
                Log.i("Hello",snapshot.getValue().toString());
                                for (DataSnapshot ds :snapshot.getChildren()){
                    UserData userData = ds.getValue(UserData.class);
                    list.add(userData);
                }

                for (UserData data : list){
                    Log.i("Name",data.getName());
                    Log.i("Phone",data.getPhone());
                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }
}