package com.group7.astrobuff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set page title
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.sky)));
        getSupportActionBar().setTitle("ASTRO BUFF : HOME");
    }
    //activate Setting button


    //setting icon
    @Override
    public boolean onCreateOptionsMenu(Menu setting) {
        getMenuInflater().inflate(R.menu.setting,setting);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //share clicked
        if (id == R.id.setting) {
            openSetting();
        }
        return true;
    }

    public void openSetting() {
        startActivity(new Intent(this, Setting.class));
    }


    //activate Space News/Updates button
    public void openSpaceNewsUpdates(View view) {
        startActivity(new Intent(this, SpaceNewsUpdates.class));
    }

    //activate Picture of the Day button
    public void openPictureOfTheDay(View view) {
        startActivity(new Intent(this, PictureOfTheDay.class));
    }

    //activate ISS Location Tracking button
    public void openISSLocationTracking(View view) {
        startActivity(new Intent(this, ISS_Location.class));
    }

}