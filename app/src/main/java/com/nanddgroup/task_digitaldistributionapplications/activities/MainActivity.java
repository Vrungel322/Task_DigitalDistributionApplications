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
import com.nanddgroup.task_digitaldistributionapplications.FilterParams;
import com.nanddgroup.task_digitaldistributionapplications.R;
import com.nanddgroup.task_digitaldistributionapplications.adapters.StudentsAdapter;
import com.nanddgroup.task_digitaldistributionapplications.fragments.FilteringDialog;
import com.nanddgroup.task_digitaldistributionapplications.presenters.MainActivityPresenter;
import com.nanddgroup.task_digitaldistributionapplications.rest.entity.StudentEntity;
import com.nanddgroup.task_digitaldistributionapplications.views.IMainActivityView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.everything.android.ui.overscroll.IOverScrollDecor;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

import static me.everything.android.ui.overscroll.IOverScrollState.STATE_BOUNCE_BACK;
import static me.everything.android.ui.overscroll.IOverScrollState.STATE_DRAG_END_SIDE;
import static me.everything.android.ui.overscroll.IOverScrollState.STATE_DRAG_START_SIDE;
import static me.everything.android.ui.overscroll.IOverScrollState.STATE_IDLE;

public class MainActivity extends AppCompatActivity implements IMainActivityView, FilteringDialog.IFilterData {
    @BindView(R.id.pbProgress)
    ProgressBar pbProgress;
    @BindView(R.id.rvStudents)
    RecyclerView rvStudents;
    @BindView(R.id.fabFilter)
    FloatingActionButton fabFilter;

    private StudentsAdapter studentsAdapter;
    private LinearLayoutManager linearLayoutManager;
    private FilterParams filterParams;

    @Inject
    MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getApp(this).getComponent().inject(this);
        linearLayoutManager = new LinearLayoutManager(this);
        rvStudents.setLayoutManager(linearLayoutManager);
        rvStudents.setItemAnimator(new DefaultItemAnimator());
        studentsAdapter = new StudentsAdapter(this, mainActivityPresenter, rvStudents);
        rvStudents.setAdapter(studentsAdapter);
//        rvStudents.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int visibleItemCount = linearLayoutManager.getChildCount();
//                int totalItemCount = linearLayoutManager.getItemCount();
//                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
//
////                if (!isLoading && !isLastPage) {
//                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
//                            && firstVisibleItemPosition >= 0
//                            && totalItemCount >= IConstants.PAGE_SIZE) {
//                        mainActivityPresenter.uploadOneMorePage();
//                    }
////                }
//            }
//        });
        IOverScrollDecor decor = OverScrollDecoratorHelper.setUpOverScroll(rvStudents, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        decor.setOverScrollStateListener((decor1, oldState, newState) -> {
            switch (newState) {
                case STATE_IDLE:
                    // No over-scroll is in effect.
                    break;
                case STATE_DRAG_START_SIDE:
                    // Dragging started at the left-end.
                    break;
                case STATE_DRAG_END_SIDE:
                    // Dragging started at the right-end.
                    break;
                case STATE_BOUNCE_BACK:
                    if (oldState == STATE_DRAG_START_SIDE) {
                        // Dragging stopped -- view is starting to bounce back from the *left-end* onto natural position.
                    } else { // i.e. (oldState == STATE_DRAG_END_SIDE)
                        // View is starting to bounce back from the *right-end*.
                        if (filterParams.isEmpty()){
                            mainActivityPresenter.uploadOneMorePage();
                        }
                        else {
                            mainActivityPresenter.increaseLimit();
                            mainActivityPresenter.filterData(filterParams);
                        }
                    }
                    break;
            }
        });

        mainActivityPresenter.bind(this);
        mainActivityPresenter.uploadData();

    }

    @OnClick(R.id.fabFilter)
    void onfabFilterClicked() {
//        mainActivityPresenter.filterData(new FilterParams(IConstants.DB.STUDENT_NAME_COURSE_0, 1));
        if (filterParams == null){
            FilteringDialog.newInstance(new FilterParams(FilterParams.NONE_COURSE, FilterParams.NONE_MARK))
                    .show(getSupportFragmentManager(), "filtering");
        }
        else {
            FilteringDialog.newInstance(filterParams).show(getSupportFragmentManager(), "filtering");
        }
    }

//    @Override
//    public void showData(List<StudentEntity> studentEntities) {
//        studentsAdapter.showUpdatedStudents(studentEntities);
//        studentsAdapter.setStudents_savelist(studentEntities);
//    }

    @Override
    public void showOneMorePageData(List<StudentEntity> studentEntities) {
        studentsAdapter.showOneMorePageStudents(studentEntities);
        studentsAdapter.setStudents_savelist(studentEntities);
    }

    @Override
    public void showFilteredData(List<StudentEntity> studentEntities) {
        studentsAdapter.showFilteredStudents(studentEntities);
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

    @Override
    public void filterByParams(FilterParams filterParams) {
        this.filterParams = filterParams;
        if (filterParams.getFilterParam_mark() == FilterParams.NONE_MARK
                | filterParams.getFilterParam_name().equals(FilterParams.NONE_COURSE)){
            filterParams.setFilterParam_mark(FilterParams.NONE_MARK);
            filterParams.setFilterParam_name(FilterParams.NONE_COURSE);
        }
        mainActivityPresenter.filterData(filterParams);
    }
}
