package com.iprismtech.studentvarsity.activities;

        import android.app.Dialog;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Gravity;
        import android.view.View;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.iprismtech.studentvarsity.R;

public class SelectNewSubject extends AppCompatActivity {

    ImageView iv_subject;
    TextView  tv_change_subject_yes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_new_subject);

        iv_subject = findViewById(R.id.fab_subject);

        iv_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog change_subject_dialogue = new Dialog(SelectNewSubject.this);
                change_subject_dialogue.requestWindowFeature(Window.FEATURE_NO_TITLE);
                change_subject_dialogue.setContentView(R.layout.item_warning_save_dialouge);


                 tv_change_subject_yes= change_subject_dialogue.findViewById(R.id.tv_changesubject_yes);
                 tv_change_subject_yes.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent intent = new Intent(SelectNewSubject.this,ProfileEdit.class);
                         startActivity(intent);
                         finish();


                     }
                 });



                Window window = change_subject_dialogue.getWindow();
                window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER_VERTICAL);
                change_subject_dialogue.show();

            }
        });
    }
}
