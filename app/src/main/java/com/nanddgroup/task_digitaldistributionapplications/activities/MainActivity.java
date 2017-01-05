package com.nanddgroup.task_digitaldistributionapplications.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nanddgroup.task_digitaldistributionapplications.App;
import com.nanddgroup.task_digitaldistributionapplications.R;
import com.nanddgroup.task_digitaldistributionapplications.adapters.StudentsAdapter;
import com.nanddgroup.task_digitaldistributionapplications.presenters.MainActivityPresenter;
import com.nanddgroup.task_digitaldistributionapplications.rest.entity.StudentEntity;
import com.nanddgroup.task_digitaldistributionapplications.views.IMainActivityView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainActivityView {
    @BindView(R.id.pbProgress)
    ProgressBar pbProgress;
    @BindView(R.id.rvStudents)
    RecyclerView rvStudents;

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

    @Override
    protected void onDestroy() {
        mainActivityPresenter.unbind();
        super.onDestroy();
    }

    @Override
    public void showMessage(String messageToShow) {
        Toast.makeText(this, messageToShow, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(List<StudentEntity> studentEntities) {
        showMessage(String.valueOf(studentEntities.size()));
        showMessage(studentEntities.get(228).getFirstName());
        studentsAdapter.appendStudents(studentEntities);

    }

    @Override
    public void showProgress() {
        pbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbProgress.setVisibility(View.GONE);
    }
}
