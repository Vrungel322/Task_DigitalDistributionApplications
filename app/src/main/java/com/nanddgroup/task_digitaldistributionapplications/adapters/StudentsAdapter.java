package com.nanddgroup.task_digitaldistributionapplications.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanddgroup.task_digitaldistributionapplications.IConstants;
import com.nanddgroup.task_digitaldistributionapplications.R;
import com.nanddgroup.task_digitaldistributionapplications.activities.MainActivity;
import com.nanddgroup.task_digitaldistributionapplications.fragments.StudentCoursesInfoDialog;
import com.nanddgroup.task_digitaldistributionapplications.rest.entity.StudentEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nikita on 05.01.2017.
 */

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder> {
    private final LayoutInflater inflater;
    private final Context context;
    private final List<StudentEntity> students = new ArrayList<>();
    private final List<StudentEntity> students_savelist = new ArrayList<>();
    private RecyclerView rvStudents;

    private int lastPosition;

    public StudentsAdapter(Context context, RecyclerView rvStudents) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.rvStudents = rvStudents;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.studetns_list_item, parent, false);
        return new StudentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        StudentEntity student = getItem(position);
        Picasso.with(context)
                .load(R.drawable.student_icon)
                .into(holder.ivStudentIcon);
        holder.tvStudentsFullName.setText(student.getFirstName() + " " + student.getLastName());
        holder.tvStudentsBirthDate.setText(String.valueOf(student.getBirthday()));
//        holder.tvStudentsBirthDate.setText(Utility.millisToDate(student.getBirthday()));
        holder.ivInfo.setOnClickListener(view -> {
            StudentCoursesInfoDialog.newInstance(student)
                    .show(((MainActivity) context).getSupportFragmentManager(), "tag");
        });

        addAnimation(holder, position);
    }

    @Override
    public void onViewDetachedFromWindow(StudentViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    private void addAnimation(StudentViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public StudentEntity getItem(int position) {
        return students.get(position);
    }

    public void showFilteredStudents(List<StudentEntity> studentEntities) {
        this.students.clear();
        this.students.addAll(studentEntities);
        int size = getItemCount();
        notifyDataSetChanged();
    }

    public void showOneMorePageStudents(List<StudentEntity> studentEntities) {
        this.students.clear();
        this.students.addAll(studentEntities);
        int size = getItemCount();
        notifyItemRangeInserted(size, IConstants.PAGE_SIZE);
    }

    public void setStudents_savelist(List<StudentEntity> students) {
        students_savelist.clear();
        students_savelist.addAll(students);
    }

    private void scrollToLastMessage() {
        rvStudents.scrollToPosition(getItemCount() - 1);
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivStudentIcon)
        ImageView ivStudentIcon;
        @BindView(R.id.tvStudentsFullName)
        TextView tvStudentsFullName;
        @BindView(R.id.tvStudentsBirthDate)
        TextView tvStudentsBirthDate;
        @BindView(R.id.ivInfo)
        ImageView ivInfo;

        StudentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public boolean onFailedToRecycleView(StudentViewHolder holder) {
        return true;
    }

}
