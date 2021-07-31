package com.example.puzzlegame.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.databinding.DataBindingUtil;

import com.example.puzzlegame.R;

public class AppUtil {
    private final String TAG = getTag(AppUtil.class);

    private Dialog mProgressDialog;

    public static String getTag(Class<?> classname) {
        return classname.getSimpleName();
    }

    public static String getResourceString(Context mContext, int key) {
        return mContext.getResources().getString(key);
    }

    public static boolean isValidString(String inputString) {
        return inputString != null && !inputString.isEmpty() && !inputString.equalsIgnoreCase("null");
    }

    public static String toStr(String inputString) {
        return (isValidString(inputString)) ? inputString : "";
    }

    public void showLoader(Context mContext) {
        try {
            if (mProgressDialog == null)
                mProgressDialog = new Dialog(mContext);

            mProgressDialog.setCancelable(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(mContext.getResources().getColor(R.color.colorTransparent)));
            mProgressDialog.getWindow().setDimAmount(0.75f);
            mProgressDialog.setContentView(DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.layout_custom_loader, null, false).getRoot());

            if (mProgressDialog.isShowing())
                mProgressDialog.dismiss();
            else
                mProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        }
    }

    public void dismissLoader() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }
}
