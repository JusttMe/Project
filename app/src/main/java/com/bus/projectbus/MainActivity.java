package com.bus.projectbus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bus.projectbus.fragments.StartFragment;
import com.bus.projectbus.fragments.SettingsFragment;
import com.bus.projectbus.fragments.ShowFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String SAVE_TOKEN = "token";
    private Intent mIntent;
    private static long backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getFragmentManager().beginTransaction().add(R.id.fragmentContainer, new StartFragment())
                .commit();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (backPressed + 2000 > System.currentTimeMillis()){
                super.onBackPressed();}
            else Toast.makeText(getBaseContext(), R.string.message_exit, Toast.LENGTH_SHORT).show();
            backPressed = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_create) {
            getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new StartFragment())
                    .commit();
        } else if (id == R.id.nav_show) {
            getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ShowFragment())
                    .commit();
        } else if (id == R.id.nav_settings) {
            getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new SettingsFragment())
                    .commit();
        } else if (id == R.id.nav_exit) {
            SharedPreferences settings = getSharedPreferences(SAVE_TOKEN, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(SAVE_TOKEN, null).commit();
            mIntent = new Intent(this, StartActivity.class);
            startActivity(mIntent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void setShowFragment(){
        getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ShowFragment())
                .commit();
    }
}
