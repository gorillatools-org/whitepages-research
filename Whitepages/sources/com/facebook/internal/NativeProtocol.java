package com.facebook.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookSdk;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.facebook.login.DefaultAudience;
import com.facebook.login.LoginTargetApp;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class NativeProtocol {
    public static final NativeProtocol INSTANCE;
    private static final Integer[] KNOWN_PROTOCOL_VERSIONS = {20210906, 20171115, 20170417, 20170411, 20170213, 20161017, 20160327, 20150702, 20150401, 20141218, 20141107, 20141028, 20141001, 20140701, 20140324, 20140313, 20140204, 20131107, 20131024, 20130618, 20130502, 20121101};
    private static final String TAG = NativeProtocol.class.getName();
    private static final Map actionToAppInfoMap;
    private static final List effectCameraAppInfoList;
    private static final List facebookAppInfoList;
    private static final AtomicBoolean protocolVersionsAsyncUpdating = new AtomicBoolean(false);

    private NativeProtocol() {
    }

    public static final /* synthetic */ TreeSet access$fetchAllAvailableProtocolVersionsForAppInfo(NativeProtocol nativeProtocol, NativeAppInfo nativeAppInfo) {
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return nativeProtocol.fetchAllAvailableProtocolVersionsForAppInfo(nativeAppInfo);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ String access$getTAG$p() {
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return TAG;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    static {
        NativeProtocol nativeProtocol = new NativeProtocol();
        INSTANCE = nativeProtocol;
        facebookAppInfoList = nativeProtocol.buildFacebookAppList();
        effectCameraAppInfoList = nativeProtocol.buildEffectCameraAppInfoList();
        actionToAppInfoMap = nativeProtocol.buildActionToAppInfoMap();
    }

    private final List buildFacebookAppList() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return CollectionsKt.arrayListOf(new KatanaAppInfo(), new WakizashiAppInfo());
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final List buildEffectCameraAppInfoList() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            ArrayList arrayListOf = CollectionsKt.arrayListOf(new EffectTestAppInfo());
            arrayListOf.addAll(buildFacebookAppList());
            return arrayListOf;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final Map buildActionToAppInfoMap() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new MessengerAppInfo());
            List list = facebookAppInfoList;
            hashMap.put("com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG", list);
            hashMap.put("com.facebook.platform.action.request.FEED_DIALOG", list);
            hashMap.put("com.facebook.platform.action.request.LIKE_DIALOG", list);
            hashMap.put("com.facebook.platform.action.request.APPINVITES_DIALOG", list);
            hashMap.put("com.facebook.platform.action.request.MESSAGE_DIALOG", arrayList);
            hashMap.put("com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG", arrayList);
            hashMap.put("com.facebook.platform.action.request.CAMERA_EFFECT", effectCameraAppInfoList);
            hashMap.put("com.facebook.platform.action.request.SHARE_STORY", list);
            return hashMap;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public static final Intent validateActivityIntent(Context context, Intent intent, NativeAppInfo nativeAppInfo) {
        ResolveInfo resolveActivity;
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(context, "context");
            if (intent == null || (resolveActivity = context.getPackageManager().resolveActivity(intent, 0)) == null) {
                return null;
            }
            String str = resolveActivity.activityInfo.packageName;
            Intrinsics.checkNotNullExpressionValue(str, "resolveInfo.activityInfo.packageName");
            if (!FacebookSignatureValidator.validateSignature(context, str)) {
                return null;
            }
            return intent;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final Intent validateServiceIntent(Context context, Intent intent, NativeAppInfo nativeAppInfo) {
        ResolveInfo resolveService;
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(context, "context");
            if (intent == null || (resolveService = context.getPackageManager().resolveService(intent, 0)) == null) {
                return null;
            }
            String str = resolveService.serviceInfo.packageName;
            Intrinsics.checkNotNullExpressionValue(str, "resolveInfo.serviceInfo.packageName");
            if (!FacebookSignatureValidator.validateSignature(context, str)) {
                return null;
            }
            return intent;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final Intent createInstagramIntent(Context context, String str, Collection collection, String str2, boolean z, boolean z2, DefaultAudience defaultAudience, String str3, String str4, String str5, boolean z3, boolean z4, boolean z5) {
        Context context2 = context;
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(context2, "context");
            Intrinsics.checkNotNullParameter(str, "applicationId");
            Intrinsics.checkNotNullParameter(collection, "permissions");
            Intrinsics.checkNotNullParameter(str2, "e2e");
            Intrinsics.checkNotNullParameter(defaultAudience, "defaultAudience");
            Intrinsics.checkNotNullParameter(str3, "clientState");
            Intrinsics.checkNotNullParameter(str4, "authType");
            InstagramAppInfo instagramAppInfo = new InstagramAppInfo();
            return validateActivityIntent(context2, INSTANCE.createNativeAppIntent(instagramAppInfo, str, collection, str2, z2, defaultAudience, str3, str4, false, str5, z3, LoginTargetApp.INSTAGRAM, z4, z5, "", (String) null, (String) null), instagramAppInfo);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    private final Intent createNativeAppIntent(NativeAppInfo nativeAppInfo, String str, Collection collection, String str2, boolean z, DefaultAudience defaultAudience, String str3, String str4, boolean z2, String str5, boolean z3, LoginTargetApp loginTargetApp, boolean z4, boolean z5, String str6, String str7, String str8) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            String loginActivity = nativeAppInfo.getLoginActivity();
            if (loginActivity == null) {
                return null;
            }
            String str9 = str;
            Intent putExtra = new Intent().setClassName(nativeAppInfo.getPackage(), loginActivity).putExtra("client_id", str);
            Intrinsics.checkNotNullExpressionValue(putExtra, "Intent()\n            .se…PP_ID_KEY, applicationId)");
            putExtra.putExtra("facebook_sdk_version", FacebookSdk.getSdkVersion());
            if (!Utility.isNullOrEmpty(collection)) {
                putExtra.putExtra("scope", TextUtils.join(",", collection));
            }
            if (!Utility.isNullOrEmpty(str2)) {
                String str10 = str2;
                putExtra.putExtra("e2e", str2);
            }
            String str11 = str3;
            putExtra.putExtra(RemoteConfigConstants.ResponseFieldKey.STATE, str3);
            putExtra.putExtra("response_type", nativeAppInfo.getResponseType());
            putExtra.putExtra("nonce", str6);
            putExtra.putExtra("return_scopes", "true");
            if (z) {
                putExtra.putExtra("default_audience", defaultAudience.getNativeProtocolAudience());
            }
            putExtra.putExtra("legacy_override", FacebookSdk.getGraphApiVersion());
            String str12 = str4;
            putExtra.putExtra("auth_type", str4);
            if (z2) {
                putExtra.putExtra("fail_on_logged_out", true);
            }
            String str13 = str5;
            putExtra.putExtra("messenger_page_id", str5);
            putExtra.putExtra("reset_messenger_state", z3);
            if (z4) {
                putExtra.putExtra("fx_app", loginTargetApp.toString());
            }
            if (z5) {
                putExtra.putExtra("skip_dedupe", true);
            }
            return putExtra;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public static final List createProxyAuthIntents(Context context, String str, Collection collection, String str2, boolean z, boolean z2, DefaultAudience defaultAudience, String str3, String str4, boolean z3, String str5, boolean z4, boolean z5, boolean z6, String str6, String str7, String str8) {
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(str, "applicationId");
            Intrinsics.checkNotNullParameter(collection, "permissions");
            Intrinsics.checkNotNullParameter(str2, "e2e");
            Intrinsics.checkNotNullParameter(defaultAudience, "defaultAudience");
            Intrinsics.checkNotNullParameter(str3, "clientState");
            Intrinsics.checkNotNullParameter(str4, "authType");
            ArrayList arrayList = new ArrayList();
            for (NativeAppInfo createNativeAppIntent : facebookAppInfoList) {
                ArrayList arrayList2 = arrayList;
                Intent createNativeAppIntent2 = INSTANCE.createNativeAppIntent(createNativeAppIntent, str, collection, str2, z2, defaultAudience, str3, str4, z3, str5, z4, LoginTargetApp.FACEBOOK, z5, z6, str6, str7, str8);
                if (createNativeAppIntent2 != null) {
                    arrayList2.add(createNativeAppIntent2);
                }
                String str9 = str;
                Collection collection2 = collection;
                String str10 = str2;
                DefaultAudience defaultAudience2 = defaultAudience;
                String str11 = str3;
                String str12 = str4;
                arrayList = arrayList2;
            }
            return arrayList;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final int getLatestKnownVersion() {
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return 0;
        }
        try {
            return KNOWN_PROTOCOL_VERSIONS[0].intValue();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return 0;
        }
    }

    public static final boolean isVersionCompatibleWithBucketedIntent(int i) {
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            return ArraysKt.contains(KNOWN_PROTOCOL_VERSIONS, Integer.valueOf(i)) && i >= 20140701;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    public static final Intent createProtocolResultIntent(Intent intent, Bundle bundle, FacebookException facebookException) {
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(intent, "requestIntent");
            UUID callIdFromIntent = getCallIdFromIntent(intent);
            if (callIdFromIntent == null) {
                return null;
            }
            Intent intent2 = new Intent();
            intent2.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", getProtocolVersionFromIntent(intent));
            Bundle bundle2 = new Bundle();
            bundle2.putString("action_id", callIdFromIntent.toString());
            if (facebookException != null) {
                bundle2.putBundle("error", createBundleForException(facebookException));
            }
            intent2.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", bundle2);
            if (bundle != null) {
                intent2.putExtra("com.facebook.platform.protocol.RESULT_ARGS", bundle);
            }
            return intent2;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final Intent createPlatformServiceIntent(Context context) {
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(context, "context");
            for (NativeAppInfo nativeAppInfo : facebookAppInfoList) {
                Intent validateServiceIntent = validateServiceIntent(context, new Intent("com.facebook.platform.PLATFORM_SERVICE").setPackage(nativeAppInfo.getPackage()).addCategory("android.intent.category.DEFAULT"), nativeAppInfo);
                if (validateServiceIntent != null) {
                    return validateServiceIntent;
                }
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final int getProtocolVersionFromIntent(Intent intent) {
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return 0;
        }
        try {
            Intrinsics.checkNotNullParameter(intent, "intent");
            return intent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return 0;
        }
    }

    public static final UUID getCallIdFromIntent(Intent intent) {
        String str;
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (CrashShieldHandler.isObjectCrashing(cls) || intent == null) {
            return null;
        }
        try {
            if (isVersionCompatibleWithBucketedIntent(getProtocolVersionFromIntent(intent))) {
                Bundle bundleExtra = intent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
                str = bundleExtra != null ? bundleExtra.getString("action_id") : null;
            } else {
                str = intent.getStringExtra("com.facebook.platform.protocol.CALL_ID");
            }
            if (str == null) {
                return null;
            }
            try {
                return UUID.fromString(str);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final Bundle getMethodArgumentsFromIntent(Intent intent) {
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(intent, "intent");
            if (!isVersionCompatibleWithBucketedIntent(getProtocolVersionFromIntent(intent))) {
                return intent.getExtras();
            }
            return intent.getBundleExtra("com.facebook.platform.protocol.METHOD_ARGS");
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final FacebookException getExceptionFromErrorData(Bundle bundle) {
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (CrashShieldHandler.isObjectCrashing(cls) || bundle == null) {
            return null;
        }
        try {
            String string = bundle.getString("error_type");
            if (string == null) {
                string = bundle.getString("com.facebook.platform.status.ERROR_TYPE");
            }
            String string2 = bundle.getString("error_description");
            if (string2 == null) {
                string2 = bundle.getString("com.facebook.platform.status.ERROR_DESCRIPTION");
            }
            if (string == null || !StringsKt.equals(string, "UserCanceled", true)) {
                return new FacebookException(string2);
            }
            return new FacebookOperationCanceledException(string2);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final Bundle createBundleForException(FacebookException facebookException) {
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (CrashShieldHandler.isObjectCrashing(cls) || facebookException == null) {
            return null;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("error_description", facebookException.toString());
            if (facebookException instanceof FacebookOperationCanceledException) {
                bundle.putString("error_type", "UserCanceled");
            }
            return bundle;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final int getLatestAvailableProtocolVersionForService(int i) {
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return 0;
        }
        try {
            return INSTANCE.getLatestAvailableProtocolVersionForAppInfoList(facebookAppInfoList, new int[]{i}).getProtocolVersion();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return 0;
        }
    }

    private final ProtocolVersionQueryResult getLatestAvailableProtocolVersionForAppInfoList(List list, int[] iArr) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            updateAllAvailableProtocolVersionsAsync();
            if (list == null) {
                return ProtocolVersionQueryResult.Companion.createEmpty();
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                NativeAppInfo nativeAppInfo = (NativeAppInfo) it.next();
                int computeLatestAvailableVersionFromVersionSpec = computeLatestAvailableVersionFromVersionSpec(nativeAppInfo.getAvailableVersions(), getLatestKnownVersion(), iArr);
                if (computeLatestAvailableVersionFromVersionSpec != -1) {
                    return ProtocolVersionQueryResult.Companion.create(nativeAppInfo, computeLatestAvailableVersionFromVersionSpec);
                }
            }
            return ProtocolVersionQueryResult.Companion.createEmpty();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public static final void updateAllAvailableProtocolVersionsAsync() {
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                if (protocolVersionsAsyncUpdating.compareAndSet(false, true)) {
                    FacebookSdk.getExecutor().execute(new NativeProtocol$$ExternalSyntheticLambda0());
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void updateAllAvailableProtocolVersionsAsync$lambda$1() {
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                for (NativeAppInfo fetchAvailableVersions : facebookAppInfoList) {
                    fetchAvailableVersions.fetchAvailableVersions(true);
                }
                protocolVersionsAsyncUpdating.set(false);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0058 */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006d A[LOOP:0: B:28:0x006d->B:31:0x0073, LOOP_START, SYNTHETIC, Splitter:B:28:0x006d] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x008b A[SYNTHETIC, Splitter:B:36:0x008b] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0094 A[Catch:{ all -> 0x008f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.TreeSet fetchAllAvailableProtocolVersionsForAppInfo(com.facebook.internal.NativeProtocol.NativeAppInfo r13) {
        /*
            r12 = this;
            java.lang.String r0 = "version"
            java.lang.String r1 = "Failed to query content resolver."
            boolean r2 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r12)
            r3 = 0
            if (r2 == 0) goto L_0x000c
            return r3
        L_0x000c:
            java.util.TreeSet r2 = new java.util.TreeSet     // Catch:{ all -> 0x008f }
            r2.<init>()     // Catch:{ all -> 0x008f }
            android.content.Context r4 = com.facebook.FacebookSdk.getApplicationContext()     // Catch:{ all -> 0x008f }
            android.content.ContentResolver r5 = r4.getContentResolver()     // Catch:{ all -> 0x008f }
            java.lang.String[] r7 = new java.lang.String[]{r0}     // Catch:{ all -> 0x008f }
            android.net.Uri r6 = r12.buildPlatformProviderVersionURI(r13)     // Catch:{ all -> 0x008f }
            android.content.Context r4 = com.facebook.FacebookSdk.getApplicationContext()     // Catch:{ all -> 0x0044 }
            android.content.pm.PackageManager r4 = r4.getPackageManager()     // Catch:{ all -> 0x0044 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0044 }
            r8.<init>()     // Catch:{ all -> 0x0044 }
            java.lang.String r13 = r13.getPackage()     // Catch:{ all -> 0x0044 }
            r8.append(r13)     // Catch:{ all -> 0x0044 }
            java.lang.String r13 = ".provider.PlatformProvider"
            r8.append(r13)     // Catch:{ all -> 0x0044 }
            java.lang.String r13 = r8.toString()     // Catch:{ all -> 0x0044 }
            r8 = 0
            android.content.pm.ProviderInfo r13 = r4.resolveContentProvider(r13, r8)     // Catch:{ RuntimeException -> 0x0047 }
            goto L_0x004e
        L_0x0044:
            r13 = move-exception
            r0 = r3
            goto L_0x0092
        L_0x0047:
            r13 = move-exception
            java.lang.String r4 = TAG     // Catch:{ all -> 0x0044 }
            android.util.Log.e(r4, r1, r13)     // Catch:{ all -> 0x0044 }
            r13 = r3
        L_0x004e:
            if (r13 == 0) goto L_0x0088
            r9 = 0
            r10 = 0
            r8 = 0
            android.database.Cursor r13 = r5.query(r6, r7, r8, r9, r10)     // Catch:{ NullPointerException -> 0x0065, SecurityException -> 0x005f, IllegalArgumentException -> 0x0058 }
            goto L_0x006b
        L_0x0058:
            java.lang.String r13 = TAG     // Catch:{ all -> 0x0044 }
            android.util.Log.e(r13, r1)     // Catch:{ all -> 0x0044 }
        L_0x005d:
            r13 = r3
            goto L_0x006b
        L_0x005f:
            java.lang.String r13 = TAG     // Catch:{ all -> 0x0044 }
            android.util.Log.e(r13, r1)     // Catch:{ all -> 0x0044 }
            goto L_0x005d
        L_0x0065:
            java.lang.String r13 = TAG     // Catch:{ all -> 0x0044 }
            android.util.Log.e(r13, r1)     // Catch:{ all -> 0x0044 }
            goto L_0x005d
        L_0x006b:
            if (r13 == 0) goto L_0x0089
        L_0x006d:
            boolean r1 = r13.moveToNext()     // Catch:{ all -> 0x0083 }
            if (r1 == 0) goto L_0x0089
            int r1 = r13.getColumnIndex(r0)     // Catch:{ all -> 0x0083 }
            int r1 = r13.getInt(r1)     // Catch:{ all -> 0x0083 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0083 }
            r2.add(r1)     // Catch:{ all -> 0x0083 }
            goto L_0x006d
        L_0x0083:
            r0 = move-exception
            r11 = r0
            r0 = r13
            r13 = r11
            goto L_0x0092
        L_0x0088:
            r13 = r3
        L_0x0089:
            if (r13 == 0) goto L_0x0091
            r13.close()     // Catch:{ all -> 0x008f }
            goto L_0x0091
        L_0x008f:
            r13 = move-exception
            goto L_0x0098
        L_0x0091:
            return r2
        L_0x0092:
            if (r0 == 0) goto L_0x0097
            r0.close()     // Catch:{ all -> 0x008f }
        L_0x0097:
            throw r13     // Catch:{ all -> 0x008f }
        L_0x0098:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r13, r12)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.NativeProtocol.fetchAllAvailableProtocolVersionsForAppInfo(com.facebook.internal.NativeProtocol$NativeAppInfo):java.util.TreeSet");
    }

    public static final int computeLatestAvailableVersionFromVersionSpec(TreeSet treeSet, int i, int[] iArr) {
        Class<NativeProtocol> cls = NativeProtocol.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return 0;
        }
        try {
            Intrinsics.checkNotNullParameter(iArr, "versionSpec");
            if (treeSet == null) {
                return -1;
            }
            int length = iArr.length - 1;
            Iterator descendingIterator = treeSet.descendingIterator();
            int i2 = -1;
            while (descendingIterator.hasNext()) {
                Integer num = (Integer) descendingIterator.next();
                Intrinsics.checkNotNullExpressionValue(num, "fbAppVersion");
                i2 = Math.max(i2, num.intValue());
                while (length >= 0 && iArr[length] > num.intValue()) {
                    length--;
                }
                if (length < 0) {
                    return -1;
                }
                if (iArr[length] == num.intValue()) {
                    if (length % 2 == 0) {
                        return Math.min(i2, i);
                    }
                    return -1;
                }
            }
            return -1;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return 0;
        }
    }

    private final Uri buildPlatformProviderVersionURI(NativeAppInfo nativeAppInfo) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Uri parse = Uri.parse("content://" + nativeAppInfo.getPackage() + ".provider.PlatformProvider/versions");
            Intrinsics.checkNotNullExpressionValue(parse, "parse(CONTENT_SCHEME + a…ATFORM_PROVIDER_VERSIONS)");
            return parse;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public static abstract class NativeAppInfo {
        private TreeSet availableVersions;

        public abstract String getLoginActivity();

        public abstract String getPackage();

        public void onAvailableVersionsNullOrEmpty() {
        }

        public String getResponseType() {
            return "id_token,token,signed_request,graph_domain";
        }

        public final TreeSet getAvailableVersions() {
            TreeSet treeSet = this.availableVersions;
            if (treeSet == null || treeSet == null || treeSet.isEmpty()) {
                fetchAvailableVersions(false);
            }
            return this.availableVersions;
        }

        public final synchronized void fetchAvailableVersions(boolean z) {
            if (!z) {
                try {
                    TreeSet treeSet = this.availableVersions;
                    if (!(treeSet == null || treeSet == null || treeSet.isEmpty())) {
                        TreeSet treeSet2 = this.availableVersions;
                        if (treeSet2 == null || treeSet2.isEmpty()) {
                            onAvailableVersionsNullOrEmpty();
                        }
                    }
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            this.availableVersions = NativeProtocol.access$fetchAllAvailableProtocolVersionsForAppInfo(NativeProtocol.INSTANCE, this);
            TreeSet treeSet22 = this.availableVersions;
            onAvailableVersionsNullOrEmpty();
        }
    }

    private static final class KatanaAppInfo extends NativeAppInfo {
        public String getLoginActivity() {
            return "com.facebook.katana.ProxyAuth";
        }

        public String getPackage() {
            return "com.facebook.katana";
        }

        public void onAvailableVersionsNullOrEmpty() {
            if (isAndroidAPIVersionNotLessThan30()) {
                Log.w(NativeProtocol.access$getTAG$p(), "Apps that target Android API 30+ (Android 11+) cannot call Facebook native apps unless the package visibility needs are declared. Please follow https://developers.facebook.com/docs/android/troubleshooting/#faq_267321845055988 to make the declaration.");
            }
        }

        private final boolean isAndroidAPIVersionNotLessThan30() {
            return FacebookSdk.getApplicationContext().getApplicationInfo().targetSdkVersion >= 30;
        }
    }

    private static final class MessengerAppInfo extends NativeAppInfo {
        public Void getLoginActivity() {
            return null;
        }

        public String getPackage() {
            return "com.facebook.orca";
        }
    }

    private static final class WakizashiAppInfo extends NativeAppInfo {
        public String getLoginActivity() {
            return "com.facebook.katana.ProxyAuth";
        }

        public String getPackage() {
            return "com.facebook.wakizashi";
        }
    }

    private static final class InstagramAppInfo extends NativeAppInfo {
        public String getLoginActivity() {
            return "com.instagram.platform.AppAuthorizeActivity";
        }

        public String getPackage() {
            return "com.instagram.android";
        }

        public String getResponseType() {
            return "token,signed_request,graph_domain,granted_scopes";
        }
    }

    private static final class EffectTestAppInfo extends NativeAppInfo {
        public Void getLoginActivity() {
            return null;
        }

        public String getPackage() {
            return "com.facebook.arstudio.player";
        }
    }

    public static final class ProtocolVersionQueryResult {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        /* access modifiers changed from: private */
        public NativeAppInfo appInfo;
        /* access modifiers changed from: private */
        public int protocolVersion;

        public /* synthetic */ ProtocolVersionQueryResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private ProtocolVersionQueryResult() {
        }

        public final int getProtocolVersion() {
            return this.protocolVersion;
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final ProtocolVersionQueryResult create(NativeAppInfo nativeAppInfo, int i) {
                ProtocolVersionQueryResult protocolVersionQueryResult = new ProtocolVersionQueryResult((DefaultConstructorMarker) null);
                protocolVersionQueryResult.appInfo = nativeAppInfo;
                protocolVersionQueryResult.protocolVersion = i;
                return protocolVersionQueryResult;
            }

            public final ProtocolVersionQueryResult createEmpty() {
                ProtocolVersionQueryResult protocolVersionQueryResult = new ProtocolVersionQueryResult((DefaultConstructorMarker) null);
                protocolVersionQueryResult.protocolVersion = -1;
                return protocolVersionQueryResult;
            }
        }
    }
}
