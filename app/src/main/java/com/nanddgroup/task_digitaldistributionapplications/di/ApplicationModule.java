package com.nanddgroup.task_digitaldistributionapplications.di;

import android.content.Context;

import com.nanddgroup.task_digitaldistributionapplications.App;
import com.nanddgroup.task_digitaldistributionapplications.IConstants;
import com.nanddgroup.task_digitaldistributionapplications.SessionRepository;
import com.nanddgroup.task_digitaldistributionapplications.data.SessionDataRepository;
import com.nanddgroup.task_digitaldistributionapplications.data.db.DBHelper;
import com.nanddgroup.task_digitaldistributionapplications.data.db.DbStudentHelper;
import com.nanddgroup.task_digitaldistributionapplications.data.mappers.StudentEntityToContentValueMapper;
import com.nanddgroup.task_digitaldistributionapplications.rest.Api;
import com.nanddgroup.task_digitaldistributionapplications.rest.RestApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
    Context provideContext() {
        return app;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkClient(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okClient) {
        return new Retrofit.Builder()
                .baseUrl(IConstants.BASE_URL)
                .client(okClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public Api providePeekabooApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    @Provides
    @Singleton
    public RestApi provideRestApi(Api api, Context c) {
        return new RestApi(api, c);
    }

    @Provides
    @Singleton
    public DBHelper provideDBHelper(Context context) {
        return new DBHelper(context);
    }

    @Provides
    @Singleton
    public StudentEntityToContentValueMapper provideMapper() {
        return new StudentEntityToContentValueMapper();
    }

    @Provides
    @Singleton
    public DbStudentHelper provideContactHelper(DBHelper helper, StudentEntityToContentValueMapper mapper) {
        return new DbStudentHelper(helper, mapper);
    }

    @Provides
    @Singleton
    public SessionRepository provideRepository(RestApi restApi, DbStudentHelper dbHelper) {
        return new SessionDataRepository(restApi, dbHelper);
    }
}
