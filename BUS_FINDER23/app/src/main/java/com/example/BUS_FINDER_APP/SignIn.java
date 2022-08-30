package com.example.BUS_FINDER_APP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class SignIn extends AppCompatActivity {
//    TextView txtSignUp;

    TextView txtSignIn;
    Button signInbtn;
    EditText edtEmail,edtPassword;
    ProgressBar progressBar;
    String txtEmail,txtPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        txtSignIn=findViewById(R.id.txtsignIn);
        edtEmail=findViewById(R.id.email2);
        edtPassword=findViewById(R.id.Signin_password);
        progressBar=findViewById(R.id.signinprogressbar);
        signInbtn=findViewById(R.id.SignInbtn);
        // FIREBASE INITIALISE
        mAuth = FirebaseAuth.getInstance();

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SignIn.this,SignUp.class);
                startActivity(intent);
//                finish();
            }
        });


        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Home_page.class));
            finish();
        }

        signInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtEmail =edtEmail.getText().toString().trim();

                txtPassword =edtPassword.getText().toString().trim();
                if(!TextUtils.isEmpty(txtEmail)){
                    if(txtEmail.matches(emailPattern)){
                        if(!TextUtils.isEmpty(txtPassword)){
                            SignInUser();
                        }else{
                            edtPassword.setError("password field can't be empty");
                        }
                    }else{
                        edtEmail.setError("Enter a valid Email Address");
                    }
                }else{
                    edtEmail.setError("Email field can't be empty");
                }
//                Intent intent= new Intent(SignIn.this,SignUp.class);
//                startActivity(intent);
////                finish();
            }
        });
    }

    private void SignInUser() {
        progressBar.setVisibility(View.VISIBLE);
        signInbtn.setVisibility(View.INVISIBLE);
        mAuth.signInWithEmailAndPassword(txtEmail,txtPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(SignIn.this, "Login Succesfull", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(SignIn.this,Home_page.class);
                startActivity(intent);
                // finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignIn.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                signInbtn.setVisibility(View.VISIBLE);
            }
        });
    }
}

