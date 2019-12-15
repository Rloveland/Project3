package com.csfu.cpsc41101.personapplication.model;

import java.util.ArrayList;

public class Person {
    protected String mFirstName;
    protected String mLastName;
    protected int mCWID;

    protected ArrayList<CourseEnrollment> mCourseEnrollments;

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
}
