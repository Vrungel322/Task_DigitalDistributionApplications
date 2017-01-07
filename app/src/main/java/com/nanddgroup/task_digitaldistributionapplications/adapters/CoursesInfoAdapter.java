package com.nanddgroup.task_digitaldistributionapplications.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nanddgroup.task_digitaldistributionapplications.R;
import com.nanddgroup.task_digitaldistributionapplications.rest.entity.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita on 06.01.2017.
 */

public class CoursesInfoAdapter extends ArrayAdapter<Course> {
    private List<Course> list;
    private Context context;
    private LayoutInflater inflater;

    public CoursesInfoAdapter(Context context, int resource) {
        super(context, resource);
        list = new ArrayList<>();
        this.context = context;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ;
    }

    public void updateList(List<Course> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Course getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.course_list_item, parent, false);
        }
        ((TextView) view.findViewById(R.id.tvCourseName)).setText(String.valueOf(getItem(position).getName()));
        ((TextView) view.findViewById(R.id.tvCourseMark)).setText(String.valueOf(getItem(position).getMark()));

        return view;
    }
}
