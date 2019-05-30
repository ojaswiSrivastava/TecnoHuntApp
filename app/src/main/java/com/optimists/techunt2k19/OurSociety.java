package com.optimists.techunt2k19;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OurSociety extends AppCompatActivity {

    private TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_society);
        tx=findViewById(R.id.textView);
        Typeface customFont=Typeface.createFromAsset(getAssets(),"OswaldR.ttf");
        tx.setTypeface(customFont);
    }
}
