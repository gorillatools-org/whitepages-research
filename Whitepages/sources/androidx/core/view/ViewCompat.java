package androidx.core.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContentInfo;
import android.view.Display;
import android.view.KeyEvent;
import android.view.OnReceiveContentListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import androidx.collection.SimpleArrayMap;
import androidx.core.R$id;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.core.view.autofill.AutofillIdCompat;
import androidx.core.view.contentcapture.ContentCaptureSessionCompat;
import com.salesforce.marketingcloud.b;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

public abstract class ViewCompat {
    private static final int[] ACCESSIBILITY_ACTIONS_RESOURCE_IDS = {R$id.accessibility_custom_action_0, R$id.accessibility_custom_action_1, R$id.accessibility_custom_action_2, R$id.accessibility_custom_action_3, R$id.accessibility_custom_action_4, R$id.accessibility_custom_action_5, R$id.accessibility_custom_action_6, R$id.accessibility_custom_action_7, R$id.accessibility_custom_action_8, R$id.accessibility_custom_action_9, R$id.accessibility_custom_action_10, R$id.accessibility_custom_action_11, R$id.accessibility_custom_action_12, R$id.accessibility_custom_action_13, R$id.accessibility_custom_action_14, R$id.accessibility_custom_action_15, R$id.accessibility_custom_action_16, R$id.accessibility_custom_action_17, R$id.accessibility_custom_action_18, R$id.accessibility_custom_action_19, R$id.accessibility_custom_action_20, R$id.accessibility_custom_action_21, R$id.accessibility_custom_action_22, R$id.accessibility_custom_action_23, R$id.accessibility_custom_action_24, R$id.accessibility_custom_action_25, R$id.accessibility_custom_action_26, R$id.accessibility_custom_action_27, R$id.accessibility_custom_action_28, R$id.accessibility_custom_action_29, R$id.accessibility_custom_action_30, R$id.accessibility_custom_action_31};
    private static final OnReceiveContentViewBehavior NO_OP_ON_RECEIVE_CONTENT_VIEW_BEHAVIOR = new ViewCompat$$ExternalSyntheticLambda0();
    private static boolean sAccessibilityDelegateCheckFailed = false;
    private static Field sAccessibilityDelegateField;
    private static final AccessibilityPaneVisibilityManager sAccessibilityPaneVisibilityManager = new AccessibilityPaneVisibilityManager();
    private static WeakHashMap sViewPropertyAnimatorMap;

    public interface OnUnhandledKeyEventListenerCompat {
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ContentInfoCompat lambda$static$0(ContentInfoCompat contentInfoCompat) {
        return contentInfoCompat;
    }

    public static void saveAttributeDataForStyleable(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.saveAttributeDataForStyleable(view, context, iArr, attributeSet, typedArray, i, i2);
        }
    }

