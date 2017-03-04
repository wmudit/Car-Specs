package com.google.carspecss;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class popupLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_layout);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (.75*width), (int) (.45*height));

        TextView heading = (TextView) findViewById(R.id.Layout);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/TitilliumWeb-SemiBold.ttf");
        heading.setTypeface(typeface);

        TextView text = (TextView) findViewById(R.id.layoutText);
        Typeface typeface1 = Typeface.createFromAsset(getAssets(),"fonts/TitilliumWeb-Light.ttf");
        text.setTypeface(typeface1);

    }
}
