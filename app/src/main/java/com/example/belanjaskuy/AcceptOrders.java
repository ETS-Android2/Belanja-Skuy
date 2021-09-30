package com.example.belanjaskuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartkartapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AcceptOrders extends AppCompatActivity {

    TextView tv1,tv2,tv3,tv4,tvtopmsg;
    static DatabaseReference databaseOngoingDelivery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_orders);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tvtopmsg = findViewById(R.id.tvtopmsg);
        final String staffname=getIntent().getStringExtra("STAFFNAME");
        final String staffpassword=getIntent().getStringExtra("STAFFPASSWORD");

        databaseOngoingDelivery= FirebaseDatabase.getInstance().getReference("deliverOrder");

       PlaceOrder.getOrder();

                PlaceOrder.databaseOrders.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int i=0;
                        for(DataSnapshot orderSnapshot:dataSnapshot.getChildren()){
                           // i++;
                           i++;
                           Orders orders=orderSnapshot.getValue(Orders.class);
                            String name=orders.getCustname();
                            String phone=orders.getCustphone();
                            String address=orders.getCustaddr();
                            String specs=orders.getSpec();
                            int price=orders.getPrice();
                            String details="NAMA:"+name+"\nHANDPHONE:"+phone+"\nALAMAT:"+address+"\nDETAIL PEMESANAN:"+specs+"\nHARGA:"+price;
                            switch(i){
                                case 1:tv1.setText(details);
                                       tv2.setText("");
                                       tv3.setText("");
                                       tv4.setText("");
                                       break;
                                case 2:tv2.setText(details);
                                       tv3.setText("");
                                       tv4.setText("");
                                       break;
                                case 3:tv3.setText(details);
                                       tv4.setText("");
                                       break;
                                case 4:tv4.setText(details);
                                       break;
                            }
                            if(i>0)
                                tvtopmsg.setText("Ketuk pesanan untuk memulai pengirimannya");
                        }
                        if(i==0)
                            tv1.setText("");
                        if(tv1.getText().equals(""))
                            tvtopmsg.setText("Tidak ada pesanan yang sedang berlangsung");

                        tv1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(!tv1.getText().toString().equals("")){
                                    String details=tv1.getText().toString();
                                    addOrderToDeliver(details,staffname,staffpassword);
                                }
                            }
                        });
                        tv2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(!tv2.getText().toString().equals("")){
                                    String details=tv2.getText().toString();
                                    addOrderToDeliver(details,staffname,staffpassword);
                                }
                            }
                        });
                        tv3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(!tv3.getText().toString().equals("")){
                                    String details=tv3.getText().toString();
                                    addOrderToDeliver(details,staffname,staffpassword);
                                }
                            }
                        });
                        tv4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(!tv4.getText().toString().equals("")){
                                    String details=tv4.getText().toString();
                                    addOrderToDeliver(details,staffname,staffpassword);;
                                }
                            }
                        });


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




    }

    public static void getDelivery(){
        databaseOngoingDelivery=FirebaseDatabase.getInstance().getReference("deliverOrder");
    }

    public void addOrderToDeliver(String details, final String staffname,final String staffpassword){
        final String name=details.substring(5,details.indexOf("\nHANDPHONE"));
        final String phone=details.substring(details.indexOf("HANDPHONE")+6,details.indexOf("\nALAMAT"));
        final String address=details.substring(details.indexOf("ALAMAT")+8,details.indexOf("\nDETAIL PEMESANAN"));
        final String specs=details.substring(details.indexOf("DETAIL PEMESANAN")+14,details.indexOf("\nHARGA"));
        final String price=details.substring(details.indexOf("HARGA")+6);
        final String id=databaseOngoingDelivery.push().getKey();
        DeliverOrder deliverOrder=new DeliverOrder(name,phone,id,address,specs,staffname,price);
        databaseOngoingDelivery.child(id).setValue(deliverOrder);
        Toast.makeText(this,"Pesanan ditambahkan ke daftar pengiriman Anda",Toast.LENGTH_SHORT).show();
        PlaceOrder.databaseOrders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot orderSnapshot:dataSnapshot.getChildren()){
                    Orders orders=orderSnapshot.getValue(Orders.class);
                    if(orders.getCustname().equals(name)&&(orders.getCustphone().equals(phone)&&(orders.getCustaddr().equals(address)&&orders.getSpec().equals(specs)))){
                        orderSnapshot.getRef().removeValue();
                        String staffname=getIntent().getStringExtra("STAFFNNAME");
                        String staffpassword=getIntent().getStringExtra("STAFFPASSWORD");
                        Intent i=new Intent(AcceptOrders.this,CurrentOrderStatus.class);
                        i.putExtra("STAFFNAMA",staffname);
                        i.putExtra("STAFFPASSWORD",staffpassword);
                        startActivity(i);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void onBackPressed(){
        startActivity(new Intent(AcceptOrders.this,StaffLogin.class));
    }
}
