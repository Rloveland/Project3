package com.csfu.cpsc41101.personapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

public class PersonDB {
    private Context context;
    private SQLiteDatabase mSQLiteDatabase;

    public PersonDB(Context c) {
        context = c;
        File dbFile = c.getDatabasePath("person.db");
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
        // Create Person and Vehicle tables if needed
        createSQLTables();
        retrievePersonObjects();
        //
    }

    private ArrayList<Person> mPersonList;

    private PersonDB() {
        createPersonObjects();
    }

    public ArrayList<Person> getPersonList() {
        return mPersonList;
    }

    public void setPersonList(ArrayList<Person> personList) {
        mPersonList = personList;
    }

    public ArrayList<Person> retrievePersonObjects() {
        ArrayList<Person> personList = new ArrayList<Person>();
        Cursor cursor = mSQLiteDatabase.query("PERSON", null, null, null, null, null,null );
        //cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Person pObj = new Person();
                pObj.initFrom(cursor, mSQLiteDatabase);
                personList.add(pObj);
            }
        }
        cursor.close();
        mPersonList = personList;
        return personList;
    }

    protected void createSQLTables() {
        String sql = "CREATE TABLE IF NOT EXISTS PERSON (FirstName Text, LastName Text, CWID INTEGER)";
        mSQLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE IF NOT EXISTS COURSEENROLLMENT (CourseID Text, Grade Text, CWID INTEGER)";
        mSQLiteDatabase.execSQL(sql);
    }

    public void createPersonObjects() {
        Person person = new Person("James", "Shen", 00000001);
        ArrayList<CourseEnrollment> courseEnrollments = new ArrayList<CourseEnrollment>();
        courseEnrollments.add(new CourseEnrollment("CPSC411", "A+",1));
        courseEnrollments.add(new CourseEnrollment("CPSC412", "A-",1));
        person.setCourseEnrollments(courseEnrollments);
        mPersonList = new ArrayList<Person>();
        person.insert(mSQLiteDatabase);
        mPersonList.add(person);

        person = new Person("Reeder", "Loveland", 88843704);
        courseEnrollments = new ArrayList<CourseEnrollment>();
        courseEnrollments.add(new CourseEnrollment("CPSC411", "A++", 88843704));
        person.setCourseEnrollments(courseEnrollments);
        person.insert(mSQLiteDatabase);
        mPersonList.add(person);
    }

    protected void addPerson(Person p){
        editPerson(p,mPersonList.size());
    }
    public void editPerson(Person p, int pos){
        ContentValues vals = new ContentValues();
        vals.put("FirstName", p.getFirstName());
        vals.put("LastName", p.getLastName());
        vals.put("CWID", p.getCWID());

        Cursor cursor = mSQLiteDatabase.query("PERSON", null, null, null, null, null,null );
        mSQLiteDatabase.update("Person", vals,"CWID = " + mPersonList.get(pos).getCWID(),  null);

        // Insert the vehicle objects
        cursor = mSQLiteDatabase.query("COURSEENROLLMENT", null, null, null, null, null,null );
        ArrayList<CourseEnrollment> courses = p.getCourseEnrollments();
        for (int i=0; i< courses.size(); i++) {
            vals = new ContentValues();
            vals.put("CourseId", courses.get(i).getCourseID());
            vals.put("Grade", courses.get(i).getGrade());
            vals.put("CWID", p.getCWID());
            if (mPersonList.get(pos).getCourseEnrollments().size() > i) {
                String cw =((Integer)mPersonList.get(pos).getCWID()).toString();
                String cID = mPersonList.get(pos).getCourseEnrollments().get(i).getCourseID();
                String[] arg = new String[]{cw, cID};
                mSQLiteDatabase.update("CourseEnrollment", vals,"CWID=? AND CourseID=?", arg);
            }
            else{
                mSQLiteDatabase.insertOrThrow("COURSEENROLLMENT", null, vals);
            }
        }
        cursor.close();
    }

    public void insertPerson(Person p){p.insert(mSQLiteDatabase);}
}
