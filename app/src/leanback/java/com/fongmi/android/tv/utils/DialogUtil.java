package com.fongmi.android.tv.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.res.ColorStateList;
import androidx.appcompat.app.AlertDialog;

public class DialogUtil {

    public static void show(AlertDialog dialog) {
        dialog.show();
        setupFocusColor(dialog.getWindow().getDecorView());
    }

    private static void setupFocusColor(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                setupFocusColor(group.getChildAt(i));
            }
        }
        if (view.isFocusable()) {
            View.OnFocusChangeListener originalListener = view.getOnFocusChangeListener();
            view.setOnFocusChangeListener((v, hasFocus) -> {
                setChildColor(v, hasFocus);
                if (originalListener != null) {
                    originalListener.onFocusChange(v, hasFocus);
                }
            });
        }
    }

    private static void setChildColor(View view, boolean hasFocus) {
        int color = hasFocus ? 0xFFFFCD00 : 0xFFFFFFFF;
        changeColorRecursive(view, color, hasFocus);
    }

    private static void changeColorRecursive(View view, int color, boolean hasFocus) {
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(color);
        } else if (view instanceof ImageView) {
            if (hasFocus) {
                ((ImageView) view).setImageTintList(ColorStateList.valueOf(color));
            } else {
                ((ImageView) view).setImageTintList(null);
            }
        } else if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                changeColorRecursive(group.getChildAt(i), color, hasFocus);
            }
        }
    }
}
