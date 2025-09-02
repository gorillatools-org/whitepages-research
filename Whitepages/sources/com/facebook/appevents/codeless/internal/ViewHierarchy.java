package com.facebook.appevents.codeless.internal;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.facebook.react.uimanager.TouchTargetHelper;
import com.facebook.react.uimanager.ViewProps;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ViewHierarchy {
    public static final ViewHierarchy INSTANCE = new ViewHierarchy();
    private static WeakReference RCTRootViewReference = new WeakReference((Object) null);
    private static final String TAG = ViewHierarchy.class.getCanonicalName();
    private static Method methodFindTouchTargetView;

    private ViewHierarchy() {
    }

    public static final ViewGroup getParentOfView(View view) {
        Class<ViewHierarchy> cls = ViewHierarchy.class;
        if (CrashShieldHandler.isObjectCrashing(cls) || view == null) {
            return null;
        }
        try {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                return (ViewGroup) parent;
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final List getChildrenOfView(View view) {
        Class<ViewHierarchy> cls = ViewHierarchy.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            if (view instanceof ViewGroup) {
                int childCount = ((ViewGroup) view).getChildCount();
                for (int i = 0; i < childCount; i++) {
                    arrayList.add(((ViewGroup) view).getChildAt(i));
                }
            }
            return arrayList;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final void updateBasicInfoOfView(View view, JSONObject jSONObject) {
        Class<ViewHierarchy> cls = ViewHierarchy.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(jSONObject, "json");
                String textOfView = getTextOfView(view);
                String hintOfView = getHintOfView(view);
                Object tag = view.getTag();
                CharSequence contentDescription = view.getContentDescription();
                jSONObject.put("classname", view.getClass().getCanonicalName());
                jSONObject.put("classtypebitmask", getClassTypeBitmask(view));
                jSONObject.put("id", view.getId());
                if (!SensitiveUserDataUtils.isSensitiveUserData(view)) {
                    jSONObject.put("text", Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(textOfView), ""));
                } else {
                    jSONObject.put("text", "");
                    jSONObject.put("is_user_input", true);
                }
                jSONObject.put("hint", Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(hintOfView), ""));
                if (tag != null) {
                    jSONObject.put("tag", Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(tag.toString()), ""));
                }
                if (contentDescription != null) {
                    jSONObject.put("description", Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(contentDescription.toString()), ""));
                }
                jSONObject.put("dimension", INSTANCE.getDimensionOfView(view));
            } catch (JSONException e) {
                Utility.logd(TAG, (Exception) e);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final JSONObject getDictionaryOfView(View view) {
        JSONObject jSONObject;
        Class<ViewHierarchy> cls = ViewHierarchy.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(view, "view");
            if (Intrinsics.areEqual((Object) view.getClass().getName(), (Object) "com.facebook.react.ReactRootView")) {
                RCTRootViewReference = new WeakReference(view);
            }
            jSONObject = new JSONObject();
            updateBasicInfoOfView(view, jSONObject);
            JSONArray jSONArray = new JSONArray();
            List childrenOfView = getChildrenOfView(view);
            int size = childrenOfView.size();
            for (int i = 0; i < size; i++) {
                jSONArray.put(getDictionaryOfView((View) childrenOfView.get(i)));
            }
            jSONObject.put("childviews", jSONArray);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create JSONObject for view.", e);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
        return jSONObject;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x004a A[Catch:{ all -> 0x0044 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int getClassTypeBitmask(android.view.View r5) {
        /*
            java.lang.Class<com.facebook.appevents.codeless.internal.ViewHierarchy> r0 = com.facebook.appevents.codeless.internal.ViewHierarchy.class
            boolean r1 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r0)
            r2 = 0
            if (r1 == 0) goto L_0x000a
            return r2
        L_0x000a:
            java.lang.String r1 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r1)     // Catch:{ all -> 0x0044 }
            boolean r1 = r5 instanceof android.widget.ImageView     // Catch:{ all -> 0x0044 }
            if (r1 == 0) goto L_0x0015
            r1 = 2
            goto L_0x0016
        L_0x0015:
            r1 = r2
        L_0x0016:
            boolean r3 = r5.isClickable()     // Catch:{ all -> 0x0044 }
            if (r3 == 0) goto L_0x001e
            r1 = r1 | 32
        L_0x001e:
            boolean r3 = isAdapterViewItem(r5)     // Catch:{ all -> 0x0044 }
            if (r3 == 0) goto L_0x0026
            r1 = r1 | 512(0x200, float:7.175E-43)
        L_0x0026:
            boolean r3 = r5 instanceof android.widget.TextView     // Catch:{ all -> 0x0044 }
            if (r3 == 0) goto L_0x004d
            r3 = r1 | 1025(0x401, float:1.436E-42)
            boolean r4 = r5 instanceof android.widget.Button     // Catch:{ all -> 0x0044 }
            if (r4 == 0) goto L_0x0042
            r3 = r1 | 1029(0x405, float:1.442E-42)
            boolean r4 = r5 instanceof android.widget.Switch     // Catch:{ all -> 0x0044 }
            if (r4 == 0) goto L_0x0039
            r1 = r1 | 9221(0x2405, float:1.2921E-41)
            goto L_0x0046
        L_0x0039:
            boolean r4 = r5 instanceof android.widget.CheckBox     // Catch:{ all -> 0x0044 }
            if (r4 == 0) goto L_0x0042
            r3 = 33797(0x8405, float:4.736E-41)
            r1 = r1 | r3
            goto L_0x0046
        L_0x0042:
            r1 = r3
            goto L_0x0046
        L_0x0044:
            r5 = move-exception
            goto L_0x007f
        L_0x0046:
            boolean r5 = r5 instanceof android.widget.EditText     // Catch:{ all -> 0x0044 }
            if (r5 == 0) goto L_0x007e
            r1 = r1 | 2048(0x800, float:2.87E-42)
            goto L_0x007e
        L_0x004d:
            boolean r3 = r5 instanceof android.widget.Spinner     // Catch:{ all -> 0x0044 }
            if (r3 != 0) goto L_0x007c
            boolean r3 = r5 instanceof android.widget.DatePicker     // Catch:{ all -> 0x0044 }
            if (r3 == 0) goto L_0x0056
            goto L_0x007c
        L_0x0056:
            boolean r3 = r5 instanceof android.widget.RatingBar     // Catch:{ all -> 0x0044 }
            if (r3 == 0) goto L_0x005e
            r5 = 65536(0x10000, float:9.18355E-41)
            r1 = r1 | r5
            goto L_0x007e
        L_0x005e:
            boolean r3 = r5 instanceof android.widget.RadioGroup     // Catch:{ all -> 0x0044 }
            if (r3 == 0) goto L_0x0065
            r1 = r1 | 16384(0x4000, float:2.2959E-41)
            goto L_0x007e
        L_0x0065:
            boolean r3 = r5 instanceof android.view.ViewGroup     // Catch:{ all -> 0x0044 }
            if (r3 == 0) goto L_0x007e
            com.facebook.appevents.codeless.internal.ViewHierarchy r3 = INSTANCE     // Catch:{ all -> 0x0044 }
            java.lang.ref.WeakReference r4 = RCTRootViewReference     // Catch:{ all -> 0x0044 }
            java.lang.Object r4 = r4.get()     // Catch:{ all -> 0x0044 }
            android.view.View r4 = (android.view.View) r4     // Catch:{ all -> 0x0044 }
            boolean r5 = r3.isRCTButton(r5, r4)     // Catch:{ all -> 0x0044 }
            if (r5 == 0) goto L_0x007e
            r1 = r1 | 64
            goto L_0x007e
        L_0x007c:
            r1 = r1 | 4096(0x1000, float:5.74E-42)
        L_0x007e:
            return r1
        L_0x007f:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r5, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.internal.ViewHierarchy.getClassTypeBitmask(android.view.View):int");
    }

    private static final boolean isAdapterViewItem(View view) {
        Class<ViewHierarchy> cls = ViewHierarchy.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            ViewParent parent = view.getParent();
            if (parent instanceof AdapterView) {
                return true;
            }
            ViewHierarchy viewHierarchy = INSTANCE;
            Class existingClass = viewHierarchy.getExistingClass("android.support.v4.view.NestedScrollingChild");
            if (existingClass != null && existingClass.isInstance(parent)) {
                return true;
            }
            Class existingClass2 = viewHierarchy.getExistingClass("androidx.core.view.NestedScrollingChild");
            if (existingClass2 == null || !existingClass2.isInstance(parent)) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    public static final String getTextOfView(View view) {
        Object obj;
        String obj2;
        Object selectedItem;
        Class<ViewHierarchy> cls = ViewHierarchy.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            if (view instanceof TextView) {
                obj = ((TextView) view).getText();
                if (view instanceof Switch) {
                    obj = ((Switch) view).isChecked() ? "1" : "0";
                }
            } else {
                if (view instanceof Spinner) {
                    if (((Spinner) view).getCount() > 0 && (selectedItem = ((Spinner) view).getSelectedItem()) != null) {
                        obj = selectedItem.toString();
                    }
                } else if (view instanceof DatePicker) {
                    int year = ((DatePicker) view).getYear();
                    int month = ((DatePicker) view).getMonth();
                    int dayOfMonth = ((DatePicker) view).getDayOfMonth();
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    obj = String.format("%04d-%02d-%02d", Arrays.copyOf(new Object[]{Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(dayOfMonth)}, 3));
                    Intrinsics.checkNotNullExpressionValue(obj, "format(format, *args)");
                } else if (view instanceof TimePicker) {
                    Integer currentHour = ((TimePicker) view).getCurrentHour();
                    Intrinsics.checkNotNullExpressionValue(currentHour, "view.currentHour");
                    int intValue = currentHour.intValue();
                    Integer currentMinute = ((TimePicker) view).getCurrentMinute();
                    Intrinsics.checkNotNullExpressionValue(currentMinute, "view.currentMinute");
                    int intValue2 = currentMinute.intValue();
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    obj = String.format("%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(intValue), Integer.valueOf(intValue2)}, 2));
                    Intrinsics.checkNotNullExpressionValue(obj, "format(format, *args)");
                } else if (view instanceof RadioGroup) {
                    int checkedRadioButtonId = ((RadioGroup) view).getCheckedRadioButtonId();
                    int childCount = ((RadioGroup) view).getChildCount();
                    int i = 0;
                    while (true) {
                        if (i >= childCount) {
                            break;
                        }
                        View childAt = ((RadioGroup) view).getChildAt(i);
                        if (childAt.getId() == checkedRadioButtonId && (childAt instanceof RadioButton)) {
                            obj = ((RadioButton) childAt).getText();
                            break;
                        }
                        i++;
                    }
                } else if (view instanceof RatingBar) {
                    obj = String.valueOf(((RatingBar) view).getRating());
                }
                obj = null;
            }
            return (obj == null || (obj2 = obj.toString()) == null) ? "" : obj2;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final String getHintOfView(View view) {
        CharSequence charSequence;
        String obj;
        Class<ViewHierarchy> cls = ViewHierarchy.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            if (view instanceof EditText) {
                charSequence = ((EditText) view).getHint();
            } else {
                charSequence = view instanceof TextView ? ((TextView) view).getHint() : null;
            }
            return (charSequence == null || (obj = charSequence.toString()) == null) ? "" : obj;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    private final JSONObject getDimensionOfView(View view) {
        JSONObject jSONObject;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            jSONObject = new JSONObject();
            jSONObject.put(ViewProps.TOP, view.getTop());
            jSONObject.put(ViewProps.LEFT, view.getLeft());
            jSONObject.put("width", view.getWidth());
            jSONObject.put("height", view.getHeight());
            jSONObject.put("scrollx", view.getScrollX());
            jSONObject.put("scrolly", view.getScrollY());
            jSONObject.put("visibility", view.getVisibility());
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create JSONObject for dimension.", e);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
        return jSONObject;
    }

    public static final View.OnClickListener getExistingOnClickListener(View view) {
        Field declaredField;
        Class<ViewHierarchy> cls = ViewHierarchy.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Field declaredField2 = Class.forName("android.view.View").getDeclaredField("mListenerInfo");
            if (declaredField2 != null) {
                declaredField2.setAccessible(true);
            }
            Object obj = declaredField2.get(view);
            if (obj == null || (declaredField = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnClickListener")) == null) {
                return null;
            }
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(obj);
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type android.view.View.OnClickListener");
            return (View.OnClickListener) obj2;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x002e A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void setOnClickListener(android.view.View r5, android.view.View.OnClickListener r6) {
        /*
            java.lang.Class<com.facebook.appevents.codeless.internal.ViewHierarchy> r0 = com.facebook.appevents.codeless.internal.ViewHierarchy.class
            boolean r1 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r0)
            if (r1 == 0) goto L_0x0009
            return
        L_0x0009:
            java.lang.String r1 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r1)     // Catch:{ all -> 0x0028 }
            r1 = 0
            java.lang.String r2 = "android.view.View"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException | NoSuchFieldException -> 0x002a }
            java.lang.String r3 = "mListenerInfo"
            java.lang.reflect.Field r2 = r2.getDeclaredField(r3)     // Catch:{ ClassNotFoundException | NoSuchFieldException -> 0x002a }
            java.lang.String r3 = "android.view.View$ListenerInfo"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch:{ ClassNotFoundException | NoSuchFieldException -> 0x002b }
            java.lang.String r4 = "mOnClickListener"
            java.lang.reflect.Field r3 = r3.getDeclaredField(r4)     // Catch:{ ClassNotFoundException | NoSuchFieldException -> 0x002b }
            goto L_0x002c
        L_0x0028:
            r5 = move-exception
            goto L_0x004d
        L_0x002a:
            r2 = r1
        L_0x002b:
            r3 = r1
        L_0x002c:
            if (r2 == 0) goto L_0x0049
            if (r3 != 0) goto L_0x0031
            goto L_0x0049
        L_0x0031:
            r4 = 1
            r2.setAccessible(r4)     // Catch:{ Exception -> 0x004c }
            r3.setAccessible(r4)     // Catch:{ Exception -> 0x004c }
            r2.setAccessible(r4)     // Catch:{ IllegalAccessException -> 0x003f }
            java.lang.Object r1 = r2.get(r5)     // Catch:{ IllegalAccessException -> 0x003f }
        L_0x003f:
            if (r1 != 0) goto L_0x0045
            r5.setOnClickListener(r6)     // Catch:{ Exception -> 0x004c }
            return
        L_0x0045:
            r3.set(r1, r6)     // Catch:{ Exception -> 0x004c }
            goto L_0x004c
        L_0x0049:
            r5.setOnClickListener(r6)     // Catch:{ Exception -> 0x004c }
        L_0x004c:
            return
        L_0x004d:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r5, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.internal.ViewHierarchy.setOnClickListener(android.view.View, android.view.View$OnClickListener):void");
    }

    public static final View.OnTouchListener getExistingOnTouchListener(View view) {
        Field declaredField;
        Class<ViewHierarchy> cls = ViewHierarchy.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Field declaredField2 = Class.forName("android.view.View").getDeclaredField("mListenerInfo");
            if (declaredField2 != null) {
                declaredField2.setAccessible(true);
            }
            Object obj = declaredField2.get(view);
            if (obj == null || (declaredField = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnTouchListener")) == null) {
                return null;
            }
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(obj);
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type android.view.View.OnTouchListener");
            return (View.OnTouchListener) obj2;
        } catch (NoSuchFieldException e) {
            Utility.logd(TAG, (Exception) e);
        } catch (ClassNotFoundException e2) {
            Utility.logd(TAG, (Exception) e2);
        } catch (IllegalAccessException e3) {
            Utility.logd(TAG, (Exception) e3);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
        return null;
    }

    private final View getTouchReactView(float[] fArr, View view) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            initTouchTargetHelperMethods();
            Method method = methodFindTouchTargetView;
            if (!(method == null || view == null)) {
                if (method != null) {
                    Object invoke = method.invoke((Object) null, new Object[]{fArr, view});
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.view.View");
                    View view2 = (View) invoke;
                    if (view2.getId() > 0) {
                        ViewParent parent = view2.getParent();
                        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
                        return (View) parent;
                    }
                } else {
                    throw new IllegalStateException("Required value was null.");
                }
            }
        } catch (IllegalAccessException e) {
            Utility.logd(TAG, (Exception) e);
        } catch (InvocationTargetException e2) {
            Utility.logd(TAG, (Exception) e2);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
        return null;
    }

    public final boolean isRCTButton(View view, View view2) {
        View touchReactView;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            Intrinsics.checkNotNullParameter(view, "view");
            if (!Intrinsics.areEqual((Object) view.getClass().getName(), (Object) "com.facebook.react.views.view.ReactViewGroup") || (touchReactView = getTouchReactView(getViewLocationOnScreen(view), view2)) == null || touchReactView.getId() != view.getId()) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    private final boolean isRCTRootView(View view) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            return Intrinsics.areEqual((Object) view.getClass().getName(), (Object) "com.facebook.react.ReactRootView");
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    public static final View findRCTRootView(View view) {
        Class<ViewHierarchy> cls = ViewHierarchy.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        while (view != null) {
            try {
                if (!INSTANCE.isRCTRootView(view)) {
                    ViewParent parent = view.getParent();
                    if (!(parent instanceof View)) {
                        break;
                    }
                    view = (View) parent;
                } else {
                    return view;
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
        return null;
    }

    private final float[] getViewLocationOnScreen(View view) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            return new float[]{(float) iArr[0], (float) iArr[1]};
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final void initTouchTargetHelperMethods() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                if (methodFindTouchTargetView == null) {
                    Class<TouchTargetHelper> cls = TouchTargetHelper.class;
                    int i = TouchTargetHelper.$r8$clinit;
                    Method declaredMethod = cls.getDeclaredMethod("findTouchTargetView", new Class[]{float[].class, ViewGroup.class});
                    methodFindTouchTargetView = declaredMethod;
                    if (declaredMethod != null) {
                        declaredMethod.setAccessible(true);
                        return;
                    }
                    throw new IllegalStateException("Required value was null.");
                }
            } catch (ClassNotFoundException e) {
                Utility.logd(TAG, (Exception) e);
            } catch (NoSuchMethodException e2) {
                Utility.logd(TAG, (Exception) e2);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final Class getExistingClass(String str) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }
}
