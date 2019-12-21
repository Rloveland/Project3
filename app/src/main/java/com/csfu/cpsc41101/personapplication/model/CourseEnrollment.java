package com.csfu.cpsc41101.personapplication.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CourseEnrollment extends PersistentObject{
    protected String mCourseID;
    protected String mGrade;
    protected int mCWID;

    public CourseEnrollment(){}
    public CourseEnrollment(String id, String grd, int cwid) {
        mCourseID = id;
        mGrade = grd;
        mCWID = cwid;
    }

    public String getCourseID() {
        return mCourseID;
    }

    public void setCourseID(String courseID) {
        mCourseID = courseID;
    }

    public String getGrade() {
        return mGrade;
    }

    public void setGrade(String grade) {
        mGrade = grade;
    }

    public int getCWID() {
        return mCWID;
    }

    public void setCWID(int CWID) {
        mCWID = CWID;
    }

    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues vals = new ContentValues();
        vals.put("CourseID", mCourseID);
        vals.put("Grade", mGrade);
        vals.put("CWID", mCWID);
        db.insertOrThrow("COURSEENROLLMENT", null, vals);
    }

    @Override
    public void initFrom(Cursor c, SQLiteDatabase db) {
        mCourseID = c.getString(c.getColumnIndex("CourseID"));
        mGrade = c.getString(c.getColumnIndex("Grade"));
        mCWID = c.getInt(c.getColumnIndex("CWID"));
    }
}
