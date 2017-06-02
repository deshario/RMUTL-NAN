package com.deshario.rmutlnan.Fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.deshario.rmutlnan.CustomFonts;
import com.deshario.rmutlnan.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mikhaellopez.circularimageview.CircularImageView;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class Developer2 extends Fragment {

    public ListView listview;
    public ArrayAdapter arrayAdapter;
    public String[] data = {"นาย จักรกฤษณ์ วรวัฒน์","รหัสนักศึกษา 56341204039-3","สาขาระบบสารสนเทศทางคอมพิวเตอร์","คณะบริหารธุรกิจและศิลปะศาสตร์"};
    ImageView _fb,_phone,_mail;
    CircularImageView profile_img;
    ImageView img_profile;

    public Developer2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        new CustomFonts().onCreate();
        View view = inflater.inflate(R.layout.fragment_developer2, container, false);
        _fb = (ImageView)view.findViewById(R.id.fb);
        _mail = (ImageView)view.findViewById(R.id.mail);
        _phone = (ImageView)view.findViewById(R.id.callnow);
        profile_img = (CircularImageView)view.findViewById(R.id.circular_image_view);

        profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_viewer();
            }
        });


        profile_img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                img_viewer();
                return false;
            }
        });

        _fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/bossmothrfckr";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        _mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail("jakkrit.vo@gmail.com");
            }
        });
        _phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call_developer();
            }
        });
        return view;
    }

    protected void sendEmail(String email_to) {

        String to = email_to;
        String subject = "ติดต่อจากแอพ RMUTL NAN";
        String message = "";

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
        //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
        //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        //need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

    public void img_viewer(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (getActivity()).getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.img_viewer, null);
        dialogBuilder.setView(dialogView);

        img_profile = (ImageView)dialogView.findViewById(R.id.img);
        Integer img_src = Integer.valueOf(R.drawable.dev2);
        img_profile.setBackgroundResource(img_src);
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        alertDialog.setCancelable(true);

        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 0.7f;
        alertDialog.getWindow().setAttributes(lp);
        alertDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
    }

    protected void call_developer(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0804955961"));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},101);
            return;
        }else{
            startActivity(callIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 101) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                call_developer();
            }else{
                Toast.makeText(getActivity(), "สิทธิในการเข้าถึงล้มเหลว", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
