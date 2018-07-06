package com.nobasedev.nbflash.main;

import android.support.annotation.Nullable;

import com.nobasedev.nbflash.data.Flash;

public class MainPresenter implements MainContract.Presenter {
    private final Flash mFlahs;
    private final MainContract.View mMainView;


    public MainPresenter(@Nullable Flash flash,
                         @Nullable MainContract.View mainView) {
        mFlahs = flash;
        mMainView = mainView;
        mMainView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void turnOnFlash() {

    }

    @Override
    public void turnOffFlash() {

    }
}
