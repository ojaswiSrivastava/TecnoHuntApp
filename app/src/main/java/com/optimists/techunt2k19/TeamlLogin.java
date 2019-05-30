package com.optimists.techunt2k19;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeamlLogin extends AppCompatActivity {

    Button b;
    EditText e1;
    EditText e2;
    String currentTeam;
    int treamNumber;
    private TextInputLayout textInputLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teaml_login);
        b=findViewById(R.id.button);
        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        Typeface customFont= Typeface.createFromAsset(getAssets(),"OswaldR.ttf");
        b.setTypeface(customFont);
        e1.setTypeface(customFont);
        e2.setTypeface(customFont);
        textInputLayout=findViewById(R.id.textInputLayout);
        textInputLayout.setTypeface(customFont);

    }
    public void onLogin(View view)
    {
        Intent myIntent=getIntent();
        String teamSelectedInWords;
        teamSelectedInWords = myIntent.getStringExtra("Team");
        boolean loadNextActivity= loginValidate(teamSelectedInWords);
        if(loadNextActivity==true)
        {
            Intent i = new Intent(this, RulesStart.class);
            i.putExtra("Team",teamSelectedInWords);
            startActivity(i);
        }
    }

    boolean loginValidate(String teamSelectedInWords)
    {
        String userName=e1.getText().toString();
        String passWord=e2.getText().toString();
        int flag=0;
        int i;
        String s1 ="Team";

        for(i=1;i<31;i++) {
            String teamNubmerInString = Integer.toString(i);
            currentTeam =teamNubmerInString;
            String teamName = s1.concat(teamNubmerInString);




            if (teamSelectedInWords.equals(teamName)) {

                if (userName.isEmpty() && passWord.isEmpty()) {
                    Toast.makeText(this, "The fields can't be left blank", Toast.LENGTH_SHORT).show();
                } else if (userName.isEmpty()) {
                    Toast.makeText(this, "Username can't be left blank", Toast.LENGTH_SHORT).show();
                } else if (passWord.isEmpty()) {
                    Toast.makeText(this, "Password can't be left blank", Toast.LENGTH_SHORT).show();

                } else if (userName.equals(teamName) && passWord.equals("zealicon2k19")) {
                    flag = 1;
                } else if (!userName.equals(teamName) && passWord.equals("zealicon2k19")) {
                    Toast.makeText(this, "Wrong Username!", Toast.LENGTH_SHORT).show();
                } else if (userName.equals(teamName) && !passWord.equals("zealicon2k19")) {
                    Toast.makeText(this, "Wrong Password!", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }


        if(flag==0)
            return false;
        else
            return true;

    }
}
