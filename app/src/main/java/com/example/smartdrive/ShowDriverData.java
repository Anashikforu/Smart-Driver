package com.example.smartdrive;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowDriverData extends AppCompatActivity {

    TextView name, location,mobile,age,vehicle;

    ImageButton call;

    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_driver_data);

        //initialize views
        name = findViewById(R.id.Name);
        location = findViewById(R.id.Location);
        mobile=findViewById(R.id.PhoneNumber);
        age=findViewById(R.id.Age);
        vehicle=findViewById(R.id.Vehicle);
        call=(ImageButton) findViewById(R.id.call);

        //get data from intent
        String dname = getIntent().getStringExtra("Name");
        String dlocation = getIntent().getStringExtra("Location");
        final String dmobile=getIntent().getStringExtra("PhoneNumber");
        String dage=getIntent().getStringExtra("Age");
        String dvehicle=getIntent().getStringExtra("Vehicle");

        //set data to views
        name.setText(dname);
        location.setText(dlocation);
        mobile.setText(dmobile);
        age.setText(dage);
        vehicle.setText(dvehicle);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+dmobile));
                startActivity(callIntent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
