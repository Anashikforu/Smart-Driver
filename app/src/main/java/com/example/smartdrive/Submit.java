package com.example.smartdrive;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.Firebase;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.dmoral.toasty.Toasty;

public class Submit extends AppCompatActivity {

    // Required empty public constructor
    ProgressDialog progressDialog;
    Firebase firebase;
    DatabaseReference databaseReference;
    public TextInputEditText name,location,vehicle,age,number;
    Button submit,driverdata;
    String NameHolder, NumberHolder,LocationHolder,AgeHolder,VehicleHolder;
    public static final String Database_Path = "Driver";
    public static final String Firebase_Server_URL = "https://smart-drive-bd228.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        Firebase.setAndroidContext(this);
        firebase = new Firebase(Firebase_Server_URL);
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);
        progressDialog = new ProgressDialog(Submit.this);
        name = (TextInputEditText) findViewById(R.id.editText3);
        location = (TextInputEditText) findViewById(R.id.editText4);
        vehicle = (TextInputEditText) findViewById(R.id.editText6);
        age = (TextInputEditText) findViewById(R.id.editText5);
        number = (TextInputEditText) findViewById(R.id.editText7);
        submit=(Button)findViewById(R.id.button5);
        driverdata=(Button)findViewById(R.id.button6);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DriverDetails driverDetails = new DriverDetails();

                GetDataFromEditText();

                if(NameHolder.equals("")||NumberHolder.equals("")||LocationHolder.equals("")||AgeHolder.equals("")||VehicleHolder.equals(""))
                {
                    Toasty.error(Submit.this,"Fill all the fields and try again! ", Toast.LENGTH_LONG).show();
                }else if(NumberHolder.length()!=11){
                    Toasty.error(Submit.this,"Invalid Phone number ! Requires 11 digit! ", Toast.LENGTH_LONG).show();
                }
                else {

                    driverDetails.setName(NameHolder);

                    driverDetails.setPhoneNumber(NumberHolder);

                    driverDetails.setLocation(LocationHolder);

                    driverDetails.setAge(AgeHolder);

                    driverDetails.setVehicle(VehicleHolder);

                    String Driver = databaseReference.push().getKey();

                    databaseReference.child(Driver).setValue(driverDetails);
                    Toasty.success(Submit.this, "Driver Information Updated Successfully ", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Submit.this, ShowDriverDetailsActivity.class);
                    startActivity(intent);
                }

            }
        });

        driverdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Submit.this, ShowDriverDetailsActivity.class);
                startActivity(intent);
            }
        });


    }

    public void GetDataFromEditText(){

        NameHolder = name.getText().toString().trim();

        NumberHolder = number.getText().toString().trim();

        LocationHolder = location.getText().toString().trim();

        AgeHolder = age.getText().toString().trim();

        VehicleHolder = vehicle.getText().toString().trim();

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder(Submit.this);
        builder.setTitle("Log Out");
        builder.setMessage("Do you want to log out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toasty.info(Submit.this,"Successfully Logged Out!").show();
                Intent intent = new Intent(Submit.this, Admin.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}