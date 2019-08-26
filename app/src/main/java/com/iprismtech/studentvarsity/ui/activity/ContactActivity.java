package com.iprismtech.studentvarsity.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.studentvarsity.R;

public class ContactActivity extends AppCompatActivity {
    private ImageView iv_back;
    private LinearLayout ll_mail, ll_call;
    private TextView tv_mail, tv_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        iv_back = findViewById(R.id.iv_back);
        ll_mail = findViewById(R.id.ll_mail);
        ll_call = findViewById(R.id.ll_call);
        tv_mail = findViewById(R.id.tv_mail);
        tv_mobile = findViewById(R.id.tv_mobile);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ll_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tv_mobile.getText().toString()));
                startActivity(intent);
            }
        });
        ll_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, tv_mail.getText().toString());
                intent.putExtra(Intent.EXTRA_SUBJECT, " Student Varsity");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(ContactActivity.this, OverviewActivity.class);
        startActivity(intent);
        finish();
    }
}
