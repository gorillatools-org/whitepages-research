package com.salesforce.marketingcloud.location;

import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.salesforce.marketingcloud.g;
import java.util.List;

class d implements OnFailureListener {
    static final String e = g.a("GmsLocationProvider");
    private final Context a;
    volatile boolean b;
    int c;
    String d;

    class a implements OnCompleteListener<Void> {
        a() {
        }

        public void onComplete(Task<Void> task) {
            g.d(d.e, "Location request completed.", new Object[0]);
            d.this.b = false;
        }
    }

    class b implements OnCompleteListener<Void> {
        b() {
        }

        public void onComplete(Task<Void> task) {
            g.d(d.e, "Add Geofences request completed.", new Object[0]);
        }
    }

    d(Context context) throws IllegalStateException {
        this.a = context;
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        this.c = isGooglePlayServicesAvailable;
        this.d = instance.getErrorString(isGooglePlayServicesAvailable);
        int i = this.c;
        if (i != 0 && !instance.isUserResolvableError(i)) {
            int i2 = this.c;
            throw new g(i2, instance.getErrorString(i2));
        }
    }

    /* access modifiers changed from: package-private */
    public void a(b... bVarArr) throws SecurityException {
        if (bVarArr == null || bVarArr.length == 0) {
            g.d(e, "No GeofenceRegions provided", new Object[0]);
            return;
        }
        PendingIntent b2 = LocationReceiver.b(this.a);
        GeofencingRequest.Builder initialTrigger = new GeofencingRequest.Builder().setInitialTrigger(1);
        for (b bVar : bVarArr) {
            g.d(e, "Adding %s to geofence request", bVar.f());
            initialTrigger.addGeofence(a(bVar));
        }
        try {
            LocationServices.getGeofencingClient(this.a).addGeofences(initialTrigger.build(), b2).addOnFailureListener(this).addOnCompleteListener(new b());
        } catch (SecurityException e2) {
            g.b(e, e2, "ACCESS_FINE_LOCATION needed to request location.", new Object[0]);
            throw e2;
        }
    }

    /* access modifiers changed from: package-private */
    public String b() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        return this.c == 0;
    }

    /* access modifiers changed from: package-private */
    public void e() throws SecurityException {
        synchronized (this) {
            try {
                if (this.b) {
                    g.d(e, "Location request already being made.", new Object[0]);
                    return;
                }
                this.b = true;
                LocationServices.getFusedLocationProviderClient(this.a).requestLocationUpdates(LocationRequest.create().setNumUpdates(1).setPriority(100), LocationReceiver.c(this.a)).addOnFailureListener(this).addOnCompleteListener(new a());
            } catch (SecurityException e2) {
                g.b(e, e2, "ACCESS_FINE_LOCATION needed to request location.", new Object[0]);
                this.b = false;
                throw e2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void onFailure(Exception exc) {
        g.b(e, exc, "LocationServices failure", new Object[0]);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        LocationServices.getGeofencingClient(this.a).removeGeofences(LocationReceiver.b(this.a)).addOnFailureListener(this);
    }

    /* access modifiers changed from: package-private */
    public void a(List<String> list) {
        if (list == null || list.size() == 0) {
            g.d(e, "No GeofenceRegions provided", new Object[0]);
        } else {
            LocationServices.getGeofencingClient(this.a).removeGeofences(list).addOnFailureListener(this);
        }
    }

    private static Geofence a(b bVar) {
        int i = 1;
        if ((bVar.j() & 1) != 1) {
            i = 0;
        }
        if ((bVar.j() & 2) == 2) {
            i |= 2;
        }
        if ((bVar.j() & 4) == 4) {
            i |= 4;
        }
        return new Geofence.Builder().setRequestId(bVar.f()).setCircularRegion(bVar.g(), bVar.h(), bVar.i()).setTransitionTypes(i).setExpirationDuration(-1).build();
    }
}
