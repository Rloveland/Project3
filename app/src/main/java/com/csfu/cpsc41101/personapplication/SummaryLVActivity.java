package com.csfu.cpsc41101.personapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.csfu.cpsc41101.personapplication.adapter.SummaryListAdapter;
import com.csfu.cpsc41101.personapplication.model.PersonDB;

public class SummaryLVActivity extends AppCompatActivity {

    protected Menu detailMenu;
    protected ListView mSummaryView;
    protected final String TAG = "Summary Screen";
    protected SummaryListAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        //
        setContentView(R.layout.person_list_lv);
        mSummaryView = findViewById(R.id.summary_list_id);
        ad = new SummaryListAdapter();
        mSummaryView.setAdapter(ad);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Custom Menu inflation
        getMenuInflater().inflate(R.menu.detail_screen_menu, menu);
        menu.findItem(R.id.action_edit).setVisible(false);
        menu.findItem(R.id.action_done).setVisible(false);
        menu.findItem(R.id.action_add).setVisible(true);
        detailMenu = menu;

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() called");
        ad.notifyDataSetChanged();
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume() called");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause() called");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop() called");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        super.onDestroy();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //go to persondetails and edit
        Intent intent = new Intent(SummaryLVActivity.this, PersonDetailsActivity.class);
        intent.putExtra("PersonIndex", PersonDB.getInstance().getPersonList().size());
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
