package com.iprismtech.studentvarsity.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.ui.activity.NewGroupActivity;

public class Ping extends AppCompatActivity {
    TextView tv_create_grup;
    ImageView iv_grpname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);

        tv_create_grup = findViewById(R.id.tv_create_grup);
        //   iv_grpname = findViewById(R.id.iv_grupname);

        tv_create_grup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ping.this, NewGroupActivity.class);
                startActivity(intent);
                finish();
            }
        });


//        iv_grpname.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Ping.this, PingFriendsGroup.class);
//                startActivity(intent);
//                finish();
//            }
//        });

    }
}
