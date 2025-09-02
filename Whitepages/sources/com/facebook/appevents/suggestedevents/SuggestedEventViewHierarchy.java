package com.facebook.appevents.suggestedevents;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

public final class SuggestedEventViewHierarchy {
    public static final SuggestedEventViewHierarchy INSTANCE = new SuggestedEventViewHierarchy();
    private static final List blacklistedViews = CollectionsKt.listOf(Switch.class, Spinner.class, DatePicker.class, TimePicker.class, RadioGroup.class, RatingBar.class, EditText.class, AdapterView.class);

    private SuggestedEventViewHierarchy() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r1v3, types: [org.json.JSONObject] */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final org.json.JSONObject getDictionaryOfView(android.view.View r5, android.view.View r6) {
        /*
            java.lang.Class<com.facebook.appevents.suggestedevents.SuggestedEventViewHierarchy> r0 = com.facebook.appevents.suggestedevents.SuggestedEventViewHierarchy.class
            boolean r1 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r0)
            r2 = 0
            if (r1 == 0) goto L_0x000a
            return r2
        L_0x000a:
            java.lang.String r1 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r1)     // Catch:{ all -> 0x0022 }
            java.lang.String r1 = "clickedView"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r1)     // Catch:{ all -> 0x0022 }
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ all -> 0x0022 }
            r1.<init>()     // Catch:{ all -> 0x0022 }
            if (r5 != r6) goto L_0x0024
            java.lang.String r3 = "is_interacted"
            r4 = 1
            r1.put(r3, r4)     // Catch:{ JSONException -> 0x004d }
            goto L_0x0024
        L_0x0022:
            r5 = move-exception
            goto L_0x004e
        L_0x0024:
            updateBasicInfo(r5, r1)     // Catch:{ JSONException -> 0x004d }
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ JSONException -> 0x004d }
            r3.<init>()     // Catch:{ JSONException -> 0x004d }
            java.util.List r5 = com.facebook.appevents.codeless.internal.ViewHierarchy.getChildrenOfView(r5)     // Catch:{ JSONException -> 0x004d }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ JSONException -> 0x004d }
        L_0x0034:
            boolean r4 = r5.hasNext()     // Catch:{ JSONException -> 0x004d }
            if (r4 == 0) goto L_0x0048
            java.lang.Object r4 = r5.next()     // Catch:{ JSONException -> 0x004d }
            android.view.View r4 = (android.view.View) r4     // Catch:{ JSONException -> 0x004d }
            org.json.JSONObject r4 = getDictionaryOfView(r4, r6)     // Catch:{ JSONException -> 0x004d }
            r3.put(r4)     // Catch:{ JSONException -> 0x004d }
            goto L_0x0034
        L_0x0048:
            java.lang.String r5 = "childviews"
            r1.put(r5, r3)     // Catch:{ JSONException -> 0x004d }
        L_0x004d:
            return r1
        L_0x004e:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r5, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.suggestedevents.SuggestedEventViewHierarchy.getDictionaryOfView(android.view.View, android.view.View):org.json.JSONObject");
    }

    public static final void updateBasicInfo(View view, JSONObject jSONObject) {
        Class<SuggestedEventViewHierarchy> cls = SuggestedEventViewHierarchy.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(jSONObject, "json");
                try {
                    String textOfView = ViewHierarchy.getTextOfView(view);
                    String hintOfView = ViewHierarchy.getHintOfView(view);
                    jSONObject.put("classname", view.getClass().getSimpleName());
                    jSONObject.put("classtypebitmask", ViewHierarchy.getClassTypeBitmask(view));
                    if (textOfView.length() > 0) {
                        jSONObject.put("text", textOfView);
                    }
                    if (hintOfView.length() > 0) {
                        jSONObject.put("hint", hintOfView);
                    }
                    if (view instanceof EditText) {
                        jSONObject.put("inputtype", ((EditText) view).getInputType());
                    }
                } catch (JSONException unused) {
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final List getAllClickableViews(View view) {
        Class<SuggestedEventViewHierarchy> cls = SuggestedEventViewHierarchy.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(view, "view");
            ArrayList arrayList = new ArrayList();
            for (Class isInstance : blacklistedViews) {
                if (isInstance.isInstance(view)) {
                    return arrayList;
                }
            }
            if (view.isClickable()) {
                arrayList.add(view);
            }
            for (View allClickableViews : ViewHierarchy.getChildrenOfView(view)) {
                arrayList.addAll(getAllClickableViews(allClickableViews));
            }
            return arrayList;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final String getTextOfViewRecursively(View view) {
        Class<SuggestedEventViewHierarchy> cls = SuggestedEventViewHierarchy.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(view, "hostView");
            String textOfView = ViewHierarchy.getTextOfView(view);
            if (textOfView.length() > 0) {
                return textOfView;
            }
            String join = TextUtils.join(" ", INSTANCE.getTextOfChildren(view));
            Intrinsics.checkNotNullExpressionValue(join, "join(\" \", childrenText)");
            return join;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    private final List getTextOfChildren(View view) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            for (View view2 : ViewHierarchy.getChildrenOfView(view)) {
                String textOfView = ViewHierarchy.getTextOfView(view2);
                if (textOfView.length() > 0) {
                    arrayList.add(textOfView);
                }
                arrayList.addAll(getTextOfChildren(view2));
            }
            return arrayList;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }
}
