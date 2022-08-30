package com.example.BUS_FINDER_APP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class displaydata extends AppCompatActivity {
//    EditText destination,source;
//

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaydata);
        //status bar hide code
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent=getIntent();

            String searchdest=intent.getStringExtra("destination");
            String searchbus_id=intent.getStringExtra("bus_id");
        //Toast.makeText(displaydata.this,"hellooooooooooooooo"+searchdest, Toast.LENGTH_SHORT).show();

            String searchsource=intent.getStringExtra("source");
        //Toast.makeText(displaydata.this,"welcome"+searchsource, Toast.LENGTH_SHORT).show();


        recyclerView=findViewById(R.id.displaydata);
        //database= FirebaseDatabase.getInstance().getReference("bus_details");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        myAdapter=new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);
        database= FirebaseDatabase.getInstance().getReference("bus_details");
        //Query query=FirebaseDatabase.getInstance().getReference("bus_details").orderByChild("destination").equalTo(searchdest);
//        .orderByChild("source").equalTo("Karkala")
        database.addValueEventListener(new ValueEventListener() {
        //query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    User user=dataSnapshot.getValue(User.class);
                    if((user.getSource().equals(searchsource) && user.getDestination().equals(searchdest))){
                        list.add(user);
                        //
                        //Toast.makeText(displaydata.this,"hellooooooooooooooo"+searchdest, Toast.LENGTH_SHORT).show();
                    }
//                    else if(user.getBus_id().equals(searchbus_id)){
//                       list.add(user);
////                        //Toast.makeText(displaydata.this,"hellooooooooooooooo"+searchdest, Toast.LENGTH_SHORT).show();
//                    }

                    //list.add(user);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}