package com.swmansion.rnscreens;

import android.util.Log;
import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class InsetsObserverProxy implements OnApplyWindowInsetsListener, LifecycleEventListener {
    public static final InsetsObserverProxy INSTANCE = new InsetsObserverProxy();
    private static WeakReference eventSourceView = new WeakReference((Object) null);
    private static boolean hasBeenRegistered;
    private static boolean isObservingContextLifetime;
    private static final ArrayList listeners = new ArrayList();
    private static boolean shouldForwardInsetsToView = true;

    public void onHostPause() {
    }

    public void onHostResume() {
    }

    private InsetsObserverProxy() {
    }

    private final boolean getAllowRegistration() {
        return !hasBeenRegistered || eventSourceView.get() == null;
    }

    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(windowInsetsCompat, "insets");
        WindowInsetsCompat onApplyWindowInsets = shouldForwardInsetsToView ? ViewCompat.onApplyWindowInsets(view, windowInsetsCompat) : windowInsetsCompat;
        Intrinsics.checkNotNull(onApplyWindowInsets);
        for (OnApplyWindowInsetsListener onApplyWindowInsets2 : listeners) {
            onApplyWindowInsets = onApplyWindowInsets2.onApplyWindowInsets(view, windowInsetsCompat);
        }
        return onApplyWindowInsets;
    }

    public final void registerWithContext(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "context");
        if (isObservingContextLifetime) {
            Log.w("[RNScreens]", "InsetObserverProxy registers on new context while it has not been invalidated on the old one. Please report this as issue at https://github.com/software-mansion/react-native-screens/issues");
        }
        isObservingContextLifetime = true;
        reactApplicationContext.addLifecycleEventListener(this);
    }

    public void onHostDestroy() {
        View observedView = getObservedView();
        if (hasBeenRegistered && observedView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(observedView, (OnApplyWindowInsetsListener) null);
            hasBeenRegistered = false;
            eventSourceView.clear();
        }
        isObservingContextLifetime = false;
    }

    public final void addOnApplyWindowInsetsListener(OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        Intrinsics.checkNotNullParameter(onApplyWindowInsetsListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        listeners.add(onApplyWindowInsetsListener);
    }

    public final void removeOnApplyWindowInsetsListener(OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        Intrinsics.checkNotNullParameter(onApplyWindowInsetsListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        listeners.remove(onApplyWindowInsetsListener);
    }

    public final boolean registerOnView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (!getAllowRegistration()) {
            return false;
        }
        ViewCompat.setOnApplyWindowInsetsListener(view, this);
        eventSourceView = new WeakReference(view);
        hasBeenRegistered = true;
        return true;
    }

    private final View getObservedView() {
        return (View) eventSourceView.get();
    }
}
