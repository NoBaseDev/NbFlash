package com.nobasedev.nbflash.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.util.Log;

public class FlashUtils {
    private static final String TAG = FlashUtils.class.getSimpleName();
    private Context mContext;
    private CameraManager mCameraManager;

    public static FlashUtils newInstance(Context contxt) {
        return new FlashUtils(contxt);
    }

    public FlashUtils(Context context) {
        mContext = context;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void turnFlashlightOn() {
        try {
            mCameraManager = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
            String cameraId = null;
            if (mCameraManager != null) {
                cameraId = mCameraManager.getCameraIdList()[0];
                mCameraManager.setTorchMode(cameraId, true);
            }
        } catch (CameraAccessException e) {
            Log.e(TAG, e.toString());
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void turnFlashlightOff() {
        try {
            String cameraId;
            mCameraManager = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
            if (mCameraManager != null) {
                cameraId = mCameraManager.getCameraIdList()[0]; // Usually front camera is at 0 position.
                mCameraManager.setTorchMode(cameraId, false);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
