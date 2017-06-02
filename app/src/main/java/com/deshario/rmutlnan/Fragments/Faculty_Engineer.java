package com.deshario.rmutlnan.Fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.makeramen.roundedimageview.RoundedImageView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.deshario.rmutlnan.Adapters.TeachersAdapter;
import com.deshario.rmutlnan.CustomFonts;
import com.deshario.rmutlnan.MainActivity;
import com.deshario.rmutlnan.Models.Teachers;
import com.deshario.rmutlnan.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Faculty_Engineer extends Fragment {
    ListView en_listview;
    ArrayList<Teachers> listdata = new ArrayList<>();
    CircularImageView select_image;
    final int REQUEST_CODE_GALLERY = 999;

    ArrayList<String> prefix_names,prefix_names_searched,prefix_names_counter,deshario;
    ArrayList<Teachers> add_teachers = new ArrayList<Teachers>();
    ArrayList<String> names,names_searched;
    ArrayList<String> surnames,surnames_searched;
    ArrayList<String> img,img_Searched;
    ArrayList<String> faculties,faculties_searched;
    ArrayList<Integer> teachers_id;
    List<HashMap<String, String>> datalist,datalist_Searched;
    Cursor cursor;
    MaterialSearchView searchView;

    View Actionpositive;
    TextView prefix_t,name_t,surname_t,sex_t,faculty_t;
    ImageView img_profile;
    RoundedImageView profile_img;
    int count_records = 0;
    public static SimpleAdapter simpleAdapter_en;
    public static TeachersAdapter adapter;
    public static int en_counter = 0;


    public Faculty_Engineer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        new CustomFonts().onCreate();
        View view = inflater.inflate(R.layout.teacher_info, container, false);
        en_listview = (ListView)view.findViewById(R.id.list_view);
        dowork();
        return view;
    }



    public void add_data(){
        teachers_lists(0,"นาย","ก้องเกียรติ","ธนะมิตร","วิศวกรรมเครื่องกล","kongkiat.jpg");
        teachers_lists(1,"นาย","สิทธิบูรณ์","ศิริพรอัครชัย","วิศวกรรมเครื่องกล","null.png");
        teachers_lists(2,"นาย","สิทธากร","พลาอาด","วิศวกรรมเครื่องกล","null.png");
        teachers_lists(3,"นาย","สุเทพ","บุญมาบำรุง","วิศวกรรมเครื่องกล","suthep.jpg");
        teachers_lists(4,"นาย","อริยะ","แสนทวีสุข","วิศวกรรมเครื่องกล","ariya.jpg");
        teachers_lists(5,"น.ส.","กันยาพร","ไชยวงศ์","วิศวกรรมเครื่องกล","null.png");
        teachers_lists(6,"นาย","ณัฐพล","วิชาญ","วิศวกรรมเครื่องกล","nutthapon.jpg");
        teachers_lists(7,"นาย","สุรชัย","อิ้มทับ","วิศวกรรมเครื่องกล","surachai.jpg");
        teachers_lists(8,"นาย","เกรียงไกร","ธนะมิตร","วิศวกรรมเครื่องกล","null.png");
        teachers_lists(9,"นาย","ไพโรจน์","ปิยรังสรรค์","วิศวกรรมไฟฟ้า","null.png");
        teachers_lists(10,"นาย","วรรณกร","พรหมอารีย์","วิศวกรรมไฟฟ้า","wannakorn.jpg");
        teachers_lists(11,"นาย","ชาญยุทธ์","กาญจนพิบูลย์","วิศวกรรมไฟฟ้า","chanyuth.jpg");
        teachers_lists(12,"นาย","ประเสริฐ","ศรีพนม","วิศวกรรมไฟฟ้า","prasert.jpg");
        teachers_lists(13,"นาย","อรรนนท์ ","บัวศรี","วิศวกรรมไฟฟ้า","unnon.jpg");
        teachers_lists(14,"นาย","ไตรรัตน์","ปะทิ","วิศวกรรมไฟฟ้า","null.png");
        teachers_lists(15,"นาย","สถาพร","พิมสาร","วิศวกรรมอุตสาหการ","null.png");
        teachers_lists(16,"นาย","ศักดิ์สิทธิ์","โรจน์ฤทธากร","วิศวกรรมอุตสาหการ","saksith1.jpg");
        teachers_lists(17,"นาย","ณัฐพล"," กาบคำ","วิศวกรรมอุตสาหการ","nuttapon.jpg");
        teachers_lists(18,"นาย","เสกสรรค์","อ้วนสอาด","วิศวกรรมอุตสาหการ","null.png");
    }

    public String gen_gender(String gender){
        String sex = gender;
        if(sex == "นาย" || sex.equals("นาย")){
            return "ชาย";
        }else{
            return "หญิง";
        }
    }

    public String genimage(String image,String prefix){
        String imageName = image;
        String sex = prefix;
        if(imageName.equals("null.png") || imageName == "null.png" || imageName.equals("null.jpg") || imageName.equals("null.jpg")){
            if(sex == "นาย" || sex.equals("นาย")){
                return Integer.toString(R.mipmap.boy);
            }else{
                return Integer.toString(R.mipmap.female);
            }
        }else{
            // String imageName = "hello.png";
            String replace = imageName.replace('.','~');
            String[] split = replace.split("~");
            // System.out.println("Image name : " + split[0]);
            // System.out.println("Image extension : " + split[1]);
            String mDrawableName = split[0];

            int resID = getResources().getIdentifier(mDrawableName , "drawable", MainActivity.PACKAGE_NAME);
            return Integer.toString(resID);
        }
    }

    public String gen_sex(String gender){
        String mygender = gender;
        if(mygender.equals("นาย") || mygender == "Mr"){
            return "ชาย";
        }else{
            return "หญิง";
        }
    }

    public void teachers_lists(int id, String prefix_name, String name, String surname, String faculty, String img) {
        Teachers teachers_data = new Teachers(id,prefix_name, name, surname, faculty, img);
        add_Teachers_info(teachers_data);
    }

    public void add_Teachers_info(Teachers data){
        prefix_names.add(data.getPrefix_name());
        faculties.add(data.getFaculty());
        surnames.add(data.getSurname());
        names.add(data.getName());
        img.add(data.getImg());
        teachers_id.add(data.getId());
        // teachers_id.add(count_records);
        count_records++;
    }

    public void dowork(){
        prefix_names = new ArrayList<String>();
        faculties = new ArrayList<String>();
        surnames = new ArrayList<String>();
        names = new ArrayList<String>();
        img = new ArrayList<String>();
        teachers_id = new ArrayList<Integer>();
        add_data();

        en_counter = teachers_id.size();
        if(en_counter == 0){
            //add_data();
            Toast.makeText(getActivity(), "ไม่มีข้อมูล", Toast.LENGTH_SHORT).show();
        }

        datalist = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < en_counter; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("teacher_name", prefix_names.get(i)+" "+names.get(i)+" "+surnames.get(i));
            hm.put("teacher_faculty", ""+faculties.get(i));
            hm.put("teacher_img",genimage(img.get(i),prefix_names.get(i)));
            hm.put("teacher_id",""+teachers_id.get(i));
            datalist.add(hm);
        }



        String[] from = {"teacher_img", "teacher_name", "teacher_faculty"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        simpleAdapter_en = new SimpleAdapter(getContext(), datalist, R.layout.teachers_custom_listview, from, to);
        // listview = (ListView)view.findViewById(R.id.list_view);
        en_listview.setAdapter(simpleAdapter_en);
        en_listview.setTextFilterEnabled(true);

        en_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String id_teacher = null;
                Map<String, Object> map = (Map<String, Object>)en_listview.getItemAtPosition(position);
                final String map_week = (String) map.get("teacher_name");
                id_teacher = (String) map.get("teacher_id");
                //Toast.makeText(Teachers_Info.this, ""+aa, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), "teacher_id_sc :: "+id_teacher, Toast.LENGTH_SHORT).show();
                display(id_teacher);
            }
        });
        en_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return true;
            }
        });

