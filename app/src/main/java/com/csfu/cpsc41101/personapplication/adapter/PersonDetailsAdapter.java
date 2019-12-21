package com.csfu.cpsc41101.personapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.csfu.cpsc41101.personapplication.PersonDetailsActivity;
import com.csfu.cpsc41101.personapplication.R;
import com.csfu.cpsc41101.personapplication.model.CourseEnrollment;
import com.csfu.cpsc41101.personapplication.model.Person;
import com.csfu.cpsc41101.personapplication.model.PersonDB;

import java.util.ArrayList;

public class PersonDetailsAdapter extends BaseAdapter {

    protected ArrayList<CourseEnrollment> mCourses;
    PersonDB mPersonDB;
    Boolean editing;
    int mPersonIndex;

    public PersonDetailsAdapter(int personIndex, Context c) {
        mPersonDB = new PersonDB(c);
        mCourses = mPersonDB.getPersonList().get(personIndex).getCourseEnrollments();
        editing = false;
        mPersonIndex=personIndex;
    }

    @Override
    public int getCount() {
        return mCourses.size();
    }

    @Override
    public Object getItem(int i) {
        return mCourses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void notifyDataSetChanged(Boolean ed, Context c) {
        editing = ed;
        mCourses = new PersonDB(c).getPersonList().get(mPersonIndex).getCourseEnrollments();
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View row_view;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            row_view = inflater.inflate(R.layout.course_row, viewGroup, false);
        } else row_view = view;
        //
        CourseEnrollment c = mCourses.get(i);
        //
        ((EditText) row_view.findViewById(R.id.course_id)).setText(c.getCourseID());
        ((EditText) row_view.findViewById(R.id.grade)).setText(c.getGrade());
        ((EditText) row_view.findViewById(R.id.course_id)).setEnabled(editing);
        ((EditText) row_view.findViewById(R.id.grade)).setEnabled(editing);

        row_view.setTag(new Integer(i));

        return row_view;
    }
}
