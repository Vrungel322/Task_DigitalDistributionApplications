package com.nanddgroup.task_digitaldistributionapplications.presenters;

import com.nanddgroup.task_digitaldistributionapplications.rest.entity.Course;
import com.nanddgroup.task_digitaldistributionapplications.rest.entity.StudentEntity;
import com.nanddgroup.task_digitaldistributionapplications.views.IStudentCoursesInfoDialogView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Nikita on 06.01.2017.
 */

public class StudentCoursesInfoDialogPresenter extends BasePresenter<IStudentCoursesInfoDialogView> implements IStudentCoursesInfoDialogPresenter {

    @Inject
    public StudentCoursesInfoDialogPresenter() {
    }

    @Override
    public void countAverageMark(StudentEntity studentEntities) {
        List<Integer> marks = new ArrayList<>();
        for (Course course : studentEntities.getCourses()) {
            marks.add(course.getMark());
        }
        if (getView() != null) {
            getView().showAverageMark(String.valueOf(calculateAverage(marks)));
        }
    }

    private double calculateAverage(List<Integer> marks) {
        Integer sum = 0;
        if (!marks.isEmpty()) {
            for (Integer mark : marks) {
                sum += mark;
            }
            return sum.doubleValue() / marks.size();
        }
        return sum;
    }
}
