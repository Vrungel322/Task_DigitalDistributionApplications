
package com.nanddgroup.task_digitaldistributionapplications.rest.entity;

import com.google.gson.annotations.SerializedName;

public class Course {

    @SerializedName("name")
    private String name;
    @SerializedName("mark")
    private int mark;

    public Course(int mark, String name) {
        this.mark = mark;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

}
