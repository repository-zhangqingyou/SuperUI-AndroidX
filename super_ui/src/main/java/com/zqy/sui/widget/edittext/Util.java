package com.zqy.sdk.sui.widget.edittext;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * @author matheew
 * @date 2019/5/21
 */
class Util {
    static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
