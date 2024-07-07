package com.izebel.propertyhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottm=findViewById(R.id.bottm);
        bottm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id ==R.id.message){
                    loadFrag(new message(), false);

                }
                else if (id== R.id.setting){
                    loadFrag(new Setting(), false);

                }
                else if (id== R.id.explore){

                    loadFrag(new Explore(), false);

                }
                else { //profile
                    loadFrag(new Home(), true);

                }

                return true;
            }
        });

        bottm.setSelectedItemId(R.id.home);

    }

    public void loadFrag(Fragment fragment, boolean flag){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag)
            ft.add(R.id.container, fragment);
        else
            ft.replace(R.id.container, fragment);
        ft.commit();

    }
}