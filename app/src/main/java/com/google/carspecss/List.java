package com.google.carspecss;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.suredigit.inappfeedback.FeedbackDialog;

import java.util.ArrayList;

public class List extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FeedbackDialog feedbackDialog;

    public final static String EXTRA_MESSAGE = "com.google.carspecs.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        feedbackDialog = new FeedbackDialog(this, "AF-FB6CEC3323D8-20");

        final Intent intent = getIntent();
        String brand = intent.getStringExtra(Brand.EXTRA_MESSAGE);


        //Opening the database
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();


        java.util.List<String> cars = new ArrayList<>();
        cars = databaseAccess.getAllCars(brand);

        ArrayAdapter<String> Adapter = new CustomAdapter(this, cars);
        ListView carsListView = (ListView) findViewById(R.id.carsListView);
        carsListView.setAdapter(Adapter);

        carsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String car = String.valueOf(parent.getItemAtPosition(position));
                        Intent intent = new Intent(List.this, Specifications.class);
                        intent.putExtra(EXTRA_MESSAGE,car);
                        startActivity(intent);
                    }
                }

        );
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(this, Home.class));
        } else if (id == R.id.nav_brand) {
            startActivity(new Intent(this, Brand.class));
        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Download this awesome app http://www.google.com");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        } else if (id == R.id.nav_send_feedback) {
            feedbackDialog.show();

        } else if (id == R.id.nav_rate) {
            //final String appPackagaeName = getPackageName();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.gombosdev.ampere")));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.gombosdev.ampere")));
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
