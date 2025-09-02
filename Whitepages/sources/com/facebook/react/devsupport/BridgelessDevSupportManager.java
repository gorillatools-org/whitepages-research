package com.facebook.react.devsupport;

import android.content.Context;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.devsupport.DevSupportManagerBase;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevLoadingViewManager;
import com.facebook.react.devsupport.interfaces.DevSplitBundleCallback;
import com.facebook.react.devsupport.interfaces.PausedInDebuggerOverlayManager;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.packagerconnection.RequestHandler;
import java.util.Map;

class BridgelessDevSupportManager extends DevSupportManagerBase {
    public BridgelessDevSupportManager(Context context, ReactInstanceDevHelper reactInstanceDevHelper, String str) {
        this(context.getApplicationContext(), reactInstanceDevHelper, str, true, (RedBoxHandler) null, (DevBundleDownloadListener) null, 2, (Map<String, RequestHandler>) null, (SurfaceDelegateFactory) null, (DevLoadingViewManager) null, (PausedInDebuggerOverlayManager) null);
    }

    public BridgelessDevSupportManager(Context context, ReactInstanceDevHelper reactInstanceDevHelper, String str, boolean z, RedBoxHandler redBoxHandler, DevBundleDownloadListener devBundleDownloadListener, int i, Map<String, RequestHandler> map, SurfaceDelegateFactory surfaceDelegateFactory, DevLoadingViewManager devLoadingViewManager, PausedInDebuggerOverlayManager pausedInDebuggerOverlayManager) {
        super(context, reactInstanceDevHelper, str, z, redBoxHandler, devBundleDownloadListener, i, map, surfaceDelegateFactory, devLoadingViewManager, pausedInDebuggerOverlayManager);
    }

    /* access modifiers changed from: protected */
    public String getUniqueTag() {
        return "Bridgeless";
    }

    public void loadSplitBundleFromServer(final String str, final DevSplitBundleCallback devSplitBundleCallback) {
        fetchSplitBundleAndCreateBundleLoader(str, new DevSupportManagerBase.CallbackWithBundleLoader() {
            public void onSuccess(JSBundleLoader jSBundleLoader) {
                try {
                    BridgelessDevSupportManager.this.mReactInstanceDevHelper.loadBundle(jSBundleLoader).waitForCompletion();
                    String devServerSplitBundleURL = BridgelessDevSupportManager.this.getDevServerHelper().getDevServerSplitBundleURL(str);
                    ReactContext currentReactContext = BridgelessDevSupportManager.this.mReactInstanceDevHelper.getCurrentReactContext();
                    if (currentReactContext != null) {
                        ((HMRClient) currentReactContext.getJSModule(HMRClient.class)).registerBundle(devServerSplitBundleURL);
                    }
                    devSplitBundleCallback.onSuccess();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("[BridgelessDevSupportManager]: Got interrupted while loading bundle", e);
                }
            }

            public void onError(String str, Throwable th) {
                devSplitBundleCallback.onError(str, th);
            }
        });
    }

    public void handleReloadJS() {
        UiThreadUtil.assertOnUiThread();
        hideRedboxDialog();
        this.mReactInstanceDevHelper.reload("BridgelessDevSupportManager.handleReloadJS()");
    }
}
