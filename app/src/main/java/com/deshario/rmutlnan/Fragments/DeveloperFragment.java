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

import com.deshario.rmutlnan.R;


/**
 * A simple {@link Fragment} subclass.
 */

public class DeveloperFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2 ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate developer_tab_view and setup Views
        View myview =  inflater.inflate(R.layout.developer_tab_view,null);
       // ((MainActivity) getActivity()).setActionBarTitle("พัฒนาการของลูกในครรภ์");
        tabLayout = (TabLayout) myview.findViewById(R.id.tabs);
        viewPager = (ViewPager) myview.findViewById(R.id.viewpager);

        //Set an Apater for the View Pager
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        final int[] ICONS = new int[] {
                R.drawable.ic_filter_1_white_24dp,
                R.drawable.ic_filter_2_white_24dp,
        };


        // SetupWithViewPager doesn't works without the runnable
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(ICONS[0]);
                tabLayout.getTabAt(1).setIcon(ICONS[1]);
            }
        });
        return myview;
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
                case 0 : return new Developer1();
                case 1 : return new Developer2();
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
                    return "นักพัฒนาคนที่ 1";
                case 1 :
                    return "นักพัฒนาคนที่ 2";
            }
            return null;
        }
    }

}


