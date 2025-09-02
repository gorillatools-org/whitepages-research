package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.AttributionSource;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.wrappers.AttributionSourceWrapper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

@KeepForSdk
public abstract class BaseGmsClient<T extends IInterface> {
    @KeepForSdk
    public static final int CONNECT_STATE_CONNECTED = 4;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTED = 1;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTING = 5;
    @KeepForSdk
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    @KeepForSdk
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = {"service_esmobile", "service_googleme"};
    @KeepForSdk
    public static final String KEY_PENDING_INTENT = "pendingIntent";
    private static final Feature[] zze = new Feature[0];
    private volatile String zzA;
    private volatile AttributionSourceWrapper zzB;
    /* access modifiers changed from: private */
    public ConnectionResult zzC;
    /* access modifiers changed from: private */
    public boolean zzD;
    private volatile zzk zzE;
    zzv zza;
    final Handler zzb;
    protected ConnectionProgressReportCallbacks zzc;
    protected AtomicInteger zzd;
    private int zzf;
    private long zzg;
    private long zzh;
    private int zzi;
    private long zzj;
    private volatile String zzk;
    private final Context zzl;
    private final Looper zzm;
    private final GmsClientSupervisor zzn;
    private final GoogleApiAvailabilityLight zzo;
    private final Object zzp;
    /* access modifiers changed from: private */
    public final Object zzq;
    /* access modifiers changed from: private */
    public IGmsServiceBroker zzr;
    private IInterface zzs;
    /* access modifiers changed from: private */
    public final ArrayList zzt;
    private zze zzu;
    private int zzv;
    /* access modifiers changed from: private */
    public final BaseConnectionCallbacks zzw;
    /* access modifiers changed from: private */
    public final BaseOnConnectionFailedListener zzx;
    private final int zzy;
    private final String zzz;

    @KeepForSdk
    public interface BaseConnectionCallbacks {
        @KeepForSdk
        public static final int CAUSE_DEAD_OBJECT_EXCEPTION = 3;
        @KeepForSdk
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        @KeepForSdk
        void onConnected(Bundle bundle);

        @KeepForSdk
        void onConnectionSuspended(int i);
    }

    @KeepForSdk
    public interface BaseOnConnectionFailedListener {
        @KeepForSdk
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    @KeepForSdk
    public interface ConnectionProgressReportCallbacks {
        @KeepForSdk
        void onReportServiceBinding(ConnectionResult connectionResult);
    }

    protected class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        @KeepForSdk
        public LegacyClientCallbackAdapter() {
        }

