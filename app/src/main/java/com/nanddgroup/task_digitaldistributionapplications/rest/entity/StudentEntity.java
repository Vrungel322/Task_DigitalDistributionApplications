
package com.nanddgroup.task_digitaldistributionapplications.rest.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class StudentEntity implements Parcelable {

    @SerializedName("id")
    private String id;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("birthday")
    private int birthday;
    @SerializedName("courses")
    private List<Course> courses = null;

    public StudentEntity(int birthday, List<Course> courses, String firstName, String id, String lastName) {
        this.birthday = birthday;
        this.courses = courses;
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeInt(birthday);
        parcel.writeList(courses);
    }

    protected StudentEntity(Parcel in) {
        courses = new ArrayList<Course>();
        id = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        birthday = in.readInt();
        in.readList(courses, null);
    }

    public static final Creator<StudentEntity> CREATOR = new Creator<StudentEntity>() {
        @Override
        public StudentEntity createFromParcel(Parcel in) {
            return new StudentEntity(in);
        }

        @Override
        public StudentEntity[] newArray(int size) {
            return new StudentEntity[size];
        }
    };
}
