package com.facebook.drawee.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.components.DraweeEventTracker;
import com.facebook.drawee.drawable.VisibilityAwareDrawable;
import com.facebook.drawee.drawable.VisibilityCallback;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;

public class DraweeHolder implements VisibilityCallback {
    private DraweeController mController = null;
    private final DraweeEventTracker mEventTracker = DraweeEventTracker.newInstance();
    private DraweeHierarchy mHierarchy;
    private boolean mIsControllerAttached = false;
    private boolean mIsHolderAttached = false;
    private boolean mIsVisible = true;

    public void registerWithContext(Context context) {
    }

    public static DraweeHolder create(DraweeHierarchy draweeHierarchy, Context context) {
        DraweeHolder draweeHolder = new DraweeHolder(draweeHierarchy);
        draweeHolder.registerWithContext(context);
        return draweeHolder;
    }

    public DraweeHolder(DraweeHierarchy draweeHierarchy) {
        if (draweeHierarchy != null) {
            setHierarchy(draweeHierarchy);
        }
    }

    public void onAttach() {
        this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_HOLDER_ATTACH);
        this.mIsHolderAttached = true;
        attachOrDetachController();
    }

    public void onDetach() {
        this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_HOLDER_DETACH);
        this.mIsHolderAttached = false;
        attachOrDetachController();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isControllerValid()) {
            return false;
        }
        return this.mController.onTouchEvent(motionEvent);
    }

    public void onVisibilityChange(boolean z) {
        if (this.mIsVisible != z) {
            this.mEventTracker.recordEvent(z ? DraweeEventTracker.Event.ON_DRAWABLE_SHOW : DraweeEventTracker.Event.ON_DRAWABLE_HIDE);
            this.mIsVisible = z;
            attachOrDetachController();
        }
    }

    public void onDraw() {
        if (!this.mIsControllerAttached) {
            FLog.w(DraweeEventTracker.class, "%x: Draw requested for a non-attached controller %x. %s", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(System.identityHashCode(this.mController)), toString());
            this.mIsHolderAttached = true;
            this.mIsVisible = true;
            attachOrDetachController();
        }
    }

    private void setVisibilityCallback(VisibilityCallback visibilityCallback) {
        Drawable topLevelDrawable = getTopLevelDrawable();
        if (topLevelDrawable instanceof VisibilityAwareDrawable) {
            ((VisibilityAwareDrawable) topLevelDrawable).setVisibilityCallback(visibilityCallback);
        }
    }

    public void setController(DraweeController draweeController) {
        boolean z = this.mIsControllerAttached;
        if (z) {
            detachController();
        }
        if (isControllerValid()) {
            this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_CLEAR_OLD_CONTROLLER);
            this.mController.setHierarchy((DraweeHierarchy) null);
        }
        this.mController = draweeController;
        if (draweeController != null) {
            this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_SET_CONTROLLER);
            this.mController.setHierarchy(this.mHierarchy);
        } else {
            this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_CLEAR_CONTROLLER);
        }
        if (z) {
            attachController();
        }
    }

    public void resetActualImage() {
        setController((DraweeController) null);
    }

    public DraweeController getController() {
        return this.mController;
    }

    public void setHierarchy(DraweeHierarchy draweeHierarchy) {
        this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_SET_HIERARCHY);
        boolean isControllerValid = isControllerValid();
        setVisibilityCallback((VisibilityCallback) null);
        DraweeHierarchy draweeHierarchy2 = (DraweeHierarchy) Preconditions.checkNotNull(draweeHierarchy);
        this.mHierarchy = draweeHierarchy2;
        Drawable topLevelDrawable = draweeHierarchy2.getTopLevelDrawable();
        onVisibilityChange(topLevelDrawable == null || topLevelDrawable.isVisible());
        setVisibilityCallback(this);
        if (isControllerValid) {
            this.mController.setHierarchy(draweeHierarchy);
        }
    }

    public DraweeHierarchy getHierarchy() {
        return (DraweeHierarchy) Preconditions.checkNotNull(this.mHierarchy);
    }

    public boolean hasHierarchy() {
        return this.mHierarchy != null;
    }

    public Drawable getTopLevelDrawable() {
        DraweeHierarchy draweeHierarchy = this.mHierarchy;
        if (draweeHierarchy == null) {
            return null;
        }
        return draweeHierarchy.getTopLevelDrawable();
    }

    public boolean isControllerValid() {
        DraweeController draweeController = this.mController;
        return draweeController != null && draweeController.getHierarchy() == this.mHierarchy;
    }

    private void attachController() {
        if (!this.mIsControllerAttached) {
            this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_ATTACH_CONTROLLER);
            this.mIsControllerAttached = true;
            DraweeController draweeController = this.mController;
            if (draweeController != null && draweeController.getHierarchy() != null) {
                this.mController.onAttach();
            }
        }
    }

    private void detachController() {
        if (this.mIsControllerAttached) {
            this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_DETACH_CONTROLLER);
            this.mIsControllerAttached = false;
            if (isControllerValid()) {
                this.mController.onDetach();
            }
        }
    }

    private void attachOrDetachController() {
        if (!this.mIsHolderAttached || !this.mIsVisible) {
            detachController();
        } else {
            attachController();
        }
    }

    public String toString() {
        return Objects.toStringHelper(this).add("controllerAttached", this.mIsControllerAttached).add("holderAttached", this.mIsHolderAttached).add("drawableVisible", this.mIsVisible).add("events", (Object) this.mEventTracker.toString()).toString();
    }
}
