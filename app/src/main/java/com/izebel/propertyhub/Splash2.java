package com.izebel.propertyhub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash2 extends AppCompatActivity {
TextView textView;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        Handler handler = new Handler();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Splash2.this,Login.class);
                startActivity(i);
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Splash2.this,Splash3.class);
                startActivity(i);
                finish();
            }
        });


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(Splash2.this,Splash3.class);
                startActivity(i);
                finish();
            }
        },5000);
    }
}