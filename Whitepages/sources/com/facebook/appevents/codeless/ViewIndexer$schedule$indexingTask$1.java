package com.facebook.appevents.codeless;

import java.util.TimerTask;

public final class ViewIndexer$schedule$indexingTask$1 extends TimerTask {
    final /* synthetic */ ViewIndexer this$0;

    ViewIndexer$schedule$indexingTask$1(ViewIndexer viewIndexer) {
        this.this$0 = viewIndexer;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x007d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r7 = this;
            com.facebook.appevents.codeless.ViewIndexer r0 = r7.this$0     // Catch:{ Exception -> 0x002f }
            java.lang.ref.WeakReference r0 = com.facebook.appevents.codeless.ViewIndexer.access$getActivityReference$p(r0)     // Catch:{ Exception -> 0x002f }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x002f }
            android.app.Activity r0 = (android.app.Activity) r0     // Catch:{ Exception -> 0x002f }
            android.view.View r1 = com.facebook.appevents.internal.AppEventUtility.getRootView(r0)     // Catch:{ Exception -> 0x002f }
            if (r0 == 0) goto L_0x0095
            if (r1 != 0) goto L_0x0016
            goto L_0x0095
        L_0x0016:
            java.lang.Class r0 = r0.getClass()     // Catch:{ Exception -> 0x002f }
            java.lang.String r0 = r0.getSimpleName()     // Catch:{ Exception -> 0x002f }
            boolean r2 = com.facebook.appevents.codeless.CodelessManager.getIsAppIndexingEnabled$facebook_core_release()     // Catch:{ Exception -> 0x002f }
            if (r2 != 0) goto L_0x0025
            return
        L_0x0025:
            boolean r2 = com.facebook.internal.InternalSettings.isUnityApp()     // Catch:{ Exception -> 0x002f }
            if (r2 == 0) goto L_0x0031
            com.facebook.appevents.codeless.internal.UnityReflection.captureViewHierarchy()     // Catch:{ Exception -> 0x002f }
            return
        L_0x002f:
            r0 = move-exception
            goto L_0x0096
        L_0x0031:
            java.util.concurrent.FutureTask r2 = new java.util.concurrent.FutureTask     // Catch:{ Exception -> 0x002f }
            com.facebook.appevents.codeless.ViewIndexer$ScreenshotTaker r3 = new com.facebook.appevents.codeless.ViewIndexer$ScreenshotTaker     // Catch:{ Exception -> 0x002f }
            r3.<init>(r1)     // Catch:{ Exception -> 0x002f }
            r2.<init>(r3)     // Catch:{ Exception -> 0x002f }
            com.facebook.appevents.codeless.ViewIndexer r3 = r7.this$0     // Catch:{ Exception -> 0x002f }
            android.os.Handler r3 = com.facebook.appevents.codeless.ViewIndexer.access$getUiThreadHandler$p(r3)     // Catch:{ Exception -> 0x002f }
            r3.post(r2)     // Catch:{ Exception -> 0x002f }
            java.lang.String r3 = ""
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x0052 }
            r5 = 1
            java.lang.Object r2 = r2.get(r5, r4)     // Catch:{ Exception -> 0x0052 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x0052 }
            r3 = r2
            goto L_0x005c
        L_0x0052:
            r2 = move-exception
            java.lang.String r4 = com.facebook.appevents.codeless.ViewIndexer.access$getTAG$cp()     // Catch:{ Exception -> 0x002f }
            java.lang.String r5 = "Failed to take screenshot."
            android.util.Log.e(r4, r5, r2)     // Catch:{ Exception -> 0x002f }
        L_0x005c:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x002f }
            r2.<init>()     // Catch:{ Exception -> 0x002f }
            java.lang.String r4 = "screenname"
            r2.put(r4, r0)     // Catch:{ JSONException -> 0x007d }
            java.lang.String r0 = "screenshot"
            r2.put(r0, r3)     // Catch:{ JSONException -> 0x007d }
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ JSONException -> 0x007d }
            r0.<init>()     // Catch:{ JSONException -> 0x007d }
            org.json.JSONObject r1 = com.facebook.appevents.codeless.internal.ViewHierarchy.getDictionaryOfView(r1)     // Catch:{ JSONException -> 0x007d }
            r0.put(r1)     // Catch:{ JSONException -> 0x007d }
            java.lang.String r1 = "view"
            r2.put(r1, r0)     // Catch:{ JSONException -> 0x007d }
            goto L_0x0086
        L_0x007d:
            java.lang.String r0 = com.facebook.appevents.codeless.ViewIndexer.access$getTAG$cp()     // Catch:{ Exception -> 0x002f }
            java.lang.String r1 = "Failed to create JSONObject"
            android.util.Log.e(r0, r1)     // Catch:{ Exception -> 0x002f }
        L_0x0086:
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x002f }
            java.lang.String r1 = "viewTree.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch:{ Exception -> 0x002f }
            com.facebook.appevents.codeless.ViewIndexer r1 = r7.this$0     // Catch:{ Exception -> 0x002f }
            com.facebook.appevents.codeless.ViewIndexer.access$sendToServer(r1, r0)     // Catch:{ Exception -> 0x002f }
            goto L_0x009f
        L_0x0095:
            return
        L_0x0096:
            java.lang.String r1 = com.facebook.appevents.codeless.ViewIndexer.access$getTAG$cp()
            java.lang.String r2 = "UI Component tree indexing failure!"
            android.util.Log.e(r1, r2, r0)
        L_0x009f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.ViewIndexer$schedule$indexingTask$1.run():void");
    }
}
