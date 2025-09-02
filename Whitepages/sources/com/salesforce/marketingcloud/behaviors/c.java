package com.salesforce.marketingcloud.behaviors;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.collection.ArrayMap;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.f;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.util.j;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public class c extends f {
    public static final String i = "timestamp";
    static final int j = 1;
    static final String k = g.a("BehaviorManager");
    private final ExecutorService d;
    private final ArrayMap e = new ArrayMap();
    private final Map<a, Bundle> f = new ArrayMap(1);
    private final Context g;
    private BroadcastReceiver h;

    class a extends BroadcastReceiver {
        a() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                g.d(c.k, "Received null intent", new Object[0]);
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                g.d(c.k, "Received null action", new Object[0]);
                return;
            }
            a a2 = a.a(action);
            if (a2 != null) {
                c.this.onBehavior(a2, intent.getExtras());
            }
        }
    }

    static class b implements Runnable {
        final Set<b> a;
        final a b;
        final Bundle c;

        b(Set<b> set, a aVar, Bundle bundle) {
            this.a = set;
            this.b = aVar;
            this.c = bundle;
        }

        public void run() {
            for (b next : this.a) {
                if (next != null) {
                    try {
                        next.onBehavior(this.b, this.c);
                    } catch (Exception e) {
                        g.b(c.k, e, "Failure delivering behavior %s to %s", this.b.a, next.getClass().getName());
                    }
                }
            }
        }
    }

    public c(Context context, ExecutorService executorService) {
        this.g = context;
        this.d = executorService;
    }

    /* access modifiers changed from: protected */
    public void a(InitializationStatus.a aVar) {
        this.h = new a();
        IntentFilter intentFilter = new IntentFilter();
        for (a aVar2 : a.values()) {
            intentFilter.addAction(aVar2.a);
        }
        LocalBroadcastManager.getInstance(this.g).registerReceiver(this.h, intentFilter);
    }

    public final String componentName() {
        return "BehaviorManager";
    }

    public final JSONObject componentState() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public void onBehavior(a aVar, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putLong("timestamp", System.currentTimeMillis());
        g.a(k, "Behavior found: %s", aVar.name());
        synchronized (this.e) {
            Set set = (Set) this.e.get(aVar);
            if (set != null && !set.isEmpty()) {
                try {
                    this.d.submit(new b(Collections.unmodifiableSet(set), aVar, bundle));
                } catch (RejectedExecutionException e2) {
                    g.b(k, e2, "Unable to deliver behavior %s.", aVar.a);
                }
            }
        }
        synchronized (this.f) {
            try {
                if (aVar.b) {
                    this.f.put(aVar, bundle);
                }
                a aVar2 = aVar.c;
                if (aVar2 != null) {
                    this.f.put(aVar2, (Object) null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void tearDown(boolean z) {
        Context context = this.g;
        if (context != null) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(this.h);
        }
    }

    public static void a(Context context, a aVar, Bundle bundle) {
        j.a(context, "Context is null");
        j.a(aVar, "Behavior is null");
        Intent intent = new Intent(aVar.a);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @SuppressLint({"LambdaLast"})
    public void a(b bVar, EnumSet<a> enumSet) {
        j.a(bVar, "BehaviorListener is null");
        j.a(enumSet, "Behavior set is null");
        synchronized (this.e) {
            try {
                g.a(k, "Registering %s for behaviors: %s", bVar.getClass().getName(), enumSet.toString());
                Iterator<a> it = enumSet.iterator();
                while (it.hasNext()) {
                    a next = it.next();
                    Set set = (Set) this.e.get(next);
                    if (set == null) {
                        set = new HashSet();
                        this.e.put(next, set);
                    }
                    set.add(bVar);
                }
            } finally {
                while (true) {
                }
            }
        }
        synchronized (this.f) {
            try {
                Iterator<a> it2 = enumSet.iterator();
                while (it2.hasNext()) {
                    a next2 = it2.next();
                    if (next2.b && this.f.containsKey(next2)) {
                        this.d.submit(new b(Collections.singleton(bVar), next2, this.f.get(next2)));
                    }
                }
            } finally {
            }
        }
    }

    public void a(b bVar) {
        synchronized (this.e) {
            try {
                for (Map.Entry<Object, Object> value : this.e.entrySet()) {
                    ((Set) value.getValue()).remove(bVar);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
