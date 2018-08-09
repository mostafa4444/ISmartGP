package com.mostafa.root.ismartgp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2345;
    SharedPreferences mPreferences;
    SharedPreferences.Editor editor;
    ImageView house,speech;
    Animation frombottom, fromtop;
    String remeber;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        speech= findViewById(R.id.speech);
        house=findViewById(R.id.home);
        frombottom= AnimationUtils.loadAnimation(this,R.anim.animation);
        fromtop=AnimationUtils.loadAnimation(this,R.anim.fromtop);
        speech.setAnimation(frombottom);
        house.setAnimation(fromtop);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(SplashScreen.this);
        editor = mPreferences.edit();
        remeber = mPreferences.getString("remember_me", "");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (remeber){
                    case "true":
                        Intent toMain = new Intent(SplashScreen.this, BluetoothConnection.class);
                        startActivity(toMain);
                        finish();
                        break;
                    case "false":
                        Intent toLogin = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(toLogin);
                        finish();
                        break;
                    default:
                        Intent toDefault = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(toDefault);
                        finish();
                        break;
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
