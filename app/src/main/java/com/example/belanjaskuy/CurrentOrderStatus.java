package com.example.belanjaskuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class CurrentOrderStatus extends AppCompatActivity {

    TextView custName,custAddr,custPhone,orderDet,orderPrice;
    EditText custPass;
    Button confirmDelivery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order_status);
        custName = findViewById(R.id.tvCustName);
        custPhone = findViewById(R.id.tvCustPhone);
        custAddr = findViewById(R.id.tvCustAddr);
        orderDet = findViewById(R.id.tvdet);
        orderPrice = findViewById(R.id.tvItemPrice);
        confirmDelivery = findViewById(R.id.confirmDelivery);
        custPass = findViewById(R.id.tvCustPass);
        custName.setText("");
        custAddr.setText("");
        orderDet.setText("");
        custPhone.setText("");
        custPass.setText("");
        final String staffname=getIntent().getStringExtra("STAFFNAME");
        final String staffpassword=getIntent().getStringExtra("STAFFPASSWORD");
        AcceptOrders.databaseOngoingDelivery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot deliverySnapshot:dataSnapshot.getChildren()){
                    DeliverOrder deliverOrder=deliverySnapshot.getValue(DeliverOrder.class);
                    if(deliverOrder.getDeliveryStaffName().equals(staffname)){
                        custName.setText(deliverOrder.getName());
                        custPhone.setText(deliverOrder.getPhone());
                        custAddr.setText(deliverOrder.getAddress());
                        orderDet.setText(deliverOrder.getOrderDetails());
                        orderPrice.setText(deliverOrder.getPrice());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        confirmDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDeliveryConfirmation();
            }
        });

    }

    public void checkDeliveryConfirmation(){
        if(TextUtils.isEmpty(custPass.getText().toString()))
            Toast.makeText(this,"Silakan masukkan kata sandi pelanggan",Toast.LENGTH_SHORT).show();
        else{
            RegisterPage.getuser();
            RegisterPage.databaseUsers.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot userSnapshot:dataSnapshot.getChildren()){
                        MemberReg memberReg=userSnapshot.getValue(MemberReg.class);
                        final String name=memberReg.getUsername();
                        final String phone=memberReg.getPhone();
                        String password=memberReg.getPassword();
                        if(name.equals(custName.getText().toString())&&phone.equals(custPhone.getText().toString())){
                            if(password.equals(custPass.getText().toString())){
                                AcceptOrders.getDelivery();
                               AcceptOrders.databaseOngoingDelivery.addValueEventListener(new ValueEventListener() {
                                   @Override
                                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                       for(DataSnapshot orderSnapshot:dataSnapshot.getChildren()){
                                           DeliverOrder deliverOrder=orderSnapshot.getValue(DeliverOrder.class);
                                           if(name.equals(deliverOrder.getName())&&(phone.equals(deliverOrder.getPhone())&&(orderDet.getText().toString()).equals(deliverOrder.getOrderDetails()))){
                                               orderSnapshot.getRef().removeValue();
                                               showSuccess();
                                               custName.setText("");
                                               custAddr.setText("");
                                               orderDet.setText("");
                                               custPhone.setText("");
                                               custPass.setText("");
                                               String staffname=getIntent().getStringExtra("STAFFNAME");
                                               String staffpassword=getIntent().getStringExtra("STAFFPASSWORD");
                                               Intent i=new Intent(CurrentOrderStatus.this,AcceptOrders.class);
                                               i.putExtra("STAFFNAME",staffname);
                                               i.putExtra("STAFFPASSWORD",staffpassword);
                                               startActivity(i);
                                               finish();
                                               break;
                                           }
                                       }
                                   }

                                   @Override
                                   public void onCancelled(@NonNull DatabaseError databaseError) {

                                   }
                               }) ;
                            }else{
                                showError();
                            }
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void showSuccess(){
        Toast.makeText(this,"Pesanan berhasil dikirim",Toast.LENGTH_SHORT).show();
    }

    public void showError(){
        Toast.makeText(this,"Password Salah",Toast.LENGTH_SHORT).show();
    }

    public void onBackPressed(){
        Toast.makeText(this,"Harap kirimkan pesanan ini sebelum Anda log out",Toast.LENGTH_SHORT).show();
    }
    public void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
