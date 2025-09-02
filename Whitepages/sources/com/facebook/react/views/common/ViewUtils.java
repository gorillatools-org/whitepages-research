package com.facebook.react.views.common;

import android.view.View;
import com.facebook.react.R;

public final class ViewUtils {
    public static final ViewUtils INSTANCE = new ViewUtils();

    private ViewUtils() {
    }

    public static final String getTestId(View view) {
        Object tag = view != null ? view.getTag(R.id.react_test_id) : null;
        if (tag instanceof String) {
            return (String) tag;
        }
        return null;
    }
}
