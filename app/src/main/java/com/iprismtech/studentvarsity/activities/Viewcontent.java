package com.iprismtech.studentvarsity.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.ui.activity.PingFriendsGroup;

public class Viewcontent extends AppCompatActivity {

    TextView tv_viewall,tv_ping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcontent);

        tv_viewall = findViewById(R.id.tv_viewall);
        tv_ping = findViewById(R.id.tv_ping);

        tv_viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog viewall_dialogue = new Dialog(Viewcontent.this,R.style.Theme_Dialog);

                viewall_dialogue.requestWindowFeature(Window.FEATURE_NO_TITLE);
                viewall_dialogue.setContentView(R.layout.view_all_dialouge);
                ImageView imageView=viewall_dialogue.findViewById(R.id.im_close);
                Window window = viewall_dialogue.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.BOTTOM);
                viewall_dialogue.show();

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewall_dialogue.dismiss();
                    }
                });

            }
        });


        tv_ping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Viewcontent.this, PingFriendsGroup.class);
                startActivity(intent);
                finish();
            }
        });




    }

}
