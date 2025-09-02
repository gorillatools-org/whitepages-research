package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class ForwardingControllerListener implements ControllerListener {
    private final List mListeners = new ArrayList(2);

    public synchronized void addListener(ControllerListener controllerListener) {
        this.mListeners.add(controllerListener);
    }

    public synchronized void clearListeners() {
        this.mListeners.clear();
    }

    private synchronized void onException(String str, Throwable th) {
        Log.e("FdingControllerListener", str, th);
    }

    public synchronized void onSubmit(String str, Object obj) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener controllerListener = (ControllerListener) this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onSubmit(str, obj);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onSubmit", e);
            }
        }
    }

    public synchronized void onFinalImageSet(String str, Object obj, Animatable animatable) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener controllerListener = (ControllerListener) this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onFinalImageSet(str, obj, animatable);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onFinalImageSet", e);
            }
        }
    }

    public void onIntermediateImageSet(String str, Object obj) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener controllerListener = (ControllerListener) this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onIntermediateImageSet(str, obj);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onIntermediateImageSet", e);
            }
        }
    }

    public void onIntermediateImageFailed(String str, Throwable th) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener controllerListener = (ControllerListener) this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onIntermediateImageFailed(str, th);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onIntermediateImageFailed", e);
            }
        }
    }

    public synchronized void onFailure(String str, Throwable th) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener controllerListener = (ControllerListener) this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onFailure(str, th);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onFailure", e);
            }
        }
    }

    public synchronized void onRelease(String str) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener controllerListener = (ControllerListener) this.mListeners.get(i);
                if (controllerListener != null) {
                    controllerListener.onRelease(str);
                }
            } catch (Exception e) {
                onException("InternalListener exception in onRelease", e);
            }
        }
    }
}
