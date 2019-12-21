package com.csfu.cpsc41101.personapplication.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Person extends PersistentObject {
    protected String mFirstName;
    protected String mLastName;
    protected int mCWID;

    protected ArrayList<CourseEnrollment> mCourseEnrollments;

    public Person(){}

    public Person(String fName, String lName, int cwid) {
        mFirstName = fName;
        mLastName = lName;
        mCWID = cwid;
        mCourseEnrollments = new ArrayList<CourseEnrollment>();
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public int getCWID() {
        return mCWID;
    }

    public void setCWID(int CWID) {
        mCWID = CWID;
    }

    public ArrayList<CourseEnrollment> getCourseEnrollments() {
        return mCourseEnrollments;
    }

    public void setCourseEnrollments(ArrayList<CourseEnrollment> courseEnrollments) {
        mCourseEnrollments = courseEnrollments;
    }

    @Override
    public void insert(SQLiteDatabase db) {
        //
        ContentValues vals = new ContentValues();
        vals.put("FirstName", mFirstName);
        vals.put("LastName", mLastName);
        vals.put("CWID", mCWID);

        db.insert("Person", null, vals);

        // Insert the vehicle objects
        for (int i=0; i<mCourseEnrollments.size(); i++) {
            mCourseEnrollments.get(i).insert(db);
        }
    }

    @Override
    public void initFrom(Cursor c, SQLiteDatabase db) {
        mFirstName = c.getString(c.getColumnIndex("FirstName"));
        mLastName = c.getString(c.getColumnIndex("LastName"));
        mCWID = c.getInt(c.getColumnIndex("CWID"));

        // retrieve the owned vehicle objects
        mCourseEnrollments = new ArrayList<CourseEnrollment>();
        Cursor cursor = db.query("COURSEENROLLMENT", null, "CWID=?", new String[]{new Integer(mCWID).toString()},null,null,null);

        cursor.moveToFirst();
    if (cursor.getCount() > 0) {
             do{
                CourseEnrollment cObj = new CourseEnrollment();
                cObj.initFrom(cursor, db);
                mCourseEnrollments.add(cObj);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}
