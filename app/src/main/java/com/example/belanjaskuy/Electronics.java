package com.example.belanjaskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.smartkartapp.R;

public class Electronics extends AppCompatActivity {
    private RecyclerView recyclerView;
    int[] images={R.drawable.e1,R.drawable.e2,R.drawable.e3,R.drawable.e4,R.drawable.e5,R.drawable.e6,R.drawable.e7,R.drawable.e8,R.drawable.e9,R.drawable.e10,R.drawable.e11,R.drawable.e12};
    String details[]={"Electronic Item 1","Electronic Item 2","Electronic Item 3","Electronic Item 4","Electronic Item 5","Electronic Item 6","Electronic Item 7","Electronic Item 8","Electronic Item 9","Electronic Item 10","Electronic Item 11","Electronic Item 12",};
    int[] prices={12999000,15999000,12399000,45999000,5699000,34999000,12999000,43399000,12999000,49999000,1399900,399000};
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_shirts);
        recyclerView=findViewById(R.id.rvTShirts);
        final String sna=getIntent().getStringExtra("NAMA");
        final String sph=getIntent().getStringExtra("HANDPHONE");
        final String spa=getIntent().getStringExtra("PASSWORD");
        layoutManager = new GridLayoutManager(this,1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(images,details,prices,this,sna,sph,spa,"Electronics");
        recyclerView.setAdapter(adapter);
    }
    public void onBackPressed(){
        final String sna=getIntent().getStringExtra("NAMA");
        final String sph=getIntent().getStringExtra("HANDPHONE");
        final String spa=getIntent().getStringExtra("PASSWORD");
        Intent intent=new Intent(Electronics.this,HomePageActivity.class);
        intent.putExtra("NAMA",sna);
        intent.putExtra("HANDPHONE",sph);
        intent.putExtra("PASSWORD",spa);
        intent.putExtra("CALLINGACTIVITY","Division");
        startActivity(intent);
    }
}
