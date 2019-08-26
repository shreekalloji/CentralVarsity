package com.iprismtech.studentvarsity.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.iprismtech.studentvarsity.R;

public class SuggestionDetailsActivity extends AppCompatActivity {

    Button submit;
    ImageView im_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_details);

        im_close=findViewById(R.id.im_close);

        im_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submit = findViewById(R.id.btn_sugestion_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
