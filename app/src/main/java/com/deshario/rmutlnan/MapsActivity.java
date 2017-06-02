package com.deshario.rmutlnan;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cn.pedant.SweetAlert.SweetAlertDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


//public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    GPSTracker gps;
    String Selected_Map_Type;
    Boolean current_permission = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(" แผนที่อาคาร");
        getSupportActionBar().setIcon(R.drawable.ic_my_location_white_24dp);
        new CustomFonts().onCreate();

        int MyVersion = Build.VERSION.SDK_INT;
        if (MyVersion >= Build.VERSION_CODES.M) {
            if (!check_Permission()) { // Permission Denied or No Permission
                request_location_permission();
            }else{ // Permission Granted
                access_granted();
            }
        }else{
            access_granted();
            Toast.makeText(this, "Current Version < Build.VERSION_CODES.M", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //getMenuInflater().inflate(R.menu.map_options,menu);
        menu.add(0, 1, 1, menuIconWithText(getResources().getDrawable(R.drawable.ic_map_black_24dp),"ปกติ"));
        menu.add(0, 2, 2, menuIconWithText(getResources().getDrawable(R.drawable.ic_layers_black_24dp),"ไฮบริด"));
        menu.add(0, 3, 3, menuIconWithText(getResources().getDrawable(R.drawable.ic_satellite_black_24dp),"ดาวเทียม"));
        menu.add(0, 4, 4, menuIconWithText(getResources().getDrawable(R.drawable.ic_terrain_black_24dp),"ภูมิประเทศ"));
         return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
            //case R.id.normal_map:
                Selected_Map_Type = "NORMAL";
                break;
            case 2:
            //case R.id.hybrid_map:
                Selected_Map_Type = "HYBRID";
                break;
            case 3:
            //case R.id.satellite_map:
                Selected_Map_Type = "SATELLITE";
                break;
            case 4:
            //case R.id.terrian_map:
                Selected_Map_Type = "TERRIAN";
                break;
            default:
                Selected_Map_Type = "NORMAL";
        }
        change_Map_Type(Selected_Map_Type);
        return super.onOptionsItemSelected(item);
    }

    private CharSequence menuIconWithText(Drawable r, String title) {

        r.setBounds(0, 0, r.getIntrinsicWidth(), r.getIntrinsicHeight());
        SpannableString sb = new SpannableString("    " + title);
        ImageSpan imageSpan = new ImageSpan(r, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;
    }

    public void access_granted(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void access_denied(){
        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("ไม่สามารถเข้าถึงแผนที่ได้");
        pDialog.setConfirmText("ข้อสิทธิในการเข้าถึง");
        pDialog.setCancelText("ปิด");
        pDialog.setCancelable(false);
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                request_location_permission();
            }
        });
        pDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                finish();
            }
        });
        pDialog.show();

        WindowManager.LayoutParams lp = pDialog.getWindow().getAttributes();
        lp.dimAmount = 0.7f;
        pDialog.getWindow().setAttributes(lp);
        pDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
    }


    private boolean check_Permission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) { // result = 0
            return true;
        } else { // result = -1
            return false;
        }
    }

    private void request_location_permission() {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){ // Granted
                    access_granted();
                } else { // Denied
                    access_denied();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) { // Set Default Font
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Bundle bundle = getIntent().getParcelableExtra("bundle");
        if(bundle != null){
            LatLng open_loc = bundle.getParcelable("location_selected");
            String title = bundle.getString("title_selected");
            dowork(open_loc,title);
        }else{
            dowork(null,null);
        }
    }

    public void dowork(LatLng lat_long, String title) {
        drawMarker(new LatLng(18.80935, 100.79213), "ตึกคณะบริหารธุรกิจ",false);
        drawMarker(new LatLng(18.80407, 100.78999), "ตึกสาขาวิชาอุตสาหกรรมเกษตร",false);
        drawMarker(new LatLng(18.81439, 100.79079), "ตึกสาขาวิชาสัตวศาสตร์",false);
        drawMarker(new LatLng(18.81097, 100.78970), "ตึกคณะวิศวกรรมศาสตร์",false);
        drawMarker(new LatLng(18.81277, 100.79044), "ตึกสาขาวิทยาศาสตร์และเทคโนโลยี",false);
        drawMarker(new LatLng(18.81092, 100.79063), "ตึกสาขาพืชศาสตร์",false);
        drawMarker(new LatLng(18.80888, 100.78861), "อาคารปฎิบัติการกลาง",false);
        drawMarker(new LatLng(18.80915, 100.78870), "ภาควิชาศิลปะศาสตร์และศึกษาศาสตร์",false);
        drawMarker(new LatLng(18.80508, 100.79088), "บ้านวิถีไทย",false);
        // Others Building
        drawMarker(new LatLng(18.80818, 100.79119),"ศาลเจ้าพ่อพุทธรักษา",false);
        drawMarker(new LatLng(18.80907, 100.78794),"สนามเทนนิสศุภภักดี",false);
        drawMarker(new LatLng(18.80822, 100.79239),"ประตูหลังมหาวิทยาลัย",false);
        drawMarker(new LatLng(18.81070, 100.79193),"สมาคมศิษย์เก่าเกษตรน่าน",false);
        drawMarker(new LatLng(18.81021, 100.79230),"อาคารเอนกประสงค์",false);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mMap.setMyLocationEnabled(true);

        if(lat_long == null){
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(18.80940, 100.79174),18.0f));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(18.80940, 100.79174), 15.5f), 4000, null);
        }else{
            drawMarker(new LatLng(lat_long.latitude,lat_long.longitude), title,true);
            Toast.makeText(this, "โปรดรอสักครู่", Toast.LENGTH_SHORT).show();
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // Normal ,hybrid, none, satellite , terrian


        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                GPSTracker gps = new GPSTracker(MapsActivity.this);
                if(gps.canGetLocation()){
                    double latitude = mMap.getMyLocation().getLatitude();
                    double longitude = mMap.getMyLocation().getLongitude();
                    MarkerOptions marker = new MarkerOptions().position(new LatLng(mMap.getMyLocation().getLatitude(),
                            mMap.getMyLocation().getLongitude())).title("คูณอยู่ที่นี่");
                    mMap.addMarker(marker).showInfoWindow();
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 15.5f), 2000, null);

                    // Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    //return true;
                }else{
                    gps.showSettingsAlert();
                }
                return false;
            }
        });
    }


    private void drawMarker(LatLng point, String title, boolean locater){
        if(locater == true){
            MarkerOptions markerOptions = new MarkerOptions();  // Creating an instance of MarkerOptions
            markerOptions.position(point); // Latitude,Longtitude
            markerOptions.title(title); // Title

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,18.0f));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 16.5f), 4000, null);
            //mMap.addMarker(markerOptions,  .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))).showInfoWindow(); // Adding marker on the Google Map
            mMap.addMarker(
                    //markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            ).showInfoWindow(); // Adding marker on the Google Map

        }else{
            MarkerOptions markerOptions = new MarkerOptions();  // Creating an instance of MarkerOptions
            markerOptions.position(point); // Latitude,Longtitude
            markerOptions.title(title); // Title
            mMap.addMarker(markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))); // Adding marker on the Google Map
        }
    }

    public void change_Map_Type(String MAPTYPE){
        switch (MAPTYPE){
            case "NORMAL":
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case "HYBRID":
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case "SATELLITE":
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case "TERRIAN":
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            default:
        }
        //mMap.setMapType();
    }

}
