package com.facebook.react.devsupport;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeRedBoxSpec;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.bridge.DefaultJSExceptionHandler;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.DebugServerException;
import com.facebook.react.common.JavascriptException;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.ShakeDetector;
import com.facebook.react.common.SurfaceDelegate;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.devsupport.BundleDownloader;
import com.facebook.react.devsupport.DevServerHelper;
import com.facebook.react.devsupport.interfaces.BundleLoadCallback;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevLoadingViewManager;
import com.facebook.react.devsupport.interfaces.DevOptionHandler;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.ErrorCustomizer;
import com.facebook.react.devsupport.interfaces.ErrorType;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.devsupport.interfaces.PausedInDebuggerOverlayManager;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.devsupport.interfaces.StackFrame;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.packagerconnection.RequestHandler;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class DevSupportManagerBase implements DevSupportManager {
    private static final String EXOPACKAGE_LOCATION_FORMAT = "/data/local/tmp/exopackage/%s//secondary-dex";
    private static final int JAVA_ERROR_COOKIE = -1;
    private static final int JSEXCEPTION_ERROR_COOKIE = -1;
    private static final String RELOAD_APP_ACTION_SUFFIX = ".RELOAD_APP_ACTION";
    /* access modifiers changed from: private */
    public final Context mApplicationContext;
    /* access modifiers changed from: private */
    public final DevBundleDownloadListener mBundleDownloadListener;
    /* access modifiers changed from: private */
    public ReactContext mCurrentReactContext;
    private final LinkedHashMap<String, DevOptionHandler> mCustomDevOptions = new LinkedHashMap<>();
    /* access modifiers changed from: private */
    public final Map<String, RequestHandler> mCustomPackagerCommandHandlers;
    private DebugOverlayController mDebugOverlayController;
    private final DefaultJSExceptionHandler mDefaultJSExceptionHandler;
    /* access modifiers changed from: private */
    public final DevLoadingViewManager mDevLoadingViewManager;
    private boolean mDevLoadingViewVisible = false;
    private AlertDialog mDevOptionsDialog;
    /* access modifiers changed from: private */
    public final DevServerHelper mDevServerHelper;
    /* access modifiers changed from: private */
    public final DeveloperSettings mDevSettings;
    private List<ErrorCustomizer> mErrorCustomizers;
    private boolean mIsDevSupportEnabled = false;
    /* access modifiers changed from: private */
    public boolean mIsPackagerConnected;
    private boolean mIsReceiverRegistered = false;
    private boolean mIsShakeDetectorStarted = false;
    private final String mJSAppBundleName;
    private final File mJSBundleDownloadedFile;
    private final File mJSSplitBundlesDir;
    private int mLastErrorCookie = 0;
    private StackFrame[] mLastErrorStack;
    private String mLastErrorTitle;
    private ErrorType mLastErrorType;
    private DevSupportManager.PackagerLocationCustomizer mPackagerLocationCustomizer;
    private final PausedInDebuggerOverlayManager mPausedInDebuggerOverlayManager;
    private int mPendingJSSplitBundleRequests = 0;
    protected final ReactInstanceDevHelper mReactInstanceDevHelper;
    private final RedBoxHandler mRedBoxHandler;
    private SurfaceDelegate mRedBoxSurfaceDelegate;
    private final BroadcastReceiver mReloadAppBroadcastReceiver;
    private final ShakeDetector mShakeDetector;
    private final SurfaceDelegateFactory mSurfaceDelegateFactory;

    public interface CallbackWithBundleLoader {
        void onError(String str, Throwable th);

        void onSuccess(JSBundleLoader jSBundleLoader);
    }

    /* access modifiers changed from: protected */
    public abstract String getUniqueTag();

    public DevSupportManagerBase(Context context, ReactInstanceDevHelper reactInstanceDevHelper, String str, boolean z, RedBoxHandler redBoxHandler, DevBundleDownloadListener devBundleDownloadListener, int i, Map<String, RequestHandler> map, SurfaceDelegateFactory surfaceDelegateFactory, DevLoadingViewManager devLoadingViewManager, PausedInDebuggerOverlayManager pausedInDebuggerOverlayManager) {
        this.mReactInstanceDevHelper = reactInstanceDevHelper;
        this.mApplicationContext = context;
        this.mJSAppBundleName = str;
        DevInternalSettings devInternalSettings = new DevInternalSettings(context, new DevSupportManagerBase$$ExternalSyntheticLambda13(this));
        this.mDevSettings = devInternalSettings;
        this.mDevServerHelper = new DevServerHelper(devInternalSettings, context, devInternalSettings.getPackagerConnectionSettings());
        this.mBundleDownloadListener = devBundleDownloadListener;
        this.mShakeDetector = new ShakeDetector(new DevSupportManagerBase$$ExternalSyntheticLambda14(this), i);
        this.mCustomPackagerCommandHandlers = map;
        this.mReloadAppBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (DevSupportManagerBase.getReloadAppAction(context).equals(intent.getAction())) {
                    if (intent.getBooleanExtra(DevServerHelper.RELOAD_APP_EXTRA_JS_PROXY, false)) {
                        DevSupportManagerBase.this.mDevSettings.setRemoteJSDebugEnabled(true);
                        DevSupportManagerBase.this.mDevServerHelper.launchJSDevtools();
                    } else {
                        DevSupportManagerBase.this.mDevSettings.setRemoteJSDebugEnabled(false);
                    }
                    DevSupportManagerBase.this.handleReloadJS();
                }
            }
        };
        String uniqueTag = getUniqueTag();
        this.mJSBundleDownloadedFile = new File(context.getFilesDir(), uniqueTag + "ReactNativeDevBundle.js");
        this.mJSSplitBundlesDir = context.getDir(uniqueTag.toLowerCase(Locale.ROOT) + "_dev_js_split_bundles", 0);
        this.mDefaultJSExceptionHandler = new DefaultJSExceptionHandler();
        setDevSupportEnabled(z);
        this.mRedBoxHandler = redBoxHandler;
        this.mDevLoadingViewManager = devLoadingViewManager == null ? new DefaultDevLoadingViewImplementation(reactInstanceDevHelper) : devLoadingViewManager;
        this.mSurfaceDelegateFactory = surfaceDelegateFactory;
        this.mPausedInDebuggerOverlayManager = pausedInDebuggerOverlayManager == null ? new PausedInDebuggerOverlayDialogManager(new DevSupportManagerBase$$ExternalSyntheticLambda15(this)) : pausedInDebuggerOverlayManager;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Context lambda$new$0() {
        Activity currentActivity = this.mReactInstanceDevHelper.getCurrentActivity();
        if (currentActivity == null || currentActivity.isFinishing()) {
            return null;
        }
        return currentActivity;
    }

    public void handleException(Exception exc) {
        if (this.mIsDevSupportEnabled) {
            logJSException(exc);
        } else {
            this.mDefaultJSExceptionHandler.handleException(exc);
        }
    }

    private void logJSException(Exception exc) {
        StringBuilder sb = new StringBuilder(exc.getMessage() == null ? "Exception in native call from JS" : exc.getMessage());
        for (Throwable cause = exc.getCause(); cause != null; cause = cause.getCause()) {
            sb.append("\n\n");
            sb.append(cause.getMessage());
        }
        if (exc instanceof JavascriptException) {
            FLog.e(ReactConstants.TAG, "Exception in native call from JS", (Throwable) exc);
            showNewError(exc.getMessage().toString(), new StackFrame[0], -1, ErrorType.JS);
            return;
        }
        showNewJavaError(sb.toString(), exc);
    }

    public void showNewJavaError(String str, Throwable th) {
        FLog.e(ReactConstants.TAG, "Exception in native call", th);
        showNewError(str, StackTraceHelper.convertJavaStackTrace(th), -1, ErrorType.NATIVE);
    }

    public void addCustomDevOption(String str, DevOptionHandler devOptionHandler) {
        this.mCustomDevOptions.put(str, devOptionHandler);
    }

    public void showNewJSError(String str, ReadableArray readableArray, int i) {
        showNewError(str, StackTraceHelper.convertJsStackTrace(readableArray), i, ErrorType.JS);
    }

    public void registerErrorCustomizer(ErrorCustomizer errorCustomizer) {
        if (this.mErrorCustomizers == null) {
            this.mErrorCustomizers = new ArrayList();
        }
        this.mErrorCustomizers.add(errorCustomizer);
    }

    public Pair<String, StackFrame[]> processErrorCustomizers(Pair<String, StackFrame[]> pair) {
        List<ErrorCustomizer> list = this.mErrorCustomizers;
        if (list != null) {
            for (ErrorCustomizer customizeErrorInfo : list) {
                Pair<String, StackFrame[]> customizeErrorInfo2 = customizeErrorInfo.customizeErrorInfo(pair);
                if (customizeErrorInfo2 != null) {
                    pair = customizeErrorInfo2;
                }
            }
        }
        return pair;
    }

    public void hideRedboxDialog() {
        SurfaceDelegate surfaceDelegate = this.mRedBoxSurfaceDelegate;
        if (surfaceDelegate != null) {
            surfaceDelegate.hide();
        }
    }

    public View createRootView(String str) {
        return this.mReactInstanceDevHelper.createRootView(str);
    }

    public void destroyRootView(View view) {
        this.mReactInstanceDevHelper.destroyRootView(view);
    }

    private void hideDevOptionsDialog() {
        AlertDialog alertDialog = this.mDevOptionsDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.mDevOptionsDialog = null;
        }
    }

    private void showNewError(String str, StackFrame[] stackFrameArr, int i, ErrorType errorType) {
        UiThreadUtil.runOnUiThread(new DevSupportManagerBase$$ExternalSyntheticLambda3(this, str, stackFrameArr, i, errorType));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showNewError$1(String str, StackFrame[] stackFrameArr, int i, ErrorType errorType) {
        updateLastErrorInfo(str, stackFrameArr, i, errorType);
        if (this.mRedBoxSurfaceDelegate == null) {
            SurfaceDelegate createSurfaceDelegate = createSurfaceDelegate(NativeRedBoxSpec.NAME);
            if (createSurfaceDelegate != null) {
                this.mRedBoxSurfaceDelegate = createSurfaceDelegate;
            } else {
                this.mRedBoxSurfaceDelegate = new RedBoxDialogSurfaceDelegate(this);
            }
            this.mRedBoxSurfaceDelegate.createContentView(NativeRedBoxSpec.NAME);
        }
        if (!this.mRedBoxSurfaceDelegate.isShowing()) {
            this.mRedBoxSurfaceDelegate.show();
        }
    }

    public void showDevOptionsDialog() {
        String str;
        String str2;
        if (this.mDevOptionsDialog == null && this.mIsDevSupportEnabled && !ActivityManager.isUserAMonkey()) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            final HashSet hashSet = new HashSet();
            linkedHashMap.put(this.mApplicationContext.getString(R.string.catalyst_reload), new DevOptionHandler() {
                public void onOptionSelected() {
                    if (!DevSupportManagerBase.this.mDevSettings.isJSDevModeEnabled() && DevSupportManagerBase.this.mDevSettings.isHotModuleReplacementEnabled()) {
                        Toast.makeText(DevSupportManagerBase.this.mApplicationContext, DevSupportManagerBase.this.mApplicationContext.getString(R.string.catalyst_hot_reloading_auto_disable), 1).show();
                        DevSupportManagerBase.this.mDevSettings.setHotModuleReplacementEnabled(false);
                    }
                    DevSupportManagerBase.this.handleReloadJS();
                }
            });
            if (this.mDevSettings.isRemoteJSDebugEnabled()) {
                this.mDevSettings.setRemoteJSDebugEnabled(false);
                handleReloadJS();
            }
            if (this.mDevSettings.isDeviceDebugEnabled() && !this.mDevSettings.isRemoteJSDebugEnabled()) {
                boolean z = this.mIsPackagerConnected;
                String string = this.mApplicationContext.getString(z ? R.string.catalyst_debug_open : R.string.catalyst_debug_open_disabled);
                if (!z) {
                    hashSet.add(string);
                }
                linkedHashMap.put(string, new DevSupportManagerBase$$ExternalSyntheticLambda5(this));
            }
            linkedHashMap.put(this.mApplicationContext.getString(R.string.catalyst_change_bundle_location), new DevSupportManagerBase$$ExternalSyntheticLambda6(this));
            linkedHashMap.put(this.mApplicationContext.getString(R.string.catalyst_inspector_toggle), new DevOptionHandler() {
                public void onOptionSelected() {
                    DevSupportManagerBase.this.mDevSettings.setElementInspectorEnabled(!DevSupportManagerBase.this.mDevSettings.isElementInspectorEnabled());
                    DevSupportManagerBase.this.mReactInstanceDevHelper.toggleElementInspector();
                }
            });
            if (this.mDevSettings.isHotModuleReplacementEnabled()) {
                str = this.mApplicationContext.getString(R.string.catalyst_hot_reloading_stop);
            } else {
                str = this.mApplicationContext.getString(R.string.catalyst_hot_reloading);
            }
            linkedHashMap.put(str, new DevSupportManagerBase$$ExternalSyntheticLambda7(this));
            if (this.mDevSettings.isFpsDebugEnabled()) {
                str2 = this.mApplicationContext.getString(R.string.catalyst_perf_monitor_stop);
            } else {
                str2 = this.mApplicationContext.getString(R.string.catalyst_perf_monitor);
            }
            linkedHashMap.put(str2, new DevSupportManagerBase$$ExternalSyntheticLambda8(this));
            linkedHashMap.put(this.mApplicationContext.getString(R.string.catalyst_settings), new DevSupportManagerBase$$ExternalSyntheticLambda9(this));
            if (this.mCustomDevOptions.size() > 0) {
                linkedHashMap.putAll(this.mCustomDevOptions);
            }
            DevOptionHandler[] devOptionHandlerArr = (DevOptionHandler[]) linkedHashMap.values().toArray(new DevOptionHandler[0]);
            Activity currentActivity = this.mReactInstanceDevHelper.getCurrentActivity();
            if (currentActivity == null || currentActivity.isFinishing()) {
                FLog.e(ReactConstants.TAG, "Unable to launch dev options menu because react activity isn't available");
                return;
            }
            LinearLayout linearLayout = new LinearLayout(currentActivity);
            linearLayout.setOrientation(1);
            TextView textView = new TextView(currentActivity);
            textView.setText(currentActivity.getString(R.string.catalyst_dev_menu_header, new Object[]{getUniqueTag()}));
            textView.setPadding(0, 50, 0, 0);
            textView.setGravity(17);
            textView.setTextSize(16.0f);
            textView.setTypeface(textView.getTypeface(), 1);
            linearLayout.addView(textView);
            String jSExecutorDescription = getJSExecutorDescription();
            if (jSExecutorDescription != null) {
                TextView textView2 = new TextView(currentActivity);
                textView2.setText(currentActivity.getString(R.string.catalyst_dev_menu_sub_header, new Object[]{jSExecutorDescription}));
                textView2.setPadding(0, 20, 0, 0);
                textView2.setGravity(17);
                textView2.setTextSize(14.0f);
                linearLayout.addView(textView2);
            }
            AlertDialog create = new AlertDialog.Builder(currentActivity).setCustomTitle(linearLayout).setAdapter(new ArrayAdapter<String>(currentActivity, 17367043, (String[]) linkedHashMap.keySet().toArray(new String[0])) {
                public boolean areAllItemsEnabled() {
                    return false;
                }

                public boolean isEnabled(int i) {
                    return !hashSet.contains(getItem(i));
                }

                public View getView(int i, View view, ViewGroup viewGroup) {
                    View view2 = super.getView(i, view, viewGroup);
                    view2.setEnabled(isEnabled(i));
                    return view2;
                }
            }, new DevSupportManagerBase$$ExternalSyntheticLambda10(this, devOptionHandlerArr)).setOnCancelListener(new DevSupportManagerBase$$ExternalSyntheticLambda11(this)).create();
            this.mDevOptionsDialog = create;
            create.show();
            ReactContext reactContext = this.mCurrentReactContext;
            if (reactContext != null) {
                ((RCTNativeAppEventEmitter) reactContext.getJSModule(RCTNativeAppEventEmitter.class)).emit("RCTDevMenuShown", (Object) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDevOptionsDialog$3() {
        Activity currentActivity = this.mReactInstanceDevHelper.getCurrentActivity();
        if (currentActivity == null || currentActivity.isFinishing()) {
            FLog.e(ReactConstants.TAG, "Unable to launch change bundle location because react activity is not available");
            return;
        }
        final EditText editText = new EditText(currentActivity);
        editText.setHint("localhost:8081");
        new AlertDialog.Builder(currentActivity).setTitle(this.mApplicationContext.getString(R.string.catalyst_change_bundle_location)).setView(editText).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DevSupportManagerBase.this.mDevSettings.getPackagerConnectionSettings().setDebugServerHost(editText.getText().toString());
                DevSupportManagerBase.this.handleReloadJS();
            }
        }).create().show();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDevOptionsDialog$4() {
        boolean isHotModuleReplacementEnabled = this.mDevSettings.isHotModuleReplacementEnabled();
        this.mDevSettings.setHotModuleReplacementEnabled(!isHotModuleReplacementEnabled);
        ReactContext reactContext = this.mCurrentReactContext;
        if (reactContext != null) {
            Class cls = HMRClient.class;
            if (!isHotModuleReplacementEnabled) {
                ((HMRClient) reactContext.getJSModule(cls)).enable();
            } else {
                ((HMRClient) reactContext.getJSModule(cls)).disable();
            }
        }
        if (!isHotModuleReplacementEnabled && !this.mDevSettings.isJSDevModeEnabled()) {
            Context context = this.mApplicationContext;
            Toast.makeText(context, context.getString(R.string.catalyst_hot_reloading_auto_enable), 1).show();
            this.mDevSettings.setJSDevModeEnabled(true);
            handleReloadJS();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDevOptionsDialog$5() {
        if (!this.mDevSettings.isFpsDebugEnabled()) {
            Activity currentActivity = this.mReactInstanceDevHelper.getCurrentActivity();
            if (currentActivity == null) {
                FLog.e(ReactConstants.TAG, "Unable to get reference to react activity");
            } else {
                DebugOverlayController.requestPermission(currentActivity);
            }
        }
        DeveloperSettings developerSettings = this.mDevSettings;
        developerSettings.setFpsDebugEnabled(!developerSettings.isFpsDebugEnabled());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDevOptionsDialog$6() {
        Intent intent = new Intent(this.mApplicationContext, DevSettingsActivity.class);
        intent.setFlags(268435456);
        this.mApplicationContext.startActivity(intent);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDevOptionsDialog$7(DevOptionHandler[] devOptionHandlerArr, DialogInterface dialogInterface, int i) {
        devOptionHandlerArr[i].onOptionSelected();
        this.mDevOptionsDialog = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDevOptionsDialog$8(DialogInterface dialogInterface) {
        this.mDevOptionsDialog = null;
    }

    private String getJSExecutorDescription() {
        try {
            return getReactInstanceDevHelper().getJavaScriptExecutorFactory().toString();
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    public void setDevSupportEnabled(boolean z) {
        this.mIsDevSupportEnabled = z;
        reloadSettings();
    }

    public boolean getDevSupportEnabled() {
        return this.mIsDevSupportEnabled;
    }

    public DeveloperSettings getDevSettings() {
        return this.mDevSettings;
    }

    public RedBoxHandler getRedBoxHandler() {
        return this.mRedBoxHandler;
    }

    public void onNewReactContextCreated(ReactContext reactContext) {
        resetCurrentContext(reactContext);
    }

    public void onReactInstanceDestroyed(ReactContext reactContext) {
        if (reactContext == this.mCurrentReactContext) {
            resetCurrentContext((ReactContext) null);
        }
        System.gc();
    }

    public String getSourceMapUrl() {
        String str = this.mJSAppBundleName;
        if (str == null) {
            return "";
        }
        return this.mDevServerHelper.getSourceMapUrl((String) Assertions.assertNotNull(str));
    }

    public String getSourceUrl() {
        String str = this.mJSAppBundleName;
        if (str == null) {
            return "";
        }
        return this.mDevServerHelper.getSourceUrl((String) Assertions.assertNotNull(str));
    }

    public String getJSBundleURLForRemoteDebugging() {
        return this.mDevServerHelper.getJSBundleURLForRemoteDebugging((String) Assertions.assertNotNull(this.mJSAppBundleName));
    }

    public String getDownloadedJSBundleFile() {
        return this.mJSBundleDownloadedFile.getAbsolutePath();
    }

    public boolean hasUpToDateJSBundleInCache() {
        if (this.mIsDevSupportEnabled && this.mJSBundleDownloadedFile.exists()) {
            try {
                String packageName = this.mApplicationContext.getPackageName();
                if (this.mJSBundleDownloadedFile.lastModified() > this.mApplicationContext.getPackageManager().getPackageInfo(packageName, 0).lastUpdateTime) {
                    File file = new File(String.format(Locale.US, EXOPACKAGE_LOCATION_FORMAT, new Object[]{packageName}));
                    if (!file.exists()) {
                        return true;
                    }
                    if (this.mJSBundleDownloadedFile.lastModified() > file.lastModified()) {
                        return true;
                    }
                    return false;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                FLog.e(ReactConstants.TAG, "DevSupport is unable to get current app info");
            }
        }
        return false;
    }

    private void resetCurrentContext(ReactContext reactContext) {
        if (this.mCurrentReactContext != reactContext) {
            this.mCurrentReactContext = reactContext;
            DebugOverlayController debugOverlayController = this.mDebugOverlayController;
            if (debugOverlayController != null) {
                debugOverlayController.setFpsDebugViewVisible(false);
            }
            if (reactContext != null) {
                this.mDebugOverlayController = new DebugOverlayController(reactContext);
            }
            if (this.mCurrentReactContext != null) {
                try {
                    URL url = new URL(getSourceUrl());
                    ((HMRClient) this.mCurrentReactContext.getJSModule(HMRClient.class)).setup("android", url.getPath().substring(1), url.getHost(), url.getPort() != -1 ? url.getPort() : url.getDefaultPort(), this.mDevSettings.isHotModuleReplacementEnabled(), url.getProtocol());
                } catch (MalformedURLException e) {
                    showNewJavaError(e.getMessage(), e);
                }
            }
            reloadSettings();
        }
    }

    public void reloadSettings() {
        if (UiThreadUtil.isOnUiThread()) {
            reload();
        } else {
            UiThreadUtil.runOnUiThread(new DevSupportManagerBase$$ExternalSyntheticLambda2(this));
        }
    }

    public ReactContext getCurrentReactContext() {
        return this.mCurrentReactContext;
    }

    public String getJSAppBundleName() {
        return this.mJSAppBundleName;
    }

    /* access modifiers changed from: protected */
    public Context getApplicationContext() {
        return this.mApplicationContext;
    }

    public DevServerHelper getDevServerHelper() {
        return this.mDevServerHelper;
    }

    public DevLoadingViewManager getDevLoadingViewManager() {
        return this.mDevLoadingViewManager;
    }

    public ReactInstanceDevHelper getReactInstanceDevHelper() {
        return this.mReactInstanceDevHelper;
    }

    private void showDevLoadingViewForUrl(String str) {
        if (this.mApplicationContext != null) {
            try {
                URL url = new URL(str);
                int port = url.getPort() != -1 ? url.getPort() : url.getDefaultPort();
                DevLoadingViewManager devLoadingViewManager = this.mDevLoadingViewManager;
                Context context = this.mApplicationContext;
                int i = R.string.catalyst_loading_from_url;
                devLoadingViewManager.showMessage(context.getString(i, new Object[]{url.getHost() + ":" + port}));
                this.mDevLoadingViewVisible = true;
            } catch (MalformedURLException e) {
                FLog.e(ReactConstants.TAG, "Bundle url format is invalid. \n\n" + e.toString());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void showDevLoadingViewForRemoteJSEnabled() {
        Context context = this.mApplicationContext;
        if (context != null) {
            this.mDevLoadingViewManager.showMessage(context.getString(R.string.catalyst_debug_connecting));
            this.mDevLoadingViewVisible = true;
        }
    }

    /* access modifiers changed from: protected */
    public void hideDevLoadingView() {
        this.mDevLoadingViewManager.hide();
        this.mDevLoadingViewVisible = false;
    }

    public void fetchSplitBundleAndCreateBundleLoader(String str, CallbackWithBundleLoader callbackWithBundleLoader) {
        String devServerSplitBundleURL = this.mDevServerHelper.getDevServerSplitBundleURL(str);
        File file = this.mJSSplitBundlesDir;
        UiThreadUtil.runOnUiThread(new DevSupportManagerBase$$ExternalSyntheticLambda0(this, devServerSplitBundleURL, new File(file, str.replaceAll(RemoteSettings.FORWARD_SLASH_STRING, "_") + ".jsbundle"), callbackWithBundleLoader));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$fetchSplitBundleAndCreateBundleLoader$9(final String str, final File file, final CallbackWithBundleLoader callbackWithBundleLoader) {
        showSplitBundleDevLoadingView(str);
        this.mDevServerHelper.downloadBundleFromURL(new DevBundleDownloadListener() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onSuccess$0() {
                DevSupportManagerBase.this.hideSplitBundleDevLoadingView();
            }

            public void onSuccess() {
                UiThreadUtil.runOnUiThread(new DevSupportManagerBase$6$$ExternalSyntheticLambda0(this));
                ReactContext r0 = DevSupportManagerBase.this.mCurrentReactContext;
                if (r0 != null && r0.hasActiveReactInstance()) {
                    callbackWithBundleLoader.onSuccess(JSBundleLoader.createCachedSplitBundleFromNetworkLoader(str, file.getAbsolutePath()));
                }
            }

            public void onProgress(String str, Integer num, Integer num2) {
                DevSupportManagerBase.this.mDevLoadingViewManager.updateProgress(str, num, num2);
            }

            public void onFailure(Exception exc) {
                UiThreadUtil.runOnUiThread(new DevSupportManagerBase$6$$ExternalSyntheticLambda1(DevSupportManagerBase.this));
                callbackWithBundleLoader.onError(str, exc);
            }
        }, file, str, (BundleDownloader.BundleInfo) null);
    }

    private void showSplitBundleDevLoadingView(String str) {
        showDevLoadingViewForUrl(str);
        this.mPendingJSSplitBundleRequests++;
    }

    /* access modifiers changed from: private */
    public void hideSplitBundleDevLoadingView() {
        int i = this.mPendingJSSplitBundleRequests - 1;
        this.mPendingJSSplitBundleRequests = i;
        if (i == 0) {
            hideDevLoadingView();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$isPackagerRunning$10(PackagerStatusCallback packagerStatusCallback) {
        this.mDevServerHelper.isPackagerRunning(packagerStatusCallback);
    }

    public void isPackagerRunning(PackagerStatusCallback packagerStatusCallback) {
        DevSupportManagerBase$$ExternalSyntheticLambda16 devSupportManagerBase$$ExternalSyntheticLambda16 = new DevSupportManagerBase$$ExternalSyntheticLambda16(this, packagerStatusCallback);
        DevSupportManager.PackagerLocationCustomizer packagerLocationCustomizer = this.mPackagerLocationCustomizer;
        if (packagerLocationCustomizer != null) {
            packagerLocationCustomizer.run(devSupportManagerBase$$ExternalSyntheticLambda16);
        } else {
            devSupportManagerBase$$ExternalSyntheticLambda16.run();
        }
    }

    public File downloadBundleResourceFromUrlSync(String str, File file) {
        return this.mDevServerHelper.downloadBundleResourceFromUrlSync(str, file);
    }

    public String getLastErrorTitle() {
        return this.mLastErrorTitle;
    }

    public StackFrame[] getLastErrorStack() {
        return this.mLastErrorStack;
    }

    public int getLastErrorCookie() {
        return this.mLastErrorCookie;
    }

    public ErrorType getLastErrorType() {
        return this.mLastErrorType;
    }

    private void updateLastErrorInfo(String str, StackFrame[] stackFrameArr, int i, ErrorType errorType) {
        this.mLastErrorTitle = str;
        this.mLastErrorStack = stackFrameArr;
        this.mLastErrorCookie = i;
        this.mLastErrorType = errorType;
    }

    public void reloadJSFromServer(String str, final BundleLoadCallback bundleLoadCallback) {
        ReactMarker.logMarker(ReactMarkerConstants.DOWNLOAD_START);
        showDevLoadingViewForUrl(str);
        final BundleDownloader.BundleInfo bundleInfo = new BundleDownloader.BundleInfo();
        this.mDevServerHelper.downloadBundleFromURL(new DevBundleDownloadListener() {
            public void onSuccess() {
                DevSupportManagerBase.this.hideDevLoadingView();
                if (DevSupportManagerBase.this.mBundleDownloadListener != null) {
                    DevSupportManagerBase.this.mBundleDownloadListener.onSuccess();
                }
                ReactMarker.logMarker(ReactMarkerConstants.DOWNLOAD_END, bundleInfo.toJSONString());
                bundleLoadCallback.onSuccess();
            }

            public void onProgress(String str, Integer num, Integer num2) {
                DevSupportManagerBase.this.mDevLoadingViewManager.updateProgress(str, num, num2);
                if (DevSupportManagerBase.this.mBundleDownloadListener != null) {
                    DevSupportManagerBase.this.mBundleDownloadListener.onProgress(str, num, num2);
                }
            }

            public void onFailure(Exception exc) {
                DevSupportManagerBase.this.hideDevLoadingView();
                if (DevSupportManagerBase.this.mBundleDownloadListener != null) {
                    DevSupportManagerBase.this.mBundleDownloadListener.onFailure(exc);
                }
                FLog.e(ReactConstants.TAG, "Unable to download JS bundle", (Throwable) exc);
                DevSupportManagerBase.this.reportBundleLoadingFailure(exc);
                bundleLoadCallback.onError(exc);
            }
        }, this.mJSBundleDownloadedFile, str, bundleInfo);
    }

    /* access modifiers changed from: private */
    public void reportBundleLoadingFailure(Exception exc) {
        UiThreadUtil.runOnUiThread(new DevSupportManagerBase$$ExternalSyntheticLambda1(this, exc));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reportBundleLoadingFailure$11(Exception exc) {
        if (exc instanceof DebugServerException) {
            showNewJavaError(((DebugServerException) exc).getMessage(), exc);
        } else {
            showNewJavaError(this.mApplicationContext.getString(R.string.catalyst_reload_error), exc);
        }
    }

    public void startInspector() {
        if (this.mIsDevSupportEnabled) {
            this.mDevServerHelper.openInspectorConnection();
        }
    }

    public void stopInspector() {
        this.mDevServerHelper.closeInspectorConnection();
    }

    public void setHotModuleReplacementEnabled(boolean z) {
        if (this.mIsDevSupportEnabled) {
            UiThreadUtil.runOnUiThread(new DevSupportManagerBase$$ExternalSyntheticLambda17(this, z));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setHotModuleReplacementEnabled$12(boolean z) {
        this.mDevSettings.setHotModuleReplacementEnabled(z);
        handleReloadJS();
    }

    public void setRemoteJSDebugEnabled(boolean z) {
        if (this.mIsDevSupportEnabled && this.mDevSettings.isRemoteJSDebugEnabled() != z) {
            UiThreadUtil.runOnUiThread(new DevSupportManagerBase$$ExternalSyntheticLambda4(this, z));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setRemoteJSDebugEnabled$13(boolean z) {
        this.mDevSettings.setRemoteJSDebugEnabled(z);
        handleReloadJS();
    }

    public void setFpsDebugEnabled(boolean z) {
        if (this.mIsDevSupportEnabled) {
            UiThreadUtil.runOnUiThread(new DevSupportManagerBase$$ExternalSyntheticLambda12(this, z));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setFpsDebugEnabled$14(boolean z) {
        this.mDevSettings.setFpsDebugEnabled(z);
    }

    public void toggleElementInspector() {
        if (this.mIsDevSupportEnabled) {
            UiThreadUtil.runOnUiThread(new DevSupportManagerBase$$ExternalSyntheticLambda18(this));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$toggleElementInspector$15() {
        DeveloperSettings developerSettings = this.mDevSettings;
        developerSettings.setElementInspectorEnabled(!developerSettings.isElementInspectorEnabled());
        this.mReactInstanceDevHelper.toggleElementInspector();
    }

    /* access modifiers changed from: private */
    public void reload() {
        UiThreadUtil.assertOnUiThread();
        if (this.mIsDevSupportEnabled) {
            DebugOverlayController debugOverlayController = this.mDebugOverlayController;
            if (debugOverlayController != null) {
                debugOverlayController.setFpsDebugViewVisible(this.mDevSettings.isFpsDebugEnabled());
            }
            if (!this.mIsShakeDetectorStarted) {
                this.mShakeDetector.start((SensorManager) this.mApplicationContext.getSystemService("sensor"));
                this.mIsShakeDetectorStarted = true;
            }
            if (!this.mIsReceiverRegistered) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(getReloadAppAction(this.mApplicationContext));
                compatRegisterReceiver(this.mApplicationContext, this.mReloadAppBroadcastReceiver, intentFilter, true);
                this.mIsReceiverRegistered = true;
            }
            if (this.mDevLoadingViewVisible) {
                this.mDevLoadingViewManager.showMessage("Reloading...");
            }
            this.mDevServerHelper.openPackagerConnection(getClass().getSimpleName(), new DevServerHelper.PackagerCommandListener() {
                public void onPackagerConnected() {
                    DevSupportManagerBase.this.mIsPackagerConnected = true;
                }

                public void onPackagerDisconnected() {
                    DevSupportManagerBase.this.mIsPackagerConnected = false;
                }

                public void onPackagerReloadCommand() {
                    if (!InspectorFlags.getFuseboxEnabled()) {
                        DevSupportManagerBase.this.mDevServerHelper.disableDebugger();
                    }
                    UiThreadUtil.runOnUiThread(new DevSupportManagerBase$8$$ExternalSyntheticLambda1(this));
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void lambda$onPackagerReloadCommand$0() {
                    DevSupportManagerBase.this.handleReloadJS();
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void lambda$onPackagerDevMenuCommand$1() {
                    DevSupportManagerBase.this.showDevOptionsDialog();
                }

                public void onPackagerDevMenuCommand() {
                    UiThreadUtil.runOnUiThread(new DevSupportManagerBase$8$$ExternalSyntheticLambda0(this));
                }

                public Map<String, RequestHandler> customCommandHandlers() {
                    return DevSupportManagerBase.this.mCustomPackagerCommandHandlers;
                }
            });
            return;
        }
        DebugOverlayController debugOverlayController2 = this.mDebugOverlayController;
        if (debugOverlayController2 != null) {
            debugOverlayController2.setFpsDebugViewVisible(false);
        }
        if (this.mIsShakeDetectorStarted) {
            this.mShakeDetector.stop();
            this.mIsShakeDetectorStarted = false;
        }
        if (this.mIsReceiverRegistered) {
            this.mApplicationContext.unregisterReceiver(this.mReloadAppBroadcastReceiver);
            this.mIsReceiverRegistered = false;
        }
        hideRedboxDialog();
        hideDevOptionsDialog();
        this.mDevLoadingViewManager.hide();
        this.mDevServerHelper.closePackagerConnection();
    }

    /* access modifiers changed from: private */
    public static String getReloadAppAction(Context context) {
        return context.getPackageName() + RELOAD_APP_ACTION_SUFFIX;
    }

    public void setPackagerLocationCustomizer(DevSupportManager.PackagerLocationCustomizer packagerLocationCustomizer) {
        this.mPackagerLocationCustomizer = packagerLocationCustomizer;
    }

    public Activity getCurrentActivity() {
        return this.mReactInstanceDevHelper.getCurrentActivity();
    }

    public SurfaceDelegate createSurfaceDelegate(String str) {
        SurfaceDelegateFactory surfaceDelegateFactory = this.mSurfaceDelegateFactory;
        if (surfaceDelegateFactory == null) {
            return null;
        }
        return surfaceDelegateFactory.createSurfaceDelegate(str);
    }

    private void compatRegisterReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, boolean z) {
        if (Build.VERSION.SDK_INT < 34 || context.getApplicationInfo().targetSdkVersion < 34) {
            context.registerReceiver(broadcastReceiver, intentFilter);
        } else {
            context.registerReceiver(broadcastReceiver, intentFilter, z ? 2 : 4);
        }
    }

    /* renamed from: openDebugger */
    public void lambda$showDevOptionsDialog$2() {
        this.mDevServerHelper.openDebugger(this.mCurrentReactContext, this.mApplicationContext.getString(R.string.catalyst_open_debugger_error));
    }

    public void showPausedInDebuggerOverlay(String str, DevSupportManager.PausedInDebuggerOverlayCommandListener pausedInDebuggerOverlayCommandListener) {
        this.mPausedInDebuggerOverlayManager.showPausedInDebuggerOverlay(str, pausedInDebuggerOverlayCommandListener);
    }

    public void hidePausedInDebuggerOverlay() {
        this.mPausedInDebuggerOverlayManager.hidePausedInDebuggerOverlay();
    }

    public void setAdditionalOptionForPackager(String str, String str2) {
        this.mDevSettings.getPackagerConnectionSettings().setAdditionalOptionForPackager(str, str2);
    }
}
