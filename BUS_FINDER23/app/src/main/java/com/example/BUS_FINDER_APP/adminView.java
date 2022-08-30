package com.example.BUS_FINDER_APP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class adminView extends AppCompatActivity {
    EditText bus_id,bus_name,destination,source,timing,st_ticket,eld_ticket;
    Button insert, view, delete;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    DatabaseReference busdetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);
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

        //busdetails = FirebaseDatabase.getInstance().getReference().child(" ");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  searchbus_id=bus_id.getText().toString().trim();
//                String  searchdest =destination.getText().toString().trim();
//                String  searchsource =source.getText().toString().trim();
                Intent intent= (new Intent(adminView.this, displaydataadmin.class));
//                intent.putExtra("destination",searchdest);
//                intent.putExtra("source",searchsource);
                intent.putExtra("bus_id",searchbus_id);
                startActivity(intent);
                //startActivity(new Intent(Home_page.this, displaydata.class));
            }
        });

//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String bus_idTXT = bus_id.getText().toString();
//                deleteBusDetails(bus_idTXT);
//
//            }
//        });
        delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String bus_idTXT = bus_id.getText().toString();
                    //Toast.makeText(adminView.this, "Deleted bus id is"+bus_idTXT, Toast.LENGTH_SHORT).show();
//                    busdetails = FirebaseDatabase.getInstance().getReference("bus_details").orderByChild("bus_id").equalTo(bus_idTXT);
//                    //busdetails = FirebaseDatabase.getInstance().getReference("bus_details").child(bus_idTXT);
//                    busdetails.removeValue();
//                    Toast.makeText(adminView.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                    deleteBusDetails(bus_idTXT);

                }
            });





    }
    private void deleteBusDetails(String bus_idTXT) {
        //FirebaseDatabase.getInstance().getReference("bus_details").orderByChild("destination").equalTo(searchdest);
        //busdetails = (DatabaseReference) FirebaseDatabase.getInstance().getReference("bus_details").orderByChild("destination").equalTo(bus_idTXT);
        //busdetails = FirebaseDatabase.getInstance().getReference("bus_details").child(bus_idTXT);
        busdetails = FirebaseDatabase.getInstance().getReference("bus_details");
        busdetails.child(bus_idTXT).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(adminView.this, "Entry Deleted is "+bus_idTXT, Toast.LENGTH_SHORT).show();
                    bus_id.setText("");
                }else{
                    Toast.makeText(adminView.this, "Failed to delete "+bus_idTXT, Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    //    private void insertBusDetails(){
//
//                   String bus_idTXT = bus_id.getText().toString();
//                    String bus_nameTXT = bus_name.getText().toString();
//                    String destinationTXT = destination.getText().toString();
//                    String sourceTXT = source.getText().toString();
//                    String timingTXT = timing.getText().toString();
//                    String std_tktTXT = st_ticket.getText().toString();
//                    String eld_tktTXT = eld_ticket.getText().toString();
//
//                    bus_details bus_details=new bus_details(bus_idTXT,bus_nameTXT,destinationTXT,sourceTXT,timingTXT,std_tktTXT,eld_tktTXT);
//////
//                    busdetails.push().setValue(bus_details);
//                    Toast.makeText(Home_page.this, "Data Inserted", Toast.LENGTH_SHORT).show();
//    }
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),SignIn.class));
        finish();
    }
}
//eld_tcktTXT