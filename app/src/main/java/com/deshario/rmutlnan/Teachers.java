package com.deshario.rmutlnan;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.deshario.rmutlnan.Fragments.Faculty_BA;
import com.deshario.rmutlnan.Fragments.Faculty_Engineer;
import com.deshario.rmutlnan.Fragments.Faculty_Science;
import com.deshario.rmutlnan.Fragments.TeacherFragment;

import cn.pedant.SweetAlert.SweetAlertDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Deshario on 4/4/2017.
 */

public class Teachers extends AppCompatActivity {
    FragmentManager mFragmentManager;
    public static MaterialSearchView searchView;
    FragmentTransaction mFragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_tablayout);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(" ข้อมูลอาจารย์");
        getSupportActionBar().setIcon(R.drawable.ic_school_white_24dp);
        new CustomFonts().onCreate();


        searchView = (MaterialSearchView)findViewById(R.id.search_view);
        searchView.setCursorDrawable(R.drawable.search_cursor);

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Toast.makeText(Teachers.this, "onSearchViewShown", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSearchViewClosed() {
                //Toast.makeText(Teachers.this, "onSearchViewClosed", Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast.makeText(Teachers_Info.this, "Searched :: "+query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                int pageno = TeacherFragment.page;
                if (TextUtils.isEmpty(newText)) {
                    //adapter.getFilter().filter("");

                    if(pageno == 0){
                        ((SimpleAdapter) Faculty_BA.simpleAdapter).getFilter().filter("");
                    }else if(pageno == 1){
                       ((SimpleAdapter) Faculty_Science.sc_simpleAdapter).getFilter().filter("");
                    }else if (pageno == 2){
                        ((SimpleAdapter) Faculty_Engineer.simpleAdapter_en).getFilter().filter("");
                    }

                } else {
                    if(pageno == 0){
                        ((SimpleAdapter) Faculty_BA.simpleAdapter).getFilter().filter(newText);
                    }else if(pageno == 1){
                        ((SimpleAdapter) Faculty_Science.sc_simpleAdapter).getFilter().filter(newText);
                    }else if (pageno == 2){
                        ((SimpleAdapter) Faculty_Engineer.simpleAdapter_en).getFilter().filter(newText);
                    }
                }

                return true;
            }

        });



        Fragment fragment_to_open;
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragment_to_open = new TeacherFragment();
        fragmentTransaction.replace(R.id.containerView, fragment_to_open);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    protected void attachBaseContext(Context newBase) { // Set Default Font
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_count){
            //Toast.makeText(this, "toast", Toast.LENGTH_SHORT).show();
            count_all();
        }
        return super.onOptionsItemSelected(item);
    }

    public void count_all(){
        int ba_size = Faculty_BA.ba_counter;
        int sc_size = Faculty_Science.sc_counter;
        int en_size = Faculty_Engineer.en_counter;
        int total_size = ba_size+sc_size+en_size;
        String data = "อาจารย์ที่อยูในระบบทั้งหมด "+total_size+" ท่าน \n\n คณะบริหารธุรกิจ "+ba_size+" ท่าน \n\n คณะวิทย์ "+sc_size+" ท่าน \n\n คณะวิศวกรรม "+en_size+" ท่าน";
        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.setTitleText("ข้อมูลอาจารย์");
        pDialog.setContentText(data);
        pDialog.setCancelable(true);
        pDialog.setConfirmText("ตกลง");
        pDialog.setCanceledOnTouchOutside(true);
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        });
        pDialog.show();

        WindowManager.LayoutParams lp = pDialog.getWindow().getAttributes();
        lp.dimAmount = 0.8f;
        pDialog.getWindow().setAttributes(lp);
        pDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
    }

    public void refresh_one(String query){
        int pageno = TeacherFragment.page;
//        if(pageno == 0){
//            Faculty_BA.simpleAdapter.getFilter().filter(query);
//        }else if(pageno == 1){
//            Faculty_Science.sc_simpleAdapter.getFilter().filter(query);
//        }else if (pageno == 2){
//            Faculty_Engineer.en_simpleAdapter.getFilter().filter(query);
//        }

//        ((SimpleAdapter) Faculty_BA.simpleAdapter).getFilter().filter(query);
//        ((SimpleAdapter) Faculty_Science.sc_simpleAdapter).getFilter().filter(query);
//        ((SimpleAdapter) Faculty_Engineer.simpleAdapter_en).getFilter().filter(query);
       // Faculty_BA.simpleAdapter.getFilter().filter(query);
       // Faculty_Science.sc_simpleAdapter.getFilter().filter(query);

    }

    @Override
    public void onBackPressed() {
        if(searchView.isSearchOpen()){
            searchView.closeSearch();
        }else{
            super.onBackPressed();
        }
        finish();
    }
}
