package com.example.undangan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.undangan.adapter.ViewPagerAdapter;
import com.github.squti.guru.Guru;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;


    fragment_tamu tamu;
    fragment_konfirmasi konfirmasi;
    ViewPager viewPager;
    private ActionBar toolbar;
    public static BadgeDrawable badge;
    String status_login;
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.i("status_loign", "onCreate: "+status_login);
        status_login = Guru.getString("status_loign", "false");
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        badge = bottomNavigationView.getOrCreateBadge(R.id.konfirmasi);
        badge.setVisible(true);
        if (status_login.equals("false")){
            badge.setVisible(false);
        }else {
            badge.setVisible(true);
        }

        loadFragment(new fragment_tamu());
        getSupportActionBar().setTitle("Tamu");


        bottomNavigationView.setOnNavigationItemSelectedListener(this);



    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.tamu:
                fragment = new fragment_tamu();
                getSupportActionBar().setTitle("Tamu");
                break;

            case R.id.konfirmasi:
                fragment = new fragment_konfirmasi();
                getSupportActionBar().setTitle("Konfiramsi");
                break;

        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        tamu=new fragment_tamu();
        konfirmasi=new fragment_konfirmasi();




        adapter.addFragment(tamu);
        adapter.addFragment(konfirmasi);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}