package com.example.belanjaskuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smartkartapp.R;

public class AdminLogin extends AppCompatActivity {
    EditText admuser,admpass;
    Button admlog;
    TextView admstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        admuser = findViewById(R.id.admuser);
        admpass = findViewById(R.id.admpass);
        admlog = findViewById(R.id.admlogin);
        admstatus = findViewById(R.id.admstatus);
        admstatus.setText("");
        admlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!((TextUtils.isEmpty(admuser.getText().toString()))||(TextUtils.isEmpty(admpass.getText().toString())))){
                    if (admuser.getText().toString().equals("belanjaskuy")&&admpass.getText().toString().equals("12345")){
                        Intent intent=new Intent(AdminLogin.this,AdminHomePage.class);
                        intent.putExtra("CALLINGACTIVITY","AdminLogin");
                        startActivity(intent);
                    }else{
                        admstatus.setText("Invalid credentials");
                    }
            }else{
                    admstatus.setText("Please enter all credentials");
                }
        }
    });

}
public void onBackPressed(){
        startActivity(new Intent(AdminLogin.this,RegLogChoice.class));
}
}
