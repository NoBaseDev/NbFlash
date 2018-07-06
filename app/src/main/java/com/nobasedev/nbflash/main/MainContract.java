package com.nobasedev.nbflash.main;

import com.nobasedev.nbflash.BasePresenter;
import com.nobasedev.nbflash.BaseView;

public interface MainContract {
    interface View extends BaseView<Presenter>{
        void showFlash(boolean isOn);
    }

    interface Presenter extends BasePresenter {
        void turnOnFlash();
        void turnOffFlash();
    }
}
