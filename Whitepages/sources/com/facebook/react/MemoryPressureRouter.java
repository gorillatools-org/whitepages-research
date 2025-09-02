package com.facebook.react;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import com.facebook.react.bridge.MemoryPressureListener;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class MemoryPressureRouter implements ComponentCallbacks2 {
    private final CopyOnWriteArrayList<MemoryPressureListener> mListeners = new CopyOnWriteArrayList<>();

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onLowMemory() {
    }

    public MemoryPressureRouter(Context context) {
        context.getApplicationContext().registerComponentCallbacks(this);
    }

    public void destroy(Context context) {
        context.getApplicationContext().unregisterComponentCallbacks(this);
    }

    public void addMemoryPressureListener(MemoryPressureListener memoryPressureListener) {
        if (!this.mListeners.contains(memoryPressureListener)) {
            this.mListeners.add(memoryPressureListener);
        }
    }

    public void removeMemoryPressureListener(MemoryPressureListener memoryPressureListener) {
        this.mListeners.remove(memoryPressureListener);
    }

    public void onTrimMemory(int i) {
        dispatchMemoryPressure(i);
    }

    private void dispatchMemoryPressure(int i) {
        Iterator<MemoryPressureListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().handleMemoryPressure(i);
        }
    }
}
