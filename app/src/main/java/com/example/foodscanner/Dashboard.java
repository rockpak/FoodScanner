package com.example.foodscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        findViewById(R.id.logout).setOnClickListener(this);
        findViewById(R.id.create).setOnClickListener(this);
        findViewById(R.id.scan).setOnClickListener(this);
        findViewById(R.id.editAllergies).setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.logout:{
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dashboard.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            }
            case R.id.create:{
               startActivity(new Intent(Dashboard.this, AddAllergen.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            }
            case R.id.scan:{
                startActivity(new Intent(Dashboard.this, QrActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            }
            case R.id.editAllergies:{
                startActivity(new Intent(Dashboard.this, EditAllergies.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            }
        }
    }
}
