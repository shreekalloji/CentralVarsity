package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.pojos.SearchUniversityPOJO;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter implements Filterable {
    SearchUniversityPOJO cartPojo;
    private ArrayList<String> mCountry;

    public MyAdapter(Context context, int textViewResourceId, SearchUniversityPOJO cartPojo) {
        super(context, textViewResourceId);
        this.cartPojo=cartPojo;
        mCountry = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mCountry.size();
    }

    @Override
    public SearchUniversityPOJO.ResponseBean getItem(int position) {
        return  cartPojo.getResponse().get(position);
    }


    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null) {
              //  filterResults.count = getCount();
                try{
                    //get data from the web
                    String term = constraint.toString();
                    for (int i=0;i<cartPojo.getResponse().size();i++){
                        mCountry.add(cartPojo.getResponse().get(i).getUniversity());
                    }

                }catch (Exception e){
                    Log.d("HUS","EXCEPTION "+e);
                }
                filterResults.values = cartPojo.getResponse();
                filterResults.count = cartPojo.getResponse().size();
            }

            // do some other stuff

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence contraint, FilterResults results) {
            if (results != null && results.count > 0) {
                notifyDataSetChanged();
            }
        }
    };


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.auto_complete_layout, parent, false);

        //get Country
       // Country contry = mCountry.get(position);

        TextView countryName = (TextView) view.findViewById(R.id.countryName);

        countryName.setText(cartPojo.getResponse().get(position).getUniversity());

        return view;
    }
}
