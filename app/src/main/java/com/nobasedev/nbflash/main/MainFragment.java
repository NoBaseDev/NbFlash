package com.nobasedev.nbflash.main;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nobasedev.nbflash.R;

public class MainFragment extends Fragment implements MainContract.View {
    private static final String TAG = MainFragment.class.getSimpleName();
    private MainContract.Presenter mPresenter;

    private TextView mTextView;

    private boolean mIsFlashOn = false;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_frag, container, false);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFlash(!mIsFlashOn);
            }
        });
        mTextView = root.findViewById(R.id.tv_main);
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
            turnFlashlightOn();
        } else {
            turnFlashlightOff();
        }
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void turnFlashlightOn() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                CameraManager camManager = (CameraManager) getContext().getSystemService(Context.CAMERA_SERVICE);
                String cameraId = null; // Usually front camera is at 0 position.
                if (camManager != null) {
                    cameraId = camManager.getCameraIdList()[0];
                    camManager.setTorchMode(cameraId, true);
                }
            } catch (CameraAccessException e) {
                Log.e(TAG, e.toString());
            }
        } else {
            Camera mCamera = Camera.open();
            Camera.Parameters parameters = mCamera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(parameters);
            mCamera.startPreview();
        }
    }

    private void turnFlashlightOff() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                String cameraId;
                CameraManager camManager = (CameraManager) getContext().getSystemService(Context.CAMERA_SERVICE);
                if (camManager != null) {
                    cameraId = camManager.getCameraIdList()[0]; // Usually front camera is at 0 position.
                    camManager.setTorchMode(cameraId, false);
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        } else {
            Camera mCamera = Camera.open();
            Camera.Parameters parameters = mCamera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            mCamera.setParameters(parameters);
            mCamera.stopPreview();
        }
    }
}
