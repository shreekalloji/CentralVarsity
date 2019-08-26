package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.dao.AdapterCallBack;
import com.iprismtech.studentvarsity.pojos.SubjectsModel;


import java.util.ArrayList;
import java.util.List;

public class RaviAdapeter extends RecyclerView.Adapter<RaviAdapeter.YearAdapterView> {
    Context context;
    ArrayList<SubjectsModel> subjectsModels;
    List<String> myList;
    AdapterCallBack adapterCallBack;

    public RaviAdapeter(Context context, ArrayList<SubjectsModel> subjectsModels, List<String> myList, AdapterCallBack adapterCallBack) {
        this.context = context;
        this.subjectsModels = subjectsModels;
        this.myList = myList;
        this.adapterCallBack = adapterCallBack;

    }

    @NonNull
    @Override
    public YearAdapterView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.subject_item, viewGroup, false);
        return new YearAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull YearAdapterView yearAdapterView, int i) {
        final SubjectsModel subjectsModel = subjectsModels.get(i);

        yearAdapterView.subjectName.setText(subjectsModel.getSubject());
        if (subjectsModel.getCheked()) {
            yearAdapterView.subjectCheck.setChecked(true);
        } else {
            yearAdapterView.subjectCheck.setChecked(false);
        }

        yearAdapterView.subjectCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String id = subjectsModel.getId();
                System.out.println("checked id" + id + "boolean" + isChecked);
                if (adapterCallBack != null) {
                    adapterCallBack.clickevent(id, isChecked);
                }
                /*for (int j = 0; j < SemisterAdapeter.filetr_semisterModels.size(); j++) {
                    for(int  k = 0;k < SemisterAdapeter.filetr_semisterModels.get(j).getSubjectsModels().size();k++){
                        SubjectsModel subjectsModel1=SemisterAdapeter.filetr_semisterModels.get(j).getSubjectsModels().get(k);

                        if(subjectsModel1.getId().equalsIgnoreCase(id)){
                            SemisterAdapeter.filetr_semisterModels.get(j).getSubjectsModels().get(k).setCheked(true);
                        }

                    }

                }*/
            }
        });
       /* for (int j = 0; j <SemisterAdapeter.checkedModels.size() ; j++) {
            CheckedModel checkedModel=SemisterAdapeter.checkedModels.get(i);
            String id=subjectsModel.getId();
            System.out.println("checked models"+ id+"kmdklflm"+checkedModel.getId());
            if(checkedModel.getChecked()){
                yearAdapterView.subjectCheck.setChecked(true);
            }else {
                yearAdapterView.subjectCheck.setChecked(false);
            }

        }*/
        /*for (int j = 0; j < myList.size(); j++) {
            if (myList.get(j).equalsIgnoreCase(subjectsModel.getId())) {
               // cartPojo.get(position).setSelected(true);
                yearAdapterView.subjectCheck.setChecked(true);
            }
        }*/


    }

    @Override
    public int getItemCount() {
        return subjectsModels.size();
    }

    public class YearAdapterView extends RecyclerView.ViewHolder {

        TextView subjectName;
        CheckBox subjectCheck;

        public YearAdapterView(View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subjectName);
            subjectCheck = itemView.findViewById(R.id.subjectCheck);
        }

    }

}
