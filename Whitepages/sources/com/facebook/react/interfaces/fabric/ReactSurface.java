package com.facebook.react.interfaces.fabric;

import android.content.Context;
import android.view.ViewGroup;
import com.facebook.react.interfaces.TaskInterface;

public interface ReactSurface {
    void clear();

    void detach();

    Context getContext();

    String getModuleName();

    int getSurfaceID();

    ViewGroup getView();

    boolean isRunning();

    TaskInterface<Void> prerender();

    TaskInterface<Void> start();

    TaskInterface<Void> stop();
}
