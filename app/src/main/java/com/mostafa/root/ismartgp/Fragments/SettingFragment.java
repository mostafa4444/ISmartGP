package com.mostafa.root.ismartgp.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mostafa.root.ismartgp.MainDrawerActivity;
import com.mostafa.root.ismartgp.R;

import javax.crypto.SecretKey;

public class SettingFragment extends android.support.v4.app.Fragment {
    SharedPreferences mPreferences;
    SharedPreferences.Editor mEditor;
    RadioGroup num_of_action;
    public SettingFragment(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        num_of_action = (RadioGroup) rootView.findViewById(R.id.action_group_radio);
        RadioButton one = (RadioButton) rootView.findViewById(R.id.choose_one_action);
        RadioButton three = (RadioButton) rootView.findViewById(R.id.choose_three_action);
        RadioButton none = (RadioButton) rootView.findViewById(R.id.choose_none_action);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mPreferences.edit();
        String setChecked = mPreferences.getString("detect_action" , "");
        switch (setChecked){
            case "One":
                one.setChecked(true);
                break;
            case "Three":
                three.setChecked(true);
                break;
            case "None":
                none.setChecked(true);
                break;
            default:
                none.setChecked(true);
                mEditor.putString("detect_action" , "None");
                mEditor.apply(); mEditor.commit();
                break;
        }
        num_of_action.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.choose_one_action:
                        mEditor.putString("detect_action" , "One");
                        mEditor.apply(); mEditor.commit();
                        Intent i = getActivity().getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage( getActivity().getBaseContext().getPackageName() );
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        break;
                    case R.id.choose_three_action:
                        mEditor.putString("detect_action" , "Three");
                        mEditor.apply(); mEditor.commit();
                        Intent a = getActivity().getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage( getActivity().getBaseContext().getPackageName() );
                        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        break;
                    case R.id.choose_none_action:
                        mEditor.putString("detect_action" , "None");
                        mEditor.apply(); mEditor.commit();
                        Intent b = getActivity().getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage( getActivity().getBaseContext().getPackageName() );
                        b.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);;
                        break;
                }
            }
        });
        return rootView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Settings");
    }
}