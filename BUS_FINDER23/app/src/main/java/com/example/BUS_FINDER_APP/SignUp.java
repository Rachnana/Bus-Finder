package com.example.BUS_FINDER_APP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
TextView txtSignUp;
Button signUpbtn;
EditText edtfullName,edtEmail,edtMobile,edtPassword,edtConfirmPassword;
ProgressBar progressBar;
String txtfullName,txtEmail,txtMobile,txtPassword,txtConfirmPassword;
String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
private FirebaseAuth mAuth;
private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        txtSignUp=findViewById(R.id.txtsignUp);
        signUpbtn=findViewById(R.id.SignUpbtn);
        edtfullName=findViewById(R.id.fullName);
        edtEmail=findViewById(R.id.email);
        edtMobile=findViewById(R.id.number);
        edtPassword=findViewById(R.id.password);
        edtConfirmPassword=findViewById(R.id.confirm_password);
        progressBar=findViewById(R.id.progressbar);
// FIREBASE INITIALISE
        mAuth = FirebaseAuth.getInstance();
        //Firebase firestore initailisation
         db = FirebaseFirestore.getInstance();
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SignUp.this,SignIn.class);
                startActivity(intent);
//                finish();
            }
        });

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Home_page.class));
            finish();
        }

        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtfullName =edtfullName.getText().toString();
                txtEmail =edtEmail.getText().toString().trim();
                txtMobile =edtMobile.getText().toString().trim();
                txtPassword =edtPassword.getText().toString().trim();
                txtConfirmPassword =edtConfirmPassword.getText().toString().trim();
                if(!TextUtils.isEmpty(txtfullName)){
                    if(!TextUtils.isEmpty(txtEmail)){
                        if(txtEmail.matches(emailPattern)){
                            if(!TextUtils.isEmpty(txtMobile)){
                                if(txtMobile.length()==10){
                                    if(!TextUtils.isEmpty(txtPassword)){
                                        if(!TextUtils.isEmpty(txtConfirmPassword)){
                                            if(txtConfirmPassword.equals(txtPassword)){
                                                signUpUser();
                                            }else{
                                                edtConfirmPassword.setError("Confirm Password and Password shoild be same");
                                            }

                                        }else{
                                            edtConfirmPassword.setError("Confirm Password field can't be empty");
                                        }
                                    }else{
                                        edtPassword.setError("Password field can't be empty");
                                    }

                                }else{
                                    edtMobile.setError("Enter a valid Mobile Number");
                                }
                            }else{
                                edtMobile.setError("Mobile Number can't be empty");
                            }
                        }else{
                            edtEmail.setError("Enter a valid Email Address");
                        }
                    }
                    else{
                        edtEmail.setError("Email field can't be empty");
                    }
                }
                else{
                    edtfullName.setError("Full Name field can't be empty");
                }

//                finish();
            }
        });
    }

  private void signUpUser() {
        progressBar.setVisibility(View.VISIBLE);
        signUpbtn.setVisibility(View.INVISIBLE);
        mAuth.createUserWithEmailAndPassword(txtEmail,txtPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                Map<String, Object> user = new HashMap<>();
                user.put("FullName", txtfullName);
                user.put("Email", txtEmail);
                user.put("MobileNumber", txtMobile);

                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(SignUp.this, "Data  Stored succesfully", Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(SignUp.this,SignIn.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUp.this, "Error-"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUp.this,"Error"+e.getMessage(),Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                signUpbtn.setVisibility(View.VISIBLE);
            }
        });
  }
}