package com.iprismtech.studentvarsity.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;

public class GroupActivity extends AppCompatActivity {

    TextView group_member, remove_grup_yes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        //group_member = findViewById(R.id.tv_grup_member);

//        group_member.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Dialog remove_gru_member_dialogue = new Dialog(GroupActivity.this);
//
//                remove_gru_member_dialogue.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                remove_gru_member_dialogue.setContentView(R.layout.item_remove_group_dialouge);
//
//                remove_grup_yes=remove_gru_member_dialogue.findViewById(R.id.remove_grup_yes);
//                remove_grup_yes.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        remove_gru_member_dialogue.dismiss();
//                    }
//                });
//
//
//
//                Window window = remove_gru_member_dialogue.getWindow();
//                window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
//                window.setGravity(Gravity.CENTER_HORIZONTAL);
//
//                remove_gru_member_dialogue.show();            }
//        });
    }
}
