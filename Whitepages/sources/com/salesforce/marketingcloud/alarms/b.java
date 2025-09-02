package com.salesforce.marketingcloud.alarms;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MCReceiver;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.f;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.storage.j;
import com.salesforce.marketingcloud.util.l;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public class b extends f implements com.salesforce.marketingcloud.behaviors.b {
    public static final String j = "com.salesforce.marketingcloud.ACTION_ALARM_WAKE_EVENT";
    public static final String k = "com.salesforce.marketingcloud.WAKE_FOR_ALARM";
    static final String l = "pending_alarms";
    static final String m = g.a("AlarmScheduler");

    /* renamed from: n  reason: collision with root package name */
    private static final long f16n = 0;
    private final Map<a.C0001a, C0003b> d = new HashMap();
    private final com.salesforce.marketingcloud.behaviors.c e;
    BroadcastReceiver f;
    private Context g;
    private j h;
    private SharedPreferences i;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.salesforce.marketingcloud.behaviors.a[] r0 = com.salesforce.marketingcloud.behaviors.a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.salesforce.marketingcloud.behaviors.a r1 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_PACKAGE_REPLACED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.salesforce.marketingcloud.behaviors.a r1 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_BOOT_COMPLETE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.alarms.b.a.<clinit>():void");
        }
    }

    /* renamed from: com.salesforce.marketingcloud.alarms.b$b  reason: collision with other inner class name */
    public interface C0003b {
        void a(a.C0001a aVar);
    }

    class c extends BroadcastReceiver {
        c() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                g.d(b.m, "Received null intent", new Object[0]);
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                g.d(b.m, "Received null action", new Object[0]);
                return;
            }
            Bundle extras = intent.getExtras();
            if (extras == null) {
                g.d(b.m, "Intent had no extras", new Object[0]);
            } else if (!action.equals(b.j)) {
                g.a(b.m, "Received unknown action: %s", action);
            } else {
                String string = extras.getString("com.salesforce.marketingcloud.WAKE_FOR_ALARM", (String) null);
                if (string != null) {
                    g.d(b.m, "ACTION_ALARM_WAKE_EVENT had extra: %s", string);
                    try {
                        b.this.a(a.C0001a.valueOf(string));
                    } catch (IllegalArgumentException unused) {
                        g.e(b.m, "Woke for an unknown alarm: %s", string);
                    }
                }
            }
        }
    }

    public b(Context context, j jVar, com.salesforce.marketingcloud.behaviors.c cVar) {
        this.g = context;
        this.h = jVar;
        this.e = (com.salesforce.marketingcloud.behaviors.c) com.salesforce.marketingcloud.util.j.a(cVar, "BehaviorManager is null");
        this.i = jVar.f();
    }

    /* access modifiers changed from: package-private */
    public void a(a.C0001a aVar) {
        a(aVar);
        C0003b bVar = this.d.get(aVar);
        if (bVar != null) {
            bVar.a(aVar);
        }
    }

    public void b(a.C0001a... aVarArr) {
        for (a.C0001a a2 : aVarArr) {
            a(a2, false);
        }
    }

    public void c(a.C0001a... aVarArr) {
        for (a.C0001a aVar : aVarArr) {
            g.a(m, "Resetting %s Alarm Interval.", aVar.name());
            this.i.edit().putLong(aVar.b().c(), 0).apply();
        }
    }

    public final String componentName() {
        return "AlarmScheduler";
    }

    public final JSONObject componentState() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            for (a.C0001a aVar : a.C0001a.values()) {
                if (a(aVar, currentTimeMillis)) {
                    jSONObject2.put(aVar.name(), l.a(new Date(this.i.getLong(aVar.b().a(), 0) + this.i.getLong(aVar.b().c(), 0))));
                }
            }
            jSONObject.put(l, jSONObject2);
        } catch (JSONException e2) {
            g.b(m, e2, "Failed to generate Component State JSONObject.", new Object[0]);
        }
        return jSONObject;
    }

    public void d(a.C0001a... aVarArr) {
        for (a.C0001a aVar : aVarArr) {
            c(aVar);
            a(aVar);
            try {
                ((AlarmManager) this.g.getSystemService("alarm")).cancel(a(this.g, aVar.name(), Integer.valueOf(aVar.b().b())));
                g.a(m, "Reset %s alarm.", aVar.name());
            } catch (Exception e2) {
                g.e(m, e2, "Could not cancel %s alarm.", aVar.name());
            }
        }
    }

    public void e(a.C0001a... aVarArr) {
        synchronized (this.d) {
            try {
                for (a.C0001a remove : aVarArr) {
                    this.d.remove(remove);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void onBehavior(com.salesforce.marketingcloud.behaviors.a aVar, Bundle bundle) {
        int i2 = a.a[aVar.ordinal()];
        if (i2 == 1 || i2 == 2) {
            a(bundle.getLong("timestamp"));
        }
    }

    public final void tearDown(boolean z) {
        if (z) {
            d(a.C0001a.values());
        }
        Context context = this.g;
        if (context != null) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(this.f);
        }
        this.e.a((com.salesforce.marketingcloud.behaviors.b) this);
    }

    /* access modifiers changed from: package-private */
    public void a(a.C0001a... aVarArr) {
        for (a.C0001a aVar : aVarArr) {
            g.a(m, "Resetting %s Alarm Active Flag to FALSE", aVar.name());
            this.i.edit().putLong(aVar.b().a(), 0).apply();
        }
    }

    /* access modifiers changed from: package-private */
    public final long b(a.C0001a aVar) {
        long j2 = this.i.getLong(aVar.b().c(), 0);
        long d2 = j2 == 0 ? aVar.b().d() : (long) (((double) j2) * aVar.b().e());
        if (d2 <= aVar.b().f()) {
            return d2;
        }
        long f2 = aVar.b().f();
        g.a(m, "%s MAX INTERVAL exceeded. Setting interval to %s milliseconds.", aVar.name(), Long.valueOf(f2));
        return f2;
    }

    public boolean c(a.C0001a aVar) {
        return aVar.b().g() && a(aVar, true);
    }

    private boolean a(a.C0001a aVar, boolean z) {
        if (!aVar.a(this.h)) {
            g.a(m, "shouldCreateAlarm() for %s Alarm was FALSE.  Aborting alarm creation.", aVar.name());
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long b = b(aVar);
        if (!a(aVar, currentTimeMillis)) {
            g.a(m, "No pending %s Alarm. Creating one ...", aVar.name());
            a(aVar, currentTimeMillis, b);
            a(this.g, aVar, z ? 1000 : b, currentTimeMillis);
            return true;
        } else if (z) {
            return false;
        } else {
            g.a(m, "%s Send Pending ... will send at %s", aVar.name(), l.a(new Date(this.h.f().getLong(aVar.b().a(), 0) + b)));
            return false;
        }
    }

    private static PendingIntent a(Context context, String str, Integer num) {
        return PendingIntent.getBroadcast(context, num.intValue(), MCReceiver.a(context, str), l.a(134217728));
    }

    /* access modifiers changed from: protected */
    public final void a(InitializationStatus.a aVar) {
        this.e.a(this, EnumSet.of(com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_BOOT_COMPLETE, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_PACKAGE_REPLACED));
        this.f = new c();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(j);
        LocalBroadcastManager.getInstance(this.g).registerReceiver(this.f, intentFilter);
    }

    /* access modifiers changed from: package-private */
    public final boolean a(a.C0001a aVar, long j2) {
        return this.i.getLong(aVar.b().a(), 0) > j2 - this.i.getLong(aVar.b().c(), 0);
    }

    private void a(long j2) {
        for (a.C0001a aVar : a.C0001a.values()) {
            a b = aVar.b();
            long j3 = this.i.getLong(b.a(), 0);
            if (j3 > 0) {
                if (a(aVar, j2)) {
                    a(this.g, aVar, this.i.getLong(b.c(), b.d()), j3);
                } else {
                    a(aVar);
                }
            }
        }
    }

    @SuppressLint({"LambdaLast"})
    public void a(C0003b bVar, a.C0001a... aVarArr) {
        synchronized (this.d) {
            try {
                for (a.C0001a put : aVarArr) {
                    this.d.put(put, bVar);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void a(a.C0001a aVar, long j2, long j3) {
        g.a(m, "Setting the %s Alarm Flag ...", aVar.name());
        this.i.edit().putLong(aVar.b().a(), j2).putLong(aVar.b().c(), j3).apply();
    }

    /* access modifiers changed from: package-private */
    @SuppressLint({"MissingPermission"})
    public void a(Context context, a.C0001a aVar, long j2, long j3) {
        PendingIntent a2 = a(context, aVar.name(), Integer.valueOf(aVar.b().b()));
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        long j4 = j3 + j2;
        String a3 = l.a(new Date(j4));
        try {
            if (Build.VERSION.SDK_INT >= 31) {
                if (!alarmManager.canScheduleExactAlarms()) {
                    alarmManager.set(0, j4, a2);
                    g.d(m, "%s Alarm scheduled to wake at %s.", aVar.name(), a3);
                }
            }
            alarmManager.setExact(0, j4, a2);
            g.d(m, "%s Alarm scheduled to wake at %s.", aVar.name(), a3);
        } catch (Exception e2) {
            g.e(m, e2, "Failed to schedule alarm %s for %s", aVar.name(), a3);
        }
    }
}
