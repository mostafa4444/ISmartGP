package com.mostafa.root.ismartgp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {
    private Button btnSignIn;
    SharedPreferences mPreferences;
    SharedPreferences.Editor mEditor;
    FirebaseAuth auth;
    FirebaseDatabase db;
    private CheckBox remember;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn = (Button) findViewById(R.id.btn_login);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        remember = (CheckBox) findViewById(R.id.remember_me);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showLoginDialog();
            }
        });
    }


    private void showLoginDialog() {
        final EditText edtEmail = (EditText) findViewById(R.id.email_login);
        final EditText edtPassword = (EditText) findViewById(R.id.password_login);
                btnSignIn.setEnabled(false);
                if(TextUtils.isEmpty(edtEmail.getText().toString())){
                    Toast.makeText(MainActivity.this , "Please enter email address" , Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(edtPassword.getText().toString())){
                    Toast.makeText(MainActivity.this , "Please enter password" , Toast.LENGTH_LONG).show();
                    return;
                }
                final AlertDialog waiting = new SpotsDialog(MainActivity.this);
                waiting.show();

                auth.signInWithEmailAndPassword(edtEmail.getText().toString() , edtPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        waiting.dismiss();
                        if(remember.isChecked()) {
                            mEditor.putString("remember_me", "true");
                            mEditor.commit();mEditor.apply();
                            mEditor.putString("email_share", edtEmail.getText().toString());
                            mEditor.commit();mEditor.apply();
                        }else{
                            mEditor.putString("remember_me", "false");
                            mEditor.commit();mEditor.apply();
                            mEditor.putString("email_share", edtEmail.getText().toString());
                            mEditor.commit();mEditor.apply();
                        }
                        startActivity(new Intent(MainActivity.this , BluetoothConnection.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        waiting.dismiss();
                        Toast.makeText(MainActivity.this , "Failed to authenticate : " +  e.getMessage() , Toast.LENGTH_LONG).show();
                        btnSignIn.setEnabled(true);

                    }
                });
            }
    }
