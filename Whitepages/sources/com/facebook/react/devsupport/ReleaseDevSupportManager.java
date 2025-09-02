package com.facebook.react.devsupport;

import android.app.Activity;
import android.util.Pair;
import android.view.View;
import com.facebook.react.bridge.DefaultJSExceptionHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.SurfaceDelegate;
import com.facebook.react.devsupport.interfaces.BundleLoadCallback;
import com.facebook.react.devsupport.interfaces.DevOptionHandler;
import com.facebook.react.devsupport.interfaces.DevSplitBundleCallback;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.ErrorCustomizer;
import com.facebook.react.devsupport.interfaces.ErrorType;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.devsupport.interfaces.StackFrame;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.io.File;
import kotlin.jvm.internal.Intrinsics;

public class ReleaseDevSupportManager implements DevSupportManager {
    private final DefaultJSExceptionHandler defaultJSExceptionHandler = new DefaultJSExceptionHandler();
    private final int lastErrorCookie;

    public void addCustomDevOption(String str, DevOptionHandler devOptionHandler) {
    }

    public View createRootView(String str) {
        return null;
    }

    public SurfaceDelegate createSurfaceDelegate(String str) {
        return null;
    }

    public void destroyRootView(View view) {
    }

    public File downloadBundleResourceFromUrlSync(String str, File file) {
        Intrinsics.checkNotNullParameter(str, "resourceURL");
        return null;
    }

    public Activity getCurrentActivity() {
        return null;
    }

    public ReactContext getCurrentReactContext() {
        return null;
    }

    public DeveloperSettings getDevSettings() {
        return null;
    }

    public boolean getDevSupportEnabled() {
        return false;
    }

    public String getDownloadedJSBundleFile() {
        return null;
    }

    public String getJSBundleURLForRemoteDebugging() {
        return null;
    }

    public StackFrame[] getLastErrorStack() {
        return null;
    }

    public String getLastErrorTitle() {
        return null;
    }

    public ErrorType getLastErrorType() {
        return null;
    }

    public RedBoxHandler getRedBoxHandler() {
        return null;
    }

    public String getSourceMapUrl() {
        return null;
    }

    public String getSourceUrl() {
        return null;
    }

    public void handleReloadJS() {
    }

    public boolean hasUpToDateJSBundleInCache() {
        return false;
    }

    public void hidePausedInDebuggerOverlay() {
    }

    public void hideRedboxDialog() {
    }

    public void loadSplitBundleFromServer(String str, DevSplitBundleCallback devSplitBundleCallback) {
        Intrinsics.checkNotNullParameter(str, "bundlePath");
        Intrinsics.checkNotNullParameter(devSplitBundleCallback, "callback");
    }

    public void onNewReactContextCreated(ReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
    }

    public void onReactInstanceDestroyed(ReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
    }

    public void openDebugger() {
    }

    public Pair<String, StackFrame[]> processErrorCustomizers(Pair<String, StackFrame[]> pair) {
        return pair;
    }

    public void registerErrorCustomizer(ErrorCustomizer errorCustomizer) {
    }

    public void reloadJSFromServer(String str, BundleLoadCallback bundleLoadCallback) {
        Intrinsics.checkNotNullParameter(str, "bundleURL");
        Intrinsics.checkNotNullParameter(bundleLoadCallback, "callback");
    }

    public void reloadSettings() {
    }

    public void setAdditionalOptionForPackager(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "value");
    }

    public void setDevSupportEnabled(boolean z) {
    }

    public void setFpsDebugEnabled(boolean z) {
    }

    public void setHotModuleReplacementEnabled(boolean z) {
    }

    public void setPackagerLocationCustomizer(DevSupportManager.PackagerLocationCustomizer packagerLocationCustomizer) {
    }

    public void setRemoteJSDebugEnabled(boolean z) {
    }

    public void showDevOptionsDialog() {
    }

    public void showNewJSError(String str, ReadableArray readableArray, int i) {
    }

    public void showNewJavaError(String str, Throwable th) {
    }

    public void showPausedInDebuggerOverlay(String str, DevSupportManager.PausedInDebuggerOverlayCommandListener pausedInDebuggerOverlayCommandListener) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        Intrinsics.checkNotNullParameter(pausedInDebuggerOverlayCommandListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
    }

    public void startInspector() {
    }

    public void stopInspector() {
    }

    public void toggleElementInspector() {
    }

    public void isPackagerRunning(PackagerStatusCallback packagerStatusCallback) {
        Intrinsics.checkNotNullParameter(packagerStatusCallback, "callback");
        packagerStatusCallback.onPackagerStatusFetched(false);
    }

    public int getLastErrorCookie() {
        return this.lastErrorCookie;
    }

    public void handleException(Exception exc) {
        Intrinsics.checkNotNullParameter(exc, "e");
        this.defaultJSExceptionHandler.handleException(exc);
    }
}
