package com.izebel.propertyhub;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class admin extends AppCompatActivity {
    ImageView back;
    LinearLayout admin;
    CardView buy,sell;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
        back=findViewById(R.id.back);
        admin=findViewById(R.id.admin);
        buy=findViewById(R.id.buy);
        sell=findViewById(R.id.Sell);

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(admin.getVisibility()==view.VISIBLE){
//                    Intent i=new Intent(admin.this,MainActivity.class);
//                    startActivity(i);
//                }else if(buylayout.getVisibility()==view.VISIBLE){
//                    buylayout.setVisibility(View.GONE);
//                    admin.setVisibility(View.VISIBLE);
//                }else if(selllayout.getVisibility()==view.VISIBLE){
//                    selllayout.setVisibility(View.GONE);
//                    admin.setVisibility(View.VISIBLE);
//                }
//
//            }
//        });
//        buy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                admin.setVisibility(View.GONE);
//                sell.setVisibility(View.GONE);
//                buy.setVisibility(View.VISIBLE);
//            }
//        });
//        sell.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                admin.setVisibility(View.GONE);
//                sell.setVisibility(View.VISIBLE);
//                buy.setVisibility(View.GONE);
//            }
//        });
    }
}