package com.optimists.techunt2k19;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Developer extends AppCompatActivity {

    TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        tx=findViewById(R.id.textView);
        Typeface customFont=Typeface.createFromAsset(getAssets(),"OswaldR.ttf");
        tx.setTypeface(customFont);
    }
}
