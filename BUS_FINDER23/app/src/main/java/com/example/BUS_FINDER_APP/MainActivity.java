package com.example.BUS_FINDER_APP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.BUS_FINDER_APP.R;


public class MainActivity extends AppCompatActivity {


    Button getuser,getadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //status bar hide code
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getuser=(Button) findViewById(R.id.getuser);
        getadmin=(Button) findViewById(R.id.getadmin);


        getuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                    startActivity(intent);
                }


        });

        getadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,adminPage.class);
                startActivity(intent);
            }


        });
    }
}


