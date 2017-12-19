package edu.monash.navdrawer2081student;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    float movementV = 0.5f;
    float movementH = 0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", resetTextView).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    View.OnClickListener resetTextView=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.constraintID);
            TextView textView = (TextView) findViewById(R.id.demoText);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            if (movementH==0.5f && movementV==0.5f){
                Snackbar.make(v, "already in the middle", Snackbar.LENGTH_LONG).setText("in the middle");
            }
            else {
                movementH = 0.5f;
                movementV = 0.5f;
                constraintSet.setVerticalBias(textView.getId(),movementV);
                constraintSet.setHorizontalBias(textView.getId(),movementH);
                constraintSet.applyTo(constraintLayout);
            }
        }
    };


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

        if (id == R.id.up) {
            moveText(id);
        } else if (id == R.id.down) {
            moveText(id);
        } else if (id == R.id.left) {
            moveText(id);
        } else if (id == R.id.right) {
            moveText(id);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void moveText(int direction) {
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.constraintID);
        TextView textView = (TextView) findViewById(R.id.demoText);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);


        if (direction == R.id.up) {
            movementV -= 0.5f;
            constraintSet.setVerticalBias(textView.getId(), movementV);
        } else if (direction == R.id.down) {
            movementV += 0.5f;
            constraintSet.setVerticalBias(textView.getId(), movementV);

        } else if (direction == R.id.left) {
            movementH -= 0.5f;
            constraintSet.setHorizontalBias(textView.getId(), movementH);
        } else if (direction == R.id.right) {
            movementH += 0.5f;
            constraintSet.setHorizontalBias(textView.getId(), movementH);
        }

        constraintSet.applyTo(constraintLayout);
    }
}
