package com.deshario.rmutlnan;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.deshario.rmutlnan.Fragments.DeveloperFragment;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Deshario on 4/4/2017.
 */

public class Developer extends AppCompatActivity {
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developer_tablayout);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(" ผู้จัดทำ");
        getSupportActionBar().setIcon(R.drawable.ic_developer_mode_white_24dp);
        new CustomFonts().onCreate();

        Fragment fragment_to_open;
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragment_to_open = new DeveloperFragment();
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
        getMenuInflater().inflate(R.menu.about_app,menu);
        //MenuItem item = menu.findItem(R.id.action_about_app);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_about_app:
                about();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void about(){
        String details = " ชื่อแอพ : RMUTL NAN \n\n เวอร์ชัน : v1 \n\n ภาษาที่ใช้ : Java ";
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("เกียวกับแอพ")
                .content(details)
                .positiveText("ตกลง")
                .show();

        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.7f;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
