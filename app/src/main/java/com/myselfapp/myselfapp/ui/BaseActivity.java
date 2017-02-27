package com.myselfapp.myselfapp.ui;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends Activity {

    public static final int PERMISSION_RUNTIME_REQUESR_CODE =1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_RUNTIME_REQUESR_CODE:
                if (grantResults.length > 0) {
                    List<String> permissionList = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            permissionList.add(permission);
                        }
                    }
                    if (permissionList.isEmpty()) {
                        mPermissionListener.allow();
                    }else {
                        mPermissionListener.refuse(permissionList);
                    }
                }

                break;
            default:
                break;
        }
    }

    private PermissionListener mPermissionListener;

    public void requestRuntimePermission(String[] requestPermission, PermissionListener permissionListener) {
        mPermissionListener = permissionListener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : requestPermission) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (permissionList.isEmpty()) {
            mPermissionListener.allow();
        } else {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]),PERMISSION_RUNTIME_REQUESR_CODE);
        }
    }

    interface PermissionListener {
        void allow();

        void refuse(List<String> permission);
    }
}
