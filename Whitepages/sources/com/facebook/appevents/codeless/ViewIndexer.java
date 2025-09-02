package com.facebook.appevents.codeless;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.storage.db.k;
import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

public final class ViewIndexer {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG;
    private static ViewIndexer instance;
    private final WeakReference activityReference;
    private Timer indexingTimer;
    private String previousDigest = null;
    private final Handler uiThreadHandler = new Handler(Looper.getMainLooper());

    public ViewIndexer(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activityReference = new WeakReference(activity);
        instance = this;
    }

    public static final /* synthetic */ WeakReference access$getActivityReference$p(ViewIndexer viewIndexer) {
        Class<ViewIndexer> cls = ViewIndexer.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return viewIndexer.activityReference;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ String access$getTAG$cp() {
        Class<ViewIndexer> cls = ViewIndexer.class;
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

    public static final /* synthetic */ Handler access$getUiThreadHandler$p(ViewIndexer viewIndexer) {
        Class<ViewIndexer> cls = ViewIndexer.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return viewIndexer.uiThreadHandler;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ void access$sendToServer(ViewIndexer viewIndexer, String str) {
        Class<ViewIndexer> cls = ViewIndexer.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                viewIndexer.sendToServer(str);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public final void schedule() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                FacebookSdk.getExecutor().execute(new ViewIndexer$$ExternalSyntheticLambda0(this, new ViewIndexer$schedule$indexingTask$1(this)));
            } catch (RejectedExecutionException e) {
                Log.e(TAG, "Error scheduling indexing job", e);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void schedule$lambda$0(ViewIndexer viewIndexer, TimerTask timerTask) {
        Class<ViewIndexer> cls = ViewIndexer.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(viewIndexer, "this$0");
                Intrinsics.checkNotNullParameter(timerTask, "$indexingTask");
                Timer timer = viewIndexer.indexingTimer;
                if (timer != null) {
                    timer.cancel();
                }
                viewIndexer.previousDigest = null;
                Timer timer2 = new Timer();
                timer2.scheduleAtFixedRate(timerTask, 0, 1000);
                viewIndexer.indexingTimer = timer2;
            } catch (Exception e) {
                Log.e(TAG, "Error scheduling indexing job", e);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public final void unschedule() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                if (((Activity) this.activityReference.get()) != null) {
                    Timer timer = this.indexingTimer;
                    if (timer != null) {
                        timer.cancel();
                    }
                    this.indexingTimer = null;
                }
            } catch (Exception e) {
                Log.e(TAG, "Error unscheduling indexing job", e);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final void sendToServer(String str) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                FacebookSdk.getExecutor().execute(new ViewIndexer$$ExternalSyntheticLambda1(str, this));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void sendToServer$lambda$1(String str, ViewIndexer viewIndexer) {
        Class<ViewIndexer> cls = ViewIndexer.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(str, "$tree");
                Intrinsics.checkNotNullParameter(viewIndexer, "this$0");
                String md5hash = Utility.md5hash(str);
                AccessToken currentAccessToken = AccessToken.Companion.getCurrentAccessToken();
                if (md5hash == null || !Intrinsics.areEqual((Object) md5hash, (Object) viewIndexer.previousDigest)) {
                    viewIndexer.processRequest(Companion.buildAppIndexingRequest(str, currentAccessToken, FacebookSdk.getApplicationId(), "app_indexing"), md5hash);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public final void processRequest(GraphRequest graphRequest, String str) {
        if (!CrashShieldHandler.isObjectCrashing(this) && graphRequest != null) {
            try {
                GraphResponse executeAndWait = graphRequest.executeAndWait();
                JSONObject jSONObject = executeAndWait.getJSONObject();
                if (jSONObject != null) {
                    if (Intrinsics.areEqual((Object) "true", (Object) jSONObject.optString(FirebaseAnalytics.Param.SUCCESS))) {
                        Logger.Companion.log(LoggingBehavior.APP_EVENTS, TAG, "Successfully send UI component tree to server");
                        this.previousDigest = str;
                    }
                    if (jSONObject.has("is_app_indexing_enabled")) {
                        CodelessManager.updateAppIndexing$facebook_core_release(jSONObject.getBoolean("is_app_indexing_enabled"));
                        return;
                    }
                    return;
                }
                String str2 = TAG;
                Log.e(str2, "Error sending UI component tree to Facebook: " + executeAndWait.getError());
            } catch (JSONException e) {
                Log.e(TAG, "Error decoding server response.", e);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private static final class ScreenshotTaker implements Callable {
        private final WeakReference rootView;

        public ScreenshotTaker(View view) {
            Intrinsics.checkNotNullParameter(view, "rootView");
            this.rootView = new WeakReference(view);
        }

        public String call() {
            View view = (View) this.rootView.get();
            if (view == null || view.getWidth() == 0 || view.getHeight() == 0) {
                return "";
            }
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(view.width,…t, Bitmap.Config.RGB_565)");
            view.draw(new Canvas(createBitmap));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            createBitmap.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream);
            String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
            Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(outputStr…eArray(), Base64.NO_WRAP)");
            return encodeToString;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final GraphRequest buildAppIndexingRequest(String str, AccessToken accessToken, String str2, String str3) {
            Intrinsics.checkNotNullParameter(str3, "requestType");
            if (str == null) {
                return null;
            }
            GraphRequest.Companion companion = GraphRequest.Companion;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(Locale.US, "%s/app_indexing", Arrays.copyOf(new Object[]{str2}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            GraphRequest newPostRequest = companion.newPostRequest(accessToken, format, (JSONObject) null, (GraphRequest.Callback) null);
            Bundle parameters = newPostRequest.getParameters();
            if (parameters == null) {
                parameters = new Bundle();
            }
            parameters.putString("tree", str);
            parameters.putString(k.a.q, AppEventUtility.getAppVersion());
            parameters.putString(k.a.b, "android");
            parameters.putString("request_type", str3);
            if (Intrinsics.areEqual((Object) str3, (Object) "app_indexing")) {
                parameters.putString("device_session_id", CodelessManager.getCurrentDeviceSessionID$facebook_core_release());
            }
            newPostRequest.setParameters(parameters);
            newPostRequest.setCallback(new ViewIndexer$Companion$$ExternalSyntheticLambda0());
            return newPostRequest;
        }

        /* access modifiers changed from: private */
        public static final void buildAppIndexingRequest$lambda$0(GraphResponse graphResponse) {
            Intrinsics.checkNotNullParameter(graphResponse, "it");
            Logger.Companion.log(LoggingBehavior.APP_EVENTS, ViewIndexer.access$getTAG$cp(), "App index sent to FB!");
        }
    }

    static {
        String canonicalName = ViewIndexer.class.getCanonicalName();
        if (canonicalName == null) {
            canonicalName = "";
        }
        TAG = canonicalName;
    }
}
