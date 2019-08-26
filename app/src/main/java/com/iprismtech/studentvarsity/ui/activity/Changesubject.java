package com.iprismtech.studentvarsity.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.activities.SelectNewSubject;

public class Changesubject extends AppCompatActivity {

    ImageView change_subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changesubject);

        change_subject = findViewById(R.id.fab_changesubject);

        change_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Changesubject.this, SelectNewSubject.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
