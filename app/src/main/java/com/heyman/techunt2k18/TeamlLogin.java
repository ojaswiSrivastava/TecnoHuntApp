package com.heyman.techunt2k18;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Type;

public class TeamlLogin extends AppCompatActivity {

    Button b;
    EditText e1;
    EditText e2;
    String currentTeam;
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
        String teamName;
        teamName = myIntent.getStringExtra("Team");
        boolean update=validate(teamName);
        if(update==true)
        {
            Intent i = new Intent(this, RulesStart.class);
            i.putExtra("Team",currentTeam);
            startActivity(i);
        }
    }

    boolean validate(String team)
    {
        String userName=e1.getText().toString();
        String passWord=e2.getText().toString();
        int flag=0;
        if(team.equals("1"))
        {
            currentTeam="1";
            if(userName.isEmpty() && passWord.isEmpty())
            {
                Toast.makeText(this,"The fields can't be left blank",Toast.LENGTH_SHORT).show();
            }
            else if(userName.isEmpty())
            {
                Toast.makeText(this,"Username can't be left blank",Toast.LENGTH_SHORT).show();
            }
            else if(passWord.isEmpty())
            {
                Toast.makeText(this,"Password can't be left blank",Toast.LENGTH_SHORT).show();

            }
            else if(userName.equals("Team1") && passWord.equals("zealicon2k18"))
            {
                flag=1;
            }
            else if(!userName.equals("Team1") && passWord.equals("zealicon2k18"))
            {
                Toast.makeText(this,"Wrong Username!",Toast.LENGTH_SHORT).show();
            }
            else if(userName.equals("Team1") && !passWord.equals("zealicon2k18"))
            {
                Toast.makeText(this,"Wrong Password!",Toast.LENGTH_SHORT).show();
            }
        }

        if(team.equals("2"))
        {
            currentTeam="2";
        if(userName.isEmpty() && passWord.isEmpty())
            {
                Toast.makeText(this,"The fields can't be left blank",Toast.LENGTH_SHORT).show();
            }
            else if(userName.isEmpty())
            {
                Toast.makeText(this,"Username can't be left blank",Toast.LENGTH_SHORT).show();
            }
            else if(passWord.isEmpty())
            {
                Toast.makeText(this,"Password can't be left blank",Toast.LENGTH_SHORT).show();

            }
            else if(userName.equals("Team2") && passWord.equals("zealicon2k18"))
            {
                flag=1;
            }
            else if(!userName.equals("Team2") && passWord.equals("zealicon2k18"))
            {
                Toast.makeText(this,"Wrong Username!",Toast.LENGTH_SHORT).show();
            }
            else if(userName.equals("Team2") && !passWord.equals("zealicon2k18"))
            {
                Toast.makeText(this,"Wrong Password!",Toast.LENGTH_SHORT).show();
            }

        }
        if(team.equals("3"))
        {
            currentTeam="3";
            if(userName.isEmpty() && passWord.isEmpty())
            {
                Toast.makeText(this,"The fields can't be left blank",Toast.LENGTH_SHORT).show();
            }
            else if(userName.isEmpty())
            {
                Toast.makeText(this,"Username can't be left blank",Toast.LENGTH_SHORT).show();
            }
            else if(passWord.isEmpty())
            {
                Toast.makeText(this,"Password can't be left blank",Toast.LENGTH_SHORT).show();

            }
            else if(userName.equals("Team3") && passWord.equals("zealicon2k18"))
            {
                flag=1;
            }
            else if(!userName.equals("Team3") && passWord.equals("zealicon2k18"))
            {
                Toast.makeText(this,"Wrong Username!",Toast.LENGTH_SHORT).show();
            }
            else if(userName.equals("Team3") && !passWord.equals("zealicon2k18"))
            {
                Toast.makeText(this,"Wrong Password!",Toast.LENGTH_SHORT).show();
            }

        }
        if(team.equals("4"))
        {
            currentTeam="4";
            if(userName.isEmpty() && passWord.isEmpty())
            {
                Toast.makeText(this,"The fields can't be left blank",Toast.LENGTH_SHORT).show();
            }
            else if(userName.isEmpty())
            {
                Toast.makeText(this,"Username can't be left blank",Toast.LENGTH_SHORT).show();
            }
            else if(passWord.isEmpty())
            {
                Toast.makeText(this,"Password can't be left blank",Toast.LENGTH_SHORT).show();

            }
            else if(userName.equals("Team4") && passWord.equals("zealicon2k18"))
            {
                flag=1;
            }
            else if(!userName.equals("Team4") && passWord.equals("zealicon2k18"))
            {
                Toast.makeText(this,"Wrong Username!",Toast.LENGTH_SHORT).show();
            }
            else if(userName.equals("Team4") && !passWord.equals("zealicon2k18"))
            {
                Toast.makeText(this,"Wrong Password!",Toast.LENGTH_SHORT).show();
            }

        }
        if(team.equals("5"))
        {
            currentTeam="5";
            if(userName.isEmpty() && passWord.isEmpty())
            {
                Toast.makeText(this,"The fields can't be left blank",Toast.LENGTH_SHORT).show();
            }
            else if(userName.isEmpty())
            {
                Toast.makeText(this,"Username can't be left blank",Toast.LENGTH_SHORT).show();
            }
            else if(passWord.isEmpty())
            {
                Toast.makeText(this,"Password can't be left blank",Toast.LENGTH_SHORT).show();

            }
            else if(userName.equals("Team5") && passWord.equals("zealicon2k18"))
            {
                flag=1;
            }
            else if(!userName.equals("Team5") && passWord.equals("zealicon2k18"))
            {
                Toast.makeText(this,"Wrong Username!",Toast.LENGTH_SHORT).show();
            }
            else if(userName.equals("Team5") && !passWord.equals("zealicon2k18"))
            {
                Toast.makeText(this,"Wrong Password!",Toast.LENGTH_SHORT).show();
            }

        }
        else if(team.equals("6"))
        {
            currentTeam="6";
            if(userName.isEmpty() && passWord.isEmpty())
            {
                Toast.makeText(this,"The fields can't be left blank",Toast.LENGTH_SHORT).show();
            }
            else if(userName.isEmpty())
            {
                Toast.makeText(this,"Username can't be left blank",Toast.LENGTH_SHORT).show();
            }
            else if(passWord.isEmpty())
            {
                Toast.makeText(this,"Password can't be left blank",Toast.LENGTH_SHORT).show();

            }
            else if(userName.equals("Team6") && passWord.equals("zealicon2k18"))
            {
                flag=1;
            }
            else if(!userName.equals("Team6") && passWord.equals("zealicon2k18"))
            {
                Toast.makeText(this,"Wrong Username!",Toast.LENGTH_SHORT).show();
            }
            else if(userName.equals("Team6") && !passWord.equals("zealicon2k18"))
            {
                Toast.makeText(this,"Wrong Password!",Toast.LENGTH_SHORT).show();
            }
        }
        else if(team.equals("7"))
        {
            currentTeam="7";
            if(userName.isEmpty() && passWord.isEmpty())
            {
                Toast.makeText(this,"The fields can't be left blank",Toast.LENGTH_SHORT).show();
            }
            else if(userName.isEmpty())
            {
                Toast.makeText(this,"Username can't be left blank",Toast.LENGTH_SHORT).show();
            }
            else if(passWord.isEmpty())
            {
                Toast.makeText(this,"Password can't be left blank",Toast.LENGTH_SHORT).show();

            }
            else if(userName.equals("Team7") && passWord.equals("zealicon2k18"))
            {
                flag=1;
            }
            else if(!userName.equals("Team7") && passWord.equals("zealicon2k18"))
            {
                Toast.makeText(this,"Wrong Username!",Toast.LENGTH_SHORT).show();
            }
            else if(userName.equals("Team7") && !passWord.equals("zealicon2k18"))
            {
                Toast.makeText(this,"Wrong Password!",Toast.LENGTH_SHORT).show();
            }
        }
        else if(team.equals("8"))
        {
            currentTeam="8";
            if(userName.isEmpty() && passWord.isEmpty())
            {
                Toast.makeText(this,"The fields can't be left blank",Toast.LENGTH_SHORT).show();
            }
            else if(userName.isEmpty())
            {
                Toast.makeText(this,"Username can't be left blank",Toast.LENGTH_SHORT).show();
            }
            else if(passWord.isEmpty())
            {
                Toast.makeText(this,"Password can't be left blank",Toast.LENGTH_SHORT).show();

            }
            else if(userName.equals("Team8") && passWord.equals("zealicon2k18"))
            {
                flag=1;
            }
            else if(!userName.equals("Team8") && passWord.equals("zealicon2k18"))
            {
                Toast.makeText(this,"Wrong Username!",Toast.LENGTH_SHORT).show();
            }
            else if(userName.equals("Team8") && !passWord.equals("zealicon2k18"))
            {
                Toast.makeText(this,"Wrong Password!",Toast.LENGTH_SHORT).show();
            }
        }

        if(flag==0)
            return false;
        else
            return true;

    }
}
