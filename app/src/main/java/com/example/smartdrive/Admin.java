package com.example.smartdrive;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import es.dmoral.toasty.Toasty;

public class Admin extends AppCompatActivity {

    TextInputEditText email,password;
    Button login;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        email=(TextInputEditText) findViewById(R.id.email);
        password=(TextInputEditText) findViewById(R.id.password);
        login=(Button) findViewById(R.id.push_button);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eMail=email.getText().toString();
                String pAssword=password.getText().toString();
                if(eMail.equals("")||pAssword.equals(""))
                {
                    Toasty.info(getBaseContext(), "Please fill the fields", Toast.LENGTH_SHORT).show();
                }else if(eMail.equals("smart247drive@gmail.com")||pAssword.equals("smartdrive")){
                    Toasty.success(getBaseContext(), "Admin Looged in Sucessfully.", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Admin.this,Submit.class);
                    startActivity(i);
                }
                else
                {
                    Toasty.error(getBaseContext(), "Invalid email or password!Please try agin with valid email and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(Admin.this,MainActivity.class);
        startActivity(intent);
    }
}
