
package com.nanddgroup.task_digitaldistributionapplications.rest.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class StudentEntity {

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

}
