package com.reapex.sv.model;

import android.app.Activity;
import android.util.Log;

import com.huawei.agconnect.auth.AGConnectAuth;
import com.huawei.agconnect.auth.SignInResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Handle login events
 */
public class LoginHelper {
    private static final String TAG = "LoginHelper";

    private List<OnLoginEventCallBack> mLoginCallbacks = new ArrayList<>();

    private Activity mActivity;

    public LoginHelper(Activity activity) {
        mActivity = activity;
    }

    public void login() {
        AGConnectAuth auth = AGConnectAuth.getInstance();
        auth.signInAnonymously().addOnSuccessListener(mActivity, signInResult -> {
            Log.w(TAG, "addOnSuccessListener: " + signInResult.getUser().getDisplayName());
            for (OnLoginEventCallBack loginEventCallBack : mLoginCallbacks) {
                loginEventCallBack.onLogin(true, signInResult);
            }
        }).addOnFailureListener(mActivity, e -> {
            Log.w(TAG, "sign in for agc failed: " + e.getMessage());
            for (OnLoginEventCallBack loginEventCallBack : mLoginCallbacks) {
                loginEventCallBack.onLogOut(false);
            }
        });
    }

    public void logOut() {
        AGConnectAuth auth = AGConnectAuth.getInstance();
        auth.signOut();
    }

    public void addLoginCallBack(OnLoginEventCallBack loginEventCallBack) {
        if (!mLoginCallbacks.contains(loginEventCallBack)) {
            mLoginCallbacks.add(loginEventCallBack);
        }
    }

    public interface OnLoginEventCallBack {
        void onLogin(boolean showLoginUserInfo, SignInResult signInResult);

        void onLogOut(boolean showLoginUserInfo);
    }
}