//        adapter = new TeachersAdapter(getContext(), teachers_id, prefix_names,names,surnames,faculties,img);
//
//        en_listview.setAdapter(adapter);
//        en_listview.setTextFilterEnabled(true);
////        en_listview.setOnItemClickListener(new OnItemClickListener() {
////            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
////
////            }
////        });



    }

    public void display(String key_){
        int key = Integer.valueOf(key_);
        key_ = null;
        boolean wrapInScrollView = true;
        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                //.title("ข้อมูลอาจารย์ : "+key)
                .customView(R.layout.teachers_lists, true)
                .positiveText("ตกลง")
                .titleColorRes(R.color.primary_bootstrap)
                .positiveColorRes(R.color.colorPrimaryDark)
                .negativeColorRes(R.color.danger_bootstrap)
                .widgetColorRes(R.color.info_bootstrap)
                .buttonRippleColorRes(R.color.orange)

                .canceledOnTouchOutside(false)
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .build();

        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.8f;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

        Actionpositive = dialog.getActionButton(DialogAction.POSITIVE);

        prefix_t = (TextView)dialog.getCustomView().findViewById(R.id.prefix_text);
        name_t = (TextView)dialog.getCustomView().findViewById(R.id.name_text);
        surname_t = (TextView)dialog.getCustomView().findViewById(R.id.surname_text);
        sex_t = (TextView)dialog.getCustomView().findViewById(R.id.sex_text);
        faculty_t = (TextView)dialog.getCustomView().findViewById(R.id.faculty_text);
        //img_profile = (ImageView)dialog.getCustomView().findViewById(R.id.profile_img);
        profile_img = (RoundedImageView) dialog.getCustomView().findViewById(R.id.profile_img);

        String _Prefix_ = prefix_names.get(key);
        String _Name_ = names.get(key);
        String _Surname_ = surnames.get(key);
        String _Sex_ = gen_sex(prefix_names.get(key));
        String _Faculty_ = faculties.get(key);
        Integer img_profile_ = Integer.valueOf(genimage(img.get(key),prefix_names.get(key)));

        //img_profile.setBackgroundResource(img_profile_);
        profile_img.setBackgroundResource(img_profile_);
        prefix_t.setText(" คำนามหน้า : " + _Prefix_);
        name_t.setText(" ชื่อ : " + _Name_);
        surname_t.setText(" นามสกุล : " + _Surname_);
        sex_t.setText(" เพศ : " + _Sex_);
        faculty_t.setText(" สาขา : " + _Faculty_);

        dialog.show();
    }

    public void update_cursor(){
        datalist.clear();
        prefix_names.clear();
        names.clear();
        surnames.clear();
        faculties.clear();
        prefix_names_searched.clear();
        faculties_searched.clear();
        surnames_searched.clear();
        names_searched.clear();
        img_Searched.clear();
        dowork();
    }

}
