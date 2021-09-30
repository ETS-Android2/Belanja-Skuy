package com.example.belanjaskuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MensClothing extends AppCompatActivity {
Button tShirts,shoes,bottomWear,formals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mens_clothing);
        tShirts = findViewById(R.id.tShirts);
        shoes = findViewById(R.id.shoes);
        bottomWear = findViewById(R.id.bottomWear);
        formals = findViewById(R.id.formals);
        final String sna=getIntent().getStringExtra("NAMA");
        final String sph=getIntent().getStringExtra("HANDPHONE");
        final String spa=getIntent().getStringExtra("PASSWORD");
        tShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MensClothing.this,tShirts.class);
                intent.putExtra("NAMA",sna);
                intent.putExtra("HANDPHONE",sph);
                intent.putExtra("PASSWORD",spa);
                startActivity(intent);
            }
        });
        formals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MensClothing.this,Formals.class);
                intent.putExtra("NAMA",sna);
                intent.putExtra("HANDPHONE",sph);
                intent.putExtra("PASSWORD",spa);
                startActivity(intent);
            }
        });
        bottomWear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MensClothing.this,BottomWear.class);
                intent.putExtra("NAMA",sna);
                intent.putExtra("HANDPHONE",sph);
                intent.putExtra("PASSWORD",spa);
                startActivity(intent);
            }
        });
        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MensClothing.this,Shoes.class);
                intent.putExtra("NAMA",sna);
                intent.putExtra("HANDPHONE",sph);
                intent.putExtra("PASSWORD",spa);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed(){
        final String sna=getIntent().getStringExtra("NAMA");
        final String sph=getIntent().getStringExtra("HANDPHONE");
        final String spa=getIntent().getStringExtra("PASSWORD");
        Intent intent=new Intent(MensClothing.this,Clothing.class);
        intent.putExtra("NAMA",sna);
        intent.putExtra("HANDPHONE",sph);
        intent.putExtra("PASSWORD",spa);
        startActivity(intent);
    }
}
