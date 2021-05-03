package com.example.foodscanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditAllergies extends AppCompatActivity {
    private FirebaseAuth mAuth;
    CheckBox c1, c2, c3, c4, c5, c6,c7,c8,c9,c10;
    Button btnSubmit;
    Button btnLogout;
    Button btnBack;
    private ListView listView;

    FirebaseDatabase database;
    DatabaseReference reff, reference;

    Member member;
    int maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_allergies);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        assert user != null;
        String uid = user.getUid();
        reff = database.getInstance().getReference("User Allergens").child(uid);

        /*
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),AddAllergen.class));
            finish();
        }

         */


        member = new Member();

        c1 = findViewById(R.id.lactose2);
        c2 = findViewById(R.id.nuts2);
        c3 = findViewById(R.id.gluten2);
        c4 = findViewById(R.id.egg2);
        c5 = findViewById(R.id.sugar2);
        c6 = findViewById(R.id.wheat2);
        c7 = findViewById(R.id.soya2);
        c8 = findViewById(R.id.fish2);
        c9 = findViewById(R.id.celery2);
        c10 = findViewById(R.id.mustard2);
        listView = findViewById(R.id.listView);

        btnSubmit = findViewById(R.id.confirm2);
        btnLogout = findViewById(R.id.logout3);
        btnBack = findViewById(R.id.back2);

        final String d1 = "Lactose";
        final String d2 = "Nuts";
        final String d3 = "Gluten";
        final String d4 = "Eggs";
        final String d5 = "Sugar";
        final String d6 = "Wheat";
        final String d7 = "Soya";
        final String d8 = "Fish";
        final String d9 = "Celery";
        final String d10 = "Mustard";

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    maxid= (int) dataSnapshot.getChildrenCount();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(EditAllergies.this, MainActivity.class);
                startActivity(intToMain);
            }
        });
        /*
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance();
                Intent intToMain = new Intent(EditAllergies.this, Dashboard.class);
                startActivity(intToMain);
            }
        });

         */
        btnSubmit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //  member.setName(txtName.getText().toString().trim());

                if (c1.isChecked()){
                    member.setA1(d1);
                }

                else{}
                if (c2.isChecked()){
                    member.setA2(d2);
                }
                else{}
                if (c3.isChecked()){
                    member.setA3(d3);
                }
                else{}
                if (c4.isChecked()){
                    member.setA4(d4);
                }


                else{}
                if (c5.isChecked()){
                    member.setA5(d5);
                }
                else{}
                if (c6.isChecked()){
                    member.setA6(d6);
                }
                else{}
                if (c7.isChecked()){
                    member.setA7(d7);
                }
                else{}
                if (c8.isChecked()){
                    member.setA8(d8);
                }
                else{}
                if (c9.isChecked()){
                    member.setA9(d9);
                }
                else{}
                if (c10.isChecked()){
                    member.setA10(d10);
                }
                else{}
                // reff.push().setValue(member);
                reff.child(String.valueOf(maxid+1)).setValue(member);

                Toast.makeText(EditAllergies.this, "Data Inserted successfully",Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance();
                Intent intToMain = new Intent(EditAllergies.this, Dashboard.class);
                startActivity(intToMain);
            }
        });

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, list );
        listView.setAdapter(adapter);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User Allergens").child(uid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren() ){
                    String result = snapshot.getValue().toString();
                    String allergen = result.substring(result.indexOf("=") + 1, result.lastIndexOf("}"));
                    list.add(allergen);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int which_item = position;
                new AlertDialog.Builder(EditAllergies.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are You Sure?")
                        .setMessage("Do you want to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                database.getInstance().getReference("User Allergens").child(uid).child(String.valueOf(maxid)).removeValue();
                                list.remove(which_item);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });

    }

}