        public final void onReportServiceBinding(ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                baseGmsClient.getRemoteService((IAccountAccessor) null, baseGmsClient.getScopes());
            } else if (BaseGmsClient.this.zzx != null) {
                BaseGmsClient.this.zzx.onConnectionFailed(connectionResult);
            }
        }
    }

    @KeepForSdk
    public interface SignOutCallbacks {
        @KeepForSdk
        void onSignOutComplete();
    }

    @KeepForSdk
    protected BaseGmsClient(Context context, Handler handler, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener) {
        this.zzk = null;
        this.zzp = new Object();
        this.zzq = new Object();
        this.zzt = new ArrayList();
        this.zzv = 1;
        this.zzC = null;
        this.zzD = false;
        this.zzE = null;
        this.zzd = new AtomicInteger(0);
        Preconditions.checkNotNull(context, "Context must not be null");
        this.zzl = context;
        Preconditions.checkNotNull(handler, "Handler must not be null");
        this.zzb = handler;
        this.zzm = handler.getLooper();
        Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzn = gmsClientSupervisor;
        Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.zzo = googleApiAvailabilityLight;
        this.zzy = i;
        this.zzw = baseConnectionCallbacks;
        this.zzx = baseOnConnectionFailedListener;
        this.zzz = null;
    }

    static /* bridge */ /* synthetic */ void zzj(BaseGmsClient baseGmsClient, zzk zzk2) {
        RootTelemetryConfiguration rootTelemetryConfiguration;
        baseGmsClient.zzE = zzk2;
        if (baseGmsClient.usesClientTelemetry()) {
            ConnectionTelemetryConfiguration connectionTelemetryConfiguration = zzk2.zzd;
            RootTelemetryConfigManager instance = RootTelemetryConfigManager.getInstance();
            if (connectionTelemetryConfiguration == null) {
                rootTelemetryConfiguration = null;
            } else {
                rootTelemetryConfiguration = connectionTelemetryConfiguration.zza();
            }
            instance.zza(rootTelemetryConfiguration);
        }
    }

    static /* bridge */ /* synthetic */ void zzk(BaseGmsClient baseGmsClient, int i) {
        int i2;
        int i3;
        synchronized (baseGmsClient.zzp) {
            i2 = baseGmsClient.zzv;
        }
        if (i2 == 3) {
            baseGmsClient.zzD = true;
            i3 = 5;
        } else {
            i3 = 4;
        }
        Handler handler = baseGmsClient.zzb;
        handler.sendMessage(handler.obtainMessage(i3, baseGmsClient.zzd.get(), 16));
    }

    static /* bridge */ /* synthetic */ boolean zzn(BaseGmsClient baseGmsClient, int i, int i2, IInterface iInterface) {
        synchronized (baseGmsClient.zzp) {
            try {
                if (baseGmsClient.zzv != i) {
                    return false;
                }
                baseGmsClient.zzp(i2, iInterface);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    static /* bridge */ /* synthetic */ boolean zzo(BaseGmsClient baseGmsClient) {
        if (baseGmsClient.zzD || TextUtils.isEmpty(baseGmsClient.getServiceDescriptor()) || TextUtils.isEmpty(baseGmsClient.getLocalStartServiceAction())) {
            return false;
        }
        try {
            Class.forName(baseGmsClient.getServiceDescriptor());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public final void zzp(int i, IInterface iInterface) {
        zzv zzv2;
        int i2;
        zzv zzv3;
        boolean z = false;
        if ((i == 4) == (iInterface != null)) {
            z = true;
        }
        Preconditions.checkArgument(z);
        synchronized (this.zzp) {
            try {
                this.zzv = i;
                this.zzs = iInterface;
                Bundle bundle = null;
                if (i == 1) {
                    zze zze2 = this.zzu;
                    if (zze2 != null) {
                        GmsClientSupervisor gmsClientSupervisor = this.zzn;
                        String zzb2 = this.zza.zzb();
                        Preconditions.checkNotNull(zzb2);
                        gmsClientSupervisor.zzc(zzb2, this.zza.zza(), 4225, zze2, zze(), this.zza.zzc());
                        this.zzu = null;
                    }
                } else if (i == 2 || i == 3) {
                    zze zze3 = this.zzu;
                    if (!(zze3 == null || (zzv3 = this.zza) == null)) {
                        Log.e("GmsClient", "Calling connect() while still connected, missing disconnect() for " + zzv3.zzb() + " on " + zzv3.zza());
                        GmsClientSupervisor gmsClientSupervisor2 = this.zzn;
                        String zzb3 = this.zza.zzb();
                        Preconditions.checkNotNull(zzb3);
                        gmsClientSupervisor2.zzc(zzb3, this.zza.zza(), 4225, zze3, zze(), this.zza.zzc());
                        this.zzd.incrementAndGet();
                    }
                    zze zze4 = new zze(this, this.zzd.get());
                    this.zzu = zze4;
                    if (this.zzv != 3 || getLocalStartServiceAction() == null) {
                        zzv2 = new zzv(getStartServicePackage(), getStartServiceAction(), false, 4225, getUseDynamicLookup());
                    } else {
                        zzv2 = new zzv(getContext().getPackageName(), getLocalStartServiceAction(), true, 4225, false);
                    }
                    this.zza = zzv2;
                    if (zzv2.zzc()) {
                        if (getMinApkVersion() < 17895000) {
                            throw new IllegalStateException("Internal Error, the minimum apk version of this BaseGmsClient is too low to support dynamic lookup. Start service action: ".concat(String.valueOf(this.zza.zzb())));
                        }
                    }
                    GmsClientSupervisor gmsClientSupervisor3 = this.zzn;
                    String zzb4 = this.zza.zzb();
                    Preconditions.checkNotNull(zzb4);
                    ConnectionResult zza2 = gmsClientSupervisor3.zza(new zzo(zzb4, this.zza.zza(), 4225, this.zza.zzc()), zze4, zze(), getBindServiceExecutor());
                    if (!zza2.isSuccess()) {
                        Log.w("GmsClient", "unable to connect to service: " + this.zza.zzb() + " on " + this.zza.zza());
                        if (zza2.getErrorCode() == -1) {
                            i2 = 16;
                        } else {
                            i2 = zza2.getErrorCode();
                        }
                        if (zza2.getResolution() != null) {
                            bundle = new Bundle();
                            bundle.putParcelable(KEY_PENDING_INTENT, zza2.getResolution());
                        }
                        zzl(i2, bundle, this.zzd.get());
                    }
                } else if (i == 4) {
                    Preconditions.checkNotNull(iInterface);
                    onConnectedLocked(iInterface);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public void checkAvailabilityAndConnect() {
        int isGooglePlayServicesAvailable = this.zzo.isGooglePlayServicesAvailable(this.zzl, getMinApkVersion());
        if (isGooglePlayServicesAvailable != 0) {
            zzp(1, (IInterface) null);
            triggerNotAvailable(new LegacyClientCallbackAdapter(), isGooglePlayServicesAvailable, (PendingIntent) null);
            return;
        }
        connect(new LegacyClientCallbackAdapter());
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    @KeepForSdk
    public void connect(ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        this.zzc = connectionProgressReportCallbacks;
        zzp(2, (IInterface) null);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract T createServiceInterface(IBinder iBinder);

    @KeepForSdk
    public void disconnect() {
        this.zzd.incrementAndGet();
        synchronized (this.zzt) {
            try {
                int size = this.zzt.size();
                for (int i = 0; i < size; i++) {
                    ((zzc) this.zzt.get(i)).zzf();
                }
                this.zzt.clear();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        synchronized (this.zzq) {
            this.zzr = null;
        }
        zzp(1, (IInterface) null);
    }

    @KeepForSdk
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i;
        IInterface iInterface;
        IGmsServiceBroker iGmsServiceBroker;
        synchronized (this.zzp) {
            i = this.zzv;
            iInterface = this.zzs;
        }
        synchronized (this.zzq) {
            iGmsServiceBroker = this.zzr;
        }
        printWriter.append(str).append("mConnectState=");
        if (i == 1) {
            printWriter.print("DISCONNECTED");
        } else if (i == 2) {
            printWriter.print("REMOTE_CONNECTING");
        } else if (i == 3) {
            printWriter.print("LOCAL_CONNECTING");
        } else if (i == 4) {
            printWriter.print("CONNECTED");
        } else if (i != 5) {
            printWriter.print("UNKNOWN");
        } else {
            printWriter.print("DISCONNECTING");
        }
        printWriter.append(" mService=");
        if (iInterface == null) {
            printWriter.append("null");
        } else {
            printWriter.append(getServiceDescriptor()).append("@").append(Integer.toHexString(System.identityHashCode(iInterface.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (iGmsServiceBroker == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(iGmsServiceBroker.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzh > 0) {
            PrintWriter append = printWriter.append(str).append("lastConnectedTime=");
            long j = this.zzh;
            String format = simpleDateFormat.format(new Date(j));
            append.println(j + " " + format);
        }
        if (this.zzg > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            int i2 = this.zzf;
            if (i2 == 1) {
                printWriter.append("CAUSE_SERVICE_DISCONNECTED");
            } else if (i2 == 2) {
                printWriter.append("CAUSE_NETWORK_LOST");
            } else if (i2 != 3) {
                printWriter.append(String.valueOf(i2));
            } else {
                printWriter.append("CAUSE_DEAD_OBJECT_EXCEPTION");
            }
            PrintWriter append2 = printWriter.append(" lastSuspendedTime=");
            long j2 = this.zzg;
            String format2 = simpleDateFormat.format(new Date(j2));
            append2.println(j2 + " " + format2);
        }
        if (this.zzj > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzi));
            PrintWriter append3 = printWriter.append(" lastFailedTime=");
            long j3 = this.zzj;
            String format3 = simpleDateFormat.format(new Date(j3));
            append3.println(j3 + " " + format3);
        }
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public boolean enableLocalFallback() {
        return false;
    }

    @KeepForSdk
    public Account getAccount() {
        return null;
    }

    @KeepForSdk
    public Feature[] getApiFeatures() {
        return zze;
    }

    @KeepForSdk
    public AttributionSourceWrapper getAttributionSourceWrapper() {
        return this.zzB;
    }

    @KeepForSdk
    public final Feature[] getAvailableFeatures() {
        zzk zzk2 = this.zzE;
        if (zzk2 == null) {
            return null;
        }
        return zzk2.zzb;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public Executor getBindServiceExecutor() {
        return null;
    }

    @KeepForSdk
    public Bundle getConnectionHint() {
        return null;
    }

    @KeepForSdk
    public final Context getContext() {
        return this.zzl;
    }

    @KeepForSdk
    public String getEndpointPackageName() {
        zzv zzv2;
        if (isConnected() && (zzv2 = this.zza) != null) {
            return zzv2.zza();
        }
        throw new RuntimeException("Failed to connect when checking package");
    }

    @KeepForSdk
    public int getGCoreServiceId() {
        return this.zzy;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    @KeepForSdk
    public String getLastDisconnectMessage() {
        return this.zzk;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public String getLocalStartServiceAction() {
        return null;
    }

    @KeepForSdk
    public final Looper getLooper() {
        return this.zzm;
    }

    @KeepForSdk
    public int getMinApkVersion() {
        return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    @KeepForSdk
    public void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set) {
        String m;
        Set<Scope> set2 = set;
        Bundle getServiceRequestExtraArgs = getGetServiceRequestExtraArgs();
        if (Build.VERSION.SDK_INT < 31) {
            m = this.zzA;
        } else if (this.zzB == null) {
            m = this.zzA;
        } else {
            AttributionSource attributionSource = this.zzB.getAttributionSource();
            m = attributionSource == null ? this.zzA : attributionSource.getAttributionTag() == null ? this.zzA : attributionSource.getAttributionTag();
        }
        String str = m;
        int i = this.zzy;
        int i2 = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        Scope[] scopeArr = GetServiceRequest.zza;
        Bundle bundle = new Bundle();
        Feature[] featureArr = GetServiceRequest.zzb;
        GetServiceRequest getServiceRequest = r3;
        GetServiceRequest getServiceRequest2 = new GetServiceRequest(6, i, i2, (String) null, (IBinder) null, scopeArr, bundle, (Account) null, featureArr, featureArr, true, 0, false, str);
        GetServiceRequest getServiceRequest3 = getServiceRequest;
        getServiceRequest3.zzf = this.zzl.getPackageName();
        getServiceRequest3.zzi = getServiceRequestExtraArgs;
        if (set2 != null) {
            getServiceRequest3.zzh = (Scope[]) set2.toArray(new Scope[0]);
        }
        if (requiresSignIn()) {
            Account account = getAccount();
            if (account == null) {
                account = new Account("<<default account>>", "com.google");
            }
            getServiceRequest3.zzj = account;
            if (iAccountAccessor != null) {
                getServiceRequest3.zzg = iAccountAccessor.asBinder();
            }
        } else if (requiresAccount()) {
            getServiceRequest3.zzj = getAccount();
        }
        getServiceRequest3.zzk = zze;
        getServiceRequest3.zzl = getApiFeatures();
        if (usesClientTelemetry()) {
            getServiceRequest3.zzo = true;
        }
        try {
            synchronized (this.zzq) {
                IGmsServiceBroker iGmsServiceBroker = this.zzr;
                if (iGmsServiceBroker != null) {
                    iGmsServiceBroker.getService(new zzd(this, this.zzd.get()), getServiceRequest3);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            triggerConnectionSuspended(3);
        } catch (SecurityException e2) {
            throw e2;
        } catch (RemoteException | RuntimeException e3) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e3);
            onPostInitHandler(8, (IBinder) null, (Bundle) null, this.zzd.get());
        } catch (Throwable th) {
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public Set<Scope> getScopes() {
        return Collections.emptySet();
    }

    @KeepForSdk
    public final T getService() throws DeadObjectException {
        T t;
        synchronized (this.zzp) {
            try {
                if (this.zzv != 5) {
                    checkConnected();
                    T t2 = this.zzs;
                    Preconditions.checkNotNull(t2, "Client is connected but service is null");
                    t = (IInterface) t2;
                } else {
                    throw new DeadObjectException();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return t;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract String getServiceDescriptor();

    @KeepForSdk
    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract String getStartServiceAction();

    /* access modifiers changed from: protected */
    @KeepForSdk
    public String getStartServicePackage() {
        return "com.google.android.gms";
    }

    @KeepForSdk
    public ConnectionTelemetryConfiguration getTelemetryConfiguration() {
        zzk zzk2 = this.zzE;
        if (zzk2 == null) {
            return null;
        }
        return zzk2.zzd;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public boolean getUseDynamicLookup() {
        return getMinApkVersion() >= 211700000;
    }

    @KeepForSdk
    public boolean hasConnectionInfo() {
        return this.zzE != null;
    }

    @KeepForSdk
    public boolean isConnected() {
        boolean z;
        synchronized (this.zzp) {
            z = this.zzv == 4;
        }
        return z;
    }

    @KeepForSdk
    public boolean isConnecting() {
        boolean z;
        synchronized (this.zzp) {
            int i = this.zzv;
            z = true;
            if (i != 2) {
                if (i != 3) {
                    z = false;
                }
            }
        }
        return z;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void onConnectedLocked(T t) {
        this.zzh = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzi = connectionResult.getErrorCode();
        this.zzj = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void onConnectionSuspended(int i) {
        this.zzf = i;
        this.zzg = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.zzb.sendMessage(this.zzb.obtainMessage(1, i2, -1, new zzf(this, i, iBinder, bundle)));
    }

    @KeepForSdk
    public void onUserSignOut(SignOutCallbacks signOutCallbacks) {
        signOutCallbacks.onSignOutComplete();
    }

    @KeepForSdk
    public boolean providesSignIn() {
        return false;
    }

    @KeepForSdk
    public boolean requiresAccount() {
        return false;
    }

    @KeepForSdk
    public boolean requiresGooglePlayServices() {
        return true;
    }

    @KeepForSdk
    public boolean requiresSignIn() {
        return false;
    }

    @KeepForSdk
    public void setAttributionSourceWrapper(AttributionSourceWrapper attributionSourceWrapper) {
        this.zzB = attributionSourceWrapper;
    }

    @KeepForSdk
    public void setAttributionTag(String str) {
        this.zzA = str;
    }

    @KeepForSdk
    public void triggerConnectionSuspended(int i) {
        this.zzb.sendMessage(this.zzb.obtainMessage(6, this.zzd.get(), i));
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void triggerNotAvailable(ConnectionProgressReportCallbacks connectionProgressReportCallbacks, int i, PendingIntent pendingIntent) {
        Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        this.zzc = connectionProgressReportCallbacks;
        this.zzb.sendMessage(this.zzb.obtainMessage(3, this.zzd.get(), i, pendingIntent));
    }

    @KeepForSdk
    public boolean usesClientTelemetry() {
        return false;
    }

    /* access modifiers changed from: protected */
    public final String zze() {
        String str = this.zzz;
        return str == null ? this.zzl.getClass().getName() : str;
    }

    /* access modifiers changed from: protected */
    public final void zzl(int i, Bundle bundle, int i2) {
        this.zzb.sendMessage(this.zzb.obtainMessage(7, i2, -1, new zzg(this, i, bundle)));
    }

    @KeepForSdk
    public IBinder getServiceBrokerBinder() {
        synchronized (this.zzq) {
            try {
                IGmsServiceBroker iGmsServiceBroker = this.zzr;
                if (iGmsServiceBroker == null) {
                    return null;
                }
                IBinder asBinder = iGmsServiceBroker.asBinder();
                return asBinder;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BaseGmsClient(android.content.Context r10, android.os.Looper r11, int r12, com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks r13, com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener r14, java.lang.String r15) {
        /*
            r9 = this;
            com.google.android.gms.common.internal.GmsClientSupervisor r3 = com.google.android.gms.common.internal.GmsClientSupervisor.getInstance(r10)
            com.google.android.gms.common.GoogleApiAvailabilityLight r4 = com.google.android.gms.common.GoogleApiAvailabilityLight.getInstance()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r14)
            r0 = r9
            r1 = r10
            r2 = r11
            r5 = r12
            r6 = r13
            r7 = r14
            r8 = r15
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.BaseGmsClient.<init>(android.content.Context, android.os.Looper, int, com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks, com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener, java.lang.String):void");
    }

    @KeepForSdk
    public void disconnect(String str) {
        this.zzk = str;
        disconnect();
    }

    @KeepForSdk
    protected BaseGmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this.zzk = null;
        this.zzp = new Object();
        this.zzq = new Object();
        this.zzt = new ArrayList();
        this.zzv = 1;
        this.zzC = null;
        this.zzD = false;
        this.zzE = null;
        this.zzd = new AtomicInteger(0);
        Preconditions.checkNotNull(context, "Context must not be null");
        this.zzl = context;
        Preconditions.checkNotNull(looper, "Looper must not be null");
        this.zzm = looper;
        Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzn = gmsClientSupervisor;
        Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.zzo = googleApiAvailabilityLight;
        this.zzb = new zzb(this, looper);
        this.zzy = i;
        this.zzw = baseConnectionCallbacks;
        this.zzx = baseOnConnectionFailedListener;
        this.zzz = str;
    }
}
