
package com.nanddgroup.task_digitaldistributionapplications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nanddgroup.task_digitaldistributionapplications.rest.entity.Course;

import java.util.List;


public class StudentEntity {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("birthday")
    @Expose
    private int birthday;
    @SerializedName("courses")
    @Expose
    private List<Course> courses = null;

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