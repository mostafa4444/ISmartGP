package com.mostafa.root.ismartgp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.geofire.util.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mostafa.root.ismartgp.Classess.*;
import com.mostafa.root.ismartgp.Fragments.AboutFragment;
import com.mostafa.root.ismartgp.Fragments.HelpFragment;
import com.mostafa.root.ismartgp.Fragments.HomeFragment;
import com.mostafa.root.ismartgp.Fragments.AddUsersFragment;
import com.mostafa.root.ismartgp.Fragments.SettingFragment;
import com.rengwuxian.materialedittext.MaterialEditText;

public  class MainDrawerActivity extends  AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences mPreferences;
    SharedPreferences.Editor mEditor;
    private TextView my_email;
    private TextView my_name;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        NavigationView nav = (NavigationView) findViewById(R.id.nav_view);
        View headerView = nav.getHeaderView(0);
        my_email = (TextView) headerView.findViewById(R.id.nav_user_email);
        my_name = (TextView) headerView.findViewById(R.id.nav_user_name);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.nav_home);
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(MainDrawerActivity.this);
        SharedPreferences.Editor editor = mPreferences.edit();
        final String my_mail = mPreferences.getString("email_share", "");
        my_email.setText(my_mail);
        mEditor.putString("current_mail" , my_mail); mEditor.commit(); mEditor.apply();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();

        myRef.child("Users").child(uid).addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String name = String.valueOf(dataSnapshot.child("name").getValue());
                my_name.setText(dataSnapshot.child("name").getValue().toString());
            }
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            final Utilities utilities = new Utilities();
            utilities.alertDialog(MainDrawerActivity.this, "Exit!", "Are you sure you want to close the application?", "YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    System.exit(0);
                }
            }, "NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displaySelectedScreen(item.getItemId());

        return true;
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_addUser:
                fragment = new AddUsersFragment();
                break;
            case R.id.nav_settings:
                fragment = new SettingFragment();
                break;
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_about:
                showAboutDialog();
                break;
            case R.id.nav_help:
                fragment = new HelpFragment();
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                mEditor.putString("remember_me", "false");
                mEditor.commit();mEditor.apply();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
        }
        if (fragment != null) {
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContent, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    private void showAboutDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("About Us");
        LayoutInflater inflater = LayoutInflater.from(this);
        View register_layout = inflater.inflate(R.layout.about_fragment , null);
        dialog.setView(register_layout);

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_drawer , menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.action_list){
//                AlertDialog.Builder dialog = new AlertDialog.Builder(MainDrawerActivity.this , R.style.CustomDialog);
//                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//                View login_layout = inflater.inflate(R.layout.blablabla, null);
//                dialog.setView(login_layout);
//                dialog.show();
        }

        return true;
    }

}