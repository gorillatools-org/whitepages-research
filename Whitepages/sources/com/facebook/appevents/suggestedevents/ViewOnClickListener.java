package com.facebook.appevents.suggestedevents;

import android.os.Bundle;
import android.view.View;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.appevents.ml.ModelManager;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

public final class ViewOnClickListener implements View.OnClickListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final Set viewsAttachedListener = new HashSet();
    private final String activityName;
    private final View.OnClickListener baseListener;
    private final WeakReference hostViewWeakReference;
    private final WeakReference rootViewWeakReference;

    public /* synthetic */ ViewOnClickListener(View view, View view2, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(view, view2, str);
    }

    private ViewOnClickListener(View view, View view2, String str) {
        this.baseListener = ViewHierarchy.getExistingOnClickListener(view);
        this.rootViewWeakReference = new WeakReference(view2);
        this.hostViewWeakReference = new WeakReference(view);
        String lowerCase = str.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        this.activityName = StringsKt.replace$default(lowerCase, "activity", "", false, 4, (Object) null);
    }

    public static final /* synthetic */ Set access$getViewsAttachedListener$cp() {
        Class<ViewOnClickListener> cls = ViewOnClickListener.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return viewsAttachedListener;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public void onClick(View view) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(view, "view");
                View.OnClickListener onClickListener = this.baseListener;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
                process();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final void process() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                View view = (View) this.rootViewWeakReference.get();
                View view2 = (View) this.hostViewWeakReference.get();
                if (view != null && view2 != null) {
                    try {
                        String textOfViewRecursively = SuggestedEventViewHierarchy.getTextOfViewRecursively(view2);
                        String pathID = PredictionHistoryManager.getPathID(view2, textOfViewRecursively);
                        if (pathID != null && !Companion.queryHistoryAndProcess(pathID, textOfViewRecursively)) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("view", SuggestedEventViewHierarchy.getDictionaryOfView(view, view2));
                            jSONObject.put("screenname", this.activityName);
                            predictAndProcess(pathID, textOfViewRecursively, jSONObject);
                        }
                    } catch (Exception unused) {
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final void predictAndProcess(String str, String str2, JSONObject jSONObject) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Utility.runOnNonUiThread(new ViewOnClickListener$$ExternalSyntheticLambda0(jSONObject, str2, this, str));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void predictAndProcess$lambda$0(JSONObject jSONObject, String str, ViewOnClickListener viewOnClickListener, String str2) {
        String[] predict;
        Class<ViewOnClickListener> cls = ViewOnClickListener.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(jSONObject, "$viewData");
                Intrinsics.checkNotNullParameter(str, "$buttonText");
                Intrinsics.checkNotNullParameter(viewOnClickListener, "this$0");
                Intrinsics.checkNotNullParameter(str2, "$pathID");
                try {
                    String lowerCase = Utility.getAppName(FacebookSdk.getApplicationContext()).toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                    float[] denseFeatures = FeatureExtractor.getDenseFeatures(jSONObject, lowerCase);
                    String textFeature = FeatureExtractor.getTextFeature(str, viewOnClickListener.activityName, lowerCase);
                    if (denseFeatures != null && (predict = ModelManager.predict(ModelManager.Task.MTML_APP_EVENT_PREDICTION, new float[][]{denseFeatures}, new String[]{textFeature})) != null) {
                        String str3 = predict[0];
                        PredictionHistoryManager.addPrediction(str2, str3);
                        if (!Intrinsics.areEqual((Object) str3, (Object) "other")) {
                            Companion.processPredictedResult(str3, str, denseFeatures);
                        }
                    }
                } catch (Exception unused) {
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void attachListener$facebook_core_release(View view, View view2, String str) {
            Intrinsics.checkNotNullParameter(view, "hostView");
            Intrinsics.checkNotNullParameter(view2, "rootView");
            Intrinsics.checkNotNullParameter(str, "activityName");
            int hashCode = view.hashCode();
            if (!ViewOnClickListener.access$getViewsAttachedListener$cp().contains(Integer.valueOf(hashCode))) {
                ViewHierarchy.setOnClickListener(view, new ViewOnClickListener(view, view2, str, (DefaultConstructorMarker) null));
                ViewOnClickListener.access$getViewsAttachedListener$cp().add(Integer.valueOf(hashCode));
            }
        }

        /* access modifiers changed from: private */
        public final boolean queryHistoryAndProcess(String str, String str2) {
            String queryEvent = PredictionHistoryManager.queryEvent(str);
            if (queryEvent == null) {
                return false;
            }
            if (Intrinsics.areEqual((Object) queryEvent, (Object) "other")) {
                return true;
            }
            Utility.runOnNonUiThread(new ViewOnClickListener$Companion$$ExternalSyntheticLambda0(queryEvent, str2));
            return true;
        }

        /* access modifiers changed from: private */
        public static final void queryHistoryAndProcess$lambda$0(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "$queriedEvent");
            Intrinsics.checkNotNullParameter(str2, "$buttonText");
            ViewOnClickListener.Companion.processPredictedResult(str, str2, new float[0]);
        }

        /* access modifiers changed from: private */
        public final void processPredictedResult(String str, String str2, float[] fArr) {
            if (SuggestedEventsManager.isProductionEvents$facebook_core_release(str)) {
                new InternalAppEventsLogger(FacebookSdk.getApplicationContext()).logEventFromSE(str, str2);
            } else if (SuggestedEventsManager.isEligibleEvents$facebook_core_release(str)) {
                sendPredictedResult(str, str2, fArr);
            }
        }

        private final void sendPredictedResult(String str, String str2, float[] fArr) {
            Bundle bundle = new Bundle();
            try {
                bundle.putString("event_name", str);
                JSONObject jSONObject = new JSONObject();
                StringBuilder sb = new StringBuilder();
                for (float append : fArr) {
                    sb.append(append);
                    sb.append(",");
                }
                jSONObject.put("dense", sb.toString());
                jSONObject.put("button_text", str2);
                bundle.putString("metadata", jSONObject.toString());
                GraphRequest.Companion companion = GraphRequest.Companion;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format(Locale.US, "%s/suggested_events", Arrays.copyOf(new Object[]{FacebookSdk.getApplicationId()}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                GraphRequest newPostRequest = companion.newPostRequest((AccessToken) null, format, (JSONObject) null, (GraphRequest.Callback) null);
                newPostRequest.setParameters(bundle);
                newPostRequest.executeAndWait();
            } catch (JSONException unused) {
            }
        }
    }
}
