package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.facebook.FacebookSdk;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class AttributionIdentifiers {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String TAG = AttributionIdentifiers.class.getCanonicalName();
    public static AttributionIdentifiers cachedIdentifiers;
    /* access modifiers changed from: private */
    public String androidAdvertiserIdValue;
    /* access modifiers changed from: private */
    public String androidInstallerPackage;
    /* access modifiers changed from: private */
    public String attributionId;
    /* access modifiers changed from: private */
    public long fetchTime;
    /* access modifiers changed from: private */
    public boolean isTrackingLimited;

    public final String getAttributionId() {
        return this.attributionId;
    }

    public final String getAndroidInstallerPackage() {
        return this.androidInstallerPackage;
    }

    public final boolean isTrackingLimited() {
        return this.isTrackingLimited;
    }

    public final String getAndroidAdvertiserId() {
        if (!FacebookSdk.isInitialized() || !FacebookSdk.getAdvertiserIDCollectionEnabled()) {
            return null;
        }
        return this.androidAdvertiserIdValue;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final AttributionIdentifiers getAndroidId(Context context) {
            AttributionIdentifiers androidIdViaReflection = getAndroidIdViaReflection(context);
            if (androidIdViaReflection != null) {
                return androidIdViaReflection;
            }
            AttributionIdentifiers androidIdViaService = getAndroidIdViaService(context);
            return androidIdViaService == null ? new AttributionIdentifiers() : androidIdViaService;
        }

        private final AttributionIdentifiers getAndroidIdViaReflection(Context context) {
            Method methodQuietly;
            Object invokeMethodQuietly;
            try {
                if (!isGooglePlayServicesAvailable(context) || (methodQuietly = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class)) == null || (invokeMethodQuietly = Utility.invokeMethodQuietly((Object) null, methodQuietly, context)) == null) {
                    return null;
                }
                boolean z = false;
                Method methodQuietly2 = Utility.getMethodQuietly((Class) invokeMethodQuietly.getClass(), "getId", new Class[0]);
                Method methodQuietly3 = Utility.getMethodQuietly((Class) invokeMethodQuietly.getClass(), "isLimitAdTrackingEnabled", new Class[0]);
                if (methodQuietly2 != null) {
                    if (methodQuietly3 != null) {
                        AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                        attributionIdentifiers.androidAdvertiserIdValue = (String) Utility.invokeMethodQuietly(invokeMethodQuietly, methodQuietly2, new Object[0]);
                        Boolean bool = (Boolean) Utility.invokeMethodQuietly(invokeMethodQuietly, methodQuietly3, new Object[0]);
                        if (bool != null) {
                            z = bool.booleanValue();
                        }
                        attributionIdentifiers.isTrackingLimited = z;
                        return attributionIdentifiers;
                    }
                }
                return null;
            } catch (Exception e) {
                Utility.logd("android_id", e);
                return null;
            }
        }

        public final boolean isTrackingLimited(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            AttributionIdentifiers attributionIdentifiers = getAttributionIdentifiers(context);
            return attributionIdentifiers != null && attributionIdentifiers.isTrackingLimited();
        }

        private final boolean isGooglePlayServicesAvailable(Context context) {
            Method methodQuietly = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
            if (methodQuietly == null) {
                return false;
            }
            Object invokeMethodQuietly = Utility.invokeMethodQuietly((Object) null, methodQuietly, context);
            if (!(invokeMethodQuietly instanceof Integer) || !Intrinsics.areEqual(invokeMethodQuietly, (Object) 0)) {
                return false;
            }
            return true;
        }

        private final AttributionIdentifiers getAndroidIdViaService(Context context) {
            if (!isGooglePlayServicesAvailable(context)) {
                return null;
            }
            GoogleAdServiceConnection googleAdServiceConnection = new GoogleAdServiceConnection();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            try {
                if (context.bindService(intent, googleAdServiceConnection, 1)) {
                    try {
                        GoogleAdInfo googleAdInfo = new GoogleAdInfo(googleAdServiceConnection.getBinder());
                        AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                        attributionIdentifiers.androidAdvertiserIdValue = googleAdInfo.getAdvertiserId();
                        attributionIdentifiers.isTrackingLimited = googleAdInfo.isTrackingLimited();
                        return attributionIdentifiers;
                    } catch (Exception e) {
                        Utility.logd("android_id", e);
                    } finally {
                        context.unbindService(googleAdServiceConnection);
                    }
                }
            } catch (SecurityException unused) {
            }
            return null;
        }

        /* JADX WARNING: Removed duplicated region for block: B:26:0x0087 A[Catch:{ Exception -> 0x0036, all -> 0x0033 }] */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x008c A[Catch:{ Exception -> 0x0036, all -> 0x0033 }] */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0091 A[Catch:{ Exception -> 0x0036, all -> 0x0033 }] */
        /* JADX WARNING: Removed duplicated region for block: B:59:0x0112  */
        /* JADX WARNING: Removed duplicated region for block: B:64:0x011a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.facebook.internal.AttributionIdentifiers getAttributionIdentifiers(android.content.Context r13) {
            /*
                r12 = this;
                java.lang.String r0 = "limit_tracking"
                java.lang.String r1 = "androidid"
                java.lang.String r2 = "aid"
                java.lang.String r3 = "context"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r3)
                com.facebook.internal.AttributionIdentifiers r3 = r12.getAndroidId(r13)
                r4 = 0
                android.os.Looper r5 = android.os.Looper.myLooper()     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                android.os.Looper r6 = android.os.Looper.getMainLooper()     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                if (r5 != 0) goto L_0x00f0
                com.facebook.internal.AttributionIdentifiers r5 = com.facebook.internal.AttributionIdentifiers.cachedIdentifiers     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                if (r5 == 0) goto L_0x003a
                long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                long r8 = r5.fetchTime     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                long r6 = r6 - r8
                r8 = 3600000(0x36ee80, double:1.7786363E-317)
                int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r6 >= 0) goto L_0x003a
                return r5
            L_0x0033:
                r13 = move-exception
                goto L_0x0118
            L_0x0036:
                r13 = move-exception
                r0 = r4
                goto L_0x00f8
            L_0x003a:
                java.lang.String[] r7 = new java.lang.String[]{r2, r1, r0}     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                android.content.pm.PackageManager r5 = r13.getPackageManager()     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                java.lang.String r6 = "com.facebook.katana.provider.AttributionIdProvider"
                r8 = 0
                android.content.pm.ProviderInfo r5 = r5.resolveContentProvider(r6, r8)     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                android.content.pm.PackageManager r6 = r13.getPackageManager()     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                java.lang.String r9 = "com.facebook.wakizashi.provider.AttributionIdProvider"
                android.content.pm.ProviderInfo r6 = r6.resolveContentProvider(r9, r8)     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                if (r5 == 0) goto L_0x006a
                java.lang.String r5 = r5.packageName     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                java.lang.String r8 = "contentProviderInfo.packageName"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r8)     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                boolean r5 = com.facebook.internal.FacebookSignatureValidator.validateSignature(r13, r5)     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                if (r5 == 0) goto L_0x006a
                java.lang.String r5 = "content://com.facebook.katana.provider.AttributionIdProvider"
                android.net.Uri r5 = android.net.Uri.parse(r5)     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
            L_0x0068:
                r6 = r5
                goto L_0x0081
            L_0x006a:
                if (r6 == 0) goto L_0x0080
                java.lang.String r5 = r6.packageName     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                java.lang.String r6 = "wakizashiProviderInfo.packageName"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                boolean r5 = com.facebook.internal.FacebookSignatureValidator.validateSignature(r13, r5)     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                if (r5 == 0) goto L_0x0080
                java.lang.String r5 = "content://com.facebook.wakizashi.provider.AttributionIdProvider"
                android.net.Uri r5 = android.net.Uri.parse(r5)     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                goto L_0x0068
            L_0x0080:
                r6 = r4
            L_0x0081:
                java.lang.String r5 = r12.getInstallerPackageName(r13)     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                if (r5 == 0) goto L_0x008a
                r3.androidInstallerPackage = r5     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
            L_0x008a:
                if (r6 != 0) goto L_0x0091
                com.facebook.internal.AttributionIdentifiers r13 = r12.cacheAndReturnIdentifiers(r3)     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                return r13
            L_0x0091:
                android.content.ContentResolver r5 = r13.getContentResolver()     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                r9 = 0
                r10 = 0
                r8 = 0
                android.database.Cursor r13 = r5.query(r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                if (r13 == 0) goto L_0x00e6
                boolean r5 = r13.moveToFirst()     // Catch:{ Exception -> 0x00d9, all -> 0x00d5 }
                if (r5 != 0) goto L_0x00a5
                goto L_0x00e6
            L_0x00a5:
                int r2 = r13.getColumnIndex(r2)     // Catch:{ Exception -> 0x00d9, all -> 0x00d5 }
                int r1 = r13.getColumnIndex(r1)     // Catch:{ Exception -> 0x00d9, all -> 0x00d5 }
                int r0 = r13.getColumnIndex(r0)     // Catch:{ Exception -> 0x00d9, all -> 0x00d5 }
                java.lang.String r2 = r13.getString(r2)     // Catch:{ Exception -> 0x00d9, all -> 0x00d5 }
                r3.attributionId = r2     // Catch:{ Exception -> 0x00d9, all -> 0x00d5 }
                if (r1 <= 0) goto L_0x00de
                if (r0 <= 0) goto L_0x00de
                java.lang.String r2 = r3.getAndroidAdvertiserId()     // Catch:{ Exception -> 0x00d9, all -> 0x00d5 }
                if (r2 != 0) goto L_0x00de
                java.lang.String r1 = r13.getString(r1)     // Catch:{ Exception -> 0x00d9, all -> 0x00d5 }
                r3.androidAdvertiserIdValue = r1     // Catch:{ Exception -> 0x00d9, all -> 0x00d5 }
                java.lang.String r0 = r13.getString(r0)     // Catch:{ Exception -> 0x00d9, all -> 0x00d5 }
                boolean r0 = java.lang.Boolean.parseBoolean(r0)     // Catch:{ Exception -> 0x00d9, all -> 0x00d5 }
                r3.isTrackingLimited = r0     // Catch:{ Exception -> 0x00d9, all -> 0x00d5 }
                goto L_0x00de
            L_0x00d5:
                r0 = move-exception
                r4 = r13
                r13 = r0
                goto L_0x0118
            L_0x00d9:
                r0 = move-exception
                r11 = r0
                r0 = r13
                r13 = r11
                goto L_0x00f8
            L_0x00de:
                r13.close()
                com.facebook.internal.AttributionIdentifiers r13 = r12.cacheAndReturnIdentifiers(r3)
                return r13
            L_0x00e6:
                com.facebook.internal.AttributionIdentifiers r0 = r12.cacheAndReturnIdentifiers(r3)     // Catch:{ Exception -> 0x00d9, all -> 0x00d5 }
                if (r13 == 0) goto L_0x00ef
                r13.close()
            L_0x00ef:
                return r0
            L_0x00f0:
                com.facebook.FacebookException r13 = new com.facebook.FacebookException     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                java.lang.String r0 = "getAttributionIdentifiers cannot be called on the main thread."
                r13.<init>((java.lang.String) r0)     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
                throw r13     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
            L_0x00f8:
                java.lang.String r1 = com.facebook.internal.AttributionIdentifiers.TAG     // Catch:{ all -> 0x0116 }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0116 }
                r2.<init>()     // Catch:{ all -> 0x0116 }
                java.lang.String r3 = "Caught unexpected exception in getAttributionId(): "
                r2.append(r3)     // Catch:{ all -> 0x0116 }
                r2.append(r13)     // Catch:{ all -> 0x0116 }
                java.lang.String r13 = r2.toString()     // Catch:{ all -> 0x0116 }
                com.facebook.internal.Utility.logd((java.lang.String) r1, (java.lang.String) r13)     // Catch:{ all -> 0x0116 }
                if (r0 == 0) goto L_0x0115
                r0.close()
            L_0x0115:
                return r4
            L_0x0116:
                r13 = move-exception
                r4 = r0
            L_0x0118:
                if (r4 == 0) goto L_0x011d
                r4.close()
            L_0x011d:
                throw r13
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.AttributionIdentifiers.Companion.getAttributionIdentifiers(android.content.Context):com.facebook.internal.AttributionIdentifiers");
        }

        private final AttributionIdentifiers cacheAndReturnIdentifiers(AttributionIdentifiers attributionIdentifiers) {
            attributionIdentifiers.fetchTime = System.currentTimeMillis();
            AttributionIdentifiers.cachedIdentifiers = attributionIdentifiers;
            return attributionIdentifiers;
        }

        private final String getInstallerPackageName(Context context) {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.getInstallerPackageName(context.getPackageName());
            }
            return null;
        }
    }

    private static final class GoogleAdServiceConnection implements ServiceConnection {
        private final AtomicBoolean consumed = new AtomicBoolean(false);
        private final BlockingQueue queue = new LinkedBlockingDeque();

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder != null) {
                try {
                    this.queue.put(iBinder);
                } catch (InterruptedException unused) {
                }
            }
        }

        public final IBinder getBinder() {
            if (!this.consumed.compareAndSet(true, true)) {
                Object take = this.queue.take();
                Intrinsics.checkNotNullExpressionValue(take, "queue.take()");
                return (IBinder) take;
            }
            throw new IllegalStateException("Binder already consumed");
        }
    }

    private static final class GoogleAdInfo implements IInterface {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final IBinder binder;

        public GoogleAdInfo(IBinder iBinder) {
            Intrinsics.checkNotNullParameter(iBinder, "binder");
            this.binder = iBinder;
        }

        public IBinder asBinder() {
            return this.binder;
        }

        public final String getAdvertiserId() {
            Parcel obtain = Parcel.obtain();
            Intrinsics.checkNotNullExpressionValue(obtain, "obtain()");
            Parcel obtain2 = Parcel.obtain();
            Intrinsics.checkNotNullExpressionValue(obtain2, "obtain()");
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.binder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public final boolean isTrackingLimited() {
            Parcel obtain = Parcel.obtain();
            Intrinsics.checkNotNullExpressionValue(obtain, "obtain()");
            Parcel obtain2 = Parcel.obtain();
            Intrinsics.checkNotNullExpressionValue(obtain2, "obtain()");
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                boolean z = true;
                obtain.writeInt(1);
                this.binder.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z = false;
                }
                return z;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }
}
