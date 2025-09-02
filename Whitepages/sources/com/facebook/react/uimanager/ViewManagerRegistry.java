package com.facebook.react.uimanager;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.MapBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class ViewManagerRegistry implements ComponentCallbacks2 {
    private final ViewManagerResolver mViewManagerResolver;
    private final Map<String, ViewManager> mViewManagers;

    public void onConfigurationChanged(Configuration configuration) {
    }

    public ViewManagerRegistry(ViewManagerResolver viewManagerResolver) {
        this.mViewManagers = MapBuilder.newHashMap();
        this.mViewManagerResolver = viewManagerResolver;
    }

    public ViewManagerRegistry(List<ViewManager> list) {
        HashMap newHashMap = MapBuilder.newHashMap();
        for (ViewManager next : list) {
            newHashMap.put(next.getName(), next);
        }
        this.mViewManagers = newHashMap;
        this.mViewManagerResolver = null;
    }

    public ViewManagerRegistry(Map<String, ViewManager> map) {
        this.mViewManagers = map == null ? MapBuilder.newHashMap() : map;
        this.mViewManagerResolver = null;
    }

    public synchronized ViewManager get(String str) {
        try {
            ViewManager viewManager = this.mViewManagers.get(str);
            if (viewManager != null) {
                return viewManager;
            }
            String str2 = "RCT" + str;
            ViewManager viewManager2 = this.mViewManagers.get(str2);
            if (viewManager2 != null) {
                return viewManager2;
            }
            if (this.mViewManagerResolver != null) {
                ViewManager viewManagerFromResolver = getViewManagerFromResolver(str);
                if (viewManagerFromResolver != null) {
                    return viewManagerFromResolver;
                }
                ViewManager viewManagerFromResolver2 = getViewManagerFromResolver(str2);
                if (viewManagerFromResolver2 != null) {
                    return viewManagerFromResolver2;
                }
                throw new IllegalViewOperationException("ViewManagerResolver returned null for either " + str + " or " + str2 + ", existing names are: " + this.mViewManagerResolver.getViewManagerNames());
            }
            throw new IllegalViewOperationException("No ViewManager found for class " + str);
        } catch (Throwable th) {
            throw th;
        }
    }

    private ViewManager getViewManagerFromResolver(String str) {
        ViewManager viewManager = this.mViewManagerResolver.getViewManager(str);
        if (viewManager != null) {
            this.mViewManagers.put(str, viewManager);
        }
        return viewManager;
    }

    /* access modifiers changed from: package-private */
    public synchronized ViewManager getViewManagerIfExists(String str) {
        ViewManager viewManager = this.mViewManagers.get(str);
        if (viewManager != null) {
            return viewManager;
        }
        if (this.mViewManagerResolver == null) {
            return null;
        }
        return getViewManagerFromResolver(str);
    }

    public void onSurfaceStopped(int i) {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.mViewManagers.values());
        }
        ViewManagerRegistry$$ExternalSyntheticLambda1 viewManagerRegistry$$ExternalSyntheticLambda1 = new ViewManagerRegistry$$ExternalSyntheticLambda1(arrayList, i);
        if (UiThreadUtil.isOnUiThread()) {
            viewManagerRegistry$$ExternalSyntheticLambda1.run();
        } else {
            UiThreadUtil.runOnUiThread(viewManagerRegistry$$ExternalSyntheticLambda1);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onSurfaceStopped$0(List list, int i) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((ViewManager) it.next()).onSurfaceStopped(i);
        }
    }

    public void invalidate() {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.mViewManagers.values());
        }
        ViewManagerRegistry$$ExternalSyntheticLambda0 viewManagerRegistry$$ExternalSyntheticLambda0 = new ViewManagerRegistry$$ExternalSyntheticLambda0(arrayList);
        if (UiThreadUtil.isOnUiThread()) {
            viewManagerRegistry$$ExternalSyntheticLambda0.run();
        } else {
            UiThreadUtil.runOnUiThread(viewManagerRegistry$$ExternalSyntheticLambda0);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$invalidate$1(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((ViewManager) it.next()).invalidate();
        }
    }

    public void onTrimMemory(int i) {
        final ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.mViewManagers.values());
        }
        AnonymousClass1 r0 = new Runnable() {
            public void run() {
                for (ViewManager trimMemory : arrayList) {
                    trimMemory.trimMemory();
                }
            }
        };
        if (UiThreadUtil.isOnUiThread()) {
            r0.run();
        } else {
            UiThreadUtil.runOnUiThread(r0);
        }
    }

    public void onLowMemory() {
        onTrimMemory(0);
    }
}
