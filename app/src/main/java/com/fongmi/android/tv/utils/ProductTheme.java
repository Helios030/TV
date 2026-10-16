package com.fongmi.android.tv.utils;

import com.fongmi.android.tv.R;

public class ProductTheme {

    public static int getThemeId(int index) {
        switch (index) {
            case 1:
                return R.style.Theme_App_Red;
            case 2:
                return R.style.Theme_App_Green;
            case 3:
                return R.style.Theme_App_Gold;
            case 4:
                return R.style.Theme_App_Orange;
            case 5:
                return R.style.Theme_App_Purple;
            default:
                return R.style.Theme_App;
        }
    }
}
