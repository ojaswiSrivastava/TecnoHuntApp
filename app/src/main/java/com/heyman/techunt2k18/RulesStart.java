package com.heyman.techunt2k18;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RulesStart extends AppCompatActivity {
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules_start);
        TextView tx = findViewById(R.id.textView2);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "OswaldR.ttf");
        tx.setTypeface(customFont);
        b = findViewById(R.id.button3);
        b.setTypeface(customFont);
    }

    public void onHunt(View view) {
        String currentTeam = getIntent().getStringExtra("Team");
        //Toast.makeText(this,"Team4Quiz",Toast.LENGTH_SHORT);
        if (currentTeam.equals("1")) {
            Intent i = new Intent(this, Team1Quiz.class);
            startActivity(i);
        }
        if (currentTeam.equals("2")) {
            Intent i = new Intent(this, Team2Quiz.class);
            startActivity(i);
        }
        if (currentTeam.equals("3")) {
            Intent i = new Intent(this, Team3Quiz.class);
            startActivity(i);
        }
        if (currentTeam.equals("4")) {
            Intent i = new Intent(this, Team4Quiz.class);
            startActivity(i);
        }

        if (currentTeam.equals("5")) {
            Intent i = new Intent(this, Team5Quiz.class);
            startActivity(i);
        }
        if (currentTeam.equals("6")) {
            Intent i = new Intent(this, Team6Quiz.class);
            startActivity(i);
        }
        if (currentTeam.equals("7")) {
            Intent i = new Intent(this, Team7Quiz.class);
            startActivity(i);
        }
        if (currentTeam.equals("8")) {
            Intent i = new Intent(this, Team8Quiz.class);
            startActivity(i);
        }

    }
}