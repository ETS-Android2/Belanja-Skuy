package com.example.belanjaskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.smartkartapp.R;

public class Shoes extends AppCompatActivity {
    private RecyclerView recyclerView;
    int[] images={R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,R.drawable.s6};
    String details[]={"Shoe 1","Shoe 2","Shoe 3","Shoe 4","Shoe 5","Shoe 6"};
    int[] prices={1299000,1599000,3499000,3199000,5999000,899000};
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
        adapter = new RecyclerAdapter(images,details,prices,this,sna,sph,spa,"Shoes");
        recyclerView.setAdapter(adapter);
    }
    public void onBackPressed(){
        final String sna=getIntent().getStringExtra("NAMA");
        final String sph=getIntent().getStringExtra("HANDPHONE");
        final String spa=getIntent().getStringExtra("PASSWORD");
        Intent intent = new Intent(Shoes.this,MensClothing.class);
        intent.putExtra("NAMA",sna);
        intent.putExtra("HANDPHONE",sph);
        intent.putExtra("PASSWORD",spa);
        startActivity(intent);
    }
}
