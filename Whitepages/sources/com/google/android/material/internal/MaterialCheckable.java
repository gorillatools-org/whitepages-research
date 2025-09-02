package com.google.android.material.internal;

import android.widget.Checkable;

public interface MaterialCheckable extends Checkable {

    public interface OnCheckedChangeListener {
        void onCheckedChanged(Object obj, boolean z);
    }

    int getId();

    void setInternalOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener);
}
