package com.salesforce.marketingcloud.proximity;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import androidx.collection.ArrayMap;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.internal.h;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.notifications.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;

class a implements BeaconConsumer, MonitorNotifier {
    static final String k = "m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24";
    static final int l = 121;
    static final String m = "0ahUKEwj";
    final Map<String, Region> a;
    private final BeaconManager b;
    private final Context c;
    private final List<c> d;
    final LocalBroadcastManager e;
    private boolean f;
    private boolean g;
    private BackgroundPowerSaver h;
    private Intent i;
    private final ProximityNotificationCustomizationOptions j;

    /* renamed from: com.salesforce.marketingcloud.proximity.a$a  reason: collision with other inner class name */
    class C0030a implements MarketingCloudSdk.WhenReadyListener {
        final /* synthetic */ Intent a;

        C0030a(Intent intent) {
            this.a = intent;
        }

        public void ready(MarketingCloudSdk marketingCloudSdk) {
            a.this.e.sendBroadcast(this.a);
        }
    }

    a(Context context) {
        this(context, (ProximityNotificationCustomizationOptions) null);
    }

    private void a() {
        String str = e.h;
        g.d(str, "clearAllMonitoredRegions", new Object[0]);
        if (!this.a.isEmpty()) {
            g.d(str, "Stop monitoring %d BeaconRegions", Integer.valueOf(this.a.size()));
            for (Region next : this.a.values()) {
                if (next != null) {
                    b(next);
                }
            }
            this.a.clear();
        }
    }

    private void b() {
        this.g = true;
        this.b.bind(this);
        g.a(e.h, "Waiting for BeaconService connection", new Object[0]);
    }

    private void c() {
        g.d(e.h, "monitorNewRegions", new Object[0]);
        if (!this.d.isEmpty()) {
            for (c next : this.d) {
                if (!this.a.containsKey(next.f())) {
                    Region a2 = a(next);
                    this.a.put(next.f(), a2);
                    g.d(e.h, "Now monitoring [%s]", next.toString());
                    this.b.startMonitoring(a2);
                } else {
                    g.d(e.h, "Region [%s] already monitored by SDK", next);
                }
            }
            this.d.clear();
        }
    }

    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i2) {
        this.i = intent;
        this.c.startService(intent);
        return this.c.bindService(intent, serviceConnection, i2);
    }

    public void d() {
        g.a(e.h, "stopMonitoring()", new Object[0]);
        synchronized (this.d) {
            try {
                if (this.f) {
                    a();
                    this.b.unbind(this);
                    this.b.removeMonitorNotifier(this);
                    if (this.h != null) {
                        ((Application) this.c.getApplicationContext()).unregisterActivityLifecycleCallbacks(this.h);
                    }
                    this.f = false;
                } else {
                    this.d.clear();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void didDetermineStateForRegion(int i2, Region region) {
        String str = e.h;
        g.d(str, "didDetermineStateForRegion(%d, %s)", Integer.valueOf(i2), region);
        if (MarketingCloudSdk.isReady() || MarketingCloudSdk.isInitializing()) {
            Intent putExtra = new Intent(i2 == 1 ? e.d : e.e).putExtra(e.f, a(region));
            if (MarketingCloudSdk.isReady()) {
                this.e.sendBroadcast(putExtra);
            } else {
                MarketingCloudSdk.requestSdk(new C0030a(putExtra));
            }
        } else {
            g.e(str, "MarketingCloudSdk#init must be called in your application's onCreate", new Object[0]);
        }
    }

    public void didEnterRegion(Region region) {
        g.d(e.h, "didEnterRegion(%s)", region);
    }

    public void didExitRegion(Region region) {
        g.d(e.h, "didExitRegion(%s)", region);
    }

    public Context getApplicationContext() {
        return this.c;
    }

    public void onBeaconServiceConnect() {
        g.a(e.h, "onBeaconServiceConnect", new Object[0]);
        synchronized (this.d) {
            this.h = new BackgroundPowerSaver(this.c);
            this.b.addMonitorNotifier(this);
            this.f = true;
            this.g = false;
            c();
        }
    }

    public void unbindService(ServiceConnection serviceConnection) {
        this.c.unbindService(serviceConnection);
        this.c.stopService(this.i);
        this.f = false;
        this.g = false;
    }

    a(Context context, ProximityNotificationCustomizationOptions proximityNotificationCustomizationOptions) {
        this.a = new ArrayMap();
        this.d = new ArrayList();
        this.c = context;
        this.e = LocalBroadcastManager.getInstance(context);
        BeaconManager instanceForApplication = BeaconManager.getInstanceForApplication(context);
        this.b = instanceForApplication;
        this.j = proximityNotificationCustomizationOptions;
        instanceForApplication.getBeaconParsers().add(new BeaconParser("iBeacon").setBeaconLayout(k));
        instanceForApplication.setBackgroundScanPeriod(5000);
        instanceForApplication.setBackgroundBetweenScanPeriod(10000);
        instanceForApplication.addMonitorNotifier(this);
    }

    private void a(ProximityNotificationCustomizationOptions proximityNotificationCustomizationOptions) {
        if (proximityNotificationCustomizationOptions != null) {
            c cVar = new c(proximityNotificationCustomizationOptions.getSmallIconResId(), proximityNotificationCustomizationOptions.getChannelIdProvider());
            HashMap hashMap = new HashMap();
            hashMap.put("alert", "Searching for available beacons ...");
            hashMap.put(NotificationMessage.NOTIF_KEY_ID, m);
            Notification build = cVar.setupNotificationBuilder(this.c, h.a(hashMap)).build();
            if (!this.b.isAnyConsumerBound()) {
                this.b.enableForegroundServiceScanning(build, l);
            }
        }
    }

    public void b(List<c> list) {
        g.a(e.h, "unmonitorBeaconRegions() - [%d regions]", Integer.valueOf(list.size()));
        if (!list.isEmpty()) {
            for (c next : list) {
                this.a.remove(next.f());
                b(a(next));
            }
        }
    }

    private void b(Region region) {
        try {
            this.b.stopMonitoring(region);
        } catch (Exception e2) {
            g.a(e.h, (Throwable) e2, "Failed to stop monitoring %s", region);
        }
    }

    public void a(List<c> list) {
        String str = e.h;
        g.a(str, "monitorBeaconRegions() - [%d regions]", Integer.valueOf(list.size()));
        a(this.j);
        this.b.addMonitorNotifier(this);
        if (!list.isEmpty()) {
            synchronized (this.d) {
                try {
                    this.d.clear();
                    this.d.addAll(list);
                    if (this.f) {
                        c();
                    } else {
                        g.d(str, "Not yet connected.  Will register Beacons once complete.", new Object[0]);
                        if (!this.g) {
                            b();
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    private static c a(Region region) {
        try {
            return new c(region.getUniqueId(), region.getId1().toString(), region.getId2().toInt(), region.getId3().toInt());
        } catch (Exception e2) {
            g.b(e.h, e2, "Unable to convert Region to BeaconRegion", new Object[0]);
            return null;
        }
    }

    static Region a(c cVar) {
        return new Region(cVar.f(), Identifier.fromUuid(UUID.fromString(cVar.e())), Identifier.fromInt(cVar.g()), Identifier.fromInt(cVar.h()));
    }
}
