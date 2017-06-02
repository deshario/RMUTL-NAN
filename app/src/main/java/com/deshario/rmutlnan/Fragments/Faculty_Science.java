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
public class Faculty_Science extends Fragment {
    ListView sc_listview;
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
    public static SimpleAdapter sc_simpleAdapter;
    public static int sc_counter = 0;

    public Faculty_Science() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        new CustomFonts().onCreate();
        View view = inflater.inflate(R.layout.teacher_info, container, false);
        sc_listview = (ListView)view.findViewById(R.id.list_view);
        dowork();
        return view;
    }



    public void add_data(){
        teachers_lists(0,"นาย","คมสัน","อำนวยสิทธิ์","พืชศาสตร์","komsan.jpg");
        teachers_lists(1,"นาง","สุภาพร","ชุติประพฤทธิ์","พืชศาสตร์","supaporn.jpg");
        teachers_lists(2,"นาง","ปราโมทย์","ทิมขำ","พืชศาสตร์","null.png");
        teachers_lists(3,"นาย ","สามารถ","ไชยพันธุ์","พืชศาสตร์","samart.jpg");
        teachers_lists(4,"นาย","เผดิมศิลป์","รามศิริ","พืชศาสตร์","padermsin.jpg");
        teachers_lists(5,"นาย","พิชัย","สุรพรไพบูลย์","พืชศาสตร์","pichai.jpg");
        teachers_lists(6,"นาง","พิกุล","สุรพรไพบูลย์","พืชศาสตร์","pikul.jpg");
        teachers_lists(7,"นาย","แสงแก้ว","คำกวน","พืชศาสตร์","saengkaew.jpg");
        teachers_lists(8,"น.ส.","กาญจนา","รุจิพจน์","พืชศาสตร์","kanchana.jpg");
        teachers_lists(9,"นาย","อนุชา","จันทรบูรณ์","พืชศาสตร์","anucha.jpg");
        teachers_lists(10,"นาย","ภาณุพงศ์","สิทธิวุฒิ","พืชศาสตร์","panupong.jpg");
        teachers_lists(11,"น.ส.","พรรณทิวา ","ใจจะดี","พืชศาสตร์","puntiwa.jpg");
        teachers_lists(12,"นาย","พิเชษฐ์ ","บัวซอย","พืชศาสตร์","pichet.jpg");
        teachers_lists(13,"นาย","บรรจง","อูปแก้ว","พืชศาสตร์","bunjong.jpg");
        teachers_lists(14,"นาย","เพลิฬ","สายปาระ","แผนกวิชาคณิตศาสตร์","plert.jpg");
        teachers_lists(15,"นาย","วิโรจน์","มงคลเทพ","แผนกวิชาคณิตศาสตร์","null.png");
        teachers_lists(16,"นาย","กฤษฎา","ยาใจ","แผนกวิชาคณิตศาสตร์","kritsada.jpg");
        teachers_lists(17,"นาย","ชัชชัย","ดีสุหล้า","แผนกวิชาคณิตศาสตร์","chutchai.jpg");
        teachers_lists(18,"นาย","ศินุพล","พิมพ์พก","แผนกวิชาฟิสิกส์","sinupol.jpg");
        teachers_lists(19,"นาย","กาศรี","นามเคน","แผนกวิชาเคมี","null.png");
        teachers_lists(20,"นาย","ประสงค์","เหลี่ยมโสภณ","แผนกวิชาเคมี","prasong.jpg");
        teachers_lists(21,"น.ส.","รัชณีภรณ์","อิ่นคำ","แผนกวิชาเคมี","null.png");
        teachers_lists(22,"นาย","วิรัน","วิสุทธิธาดา","แผนกวิชาเคมี","wiran.jpg");
        teachers_lists(23,"นาง","เจนจิรา","ลานแก้ว","แผนกวิชาเคมี","jenjira.jpg");
        teachers_lists(24,"นาง","สวาท","สายปาระ","แผนกวิชาชีววิทยา","swat.jpg");
        teachers_lists(25,"น.ส.","ประชุมพร ","ชัยศรี","แผนกวิชาชีววิทยา","prachumporn.jpg");
        teachers_lists(26,"น.ส.","พรรณพร"," กุลมา","แผนกวิชาชีววิทยา","punnaporn.jpg");
        teachers_lists(27,"นาย","ปกรณ์","จันทร์อินทร์","วิชาเทคโนโลยีคอมฯ","null.png");
        teachers_lists(28,"นาง","นงนุช ","เกตุ้ย","วิชาเทคโนโลยีคอมฯ","null.png");
        teachers_lists(29,"นาย","ปกรณ์","สุนทรเมธ","วิชาเทคโนโลยีคอมฯ","pakorn.jpg");
        teachers_lists(30,"น.ส.","ศิริลักษณ์","แก้วศิริรุ่ง","วิชาเทคโนโลยีคอมฯ","silirak.jpg");
        teachers_lists(31,"น.ส.","ขนิษฐา","หอมจันทร์","วิชาเทคโนโลยีคอมฯ","null.png");
        teachers_lists(32,"นาย","สุวรรณ","ช่างกลึงดี","สัตวศาสตร์และประมง ","null.png");
        teachers_lists(33,"นาย","เกชา","คูหา","สัตวศาสตร์และประมง ","kaecha.jpg");
        teachers_lists(34,"นาย","กฤษณธร","สินตะละ","สัตวศาสตร์และประมง","null.jpg");
        teachers_lists(35,"นาย","อมรชัย","ล้อทองคำ","สัตวศาสตร์และประมง","amonchai.jpg");
        teachers_lists(36,"นาย","ประมวล","เติมสมบัติถาวร","สัตวศาสตร์และประมง ","pramuan.jpg");
        teachers_lists(37,"นาง","เจียมจิตร ","ช่างสาร","สัตวศาสตร์และประมง","jiamjit.jpg");
        teachers_lists(38,"นาง","วิไลพร","จันทร์ไชย","สัตวศาสตร์และประมง ","null.png");
        teachers_lists(39,"นาย","ชนินทร์","แก้วมณี","สัตวศาสตร์และประมง","null.png");
        teachers_lists(40,"นาย","เอกชัย","ดวงใจ","สัตวศาสตร์และประมง","null.png");
        teachers_lists(41,"น.ส.","รัชนี","บัวระภา","สัตวศาสตร์และประมง ","null.png");
        teachers_lists(42,"น.ส.","สุธาทิพย์","ไชยวงศ์","สัตวศาสตร์และประมง ","null.png");
        teachers_lists(43,"นาย","จุลทรรศน์","คีรีแลง","สัตวศาสตร์และประมง","null.png");
        teachers_lists(44,"น.ส.","เชาวลีย์","ใจสุข","สัตวศาสตร์และประมง","null.png");
        teachers_lists(45,"น.ส.","มลิวรรณ์","กิจชัยเจริญ","อุตสาหกรรมเกษตร","maliwan.jpg");
        teachers_lists(46,"นาง","ปิยะนุช","รสเครือ","อุตสาหกรรมเกษตร","piyanuch1.jpg");
        teachers_lists(47,"นาย ","ประกิต","ทิมขำ","อุตสาหกรรมเกษตร","prakit.jpg");
        teachers_lists(48,"นาย","เสกสรร","วงศ์ศิริ","อุตสาหกรรมเกษตร","null.png");
        teachers_lists(49,"นาย","นพรัตน์","จันทร์ไชย","อุตสาหกรรมเกษตร","nopparat.jpg");
        teachers_lists(50,"น.ส.","สุทธิดา"," ปัญญาอินทร์","อุตสาหกรรมเกษตร","null.png");
        teachers_lists(51,"น.ส.","สุภาวดี","ศรีแย้ม","อุตสาหกรรมเกษตร","supawadi.jpg");
        teachers_lists(52,"นาง","บุษบา","มะโนแสน","อุตสาหกรรมเกษตร","butsaba.jpg");
        teachers_lists(53,"น.ส.","จิรรัชต์","กันทะขู้","อุตสาหกรรมเกษตร","jirarat.jpg");
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

        sc_counter = teachers_id.size();
        if(sc_counter == 0){
            //add_data();
            Toast.makeText(getActivity(), "ไม่มีข้อมูล", Toast.LENGTH_SHORT).show();
        }


        datalist = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < sc_counter; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("teacher_name", prefix_names.get(i)+" "+names.get(i)+" "+surnames.get(i));
            hm.put("teacher_faculty", "สาขา : "+faculties.get(i));
            hm.put("teacher_img",genimage(img.get(i),prefix_names.get(i)));
            hm.put("teacher_id",""+teachers_id.get(i));
            datalist.add(hm);
        }

        String[] from = {"teacher_img", "teacher_name", "teacher_faculty"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        sc_simpleAdapter = new SimpleAdapter(getContext(), datalist, R.layout.teachers_custom_listview, from, to);
        // listview = (ListView)view.findViewById(R.id.list_view);
        sc_listview.setAdapter(sc_simpleAdapter);
        sc_listview.setTextFilterEnabled(true);

        sc_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String id_teacher = null;
                Map<String, Object> map = (Map<String, Object>)sc_listview.getItemAtPosition(position);
                final String map_week = (String) map.get("teacher_name");
                id_teacher = (String) map.get("teacher_id");
                //Toast.makeText(Teachers_Info.this, ""+aa, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), "teacher_id_sc :: "+id_teacher, Toast.LENGTH_SHORT).show();
                display(id_teacher);
            }
        });
        sc_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return true;
            }
        });

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


        //Cursor ct = DB_Manager.getTeacher_Data(key);
//        String _U_ID = ct.getString(ct.getColumnIndexOrThrow("_id"));
//        String _Prefix_ = ct.getString(ct.getColumnIndexOrThrow("prefix_name"));
//        String _Name_ = ct.getString(ct.getColumnIndexOrThrow("name"));
//        String _Surname_ = ct.getString(ct.getColumnIndexOrThrow("surname"));
//        String _Sex_ = ct.getString(ct.getColumnIndexOrThrow("sex"));
//        String _Faculty_ = ct.getString(ct.getColumnIndexOrThrow("faculty"));

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
