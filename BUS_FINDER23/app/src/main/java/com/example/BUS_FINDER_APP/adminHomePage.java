package com.example.BUS_FINDER_APP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class adminHomePage extends AppCompatActivity {
    EditText bus_id,bus_name,destination,source,timing,st_ticket,eld_ticket;
    Button insert, view, delete,update;
//    DBHelper DB;

    //    TextView txtbus_id,txtbus_name,txtdestination,;
//    String rtvFullName,rtvEmail,rtvMobile,loggedEmail;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    DatabaseReference busdetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        //status bar hide code
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth=FirebaseAuth.getInstance();
//        db=FirebaseFirestore.getInstance();


        bus_id= findViewById(R.id.bus_id);
        bus_name= findViewById(R.id.bus_name);
        destination = findViewById(R.id.dest);
        source = findViewById(R.id.source);
        timing = findViewById(R.id.timing);
        st_ticket = findViewById(R.id.std_tckt);
        eld_ticket = findViewById(R.id.eld_ticket );
        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnView);
        delete = findViewById(R.id.btnDelete);
        //update = findViewById(R.id.btnUpdate);


        busdetails = FirebaseDatabase.getInstance().getReference().child("bus_details");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String  searchdest =destination.getText().toString().trim();
//                String  searchsource =source.getText().toString().trim();
              //String  searchbus_id=bus_id.getText().toString().trim();
                //Intent intent= (new Intent(adminHomePage.this, displaydata.class));
                Intent intent= (new Intent(adminHomePage.this,adminView.class));
//                intent.putExtra("destination",searchdest);
//                intent.putExtra("source",searchsource);
               //intent.putExtra("bus_id",searchbus_id);
                startActivity(intent);
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertBusDetails();

            }
        });


//            delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String bus_idTXT = bus_id.getText().toString();
//                    deleteBusDetails(bus_idTXT);
//
//                    //String bus_idTXT = bus_id.getText().toString();
//
//                    //Boolean checkdeletedata  = db.deleteuserdata(bus_idTXT);
////                    if(checkdeletedata==true)
////                    {
////                        Toast.makeText(adminHomePage.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
////                    }
////                    else
////                    {
////                        Toast.makeText(adminHomePage.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
////                    }
//
//                }
//            });


    }

    private void deleteBusDetails(String bus_idTXT) {

        DatabaseReference drBus=FirebaseDatabase.getInstance().getReference("bus_details").child(bus_idTXT);
        drBus.removeValue();
        Toast.makeText(adminHomePage.this, "Entry Deleted", Toast.LENGTH_SHORT).show();

    }

    //
    private void insertBusDetails(){

        String bus_idTXT = bus_id.getText().toString();
        String bus_nameTXT = bus_name.getText().toString();
        String destinationTXT = destination.getText().toString();
        String sourceTXT = source.getText().toString();
        String timingTXT = timing.getText().toString();
        String std_tktTXT = st_ticket.getText().toString();
        String eld_tktTXT = eld_ticket.getText().toString();

        bus_details bus_details=new bus_details(bus_idTXT,bus_nameTXT,destinationTXT,sourceTXT,timingTXT,std_tktTXT,eld_tktTXT);

//        busdetails.addValueEventListener()
////
        busdetails.child(bus_idTXT).setValue(bus_details);

        //busdetails.push().setValue(bus_details);
        Toast.makeText(adminHomePage.this, "Data Inserted", Toast.LENGTH_SHORT).show();
    }
    public void logout(View view) {
        //FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
//eld_tcktTXT

