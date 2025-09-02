package com.salesforce.marketingcloud.behaviors;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.f;
import com.salesforce.marketingcloud.g;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public final class LifecycleManager extends f implements LifecycleObserver {
    static LifecycleManager g;
    private final Application d;
    private final AtomicBoolean e = new AtomicBoolean(false);
    AtomicBoolean f = new AtomicBoolean(false);

    private LifecycleManager(Application application) {
        this.d = application;
    }

    public static synchronized LifecycleManager a(Application application) {
        LifecycleManager lifecycleManager;
        synchronized (LifecycleManager.class) {
            try {
                if (g == null) {
                    g = new LifecycleManager(application);
                }
                lifecycleManager = g;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return lifecycleManager;
    }

    public String componentName() {
        return "LifecycleManager";
    }

    public JSONObject componentState() {
        return null;
    }

    /* access modifiers changed from: package-private */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onApplicationBackgrounded() {
        if (this.f.getAndSet(false)) {
            g.a(c.k, "Application went into the background.", new Object[0]);
            c.a(this.d, a.BEHAVIOR_APP_BACKGROUNDED, new Bundle());
        }
    }

    /* access modifiers changed from: package-private */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onApplicationForegrounded() {
        if (!this.f.getAndSet(true) && this.e.get()) {
            g.a(c.k, "Application came into the foreground.", new Object[0]);
            c.a(this.d, a.BEHAVIOR_APP_FOREGROUNDED, new Bundle());
        }
    }

    public void tearDown(boolean z) {
        this.e.set(false);
    }

    /* access modifiers changed from: protected */
    public void a(InitializationStatus.a aVar) {
        this.e.set(true);
        if (this.f.get()) {
            c.a(this.d, a.BEHAVIOR_APP_FOREGROUNDED, (Bundle) null);
        }
    }
}
