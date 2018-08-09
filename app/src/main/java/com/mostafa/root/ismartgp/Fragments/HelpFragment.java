package com.mostafa.root.ismartgp.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mostafa.root.ismartgp.Model.ProblemModel;
import com.mostafa.root.ismartgp.Model.UsersModel;
import com.mostafa.root.ismartgp.R;

public class HelpFragment extends Fragment {
    private EditText problemEdit;
    private Button submit_problem;
    private ImageView open_gmail_image;
    SharedPreferences mPreferences;
    SharedPreferences.Editor mEditor;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference problems;
    public HelpFragment(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.help_fragment, container, false);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mPreferences.edit();
        problemEdit = (EditText) rootView.findViewById(R.id.problem_editText);
        submit_problem = (Button) rootView.findViewById(R.id.submit_problem);
        open_gmail_image =  (ImageView) rootView.findViewById(R.id.open_gmail_to_send);
        open_gmail_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"ihome.speech@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity() , "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        problems = db.getReference("Problems");
        submit_problem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(TextUtils.isEmpty(problemEdit.getText().toString())){
                    Toast.makeText(getActivity() , "Please enter problem to send your feedback to support department" , Toast.LENGTH_LONG).show();
                    return;
                }
                ProblemModel problemModel = new ProblemModel();
                String mail = mPreferences.getString("current_mail" , "");
                problemModel.setFrom(mail);
                problemModel.setProblem(problemEdit.getText().toString());
                problems.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(problemModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity() , "Problem Added To Review" , Snackbar.LENGTH_LONG).show();
                        problemEdit.setText("");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity() , "Failed  : "+ e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

            }
        });
        return rootView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Help");
    }





}
