package com.example.belanjaskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.smartkartapp.R;

public class WomensClothing extends AppCompatActivity {

    private RecyclerView recyclerView;
    int[] images={R.drawable.w0,R.drawable.w1,R.drawable.w2,R.drawable.w3,R.drawable.w4,R.drawable.w5};
    String details[]={"Women Clothing 1","Women Clothing 2","Women Clothing 3","Women Clothing 4","Women Clothing 5","Women Clothing 6"};
    int[] prices={6999000,1299000,4159000,1899000,2949000,1499000};
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
        adapter = new RecyclerAdapter(images,details,prices,this,sna,sph,spa,"WomensClothing");
        recyclerView.setAdapter(adapter);
    }
    public void onBackPressed(){
        final String sna=getIntent().getStringExtra("NAMA");
        final String sph=getIntent().getStringExtra("HANDPHONE");
        final String spa=getIntent().getStringExtra("PASSWORD");
        Intent intent=new Intent(WomensClothing.this,HomePageActivity.class);
        intent.putExtra("NAMA",sna);
        intent.putExtra("HANDPHONE",sph);
        intent.putExtra("PASSWORD",spa);
        intent.putExtra("CALLINGACTIVITY","Division");
        startActivity(intent);
    }
}
