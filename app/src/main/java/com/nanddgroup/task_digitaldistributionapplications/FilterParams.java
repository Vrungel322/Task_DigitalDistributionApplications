package com.nanddgroup.task_digitaldistributionapplications;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nikita on 06.01.2017.
 */

public class FilterParams implements Parcelable {
    public static final int NONE_MARK = 0;
    public static final String NONE_COURSE = "NoNe";
    private String filterParam_name;
    private int filterParam_mark;

    public FilterParams(String filterParam_name, int filterParam_mark) {
        this.filterParam_name = filterParam_name;
        this.filterParam_mark = filterParam_mark;
    }

    public String getFilterParam_name() {
        return filterParam_name;
    }

    public int getFilterParam_mark() {
        return filterParam_mark;
    }

    public void setFilterParam_mark(int filterParam_mark) {
        this.filterParam_mark = filterParam_mark;
    }

    public void setFilterParam_name(String filterParam_name) {
        this.filterParam_name = filterParam_name;
    }

    public static FilterParams produseEmpty() {
        return new FilterParams(NONE_COURSE, NONE_MARK);
    }

    public boolean isEmpty() {
        if (this.filterParam_mark == NONE_MARK | this.filterParam_name.equals(NONE_COURSE)) {
            return true;
        } else {
            return false;
        }
    }

    protected FilterParams(Parcel in) {
        filterParam_name = in.readString();
        filterParam_mark = in.readInt();
    }

    public static final Creator<FilterParams> CREATOR = new Creator<FilterParams>() {
        @Override
        public FilterParams createFromParcel(Parcel in) {
            return new FilterParams(in);
        }

        @Override
        public FilterParams[] newArray(int size) {
            return new FilterParams[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(filterParam_name);
        parcel.writeInt(filterParam_mark);
    }
}
