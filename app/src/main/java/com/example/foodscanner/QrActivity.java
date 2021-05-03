package com.example.foodscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QrActivity extends AppCompatActivity {
    Button qrbtn;
    public static TextView qrtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        qrbtn=(Button)findViewById(R.id.qrbtn);
        qrtext=(TextView)findViewById(R.id.qrtext);

        qrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),qrscanner.class));
            }
        });
    }
}