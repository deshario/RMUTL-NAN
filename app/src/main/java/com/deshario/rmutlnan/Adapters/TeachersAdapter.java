package com.deshario.rmutlnan.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.deshario.rmutlnan.MainActivity;
import com.deshario.rmutlnan.R;

import java.util.ArrayList;

public class TeachersAdapter extends BaseAdapter implements Filterable {
    Context mContext;
    ArrayList<Integer> teachers_id;
    ArrayList<String> prefix_names;
    ArrayList<String> names;
    ArrayList<String> surnames;
    ArrayList<String> faculties;
    ArrayList<String> img;
    public ItemFilter mFilter = new ItemFilter();
    public ArrayList<String>filteredData = null;
    private ArrayList<String>originalData = null;


   // public TeachersAdapter(Context context, ArrayList<String>strName, ArrayList<String> desc, int[] resId) {
    public TeachersAdapter(Context context, ArrayList<Integer> teachers_id, ArrayList<String>prefix_names, ArrayList<String> names, ArrayList<String> surnames, ArrayList<String> faculties, ArrayList<String> img) {
        this.mContext= context;
        this.teachers_id = teachers_id;
        this.prefix_names = prefix_names;
        this.names = names;
        this.surnames = surnames;
        this.faculties = faculties;
        this.img = img;
        this.filteredData = prefix_names;
        this.originalData = prefix_names;
    }

    public int getCount() {
        //return prefix_names.size();
        //return filteredData.size();
        if(filteredData==null){
            return 0;
        }else{
            return filteredData.size();
        }
    }

    public Object getItem(int position) {
       // return null;
        return filteredData.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater mInflater =(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.teachers_custom_listview, parent, false);

        TextView title = (TextView)view.findViewById(R.id.listview_item_title);
        TextView description = (TextView)view.findViewById(R.id.listview_item_short_description);
        ImageView imageView = (ImageView)view.findViewById(R.id.listview_image);

        title.setText(prefix_names.get(position)+" "+names.get(position)+" "+surnames.get(position));
        description.setText(faculties.get(position));
        String aa = genimage(img.get(position),prefix_names.get(position));
        imageView.setBackgroundResource(Integer.valueOf(aa));
        //imageView.setBackgroundResource(resId[position]);

        return view;
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

            int resID = mContext.getResources().getIdentifier(mDrawableName , "drawable", MainActivity.PACKAGE_NAME);
            return Integer.toString(resID);
        }
    }

    @Override
    public Filter getFilter() {
       // return null;
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final ArrayList<String> list = names;
            final ArrayList<String> list2 = surnames;

            int count = list.size();
            final ArrayList<String> nlist = new ArrayList<String>(count);

            String filterableString ;
            String filterableString1 ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                filterableString1 = list2.get(i);
                if (filterableString.toLowerCase().contains(filterString) || filterableString1.toLowerCase().contains(filterString)) {
                    nlist.add(filterableString);
                    nlist.add(filterableString1);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<String>) results.values;
            notifyDataSetChanged();
        }

    }
}