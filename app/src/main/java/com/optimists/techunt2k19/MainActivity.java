package com.optimists.techunt2k19;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



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


        Log.d("DRAWER","outside drawer");

        int[] teamNumber =new int[30];
        String[] teamNames=new String[30];
        int i;
        for(i=0;i<30;i++)
        {
            teamNumber[i]=i+1;
            teamNames[i]="Team"+Integer.toString(i+1);
        }

        ListAdapter RsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,teamNames);
        ListView StListView = (ListView) findViewById(R.id.teamSelector);

        StListView.setAdapter(RsAdapter);

        StListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        String teamSelectedInWords = String.valueOf(parent.getItemAtPosition(position));
                        //Toast is used for a pop-up message on click
                        //Toast.makeText(getApplicationContext(), ((TextView)view).getText(), Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(MainActivity.this,TeamlLogin.class);
                        i.putExtra("Team",teamSelectedInWords);
                        startActivity(i);
                    }
                }
        );





        TextView tx = (TextView)findViewById(R.id.textView);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "BebasNeuR.ttf");
        tx.setTypeface(custom_font);

        TextView tx2 = (TextView)findViewById(R.id.textView11);
        TextView tx3 = (TextView)findViewById(R.id.textView12);


        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "OswaldR.ttf");


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
        Log.d("DRAWER","button pressed");

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
