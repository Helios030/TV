package com.fongmi.android.tv.utils;

import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.fongmi.android.tv.R;
import com.google.android.material.button.MaterialButton;

public class DialogUtil {

    public static AlertDialog show(AlertDialog dialog) {
        dialog.setOnShowListener(dialogInterface -> style(dialog));
        dialog.show();
        return dialog;
    }

    public static AlertDialog style(AlertDialog dialog) {
        Window window = dialog.getWindow();
        if (window != null) {
            window.setDimAmount(0);
            window.setBackgroundDrawableResource(R.drawable.shape_dialog);
        }
        int white = ContextCompat.getColor(dialog.getContext(), R.color.white);
        int accent = ContextCompat.getColor(dialog.getContext(), R.color.player_item_border);
        tint(dialog.getWindow() == null ? null : dialog.getWindow().getDecorView(), white, accent);
        setButton(dialog, DialogInterface.BUTTON_POSITIVE, accent);
        setButton(dialog, DialogInterface.BUTTON_NEGATIVE, white);
        setButton(dialog, DialogInterface.BUTTON_NEUTRAL, white);
        return dialog;
    }

    private static void setButton(AlertDialog dialog, int which, int color) {
        TextView button = dialog.getButton(which);
        if (button != null) button.setTextColor(color);
    }

    private static void tint(View view, int white, int accent) {
        if (view == null) return;
        if (view instanceof ListView listView) listView.setCacheColorHint(0);
        if (view instanceof TextView textView && !(view instanceof MaterialButton)) textView.setTextColor(isSelected(view) ? accent : white);
        if (view instanceof ViewGroup group) for (int i = 0; i < group.getChildCount(); i++) tint(group.getChildAt(i), white, accent);
    }

    private static boolean isSelected(View view) {
        if (view instanceof CheckedTextView checkedTextView) return checkedTextView.isChecked();
        return view.isActivated() || view.isSelected();
    }
}
