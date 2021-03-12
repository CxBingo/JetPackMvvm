package com.bingo.basemodule.network.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bingo.basemodule.R;


/**
 * Created by francisbingo on 3/12/21 11:54 AM
 * <p>
 * 自定义Toast工具类
 */
public class ToastUtils {

    public static int POSITION_DEFAULT = -200;
    public static int POSITION_MIDDLE = 334;

    private static final int TIME = 500;
    private static long lastOprationExetingTime = 0;

    public static boolean showOprationToast(Context context) {
        long now = System.currentTimeMillis();
        if (now - lastOprationExetingTime < TIME) {
            showCommanToast(context.getApplicationContext(), "请勿频繁操作");
            return true;
        }
        lastOprationExetingTime = System.currentTimeMillis();
        return false;
    }

    public static void showCommanToast(Context context, String string) {
        showCommanToast(context, string, POSITION_DEFAULT);
    }

    public static void showCommanToast(Context context, String string, int yOffSet) {
        if (TextUtils.isEmpty(string)) {
            return;
        } else if (null == context) {
            return;
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view;
        if (string.length() > 5) {
            view = inflater.inflate(R.layout.common_toast_center_layout, null);
        } else {
            view = inflater.inflate(R.layout.common_toast_top_layout, null);
        }
        Toast toast = new Toast(context);
        TextView textView = view.findViewById(R.id.toast_tv);
        textView.setText(string);
        toast.setGravity(Gravity.CENTER, 0, yOffSet);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }


}
