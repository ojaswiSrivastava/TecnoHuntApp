package com.optimists.techunt2k19;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RulesStart extends AppCompatActivity {
    private Button b;
    String TAG=("OJASWI");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules_start);
        TextView tx = findViewById(R.id.textView2);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "OswaldR.ttf");
        tx.setTypeface(customFont);
        b = findViewById(R.id.button3);
        b.setTypeface(customFont);
        String teamSelectedInWords = getIntent().getStringExtra("Team");
        String teamNumber= teamSelectedInWords.replaceAll("[^0-9]", "");
        int teamNumberInteger = Integer.parseInt(teamNumber);
        Log.d(TAG,String.valueOf("team="+teamNumberInteger));

    }

    public void onHunt(View view) {
        String teamSelectedInWords = getIntent().getStringExtra("Team");
        Intent i = new Intent(this, TeamQuiz.class);
        i.putExtra("Team",teamSelectedInWords);
        startActivity(i);
    }
}