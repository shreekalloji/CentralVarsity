package com.iprismtech.studentvarsity.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.network.listener.ChildClickListener;
import com.iprismtech.studentvarsity.pojos.ChaptersPojo;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    ChildClickListener childClickListener;
    private List<String> parentDataSource;
    private HashMap<String, List<String>> childDataSource;
    private ChaptersPojo chaptersPojo;

    public ExpandableListViewAdapter(ChaptersPojo chaptersPojo, List<String> parentHeaderInformation, HashMap<String, List<String>> allChildItems, Context context) {
        this.context = context;
        this.parentDataSource = parentHeaderInformation;
        // this.childClickListener = ;
        this.childDataSource = allChildItems;
        this.chaptersPojo = chaptersPojo;
    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int group_position, int child_position);
    }

    @Override
    public int getGroupCount() {
        return parentDataSource.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //return childDataSource.get(this.parentDataSource.get(groupPosition)).size();
        //return this.childDataSource.get(this.parentDataSource.get(groupPosition)).size();
        if (this.childDataSource.get(this.parentDataSource.get(groupPosition)) == null) {
            return 0;

        } else {
            return this.childDataSource.get(this.parentDataSource.get(groupPosition)).size();
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentDataSource.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String bjs = "";
        // return childDataSource.get(parentDataSource.get(groupPosition)).get(childPosition);
        if (childDataSource != null) {
            if (parentDataSource != null) {
                String patext = parentDataSource.get(groupPosition);
                if (patext != null) {

                    if (childDataSource.get(patext) != null) {

                        String chailsourcetext = childDataSource.get(patext).get(childPosition);

                        if (chailsourcetext != null) {
                            bjs = this.childDataSource.get(parentDataSource.get(groupPosition)).get(childPosition);
                        }
                    }
                }
            }


            return bjs;

        }
        return bjs;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        //  View view = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.parent_layout, parent, false);

        String parentHeader = (String) getGroup(groupPosition);

        TextView parentItem = (TextView) convertView.findViewById(R.id.parent_text);
        parentItem.setText(parentHeader);
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        //    View view = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.child_layout, parent, false);

        String childName = (String) getChild(groupPosition, childPosition);


        TextView childItem = (TextView) convertView.findViewById(R.id.child_text);

        childItem.setText(Html.fromHtml(childName));

//        childItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (mListner != null) {
//                    mListner.onItemClick(view, groupPosition, childPosition);
//                }
//
//
////                String category_id= String.valueOf(departmentCategoryPOJO.getResponse().get(groupPosition).getCategory_id());
////                String sub_category_id= String.valueOf(departmentCategoryPOJO.getResponse().get(groupPosition).getSubcategory().get(childPosition).getId());
////                Toast.makeText(context, category_id+" \n "+sub_category_id, Toast.LENGTH_SHORT).show();
//
//                //Sending Bundle object to T-Shirt Fragment
////                Bundle b=new Bundle();
////                b.putString("category_id", "1");
////                b.putString("sub_category_id","2");
////                b.putString("tag","Tag");
////                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HOME_SCREEN,b); Bundle b=new Bundle();
//
//
//            }
//        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
