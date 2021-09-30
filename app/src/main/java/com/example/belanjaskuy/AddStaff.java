package com.example.belanjaskuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStaff extends AppCompatActivity {
EditText stfName,stfPass,stfRePass;
Button regStaff;
static DatabaseReference databaseStaff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);
        databaseStaff= FirebaseDatabase.getInstance().getReference("staffReg");
        stfName = findViewById(R.id.reg_staff_name);
        stfPass = findViewById(R.id.reg_staff_pass);
        stfRePass = findViewById(R.id.reg_staff_repass);
        regStaff = findViewById(R.id.btn_reg_staff);
        regStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerStaff();
            }
        });



    }
    public void registerStaff(){
        String name=stfName.getText().toString();
        String pass=stfPass.getText().toString();
        String repass=stfRePass.getText().toString();
        if(TextUtils.isEmpty(name))
            Toast.makeText(this,"Silahkan masukan nama anda",Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(pass))
            Toast.makeText(this,"Silahkan masukan password anda",Toast.LENGTH_SHORT).show();
        else if(!pass.equals(repass))
            Toast.makeText(this,"Kata sandi yang dikonfirmasi tidak cocok dengan kata sandi yang diberikan",Toast.LENGTH_SHORT).show();
        else{
            String id=databaseStaff.push().getKey();
            StaffReg staffReg=new StaffReg(name,pass,id);
            databaseStaff.child(id).setValue(staffReg);
            Toast.makeText(this,"Staff Terdaftar",Toast.LENGTH_SHORT).show();
        }
    }
    public static void getStaff(){
        databaseStaff=FirebaseDatabase.getInstance().getReference("staffReg");
    }
    public void onBackPressed(){
        Intent intent=new Intent(AddStaff.this,AdminHomePage.class);
        intent.putExtra("CALLINGACTIVITY","AddStaff1");
        startActivity(intent);
    }
}
