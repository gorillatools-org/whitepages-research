package io.branch.coroutines;

import android.content.Context;
import io.branch.data.InstallReferrerResult;
import io.branch.referral.Defines$Jsonkey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.SupervisorKt;

public abstract class InstallReferrersKt {
    public static final Object getGooglePlayStoreReferrerDetails(Context context, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new InstallReferrersKt$getGooglePlayStoreReferrerDetails$2(context, (Continuation) null), continuation);
    }

    public static final Object getHuaweiAppGalleryReferrerDetails(Context context, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new InstallReferrersKt$getHuaweiAppGalleryReferrerDetails$2(context, (Continuation) null), continuation);
    }

    public static final Object getSamsungGalaxyStoreReferrerDetails(Context context, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new InstallReferrersKt$getSamsungGalaxyStoreReferrerDetails$2(context, (Continuation) null), continuation);
    }

    public static final Object getXiaomiGetAppsReferrerDetails(Context context, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new InstallReferrersKt$getXiaomiGetAppsReferrerDetails$2(context, (Continuation) null), continuation);
    }

    public static final Object getMetaInstallReferrerDetails(Context context, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new InstallReferrersKt$getMetaInstallReferrerDetails$2(context, (Continuation) null), continuation);
    }

    /* access modifiers changed from: private */
    public static final InstallReferrerResult queryMetaInstallReferrer(Context context, String str) {
        String str2 = "content://com.facebook.katana.provider.InstallReferrerProvider/" + str;
        InstallReferrerResult queryProvider = queryProvider(context, str2);
        InstallReferrerResult queryProvider2 = queryProvider(context, "content://com.instagram.contentprovider.InstallReferrerProvider/" + str);
        if (queryProvider == null || queryProvider2 == null) {
            if (queryProvider != null) {
                return queryProvider;
            }
        } else if (queryProvider.getLatestClickTimestamp() > queryProvider2.getLatestClickTimestamp()) {
            return queryProvider;
        }
        return queryProvider2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0127, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0128, code lost:
        kotlin.io.CloseableKt.closeFinally(r13, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x012b, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final io.branch.data.InstallReferrerResult queryProvider(android.content.Context r13, java.lang.String r14) {
        /*
            java.lang.String r0 = "install_referrer"
            java.lang.String r1 = "is_ct"
            java.lang.String r2 = "actual_timestamp"
            java.lang.String[] r5 = new java.lang.String[]{r0, r1, r2}
            android.content.ContentResolver r3 = r13.getContentResolver()
            android.net.Uri r4 = android.net.Uri.parse(r14)
            r7 = 0
            r8 = 0
            r6 = 0
            android.database.Cursor r13 = r3.query(r4, r5, r6, r7, r8)
            r3 = 0
            if (r13 == 0) goto L_0x012c
            boolean r4 = r13.moveToFirst()     // Catch:{ all -> 0x003a }
            if (r4 != 0) goto L_0x003d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x003a }
            r0.<init>()     // Catch:{ all -> 0x003a }
            java.lang.String r1 = "getMetaInstallReferrerDetails - cursor is empty or null for provider "
            r0.append(r1)     // Catch:{ all -> 0x003a }
            r0.append(r14)     // Catch:{ all -> 0x003a }
            java.lang.String r14 = r0.toString()     // Catch:{ all -> 0x003a }
            io.branch.referral.BranchLogger.d(r14)     // Catch:{ all -> 0x003a }
            kotlin.io.CloseableKt.closeFinally(r13, r3)
            return r3
        L_0x003a:
            r14 = move-exception
            goto L_0x0126
        L_0x003d:
            int r2 = r13.getColumnIndex(r2)     // Catch:{ all -> 0x003a }
            int r1 = r13.getColumnIndex(r1)     // Catch:{ all -> 0x003a }
            int r0 = r13.getColumnIndex(r0)     // Catch:{ all -> 0x003a }
            r4 = -1
            if (r2 == r4) goto L_0x010e
            if (r1 == r4) goto L_0x010e
            if (r0 != r4) goto L_0x0052
            goto L_0x010e
        L_0x0052:
            long r10 = r13.getLong(r2)     // Catch:{ all -> 0x003a }
            int r1 = r13.getInt(r1)     // Catch:{ all -> 0x003a }
            r2 = 1
            if (r1 != r2) goto L_0x005f
            r12 = r2
            goto L_0x0061
        L_0x005f:
            r1 = 0
            r12 = r1
        L_0x0061:
            java.lang.String r9 = r13.getString(r0)     // Catch:{ all -> 0x003a }
            java.lang.String r0 = "UTF-8"
            java.lang.String r0 = java.net.URLDecoder.decode(r9, r0)     // Catch:{ IllegalArgumentException -> 0x00f5 }
            java.lang.String r1 = "decode(installReferrerString, \"UTF-8\")"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch:{ IllegalArgumentException -> 0x00f5 }
            java.lang.String r1 = "utm_content="
            java.lang.String r2 = ""
            java.lang.String r0 = kotlin.text.StringsKt.substringAfter((java.lang.String) r0, (java.lang.String) r1, (java.lang.String) r2)     // Catch:{ IllegalArgumentException -> 0x00f5 }
            int r1 = r0.length()     // Catch:{ all -> 0x003a }
            if (r1 != 0) goto L_0x0096
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x003a }
            r0.<init>()     // Catch:{ all -> 0x003a }
            java.lang.String r1 = "getMetaInstallReferrerDetails - utm_content is empty for provider "
            r0.append(r1)     // Catch:{ all -> 0x003a }
            r0.append(r14)     // Catch:{ all -> 0x003a }
            java.lang.String r14 = r0.toString()     // Catch:{ all -> 0x003a }
            io.branch.referral.BranchLogger.w(r14)     // Catch:{ all -> 0x003a }
            kotlin.io.CloseableKt.closeFinally(r13, r3)
            return r3
        L_0x0096:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x003a }
            r1.<init>()     // Catch:{ all -> 0x003a }
            java.lang.String r2 = "getMetaInstallReferrerDetails - Got Meta Install Referrer as "
            r1.append(r2)     // Catch:{ all -> 0x003a }
            if (r12 == 0) goto L_0x00a5
            java.lang.String r2 = "click-through"
            goto L_0x00a7
        L_0x00a5:
            java.lang.String r2 = "view-through"
        L_0x00a7:
            r1.append(r2)     // Catch:{ all -> 0x003a }
            java.lang.String r2 = " from provider "
            r1.append(r2)     // Catch:{ all -> 0x003a }
            r1.append(r14)     // Catch:{ all -> 0x003a }
            java.lang.String r14 = ": "
            r1.append(r14)     // Catch:{ all -> 0x003a }
            r1.append(r9)     // Catch:{ all -> 0x003a }
            java.lang.String r14 = r1.toString()     // Catch:{ all -> 0x003a }
            io.branch.referral.BranchLogger.i(r14)     // Catch:{ all -> 0x003a }
            org.json.JSONObject r14 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00dc }
            r14.<init>(r0)     // Catch:{ JSONException -> 0x00dc }
            java.lang.String r0 = "t"
            long r7 = r14.getLong(r0)     // Catch:{ JSONException -> 0x00dc }
            io.branch.data.InstallReferrerResult r14 = new io.branch.data.InstallReferrerResult     // Catch:{ JSONException -> 0x00dc }
            io.branch.referral.Defines$Jsonkey r0 = io.branch.referral.Defines$Jsonkey.Meta_Install_Referrer     // Catch:{ JSONException -> 0x00dc }
            java.lang.String r6 = r0.getKey()     // Catch:{ JSONException -> 0x00dc }
            r5 = r14
            r5.<init>(r6, r7, r9, r10, r12)     // Catch:{ JSONException -> 0x00dc }
            kotlin.io.CloseableKt.closeFinally(r13, r3)
            return r14
        L_0x00dc:
            r14 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x003a }
            r0.<init>()     // Catch:{ all -> 0x003a }
            java.lang.String r1 = "getMetaInstallReferrerDetails - JSONException in queryProvider: "
            r0.append(r1)     // Catch:{ all -> 0x003a }
            r0.append(r14)     // Catch:{ all -> 0x003a }
            java.lang.String r14 = r0.toString()     // Catch:{ all -> 0x003a }
            io.branch.referral.BranchLogger.w(r14)     // Catch:{ all -> 0x003a }
            kotlin.io.CloseableKt.closeFinally(r13, r3)
            return r3
        L_0x00f5:
            r14 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x003a }
            r0.<init>()     // Catch:{ all -> 0x003a }
            java.lang.String r1 = "getMetaInstallReferrerDetails - Error decoding URL: "
            r0.append(r1)     // Catch:{ all -> 0x003a }
            r0.append(r14)     // Catch:{ all -> 0x003a }
            java.lang.String r14 = r0.toString()     // Catch:{ all -> 0x003a }
            io.branch.referral.BranchLogger.w(r14)     // Catch:{ all -> 0x003a }
            kotlin.io.CloseableKt.closeFinally(r13, r3)
            return r3
        L_0x010e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x003a }
            r0.<init>()     // Catch:{ all -> 0x003a }
            java.lang.String r1 = "getMetaInstallReferrerDetails - Required column not found in cursor for provider "
            r0.append(r1)     // Catch:{ all -> 0x003a }
            r0.append(r14)     // Catch:{ all -> 0x003a }
            java.lang.String r14 = r0.toString()     // Catch:{ all -> 0x003a }
            io.branch.referral.BranchLogger.w(r14)     // Catch:{ all -> 0x003a }
            kotlin.io.CloseableKt.closeFinally(r13, r3)
            return r3
        L_0x0126:
            throw r14     // Catch:{ all -> 0x0127 }
        L_0x0127:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r13, r14)
            throw r0
        L_0x012c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.coroutines.InstallReferrersKt.queryProvider(android.content.Context, java.lang.String):io.branch.data.InstallReferrerResult");
    }

    public static final Object fetchLatestInstallReferrer(Context context, Continuation continuation) {
        return SupervisorKt.supervisorScope(new InstallReferrersKt$fetchLatestInstallReferrer$2(context, (Continuation) null), continuation);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: io.branch.data.InstallReferrerResult} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final io.branch.data.InstallReferrerResult getLatestValidReferrerStore(java.util.List r9) {
        /*
            java.lang.String r0 = "allReferrers"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            r0 = r9
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.List r1 = kotlin.collections.CollectionsKt.filterNotNull(r0)
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
            boolean r2 = r1.hasNext()
            if (r2 != 0) goto L_0x001a
            r1 = 0
            goto L_0x0045
        L_0x001a:
            java.lang.Object r2 = r1.next()
            boolean r3 = r1.hasNext()
            if (r3 != 0) goto L_0x0026
        L_0x0024:
            r1 = r2
            goto L_0x0045
        L_0x0026:
            r3 = r2
            io.branch.data.InstallReferrerResult r3 = (io.branch.data.InstallReferrerResult) r3
            long r3 = r3.getLatestInstallTimestamp()
        L_0x002d:
            java.lang.Object r5 = r1.next()
            r6 = r5
            io.branch.data.InstallReferrerResult r6 = (io.branch.data.InstallReferrerResult) r6
            long r6 = r6.getLatestInstallTimestamp()
            int r8 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x003e
            r2 = r5
            r3 = r6
        L_0x003e:
            boolean r5 = r1.hasNext()
            if (r5 != 0) goto L_0x002d
            goto L_0x0024
        L_0x0045:
            io.branch.data.InstallReferrerResult r1 = (io.branch.data.InstallReferrerResult) r1
            java.util.List r0 = kotlin.collections.CollectionsKt.filterNotNull(r0)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            boolean r2 = r0 instanceof java.util.Collection
            if (r2 == 0) goto L_0x005b
            r2 = r0
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x005b
            goto L_0x0083
        L_0x005b:
            java.util.Iterator r0 = r0.iterator()
        L_0x005f:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0083
            java.lang.Object r2 = r0.next()
            io.branch.data.InstallReferrerResult r2 = (io.branch.data.InstallReferrerResult) r2
            java.lang.String r2 = r2.getAppStore()
            io.branch.referral.Defines$Jsonkey r3 = io.branch.referral.Defines$Jsonkey.Meta_Install_Referrer
            java.lang.String r3 = r3.getKey()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r2 == 0) goto L_0x005f
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            io.branch.data.InstallReferrerResult r9 = handleMetaInstallReferrer(r9, r1)
            return r9
        L_0x0083:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.coroutines.InstallReferrersKt.getLatestValidReferrerStore(java.util.List):io.branch.data.InstallReferrerResult");
    }

    private static final InstallReferrerResult handleMetaInstallReferrer(List list, InstallReferrerResult installReferrerResult) {
        Object obj;
        Object obj2;
        Object obj3;
        Iterable iterable = list;
        Iterator it = CollectionsKt.filterNotNull(iterable).iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it.next();
            if (Intrinsics.areEqual((Object) ((InstallReferrerResult) obj2).getAppStore(), (Object) Defines$Jsonkey.Meta_Install_Referrer.getKey())) {
                break;
            }
        }
        InstallReferrerResult installReferrerResult2 = (InstallReferrerResult) obj2;
        Intrinsics.checkNotNull(installReferrerResult2);
        if (!installReferrerResult2.isClickThrough()) {
            Iterator it2 = CollectionsKt.filterNotNull(iterable).iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj3 = null;
                    break;
                }
                obj3 = it2.next();
                if (Intrinsics.areEqual((Object) ((InstallReferrerResult) obj3).getAppStore(), (Object) Defines$Jsonkey.Google_Play_Store.getKey())) {
                    break;
                }
            }
            InstallReferrerResult installReferrerResult3 = (InstallReferrerResult) obj3;
            if (installReferrerResult3 != null && installReferrerResult3.getLatestClickTimestamp() == 0) {
                return installReferrerResult2;
            }
            ArrayList arrayList = new ArrayList();
            for (Object next : CollectionsKt.filterNotNull(iterable)) {
                if (!Intrinsics.areEqual((Object) ((InstallReferrerResult) next).getAppStore(), (Object) Defines$Jsonkey.Meta_Install_Referrer.getKey())) {
                    arrayList.add(next);
                }
            }
            Iterator it3 = arrayList.iterator();
            if (it3.hasNext()) {
                obj = it3.next();
                if (it3.hasNext()) {
                    long latestInstallTimestamp = ((InstallReferrerResult) obj).getLatestInstallTimestamp();
                    do {
                        Object next2 = it3.next();
                        long latestInstallTimestamp2 = ((InstallReferrerResult) next2).getLatestInstallTimestamp();
                        if (latestInstallTimestamp < latestInstallTimestamp2) {
                            obj = next2;
                            latestInstallTimestamp = latestInstallTimestamp2;
                        }
                    } while (it3.hasNext());
                }
            }
            return (InstallReferrerResult) obj;
        } else if (!Intrinsics.areEqual((Object) installReferrerResult.getAppStore(), (Object) Defines$Jsonkey.Google_Play_Store.getKey()) || installReferrerResult.getLatestClickTimestamp() != installReferrerResult2.getLatestClickTimestamp()) {
            return installReferrerResult;
        } else {
            return installReferrerResult2;
        }
    }
}
