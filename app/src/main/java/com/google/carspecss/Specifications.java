package com.google.carspecss;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.suredigit.inappfeedback.FeedbackDialog;

public class Specifications extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final static String EXTRA_MESSAGE = "com.google.carspecs.MESSAGE";


    private FeedbackDialog feedbackDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifications);
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

        //Populating the text views

        String cname ;

        Intent intent = getIntent();
        cname = intent.getStringExtra(Home.EXTRA_MESSAGE);
        if(cname == null){
            cname = intent.getStringExtra(List.EXTRA_MESSAGE);
        }
        cname = cname.trim();

        //Referncing all details TextView
        TextView nameDet = (TextView) findViewById(R.id.nameDet);
        TextView manuDet = (TextView) findViewById(R.id.manuDet);
        TextView bstyleDet = (TextView) findViewById(R.id.bstyleDet);
        TextView layoutDet = (TextView) findViewById(R.id.layoutDet);
        TextView engineDet = (TextView) findViewById(R.id.engineDet);
        TextView dispDet = (TextView) findViewById(R.id.dispDet);
        TextView powerDet = (TextView) findViewById(R.id.powerDet);
        TextView torqDet = (TextView) findViewById(R.id.torqDet);
        TextView CO2Det = (TextView) findViewById(R.id.CO2Det);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        if (databaseAccess.check(cname)) {

            //Getting all the specs
            String Manu = databaseAccess.getManu(cname);
            String Bstyle = databaseAccess.getBstyle(cname);
            String Layout = databaseAccess.getLayout(cname);
            String Engine = databaseAccess.getEngine(cname);
            String Disp = databaseAccess.getDisp(cname);
            String Power = databaseAccess.getPower(cname);
            String Torq = databaseAccess.getTorq(cname);
            String CO2 = databaseAccess.getCO2(cname);

            databaseAccess.close();

            //Setting the TextView text to the retrieved data
            nameDet.setText(cname);
            manuDet.setText(Manu);
            bstyleDet.setText(Bstyle);
            layoutDet.setText(Layout);
            engineDet.setText(Engine);
            dispDet.setText(Disp);
            powerDet.setText(Power);
            torqDet.setText(Torq);
            CO2Det.setText(CO2);

            TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
            TextView manuTextView = (TextView) findViewById(R.id.manuTextView);
            TextView bstyleTextView = (TextView) findViewById(R.id.bstyleTextView);
            TextView layoutTextView = (TextView) findViewById(R.id.layoutTextView);
            TextView engineTextView = (TextView) findViewById(R.id.engineTextView);
            TextView dispTextView = (TextView) findViewById(R.id.dispTextView);
            TextView powerTextView = (TextView) findViewById(R.id.powerTextView);
            TextView torqTextView = (TextView) findViewById(R.id.torqTextView);
            TextView co2TextView = (TextView) findViewById(R.id.CO2TextView);
            Typeface typefaceLeft = Typeface.createFromAsset(getAssets(), "fonts/Overpass-SemiBold.ttf");
            nameTextView.setTypeface(typefaceLeft);
            manuTextView.setTypeface(typefaceLeft);
            bstyleTextView.setTypeface(typefaceLeft);
            layoutTextView.setTypeface(typefaceLeft);
            engineTextView.setTypeface(typefaceLeft);
            dispTextView.setTypeface(typefaceLeft);
            powerTextView.setTypeface(typefaceLeft);
            dispTextView.setTypeface(typefaceLeft);
            torqTextView.setTypeface(typefaceLeft);
            co2TextView.setTypeface(typefaceLeft);

            layoutTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Specifications.this, popupLayout.class));
                }
            });

            engineTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Specifications.this, popupEngine.class));
                }
            });

            dispTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Specifications.this, popupDisplacement.class));
                }
            });

            powerTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Specifications.this, popupPower.class));
                }
            });

            torqTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Specifications.this, popupTorque.class));
                }
            });

            co2TextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Specifications.this, popupCO2.class));
                }
            });


            Typeface typefaceRight = Typeface.createFromAsset(getAssets(), "fonts/FiraSansCondensed-Light.ttf");
            nameDet.setTypeface(typefaceRight);
            manuDet.setTypeface(typefaceRight);
            bstyleDet.setTypeface(typefaceRight);
            layoutDet.setTypeface(typefaceRight);
            engineDet.setTypeface(typefaceRight);
            dispDet.setTypeface(typefaceRight);
            powerDet.setTypeface(typefaceRight);
            torqDet.setTypeface(typefaceRight);
            CO2Det.setTypeface(typefaceRight);
        }
        else {
            Toast.makeText(this, "Car not found.. Browse instead", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Send Feedback to notify us about it", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, Brand.class));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.specifications, menu);
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
