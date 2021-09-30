package com.example.belanjaskuy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPage extends AppCompatActivity {
    EditText etname,etphone,etpass;
    Button register;
    static DatabaseReference databaseUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        databaseUsers= FirebaseDatabase.getInstance().getReference("memberReg");
        register = findViewById(R.id.btnregister);
        etname = findViewById(R.id.etName);
        etphone = findViewById(R.id.etPhone);
        etpass = findViewById(R.id.etPassword);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg();
            }
        });
    }
    public void reg(){
        String name=etname.getText().toString();
        String phone=etphone.getText().toString();
        String password=etpass.getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Silakan tulis nama Anda",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Silakan tulis no handphone Anda",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Silakan tulis password Anda",Toast.LENGTH_SHORT).show();
        }else{
            String id=databaseUsers.push().getKey();
            MemberReg memberReg=new MemberReg(id,name,password,phone);
            databaseUsers.child(id).setValue(memberReg);
            Toast.makeText(this,"User telah terdaftar",Toast.LENGTH_SHORT).show();
        }
    }
    public static void getuser(){
        databaseUsers=FirebaseDatabase.getInstance().getReference("memberReg");
    }
}
