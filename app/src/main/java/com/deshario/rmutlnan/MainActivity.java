package com.deshario.rmutlnan;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    MapsActivity ma;
    Button teacher_info_btn,phone_numbers_btn,building_maps_btn,search_building_btn,about_me_btn;
    ImageView menu1,menu2,menu3,menu4,menu5,menu6;
    public static String PACKAGE_NAME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        PACKAGE_NAME = getApplicationContext().getPackageName();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        //getSupportActionBar().setIcon(R.drawable.ic_home_white_24dp);
        new CustomFonts().onCreate();

        int MyVersion = Build.VERSION.SDK_INT;
        if (MyVersion >= Build.VERSION_CODES.M) {
            if (!check_Permission()) { // Permission Denied or No Permission
                request_permission();
            }
        }

        menu1 = (ImageView) findViewById(R.id.menu1);
        menu2 = (ImageView) findViewById(R.id.menu2);
        menu3 = (ImageView) findViewById(R.id.menu3);
        menu4 = (ImageView) findViewById(R.id.menu4);
        menu5 = (ImageView) findViewById(R.id.menu5);
        menu6 = (ImageView) findViewById(R.id.menu6);

        // teacher_info_btn = (Button) findViewById(R.id.teacher_info);
        //phone_numbers_btn = (Button) findViewById(R.id.phone_numbers);
        //building_maps_btn = (Button) findViewById(R.id.building_maps);
        //search_building_btn = (Button) findViewById(R.id.search_building);
       // about_me_btn = (Button) findViewById(R.id.about_me);

//        teacher_info_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,Teachers_Info.class));
//            }
//        });

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this,Teachers_Info.class));
                startActivity(new Intent(MainActivity.this,Teachers.class));
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PhoneNumbers.class));
            }
        });

        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MapsActivity.class));
            }
        });

        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,BuildingsActivity.class));
            }
        });

        menu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Developer.class));
            }
        });

        menu6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
               // startActivity(new Intent(MainActivity.this,SettingsActivity.class));
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) { // Set Default Font
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private boolean check_Permission() {
        int result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        int result2 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED) { // result1 = 0 || result2 == 0
            return true;
        } else { // result1 = -1 || result2 = -1
            return false;
        }
    }

    private void request_permission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_LOCATION);
    }

}
