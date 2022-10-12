package com.example.endoscope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegActivity extends AppCompatActivity {

    EditText username,password, verifyPassword;
    Button btnRegister;
    TextView btnLogin;
    connectDB myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        username = (EditText) findViewById(R.id.regName);
        password = (EditText) findViewById(R.id.regPass);
        verifyPassword = (EditText) findViewById(R.id.regPassVerify);

        btnRegister = (Button) findViewById(R.id.regButton);

        btnLogin = (TextView) findViewById(R.id.gotoLogin);

        myDB = new connectDB(this);

        btnRegister.setOnClickListener(view -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            String repass = verifyPassword.getText().toString();
            if(user.equals("")||pass.equals("")||repass.equals("")){
                Toast.makeText(RegActivity.this,"Fill all fields",Toast.LENGTH_LONG).show();
            }else{
                if(pass.equals(repass)){
                    Boolean userCheckResult = myDB.userVerify(user);
                    if(!userCheckResult){
                        Boolean checkInsert = myDB.insertData(user,pass);
                        if(!checkInsert){
                            Toast.makeText(RegActivity.this,"ERROR! Registration Failed",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegActivity.this,"SUCCESS! Hello "+user+" Please Login",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                        }
                    }else{
                        Toast.makeText(RegActivity.this,"Username already exists!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegActivity.this,"Passwords do not match!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        });
    }
}