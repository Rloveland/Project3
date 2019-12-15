package com.csfu.cpsc41101.personapplication.model;

import java.util.ArrayList;

public class PersonDB {
    private static final PersonDB ourInstance = new PersonDB();

    private ArrayList<Person> mPersonList;

    static public PersonDB getInstance() {
        return ourInstance;
    }

    private PersonDB() {
        createPersonObjects();
    }

    public ArrayList<Person> getPersonList() {
        return mPersonList;
    }

    public void setPersonList(ArrayList<Person> personList) {
        mPersonList = personList;
    }

    protected void createPersonObjects() {
        Person person = new Person("James", "Shen", 00000001);
        ArrayList<CourseEnrollment> courseEnrollments = new ArrayList<CourseEnrollment>();
        courseEnrollments.add(new CourseEnrollment("CPSC411", "A+"));
        courseEnrollments.add(new CourseEnrollment("CPSC412", "A-"));
        person.setCourseEnrollments(courseEnrollments);
        mPersonList = new ArrayList<Person>();
        mPersonList.add(person);

        person = new Person("Reeder", "Loveland", 88843704);
        courseEnrollments = new ArrayList<CourseEnrollment>();
        courseEnrollments.add(new CourseEnrollment("CPSC411", "A++"));
        person.setCourseEnrollments(courseEnrollments);
        mPersonList.add(person);
    }
}
