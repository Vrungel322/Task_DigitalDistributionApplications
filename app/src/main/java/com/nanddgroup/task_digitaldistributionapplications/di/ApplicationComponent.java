package com.nanddgroup.task_digitaldistributionapplications.di;

import com.nanddgroup.task_digitaldistributionapplications.activities.MainActivity;
import com.nanddgroup.task_digitaldistributionapplications.fragments.StudentCoursesInfoDialog;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Nikita on 05.01.2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);

    void inject(StudentCoursesInfoDialog studentCoursesInfoDialog);
}
