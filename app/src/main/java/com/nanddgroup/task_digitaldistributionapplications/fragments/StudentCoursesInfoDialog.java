package com.nanddgroup.task_digitaldistributionapplications.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nanddgroup.task_digitaldistributionapplications.App;
import com.nanddgroup.task_digitaldistributionapplications.R;
import com.nanddgroup.task_digitaldistributionapplications.adapters.CoursesInfoAdapter;
import com.nanddgroup.task_digitaldistributionapplications.presenters.StudentCoursesInfoDialogPresenter;
import com.nanddgroup.task_digitaldistributionapplications.rest.entity.StudentEntity;
import com.nanddgroup.task_digitaldistributionapplications.views.IStudentCoursesInfoDialogView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Nikita on 06.01.2017.
 */

public class StudentCoursesInfoDialog extends DialogFragment implements IStudentCoursesInfoDialogView {
    @BindView(R.id.lvCourses)
    ListView lvCourses;
    @BindView(R.id.tvAverageMark)
    TextView tvAverageMark;
    @BindView(R.id.bOkDialogCoursesInfo)
    Button bOkDialogCoursesInfo;

    @Inject
    StudentCoursesInfoDialogPresenter presenter;

    private StudentEntity studentEntity;
    private CoursesInfoAdapter coursesInfoAdapter;

    public static StudentCoursesInfoDialog newInstance(StudentEntity studentEntity) {
        Bundle args = new Bundle();
        args.putParcelable("STUDENT", studentEntity);
        StudentCoursesInfoDialog fragment = new StudentCoursesInfoDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getApp(getActivity()).getComponent().inject(this);
        presenter.bind(this);
        studentEntity = getArguments().getParcelable("STUDENT");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_courses_info, container, false);
        ButterKnife.bind(this, rootView);
        getDialog().setTitle("Courses");
        coursesInfoAdapter = new CoursesInfoAdapter(getContext(), R.layout.course_list_item);
        coursesInfoAdapter.updateList(studentEntity.getCourses());
        lvCourses.setAdapter(coursesInfoAdapter);
        presenter.countAverageMark(studentEntity);
        return rootView;
    }

    @OnClick(R.id.bOkDialogCoursesInfo)
    public void bOkDialogCoursesInfoClicked(){
        getDialog().cancel();
    }

    @Override
    public void showAverageMark(String mark) {
        tvAverageMark.setText("Average mark : " + mark);
    }

    @Override
    public void showMessage(String messageToShow) {

    }

    @Override
    public void onDestroy() {
        presenter.unbind();
        super.onDestroy();
    }
}
