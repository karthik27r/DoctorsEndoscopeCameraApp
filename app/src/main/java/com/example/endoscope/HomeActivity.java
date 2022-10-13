package com.example.endoscope;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {

    private int STORAGE_PERM = 1;

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
                if(ContextCompat.checkSelfPermission(HomeActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(HomeActivity.this,"Perm Enabled",Toast.LENGTH_SHORT).show();}
                else{requestStoragePermissions();}
                Boolean res = myDB.addData(getAuth,authName,pname,sample);
                if(res){  Toast.makeText(HomeActivity.this,"Success",Toast.LENGTH_SHORT).show();}
                else{Toast.makeText(HomeActivity.this,"Error",Toast.LENGTH_SHORT).show();}
            }
        });



    }
    private void requestStoragePermissions(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE))
        new AlertDialog.Builder(this)
                .setTitle("Attention")
                .setMessage("Permission required for the application to work")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        ActivityCompat.requestPermissions(HomeActivity.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERM);
                        

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                })
                .create().show();
        else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERM);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERM) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

    }
}