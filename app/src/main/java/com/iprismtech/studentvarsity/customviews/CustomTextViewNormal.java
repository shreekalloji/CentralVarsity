package com.iprismtech.studentvarsity.customviews;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Created by admin on 2/15/2017.
 */

@SuppressLint("AppCompatCustomView")
public class CustomTextViewNormal extends TextView {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomTextViewNormal(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        super(context, attrs, defStyle,defStyleRes);
        init();
    }
    public CustomTextViewNormal(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextViewNormal(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextViewNormal(Context context) {
        super(context);
        init();
    }

    private void init() {

        Typeface externalFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/helvetica_neue_light.ttf");

        setTypeface(externalFont);

    }
}
