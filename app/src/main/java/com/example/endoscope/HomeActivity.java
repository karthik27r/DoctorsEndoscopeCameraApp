package com.example.endoscope;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {

    TextView headingText;
    Button camButton;
    connectDB myDB;
    mediaDB mediaDB;
    RecyclerView rv;

    ArrayList<model> mediaData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        int getAuth = getIntent().getIntExtra("id",-404);
        String authString = Integer.toString(getAuth);
        String authName = getIntent().getStringExtra("authName");

        headingText = (TextView) findViewById(R.id.headingText);
        headingText.setText("Hello Doctor, "+authName+" "+getAuth);

        myDB= new connectDB(this);
        // mediaDB = new mediaDB(this);

        rv=(RecyclerView)findViewById(R.id.userData);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = new connectDB(this).getUserData(authString);
        while(cursor.moveToNext()){
            model obj = new model(cursor.getString(0), cursor.getString(1));
            mediaData.add(obj);
        }
        myAdapter adapter = new myAdapter(mediaData);
        rv.setAdapter(adapter);

        camButton=(Button) findViewById(R.id.camButton);
        String pname="Prathik";
        String sample="storage/emulated/0/Pictures/IMG_20221012_001358.jpg";
        camButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean res = myDB.addData(getAuth,authName,pname,sample);
                if(res){  Toast.makeText(HomeActivity.this,"Success",Toast.LENGTH_SHORT).show();}
                else{Toast.makeText(HomeActivity.this,"Error",Toast.LENGTH_SHORT).show();}
            }
        });



    }
}