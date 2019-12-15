package com.csfu.cpsc41101.personapplication.model;

public class CourseEnrollment {
    protected String mCourseID;
    protected String mGrade;

    public CourseEnrollment(String id, String grd) {
        mCourseID = id;
        mGrade = grd;
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

}
