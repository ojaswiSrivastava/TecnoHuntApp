package com.heyman.techunt2k18;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RadioButton r1,r2,r3,r4,r5,r6,r7,r8;
    private Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        r1= findViewById(R.id.r1);
        r2= findViewById(R.id.r2);
        r3= findViewById(R.id.r3);
        r4= findViewById(R.id.r4);
        r5= findViewById(R.id.r5);
        r6= findViewById(R.id.r6);
        r7= findViewById(R.id.r7);
        r8= findViewById(R.id.r8);

        b1=findViewById(R.id.button);

        TextView tx = (TextView)findViewById(R.id.textView);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "BebasNeuR.ttf");
        tx.setTypeface(custom_font);

        TextView tx2 = (TextView)findViewById(R.id.textView11);
        TextView tx3 = (TextView)findViewById(R.id.textView12);


        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "OswaldR.ttf");
        r1.setTypeface(custom_font2);
        r2.setTypeface(custom_font2);
        r3.setTypeface(custom_font2);
        r4.setTypeface(custom_font2);
        r5.setTypeface(custom_font2);
        r6.setTypeface(custom_font2);
        r7.setTypeface(custom_font2);
        r8.setTypeface(custom_font2);

        b1.setTypeface(custom_font2);

    }

    public void onProceed(View view)
    {
        Intent i=new Intent(this,TeamlLogin.class);
        if(r1.isChecked()) {
            i.putExtra("Team","1");
            startActivity(i);
        }
        if(r2.isChecked())
        {
            i.putExtra("Team","2");
            startActivity(i);
        }
        if(r3.isChecked())
        {
            i.putExtra("Team","3");
            startActivity(i);
        }
        if(r4.isChecked())
        {
            i.putExtra("Team","4");
            startActivity(i);
        }
        if(r5.isChecked())
        {
            i.putExtra("Team","5");
            startActivity(i);
        }
        if(r6.isChecked())
        {
            i.putExtra("Team","6");
            startActivity(i);
        }
        if(r7.isChecked())
        {
            i.putExtra("Team","7");
            startActivity(i);
        }
        if(r8.isChecked())
        {
            i.putExtra("Team","8");
            startActivity(i);
        }
    }
    
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_game) {
            Intent i=new Intent(this,AboutGame.class);
            startActivity(i);
        } else if (id == R.id.nav_rules) {
            Intent i=new Intent(this,NavRules.class);
            startActivity(i);
        } else if (id == R.id.nav_society) {
            Intent i=new Intent(this,OurSociety.class);
            startActivity(i);
        } else if (id == R.id.nav_developer) {
            Intent i=new Intent(this,Developer.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
