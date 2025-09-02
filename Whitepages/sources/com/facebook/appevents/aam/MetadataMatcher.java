package com.facebook.appevents.aam;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

public final class MetadataMatcher {
    public static final MetadataMatcher INSTANCE = new MetadataMatcher();

    private MetadataMatcher() {
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(13:4|5|(1:7)|10|(1:12)|13|14|(2:16|(1:18))|19|20|(4:23|(2:27|35)|31|21)|32|28) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0071 */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0080 A[Catch:{ all -> 0x0029 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.util.List getCurrentViewIndicators(android.view.View r6) {
        /*
            java.lang.Class<com.facebook.appevents.aam.MetadataMatcher> r0 = com.facebook.appevents.aam.MetadataMatcher.class
            boolean r1 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r0)
            r2 = 0
            if (r1 == 0) goto L_0x000a
            return r2
        L_0x000a:
            java.lang.String r1 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r1)     // Catch:{ all -> 0x0029 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0029 }
            r1.<init>()     // Catch:{ all -> 0x0029 }
            java.lang.String r3 = com.facebook.appevents.codeless.internal.ViewHierarchy.getHintOfView(r6)     // Catch:{ all -> 0x0029 }
            r1.add(r3)     // Catch:{ all -> 0x0029 }
            java.lang.Object r3 = r6.getTag()     // Catch:{ all -> 0x0029 }
            if (r3 == 0) goto L_0x002c
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0029 }
            r1.add(r3)     // Catch:{ all -> 0x0029 }
            goto L_0x002c
        L_0x0029:
            r6 = move-exception
            goto L_0x00a2
        L_0x002c:
            java.lang.CharSequence r3 = r6.getContentDescription()     // Catch:{ all -> 0x0029 }
            if (r3 == 0) goto L_0x0039
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0029 }
            r1.add(r3)     // Catch:{ all -> 0x0029 }
        L_0x0039:
            int r3 = r6.getId()     // Catch:{ NotFoundException -> 0x0071 }
            r4 = -1
            if (r3 == r4) goto L_0x0071
            android.content.res.Resources r3 = r6.getResources()     // Catch:{ NotFoundException -> 0x0071 }
            int r6 = r6.getId()     // Catch:{ NotFoundException -> 0x0071 }
            java.lang.String r6 = r3.getResourceName(r6)     // Catch:{ NotFoundException -> 0x0071 }
            java.lang.String r3 = "resourceName"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r3)     // Catch:{ NotFoundException -> 0x0071 }
            kotlin.text.Regex r3 = new kotlin.text.Regex     // Catch:{ NotFoundException -> 0x0071 }
            java.lang.String r4 = "/"
            r3.<init>((java.lang.String) r4)     // Catch:{ NotFoundException -> 0x0071 }
            r4 = 0
            java.util.List r6 = r3.split(r6, r4)     // Catch:{ NotFoundException -> 0x0071 }
            java.util.Collection r6 = (java.util.Collection) r6     // Catch:{ NotFoundException -> 0x0071 }
            java.lang.String[] r3 = new java.lang.String[r4]     // Catch:{ NotFoundException -> 0x0071 }
            java.lang.Object[] r6 = r6.toArray(r3)     // Catch:{ NotFoundException -> 0x0071 }
            java.lang.String[] r6 = (java.lang.String[]) r6     // Catch:{ NotFoundException -> 0x0071 }
            int r3 = r6.length     // Catch:{ NotFoundException -> 0x0071 }
            r4 = 2
            if (r3 != r4) goto L_0x0071
            r3 = 1
            r6 = r6[r3]     // Catch:{ NotFoundException -> 0x0071 }
            r1.add(r6)     // Catch:{ NotFoundException -> 0x0071 }
        L_0x0071:
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x0029 }
            r6.<init>()     // Catch:{ all -> 0x0029 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0029 }
        L_0x007a:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x0029 }
            if (r3 == 0) goto L_0x00a1
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x0029 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x0029 }
            int r4 = r3.length()     // Catch:{ all -> 0x0029 }
            if (r4 <= 0) goto L_0x007a
            int r4 = r3.length()     // Catch:{ all -> 0x0029 }
            r5 = 100
            if (r4 > r5) goto L_0x007a
            java.lang.String r3 = r3.toLowerCase()     // Catch:{ all -> 0x0029 }
            java.lang.String r4 = "this as java.lang.String).toLowerCase()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)     // Catch:{ all -> 0x0029 }
            r6.add(r3)     // Catch:{ all -> 0x0029 }
            goto L_0x007a
        L_0x00a1:
            return r6
        L_0x00a2:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r6, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.aam.MetadataMatcher.getCurrentViewIndicators(android.view.View):java.util.List");
    }

    public static final List getAroundViewIndicators(View view) {
        Class<MetadataMatcher> cls = MetadataMatcher.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(view, "view");
            ArrayList arrayList = new ArrayList();
            ViewGroup parentOfView = ViewHierarchy.getParentOfView(view);
            if (parentOfView != null) {
                for (View view2 : ViewHierarchy.getChildrenOfView(parentOfView)) {
                    if (view != view2) {
                        arrayList.addAll(INSTANCE.getTextIndicators(view2));
                    }
                }
            }
            return arrayList;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final boolean matchIndicator(List list, List list2) {
        Class<MetadataMatcher> cls = MetadataMatcher.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            Intrinsics.checkNotNullParameter(list, "indicators");
            Intrinsics.checkNotNullParameter(list2, "keys");
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (INSTANCE.matchIndicator((String) it.next(), list2)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    private final boolean matchIndicator(String str, List list) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) (String) it.next(), false, 2, (Object) null)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    public static final boolean matchValue(String str, String str2) {
        Class<MetadataMatcher> cls = MetadataMatcher.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            Intrinsics.checkNotNullParameter(str, "text");
            Intrinsics.checkNotNullParameter(str2, "rule");
            return new Regex(str2).matches(str);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    private final List getTextIndicators(View view) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            if (view instanceof EditText) {
                return arrayList;
            }
            if (view instanceof TextView) {
                String obj = ((TextView) view).getText().toString();
                if (obj.length() > 0 && obj.length() < 100) {
                    String lowerCase = obj.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                    arrayList.add(lowerCase);
                }
                return arrayList;
            }
            for (View textIndicators : ViewHierarchy.getChildrenOfView(view)) {
                arrayList.addAll(getTextIndicators(textIndicators));
            }
            return arrayList;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }
}
