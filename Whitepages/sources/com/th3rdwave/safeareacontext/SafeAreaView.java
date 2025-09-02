package com.th3rdwave.safeareacontext;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;

public final class SafeAreaView extends ReactViewGroup implements ViewTreeObserver.OnPreDrawListener {
    private SafeAreaViewEdges mEdges;
    private EdgeInsets mInsets;
    private SafeAreaViewMode mMode = SafeAreaViewMode.PADDING;
    private View mProviderView;
    private StateWrapper mStateWrapper;

    public SafeAreaView(Context context) {
        super(context);
    }

    public final StateWrapper getStateWrapper() {
        return this.mStateWrapper;
    }

    public final void setStateWrapper(StateWrapper stateWrapper) {
        this.mStateWrapper = stateWrapper;
    }

    private final void updateInsets() {
        EdgeInsets edgeInsets = this.mInsets;
        if (edgeInsets != null) {
            SafeAreaViewEdges safeAreaViewEdges = this.mEdges;
            if (safeAreaViewEdges == null) {
                SafeAreaViewEdgeModes safeAreaViewEdgeModes = SafeAreaViewEdgeModes.ADDITIVE;
                safeAreaViewEdges = new SafeAreaViewEdges(safeAreaViewEdgeModes, safeAreaViewEdgeModes, safeAreaViewEdgeModes, safeAreaViewEdgeModes);
            }
            StateWrapper stateWrapper = getStateWrapper();
            if (stateWrapper != null) {
                WritableMap createMap = Arguments.createMap();
                createMap.putMap("insets", SerializationUtilsKt.edgeInsetsToJsMap(edgeInsets));
                stateWrapper.updateState(createMap);
                return;
            }
            SafeAreaViewLocalData safeAreaViewLocalData = new SafeAreaViewLocalData(edgeInsets, this.mMode, safeAreaViewEdges);
            ReactContext reactContext = UIManagerHelperCompatKt.getReactContext(this);
            UIManagerModule uIManagerModule = (UIManagerModule) reactContext.getNativeModule(UIManagerModule.class);
            if (uIManagerModule != null) {
                uIManagerModule.setViewLocalData(getId(), safeAreaViewLocalData);
                reactContext.runOnNativeModulesQueueThread(new SafeAreaView$$ExternalSyntheticLambda0(uIManagerModule));
                waitForReactLayout();
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void updateInsets$lambda$0(UIManagerModule uIManagerModule) {
        uIManagerModule.getUIImplementation().dispatchViewUpdates(-1);
    }

    private final void waitForReactLayout() {
        Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition newCondition = reentrantLock.newCondition();
        long nanoTime = System.nanoTime();
        UIManagerHelperCompatKt.getReactContext(this).runOnNativeModulesQueueThread(new SafeAreaView$$ExternalSyntheticLambda1(reentrantLock, ref$BooleanRef, newCondition));
        reentrantLock.lock();
        long j = 0;
        while (!ref$BooleanRef.element && j < 500000000) {
            try {
                newCondition.awaitNanos(500000000);
            } catch (InterruptedException unused) {
                ref$BooleanRef.element = true;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
            j += System.nanoTime() - nanoTime;
        }
        Unit unit = Unit.INSTANCE;
        reentrantLock.unlock();
        if (j >= 500000000) {
            Log.w("SafeAreaView", "Timed out waiting for layout.");
        }
    }

    /* access modifiers changed from: private */
    public static final void waitForReactLayout$lambda$2(ReentrantLock reentrantLock, Ref$BooleanRef ref$BooleanRef, Condition condition) {
        reentrantLock.lock();
        try {
            if (!ref$BooleanRef.element) {
                ref$BooleanRef.element = true;
                condition.signal();
            }
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final void setMode(SafeAreaViewMode safeAreaViewMode) {
        Intrinsics.checkNotNullParameter(safeAreaViewMode, "mode");
        this.mMode = safeAreaViewMode;
        updateInsets();
    }

    public final void setEdges(SafeAreaViewEdges safeAreaViewEdges) {
        Intrinsics.checkNotNullParameter(safeAreaViewEdges, "edges");
        this.mEdges = safeAreaViewEdges;
        updateInsets();
    }

    private final boolean maybeUpdateInsets() {
        EdgeInsets safeAreaInsets;
        View view = this.mProviderView;
        if (view == null || (safeAreaInsets = SafeAreaUtilsKt.getSafeAreaInsets(view)) == null || Intrinsics.areEqual((Object) this.mInsets, (Object) safeAreaInsets)) {
            return false;
        }
        this.mInsets = safeAreaInsets;
        updateInsets();
        return true;
    }

    private final View findProvider() {
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof SafeAreaProvider) {
                return (View) parent;
            }
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        ViewTreeObserver viewTreeObserver;
        super.onAttachedToWindow();
        View findProvider = findProvider();
        this.mProviderView = findProvider;
        if (!(findProvider == null || (viewTreeObserver = findProvider.getViewTreeObserver()) == null)) {
            viewTreeObserver.addOnPreDrawListener(this);
        }
        maybeUpdateInsets();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        ViewTreeObserver viewTreeObserver;
        super.onDetachedFromWindow();
        View view = this.mProviderView;
        if (!(view == null || (viewTreeObserver = view.getViewTreeObserver()) == null)) {
            viewTreeObserver.removeOnPreDrawListener(this);
        }
        this.mProviderView = null;
    }

    public boolean onPreDraw() {
        boolean maybeUpdateInsets = maybeUpdateInsets();
        if (maybeUpdateInsets) {
            requestLayout();
        }
        return !maybeUpdateInsets;
    }
}
