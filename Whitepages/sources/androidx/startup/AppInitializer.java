package androidx.startup;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.tracing.Trace;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class AppInitializer {
    private static volatile AppInitializer sInstance;
    private static final Object sLock = new Object();
    final Context mContext;
    final Set mDiscovered = new HashSet();
    final Map mInitialized = new HashMap();

    AppInitializer(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static AppInitializer getInstance(Context context) {
        if (sInstance == null) {
            synchronized (sLock) {
                try {
                    if (sInstance == null) {
                        sInstance = new AppInitializer(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public Object initializeComponent(Class cls) {
        return doInitialize(cls);
    }

    public boolean isEagerlyInitialized(Class cls) {
        return this.mDiscovered.contains(cls);
    }

    /* access modifiers changed from: package-private */
    public Object doInitialize(Class cls) {
        Object obj;
        synchronized (sLock) {
            try {
                obj = this.mInitialized.get(cls);
                if (obj == null) {
                    obj = doInitialize(cls, new HashSet());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }

    private Object doInitialize(Class cls, Set set) {
        Object obj;
        if (Trace.isEnabled()) {
            try {
                Trace.beginSection(cls.getSimpleName());
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        if (!set.contains(cls)) {
            if (!this.mInitialized.containsKey(cls)) {
                set.add(cls);
                Initializer initializer = (Initializer) cls.getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
                List<Class> dependencies = initializer.dependencies();
                if (!dependencies.isEmpty()) {
                    for (Class cls2 : dependencies) {
                        if (!this.mInitialized.containsKey(cls2)) {
                            doInitialize(cls2, set);
                        }
                    }
                }
                obj = initializer.create(this.mContext);
                set.remove(cls);
                this.mInitialized.put(cls, obj);
            } else {
                obj = this.mInitialized.get(cls);
            }
            Trace.endSection();
            return obj;
        }
        throw new IllegalStateException(String.format("Cannot initialize %s. Cycle detected.", new Object[]{cls.getName()}));
    }

    /* access modifiers changed from: package-private */
    public void discoverAndInitialize() {
        try {
            Trace.beginSection("Startup");
            discoverAndInitialize(this.mContext.getPackageManager().getProviderInfo(new ComponentName(this.mContext.getPackageName(), InitializationProvider.class.getName()), 128).metaData);
            Trace.endSection();
        } catch (PackageManager.NameNotFoundException e) {
            throw new StartupException((Throwable) e);
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public void discoverAndInitialize(Bundle bundle) {
        String string = this.mContext.getString(R$string.androidx_startup);
        if (bundle != null) {
            try {
                HashSet hashSet = new HashSet();
                for (String next : bundle.keySet()) {
                    if (string.equals(bundle.getString(next, (String) null))) {
                        Class<?> cls = Class.forName(next);
                        if (Initializer.class.isAssignableFrom(cls)) {
                            this.mDiscovered.add(cls);
                        }
                    }
                }
                for (Class doInitialize : this.mDiscovered) {
                    doInitialize(doInitialize, hashSet);
                }
            } catch (ClassNotFoundException e) {
                throw new StartupException((Throwable) e);
            }
        }
    }
}
