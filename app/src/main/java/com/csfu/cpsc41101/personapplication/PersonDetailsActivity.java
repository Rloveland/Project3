package com.csfu.cpsc41101.personapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.csfu.cpsc41101.personapplication.adapter.PersonDetailsAdapter;
import com.csfu.cpsc41101.personapplication.model.CourseEnrollment;
import com.csfu.cpsc41101.personapplication.model.Person;
import com.csfu.cpsc41101.personapplication.model.PersonDB;

public class PersonDetailsActivity extends AppCompatActivity {

    protected Menu detailMenu;
    protected int personIndx;
    protected Person pObj;
    protected final String TAG = "Detail Screen";
    protected ListView mCourseTable;
    protected PersonDetailsAdapter ad;
    protected Boolean isNew;
    protected Boolean isDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);

        personIndx = getIntent().getIntExtra("PersonIndex", 0);

        Button but = findViewById(R.id.add_course);
        isNew=false;
        //if add new person
        if(personIndx == PersonDB.getInstance().getPersonList().size()) {
            pObj = new Person("", "", 0);
            PersonDB.getInstance().getPersonList().add(pObj);
            but.setVisibility(View.VISIBLE);
            isNew = true;
            isDone = false;
        }
        else
            pObj = PersonDB.getInstance().getPersonList().get(personIndx);
            //
            EditText editView = findViewById(R.id.p_first_name_id);
            editView.setText(pObj.getFirstName());
            editView.setEnabled(isNew);
            editView = findViewById(R.id.p_last_name_id);
            editView.setText(pObj.getLastName());
            editView.setEnabled(isNew);
            editView = findViewById(R.id.p_cwid_id);
            editView.setText(pObj.getCWID() + "");
            editView.setEnabled(isNew);

        mCourseTable = findViewById(R.id.course_table);
        ad = new PersonDetailsAdapter(personIndx);
        mCourseTable.setAdapter(ad);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Page Navigation
                View v;
                EditText et, et2;

                int listLength = mCourseTable.getChildCount();
                for (int i = 0; i < listLength; i++)
                {
                    v = mCourseTable.getChildAt(i);
                    et = (EditText) v.findViewById(R.id.course_id);
                    et2 = (EditText) v.findViewById(R.id.grade);
                    PersonDB.getInstance().getPersonList().get(personIndx).getCourseEnrollments().get(i).setCourseID(et.getText().toString());
                    PersonDB.getInstance().getPersonList().get(personIndx).getCourseEnrollments().get(i).setGrade(et2.getText().toString());
                }
                PersonDB.getInstance().getPersonList().get(personIndx).getCourseEnrollments().add(new CourseEnrollment("",""));
                ad.notifyDataSetChanged(true);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Custom Menu inflation
        getMenuInflater().inflate(R.menu.detail_screen_menu, menu);
        if(findViewById(R.id.add_course).getVisibility()==View.VISIBLE){
            menu.findItem(R.id.action_edit).setVisible(false);
            menu.findItem(R.id.action_done).setVisible(true);
        }else{
        menu.findItem(R.id.action_edit).setVisible(true);
        menu.findItem(R.id.action_done).setVisible(false);
        }
        menu.findItem(R.id.action_add).setVisible(false);
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
        if(isNew && !isDone)
            PersonDB.getInstance().getPersonList().remove(personIndx);
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
        if (item.getItemId() == R.id.action_edit) {
            EditText editView = findViewById(R.id.p_first_name_id);
            editView.setEnabled(true);
            editView = findViewById(R.id.p_last_name_id);
            editView.setEnabled(true);
            editView = findViewById(R.id.p_cwid_id);
            editView.setEnabled(true);
            //
            Button but = findViewById(R.id.add_course);
            but.setVisibility(View.VISIBLE);
            //
            detailMenu.findItem(R.id.action_done).setVisible(true);
            View v;
            int listLength = mCourseTable.getChildCount();
            for (int i = 0; i < listLength; i++)
            {
                v = mCourseTable.getChildAt(i);
                v.findViewById(R.id.course_id).setEnabled(true);
                v.findViewById(R.id.grade).setEnabled(true);
            }
            //
            item.setVisible(false);
        } else if (item.getItemId() == R.id.action_done) {
            isDone = true;
            EditText editView = findViewById(R.id.p_first_name_id);
            PersonDB.getInstance().getPersonList().get(personIndx).setFirstName(editView.getText().toString());
            editView.setEnabled(false);
            editView = findViewById(R.id.p_last_name_id);
            PersonDB.getInstance().getPersonList().get(personIndx).setLastName(editView.getText().toString());
            editView.setEnabled(false);
            editView = findViewById(R.id.p_cwid_id);
            try{
            PersonDB.getInstance().getPersonList().get(personIndx).setCWID(Integer.parseInt(editView.getText().toString()));
            } catch (NumberFormatException nfe) {
                PersonDB.getInstance().getPersonList().get(personIndx).setCWID(0);}
            editView.setEnabled(false);
            //
            Button but = findViewById(R.id.add_course);
            but.setVisibility(View.INVISIBLE);
            //
            EditText et, et2;
            View v;
            int listLength = mCourseTable.getChildCount();
            for (int i = 0; i < listLength; i++)
            {
                v = mCourseTable.getChildAt(i);
                et = (EditText) v.findViewById(R.id.course_id);
                et2 = (EditText) v.findViewById(R.id.grade);
                PersonDB.getInstance().getPersonList().get(personIndx).getCourseEnrollments().get(i).setCourseID(et.getText().toString());
                PersonDB.getInstance().getPersonList().get(personIndx).getCourseEnrollments().get(i).setGrade(et2.getText().toString());
                et.setEnabled(false);
                et2.setEnabled(false);
            }
            //
            item.setVisible(false);
            detailMenu.findItem(R.id.action_edit).setVisible(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
