package com.example.anismart;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Me extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottm_navigation);
        bottomNavigationView.setSelectedItemId(R.id.me);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext()
                                , Search.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , InsideContent.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.bookshelf:
                        startActivity(new Intent(getApplicationContext()
                                , Bookshelf.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.me:
                        return true;
                }
                return false;
            }
        });
    }
}