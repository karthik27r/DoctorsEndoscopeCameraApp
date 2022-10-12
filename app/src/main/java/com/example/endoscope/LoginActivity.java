package com.example.endoscope;
//package com.example.sqliteloginregistration;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button btnLogin;
    View btnReg;
    connectDB myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.loginName);
        password = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.loginButton);
        btnReg = findViewById(R.id.createAccount);

        myDB = new connectDB(this);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(view->{
            String user = username.getText().toString();
            String pass = password.getText().toString();
            if(user.equals("")||pass.equals("")) Toast.makeText(LoginActivity.this,"Please fill all the details",Toast.LENGTH_SHORT).show();
            else {
                int userID = myDB.loginCheck(user, pass);
                if(userID>0){
                    Toast.makeText(LoginActivity.this,"Hello "+user+" "+userID,Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    intent.putExtra("id",userID);
                    intent.putExtra("authName",user);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,"Invalid name or password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}