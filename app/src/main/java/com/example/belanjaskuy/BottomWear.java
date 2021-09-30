package com.example.belanjaskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.smartkartapp.R;

public class BottomWear extends AppCompatActivity {
    private RecyclerView recyclerView;
    int[] images={R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4};
    String details[]={"Bottom Wear 1","Bottom Wear 2","Bottom Wear 3","Bottom Wear 4"};
    int[] prices={799000,1249000,599000,2999000};
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_shirts);
        recyclerView = findViewById(R.id.rvTShirts);
        final String sna=getIntent().getStringExtra("NAMA");
        final String sph=getIntent().getStringExtra("HANDPHONE");
        final String spa=getIntent().getStringExtra("PASSWORD");
        layoutManager = new GridLayoutManager(this,1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(images,details,prices,this,sna,sph,spa,"BottomWear");
        recyclerView.setAdapter(adapter);
    }
    public void onBackPressed(){
        final String sna=getIntent().getStringExtra("NAMA");
        final String sph=getIntent().getStringExtra("HANDPHONE");
        final String spa=getIntent().getStringExtra("PASSWORD");
        Intent intent = new Intent(BottomWear.this,MensClothing.class);
        intent.putExtra("NAMA",sna);
        intent.putExtra("HANDPHONE",sph);
        intent.putExtra("PASSWORD",spa);
        startActivity(intent);
    }
}