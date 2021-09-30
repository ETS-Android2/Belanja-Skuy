package com.example.belanjaskuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartkartapp.R;

public class RegLogChoice extends AppCompatActivity {
    Button reg,log;
    TextView admlog,stflog,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_log_choice);
        reg =findViewById(R.id.btnreg);
        log =findViewById(R.id.btnlog);
        admlog = findViewById(R.id.admlog);
        stflog = findViewById(R.id.stflog);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegLogChoice.this,LoginPage.class);
                startActivity(intent);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegLogChoice.this,RegisterPage.class);
                startActivity(intent);
            }
        });
        admlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegLogChoice.this,AdminLogin.class));
            }
        });
        stflog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegLogChoice.this,StaffLogin.class));
            }
        });
    }
    public void onBackPressed(){
     moveTaskToBack(true);
     android.os.Process.killProcess(android.os.Process.myPid());
     System.exit(1);
    }
}
