package com.heyman.techunt2k18;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class NavRules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_rules);
        TextView tx= findViewById(R.id.textView2);
        Typeface customFont=Typeface.createFromAsset(getAssets(),"OswaldR.ttf");
        tx.setTypeface(customFont);
    }
}
