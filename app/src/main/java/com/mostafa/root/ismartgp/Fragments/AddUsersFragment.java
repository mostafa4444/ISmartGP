package com.mostafa.root.ismartgp.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mostafa.root.ismartgp.MainActivity;
import com.mostafa.root.ismartgp.Model.UsersModel;
import com.mostafa.root.ismartgp.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;


public class AddUsersFragment extends Fragment {
    private Button btn_add_new_user;
    private EditText mail , name , password , phone;
    private final int PICK_IMAGE_REQUEST = 71;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    public AddUsersFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_users, container, false);
        btn_add_new_user = (Button) rootView.findViewById(R.id.btn_addUser);
        mail = (EditText) rootView.findViewById(R.id.email_register);
        name = (EditText) rootView.findViewById(R.id.user_name_register);
        phone = (EditText) rootView.findViewById(R.id.phone_register);
        password = (EditText) rootView.findViewById(R.id.password_register);
        FirebaseApp.initializeApp(getActivity());
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        btn_add_new_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showRegisterDialog();
            }
        });
        return rootView;
    }



    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Add New User");
    }

    private void showRegisterDialog() {
                if(TextUtils.isEmpty(mail.getText().toString())){
                    Toast.makeText(getActivity() , "Please enter email address" , Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(getActivity() , "Please enter password" , Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(phone.getText().toString())){
                    Toast.makeText(getActivity() , "Please enter phone" , Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(name.getText().toString())){
                    Toast.makeText(getActivity() , "Please enter name" , Toast.LENGTH_LONG).show();
                    return;
                }


                auth.createUserWithEmailAndPassword(mail.getText().toString() , password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        UsersModel user = new UsersModel();
                        user.setEmail(mail.getText().toString());
                        user.setPassword(password.getText().toString());
                        user.setPhone(phone.getText().toString());
                        user.setName(name.getText().toString());

                        users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity() , "User Added Successfully" , Snackbar.LENGTH_LONG).show();
                                name.setText("");
                                phone.setText("");
                                mail.setText("");
                                password.setText("");

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity() , "Failed  : "+ e.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity() , "Failed " + e.getMessage() , Snackbar.LENGTH_LONG).show();
                    }
                });
            }
    }