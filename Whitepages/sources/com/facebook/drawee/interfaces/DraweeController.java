package com.facebook.drawee.interfaces;

import android.view.MotionEvent;

public interface DraweeController {
    DraweeHierarchy getHierarchy();

    void onAttach();

    void onDetach();

    boolean onTouchEvent(MotionEvent motionEvent);

    void setHierarchy(DraweeHierarchy draweeHierarchy);
}
