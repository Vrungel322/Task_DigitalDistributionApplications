package com.nanddgroup.task_digitaldistributionapplications.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanddgroup.task_digitaldistributionapplications.R;
import com.nanddgroup.task_digitaldistributionapplications.StudentEntity;
import com.nanddgroup.task_digitaldistributionapplications.presenters.MainActivityPresenter;
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
    private MainActivityPresenter mainActivityPresenter;
    private RecyclerView rvStudents;

    @Nullable
    private OnItemLongClickListener onItemLongClickListener;
    @Nullable
    private OnItemClickListener onItemClickListener;

    public StudentsAdapter(Context context, MainActivityPresenter mainActivityPresenter, RecyclerView rvStudents) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.mainActivityPresenter = mainActivityPresenter;
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
//        holder.tvStudentsBirthDate.setText(student.getBirthday());
        holder.ivInfo.setOnClickListener(view -> {
            //todo Info dialog
        });

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public StudentEntity getItem(int position) {
        return students.get(position);
    }

    public void appendStudents(List<StudentEntity> messages) {
        int size = getItemCount();
        this.students.addAll(messages);
        notifyItemRangeInserted(size, messages.size());
        notifyItemChanged(size - 1);
        scrollToLastMessage();
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

    public void setOnItemLongClickListener(@Nullable OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemClickListener(@Nullable OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemLongClickListener {
        void onLongClick(int position);
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}
