package com.example.smartdrive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class guest extends AppCompatActivity {


    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_driver_details);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(guest.this));

        progressDialog = new ProgressDialog(guest.this);

        progressDialog.setMessage("Loading Driver Data ");


        databaseReference = FirebaseDatabase.getInstance().getReference(Submit.Database_Path);



        Toasty.info(guest.this,"Loading Driver Data",Toasty.LENGTH_LONG).show();


    }


    private void searchData(String searchText)
    {
        Query firebaseSearchQuery = databaseReference.orderByChild("location").startAt(searchText).endAt(searchText+"\uf8ff");

        FirebaseRecyclerAdapter<DriverDetails,ViewHolder>firebaseRecyclerAdapter
                = new FirebaseRecyclerAdapter<DriverDetails, ViewHolder>(
                DriverDetails.class,
                R.layout.recyclerview_items,
                ViewHolder.class,
                firebaseSearchQuery
        ) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, DriverDetails driverDetails, int i) {
                viewHolder.setDetails(getApplicationContext(), driverDetails.getName(), driverDetails.getLocation(), driverDetails.getPhoneNumber(),driverDetails.getAge(),driverDetails.getVehicle());
            }

            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Views
                        TextView name = view.findViewById(R.id.ShowStudentNameTextView);
                        TextView location =  view.findViewById(R.id.ShowStudentLocationTextView);
                        TextView number =  view.findViewById(R.id.ShowStudentNumberTextView);
                        TextView age =  view.findViewById(R.id.ShowAgeTextView);
                        TextView vehicle =  view.findViewById(R.id.ShowVehicleTextView);
                        //get data from views
                        String dname = name.getText().toString();
                        String dlocation = location.getText().toString();
                        String dnumber = number.getText().toString();
                        String dage = age.getText().toString();
                        String dvehicle = vehicle.getText().toString();

                        //pass this data to new activity
                        Intent intent = new Intent(view.getContext(), ShowDriverData.class);
                        intent.putExtra("Name", dname); // put name
                        intent.putExtra("Location", dlocation); //put location
                        intent.putExtra("PhoneNumber", dnumber); //put number
                        intent.putExtra("Age", dage); //put number
                        intent.putExtra("Vehicle", dvehicle); //put number
                        startActivity(intent); //start activity

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }

                });

                return viewHolder;
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<DriverDetails,ViewHolder>firebaseRecyclerAdapter
                =new FirebaseRecyclerAdapter<DriverDetails, ViewHolder>(
                DriverDetails.class,
                R.layout.recyclerview_items,
                ViewHolder.class,
                databaseReference

        ) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, DriverDetails driverDetails, int i) {
                viewHolder.setDetails(getApplicationContext(),driverDetails.getName(),driverDetails.getLocation(),driverDetails.getPhoneNumber(),driverDetails.getAge(),driverDetails.getVehicle());
            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                        //Views
                        TextView name = view.findViewById(R.id.ShowStudentNameTextView);
                        TextView location =  view.findViewById(R.id.ShowStudentLocationTextView);
                        TextView number =  view.findViewById(R.id.ShowStudentNumberTextView);
                        TextView age =  view.findViewById(R.id.ShowAgeTextView);
                        TextView vehicle =  view.findViewById(R.id.ShowVehicleTextView);
                        //get data from views
                        String dname = name.getText().toString();
                        String dlocation = location.getText().toString();
                        String dnumber = number.getText().toString();
                        String dage = age.getText().toString();
                        String dvehicle = vehicle.getText().toString();


                        //pass this data to new activity
                        Intent intent = new Intent(view.getContext(), ShowDriverData.class);
                        intent.putExtra("Name", dname); // put name
                        intent.putExtra("Location", dlocation); //put location
                        intent.putExtra("PhoneNumber", dnumber); //put number
                        intent.putExtra("Age", dage); //put number
                        intent.putExtra("Vehicle", dvehicle); //put number
                        startActivity(intent); //start activity
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                    }
                });
                return viewHolder;
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchData(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return false;
        }

        return super.onOptionsItemSelected(item);
    }

}
