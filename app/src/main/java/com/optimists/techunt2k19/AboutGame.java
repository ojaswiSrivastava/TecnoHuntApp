package com.optimists.techunt2k19;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_game);
        TextView tx= findViewById(R.id.textView3);
        Typeface customFont=Typeface.createFromAsset(getAssets(),"OswaldR.ttf");
        tx.setTypeface(customFont);

    }
}
