package com.salesforce.marketingcloud.location;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.LocationResult;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.util.h;
import com.salesforce.marketingcloud.util.l;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"UnknownNullness"})
public class LocationReceiver extends BroadcastReceiver {
    private static final String a = "com.salesforce.marketingcloud.LOCATION_UPDATE";
    private static final String b = "com.salesforce.marketingcloud.GEOFENCE_TRIGGERED";
    private static final String c = g.a("LocationReceiver");

    static boolean a(Context context) {
        return h.a(context.getPackageManager(), new Intent(context, LocationReceiver.class));
    }

    static PendingIntent b(Context context) {
        return PendingIntent.getBroadcast(context, 1, new Intent(context, LocationReceiver.class).setAction(b), l.b(134217728));
    }

    static PendingIntent c(Context context) {
        return PendingIntent.getBroadcast(context, 0, new Intent(context, LocationReceiver.class).setAction(a), l.b(134217728));
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null) {
            String str = c;
            g.d(str, "onReceive - %s", intent.getAction());
            if (!l.a(500, 50) || MarketingCloudSdk.getInstance() == null) {
                g.e(str, "MarketingCloudSdk#init must be called in your application's onCreate", new Object[0]);
                return;
            }
            String action = intent.getAction();
            action.hashCode();
            if (action.equals(b)) {
                a(context, GeofencingEvent.fromIntent(intent));
            } else if (action.equals(a)) {
                a(context, LocationResult.extractResult(intent));
            }
        }
    }

    private static void a(Context context, GeofencingEvent geofencingEvent) {
        LocalBroadcastManager instance;
        Intent a2;
        if (geofencingEvent == null) {
            g.d(c, "Geofencing event was null.", new Object[0]);
            return;
        }
        if (geofencingEvent.hasError()) {
            String statusCodeString = GeofenceStatusCodes.getStatusCodeString(geofencingEvent.getErrorCode());
            g.a(c, "Geofencing event contained error: %s", statusCodeString);
            instance = LocalBroadcastManager.getInstance(context);
            a2 = f.a(geofencingEvent.getErrorCode(), statusCodeString);
        } else if (geofencingEvent.getTriggeringGeofences() == null || geofencingEvent.getTriggeringGeofences().isEmpty()) {
            g.a(c, "GeofencingEvent without triggering geofences.", new Object[0]);
            return;
        } else {
            int geofenceTransition = geofencingEvent.getGeofenceTransition();
            g.d(c, "Geofencing event transition: %d", Integer.valueOf(geofenceTransition));
            ArrayList arrayList = new ArrayList();
            for (Geofence next : geofencingEvent.getTriggeringGeofences()) {
                g.d(c, "Triggered fence id: %s", next.getRequestId());
                arrayList.add(next.getRequestId());
            }
            instance = LocalBroadcastManager.getInstance(context);
            a2 = f.a(a(geofenceTransition), (List<String>) arrayList, geofencingEvent.getTriggeringLocation());
        }
        instance.sendBroadcast(a2);
    }

    private static void a(Context context, LocationResult locationResult) {
        if (locationResult == null) {
            g.d(c, "LocationResult was null.", new Object[0]);
            return;
        }
        Location lastLocation = locationResult.getLastLocation();
        if (lastLocation == null) {
            g.d(c, "LastLocation was null.", new Object[0]);
        } else {
            LocalBroadcastManager.getInstance(context).sendBroadcast(f.a(lastLocation));
        }
    }

    private static int a(int i) {
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                i2 = 4;
                if (i != 4) {
                    g.d(c, "Unknown geofence transition %d", Integer.valueOf(i));
                    return -1;
                }
            }
        }
        return i2;
    }
}
