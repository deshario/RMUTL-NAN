package com.deshario.rmutlnan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.deshario.rmutlnan.Fragments.Faculty_BA;
import com.google.android.gms.maps.model.LatLng;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Deshario on 4/4/2017.
 */

public class BuildingsActivity extends AppCompatActivity {
    int[] listviewImage = new int[]{
            R.drawable.business_administration,
            R.drawable.kaset_uthsakam,
            R.drawable.animal_science,
            R.drawable.enginerring,
            R.drawable.sat,
            R.drawable.plant_science,
            R.drawable.patibat_klang,
            R.drawable.silapasat,
            R.drawable.ban_witthi_thai,
            // Other Buildings
            R.drawable.putharaksa,
            R.drawable.tennis_court,
            R.drawable.uni_backdoor,
            R.drawable.std_group,
            R.drawable.anekprasong,
    };
    ArrayList<String> names;
    ArrayList<String> building_short;
    ArrayList<String> shortkey;
    ArrayList<String> more_info;
    ArrayList<LatLng> lat_long;
    ListView listview;
    MaterialSearchView searchView;
    SimpleAdapter buildings_adapter;


    View positiveAction;
    TextView item1,data_item,item2,item3,item4;
    ImageView dataimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buildings_info);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(" ค้นหาอาคาร");
        getSupportActionBar().setIcon(R.drawable.ic_my_location_white_24dp);
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
                    ((SimpleAdapter) buildings_adapter).getFilter().filter("");
                }else{
                    ((SimpleAdapter) buildings_adapter).getFilter().filter(newText);
                }
                return false;
            }
        });

        listview = (ListView) findViewById(R.id.list_view);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3) {
                //String name = arg0.getItemAtPosition(position).toString();
                TextView temp = (TextView)arg1.findViewById(R.id.listview_item_title);
                String title = temp.getText().toString();
                //Toast.makeText(BuildingsActivity.this, "Pos : "+position+"\n Title : "+title, Toast.LENGTH_SHORT).show();
                display(position,title);
            }
        });
       // add_data();
        dowork();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_buildings,menu);
        MenuItem item = menu.findItem(R.id.action_search_buildings);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) { // Set Default Font
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void dowork(){

        names = new ArrayList<String>();
        names.add("ตึกคณะบริหารธุรกิจ");
        names.add("ตึกสาขาวิชาอุตสาหกรรมเกษตร");
        names.add("ตึกสาขาวิชาสัตวศาสตร์");
        names.add("ตึกคณะวิศวกรรมศาสตร์");
        names.add("ตึกสาขาวิทยาศาสตร์และเทคโนโลยี");
        names.add("ตึกสาขาพืชศาสตร์ ");
        names.add("อาคารปฎิบัติการกลาง");
        names.add("ภาควิชาศิลปะศาสตร์และศึกษาศาสตร์");
        names.add("บ้านวิถีไทย");
        // Other Buildings
        names.add("ศาลเจ้าพ่อพุทธรักษา");
        names.add("สนามเทนนิสศุภภักดี");
        names.add("ประตูหลังมหาวิทยาลัย");
        names.add("สมาคมศิษย์เก่าเกษตรน่าน");
        names.add("อาคารเอนกประสงค์");

        shortkey = new ArrayList<String>();
        shortkey.add("รหัสย่อ : BA");
        shortkey.add("รหัสย่อ : ไม่มี");
        shortkey.add("รหัสย่อ : SA");
        shortkey.add("รหัสย่อ : ไม่มี");
        shortkey.add("รหัสย่อ : SAT");
        shortkey.add("รหัสย่อ : PS");
        shortkey.add("รหัสย่อ : ไม่มี");
        shortkey.add("รหัสย่อ : ไม่มี");
        shortkey.add("รหัสย่อ : ไม่มี");
        // Other Buildings
        shortkey.add("รหัสย่อ : ไม่มี");
        shortkey.add("รหัสย่อ : ไม่มี");
        shortkey.add("รหัสย่อ : ไม่มี");
        shortkey.add("รหัสย่อ : ไม่มี");
        shortkey.add("รหัสย่อ : ไม่มี");

        more_info = new ArrayList<String>();
        more_info.add("ศาลเจ้าพ่อพุทธรักษา เป็นศาลที่นักศึกษาคณะอาจารย์ให้ความเคารพและบูชา จะมี การทำพิธีไหว้เจ้าพ่อพุทธรักษา ทุกๆ ปีการศึกษา ช่วงเปิดเทอมภาคเรียนที่1");
        more_info.add("สนามเทนนิสศุภภักดีตั้งอยู่ที่บริเวณหลังอาคารปฎิบัติการกลาง สำหรับ ให้นักศึกษา และ บุคลากรในมหาวิทยาลัย เล่นเทนนิส และ ทำกิจกรรม ต่างๆ");
        more_info.add("ประตูหลังมหาวิทยาลัย มีการเปิดปิดเป็น 3 ช่วงเวลาดังนี้ \n" +
                " 07.00น. – 09.30น.\n" +
                " 11.00น. - 13.30น.\n" +
                " 15.30น.- 17.00น.");
        more_info.add("เป็นสมาคมของนักศึกษาที่สำเร็จการศึกษาไปแล้ว");
        more_info.add("เป็นอาคารสำหรับทำกิจกรรม นันทนาการ แก่นักศึกษา และ บุคลากร");


        building_short = new ArrayList<String>();
        building_short.add("รหัสอาคาร : Faculty_BA  (Business Administration)");
        building_short.add("รหัสอาคาร : ไม่มี");
        building_short.add("รหัสอาคาร : SA (Department of Animal Science And Fisheries)");
        building_short.add("รหัสอาคาร : ไม่มี");
        building_short.add("รหัสอาคาร : SAT (Faculty Of Science And Agricultural Technology)");
        building_short.add("รหัสอาคาร : PS (Plant And Science) ");
        building_short.add("รหัสอาคาร : ไม่มี");
        building_short.add("รหัสอาคาร : (Department Of Liberal Arts And Education) ");
        building_short.add("รหัสอาคาร : ไม่มี");
        // Other Buildings

        lat_long = new ArrayList<LatLng>();
        lat_long.add(new LatLng(18.80935, 100.79213));
        lat_long.add(new LatLng(18.80407, 100.78999));
        lat_long.add(new LatLng(18.81439, 100.79079));
        lat_long.add(new LatLng(18.81097, 100.78970));
        lat_long.add(new LatLng(18.81277, 100.79044));
        lat_long.add(new LatLng(18.81092, 100.79063));
        lat_long.add(new LatLng(18.80888, 100.78861));
        lat_long.add(new LatLng(18.80915, 100.78870));
        lat_long.add(new LatLng(18.80508, 100.79088));
        // Other Buildings
        lat_long.add(new LatLng(18.80818, 100.79119));
        lat_long.add(new LatLng(18.80907, 100.78794));
        lat_long.add(new LatLng(18.80822, 100.79239));
        lat_long.add(new LatLng(18.81070, 100.79193));
        lat_long.add(new LatLng(18.81021, 100.79230));


        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < names.size(); i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("title", names.get(i));
            hm.put("description", shortkey.get(i));
            hm.put("image", Integer.toString(listviewImage[i])); //listviewImage[i]
            aList.add(hm);
        }

        String[] from = {"image","description","title"};
        int[] to = {R.id.building_logo, R.id.listview_item_description, R.id.listview_item_title};

        buildings_adapter = new SimpleAdapter(BuildingsActivity.this, aList, R.layout.buildings_custom_listview, from, to);
        listview.setAdapter(buildings_adapter);
    }

    public void display(final int position, final String title){
        boolean wrapInScrollView = true;
        MaterialDialog dialog = new MaterialDialog.Builder(BuildingsActivity.this)
                .title(title)
                .customView(R.layout.buildings_lists, true)
                .negativeText("ปิด")
                .positiveText("เปิดในแผนที่")
                .titleColorRes(R.color.primary_bootstrap)
                .positiveColorRes(R.color.colorPrimaryDark)
                .negativeColorRes(R.color.danger_bootstrap)
                .widgetColorRes(R.color.info_bootstrap)
                .buttonRippleColorRes(R.color.orange)
                .canceledOnTouchOutside(false)
                .cancelable(true)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        finish();
                        double _lat = lat_long.get (position).latitude;
                        double _long = lat_long.get (position).longitude;
                        LatLng selected_location = new LatLng(_lat,_long);
                        Bundle args = new Bundle();
                        args.putParcelable("location_selected", selected_location);
                        args.putString("title_selected",title);
                        Intent i= new Intent(BuildingsActivity.this, MapsActivity.class);
                        i.putExtra("bundle", args);
                        startActivity(i);
                        //Toast.makeText(BuildingsActivity.this, "lat : "+lat_long.get (position).latitude, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
                lp.dimAmount=1f;
                dialog.getWindow().setAttributes(lp);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

            positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
            //mytitle = (TextView)dialog.getCustomView().findViewById(R.id.title);
            item1 = (TextView)dialog.getCustomView().findViewById(R.id.item1);
            data_item = (TextView)dialog.getCustomView().findViewById(R.id.itemdata);
            item2 = (TextView)dialog.getCustomView().findViewById(R.id.item2);
            item3 = (TextView)dialog.getCustomView().findViewById(R.id.item3);
            item4 = (TextView)dialog.getCustomView().findViewById(R.id.item4);
            dataimg = (ImageView)dialog.getCustomView().findViewById(R.id.img);

            dataimg.setBackgroundResource(listviewImage[position]);
            item1.setText("อาคาร : "+title);

            if(position <= 8){
                data_item.setVisibility(View.GONE);
                item2.setText(building_short.get(position));
            }else{
                data_item.setText("ข้อมูล : "+more_info.get(position-9));
                item2.setVisibility(View.GONE);
            }

            item3.setText("ละติจูด : "+lat_long.get(position).latitude);
            item4.setText("ลองจิจูด : "+lat_long.get(position).longitude);
            dialog.show();
    }

    public void gen_searched(String query){
//        String[] from = {"teacher_img", "teacher_name", "teacher_faculty"};
//        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};
//        prefix_names_searched = DB_Manager.Search_Teachers("prefix_name",query);
//        names_searched = DB_Manager.Search_Teachers("name",query);
//        surnames_searched = DB_Manager.Search_Teachers("surname",query);
//        faculties_searched = DB_Manager.Search_Teachers("faculty",query);
//        gender_searched = DB_Manager.Search_Teachers("sex",query);
//        datalist_Searched = new ArrayList<HashMap<String, String>>();
//
//        for (int i = 0; i < names_searched.size(); i++) {
//            HashMap<String, String> hm = new HashMap<String, String>();
//            hm.put("teacher_name", prefix_names_searched.get(i)+" "+names_searched.get(i)+" "+surnames_searched.get(i));
//            hm.put("teacher_faculty", faculties_searched.get(i));
//            hm.put("teacher_img",genimage(gender_searched.get(i)));
//            datalist_Searched.add(hm);
//        }
//        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), datalist_Searched, R.layout.teachers_custom_listview, from, to);
//
//        listview.setAdapter(simpleAdapter);
    }
}
