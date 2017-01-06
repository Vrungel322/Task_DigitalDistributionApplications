package com.nanddgroup.task_digitaldistributionapplications.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nanddgroup.task_digitaldistributionapplications.App;
import com.nanddgroup.task_digitaldistributionapplications.IConstants;
import com.nanddgroup.task_digitaldistributionapplications.R;
import com.nanddgroup.task_digitaldistributionapplications.adapters.StudentsAdapter;
import com.nanddgroup.task_digitaldistributionapplications.presenters.MainActivityPresenter;
import com.nanddgroup.task_digitaldistributionapplications.rest.entity.StudentEntity;
import com.nanddgroup.task_digitaldistributionapplications.views.IMainActivityView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IMainActivityView {
    @BindView(R.id.pbProgress)
    ProgressBar pbProgress;
    @BindView(R.id.rvStudents)
    RecyclerView rvStudents;
    @BindView(R.id.fabFilter)
    FloatingActionButton fabFilter;

    private StudentsAdapter studentsAdapter;

    @Inject
    MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getApp(this).getComponent().inject(this);
        rvStudents.setLayoutManager(new LinearLayoutManager(this));
        rvStudents.setItemAnimator(new DefaultItemAnimator());
        studentsAdapter = new StudentsAdapter(this, mainActivityPresenter, rvStudents);
        rvStudents.setAdapter(studentsAdapter);
        mainActivityPresenter.bind(this);
        mainActivityPresenter.uploadData();

    }

    @OnClick(R.id.fabFilter)
    void onfabFilterClicked(){
        mainActivityPresenter.filterData(IConstants.DB.STUDENT_NAME_COURSE_0, 1);
    }

    @Override
    public void showData(List<StudentEntity> studentEntities) {
        studentsAdapter.appendStudents(studentEntities);
    }

    @Override
    public void showFilteredData(List<StudentEntity> studentEntities) {
        studentsAdapter.showUpdatedStudents(studentEntities);
    }

    @Override
    public void showMessage(String messageToShow) {
        Toast.makeText(this, messageToShow, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        rvStudents.setVisibility(View.GONE);
        pbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        rvStudents.setVisibility(View.VISIBLE);
        pbProgress.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        mainActivityPresenter.unbind();
        super.onDestroy();
    }
}
