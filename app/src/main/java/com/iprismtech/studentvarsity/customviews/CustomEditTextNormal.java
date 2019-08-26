package com.iprismtech.studentvarsity.customviews;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

@SuppressLint("AppCompatCustomView")
public class CustomEditTextNormal extends EditText {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomEditTextNormal(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        super(context, attrs, defStyle, defStyleRes);
        init();
    }

    public CustomEditTextNormal(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomEditTextNormal(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditTextNormal(Context context) {
        super(context);
        init();
    }

    private void init() {

        Typeface externalFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/montserrat_regular.ttf");

        setTypeface(externalFont);

    }

}
