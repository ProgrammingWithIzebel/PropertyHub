package com.izebel.propertyhub;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

public class PropertyDetail extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    private float lastX;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.propertydetail);
        viewFlipper = findViewById(R.id.viewFlipper);

        // Set touch listener to detect swipe gestures
        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        float currentX = event.getX();
                        if (lastX < currentX) {
                            viewFlipper.showNext(); // Swipe to the right
                        } else if (lastX > currentX) {
                            viewFlipper.showPrevious(); // Swipe to the left
                        }
                        break;
                }
                return true;
            }
        });
    }

}