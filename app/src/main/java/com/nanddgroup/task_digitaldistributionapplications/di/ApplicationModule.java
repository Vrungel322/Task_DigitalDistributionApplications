package com.nanddgroup.task_digitaldistributionapplications.di;

import android.content.Context;

import com.nanddgroup.task_digitaldistributionapplications.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nikita on 05.01.2017.
 */

@Module
public class ApplicationModule {
    private final App app;

    public ApplicationModule(Context app) {
        this.app = (App) app;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return app;
    }
}
