package com.panshul.firebasedemoadg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button submit;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviewbyIds();
        mauth=FirebaseAuth.getInstance();
        if (mauth.getCurrentUser()!=null){
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmpty()){
                    mauth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Invalid email id or password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(LoginActivity.this, "Please enter email id or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void findviewbyIds(){
        email=findViewById(R.id.loginEmail);
        password=findViewById(R.id.loginPassword);
        submit=findViewById(R.id.loginSubmit);
    }
    public boolean checkEmpty(){
        if (email.getText().toString().length()==0){
            return false;
        }
        else if (password.getText().toString().length()==0){
            return false;
        }
        return true;
    }
}