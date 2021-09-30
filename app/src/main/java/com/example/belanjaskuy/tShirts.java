package com.example.belanjaskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.smartkartapp.R;

public class tShirts extends AppCompatActivity {
    private RecyclerView recyclerView;
    int[] images={R.drawable.t1,R.drawable.t2,R.drawable.t3,R.drawable.t4,R.drawable.t5,R.drawable.t6};
    String details[]={"T-Shirt 1","T-Shirt 2","T-Shirt 3","T-Shirt 4","T-Shirt 5","T-Shirt 6"};
    int[] prices={699000,1299000,459000,899000,949000,499000};
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
        adapter = new RecyclerAdapter(images,details,prices,this,sna,sph,spa,"tShirts");
        recyclerView.setAdapter(adapter);
    }
    public void onBackPressed(){
        final String sna=getIntent().getStringExtra("NAMA");
        final String sph=getIntent().getStringExtra("HANDPHONE");
        final String spa=getIntent().getStringExtra("PASSWORD");
        Intent intent=new Intent(tShirts.this,MensClothing.class);
        intent.putExtra("NAMA",sna);
        intent.putExtra("HANDPHONE",sph);
        intent.putExtra("PASSWORD",spa);
        startActivity(intent);
    }
}