    public static void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        view.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat.unwrap());
    }

    public static void setAccessibilityDelegate(View view, AccessibilityDelegateCompat accessibilityDelegateCompat) {
        View.AccessibilityDelegate accessibilityDelegate;
        if (accessibilityDelegateCompat == null && (getAccessibilityDelegateInternal(view) instanceof AccessibilityDelegateCompat.AccessibilityDelegateAdapter)) {
            accessibilityDelegateCompat = new AccessibilityDelegateCompat();
        }
        setImportantForAccessibilityIfNeeded(view);
        if (accessibilityDelegateCompat == null) {
            accessibilityDelegate = null;
        } else {
            accessibilityDelegate = accessibilityDelegateCompat.getBridge();
        }
        view.setAccessibilityDelegate(accessibilityDelegate);
    }

    public static int getImportantForAutofill(View view) {
        return Api26Impl.getImportantForAutofill(view);
    }

    public static void setImportantForAutofill(View view, int i) {
        Api26Impl.setImportantForAutofill(view, i);
    }

    public static boolean hasAccessibilityDelegate(View view) {
        return getAccessibilityDelegateInternal(view) != null;
    }

    public static AccessibilityDelegateCompat getAccessibilityDelegate(View view) {
        View.AccessibilityDelegate accessibilityDelegateInternal = getAccessibilityDelegateInternal(view);
        if (accessibilityDelegateInternal == null) {
            return null;
        }
        if (accessibilityDelegateInternal instanceof AccessibilityDelegateCompat.AccessibilityDelegateAdapter) {
            return ((AccessibilityDelegateCompat.AccessibilityDelegateAdapter) accessibilityDelegateInternal).mCompat;
        }
        return new AccessibilityDelegateCompat(accessibilityDelegateInternal);
    }

    static void ensureAccessibilityDelegateCompat(View view) {
        AccessibilityDelegateCompat accessibilityDelegate = getAccessibilityDelegate(view);
        if (accessibilityDelegate == null) {
            accessibilityDelegate = new AccessibilityDelegateCompat();
        }
        setAccessibilityDelegate(view, accessibilityDelegate);
    }

    private static View.AccessibilityDelegate getAccessibilityDelegateInternal(View view) {
        if (Build.VERSION.SDK_INT >= 29) {
            return Api29Impl.getAccessibilityDelegate(view);
        }
        return getAccessibilityDelegateThroughReflection(view);
    }

    private static View.AccessibilityDelegate getAccessibilityDelegateThroughReflection(View view) {
        if (sAccessibilityDelegateCheckFailed) {
            return null;
        }
        if (sAccessibilityDelegateField == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                sAccessibilityDelegateField = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                sAccessibilityDelegateCheckFailed = true;
                return null;
            }
        }
        try {
            Object obj = sAccessibilityDelegateField.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            sAccessibilityDelegateCheckFailed = true;
            return null;
        }
    }

    public static boolean hasTransientState(View view) {
        return view.hasTransientState();
    }

    public static void setHasTransientState(View view, boolean z) {
        view.setHasTransientState(z);
    }

    public static void postInvalidateOnAnimation(View view) {
        view.postInvalidateOnAnimation();
    }

    public static void postOnAnimation(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static void postOnAnimationDelayed(View view, Runnable runnable, long j) {
        view.postOnAnimationDelayed(runnable, j);
    }

    public static int getImportantForAccessibility(View view) {
        return view.getImportantForAccessibility();
    }

    public static void setImportantForAccessibility(View view, int i) {
        view.setImportantForAccessibility(i);
    }

    public static boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        return view.performAccessibilityAction(i, bundle);
    }

    public static int addAccessibilityAction(View view, CharSequence charSequence, AccessibilityViewCommand accessibilityViewCommand) {
        int availableActionIdFromResources = getAvailableActionIdFromResources(view, charSequence);
        if (availableActionIdFromResources != -1) {
            addAccessibilityAction(view, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(availableActionIdFromResources, charSequence, accessibilityViewCommand));
        }
        return availableActionIdFromResources;
    }

    private static int getAvailableActionIdFromResources(View view, CharSequence charSequence) {
        List actionList = getActionList(view);
        for (int i = 0; i < actionList.size(); i++) {
            if (TextUtils.equals(charSequence, ((AccessibilityNodeInfoCompat.AccessibilityActionCompat) actionList.get(i)).getLabel())) {
                return ((AccessibilityNodeInfoCompat.AccessibilityActionCompat) actionList.get(i)).getId();
            }
        }
        int i2 = -1;
        int i3 = 0;
        while (true) {
            int[] iArr = ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
            if (i3 >= iArr.length || i2 != -1) {
                return i2;
            }
            int i4 = iArr[i3];
            boolean z = true;
            for (int i5 = 0; i5 < actionList.size(); i5++) {
                z &= ((AccessibilityNodeInfoCompat.AccessibilityActionCompat) actionList.get(i5)).getId() != i4;
            }
            if (z) {
                i2 = i4;
            }
            i3++;
        }
        return i2;
    }

    public static void replaceAccessibilityAction(View view, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, CharSequence charSequence, AccessibilityViewCommand accessibilityViewCommand) {
        if (accessibilityViewCommand == null && charSequence == null) {
            removeAccessibilityAction(view, accessibilityActionCompat.getId());
        } else {
            addAccessibilityAction(view, accessibilityActionCompat.createReplacementAction(charSequence, accessibilityViewCommand));
        }
    }

    private static void addAccessibilityAction(View view, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat) {
        ensureAccessibilityDelegateCompat(view);
        removeActionWithId(accessibilityActionCompat.getId(), view);
        getActionList(view).add(accessibilityActionCompat);
        notifyViewAccessibilityStateChangedIfNeeded(view, 0);
    }

    public static void removeAccessibilityAction(View view, int i) {
        removeActionWithId(i, view);
        notifyViewAccessibilityStateChangedIfNeeded(view, 0);
    }

    private static void removeActionWithId(int i, View view) {
        List actionList = getActionList(view);
        for (int i2 = 0; i2 < actionList.size(); i2++) {
            if (((AccessibilityNodeInfoCompat.AccessibilityActionCompat) actionList.get(i2)).getId() == i) {
                actionList.remove(i2);
                return;
            }
        }
    }

    private static List getActionList(View view) {
        ArrayList arrayList = (ArrayList) view.getTag(R$id.tag_accessibility_actions);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(R$id.tag_accessibility_actions, arrayList2);
        return arrayList2;
    }

    public static void setStateDescription(View view, CharSequence charSequence) {
        stateDescriptionProperty().set(view, charSequence);
    }

    public static CharSequence getStateDescription(View view) {
        return (CharSequence) stateDescriptionProperty().get(view);
    }

    public static int getLayoutDirection(View view) {
        return view.getLayoutDirection();
    }

    public static ViewParent getParentForAccessibility(View view) {
        return view.getParentForAccessibility();
    }

    public static void setAccessibilityLiveRegion(View view, int i) {
        view.setAccessibilityLiveRegion(i);
    }

    public static int getPaddingStart(View view) {
        return view.getPaddingStart();
    }

    public static int getPaddingEnd(View view) {
        return view.getPaddingEnd();
    }

    public static void setPaddingRelative(View view, int i, int i2, int i3, int i4) {
        view.setPaddingRelative(i, i2, i3, i4);
    }

    public static int getMinimumWidth(View view) {
        return view.getMinimumWidth();
    }

    public static int getMinimumHeight(View view) {
        return view.getMinimumHeight();
    }

    public static ViewPropertyAnimatorCompat animate(View view) {
        if (sViewPropertyAnimatorMap == null) {
            sViewPropertyAnimatorMap = new WeakHashMap();
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = (ViewPropertyAnimatorCompat) sViewPropertyAnimatorMap.get(view);
        if (viewPropertyAnimatorCompat != null) {
            return viewPropertyAnimatorCompat;
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2 = new ViewPropertyAnimatorCompat(view);
        sViewPropertyAnimatorMap.put(view, viewPropertyAnimatorCompat2);
        return viewPropertyAnimatorCompat2;
    }

    public static void setElevation(View view, float f) {
        Api21Impl.setElevation(view, f);
    }

    public static float getElevation(View view) {
        return Api21Impl.getElevation(view);
    }

    public static void setTranslationZ(View view, float f) {
        Api21Impl.setTranslationZ(view, f);
    }

    public static void setTransitionName(View view, String str) {
        Api21Impl.setTransitionName(view, str);
    }

    public static String getTransitionName(View view) {
        return Api21Impl.getTransitionName(view);
    }

    public static int getWindowSystemUiVisibility(View view) {
        return view.getWindowSystemUiVisibility();
    }

    public static void requestApplyInsets(View view) {
        Api20Impl.requestApplyInsets(view);
    }

    public static boolean getFitsSystemWindows(View view) {
        return view.getFitsSystemWindows();
    }

    public static void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        Api21Impl.setOnApplyWindowInsetsListener(view, onApplyWindowInsetsListener);
    }

    public static WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        WindowInsets windowInsets = windowInsetsCompat.toWindowInsets();
        if (windowInsets != null) {
            WindowInsets onApplyWindowInsets = Api20Impl.onApplyWindowInsets(view, windowInsets);
            if (!onApplyWindowInsets.equals(windowInsets)) {
                return WindowInsetsCompat.toWindowInsetsCompat(onApplyWindowInsets, view);
            }
        }
        return windowInsetsCompat;
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        WindowInsets windowInsets = windowInsetsCompat.toWindowInsets();
        if (windowInsets != null) {
            WindowInsets dispatchApplyWindowInsets = Api20Impl.dispatchApplyWindowInsets(view, windowInsets);
            if (!dispatchApplyWindowInsets.equals(windowInsets)) {
                return WindowInsetsCompat.toWindowInsetsCompat(dispatchApplyWindowInsets, view);
            }
        }
        return windowInsetsCompat;
    }

    public static WindowInsetsCompat getRootWindowInsets(View view) {
        return Api23Impl.getRootWindowInsets(view);
    }

    public static WindowInsetsCompat computeSystemWindowInsets(View view, WindowInsetsCompat windowInsetsCompat, Rect rect) {
        return Api21Impl.computeSystemWindowInsets(view, windowInsetsCompat, rect);
    }

    public static void setWindowInsetsAnimationCallback(View view, WindowInsetsAnimationCompat.Callback callback) {
        WindowInsetsAnimationCompat.setCallback(view, callback);
    }

    public static String[] getOnReceiveContentMimeTypes(View view) {
        if (Build.VERSION.SDK_INT >= 31) {
            return Api31Impl.getReceiveContentMimeTypes(view);
        }
        return (String[]) view.getTag(R$id.tag_on_receive_content_mime_types);
    }

    public static ContentInfoCompat performReceiveContent(View view, ContentInfoCompat contentInfoCompat) {
        if (Log.isLoggable("ViewCompat", 3)) {
            Log.d("ViewCompat", "performReceiveContent: " + contentInfoCompat + ", view=" + view.getClass().getSimpleName() + "[" + view.getId() + "]");
        }
        if (Build.VERSION.SDK_INT >= 31) {
            return Api31Impl.performReceiveContent(view, contentInfoCompat);
        }
        OnReceiveContentListener onReceiveContentListener = (OnReceiveContentListener) view.getTag(R$id.tag_on_receive_content_listener);
        if (onReceiveContentListener == null) {
            return getFallback(view).onReceiveContent(contentInfoCompat);
        }
        ContentInfoCompat onReceiveContent = onReceiveContentListener.onReceiveContent(view, contentInfoCompat);
        if (onReceiveContent == null) {
            return null;
        }
        return getFallback(view).onReceiveContent(onReceiveContent);
    }

    private static OnReceiveContentViewBehavior getFallback(View view) {
        if (view instanceof OnReceiveContentViewBehavior) {
            return (OnReceiveContentViewBehavior) view;
        }
        return NO_OP_ON_RECEIVE_CONTENT_VIEW_BEHAVIOR;
    }

    private static final class Api31Impl {
        public static void setOnReceiveContentListener(View view, String[] strArr, OnReceiveContentListener onReceiveContentListener) {
            if (onReceiveContentListener == null) {
                view.setOnReceiveContentListener(strArr, (OnReceiveContentListener) null);
            } else {
                view.setOnReceiveContentListener(strArr, new OnReceiveContentListenerAdapter(onReceiveContentListener));
            }
        }

        public static String[] getReceiveContentMimeTypes(View view) {
            return view.getReceiveContentMimeTypes();
        }

        public static ContentInfoCompat performReceiveContent(View view, ContentInfoCompat contentInfoCompat) {
            ContentInfo contentInfo = contentInfoCompat.toContentInfo();
            ContentInfo performReceiveContent = view.performReceiveContent(contentInfo);
            if (performReceiveContent == null) {
                return null;
            }
            if (performReceiveContent == contentInfo) {
                return contentInfoCompat;
            }
            return ContentInfoCompat.toContentInfoCompat(performReceiveContent);
        }
    }

    private static final class OnReceiveContentListenerAdapter implements OnReceiveContentListener {
        private final OnReceiveContentListener mJetpackListener;

        OnReceiveContentListenerAdapter(OnReceiveContentListener onReceiveContentListener) {
            this.mJetpackListener = onReceiveContentListener;
        }

        public ContentInfo onReceiveContent(View view, ContentInfo contentInfo) {
            ContentInfoCompat contentInfoCompat = ContentInfoCompat.toContentInfoCompat(contentInfo);
            ContentInfoCompat onReceiveContent = this.mJetpackListener.onReceiveContent(view, contentInfoCompat);
            if (onReceiveContent == null) {
                return null;
            }
            if (onReceiveContent == contentInfoCompat) {
                return contentInfo;
            }
            return onReceiveContent.toContentInfo();
        }
    }

    public static boolean hasOverlappingRendering(View view) {
        return view.hasOverlappingRendering();
    }

    public static boolean isPaddingRelative(View view) {
        return view.isPaddingRelative();
    }

    public static void setBackground(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    public static ColorStateList getBackgroundTintList(View view) {
        return Api21Impl.getBackgroundTintList(view);
    }

    public static void setBackgroundTintList(View view, ColorStateList colorStateList) {
        Api21Impl.setBackgroundTintList(view, colorStateList);
    }

    public static PorterDuff.Mode getBackgroundTintMode(View view) {
        return Api21Impl.getBackgroundTintMode(view);
    }

    public static void setBackgroundTintMode(View view, PorterDuff.Mode mode) {
        Api21Impl.setBackgroundTintMode(view, mode);
    }

    public static void setNestedScrollingEnabled(View view, boolean z) {
        Api21Impl.setNestedScrollingEnabled(view, z);
    }

    public static boolean isNestedScrollingEnabled(View view) {
        return Api21Impl.isNestedScrollingEnabled(view);
    }

    public static void stopNestedScroll(View view) {
        Api21Impl.stopNestedScroll(view);
    }

    public static boolean isLaidOut(View view) {
        return view.isLaidOut();
    }

    public static float getZ(View view) {
        return Api21Impl.getZ(view);
    }

    public static void offsetTopAndBottom(View view, int i) {
        view.offsetTopAndBottom(i);
    }

    public static void offsetLeftAndRight(View view, int i) {
        view.offsetLeftAndRight(i);
    }

    public static void setClipBounds(View view, Rect rect) {
        view.setClipBounds(rect);
    }

    public static Rect getClipBounds(View view) {
        return view.getClipBounds();
    }

    public static boolean isAttachedToWindow(View view) {
        return view.isAttachedToWindow();
    }

    public static boolean hasOnClickListeners(View view) {
        return view.hasOnClickListeners();
    }

    public static void setScrollIndicators(View view, int i, int i2) {
        Api23Impl.setScrollIndicators(view, i, i2);
    }

    public static Display getDisplay(View view) {
        return view.getDisplay();
    }

    public static int generateViewId() {
        return View.generateViewId();
    }

    static boolean dispatchUnhandledKeyEventBeforeHierarchy(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return UnhandledKeyEventManager.at(view).preDispatch(keyEvent);
    }

    static boolean dispatchUnhandledKeyEventBeforeCallback(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return UnhandledKeyEventManager.at(view).dispatch(view, keyEvent);
    }

    public static void setScreenReaderFocusable(View view, boolean z) {
        screenReaderFocusableProperty().set(view, Boolean.valueOf(z));
    }

    public static boolean isScreenReaderFocusable(View view) {
        Boolean bool = (Boolean) screenReaderFocusableProperty().get(view);
        return bool != null && bool.booleanValue();
    }

    private static AccessibilityViewProperty screenReaderFocusableProperty() {
        return new AccessibilityViewProperty(R$id.tag_screen_reader_focusable, Boolean.class, 28) {
            /* access modifiers changed from: package-private */
            public Boolean frameworkGet(View view) {
                return Boolean.valueOf(Api28Impl.isScreenReaderFocusable(view));
            }

            /* access modifiers changed from: package-private */
            public void frameworkSet(View view, Boolean bool) {
                Api28Impl.setScreenReaderFocusable(view, bool.booleanValue());
            }

            /* access modifiers changed from: package-private */
            public boolean shouldUpdate(Boolean bool, Boolean bool2) {
                return !booleanNullToFalseEquals(bool, bool2);
            }
        };
    }

    public static void setAccessibilityPaneTitle(View view, CharSequence charSequence) {
        paneTitleProperty().set(view, charSequence);
        if (charSequence != null) {
            sAccessibilityPaneVisibilityManager.addAccessibilityPane(view);
        } else {
            sAccessibilityPaneVisibilityManager.removeAccessibilityPane(view);
        }
    }

    public static CharSequence getAccessibilityPaneTitle(View view) {
        return (CharSequence) paneTitleProperty().get(view);
    }

    private static AccessibilityViewProperty paneTitleProperty() {
        return new AccessibilityViewProperty(R$id.tag_accessibility_pane_title, CharSequence.class, 8, 28) {
            /* access modifiers changed from: package-private */
            public CharSequence frameworkGet(View view) {
                return Api28Impl.getAccessibilityPaneTitle(view);
            }

            /* access modifiers changed from: package-private */
            public void frameworkSet(View view, CharSequence charSequence) {
                Api28Impl.setAccessibilityPaneTitle(view, charSequence);
            }

            /* access modifiers changed from: package-private */
            public boolean shouldUpdate(CharSequence charSequence, CharSequence charSequence2) {
                return !TextUtils.equals(charSequence, charSequence2);
            }
        };
    }

    private static AccessibilityViewProperty stateDescriptionProperty() {
        return new AccessibilityViewProperty(R$id.tag_state_description, CharSequence.class, 64, 30) {
            /* access modifiers changed from: package-private */
            public CharSequence frameworkGet(View view) {
                return Api30Impl.getStateDescription(view);
            }

            /* access modifiers changed from: package-private */
            public void frameworkSet(View view, CharSequence charSequence) {
                Api30Impl.setStateDescription(view, charSequence);
            }

            /* access modifiers changed from: package-private */
            public boolean shouldUpdate(CharSequence charSequence, CharSequence charSequence2) {
                return !TextUtils.equals(charSequence, charSequence2);
            }
        };
    }

    public static boolean isAccessibilityHeading(View view) {
        Boolean bool = (Boolean) accessibilityHeadingProperty().get(view);
        return bool != null && bool.booleanValue();
    }

    public static void setAccessibilityHeading(View view, boolean z) {
        accessibilityHeadingProperty().set(view, Boolean.valueOf(z));
    }

    private static AccessibilityViewProperty accessibilityHeadingProperty() {
        return new AccessibilityViewProperty(R$id.tag_accessibility_heading, Boolean.class, 28) {
            /* access modifiers changed from: package-private */
            public Boolean frameworkGet(View view) {
                return Boolean.valueOf(Api28Impl.isAccessibilityHeading(view));
            }

            /* access modifiers changed from: package-private */
            public void frameworkSet(View view, Boolean bool) {
                Api28Impl.setAccessibilityHeading(view, bool.booleanValue());
            }

            /* access modifiers changed from: package-private */
            public boolean shouldUpdate(Boolean bool, Boolean bool2) {
                return !booleanNullToFalseEquals(bool, bool2);
            }
        };
    }

    static abstract class AccessibilityViewProperty {
        private final int mContentChangeType;
        private final int mFrameworkMinimumSdk;
        private final int mTagKey;
        private final Class mType;

        /* access modifiers changed from: package-private */
        public abstract Object frameworkGet(View view);

        /* access modifiers changed from: package-private */
        public abstract void frameworkSet(View view, Object obj);

        /* access modifiers changed from: package-private */
        public abstract boolean shouldUpdate(Object obj, Object obj2);

        AccessibilityViewProperty(int i, Class cls, int i2) {
            this(i, cls, 0, i2);
        }

        AccessibilityViewProperty(int i, Class cls, int i2, int i3) {
            this.mTagKey = i;
            this.mType = cls;
            this.mContentChangeType = i2;
            this.mFrameworkMinimumSdk = i3;
        }

        /* access modifiers changed from: package-private */
        public void set(View view, Object obj) {
            if (frameworkAvailable()) {
                frameworkSet(view, obj);
            } else if (shouldUpdate(get(view), obj)) {
                ViewCompat.ensureAccessibilityDelegateCompat(view);
                view.setTag(this.mTagKey, obj);
                ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(view, this.mContentChangeType);
            }
        }

        /* access modifiers changed from: package-private */
        public Object get(View view) {
            if (frameworkAvailable()) {
                return frameworkGet(view);
            }
            Object tag = view.getTag(this.mTagKey);
            if (this.mType.isInstance(tag)) {
                return tag;
            }
            return null;
        }

        private boolean frameworkAvailable() {
            return Build.VERSION.SDK_INT >= this.mFrameworkMinimumSdk;
        }

        /* access modifiers changed from: package-private */
        public boolean booleanNullToFalseEquals(Boolean bool, Boolean bool2) {
            if ((bool != null && bool.booleanValue()) == (bool2 != null && bool2.booleanValue())) {
                return true;
            }
            return false;
        }
    }

    static void notifyViewAccessibilityStateChangedIfNeeded(View view, int i) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            boolean z = getAccessibilityPaneTitle(view) != null && view.isShown() && view.getWindowVisibility() == 0;
            int i2 = 32;
            if (view.getAccessibilityLiveRegion() != 0 || z) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain();
                if (!z) {
                    i2 = b.u;
                }
                obtain.setEventType(i2);
                obtain.setContentChangeTypes(i);
                if (z) {
                    obtain.getText().add(getAccessibilityPaneTitle(view));
                    setImportantForAccessibilityIfNeeded(view);
                }
                view.sendAccessibilityEventUnchecked(obtain);
            } else if (i == 32) {
                AccessibilityEvent obtain2 = AccessibilityEvent.obtain();
                view.onInitializeAccessibilityEvent(obtain2);
                obtain2.setEventType(32);
                obtain2.setContentChangeTypes(i);
                obtain2.setSource(view);
                view.onPopulateAccessibilityEvent(obtain2);
                obtain2.getText().add(getAccessibilityPaneTitle(view));
                accessibilityManager.sendAccessibilityEvent(obtain2);
            } else if (view.getParent() != null) {
                try {
                    view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i);
                } catch (AbstractMethodError e) {
                    Log.e("ViewCompat", view.getParent().getClass().getSimpleName() + " does not fully implement ViewParent", e);
                }
            }
        }
    }

    private static void setImportantForAccessibilityIfNeeded(View view) {
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
        }
    }

    static class AccessibilityPaneVisibilityManager implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {
        private final WeakHashMap mPanesToVisible = new WeakHashMap();

        public void onViewDetachedFromWindow(View view) {
        }

        AccessibilityPaneVisibilityManager() {
        }

        public void onGlobalLayout() {
            if (Build.VERSION.SDK_INT < 28) {
                for (Map.Entry checkPaneVisibility : this.mPanesToVisible.entrySet()) {
                    checkPaneVisibility(checkPaneVisibility);
                }
            }
        }

        public void onViewAttachedToWindow(View view) {
            registerForLayoutCallback(view);
        }

        /* access modifiers changed from: package-private */
        public void addAccessibilityPane(View view) {
            this.mPanesToVisible.put(view, Boolean.valueOf(view.isShown() && view.getWindowVisibility() == 0));
            view.addOnAttachStateChangeListener(this);
            if (view.isAttachedToWindow()) {
                registerForLayoutCallback(view);
            }
        }

        /* access modifiers changed from: package-private */
        public void removeAccessibilityPane(View view) {
            this.mPanesToVisible.remove(view);
            view.removeOnAttachStateChangeListener(this);
            unregisterForLayoutCallback(view);
        }

        private void checkPaneVisibility(Map.Entry entry) {
            View view = (View) entry.getKey();
            boolean booleanValue = ((Boolean) entry.getValue()).booleanValue();
            boolean z = view.isShown() && view.getWindowVisibility() == 0;
            if (booleanValue != z) {
                ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(view, z ? 16 : 32);
                entry.setValue(Boolean.valueOf(z));
            }
        }

        private void registerForLayoutCallback(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        private void unregisterForLayoutCallback(View view) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    static class UnhandledKeyEventManager {
        private static final ArrayList sViewsWithListeners = new ArrayList();
        private SparseArray mCapturedKeys = null;
        private WeakReference mLastDispatchedPreViewKeyEvent = null;
        private WeakHashMap mViewsContainingListeners = null;

        UnhandledKeyEventManager() {
        }

        private SparseArray getCapturedKeys() {
            if (this.mCapturedKeys == null) {
                this.mCapturedKeys = new SparseArray();
            }
            return this.mCapturedKeys;
        }

        static UnhandledKeyEventManager at(View view) {
            UnhandledKeyEventManager unhandledKeyEventManager = (UnhandledKeyEventManager) view.getTag(R$id.tag_unhandled_key_event_manager);
            if (unhandledKeyEventManager != null) {
                return unhandledKeyEventManager;
            }
            UnhandledKeyEventManager unhandledKeyEventManager2 = new UnhandledKeyEventManager();
            view.setTag(R$id.tag_unhandled_key_event_manager, unhandledKeyEventManager2);
            return unhandledKeyEventManager2;
        }

        /* access modifiers changed from: package-private */
        public boolean dispatch(View view, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0) {
                recalcViewsWithUnhandled();
            }
            View dispatchInOrder = dispatchInOrder(view, keyEvent);
            if (keyEvent.getAction() == 0) {
                int keyCode = keyEvent.getKeyCode();
                if (dispatchInOrder != null && !KeyEvent.isModifierKey(keyCode)) {
                    getCapturedKeys().put(keyCode, new WeakReference(dispatchInOrder));
                }
            }
            return dispatchInOrder != null;
        }

        private View dispatchInOrder(View view, KeyEvent keyEvent) {
            WeakHashMap weakHashMap = this.mViewsContainingListeners;
            if (weakHashMap != null && weakHashMap.containsKey(view)) {
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        View dispatchInOrder = dispatchInOrder(viewGroup.getChildAt(childCount), keyEvent);
                        if (dispatchInOrder != null) {
                            return dispatchInOrder;
                        }
                    }
                }
                if (onUnhandledKeyEvent(view, keyEvent)) {
                    return view;
                }
            }
            return null;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.lang.ref.WeakReference} */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean preDispatch(android.view.KeyEvent r6) {
            /*
                r5 = this;
                java.lang.ref.WeakReference r0 = r5.mLastDispatchedPreViewKeyEvent
                r1 = 0
                if (r0 == 0) goto L_0x000c
                java.lang.Object r0 = r0.get()
                if (r0 != r6) goto L_0x000c
                return r1
            L_0x000c:
                java.lang.ref.WeakReference r0 = new java.lang.ref.WeakReference
                r0.<init>(r6)
                r5.mLastDispatchedPreViewKeyEvent = r0
                android.util.SparseArray r0 = r5.getCapturedKeys()
                int r2 = r6.getAction()
                r3 = 1
                if (r2 != r3) goto L_0x0032
                int r2 = r6.getKeyCode()
                int r2 = r0.indexOfKey(r2)
                if (r2 < 0) goto L_0x0032
                java.lang.Object r4 = r0.valueAt(r2)
                java.lang.ref.WeakReference r4 = (java.lang.ref.WeakReference) r4
                r0.removeAt(r2)
                goto L_0x0033
            L_0x0032:
                r4 = 0
            L_0x0033:
                if (r4 != 0) goto L_0x0040
                int r2 = r6.getKeyCode()
                java.lang.Object r0 = r0.get(r2)
                r4 = r0
                java.lang.ref.WeakReference r4 = (java.lang.ref.WeakReference) r4
            L_0x0040:
                if (r4 == 0) goto L_0x0054
                java.lang.Object r0 = r4.get()
                android.view.View r0 = (android.view.View) r0
                if (r0 == 0) goto L_0x0053
                boolean r1 = r0.isAttachedToWindow()
                if (r1 == 0) goto L_0x0053
                r5.onUnhandledKeyEvent(r0, r6)
            L_0x0053:
                return r3
            L_0x0054:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.ViewCompat.UnhandledKeyEventManager.preDispatch(android.view.KeyEvent):boolean");
        }

        private boolean onUnhandledKeyEvent(View view, KeyEvent keyEvent) {
            int size;
            ArrayList arrayList = (ArrayList) view.getTag(R$id.tag_unhandled_key_listeners);
            if (arrayList == null || arrayList.size() - 1 < 0) {
                return false;
            }
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(arrayList.get(size));
            throw null;
        }

        private void recalcViewsWithUnhandled() {
            WeakHashMap weakHashMap = this.mViewsContainingListeners;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList arrayList = sViewsWithListeners;
            if (!arrayList.isEmpty()) {
                synchronized (arrayList) {
                    try {
                        if (this.mViewsContainingListeners == null) {
                            this.mViewsContainingListeners = new WeakHashMap();
                        }
                        for (int size = arrayList.size() - 1; size >= 0; size--) {
                            ArrayList arrayList2 = sViewsWithListeners;
                            View view = (View) ((WeakReference) arrayList2.get(size)).get();
                            if (view == null) {
                                arrayList2.remove(size);
                            } else {
                                this.mViewsContainingListeners.put(view, Boolean.TRUE);
                                for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                                    this.mViewsContainingListeners.put((View) parent, Boolean.TRUE);
                                }
                            }
                        }
                    } finally {
                    }
                }
            }
        }
    }

    private static class Api21Impl {
        public static WindowInsetsCompat getRootWindowInsets(View view) {
            return WindowInsetsCompat.Api21ReflectionHolder.getRootWindowInsets(view);
        }

        static WindowInsetsCompat computeSystemWindowInsets(View view, WindowInsetsCompat windowInsetsCompat, Rect rect) {
            WindowInsets windowInsets = windowInsetsCompat.toWindowInsets();
            if (windowInsets != null) {
                return WindowInsetsCompat.toWindowInsetsCompat(view.computeSystemWindowInsets(windowInsets, rect), view);
            }
            rect.setEmpty();
            return windowInsetsCompat;
        }

        static void setOnApplyWindowInsetsListener(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
            if (Build.VERSION.SDK_INT < 30) {
                view.setTag(R$id.tag_on_apply_window_listener, onApplyWindowInsetsListener);
            }
            if (onApplyWindowInsetsListener == null) {
                view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) view.getTag(R$id.tag_window_insets_animation_callback));
            } else {
                view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                    WindowInsetsCompat mLastInsets = null;

                    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                        WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(windowInsets, view);
                        int i = Build.VERSION.SDK_INT;
                        if (i < 30) {
                            Api21Impl.callCompatInsetAnimationCallback(windowInsets, view);
                            if (windowInsetsCompat.equals(this.mLastInsets)) {
                                return onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsetsCompat).toWindowInsets();
                            }
                        }
                        this.mLastInsets = windowInsetsCompat;
                        WindowInsetsCompat onApplyWindowInsets = onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsetsCompat);
                        if (i >= 30) {
                            return onApplyWindowInsets.toWindowInsets();
                        }
                        ViewCompat.requestApplyInsets(view);
                        return onApplyWindowInsets.toWindowInsets();
                    }
                });
            }
        }

        static void callCompatInsetAnimationCallback(WindowInsets windowInsets, View view) {
            View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = (View.OnApplyWindowInsetsListener) view.getTag(R$id.tag_window_insets_animation_callback);
            if (onApplyWindowInsetsListener != null) {
                onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsets);
            }
        }

        static boolean dispatchNestedFling(View view, float f, float f2, boolean z) {
            return view.dispatchNestedFling(f, f2, z);
        }

        static boolean dispatchNestedPreFling(View view, float f, float f2) {
            return view.dispatchNestedPreFling(f, f2);
        }

        static float getZ(View view) {
            return view.getZ();
        }

        static void setZ(View view, float f) {
            view.setZ(f);
        }

        static void setElevation(View view, float f) {
            view.setElevation(f);
        }

        static void setTranslationZ(View view, float f) {
            view.setTranslationZ(f);
        }

        static float getTranslationZ(View view) {
            return view.getTranslationZ();
        }

        static void setTransitionName(View view, String str) {
            view.setTransitionName(str);
        }

        static boolean isImportantForAccessibility(View view) {
            return view.isImportantForAccessibility();
        }

        static float getElevation(View view) {
            return view.getElevation();
        }

        static String getTransitionName(View view) {
            return view.getTransitionName();
        }

        static void setBackgroundTintList(View view, ColorStateList colorStateList) {
            view.setBackgroundTintList(colorStateList);
        }

        static ColorStateList getBackgroundTintList(View view) {
            return view.getBackgroundTintList();
        }

        static PorterDuff.Mode getBackgroundTintMode(View view) {
            return view.getBackgroundTintMode();
        }

        static void setBackgroundTintMode(View view, PorterDuff.Mode mode) {
            view.setBackgroundTintMode(mode);
        }

        static void setNestedScrollingEnabled(View view, boolean z) {
            view.setNestedScrollingEnabled(z);
        }

        static boolean isNestedScrollingEnabled(View view) {
            return view.isNestedScrollingEnabled();
        }

        static boolean startNestedScroll(View view, int i) {
            return view.startNestedScroll(i);
        }

        static void stopNestedScroll(View view) {
            view.stopNestedScroll();
        }

        static boolean hasNestedScrollingParent(View view) {
            return view.hasNestedScrollingParent();
        }

        static boolean dispatchNestedScroll(View view, int i, int i2, int i3, int i4, int[] iArr) {
            return view.dispatchNestedScroll(i, i2, i3, i4, iArr);
        }

        static boolean dispatchNestedPreScroll(View view, int i, int i2, int[] iArr, int[] iArr2) {
            return view.dispatchNestedPreScroll(i, i2, iArr, iArr2);
        }
    }

    private static class Api23Impl {
        public static WindowInsetsCompat getRootWindowInsets(View view) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            if (rootWindowInsets == null) {
                return null;
            }
            WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(rootWindowInsets);
            windowInsetsCompat.setRootWindowInsets(windowInsetsCompat);
            windowInsetsCompat.copyRootViewBounds(view.getRootView());
            return windowInsetsCompat;
        }

        static void setScrollIndicators(View view, int i) {
            view.setScrollIndicators(i);
        }

        static void setScrollIndicators(View view, int i, int i2) {
            view.setScrollIndicators(i, i2);
        }

        static int getScrollIndicators(View view) {
            return view.getScrollIndicators();
        }
    }

    private static class Api29Impl {
        static void saveAttributeDataForStyleable(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i, int i2) {
            view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i, i2);
        }

        static View.AccessibilityDelegate getAccessibilityDelegate(View view) {
            return view.getAccessibilityDelegate();
        }

        static void setSystemGestureExclusionRects(View view, List<Rect> list) {
            view.setSystemGestureExclusionRects(list);
        }

        static List<Rect> getSystemGestureExclusionRects(View view) {
            return view.getSystemGestureExclusionRects();
        }

        static ContentCaptureSession getContentCaptureSession(View view) {
            return view.getContentCaptureSession();
        }

        static void setContentCaptureSession(View view, ContentCaptureSessionCompat contentCaptureSessionCompat) {
            view.setContentCaptureSession((ContentCaptureSession) null);
        }
    }

    private static class Api30Impl {
        static void setStateDescription(View view, CharSequence charSequence) {
            view.setStateDescription(charSequence);
        }

        static CharSequence getStateDescription(View view) {
            return view.getStateDescription();
        }

        static void setImportantForContentCapture(View view, int i) {
            view.setImportantForContentCapture(i);
        }

        static boolean isImportantForContentCapture(View view) {
            return view.isImportantForContentCapture();
        }

        static int getImportantForContentCapture(View view) {
            return view.getImportantForContentCapture();
        }
    }

    static class Api26Impl {
        static void setAutofillHints(View view, String... strArr) {
            view.setAutofillHints(strArr);
        }

        static void setTooltipText(View view, CharSequence charSequence) {
            view.setTooltipText(charSequence);
        }

        static int getNextClusterForwardId(View view) {
            return view.getNextClusterForwardId();
        }

        static void setNextClusterForwardId(View view, int i) {
            view.setNextClusterForwardId(i);
        }

        static boolean isKeyboardNavigationCluster(View view) {
            return view.isKeyboardNavigationCluster();
        }

        static void setKeyboardNavigationCluster(View view, boolean z) {
            view.setKeyboardNavigationCluster(z);
        }

        static boolean isFocusedByDefault(View view) {
            return view.isFocusedByDefault();
        }

        static void setFocusedByDefault(View view, boolean z) {
            view.setFocusedByDefault(z);
        }

        static View keyboardNavigationClusterSearch(View view, View view2, int i) {
            return view.keyboardNavigationClusterSearch(view2, i);
        }

        static void addKeyboardNavigationClusters(View view, Collection<View> collection, int i) {
            view.addKeyboardNavigationClusters(collection, i);
        }

        static boolean restoreDefaultFocus(View view) {
            return view.restoreDefaultFocus();
        }

        static boolean hasExplicitFocusable(View view) {
            return view.hasExplicitFocusable();
        }

        static int getImportantForAutofill(View view) {
            return view.getImportantForAutofill();
        }

        static void setImportantForAutofill(View view, int i) {
            view.setImportantForAutofill(i);
        }

        static boolean isImportantForAutofill(View view) {
            return view.isImportantForAutofill();
        }

        public static AutofillId getAutofillId(View view) {
            return view.getAutofillId();
        }
    }

    static class Api28Impl {
        static <T> T requireViewById(View view, int i) {
            return view.requireViewById(i);
        }

        static CharSequence getAccessibilityPaneTitle(View view) {
            return view.getAccessibilityPaneTitle();
        }

        static void setAccessibilityPaneTitle(View view, CharSequence charSequence) {
            view.setAccessibilityPaneTitle(charSequence);
        }

        static void setAccessibilityHeading(View view, boolean z) {
            view.setAccessibilityHeading(z);
        }

        static boolean isAccessibilityHeading(View view) {
            return view.isAccessibilityHeading();
        }

        static boolean isScreenReaderFocusable(View view) {
            return view.isScreenReaderFocusable();
        }

        static void setScreenReaderFocusable(View view, boolean z) {
            view.setScreenReaderFocusable(z);
        }

        static void addOnUnhandledKeyEventListener(View view, OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) view.getTag(R$id.tag_unhandled_key_listeners);
            if (simpleArrayMap == null) {
                simpleArrayMap = new SimpleArrayMap();
                view.setTag(R$id.tag_unhandled_key_listeners, simpleArrayMap);
            }
            Objects.requireNonNull(onUnhandledKeyEventListenerCompat);
            ViewCompat$Api28Impl$$ExternalSyntheticLambda0 viewCompat$Api28Impl$$ExternalSyntheticLambda0 = new ViewCompat$Api28Impl$$ExternalSyntheticLambda0(onUnhandledKeyEventListenerCompat);
            simpleArrayMap.put(onUnhandledKeyEventListenerCompat, viewCompat$Api28Impl$$ExternalSyntheticLambda0);
            view.addOnUnhandledKeyEventListener(viewCompat$Api28Impl$$ExternalSyntheticLambda0);
        }

        static void removeOnUnhandledKeyEventListener(View view, OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
            View.OnUnhandledKeyEventListener onUnhandledKeyEventListener;
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) view.getTag(R$id.tag_unhandled_key_listeners);
            if (simpleArrayMap != null && (onUnhandledKeyEventListener = (View.OnUnhandledKeyEventListener) simpleArrayMap.get(onUnhandledKeyEventListenerCompat)) != null) {
                view.removeOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
            }
        }

        public static void setAutofillId(View view, AutofillIdCompat autofillIdCompat) {
            view.setAutofillId((AutofillId) null);
        }
    }

    static class Api20Impl {
        static void requestApplyInsets(View view) {
            view.requestApplyInsets();
        }

        static WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            return view.onApplyWindowInsets(windowInsets);
        }

        static WindowInsets dispatchApplyWindowInsets(View view, WindowInsets windowInsets) {
            return view.dispatchApplyWindowInsets(windowInsets);
        }
    }
}
