package com.deshario.rmutlnan.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.deshario.rmutlnan.R;
import com.deshario.rmutlnan.Teachers;

/**
 * Created by Deshario on 4/17/2017.
 */

public class TeacherFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3 ;
    public static int page;
    MyAdapter myAdapter;

    final int[] ICONS = new int[] {
            R.drawable.business,
            R.drawable.science,
            R.drawable.engineering,
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate developer_tab_view and setup Views
        View myview =  inflater.inflate(R.layout.teacher_tab_view,null);
        // ((MainActivity) getActivity()).setActionBarTitle("พัฒนาการของลูกในครรภ์");
        tabLayout = (TabLayout) myview.findViewById(R.id.tabs);
        viewPager = (ViewPager) myview.findViewById(R.id.viewpager);
        adap();
        return myview;
    }

    public void adap(){
        //Set an Apater for the View Pager
        myAdapter = new MyAdapter(getChildFragmentManager());
        viewPager.setAdapter(myAdapter);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0:
                        page = 0;
                        if(Teachers.searchView.isSearchOpen()){
                            Teachers.searchView.closeSearch();
                            //((SimpleAdapter) Faculty_BA.simpleAdapter).getFilter().filter("");
                        }
                        ((SimpleAdapter) Faculty_BA.simpleAdapter).getFilter().filter("");
                        break;
                    case 1:
                        page = 1;
                        if(Teachers.searchView.isSearchOpen()){
                            Teachers.searchView.closeSearch();
                            //((SimpleAdapter) Faculty_Science.sc_simpleAdapter).getFilter().filter("");
                        }
                        ((SimpleAdapter) Faculty_Science.sc_simpleAdapter).getFilter().filter("");
                        break;
                    case 2:
                        page = 2;
                        if(Teachers.searchView.isSearchOpen()){
                            Teachers.searchView.closeSearch();
                            //((SimpleAdapter) Faculty_Engineer.simpleAdapter_en).getFilter().filter("");
                        }
                        ((SimpleAdapter) Faculty_Engineer.simpleAdapter_en).getFilter().filter("");
                        break;
                    default:
                        page = 0;
//                        ((SimpleAdapter) Faculty_BA.simpleAdapter).getFilter().filter("");
//                        ((SimpleAdapter) Faculty_Science.sc_simpleAdapter).getFilter().filter("");
//                        ((SimpleAdapter) Faculty_Engineer.simpleAdapter_en).getFilter().filter("");

                }
                tabLayout.getTabAt(0).setIcon(ICONS[0]);
                tabLayout.getTabAt(1).setIcon(ICONS[1]);
                tabLayout.getTabAt(2).setIcon(ICONS[2]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // SetupWithViewPager doesn't works without the runnable
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(ICONS[0]);
                tabLayout.getTabAt(1).setIcon(ICONS[1]);
                tabLayout.getTabAt(2).setIcon(ICONS[2]);
            }
        });

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        // Return fragment with respect to Position
        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new Faculty_BA();
                case 1 : return new Faculty_Science();
                case 2 : return new Faculty_Engineer();

            }
            return null;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        // Returns the title of the tab according to the position.
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0 :
                    return "คณะบริหาร";
                case 1 :
                    return "คณะวิทย์";
                case 2 :
                    return "คณะวิศวกรรม";
            }
            return null;
        }
    }

}


