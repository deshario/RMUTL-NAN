package com.deshario.rmutlnan.Fragments;


import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
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

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class Faculty_BA extends Fragment {
    ListView listview;
    ArrayList<Teachers> listdata = new ArrayList<>();
    CircularImageView select_image;
    final int REQUEST_CODE_GALLERY = 999;

    ArrayList<String> ba_prefix_names,ba_prefix_names_searched,ba_prefix_names_counter,deshario;
    ArrayList<Teachers> add_teachers = new ArrayList<Teachers>();
    ArrayList<String> names,names_searched;
    ArrayList<String> surnames,surnames_searched;
    ArrayList<String> img,img_Searched;
    ArrayList<String> faculties,faculties_searched;
    ArrayList<Integer> teachers_id;
    ArrayList<String> gender,gender_searched;
    Integer faculties_BA,faculties_Arts,faculties_Accounting; //สาขาบริหารธุรกิจ , สาขาศิลปศาสตร์ , สาขาการบัญชี

    Spinner prefix_spinner,faculties_spinner;
    View positiveAction;
    EditText user_name,user_surname;
    String user_prefix,user_faculty;
    String name_string,surname_string,prefix_string,faculties_string,sex_string;
    List<HashMap<String, String>> datalist,datalist_Searched;
    Cursor cursor;
    MaterialSearchView searchView;

    View Actionpositive;
    TextView prefix_t,name_t,surname_t,sex_t,faculty_t;
    ImageView img_profile;
    RoundedImageView profile_img;
    int count_records = 0;
    public static SimpleAdapter simpleAdapter;
    public static TeachersAdapter adapter;
    public static int ba_counter = 0;

    public Faculty_BA() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        new CustomFonts().onCreate();
        View view = inflater.inflate(R.layout.teacher_info, container, false);
        listview = (ListView)view.findViewById(R.id.list_view);
        dowork();
        return view;
    }


    public void add_data(){
        teachers_lists(0,"นาย","สรศักดิ์","เชี่ยวชาญ","แผนกภาษาตะวันออก","sorasak.jpg");
        teachers_lists(1,"นาย","เสน่ห์" ,"สวัสดิ์", "แผนกนันทนาการ" ,"null.png");
        teachers_lists(2,"น.ส.","วันวิภา" ,"คำมงคล", "วิชาการบัญชี" ,"wanwipa.jpg");
        teachers_lists(3,"น.ส.","นงนุช" ,"พรหมวีระไชย", "วิชาระบบสารสนเทศทางคอมพิวเตอร์","nongnuch.jpg");
        teachers_lists(4,"นาย","ชูเกียรติ" ,"เต็งไตรสรณ์", " แผนกสังคมศาสตร์","chukiat.jpg");
        teachers_lists(5,"นาง","สุขสวรรค์ " ," คำวงศ์", "วิชาระบบสารสนเทศทางคอมพิวเตอร์" ,"suksawan.jpg");
        teachers_lists(6,"นาง","ศิริลักษณ์  " ,"นรินทร์รัตน์ ", "แผนกภาษาตะวันตก " ,"null.png");
        teachers_lists(7,"นาง","เกตสุดา " ,"พานิชกุล ", "วิชาการจัดการ","null.png");
        teachers_lists(8,"นาง","เพ็ญจันทร์" ,"รวิยะวงศ์", "วิชาการจัดการ","null.png");
        teachers_lists(9,"นาย","อนันต์ " ,"มงคลเกียรติชัย", "วิชาระบบสารสนเทศทางคอมพิวเตอร์" ,"anun.jpg");
        teachers_lists(10,"น.ส.","กนกรัตน์" ,"ดวงพิกุล", "วิชาการจัดการ","kanokrat.jpg");
        teachers_lists(11,"นาง","นันทา " ,"เติมสมบัติถาวร"," แผนกสังคมศาสตร์", "nantar.jpg" );
        teachers_lists(12,"นาย","ธวัชชัย " ,"ซ้อนยนต์","วิชาการตลาด ", "null.jpg");
        teachers_lists(13,"นาง","วรรณิดา" ,"ชินบุตร","วิชาการตลาด", "wannida.jpg");
        teachers_lists(14,"นาง","สุกัญญา" ,"แสงเดือน"," ศิลปศาสตร์", "null.png");
        teachers_lists(15,"น.ส.","เมตตา" ,"ตาละลักษณ์"," วิชาระบบสารสนเทศทางคอมพิวเตอร์", "mettar.jpg");
        teachers_lists(16,"นาย","สมบูรณ์" ,"กุมาร","วิชาการบัญชี ", "somboon.jpg");
        teachers_lists(17,"นาง","ชุติสร" ," แก้วบรรจง","วิชาการจัดการ", "shutisorn.jpg");
        teachers_lists(18,"นาง","จุไรรัตน์" ,"สวัสดิ์","แผนกภาษาตะวันตก ", "jurairat.jpg");
        teachers_lists(19,"น.ส.","จิตรา " ,"ปั้นรูป","วิชาการตลาด", "null.png");
        teachers_lists(20,"นาง","ปิยะนุช" ,"สินันตา","แผนกสังคมศาสตร์ ", "piyanuch.jpg");
        teachers_lists(21,"นาย","คทาวุธ" ,"แก้วบรรจง","วิชาระบบสารสนเทศทางคอมพิวเตอร์ ", "kwan.jpg");
        teachers_lists(22,"นาย","ณัฐพันธ์" ,"ปัญญโรจน์","วิชาการจัดการ", "null.png");
        teachers_lists(23,"นาง","ธัญทิพย์  " ,"ศิริพรอัครชัย","วิชาการจัดการ", "null.png");
        teachers_lists(24,"น.ส.","ฐาณิญา" ,"อิสสระ"," วิชาการตลาด", "null.png");
        teachers_lists(25,"นาง","ธิตินันท์" ,"กุมาร","วิชาการบัญชี", "thitinun.jpg");
        teachers_lists(26,"นาง","อัจฉราภรณ์ " ,"พูลยิ่ง","วิชาการบัญชี", "autchara.jpg");
        teachers_lists(27,"น.ส.","จารุนันท์" ," เมธะพันธุ์","แผนกภาษาตะวันออก", "jarunan.jpg");
        teachers_lists(28,"นาง","พัชราภรณ์" ,"หงษ์สิบสอง","วิชาระบบสารสนเทศทางคอมพิวเตอร์", "patcharaporn.jpg");
        teachers_lists(29,"น.ส.","มินตรา " ,"ไชยชนะ","แผนกภาษาตะวันตก", "null.png");
        teachers_lists(30,"น.ส.","ขนิษฐา" ,"สุวรรณประชา","แผนกภาษาตะวันตก", "kanittha.jpg");
        teachers_lists(31,"นาย","ศักรินทร์" ,"ณ น่าน","ประจำศิลปศาสตร์", "null.png");
        teachers_lists(32,"น.ส.","อภิญญา" ,"กันธิยะ","วิชาการตลาด", "apinya.jpg");
        teachers_lists(33,"นาย","กิจจาณัฏฐ์" ,"ตั้งจิตนุสรณ์","แผนกภาษาตะวันตก", "null.png");
        teachers_lists(34,"นาย","สิทธิโชค" ,"จันทร์รัตนสิริ","วิชาระบบสารสนเทศทางคอมพิวเตอร์ ", "sitthichoke.jpg");
        teachers_lists(35,"นาง","ดวงสมร" ,"พลเยี่ยม","วิชาการจัดการ", "duangsamon.jpg");
        teachers_lists(36,"นาย","ธีรวุฒิ" ,"ปิงยศ","แผนกนันทนาการ", "teerawut.jpg");
        teachers_lists(37,"น.ส.","ปรียานุช" ,"ดีพรมกุล","วิชาการจัดการ", "preeyanuch.JPG");
        teachers_lists(38,"Mr","Karl" ,"Walsh","แผนกภาษาตะวันตก", "karlwalsh.JPG");
        teachers_lists(39,"น.ส.","พรลภัส" ,"วิชา","แผนกภาษาตะวันออก", "null.png");
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
        ba_prefix_names.add(data.getPrefix_name());
        faculties.add(data.getFaculty());
        surnames.add(data.getSurname());
        names.add(data.getName());
        img.add(data.getImg());
        teachers_id.add(data.getId());
        //teachers_id.add(count_records);
        count_records++;
    }

    public void dowork(){
        ba_prefix_names = new ArrayList<String>();
        faculties = new ArrayList<String>();
        surnames = new ArrayList<String>();
        names = new ArrayList<String>();
        img = new ArrayList<String>();
        teachers_id = new ArrayList<Integer>();
        add_data();

        ba_counter = teachers_id.size();
        if(ba_counter == 0){
            //add_data();
            Toast.makeText(getActivity(), "ไม่มีข้อมูล", Toast.LENGTH_SHORT).show();
        }


        datalist = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < ba_counter; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("teacher_name", ba_prefix_names.get(i)+" "+names.get(i)+" "+surnames.get(i));
            hm.put("teacher_faculty", "สาขา : "+faculties.get(i));
            hm.put("teacher_img",genimage(img.get(i),ba_prefix_names.get(i)));
            hm.put("teacher_id",""+teachers_id.get(i));
            datalist.add(hm);
        }

        String[] from = {"teacher_img", "teacher_name", "teacher_faculty"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        simpleAdapter = new SimpleAdapter(getContext(), datalist, R.layout.teachers_custom_listview, from, to);
       // listview = (ListView)view.findViewById(R.id.list_view);
        listview.setAdapter(simpleAdapter);
        listview.setTextFilterEnabled(true);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String id_teacher = null;
                Map<String, Object> map = (Map<String, Object>)listview.getItemAtPosition(position);
                final String map_week = (String) map.get("teacher_name");
                id_teacher = (String) map.get("teacher_id");
                //Toast.makeText(Teachers_Info.this, ""+aa, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), "teacher_id :: "+id_teacher, Toast.LENGTH_SHORT).show();
                display(id_teacher);
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return true;
            }
        });

        //adapter = new TeachersAdapter(getContext(), teachers_id, ba_prefix_names,names,surnames,faculties,img);
       // listview.setAdapter(adapter);
        //listview.setTextFilterEnabled(true);
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

        String _Prefix_ = ba_prefix_names.get(key);
        String _Name_ = names.get(key);
        String _Surname_ = surnames.get(key);
        String _Sex_ = gen_sex(ba_prefix_names.get(key));
        String _Faculty_ = faculties.get(key);
        Integer img_profile_ = Integer.valueOf(genimage(img.get(key),ba_prefix_names.get(key)));

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
        ba_prefix_names.clear();
        names.clear();
        surnames.clear();
        faculties.clear();
        ba_prefix_names_searched.clear();
        faculties_searched.clear();
        surnames_searched.clear();
        names_searched.clear();
        img_Searched.clear();
        dowork();
    }

    public void inform_added_or_updated(String sweettitle, String sweetcontent) {
        //Toast.makeText(this, " "+sweettitle+"\n "+sweetcontent, Toast.LENGTH_LONG).show();
        //Toast.makeText(this, ""+sweetcontent, Toast.LENGTH_LONG).show();
        SweetAlertDialog alert_user = new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE);
        alert_user.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        alert_user.setTitleText(sweettitle);
        alert_user.setConfirmText("ตกลง");
        alert_user.setContentText(sweetcontent);
        alert_user.setCancelable(true);
        alert_user.show();

        WindowManager.LayoutParams lp = alert_user.getWindow().getAttributes();
        lp.dimAmount = 0.8f;
        alert_user.getWindow().setAttributes(lp);
        alert_user.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
    }


}
