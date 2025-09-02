package com.facebook.react.devsupport;

import android.content.Context;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevLoadingViewManager;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.PausedInDebuggerOverlayManager;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.packagerconnection.RequestHandler;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class DefaultDevSupportManagerFactory implements DevSupportManagerFactory {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String DEVSUPPORT_IMPL_CLASS = "BridgeDevSupportManager";
    private static final String DEVSUPPORT_IMPL_PACKAGE = "com.facebook.react.devsupport";

    public DevSupportManager create(Context context, ReactInstanceDevHelper reactInstanceDevHelper, String str, boolean z, RedBoxHandler redBoxHandler, DevBundleDownloadListener devBundleDownloadListener, int i, Map<String, ? extends RequestHandler> map, SurfaceDelegateFactory surfaceDelegateFactory, DevLoadingViewManager devLoadingViewManager, PausedInDebuggerOverlayManager pausedInDebuggerOverlayManager) {
        Context context2 = context;
        Intrinsics.checkNotNullParameter(context2, "applicationContext");
        Intrinsics.checkNotNullParameter(reactInstanceDevHelper, "reactInstanceManagerHelper");
        if (!z) {
            return new ReleaseDevSupportManager();
        }
        try {
            String str2 = DEVSUPPORT_IMPL_PACKAGE + "." + DEVSUPPORT_IMPL_CLASS;
            Intrinsics.checkNotNullExpressionValue(str2, "toString(...)");
            Object newInstance = Class.forName(str2).getConstructor(new Class[]{Context.class, ReactInstanceDevHelper.class, String.class, Boolean.TYPE, RedBoxHandler.class, DevBundleDownloadListener.class, Integer.TYPE, Map.class, SurfaceDelegateFactory.class, DevLoadingViewManager.class, PausedInDebuggerOverlayManager.class}).newInstance(new Object[]{context, reactInstanceDevHelper, str, Boolean.TRUE, redBoxHandler, devBundleDownloadListener, Integer.valueOf(i), map, surfaceDelegateFactory, devLoadingViewManager, pausedInDebuggerOverlayManager});
            Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type com.facebook.react.devsupport.interfaces.DevSupportManager");
            return (DevSupportManager) newInstance;
        } catch (Exception unused) {
            return new PerftestDevSupportManager(context2);
        }
    }

    public DevSupportManager create(Context context, ReactInstanceDevHelper reactInstanceDevHelper, String str, boolean z, RedBoxHandler redBoxHandler, DevBundleDownloadListener devBundleDownloadListener, int i, Map<String, RequestHandler> map, SurfaceDelegateFactory surfaceDelegateFactory, DevLoadingViewManager devLoadingViewManager, PausedInDebuggerOverlayManager pausedInDebuggerOverlayManager, boolean z2) {
        Context context2 = context;
        Intrinsics.checkNotNullParameter(context, "applicationContext");
        ReactInstanceDevHelper reactInstanceDevHelper2 = reactInstanceDevHelper;
        Intrinsics.checkNotNullParameter(reactInstanceDevHelper, "reactInstanceManagerHelper");
        if (z2) {
            return new BridgelessDevSupportManager(context, reactInstanceDevHelper, str, z, redBoxHandler, devBundleDownloadListener, i, map, surfaceDelegateFactory, devLoadingViewManager, pausedInDebuggerOverlayManager);
        }
        if (ReactBuildConfig.UNSTABLE_ENABLE_FUSEBOX_RELEASE) {
            return new PerftestDevSupportManager(context);
        }
        return new ReleaseDevSupportManager();
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
