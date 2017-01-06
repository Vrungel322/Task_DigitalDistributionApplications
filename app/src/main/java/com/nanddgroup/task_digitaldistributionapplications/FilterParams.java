package com.nanddgroup.task_digitaldistributionapplications;

/**
 * Created by Nikita on 06.01.2017.
 */

public class FilterParams {
    private String filterParam_name;
    private int filterParam_mark;

    public FilterParams(String filterParam_name , int filterParam_mark) {
        this.filterParam_name = filterParam_name;
        this.filterParam_mark = filterParam_mark;
    }

    public String getFilterParam_name() {
        return filterParam_name;
    }

    public int getFilterParam_mark() {
        return filterParam_mark;
    }

    public boolean isEmpty(){
        if (this.filterParam_mark == 0 | this.filterParam_name.equals("NoNe")){
            return true;
        }
        else {
            return false;
        }
    }
}
