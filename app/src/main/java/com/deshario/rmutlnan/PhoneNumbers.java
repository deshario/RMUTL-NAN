package com.deshario.rmutlnan;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Deshario on 3/25/2017.
 */

public class PhoneNumbers extends AppCompatActivity {

    ArrayAdapter arrayAdapter;

    String[] contactlists = new String[]{"กองการศึกษา","ผู้อำนวยการกองการศึกษา","สำนักงานกองการศึกษา","ฝ่ายวิชาการ/งานทะเบียน","ฝ่ายกิจการนักศึกษา","ฝ่ายวิจัยและพัฒนา",
            "ศูนย์คอมพิวเตอร์","ศูนย์วัฒนธรรมศึกษา","ฝ่ายวิทยบริการและสารสนเทศ","หัวหน้าแผนกห้องสมุด","แผนกห้องสมุด (Counter)"};

    String numberslists[] = {"","7119","7252","7253","7118","7249","7259",
             "7228","7229","7230","7233"};

    ListView listview;
    SimpleAdapter phone_adapter;
    MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phoneno_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" เบอร์โทรภายใน");
        getSupportActionBar().setIcon(R.drawable.ic_contact_phone_white_24dp);
        new CustomFonts().onCreate();
        searchView = (MaterialSearchView)findViewById(R.id.search_view);
        searchView.setCursorDrawable(R.drawable.search_cursor);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    ((SimpleAdapter) phone_adapter).getFilter().filter("");
                }else{
                    ((SimpleAdapter) phone_adapter).getFilter().filter(newText);
                }
                return false;
            }
        });

        int MyVersion = Build.VERSION.SDK_INT;
        if (MyVersion >= Build.VERSION_CODES.M) {
            if (!check_Permission()) { // Permission Denied or No Permission
                request_call_permission();
            }else{ // Permission Granted
                dowork();
            }
        }else{
            dowork();
            Toast.makeText(this, "Current Version < Build.VERSION_CODES.M", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_contacts,menu);
        MenuItem item = menu.findItem(R.id.action_search_contacts);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) { // Set Default Font
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void access_denied(){
        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("สิทธิในการเข้าถึงล้มเหลว");
        pDialog.setConfirmText("ข้อสิทธิในการเข้าถึง");
        pDialog.setCancelText("ปิด");
        pDialog.setCancelable(false);
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                request_call_permission();
            }
        });
        pDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                finish();
                sweetAlertDialog.dismiss();
            }
        });
        pDialog.show();

        WindowManager.LayoutParams lp = pDialog.getWindow().getAttributes();
        lp.dimAmount = 0.7f;
        pDialog.getWindow().setAttributes(lp);
        pDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

    }

    private boolean check_Permission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED) { // result = 0
            return true;
        } else { // result = -1
            return false;
        }
    }

    private void request_call_permission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 911);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 911:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){ // Granted
                    dowork();
                } else { // Denied
                    access_denied();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void dowork(){
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        int lengther = contactlists.length;
        for (int i = 0; i < lengther; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", contactlists[i]);
            hm.put("listview_image", Integer.toString(R.mipmap.caller));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title"};
        int[] to = {R.id.listview_image, R.id.listview_item_title};

        phone_adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.phoneno_custom_listview, from, to);
        listview = (ListView) findViewById(R.id.contacts_list);
        listview.setAdapter(phone_adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                caller(position+1);
            }
        });
    }

    public void caller(int number){
        final String DATA[] = data_fetcher(number);
        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        pDialog.setTitleText(DATA[0]);
        if(DATA[1] == ""){
            pDialog.setContentText("054710259");
        }else{
            pDialog.setContentText("054710259 ต่อ "+DATA[1]);
        }
        pDialog.setCustomImage(R.drawable.phone_call);
        pDialog.setCancelable(true);
        pDialog.setConfirmText("โทร");
        pDialog.setCancelText("ยกเลิก");
        pDialog.setCanceledOnTouchOutside(true);
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:054710259"));
                if (ActivityCompat.checkSelfPermission(PhoneNumbers.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    return;
                }
                startActivity(callIntent);
                //Toast.makeText(PhoneNumbers.this, "Call :: "+DATA[1], Toast.LENGTH_SHORT).show();
                sweetAlertDialog.dismiss();
            }
        });
        pDialog.show();

        WindowManager.LayoutParams lp = pDialog.getWindow().getAttributes();
        lp.dimAmount = 0.8f;
        pDialog.getWindow().setAttributes(lp);
        pDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
    }

    public String[] data_fetcher(int position){
        String data1 = null;
        String data2 = null;
        switch(position){
            case 1:
                data1 = contactlists[1-1];
                data2 = numberslists[1-1];
                break;

            case 2:
                data1 = contactlists[2-1];
                data2 = numberslists[2-1];
                break;

            case 3:
                data1 = contactlists[3-1];
                data2 = numberslists[3-1];
                break;

            case 4:
                data1 = contactlists[4-1];
                data2 = numberslists[4-1];
                break;

            case 5:
                data1 = contactlists[5-1];
                data2 = numberslists[5-1];
                break;

            case 6:
                data1 = contactlists[6-1];
                data2 = numberslists[6-1];
                break;

            case 7:
                data1 = contactlists[7-1];
                data2 = numberslists[7-1];
                break;

            case 8:
                data1 = contactlists[8-1];
                data2 = numberslists[8-1];
                break;

            case 9:
                data1 = contactlists[9-1];
                data2 = numberslists[9-1];
                break;

            case 10:
                data1 = contactlists[10-1];
                data2 = numberslists[10-1];
                break;

            case 11:
                data1 = contactlists[11-1];
                data2 = numberslists[11-1];
                break;
            default:
        }
        return new String[]{data1,data2};
    }


}

