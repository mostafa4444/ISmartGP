package com.mostafa.root.ismartgp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mostafa.root.ismartgp.Model.HomeAction;
import com.mostafa.root.ismartgp.Model.HomeActionAdapter;
import com.mostafa.root.ismartgp.Model.HomeHelper;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private RecyclerView actionRecyclerView;
    private List<HomeAction> homeActions;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        actionRecyclerView = (RecyclerView) findViewById(R.id.rec_view_all_action);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        actionRecyclerView.setHasFixedSize(true);
        actionRecyclerView.setLayoutManager(linearLayoutManager);
        homeActions = new ArrayList<>();
        adapter = new HomeActionAdapter(homeActions, ListActivity.this);
        actionRecyclerView.setAdapter(adapter);
        viewAllAction();

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(ListActivity.this , MainDrawerActivity.class));
        finish();
    }

    public void viewAllAction() {
        HomeHelper db = new HomeHelper(this);
        List<HomeAction> home = db.getAllAction();
        for (HomeAction c : home) {
            HomeAction action = new HomeAction(c.getAction_id(), c.getCurrent_action(), c.getNext_action()
                    , c.getAction_count());
            homeActions.add(action);



        }
    }
}