package com.example.endoscope;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {

    TextView headingText;
    Button camButton;
    connectDB myDB;
    mediaDB mediaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String auth = getIntent().getStringExtra("auth");

        headingText = (TextView) findViewById(R.id.headingText);
        headingText.setText("Hello Doctor, "+auth);

        myDB= new connectDB(this);
        mediaDB = new mediaDB(this);

        camButton=(Button) findViewById(R.id.camButton);
        String sample="storage/emulated/0/Pictures/IMG_20221012_001358.jpg";
        camButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean res = mediaDB.addData(auth,sample);
                if(res){  Toast.makeText(HomeActivity.this,"Success",Toast.LENGTH_SHORT).show();}
                else{Toast.makeText(HomeActivity.this,"Error",Toast.LENGTH_SHORT).show();}
            }
        });



    }
}