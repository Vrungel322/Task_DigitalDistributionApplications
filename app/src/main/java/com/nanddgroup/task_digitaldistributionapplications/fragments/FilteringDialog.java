package com.nanddgroup.task_digitaldistributionapplications.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.nanddgroup.task_digitaldistributionapplications.FilterParams;
import com.nanddgroup.task_digitaldistributionapplications.IConstants;
import com.nanddgroup.task_digitaldistributionapplications.R;
import com.nanddgroup.task_digitaldistributionapplications.adapters.AdapterelvCourses;
import com.nanddgroup.task_digitaldistributionapplications.adapters.AdapterelvMarks;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Nikita on 07.01.2017.
 */

public class FilteringDialog extends DialogFragment {
    @BindView(R.id.elvCourses)
    ExpandableListView elvCourses;
    @BindView(R.id.elvMarks)
    ExpandableListView elvMarks;
    @BindView(R.id.tvFilterCourse)
    TextView tvFilterCourse;
    @BindView(R.id.tvFilterMark)
    TextView tvFilterMark;
    @BindView(R.id.bClearFilters)
    Button bClearFilters;
    @BindView(R.id.bOkFiltering)
    Button bOkFiltering;


    private FilterParams filterParams;
    private IFilterData iFilterData;

    public static FilteringDialog newInstance(FilterParams filterParams) {
        Bundle args = new Bundle();
        args.putParcelable("FILTER_PARAMS", filterParams);
        FilteringDialog fragment = new FilteringDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        iFilterData = (IFilterData)activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        App.getApp(getActivity()).getComponent().inject(this);
        filterParams = getArguments().getParcelable("FILTER_PARAMS");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_filter_students, container, false);
        ButterKnife.bind(this, rootView);
        getDialog().setTitle("Filters");
        setIncomeFilterParams();
        settingsForELVCourses();
        settingsForELVMarks();

        return rootView;
    }

    private void setIncomeFilterParams() {
        if (filterParams.isEmpty()){
            tvFilterCourse.setText("");
            tvFilterMark.setText("");
        }
        else {
            tvFilterCourse.setText(filterParams.getFilterParam_name() + ", ");
            tvFilterMark.setText(String.valueOf(filterParams.getFilterParam_mark()));
        }
    }

    private void settingsForELVCourses() {
        AdapterelvCourses adapterelvCourses = new AdapterelvCourses(getActivity());
        elvCourses.setAdapter(adapterelvCourses.getAdapter());

        elvCourses.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            elvCourses.collapseGroup(groupPosition);
            switch (childPosition) {
                case 0:
                   tvFilterCourse.setText(" NoNE, ");
                    filterParams.setFilterParam_name(FilterParams.NONE_COURSE);
                    break;
                case 1:
                    tvFilterCourse.setText(" Course - 0, ");
                    filterParams.setFilterParam_name(IConstants.DB.STUDENT_NAME_COURSE_0);
                    break;
                case 2:
                    tvFilterCourse.setText(" Course - 1, ");
                    filterParams.setFilterParam_name(IConstants.DB.STUDENT_NAME_COURSE_1);
                    break;
                case 3:
                    tvFilterCourse.setText(" Course - 2, ");
                    filterParams.setFilterParam_name(IConstants.DB.STUDENT_NAME_COURSE_2);
                    break;
                case 4:
                    tvFilterCourse.setText(" Course - 3, ");
                    filterParams.setFilterParam_name(IConstants.DB.STUDENT_NAME_COURSE_3);
                    break;
            }
            return false;
        });
    }

    private void settingsForELVMarks() {
        AdapterelvMarks adapterelvCourses = new AdapterelvMarks(getActivity());
        elvMarks.setAdapter(adapterelvCourses.getAdapter());

        elvMarks.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            elvMarks.collapseGroup(groupPosition);
            switch (childPosition) {
                case 0:
                    tvFilterMark.setText(" NoNE");
                    filterParams.setFilterParam_mark(FilterParams.NONE_MARK);
                    break;
                case 1:
                    tvFilterMark.setText(" 1");
                    filterParams.setFilterParam_mark(1);
                    break;
                case 2:
                    tvFilterMark.setText(" 2");
                    filterParams.setFilterParam_mark(2);
                    break;
                case 3:
                    tvFilterMark.setText(" 3");
                    filterParams.setFilterParam_mark(3);
                    break;
                case 4:
                    tvFilterMark.setText(" 4");
                    filterParams.setFilterParam_mark(4);
                    break;
                case 5:
                    tvFilterMark.setText(" 5");
                    filterParams.setFilterParam_mark(5);
                    break;
            }
            return false;
        });
    }

    @OnClick(R.id.bOkFiltering)
    public void bOkFilteringClicked(){
        iFilterData.filterByParams(filterParams);
        getDialog().cancel();
    }

    @OnClick(R.id.bClearFilters)
    public void bClearFiltersClicked(){
        filterParams.setFilterParam_name(FilterParams.NONE_COURSE);
        filterParams.setFilterParam_mark(FilterParams.NONE_MARK);
        tvFilterCourse.setText("");
        tvFilterMark.setText("");
    }

   public interface IFilterData{
        void filterByParams(FilterParams filterParams);
    }
}
