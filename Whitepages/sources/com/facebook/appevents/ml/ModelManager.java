package com.facebook.appevents.ml;

import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.appevents.integrity.IntegrityManager;
import com.facebook.appevents.internal.FileDownloadTask;
import com.facebook.appevents.suggestedevents.SuggestedEventsManager;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ModelManager {
    public static final ModelManager INSTANCE = new ModelManager();
    private static final List MTML_INTEGRITY_DETECT_PREDICTION = CollectionsKt.listOf("none", "address", "health");
    private static final List MTML_SUGGESTED_EVENTS_PREDICTION = CollectionsKt.listOf("other", "fb_mobile_complete_registration", "fb_mobile_add_to_cart", "fb_mobile_purchase", "fb_mobile_initiated_checkout");
    private static final Map taskHandlers = new ConcurrentHashMap();

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.facebook.appevents.ml.ModelManager$Task[] r0 = com.facebook.appevents.ml.ModelManager.Task.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.appevents.ml.ModelManager$Task r1 = com.facebook.appevents.ml.ModelManager.Task.MTML_APP_EVENT_PREDICTION     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.appevents.ml.ModelManager$Task r1 = com.facebook.appevents.ml.ModelManager.Task.MTML_INTEGRITY_DETECT     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.ml.ModelManager.WhenMappings.<clinit>():void");
        }
    }

    private ModelManager() {
    }

    public static final /* synthetic */ float[] access$parseJsonArray(ModelManager modelManager, JSONArray jSONArray) {
        Class<ModelManager> cls = ModelManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return modelManager.parseJsonArray(jSONArray);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public enum Task {
        MTML_INTEGRITY_DETECT,
        MTML_APP_EVENT_PREDICTION;

        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            static {
                /*
                    com.facebook.appevents.ml.ModelManager$Task[] r0 = com.facebook.appevents.ml.ModelManager.Task.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.facebook.appevents.ml.ModelManager$Task r1 = com.facebook.appevents.ml.ModelManager.Task.MTML_INTEGRITY_DETECT     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.facebook.appevents.ml.ModelManager$Task r1 = com.facebook.appevents.ml.ModelManager.Task.MTML_APP_EVENT_PREDICTION     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.ml.ModelManager.Task.WhenMappings.<clinit>():void");
            }
        }

        public final String toKey() {
            int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
            if (i == 1) {
                return "integrity_detect";
            }
            if (i == 2) {
                return "app_event_pred";
            }
            throw new NoWhenBranchMatchedException();
        }

        public final String toUseCase() {
            int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
            if (i == 1) {
                return "MTML_INTEGRITY_DETECT";
            }
            if (i == 2) {
                return "MTML_APP_EVENT_PRED";
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    public static final void enable() {
        Class<ModelManager> cls = ModelManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Utility.runOnNonUiThread(new ModelManager$$ExternalSyntheticLambda0());
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0057 A[Catch:{ Exception -> 0x007b, all -> 0x002c }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0058 A[Catch:{ Exception -> 0x007b, all -> 0x002c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void enable$lambda$0() {
        /*
            java.lang.String r0 = "model_request_timestamp"
            java.lang.String r1 = "models"
            java.lang.Class<com.facebook.appevents.ml.ModelManager> r2 = com.facebook.appevents.ml.ModelManager.class
            boolean r3 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r2)
            if (r3 == 0) goto L_0x000d
            return
        L_0x000d:
            android.content.Context r3 = com.facebook.FacebookSdk.getApplicationContext()     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            java.lang.String r4 = "com.facebook.internal.MODEL_STORE"
            r5 = 0
            android.content.SharedPreferences r3 = r3.getSharedPreferences(r4, r5)     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            r4 = 0
            java.lang.String r4 = r3.getString(r1, r4)     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            if (r4 == 0) goto L_0x002e
            int r5 = r4.length()     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            if (r5 != 0) goto L_0x0026
            goto L_0x002e
        L_0x0026:
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            r5.<init>(r4)     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            goto L_0x0033
        L_0x002c:
            r0 = move-exception
            goto L_0x0078
        L_0x002e:
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            r5.<init>()     // Catch:{ Exception -> 0x007b, all -> 0x002c }
        L_0x0033:
            r6 = 0
            long r6 = r3.getLong(r0, r6)     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            com.facebook.internal.FeatureManager$Feature r4 = com.facebook.internal.FeatureManager.Feature.ModelRequest     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            boolean r4 = com.facebook.internal.FeatureManager.isEnabled(r4)     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            if (r4 == 0) goto L_0x004f
            int r4 = r5.length()     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            if (r4 == 0) goto L_0x004f
            com.facebook.appevents.ml.ModelManager r4 = INSTANCE     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            boolean r4 = r4.isValidTimestamp(r6)     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            if (r4 != 0) goto L_0x006f
        L_0x004f:
            com.facebook.appevents.ml.ModelManager r4 = INSTANCE     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            org.json.JSONObject r5 = r4.fetchModels()     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            if (r5 != 0) goto L_0x0058
            return
        L_0x0058:
            android.content.SharedPreferences$Editor r3 = r3.edit()     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            java.lang.String r4 = r5.toString()     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            android.content.SharedPreferences$Editor r1 = r3.putString(r1, r4)     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            android.content.SharedPreferences$Editor r0 = r1.putLong(r0, r3)     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            r0.apply()     // Catch:{ Exception -> 0x007b, all -> 0x002c }
        L_0x006f:
            com.facebook.appevents.ml.ModelManager r0 = INSTANCE     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            r0.addModels(r5)     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            r0.enableMTML()     // Catch:{ Exception -> 0x007b, all -> 0x002c }
            goto L_0x007b
        L_0x0078:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r0, r2)
        L_0x007b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.ml.ModelManager.enable$lambda$0():void");
    }

    private final boolean isValidTimestamp(long j) {
        if (CrashShieldHandler.isObjectCrashing(this) || j == 0) {
            return false;
        }
        try {
            return System.currentTimeMillis() - j < 259200000;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    private final void addModels(JSONObject jSONObject) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    try {
                        TaskHandler build = TaskHandler.Companion.build(jSONObject.getJSONObject(keys.next()));
                        if (build != null) {
                            taskHandlers.put(build.getUseCase(), build);
                        }
                    } catch (JSONException unused) {
                        return;
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:16|17|21) */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return new org.json.JSONObject();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0063 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final org.json.JSONObject parseRawJsonObject(org.json.JSONObject r13) {
        /*
            r12 = this;
            java.lang.String r0 = "asset_uri"
            java.lang.String r1 = "thresholds"
            java.lang.String r2 = "version_id"
            java.lang.String r3 = "rules_uri"
            java.lang.String r4 = "use_case"
            boolean r5 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r12)
            r6 = 0
            if (r5 == 0) goto L_0x0012
            return r6
        L_0x0012:
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ all -> 0x0057 }
            r5.<init>()     // Catch:{ all -> 0x0057 }
            java.lang.String r7 = "data"
            org.json.JSONArray r13 = r13.getJSONArray(r7)     // Catch:{ JSONException -> 0x0063 }
            int r7 = r13.length()     // Catch:{ JSONException -> 0x0063 }
            r8 = 0
        L_0x0022:
            if (r8 >= r7) goto L_0x0068
            org.json.JSONObject r9 = r13.getJSONObject(r8)     // Catch:{ JSONException -> 0x0063 }
            org.json.JSONObject r10 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0063 }
            r10.<init>()     // Catch:{ JSONException -> 0x0063 }
            java.lang.String r11 = r9.getString(r2)     // Catch:{ JSONException -> 0x0063 }
            r10.put(r2, r11)     // Catch:{ JSONException -> 0x0063 }
            java.lang.String r11 = r9.getString(r4)     // Catch:{ JSONException -> 0x0063 }
            r10.put(r4, r11)     // Catch:{ JSONException -> 0x0063 }
            org.json.JSONArray r11 = r9.getJSONArray(r1)     // Catch:{ JSONException -> 0x0063 }
            r10.put(r1, r11)     // Catch:{ JSONException -> 0x0063 }
            java.lang.String r11 = r9.getString(r0)     // Catch:{ JSONException -> 0x0063 }
            r10.put(r0, r11)     // Catch:{ JSONException -> 0x0063 }
            boolean r11 = r9.has(r3)     // Catch:{ JSONException -> 0x0063 }
            if (r11 == 0) goto L_0x0059
            java.lang.String r11 = r9.getString(r3)     // Catch:{ JSONException -> 0x0063 }
            r10.put(r3, r11)     // Catch:{ JSONException -> 0x0063 }
            goto L_0x0059
        L_0x0057:
            r13 = move-exception
            goto L_0x0069
        L_0x0059:
            java.lang.String r9 = r9.getString(r4)     // Catch:{ JSONException -> 0x0063 }
            r5.put(r9, r10)     // Catch:{ JSONException -> 0x0063 }
            int r8 = r8 + 1
            goto L_0x0022
        L_0x0063:
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ all -> 0x0057 }
            r5.<init>()     // Catch:{ all -> 0x0057 }
        L_0x0068:
            return r5
        L_0x0069:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r13, r12)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.ml.ModelManager.parseRawJsonObject(org.json.JSONObject):org.json.JSONObject");
    }

    private final JSONObject fetchModels() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("fields", TextUtils.join(",", new String[]{"use_case", "version_id", "asset_uri", "rules_uri", "thresholds"}));
            GraphRequest newGraphPathRequest = GraphRequest.Companion.newGraphPathRequest((AccessToken) null, "app/model_asset", (GraphRequest.Callback) null);
            newGraphPathRequest.setParameters(bundle);
            JSONObject jSONObject = newGraphPathRequest.executeAndWait().getJSONObject();
            if (jSONObject == null) {
                return null;
            }
            return parseRawJsonObject(jSONObject);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final void enableMTML() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                ArrayList arrayList = new ArrayList();
                String str = null;
                int i = 0;
                for (Map.Entry entry : taskHandlers.entrySet()) {
                    String str2 = (String) entry.getKey();
                    TaskHandler taskHandler = (TaskHandler) entry.getValue();
                    if (Intrinsics.areEqual((Object) str2, (Object) Task.MTML_APP_EVENT_PREDICTION.toUseCase())) {
                        String assetUri = taskHandler.getAssetUri();
                        int max = Math.max(i, taskHandler.getVersionId());
                        if (FeatureManager.isEnabled(FeatureManager.Feature.SuggestedEvents) && isLocaleEnglish()) {
                            arrayList.add(taskHandler.setOnPostExecute(new ModelManager$$ExternalSyntheticLambda1()));
                        }
                        str = assetUri;
                        i = max;
                    }
                    if (Intrinsics.areEqual((Object) str2, (Object) Task.MTML_INTEGRITY_DETECT.toUseCase())) {
                        str = taskHandler.getAssetUri();
                        i = Math.max(i, taskHandler.getVersionId());
                        if (FeatureManager.isEnabled(FeatureManager.Feature.IntelligentIntegrity)) {
                            arrayList.add(taskHandler.setOnPostExecute(new ModelManager$$ExternalSyntheticLambda2()));
                        }
                    }
                }
                if (str != null && i > 0 && !arrayList.isEmpty()) {
                    TaskHandler.Companion.execute(new TaskHandler("MTML", str, (String) null, i, (float[]) null), arrayList);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void enableMTML$lambda$1() {
        Class<ModelManager> cls = ModelManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                SuggestedEventsManager.enable();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void enableMTML$lambda$2() {
        Class<ModelManager> cls = ModelManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                IntegrityManager.enable();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final boolean isLocaleEnglish() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            Locale resourceLocale = Utility.getResourceLocale();
            if (resourceLocale != null) {
                String language = resourceLocale.getLanguage();
                Intrinsics.checkNotNullExpressionValue(language, "locale.language");
                if (StringsKt.contains$default((CharSequence) language, (CharSequence) "en", false, 2, (Object) null)) {
                    return true;
                }
                return false;
            }
            return true;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    private final float[] parseJsonArray(JSONArray jSONArray) {
        if (CrashShieldHandler.isObjectCrashing(this) || jSONArray == null) {
            return null;
        }
        try {
            float[] fArr = new float[jSONArray.length()];
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    String string = jSONArray.getString(i);
                    Intrinsics.checkNotNullExpressionValue(string, "jsonArray.getString(i)");
                    fArr[i] = Float.parseFloat(string);
                } catch (JSONException unused) {
                }
            }
            return fArr;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public static final File getRuleFile(Task task) {
        Class<ModelManager> cls = ModelManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(task, "task");
            TaskHandler taskHandler = (TaskHandler) taskHandlers.get(task.toUseCase());
            if (taskHandler == null) {
                return null;
            }
            return taskHandler.getRuleFile();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final String[] predict(Task task, float[][] fArr, String[] strArr) {
        Class<ModelManager> cls = ModelManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(fArr, "denses");
            Intrinsics.checkNotNullParameter(strArr, "texts");
            TaskHandler taskHandler = (TaskHandler) taskHandlers.get(task.toUseCase());
            if (taskHandler == null) {
                return null;
            }
            Model model = taskHandler.getModel();
            if (model == null) {
                return null;
            }
            float[] thresholds = taskHandler.getThresholds();
            int length = strArr.length;
            int length2 = fArr[0].length;
            MTensor mTensor = new MTensor(new int[]{length, length2});
            for (int i = 0; i < length; i++) {
                System.arraycopy(fArr[i], 0, mTensor.getData(), i * length2, length2);
            }
            MTensor predictOnMTML = model.predictOnMTML(mTensor, strArr, task.toKey());
            if (predictOnMTML == null || thresholds == null) {
                return null;
            }
            if (predictOnMTML.getData().length == 0) {
                return null;
            }
            if (thresholds.length == 0) {
                return null;
            }
            int i2 = WhenMappings.$EnumSwitchMapping$0[task.ordinal()];
            if (i2 == 1) {
                return INSTANCE.processSuggestedEventResult(predictOnMTML, thresholds);
            }
            if (i2 == 2) {
                return INSTANCE.processIntegrityDetectionResult(predictOnMTML, thresholds);
            }
            throw new NoWhenBranchMatchedException();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    private final String[] processSuggestedEventResult(MTensor mTensor, float[] fArr) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            int shape = mTensor.getShape(0);
            int shape2 = mTensor.getShape(1);
            float[] data = mTensor.getData();
            if (shape2 != fArr.length) {
                return null;
            }
            IntRange until = RangesKt.until(0, shape);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
            Iterator it = until.iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                Object obj = "other";
                int length = fArr.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    int i3 = i2 + 1;
                    if (data[(nextInt * shape2) + i2] >= fArr[i]) {
                        obj = MTML_SUGGESTED_EVENTS_PREDICTION.get(i2);
                    }
                    i++;
                    i2 = i3;
                }
                arrayList.add((String) obj);
            }
            return (String[]) arrayList.toArray(new String[0]);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final String[] processIntegrityDetectionResult(MTensor mTensor, float[] fArr) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            int shape = mTensor.getShape(0);
            int shape2 = mTensor.getShape(1);
            float[] data = mTensor.getData();
            if (shape2 != fArr.length) {
                return null;
            }
            IntRange until = RangesKt.until(0, shape);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
            Iterator it = until.iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                Object obj = "none";
                int length = fArr.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    int i3 = i2 + 1;
                    if (data[(nextInt * shape2) + i2] >= fArr[i]) {
                        obj = MTML_INTEGRITY_DETECT_PREDICTION.get(i2);
                    }
                    i++;
                    i2 = i3;
                }
                arrayList.add((String) obj);
            }
            return (String[]) arrayList.toArray(new String[0]);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public static final class TaskHandler {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private String assetUri;
        private Model model;
        /* access modifiers changed from: private */
        public Runnable onPostExecute;
        private File ruleFile;
        private String ruleUri;
        private float[] thresholds;
        private String useCase;
        private int versionId;

        public TaskHandler(String str, String str2, String str3, int i, float[] fArr) {
            Intrinsics.checkNotNullParameter(str, "useCase");
            Intrinsics.checkNotNullParameter(str2, "assetUri");
            this.useCase = str;
            this.assetUri = str2;
            this.ruleUri = str3;
            this.versionId = i;
            this.thresholds = fArr;
        }

        public final String getUseCase() {
            return this.useCase;
        }

        public final String getAssetUri() {
            return this.assetUri;
        }

        public final String getRuleUri() {
            return this.ruleUri;
        }

        public final int getVersionId() {
            return this.versionId;
        }

        public final float[] getThresholds() {
            return this.thresholds;
        }

        public final File getRuleFile() {
            return this.ruleFile;
        }

        public final void setRuleFile(File file) {
            this.ruleFile = file;
        }

        public final Model getModel() {
            return this.model;
        }

        public final void setModel(Model model2) {
            this.model = model2;
        }

        public final TaskHandler setOnPostExecute(Runnable runnable) {
            this.onPostExecute = runnable;
            return this;
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final TaskHandler build(JSONObject jSONObject) {
                if (jSONObject == null) {
                    return null;
                }
                try {
                    String string = jSONObject.getString("use_case");
                    String string2 = jSONObject.getString("asset_uri");
                    String optString = jSONObject.optString("rules_uri", (String) null);
                    int i = jSONObject.getInt("version_id");
                    float[] access$parseJsonArray = ModelManager.access$parseJsonArray(ModelManager.INSTANCE, jSONObject.getJSONArray("thresholds"));
                    Intrinsics.checkNotNullExpressionValue(string, "useCase");
                    Intrinsics.checkNotNullExpressionValue(string2, "assetUri");
                    return new TaskHandler(string, string2, optString, i, access$parseJsonArray);
                } catch (Exception unused) {
                    return null;
                }
            }

            public final void execute(TaskHandler taskHandler, List list) {
                Intrinsics.checkNotNullParameter(taskHandler, "master");
                Intrinsics.checkNotNullParameter(list, "slaves");
                deleteOldFiles(taskHandler.getUseCase(), taskHandler.getVersionId());
                download(taskHandler.getAssetUri(), taskHandler.getUseCase() + '_' + taskHandler.getVersionId(), new ModelManager$TaskHandler$Companion$$ExternalSyntheticLambda0(list));
            }

            /* access modifiers changed from: private */
            public static final void execute$lambda$1(List list, File file) {
                Intrinsics.checkNotNullParameter(list, "$slaves");
                Intrinsics.checkNotNullParameter(file, "file");
                Model build = Model.Companion.build(file);
                if (build != null) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        TaskHandler taskHandler = (TaskHandler) it.next();
                        TaskHandler.Companion.download(taskHandler.getRuleUri(), taskHandler.getUseCase() + '_' + taskHandler.getVersionId() + "_rule", new ModelManager$TaskHandler$Companion$$ExternalSyntheticLambda1(taskHandler, build));
                    }
                }
            }

            /* access modifiers changed from: private */
            public static final void execute$lambda$1$lambda$0(TaskHandler taskHandler, Model model, File file) {
                Intrinsics.checkNotNullParameter(taskHandler, "$slave");
                Intrinsics.checkNotNullParameter(file, "file");
                taskHandler.setModel(model);
                taskHandler.setRuleFile(file);
                Runnable access$getOnPostExecute$p = taskHandler.onPostExecute;
                if (access$getOnPostExecute$p != null) {
                    access$getOnPostExecute$p.run();
                }
            }

            private final void deleteOldFiles(String str, int i) {
                File[] listFiles;
                File mlDir = Utils.getMlDir();
                if (mlDir != null && (listFiles = mlDir.listFiles()) != null && listFiles.length != 0) {
                    String str2 = str + '_' + i;
                    for (File file : listFiles) {
                        String name = file.getName();
                        Intrinsics.checkNotNullExpressionValue(name, "name");
                        if (StringsKt.startsWith$default(name, str, false, 2, (Object) null) && !StringsKt.startsWith$default(name, str2, false, 2, (Object) null)) {
                            file.delete();
                        }
                    }
                }
            }

            private final void download(String str, String str2, FileDownloadTask.Callback callback) {
                File file = new File(Utils.getMlDir(), str2);
                if (str == null || file.exists()) {
                    callback.onComplete(file);
                } else {
                    new FileDownloadTask(str, file, callback).execute(new String[0]);
                }
            }
        }
    }
}
