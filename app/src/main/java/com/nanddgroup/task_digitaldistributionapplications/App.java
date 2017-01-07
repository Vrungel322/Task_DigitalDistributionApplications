package com.nanddgroup.task_digitaldistributionapplications;

import android.app.Application;
import android.content.Context;

import com.nanddgroup.task_digitaldistributionapplications.di.ApplicationComponent;
import com.nanddgroup.task_digitaldistributionapplications.di.ApplicationModule;
import com.nanddgroup.task_digitaldistributionapplications.di.DaggerApplicationComponent;

/**
 * Created by Nikita on 05.01.2017.
 */

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    public static App getApp(Context context) {
        return (App) context.getApplicationContext();
    }
}
