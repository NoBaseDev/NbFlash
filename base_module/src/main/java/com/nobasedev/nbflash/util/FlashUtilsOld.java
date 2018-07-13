package com.nobasedev.nbflash.util;

import android.hardware.Camera;

public class FlashUtilsOld {
    private static final String TAG = FlashUtilsOld.class.getSimpleName();
    private Camera mCamera;
    private Camera.Parameters mParameters;

    public static FlashUtilsOld newInstance() {
        return new FlashUtilsOld();
    }

    public FlashUtilsOld() {
    }

    public void turnFlashlightOn() {
        if(mCamera == null) {
            mCamera = Camera.open();
        }
        mParameters = mCamera.getParameters();
        mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        mCamera.setParameters(mParameters);
        mCamera.startPreview();
    }

    public void turnFlashlightOff() {
        if(mCamera == null) {
            mCamera = Camera.open();
        }
        mParameters = mCamera.getParameters();
        mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        mCamera.setParameters(mParameters);
        mCamera.stopPreview();
    }
}
