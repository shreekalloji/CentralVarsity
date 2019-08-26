package com.iprismtech.studentvarsity.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.dao.AdapterCallBack;

import com.iprismtech.studentvarsity.pojos.SemisterModel;
import com.iprismtech.studentvarsity.pojos.SubjectsModel;
import com.iprismtech.studentvarsity.sharepref.UserSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SemisterAdapeter extends RecyclerView.Adapter<SemisterAdapeter.YearAdapterView> implements AdapterCallBack {
    ArrayList<SemisterModel> semisterModels;
    Context context;
    UserSession userSession;
    List<String> myList;
 public static    ArrayList<SemisterModel> filetr_semisterModels;


    AdapterCallBack adapterCallBack;

    public SemisterAdapeter(Context context, ArrayList<SemisterModel> semisterModels) {
        this.context=context;
        this.semisterModels=semisterModels;
        userSession=new UserSession(context);

        filetr_semisterModels = new ArrayList<SemisterModel>();
        filetr_semisterModels.clear();
        this.filetr_semisterModels.addAll(this.semisterModels);
        adapterCallBack=this;

    }
    public void filter(final String text) {
// Searching could be complex..so we will dispatch it to a different thread...
        new Thread(new Runnable() {
            @Override
            public void run() {
// Clear the filter list
// If there is no search value, then add all original list items to filter list
                filetr_semisterModels.clear();
                if (TextUtils.isEmpty(text)) {
                    filetr_semisterModels.addAll(semisterModels);
                } else {
// Iterate in the original List and add it to filter list...
                    ArrayList<SubjectsModel>  subjectsModels =null;

                    for (int i = 0; i < semisterModels.size(); i++) {

                        subjectsModels = new ArrayList<>();
                        for (int k = 0; k < semisterModels.get(i).getSubjectsModels().size(); k++) {
                            SubjectsModel subjectsModel = semisterModels.get(i).getSubjectsModels().get(k);

                            String subject = subjectsModel.getSubject();
                            if (subjectsModel.getSubject().toLowerCase().contains(text.toLowerCase())) {
                                System.out.println("filetr_semisterModels"+text  +"  "+subjectsModel.getSubject());




                                String id = subjectsModel.getId();

                                String subject12 = subjectsModel.getSubject();
                                Boolean checked=subjectsModel.getCheked();


                                SubjectsModel subjectsModel123 = new SubjectsModel(id, subject12 ,checked);
                                subjectsModels.add(subjectsModel123);

                                System.out.println("subjectsModels"+subjectsModels.size());


                               // System.out.println("filetr_semisterModels" + filetr_semisterModels.size() +subject12);
                            }

                        }
                        SemisterModel semisterModel = new SemisterModel(semisterModels.get(i).getSemester(), subjectsModels);

                        filetr_semisterModels.add(semisterModel);


                    }



/*
                    for (Writers_Pojo item : location_pojos) {
                        if (item.getF_name().toLowerCase().contains(text.toLowerCase())) {
// Adding Matched items
                            filteredList.add(item);
                        }

                    }
*/

                }
// Set on UI Thread
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
// Notify the List that the DataSet has changed...
                        notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }


    @NonNull
    @Override
    public YearAdapterView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.semester_item, viewGroup, false);
        return new YearAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull YearAdapterView yearAdapterView, int i) {

        SemisterModel semisterModel=filetr_semisterModels.get(i);

            yearAdapterView.semesterName.setVisibility(View.VISIBLE);
            yearAdapterView.semesterName.setText("Semister "+semisterModel.getSemester());
       /* }else {
            yearAdapterView.semesterName.setVisibility(View.GONE);
        }*/

        String KEY_SUBJECTS = userSession.getUserDetails().get("subjects");

        if (KEY_SUBJECTS != null) {
            myList = new ArrayList<String>(Arrays.asList(KEY_SUBJECTS.split(",")));

            Log.d("selected_subjects", myList.toString());
           /* if (cartPojo.getResponse().get(position).getSubjetcs().size() > 0) {
                for (int i = 0; i < cartPojo.getResponse().get(position).getSubjetcs().size(); i++) {
                    String checkstream = cartPojo.getResponse().get(position).getSubjetcs().get(i).getId();

                    if (myList.contains(checkstream)) {
                        subjectcount.add("1");
                    } else {
                        subjectcount.add("0");
                    }

//                                streamcount.add("0");
                }
            }*/
        }

            yearAdapterView.subject_recycler_view.setVisibility(View.VISIBLE);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
            //setting horizontal list
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            yearAdapterView.subject_recycler_view.setLayoutManager(mLayoutManager);
            yearAdapterView.subject_recycler_view.setItemAnimator(new DefaultItemAnimator());

            //Adding Adapter to recyclerView
            final RaviAdapeter raviAdapeter = new RaviAdapeter(context,semisterModel.getSubjectsModels(),myList,adapterCallBack);
            yearAdapterView.subject_recycler_view.setAdapter(raviAdapeter);





    }

    @Override
    public int getItemCount() {
        return semisterModels.size();
    }

    @Override
    public void clickevent(String id, Boolean checked) {
        for (int j = 0; j < SemisterAdapeter.filetr_semisterModels.size(); j++) {
            for(int  k = 0;k < SemisterAdapeter.filetr_semisterModels.get(j).getSubjectsModels().size();k++){
                SubjectsModel subjectsModel1=SemisterAdapeter.filetr_semisterModels.get(j).getSubjectsModels().get(k);

                if(subjectsModel1.getId().equalsIgnoreCase(id)){
                    SemisterAdapeter.filetr_semisterModels.get(j).getSubjectsModels().get(k).setCheked(checked);
                }

            }

        }

        for (int j = 0; j < semisterModels.size(); j++) {
            for(int  k = 0;k < semisterModels.get(j).getSubjectsModels().size();k++){
                SubjectsModel subjectsModel1=semisterModels.get(j).getSubjectsModels().get(k);

                if(subjectsModel1.getId().equalsIgnoreCase(id)){
                    semisterModels.get(j).getSubjectsModels().get(k).setCheked(checked);
                }

            }

        }
        notifyDataSetChanged();

    }

    public class YearAdapterView extends RecyclerView.ViewHolder {
//        TextView tv_sem;
//        RecyclerView rv_subjectsub;


        TextView semesterName;
        RecyclerView subject_recycler_view;

        public YearAdapterView(View itemView) {
            super(itemView);
//            tv_sem = itemView.findViewById(R.id.tv_sem);
//            rv_subjectsub = itemView.findViewById(R.id.rv_subjectsub);
//            itemView.setOnClickListener(this);

            semesterName = itemView.findViewById(R.id.semesterName);
            subject_recycler_view = itemView.findViewById(R.id.subject_recycler_view);

        }

//        @Override
//        public void onClick(View v) {
//
//            if (mListner != null) {
//                mListner.onItemClick(v, getAdapterPosition(), sub_ids);
//            }
//        }
    }

}
