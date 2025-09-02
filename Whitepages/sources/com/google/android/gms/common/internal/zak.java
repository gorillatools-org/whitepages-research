package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.base.zau;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class zak implements Handler.Callback {
    final ArrayList zaa = new ArrayList();
    private final zaj zab;
    private final ArrayList zac = new ArrayList();
    private final ArrayList zad = new ArrayList();
    private volatile boolean zae = false;
    private final AtomicInteger zaf = new AtomicInteger(0);
    private boolean zag = false;
    private final Handler zah;
    private final Object zai = new Object();

    public zak(Looper looper, zaj zaj) {
        this.zab = zaj;
        this.zah = new zau(looper, this);
    }

    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) message.obj;
            synchronized (this.zai) {
                try {
                    if (this.zae && this.zab.isConnected() && this.zac.contains(connectionCallbacks)) {
                        connectionCallbacks.onConnected((Bundle) null);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return true;
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle message: " + i, new Exception());
        return false;
    }

    public final void zaa() {
        this.zae = false;
        this.zaf.incrementAndGet();
    }

    public final void zab() {
        this.zae = true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zac(com.google.android.gms.common.ConnectionResult r6) {
        /*
            r5 = this;
            android.os.Handler r0 = r5.zah
            java.lang.String r1 = "onConnectionFailure must only be called on the Handler thread"
            com.google.android.gms.common.internal.Preconditions.checkHandlerThread(r0, r1)
            android.os.Handler r0 = r5.zah
            r1 = 1
            r0.removeMessages(r1)
            java.lang.Object r0 = r5.zai
            monitor-enter(r0)
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0046 }
            java.util.ArrayList r2 = r5.zad     // Catch:{ all -> 0x0046 }
            r1.<init>(r2)     // Catch:{ all -> 0x0046 }
            java.util.concurrent.atomic.AtomicInteger r2 = r5.zaf     // Catch:{ all -> 0x0046 }
            int r2 = r2.get()     // Catch:{ all -> 0x0046 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0046 }
        L_0x0021:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x0046 }
            if (r3 == 0) goto L_0x004a
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x0046 }
            com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener r3 = (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) r3     // Catch:{ all -> 0x0046 }
            boolean r4 = r5.zae     // Catch:{ all -> 0x0046 }
            if (r4 == 0) goto L_0x0048
            java.util.concurrent.atomic.AtomicInteger r4 = r5.zaf     // Catch:{ all -> 0x0046 }
            int r4 = r4.get()     // Catch:{ all -> 0x0046 }
            if (r4 == r2) goto L_0x003a
            goto L_0x0048
        L_0x003a:
            java.util.ArrayList r4 = r5.zad     // Catch:{ all -> 0x0046 }
            boolean r4 = r4.contains(r3)     // Catch:{ all -> 0x0046 }
            if (r4 == 0) goto L_0x0021
            r3.onConnectionFailed(r6)     // Catch:{ all -> 0x0046 }
            goto L_0x0021
        L_0x0046:
            r6 = move-exception
            goto L_0x004c
        L_0x0048:
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            return
        L_0x004a:
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            return
        L_0x004c:
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zak.zac(com.google.android.gms.common.ConnectionResult):void");
    }

    public final void zad(Bundle bundle) {
        Preconditions.checkHandlerThread(this.zah, "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.zai) {
            try {
                Preconditions.checkState(!this.zag);
                this.zah.removeMessages(1);
                this.zag = true;
                Preconditions.checkState(this.zaa.isEmpty());
                ArrayList arrayList = new ArrayList(this.zac);
                int i = this.zaf.get();
                Iterator it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                    if (!this.zae || !this.zab.isConnected()) {
                        break;
                    } else if (this.zaf.get() != i) {
                        break;
                    } else if (!this.zaa.contains(connectionCallbacks)) {
                        connectionCallbacks.onConnected(bundle);
                    }
                }
                this.zaa.clear();
                this.zag = false;
            } finally {
            }
        }
    }

    public final void zae(int i) {
        Preconditions.checkHandlerThread(this.zah, "onUnintentionalDisconnection must only be called on the Handler thread");
        this.zah.removeMessages(1);
        synchronized (this.zai) {
            try {
                this.zag = true;
                ArrayList arrayList = new ArrayList(this.zac);
                int i2 = this.zaf.get();
                Iterator it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                    if (!this.zae) {
                        break;
                    } else if (this.zaf.get() != i2) {
                        break;
                    } else if (this.zac.contains(connectionCallbacks)) {
                        connectionCallbacks.onConnectionSuspended(i);
                    }
                }
                this.zaa.clear();
                this.zag = false;
            } finally {
            }
        }
    }

    public final void zaf(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.zai) {
            try {
                if (this.zac.contains(connectionCallbacks)) {
                    String valueOf = String.valueOf(connectionCallbacks);
                    Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + valueOf + " is already registered");
                } else {
                    this.zac.add(connectionCallbacks);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (this.zab.isConnected()) {
            Handler handler = this.zah;
            handler.sendMessage(handler.obtainMessage(1, connectionCallbacks));
        }
    }

    public final void zag(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.zai) {
            try {
                if (this.zad.contains(onConnectionFailedListener)) {
                    String valueOf = String.valueOf(onConnectionFailedListener);
                    Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + valueOf + " is already registered");
                } else {
                    this.zad.add(onConnectionFailedListener);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void zah(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.zai) {
            try {
                if (!this.zac.remove(connectionCallbacks)) {
                    String valueOf = String.valueOf(connectionCallbacks);
                    Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + valueOf + " not found");
                } else if (this.zag) {
                    this.zaa.add(connectionCallbacks);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void zai(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.zai) {
            try {
                if (!this.zad.remove(onConnectionFailedListener)) {
                    String valueOf = String.valueOf(onConnectionFailedListener);
                    Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + valueOf + " not found");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean zaj(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        boolean contains;
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.zai) {
            contains = this.zac.contains(connectionCallbacks);
        }
        return contains;
    }

    public final boolean zak(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        boolean contains;
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.zai) {
            contains = this.zad.contains(onConnectionFailedListener);
        }
        return contains;
    }
}
