package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.BlockingServiceConnection;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads_identifier.zze;
import com.google.android.gms.internal.ads_identifier.zzf;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@KeepForSdk
public class AdvertisingIdClient {
    BlockingServiceConnection zza;
    zzf zzb;
    boolean zzc;
    final Object zzd;
    zzb zze;
    final long zzf;
    private final Context zzg;

    @KeepForSdkWithMembers
    public static final class Info {
        private final String zza;
        private final boolean zzb;

        @Deprecated
        public Info(String str, boolean z) {
            this.zza = str;
            this.zzb = z;
        }

        public String getId() {
            return this.zza;
        }

        public boolean isLimitAdTrackingEnabled() {
            return this.zzb;
        }

        public String toString() {
            String str = this.zza;
            boolean z = this.zzb;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 7);
            sb.append("{");
            sb.append(str);
            sb.append("}");
            sb.append(z);
            return sb.toString();
        }
    }

    @KeepForSdk
    public AdvertisingIdClient(Context context) {
        this(context, 30000, false, false);
    }

    @KeepForSdk
    public static Info getAdvertisingIdInfo(Context context) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(context, -1, true, false);
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            advertisingIdClient.zzb(false);
            Info zzd2 = advertisingIdClient.zzd(-1);
            advertisingIdClient.zzc(zzd2, true, 0.0f, SystemClock.elapsedRealtime() - elapsedRealtime, "", (Throwable) null);
            advertisingIdClient.zza();
            return zzd2;
        } catch (Throwable th) {
            advertisingIdClient.zza();
            throw th;
        }
    }

    @KeepForSdk
    public static boolean getIsAdIdFakeForDebugLogging(Context context) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        boolean zzd2;
        AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(context, -1, false, false);
        try {
            advertisingIdClient.zzb(false);
            Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
            synchronized (advertisingIdClient) {
                if (!advertisingIdClient.zzc) {
                    synchronized (advertisingIdClient.zzd) {
                        zzb zzb2 = advertisingIdClient.zze;
                        if (zzb2 == null || !zzb2.zzb) {
                            throw new IOException("AdvertisingIdClient is not connected.");
                        }
                    }
                    advertisingIdClient.zzb(false);
                    if (!advertisingIdClient.zzc) {
                        throw new IOException("AdvertisingIdClient cannot reconnect.");
                    }
                }
                Preconditions.checkNotNull(advertisingIdClient.zza);
                Preconditions.checkNotNull(advertisingIdClient.zzb);
                zzd2 = advertisingIdClient.zzb.zzd();
            }
            advertisingIdClient.zze();
            advertisingIdClient.zza();
            return zzd2;
        } catch (RemoteException e) {
            Log.i("AdvertisingIdClient", "GMS remote exception ", e);
            throw new IOException("Remote exception");
        } catch (Exception e2) {
            throw new IOException("AdvertisingIdClient cannot reconnect.", e2);
        } catch (Throwable th) {
            advertisingIdClient.zza();
            throw th;
        }
    }

    @ShowFirstParty
    @KeepForSdk
    public static void setShouldSkipGmsCoreVersionCheck(boolean z) {
    }

    private final Info zzd(int i) throws IOException {
        Info info;
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            try {
                if (!this.zzc) {
                    synchronized (this.zzd) {
                        zzb zzb2 = this.zze;
                        if (zzb2 == null || !zzb2.zzb) {
                            throw new IOException("AdvertisingIdClient is not connected.");
                        }
                    }
                    zzb(false);
                    if (!this.zzc) {
                        throw new IOException("AdvertisingIdClient cannot reconnect.");
                    }
                }
                Preconditions.checkNotNull(this.zza);
                Preconditions.checkNotNull(this.zzb);
                info = new Info(this.zzb.zzc(), this.zzb.zze(true));
            } catch (RemoteException e) {
                Log.i("AdvertisingIdClient", "GMS remote exception ", e);
                throw new IOException("Remote exception");
            } catch (Exception e2) {
                throw new IOException("AdvertisingIdClient cannot reconnect.", e2);
            } catch (Throwable th) {
                throw th;
            }
        }
        zze();
        return info;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:2|3|(3:5|6|7)|9|10|(1:12)|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0014 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zze() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.zzd
            monitor-enter(r0)
            com.google.android.gms.ads.identifier.zzb r1 = r5.zze     // Catch:{ all -> 0x0012 }
            if (r1 == 0) goto L_0x0014
            java.util.concurrent.CountDownLatch r1 = r1.zza     // Catch:{ all -> 0x0012 }
            r1.countDown()     // Catch:{ all -> 0x0012 }
            com.google.android.gms.ads.identifier.zzb r1 = r5.zze     // Catch:{ InterruptedException -> 0x0014 }
            r1.join()     // Catch:{ InterruptedException -> 0x0014 }
            goto L_0x0014
        L_0x0012:
            r1 = move-exception
            goto L_0x0025
        L_0x0014:
            long r1 = r5.zzf     // Catch:{ all -> 0x0012 }
            r3 = 0
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x0023
            com.google.android.gms.ads.identifier.zzb r3 = new com.google.android.gms.ads.identifier.zzb     // Catch:{ all -> 0x0012 }
            r3.<init>(r5, r1)     // Catch:{ all -> 0x0012 }
            r5.zze = r3     // Catch:{ all -> 0x0012 }
        L_0x0023:
            monitor-exit(r0)     // Catch:{ all -> 0x0012 }
            return
        L_0x0025:
            monitor-exit(r0)     // Catch:{ all -> 0x0012 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.identifier.AdvertisingIdClient.zze():void");
    }

    /* access modifiers changed from: protected */
    public final void finalize() throws Throwable {
        zza();
        super.finalize();
    }

    @KeepForSdk
    public Info getInfo() throws IOException {
        return zzd(-1);
    }

    @KeepForSdk
    public void start() throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        zzb(true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0034, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza() {
        /*
            r3 = this;
            java.lang.String r0 = "Calling this from your main thread can lead to deadlock"
            com.google.android.gms.common.internal.Preconditions.checkNotMainThread(r0)
            monitor-enter(r3)
            android.content.Context r0 = r3.zzg     // Catch:{ all -> 0x0031 }
            if (r0 == 0) goto L_0x0033
            com.google.android.gms.common.BlockingServiceConnection r0 = r3.zza     // Catch:{ all -> 0x0031 }
            if (r0 != 0) goto L_0x000f
            goto L_0x0033
        L_0x000f:
            boolean r0 = r3.zzc     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x0027
            com.google.android.gms.common.stats.ConnectionTracker r0 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ all -> 0x001f }
            android.content.Context r1 = r3.zzg     // Catch:{ all -> 0x001f }
            com.google.android.gms.common.BlockingServiceConnection r2 = r3.zza     // Catch:{ all -> 0x001f }
            r0.unbindService(r1, r2)     // Catch:{ all -> 0x001f }
            goto L_0x0027
        L_0x001f:
            r0 = move-exception
            java.lang.String r1 = "AdvertisingIdClient"
            java.lang.String r2 = "AdvertisingIdClient unbindService failed."
            android.util.Log.i(r1, r2, r0)     // Catch:{ all -> 0x0031 }
        L_0x0027:
            r0 = 0
            r3.zzc = r0     // Catch:{ all -> 0x0031 }
            r0 = 0
            r3.zzb = r0     // Catch:{ all -> 0x0031 }
            r3.zza = r0     // Catch:{ all -> 0x0031 }
            monitor-exit(r3)     // Catch:{ all -> 0x0031 }
            return
        L_0x0031:
            r0 = move-exception
            goto L_0x0035
        L_0x0033:
            monitor-exit(r3)     // Catch:{ all -> 0x0031 }
            return
        L_0x0035:
            monitor-exit(r3)     // Catch:{ all -> 0x0031 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.identifier.AdvertisingIdClient.zza():void");
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public final void zzb(boolean z) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            try {
                if (this.zzc) {
                    zza();
                }
                Context context = this.zzg;
                context.getPackageManager().getPackageInfo("com.android.vending", 0);
                int isGooglePlayServicesAvailable = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
                if (isGooglePlayServicesAvailable != 0) {
                    if (isGooglePlayServicesAvailable != 2) {
                        throw new IOException("Google Play services not available");
                    }
                }
                BlockingServiceConnection blockingServiceConnection = new BlockingServiceConnection();
                Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                intent.setPackage("com.google.android.gms");
                if (ConnectionTracker.getInstance().bindService(context, intent, blockingServiceConnection, 1)) {
                    this.zza = blockingServiceConnection;
                    this.zzb = zze.zza(blockingServiceConnection.getServiceWithTimeout(10000, TimeUnit.MILLISECONDS));
                    this.zzc = true;
                    if (z) {
                        zze();
                    }
                } else {
                    throw new IOException("Connection failure");
                }
            } catch (PackageManager.NameNotFoundException unused) {
                throw new GooglePlayServicesNotAvailableException(9);
            } catch (InterruptedException unused2) {
                throw new IOException("Interrupted exception");
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final boolean zzc(Info info, boolean z, float f, long j, String str, Throwable th) {
        if (Math.random() > 0.0d) {
            return false;
        }
        HashMap hashMap = new HashMap();
        String str2 = "1";
        hashMap.put("app_context", str2);
        if (info != null) {
            if (true != info.isLimitAdTrackingEnabled()) {
                str2 = "0";
            }
            hashMap.put("limit_ad_tracking", str2);
            String id = info.getId();
            if (id != null) {
                hashMap.put("ad_id_size", Integer.toString(id.length()));
            }
        }
        if (th != null) {
            hashMap.put("error", th.getClass().getName());
        }
        hashMap.put("tag", "AdvertisingIdClient");
        hashMap.put("time_spent", Long.toString(j));
        new zza(this, hashMap).start();
        return true;
    }

    @VisibleForTesting
    public AdvertisingIdClient(Context context, long j, boolean z, boolean z2) {
        Context applicationContext;
        this.zzd = new Object();
        Preconditions.checkNotNull(context);
        if (z && (applicationContext = context.getApplicationContext()) != null) {
            context = applicationContext;
        }
        this.zzg = context;
        this.zzc = false;
        this.zzf = j;
    }
}
