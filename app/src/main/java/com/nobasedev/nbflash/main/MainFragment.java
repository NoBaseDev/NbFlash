package com.nobasedev.nbflash.main;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nobasedev.nbflash.R;
import com.nobasedev.nbflash.util.FlashUtils;
import com.nobasedev.nbflash.util.FlashUtilsOld;

public class MainFragment extends Fragment implements MainContract.View {
    private static final String TAG = MainFragment.class.getSimpleName();
    private MainContract.Presenter mPresenter;

    private View mFlash;

    private boolean mIsFlashOn = false;
    private FlashUtilsOld mFlashUtilsOld;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFlashUtilsOld = FlashUtilsOld.newInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_frag, container, false);
        mFlash = root.findViewById(R.id.v_flash);
        mFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFlash(!mIsFlashOn);
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        //mPresenter.start();
    }

    @Override
    public void showFlash(boolean isOn) {
        mIsFlashOn = isOn;
        if(isOn) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                FlashUtils.newInstance(getContext()).turnFlashlightOn();
            } else {
                mFlashUtilsOld.turnFlashlightOn();
            }
            mFlash.setBackgroundResource(R.drawable.flash_on);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                FlashUtils.newInstance(getContext()).turnFlashlightOff();
            } else {
                mFlashUtilsOld.turnFlashlightOff();
            }
            mFlash.setBackgroundResource(R.drawable.flash_off);
        }
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
