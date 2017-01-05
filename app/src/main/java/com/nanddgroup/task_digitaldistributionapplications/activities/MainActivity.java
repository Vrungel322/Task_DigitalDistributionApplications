package com.nanddgroup.task_digitaldistributionapplications.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.nanddgroup.task_digitaldistributionapplications.R;
import com.nanddgroup.task_digitaldistributionapplications.presenters.MainActivityPresenter;
import com.nanddgroup.task_digitaldistributionapplications.views.IMainActivityView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainActivityView {
    @BindView(R.id.tvTestText)
    TextView tvTestText;

    @Inject
    MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
    public void showData() {

    }
}
