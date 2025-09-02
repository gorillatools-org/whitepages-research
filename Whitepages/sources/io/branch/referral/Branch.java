package io.branch.referral;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import com.google.firebase.perf.util.Constants;
import com.google.firebase.sessions.settings.RemoteSettings;
import io.branch.indexing.BranchUniversalObject;
import io.branch.interfaces.IBranchLoggingCallbacks;
import io.branch.referral.BranchLogger;
import io.branch.referral.ServerRequest;
import io.branch.referral.ServerRequestGetLATD;
import io.branch.referral.SystemObserver;
import io.branch.referral.network.BranchRemoteInterface;
import io.branch.referral.network.BranchRemoteInterfaceUrlConnection;
import io.branch.referral.util.BRANCH_STANDARD_EVENT;
import io.branch.referral.util.BranchEvent;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

public class Branch {
    private static final String BRANCH_LIBRARY_VERSION;
    private static final String[] EXTERNAL_INTENT_EXTRA_KEY_WHITE_LIST = {"extra_launch_uri", "branch_intent"};
    private static final String GOOGLE_VERSION_TAG;
    public static String _userAgentString = "";
    private static Branch branchReferral_;
    private static boolean bypassCurrentActivityIntentState_ = false;
    static boolean bypassWaitingForIntent_ = false;
    static boolean deferInitForPluginRuntime = false;
    static boolean disableAutoSessionInitialization;
    private static boolean disableDeviceIDFetch_;
    private static boolean enableInstantDeepLinking = false;
    public static String installDeveloperId = null;
    private static boolean isActivityLifeCycleCallbackRegistered_ = false;
    private static String pluginName = null;
    private static String pluginVersion = null;
    static boolean referringLinkAttributionForPreinstalledAppsEnabled = false;
    static boolean userAgentSync;
    private BranchActivityLifecycleObserver activityLifeCycleObserver;
    private final BranchPluginSupport branchPluginSupport_;
    private final BranchQRCodeCache branchQRCodeCache_;
    /* access modifiers changed from: private */
    public BranchRemoteInterface branchRemoteInterface_;
    public boolean closeRequestNeeded = false;
    private final Context context_;
    WeakReference currentActivityReference_;
    private JSONObject deeplinkDebugParams_;
    /* access modifiers changed from: private */
    public InitSessionBuilder deferredSessionBuilder;
    private final DeviceInfo deviceInfo_;
    CountDownLatch getFirstReferringParamsLatch = null;
    CountDownLatch getLatestReferringParamsLatch = null;
    SESSION_STATE initState_ = SESSION_STATE.UNINITIALISED;
    private INTENT_STATE intentState_ = INTENT_STATE.PENDING;
    /* access modifiers changed from: private */
    public boolean isInstantDeepLinkPossible = false;
    final ConcurrentHashMap linkCache_ = new ConcurrentHashMap();
    final PrefHelper prefHelper_;
    public final ServerRequestQueue requestQueue_;
    private ShareLinkManager shareLinkManager_;
    private final TrackingController trackingController;

    public interface BranchLinkCreateListener {
        void onLinkCreate(String str, BranchError branchError);
    }

    public interface BranchLinkShareListener {
        void onChannelSelected(String str);

        void onLinkShareResponse(String str, String str2, BranchError branchError);

        void onShareLinkDialogDismissed();

        void onShareLinkDialogLaunched();
    }

    public interface BranchReferralInitListener {
        void onInitFinished(JSONObject jSONObject, BranchError branchError);
    }

    public interface BranchUniversalReferralInitListener {
    }

    public interface IChannelProperties {
    }

    enum INTENT_STATE {
        PENDING,
        READY
    }

    public interface LogoutStatusListener {
        void onLogoutFinished(boolean z, BranchError branchError);
    }

    enum SESSION_STATE {
        INITIALISED,
        INITIALISING,
        UNINITIALISED
    }

    public interface TrackingStateCallback {
        void onTrackingStateChanged(boolean z, JSONObject jSONObject, BranchError branchError);
    }

    static {
        String str = "io.branch.sdk.android:library:" + getSdkVersionNumber();
        BRANCH_LIBRARY_VERSION = str;
        GOOGLE_VERSION_TAG = "!SDK-VERSION-STRING!:" + str;
    }

    private Branch(Context context) {
        this.context_ = context;
        this.prefHelper_ = PrefHelper.getInstance(context);
        this.trackingController = new TrackingController(context);
        this.branchRemoteInterface_ = new BranchRemoteInterfaceUrlConnection(this);
        this.deviceInfo_ = new DeviceInfo(context);
        this.branchPluginSupport_ = new BranchPluginSupport(context);
        this.branchQRCodeCache_ = new BranchQRCodeCache(context);
        this.requestQueue_ = ServerRequestQueue.getInstance(context);
    }

    public static synchronized Branch getInstance() {
        Branch branch;
        synchronized (Branch.class) {
            try {
                if (branchReferral_ == null) {
                    BranchLogger.v("Branch instance is not created yet. Make sure you call getAutoInstance(Context).");
                }
                branch = branchReferral_;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return branch;
    }

    private static synchronized Branch initBranchSDK(Context context, String str) {
        synchronized (Branch.class) {
            if (branchReferral_ != null) {
                BranchLogger.w("Warning, attempted to reinitialize Branch SDK singleton!");
                Branch branch = branchReferral_;
                return branch;
            }
            branchReferral_ = new Branch(context.getApplicationContext());
            if (TextUtils.isEmpty(str)) {
                BranchLogger.w("Warning: Please enter your branch_key in your project's Manifest file!");
                branchReferral_.prefHelper_.setBranchKey("bnc_no_value");
            } else {
                branchReferral_.prefHelper_.setBranchKey(str);
            }
            if (context instanceof Application) {
                branchReferral_.setActivityLifeCycleObserver((Application) context);
            }
            Branch branch2 = branchReferral_;
            return branch2;
        }
    }

    public static synchronized Branch getAutoInstance(Context context) {
        Branch branch;
        synchronized (Branch.class) {
            try {
                if (branchReferral_ == null) {
                    if (BranchUtil.getEnableLoggingConfig(context)) {
                        enableLogging();
                    }
                    deferInitForPluginRuntime(BranchUtil.getDeferInitForPluginRuntimeConfig(context));
                    BranchUtil.setAPIBaseUrlFromConfig(context);
                    BranchUtil.setFbAppIdFromConfig(context);
                    BranchUtil.setTestMode(BranchUtil.checkTestMode(context));
                    Branch initBranchSDK = initBranchSDK(context, BranchUtil.readBranchKey(context));
                    branchReferral_ = initBranchSDK;
                    BranchPreinstall.getPreinstallSystemData(initBranchSDK, context);
                }
                branch = branchReferral_;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return branch;
    }

    public Context getApplicationContext() {
        return this.context_;
    }

    public BranchRemoteInterface getBranchRemoteInterface() {
        return this.branchRemoteInterface_;
    }

    public static void expectDelayedSessionInitialization(boolean z) {
        disableAutoSessionInitialization = z;
    }

    public static void setAPIUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (!str.endsWith(RemoteSettings.FORWARD_SLASH_STRING)) {
                str = str + RemoteSettings.FORWARD_SLASH_STRING;
            }
            PrefHelper.setAPIUrl(str);
            BranchLogger.v("setAPIUrl: Branch API URL was set to " + str);
            return;
        }
        BranchLogger.w("setAPIUrl: URL cannot be empty or null");
    }

    public void disableTracking(boolean z, TrackingStateCallback trackingStateCallback) {
        this.trackingController.disableTracking(this.context_, z, trackingStateCallback);
    }

    public void disableTracking(boolean z) {
        disableTracking(z, (TrackingStateCallback) null);
    }

    public boolean isTrackingDisabled() {
        return this.trackingController.isTrackingDisabled();
    }

    public static boolean isDeviceIDFetchDisabled() {
        return disableDeviceIDFetch_;
    }

    public void setDMAParamsForEEA(boolean z, boolean z2, boolean z3) {
        this.prefHelper_.setEEARegion(z);
        this.prefHelper_.setAdPersonalizationConsent(z2);
        this.prefHelper_.setAdUserDataUsageConsent(z3);
    }

    public void setRequestMetadata(String str, String str2) {
        this.prefHelper_.setRequestMetadata(str, str2);
    }

    public Branch addInstallMetadata(String str, String str2) {
        this.prefHelper_.addInstallMetadata(str, str2);
        return this;
    }

    public Branch setPreinstallCampaign(String str) {
        addInstallMetadata(Defines$PreinstallKey.campaign.getKey(), str);
        return this;
    }

    public Branch setPreinstallPartner(String str) {
        addInstallMetadata(Defines$PreinstallKey.partner.getKey(), str);
        return this;
    }

    public static boolean isReferringLinkAttributionForPreinstalledAppsEnabled() {
        return referringLinkAttributionForPreinstalledAppsEnabled;
    }

    /* access modifiers changed from: package-private */
    public void closeSessionInternal() {
        clearPartnerParameters();
        executeClose();
        this.prefHelper_.setSessionParams("bnc_no_value");
        this.prefHelper_.setExternalIntentUri((String) null);
        this.trackingController.updateTrackingState(this.context_);
    }

    /* access modifiers changed from: package-private */
    public void clearPendingRequests() {
        this.requestQueue_.clear();
    }

    private void executeClose() {
        SESSION_STATE session_state = this.initState_;
        SESSION_STATE session_state2 = SESSION_STATE.UNINITIALISED;
        if (session_state != session_state2) {
            setInitState(session_state2);
        }
    }

    public static void registerPlugin(String str, String str2) {
        pluginName = str;
        pluginVersion = str2;
    }

    public static String getPluginVersion() {
        return pluginVersion;
    }

    static String getPluginName() {
        return pluginName;
    }

    /* access modifiers changed from: private */
    public void readAndStripParam(Uri uri, Activity activity) {
        BranchLogger.v("Read params uri: " + uri + " bypassCurrentActivityIntentState: " + bypassCurrentActivityIntentState_ + " intent state: " + this.intentState_);
        if (enableInstantDeepLinking) {
            boolean z = this.intentState_ == INTENT_STATE.READY || !this.activityLifeCycleObserver.isCurrentActivityLaunchedFromStack();
            boolean isRestartSessionRequested = isRestartSessionRequested(activity != null ? activity.getIntent() : null);
            if (z && !isRestartSessionRequested) {
                extractSessionParamsForIDL(uri, activity);
            }
        }
        if (bypassCurrentActivityIntentState_) {
            this.intentState_ = INTENT_STATE.READY;
        }
        if (this.intentState_ == INTENT_STATE.READY) {
            extractExternalUriAndIntentExtras(uri, activity);
            if (!extractBranchLinkFromIntentExtra(activity) && !isActivityLaunchedFromHistory(activity) && !extractClickID(uri, activity)) {
                extractAppLink(uri, activity);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void unlockSDKInitWaitLock() {
        ServerRequestQueue serverRequestQueue = this.requestQueue_;
        if (serverRequestQueue != null) {
            serverRequestQueue.postInitClear();
            this.requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.SDK_INIT_WAIT_LOCK);
            this.requestQueue_.processNextQueueItem("unlockSDKInitWaitLock");
        }
    }

    private boolean isIntentParamsAlreadyConsumed(Activity activity) {
        boolean z = false;
        if (!(activity == null || activity.getIntent() == null || !activity.getIntent().getBooleanExtra(Defines$IntentKeys.BranchLinkUsed.getKey(), false))) {
            z = true;
        }
        BranchLogger.v("isIntentParamsAlreadyConsumed " + z);
        return z;
    }

    private boolean isActivityLaunchedFromHistory(Activity activity) {
        return (activity == null || activity.getIntent() == null || (activity.getIntent().getFlags() & 1048576) == 0) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public void updateSkipURLFormats() {
        UniversalResourceAnalyser.getInstance(this.context_).checkAndUpdateSkipURLFormats(this.context_);
    }

    public void setIdentity(String str) {
        setIdentity(str, (BranchReferralInitListener) null);
    }

    public void setIdentity(String str, BranchReferralInitListener branchReferralInitListener) {
        if (str != null && !str.equals(this.prefHelper_.getIdentity())) {
            installDeveloperId = str;
            this.prefHelper_.setIdentity(str);
        }
        if (branchReferralInitListener != null) {
            branchReferralInitListener.onInitFinished(getFirstReferringParams(), (BranchError) null);
        }
    }

    public void getLastAttributedTouchData(ServerRequestGetLATD.BranchLastAttributedTouchDataListener branchLastAttributedTouchDataListener, int i) {
        if (this.context_ != null) {
            this.requestQueue_.handleNewRequest(new ServerRequestGetLATD(this.context_, Defines$RequestPath.GetLATD, branchLastAttributedTouchDataListener, i));
        }
    }

    public void logout() {
        logout((LogoutStatusListener) null);
    }

    public void logout(LogoutStatusListener logoutStatusListener) {
        this.prefHelper_.setIdentity("bnc_no_value");
        this.prefHelper_.clearUserValues();
        this.linkCache_.clear();
        this.requestQueue_.clear();
        if (logoutStatusListener != null) {
            logoutStatusListener.onLogoutFinished(true, (BranchError) null);
        }
    }

    public JSONObject getFirstReferringParams() {
        return appendDebugParams(convertParamsStringToDictionary(this.prefHelper_.getInstallParams()));
    }

    public void removeSessionInitializationDelay() {
        this.requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.USER_SET_WAIT_LOCK);
        this.requestQueue_.processNextQueueItem("removeSessionInitializationDelay");
    }

    public JSONObject getLatestReferringParams() {
        return appendDebugParams(convertParamsStringToDictionary(this.prefHelper_.getSessionParams()));
    }

    public JSONObject getLatestReferringParamsSync() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.getLatestReferringParamsLatch = countDownLatch;
        try {
            if (this.initState_ != SESSION_STATE.INITIALISED) {
                countDownLatch.await(2500, TimeUnit.MILLISECONDS);
            }
        } catch (InterruptedException unused) {
        }
        JSONObject appendDebugParams = appendDebugParams(convertParamsStringToDictionary(this.prefHelper_.getSessionParams()));
        this.getLatestReferringParamsLatch = null;
        return appendDebugParams;
    }

    public void addFacebookPartnerParameterWithName(String str, String str2) {
        if (!this.trackingController.isTrackingDisabled()) {
            this.prefHelper_.partnerParams_.addFacebookParameter(str, str2);
        }
    }

    public void addSnapPartnerParameterWithName(String str, String str2) {
        if (!this.trackingController.isTrackingDisabled()) {
            this.prefHelper_.partnerParams_.addSnapParameter(str, str2);
        }
    }

    public void clearPartnerParameters() {
        this.prefHelper_.partnerParams_.clearAllParameters();
    }

    private JSONObject appendDebugParams(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = this.deeplinkDebugParams_;
                if (jSONObject2 != null) {
                    if (jSONObject2.length() > 0) {
                        BranchLogger.v("You're currently in deep link debug mode. Please comment out 'setDeepLinkDebugMode' to receive the deep link parameters from a real Branch link");
                    }
                    Iterator<String> keys = this.deeplinkDebugParams_.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        jSONObject.put(next, this.deeplinkDebugParams_.get(next));
                    }
                }
            } catch (Exception e) {
                BranchLogger.d(e.getMessage());
            }
        }
        return jSONObject;
    }

    public JSONObject getDeeplinkDebugParams() {
        JSONObject jSONObject = this.deeplinkDebugParams_;
        if (jSONObject != null && jSONObject.length() > 0) {
            BranchLogger.v("You're currently in deep link debug mode. Please comment out 'setDeepLinkDebugMode' to receive the deep link parameters from a real Branch link");
        }
        return this.deeplinkDebugParams_;
    }

    /* access modifiers changed from: package-private */
    public String generateShortLinkInternal(ServerRequestCreateUrl serverRequestCreateUrl) {
        if (serverRequestCreateUrl.constructError_ || serverRequestCreateUrl.handleErrors(this.context_)) {
            return null;
        }
        if (this.linkCache_.containsKey(serverRequestCreateUrl.getLinkPost())) {
            String str = (String) this.linkCache_.get(serverRequestCreateUrl.getLinkPost());
            serverRequestCreateUrl.onUrlAvailable(str);
            return str;
        } else if (!serverRequestCreateUrl.isAsync()) {
            return generateShortLinkSync(serverRequestCreateUrl);
        } else {
            this.requestQueue_.handleNewRequest(serverRequestCreateUrl);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void shareLink(BranchShareSheetBuilder branchShareSheetBuilder) {
        ShareLinkManager shareLinkManager = this.shareLinkManager_;
        if (shareLinkManager != null) {
            shareLinkManager.cancelShareLinkDialog(true);
        }
        ShareLinkManager shareLinkManager2 = new ShareLinkManager();
        this.shareLinkManager_ = shareLinkManager2;
        shareLinkManager2.shareLink(branchShareSheetBuilder);
    }

    private String generateShortLinkSync(ServerRequestCreateUrl serverRequestCreateUrl) {
        ServerResponse serverResponse;
        String str = null;
        try {
            serverResponse = (ServerResponse) new GetShortLinkTask().execute(new ServerRequest[]{serverRequestCreateUrl}).get((long) (this.prefHelper_.getTimeout() + Constants.MAX_URL_LENGTH), TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            BranchLogger.d(e.getMessage());
            serverResponse = null;
        }
        if (serverRequestCreateUrl.isDefaultToLongUrl()) {
            str = serverRequestCreateUrl.getLongUrl();
        }
        if (serverResponse != null && serverResponse.getStatusCode() == 200) {
            try {
                str = serverResponse.getObject().getString("url");
                if (serverRequestCreateUrl.getLinkPost() != null) {
                    this.linkCache_.put(serverRequestCreateUrl.getLinkPost(), str);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return str;
    }

    private JSONObject convertParamsStringToDictionary(String str) {
        if (str.equals("bnc_no_value")) {
            return new JSONObject();
        }
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            try {
                return new JSONObject(new String(Base64.decode(str.getBytes(), 2)));
            } catch (JSONException e) {
                e.printStackTrace();
                return new JSONObject();
            }
        }
    }

    public TrackingController getTrackingController() {
        return this.trackingController;
    }

    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo_;
    }

    public BranchQRCodeCache getBranchQRCodeCache() {
        return this.branchQRCodeCache_;
    }

    /* access modifiers changed from: package-private */
    public PrefHelper getPrefHelper() {
        return this.prefHelper_;
    }

    /* access modifiers changed from: package-private */
    public ShareLinkManager getShareLinkManager() {
        return this.shareLinkManager_;
    }

    /* access modifiers changed from: package-private */
    public void setIntentState(INTENT_STATE intent_state) {
        this.intentState_ = intent_state;
    }

    /* access modifiers changed from: package-private */
    public void setInitState(SESSION_STATE session_state) {
        this.initState_ = session_state;
    }

    /* access modifiers changed from: package-private */
    public SESSION_STATE getInitState() {
        return this.initState_;
    }

    public void setInstantDeepLinkPossible(boolean z) {
        this.isInstantDeepLinkPossible = z;
    }

    public boolean isInstantDeepLinkPossible() {
        return this.isInstantDeepLinkPossible;
    }

    /* access modifiers changed from: private */
    public void initializeSession(ServerRequestInitSession serverRequestInitSession, int i) {
        BranchLogger.v("initializeSession " + serverRequestInitSession + " delay " + i);
        if (this.prefHelper_.getBranchKey() == null || this.prefHelper_.getBranchKey().equalsIgnoreCase("bnc_no_value")) {
            setInitState(SESSION_STATE.UNINITIALISED);
            BranchReferralInitListener branchReferralInitListener = serverRequestInitSession.callback_;
            if (branchReferralInitListener != null) {
                branchReferralInitListener.onInitFinished((JSONObject) null, new BranchError("Trouble initializing Branch.", -114));
            }
            BranchLogger.w("Warning: Please enter your branch_key in your project's manifest");
            return;
        }
        if (BranchUtil.isTestModeEnabled()) {
            BranchLogger.w("Warning: You are using your test app's Branch Key. Remember to change it to live Branch Key during deployment.");
        }
        if (i > 0) {
            serverRequestInitSession.addProcessWaitLock(ServerRequest.PROCESS_WAIT_LOCK.USER_SET_WAIT_LOCK);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Branch.this.removeSessionInitializationDelay();
                }
            }, (long) i);
        }
        Intent intent = getCurrentActivity() != null ? getCurrentActivity().getIntent() : null;
        boolean isRestartSessionRequested = isRestartSessionRequested(intent);
        SESSION_STATE initState = getInitState();
        BranchLogger.v("Intent: " + intent + " forceBranchSession: " + isRestartSessionRequested + " initState: " + initState);
        if (initState == SESSION_STATE.UNINITIALISED || isRestartSessionRequested) {
            if (isRestartSessionRequested && intent != null) {
                intent.removeExtra(Defines$IntentKeys.ForceNewBranchSession.getKey());
            }
            registerAppInit(serverRequestInitSession, false, isRestartSessionRequested);
            return;
        }
        BranchReferralInitListener branchReferralInitListener2 = serverRequestInitSession.callback_;
        if (branchReferralInitListener2 != null) {
            branchReferralInitListener2.onInitFinished((JSONObject) null, new BranchError("Warning.", -118));
        }
    }

    /* access modifiers changed from: package-private */
    public void registerAppInit(ServerRequestInitSession serverRequestInitSession, boolean z, boolean z2) {
        BranchLogger.v("registerAppInit " + serverRequestInitSession);
        setInitState(SESSION_STATE.INITIALISING);
        ServerRequestInitSession selfInitRequest = this.requestQueue_.getSelfInitRequest();
        BranchLogger.v("Ordering init calls");
        this.requestQueue_.printQueue();
        if (selfInitRequest == null || z2) {
            BranchLogger.v("Moving " + serverRequestInitSession + "  to front of the queue or behind network-in-progress request");
            this.requestQueue_.insertRequestAtFront(serverRequestInitSession);
        } else {
            BranchLogger.v("Retrieved " + selfInitRequest + " with callback " + selfInitRequest.callback_ + " in queue currently");
            selfInitRequest.callback_ = serverRequestInitSession.callback_;
            StringBuilder sb = new StringBuilder();
            sb.append(selfInitRequest);
            sb.append(" now has callback ");
            sb.append(serverRequestInitSession.callback_);
            BranchLogger.v(sb.toString());
        }
        BranchLogger.v("Finished ordering init calls");
        this.requestQueue_.printQueue();
        initTasks(serverRequestInitSession, z);
        this.requestQueue_.processNextQueueItem("registerAppInit");
    }

    private void initTasks(final ServerRequest serverRequest, boolean z) {
        BranchLogger.v("initTasks " + serverRequest + " ignoreWaitLocks " + z);
        if (!z) {
            if (this.intentState_ != INTENT_STATE.READY && isWaitingForIntent()) {
                BranchLogger.v("Adding INTENT_PENDING_WAIT_LOCK");
                serverRequest.addProcessWaitLock(ServerRequest.PROCESS_WAIT_LOCK.INTENT_PENDING_WAIT_LOCK);
            }
            serverRequest.addProcessWaitLock(ServerRequest.PROCESS_WAIT_LOCK.GAID_FETCH_WAIT_LOCK);
            if (serverRequest instanceof ServerRequestRegisterInstall) {
                serverRequest.addProcessWaitLock(ServerRequest.PROCESS_WAIT_LOCK.INSTALL_REFERRER_FETCH_WAIT_LOCK);
                BranchLogger.v("Adding INSTALL_REFERRER_FETCH_WAIT_LOCK");
                this.deviceInfo_.getSystemObserver().fetchInstallReferrer(this.context_, new SystemObserver.InstallReferrerFetchEvents() {
                    public void onInstallReferrersFinished() {
                        serverRequest.removeProcessWaitLock(ServerRequest.PROCESS_WAIT_LOCK.INSTALL_REFERRER_FETCH_WAIT_LOCK);
                        BranchLogger.v("INSTALL_REFERRER_FETCH_WAIT_LOCK removed");
                        Branch.this.requestQueue_.processNextQueueItem("onInstallReferrersFinished");
                    }
                });
            }
        }
        this.deviceInfo_.getSystemObserver().fetchAdId(this.context_, new SystemObserver.AdsParamsFetchEvents() {
            public void onAdsParamsFetchFinished() {
                Branch.this.requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.GAID_FETCH_WAIT_LOCK);
                Branch.this.requestQueue_.processNextQueueItem("onAdsParamsFetchFinished");
            }
        });
    }

    /* access modifiers changed from: package-private */
    public ServerRequestInitSession getInstallOrOpenRequest(BranchReferralInitListener branchReferralInitListener, boolean z) {
        if (this.requestQueue_.hasUser()) {
            return new ServerRequestRegisterOpen(this.context_, branchReferralInitListener, z);
        }
        return new ServerRequestRegisterInstall(this.context_, branchReferralInitListener, z);
    }

    /* access modifiers changed from: package-private */
    public void onIntentReady(Activity activity) {
        BranchLogger.v("onIntentReady " + activity + " removing INTENT_PENDING_WAIT_LOCK");
        setIntentState(INTENT_STATE.READY);
        this.requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.INTENT_PENDING_WAIT_LOCK);
        if (!(activity.getIntent() == null || getInitState() == SESSION_STATE.INITIALISED)) {
            readAndStripParam(activity.getIntent().getData(), activity);
        }
        this.requestQueue_.processNextQueueItem("onIntentReady");
    }

    private void setActivityLifeCycleObserver(Application application) {
        try {
            BranchActivityLifecycleObserver branchActivityLifecycleObserver = new BranchActivityLifecycleObserver();
            this.activityLifeCycleObserver = branchActivityLifecycleObserver;
            application.unregisterActivityLifecycleCallbacks(branchActivityLifecycleObserver);
            application.registerActivityLifecycleCallbacks(this.activityLifeCycleObserver);
            isActivityLifeCycleCallbackRegistered_ = true;
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
            isActivityLifeCycleCallbackRegistered_ = false;
            BranchLogger.v(new BranchError("", -108).getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isRestartSessionRequested(Intent intent) {
        return checkIntentForSessionRestart(intent) || checkIntentForUnusedBranchLink(intent);
    }

    private boolean checkIntentForSessionRestart(Intent intent) {
        if (intent != null) {
            return intent.getBooleanExtra(Defines$IntentKeys.ForceNewBranchSession.getKey(), false);
        }
        return false;
    }

    private boolean checkIntentForUnusedBranchLink(Intent intent) {
        if (intent == null) {
            return false;
        }
        boolean z = intent.getStringExtra(Defines$IntentKeys.BranchURI.getKey()) != null;
        boolean booleanExtra = intent.getBooleanExtra(Defines$IntentKeys.BranchLinkUsed.getKey(), false);
        if (!z || booleanExtra) {
            return false;
        }
        return true;
    }

    private class GetShortLinkTask extends AsyncTask {
        private GetShortLinkTask() {
        }

        /* access modifiers changed from: protected */
        public ServerResponse doInBackground(ServerRequest... serverRequestArr) {
            BranchRemoteInterface access$100 = Branch.this.branchRemoteInterface_;
            JSONObject post = serverRequestArr[0].getPost();
            StringBuilder sb = new StringBuilder();
            sb.append(Branch.this.prefHelper_.getAPIBaseUrl());
            Defines$RequestPath defines$RequestPath = Defines$RequestPath.GetURL;
            sb.append(defines$RequestPath.getPath());
            return access$100.make_restful_post(post, sb.toString(), defines$RequestPath.getPath(), Branch.this.prefHelper_.getBranchKey());
        }
    }

    /* access modifiers changed from: package-private */
    public void checkForAutoDeepLinkConfiguration() {
        ActivityInfo activityInfo;
        Bundle bundle;
        JSONObject latestReferringParams = getLatestReferringParams();
        String str = null;
        try {
            Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.Clicked_Branch_Link;
            if (latestReferringParams.has(defines$Jsonkey.getKey())) {
                if (latestReferringParams.getBoolean(defines$Jsonkey.getKey())) {
                    if (latestReferringParams.length() > 0) {
                        Bundle bundle2 = this.context_.getPackageManager().getApplicationInfo(this.context_.getPackageName(), 128).metaData;
                        int i = 0;
                        if (bundle2 == null || !bundle2.getBoolean("io.branch.sdk.auto_link_disable", false)) {
                            ActivityInfo[] activityInfoArr = this.context_.getPackageManager().getPackageInfo(this.context_.getPackageName(), 129).activities;
                            int i2 = 1501;
                            if (activityInfoArr != null) {
                                int length = activityInfoArr.length;
                                while (true) {
                                    if (i >= length) {
                                        break;
                                    }
                                    activityInfo = activityInfoArr[i];
                                    if (activityInfo == null || (bundle = activityInfo.metaData) == null || ((bundle.getString("io.branch.sdk.auto_link_keys") == null && activityInfo.metaData.getString("io.branch.sdk.auto_link_path") == null) || (!checkForAutoDeepLinkKeys(latestReferringParams, activityInfo) && !checkForAutoDeepLinkPath(latestReferringParams, activityInfo)))) {
                                        i++;
                                    }
                                }
                                str = activityInfo.name;
                                i2 = activityInfo.metaData.getInt("io.branch.sdk.auto_link_request_code", 1501);
                            }
                            if (str == null || getCurrentActivity() == null) {
                                BranchLogger.v("No activity reference to launch deep linked activity");
                                return;
                            }
                            BranchLogger.v("deepLinkActivity " + str + " getCurrentActivity " + getCurrentActivity());
                            Activity currentActivity = getCurrentActivity();
                            Intent intent = new Intent(currentActivity, Class.forName(str));
                            intent.putExtra(Defines$IntentKeys.AutoDeepLinked.getKey(), "true");
                            intent.putExtra(Defines$Jsonkey.ReferringData.getKey(), latestReferringParams.toString());
                            Iterator<String> keys = latestReferringParams.keys();
                            while (keys.hasNext()) {
                                String next = keys.next();
                                intent.putExtra(next, latestReferringParams.getString(next));
                            }
                            currentActivity.startActivityForResult(intent, i2);
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
            BranchLogger.v("Does not have Clicked_Branch_Link or Clicked_Branch_Link is false, returning");
        } catch (PackageManager.NameNotFoundException unused) {
            BranchLogger.w("Warning: Please make sure Activity names set for auto deep link are correct!");
        } catch (ClassNotFoundException unused2) {
            BranchLogger.w("Warning: Please make sure Activity names set for auto deep link are correct! Error while looking for activity " + null);
        } catch (Exception unused3) {
        }
    }

    private boolean checkForAutoDeepLinkKeys(JSONObject jSONObject, ActivityInfo activityInfo) {
        if (activityInfo.metaData.getString("io.branch.sdk.auto_link_keys") != null) {
            for (String has : activityInfo.metaData.getString("io.branch.sdk.auto_link_keys").split(",")) {
                if (jSONObject.has(has)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkForAutoDeepLinkPath(JSONObject jSONObject, ActivityInfo activityInfo) {
        String str = null;
        try {
            Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.AndroidDeepLinkPath;
            if (jSONObject.has(defines$Jsonkey.getKey())) {
                str = jSONObject.getString(defines$Jsonkey.getKey());
            } else {
                Defines$Jsonkey defines$Jsonkey2 = Defines$Jsonkey.DeepLinkPath;
                if (jSONObject.has(defines$Jsonkey2.getKey())) {
                    str = jSONObject.getString(defines$Jsonkey2.getKey());
                }
            }
        } catch (JSONException e) {
            BranchLogger.d(e.getMessage());
        }
        if (!(activityInfo.metaData.getString("io.branch.sdk.auto_link_path") == null || str == null)) {
            for (String trim : activityInfo.metaData.getString("io.branch.sdk.auto_link_path").split(",")) {
                if (pathMatch(trim.trim(), str)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean pathMatch(String str, String str2) {
        String[] split = str.split("\\?")[0].split(RemoteSettings.FORWARD_SLASH_STRING);
        String[] split2 = str2.split("\\?")[0].split(RemoteSettings.FORWARD_SLASH_STRING);
        if (split.length != split2.length) {
            return false;
        }
        int i = 0;
        while (i < split.length && i < split2.length) {
            String str3 = split[i];
            if (!str3.equals(split2[i]) && !str3.contains("*")) {
                return false;
            }
            i++;
        }
        return true;
    }

    private static void enableLogging(IBranchLoggingCallbacks iBranchLoggingCallbacks, BranchLogger.BranchLogLevel branchLogLevel) {
        BranchLogger.setLoggerCallback(iBranchLoggingCallbacks);
        BranchLogger.setLoggingLevel(branchLogLevel);
        BranchLogger.setLoggingEnabled(true);
        BranchLogger.logAlways(GOOGLE_VERSION_TAG);
    }

    public static void enableLogging() {
        enableLogging((IBranchLoggingCallbacks) null, BranchLogger.BranchLogLevel.DEBUG);
    }

    public static void bypassWaitingForIntent(boolean z) {
        bypassWaitingForIntent_ = z;
    }

    public static boolean isWaitingForIntent() {
        return !bypassWaitingForIntent_;
    }

    public static boolean bypassCurrentActivityIntentState() {
        return bypassCurrentActivityIntentState_;
    }

    public void registerView(BranchUniversalObject branchUniversalObject, BranchUniversalObject.RegisterViewStatusListener registerViewStatusListener) {
        if (this.context_ != null) {
            new BranchEvent(BRANCH_STANDARD_EVENT.VIEW_ITEM).addContentItems(branchUniversalObject).logEvent(this.context_);
        }
    }

    private void extractSessionParamsForIDL(Uri uri, Activity activity) {
        if (activity != null && activity.getIntent() != null) {
            Intent intent = activity.getIntent();
            if (uri != null) {
                try {
                    if (!isIntentParamsAlreadyConsumed(activity)) {
                        Defines$IntentKeys defines$IntentKeys = Defines$IntentKeys.BranchData;
                        if (!TextUtils.isEmpty(intent.getStringExtra(defines$IntentKeys.getKey()))) {
                            String stringExtra = intent.getStringExtra(defines$IntentKeys.getKey());
                            if (stringExtra != null) {
                                JSONObject jSONObject = new JSONObject(stringExtra);
                                jSONObject.put(Defines$Jsonkey.Clicked_Branch_Link.getKey(), true);
                                this.prefHelper_.setSessionParams(jSONObject.toString());
                                this.isInstantDeepLinkPossible = true;
                            }
                            intent.removeExtra(defines$IntentKeys.getKey());
                            activity.setIntent(intent);
                            return;
                        } else if (uri.isHierarchical() && Boolean.valueOf(uri.getQueryParameter(Defines$Jsonkey.Instant.getKey())).booleanValue()) {
                            JSONObject jSONObject2 = new JSONObject();
                            for (String next : uri.getQueryParameterNames()) {
                                jSONObject2.put(next, uri.getQueryParameter(next));
                            }
                            jSONObject2.put(Defines$Jsonkey.Clicked_Branch_Link.getKey(), true);
                            this.prefHelper_.setSessionParams(jSONObject2.toString());
                            this.isInstantDeepLinkPossible = true;
                            return;
                        } else {
                            return;
                        }
                    }
                } catch (JSONException e) {
                    BranchLogger.d(e.getMessage());
                    return;
                }
            }
            if (!this.prefHelper_.getInstallParams().equals("bnc_no_value")) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(Defines$Jsonkey.IsFirstSession.getKey(), false);
                this.prefHelper_.setSessionParams(jSONObject3.toString());
                this.isInstantDeepLinkPossible = true;
            }
        }
    }

    private void extractAppLink(Uri uri, Activity activity) {
        if (uri != null && activity != null) {
            String scheme = uri.getScheme();
            Intent intent = activity.getIntent();
            if (scheme != null && intent != null) {
                if ((scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("https")) && !TextUtils.isEmpty(uri.getHost()) && !isIntentParamsAlreadyConsumed(activity)) {
                    if (uri.toString().equalsIgnoreCase(UniversalResourceAnalyser.getInstance(this.context_).getStrippedURL(uri.toString()))) {
                        this.prefHelper_.setAppLink(uri.toString());
                    }
                    intent.putExtra(Defines$IntentKeys.BranchLinkUsed.getKey(), true);
                    activity.setIntent(intent);
                }
            }
        }
    }

    private boolean extractClickID(Uri uri, Activity activity) {
        String str;
        if (uri != null) {
            try {
                if (uri.isHierarchical()) {
                    String queryParameter = uri.getQueryParameter(Defines$Jsonkey.LinkClickID.getKey());
                    if (queryParameter == null) {
                        return false;
                    }
                    this.prefHelper_.setLinkClickIdentifier(queryParameter);
                    String str2 = "link_click_id=" + queryParameter;
                    String uri2 = uri.toString();
                    if (str2.equals(uri.getQuery())) {
                        str = "\\?" + str2;
                    } else if (uri2.length() - str2.length() == uri2.indexOf(str2)) {
                        str = "&" + str2;
                    } else {
                        str = str2 + "&";
                    }
                    activity.getIntent().setData(Uri.parse(uri2.replaceFirst(str, "")));
                    activity.getIntent().putExtra(Defines$IntentKeys.BranchLinkUsed.getKey(), true);
                    return true;
                }
            } catch (Exception e) {
                BranchLogger.d(e.getMessage());
            }
        }
        return false;
    }

    private boolean extractBranchLinkFromIntentExtra(Activity activity) {
        String str;
        BranchLogger.v("extractBranchLinkFromIntentExtra " + activity);
        if (activity == null) {
            return false;
        }
        try {
            if (activity.getIntent() == null || activity.getIntent().getExtras() == null || isIntentParamsAlreadyConsumed(activity)) {
                return false;
            }
            Object obj = activity.getIntent().getExtras().get(Defines$IntentKeys.BranchURI.getKey());
            if (obj instanceof String) {
                str = (String) obj;
            } else {
                str = obj instanceof Uri ? ((Uri) obj).toString() : null;
            }
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            this.prefHelper_.setPushIdentifier(str);
            Intent intent = activity.getIntent();
            intent.putExtra(Defines$IntentKeys.BranchLinkUsed.getKey(), true);
            activity.setIntent(intent);
            return true;
        } catch (Exception e) {
            BranchLogger.d(e.getMessage());
            return false;
        }
    }

    private void extractExternalUriAndIntentExtras(Uri uri, Activity activity) {
        BranchLogger.v("extractExternalUriAndIntentExtras " + uri + " " + activity);
        try {
            if (!isIntentParamsAlreadyConsumed(activity)) {
                String strippedURL = UniversalResourceAnalyser.getInstance(this.context_).getStrippedURL(uri.toString());
                this.prefHelper_.setExternalIntentUri(strippedURL);
                if (strippedURL.equals(uri.toString())) {
                    Bundle extras = activity.getIntent().getExtras();
                    Set<String> keySet = extras.keySet();
                    if (!keySet.isEmpty()) {
                        JSONObject jSONObject = new JSONObject();
                        for (String str : EXTERNAL_INTENT_EXTRA_KEY_WHITE_LIST) {
                            if (keySet.contains(str)) {
                                jSONObject.put(str, extras.get(str));
                            }
                        }
                        if (jSONObject.length() > 0) {
                            this.prefHelper_.setExternalIntentExtra(jSONObject.toString());
                        }
                    }
                }
            }
        } catch (Exception e) {
            BranchLogger.d(e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public Activity getCurrentActivity() {
        WeakReference weakReference = this.currentActivityReference_;
        if (weakReference == null) {
            return null;
        }
        return (Activity) weakReference.get();
    }

    public static class InitSessionBuilder {
        private BranchReferralInitListener callback;
        private int delay;
        private Boolean ignoreIntent;
        private boolean isAutoInitialization;
        private boolean isReInitializing;
        private Uri uri;

        private InitSessionBuilder(Activity activity) {
            Branch instance = Branch.getInstance();
            if (activity == null) {
                return;
            }
            if (instance.getCurrentActivity() == null || !instance.getCurrentActivity().getLocalClassName().equals(activity.getLocalClassName())) {
                instance.currentActivityReference_ = new WeakReference(activity);
            }
        }

        /* access modifiers changed from: package-private */
        public InitSessionBuilder isAutoInitialization(boolean z) {
            this.isAutoInitialization = z;
            return this;
        }

        public InitSessionBuilder withCallback(BranchReferralInitListener branchReferralInitListener) {
            BranchLogger.v("InitSessionBuilder setting BranchReferralInitListener withCallback with " + branchReferralInitListener);
            this.callback = branchReferralInitListener;
            return this;
        }

        public InitSessionBuilder withData(Uri uri2) {
            BranchLogger.v("InitSessionBuilder setting withData with " + uri2);
            this.uri = uri2;
            return this;
        }

        public void init() {
            BranchLogger.v("Beginning session initialization");
            BranchLogger.v("Session uri is " + this.uri);
            BranchLogger.v("Callback is " + this.callback);
            BranchLogger.v("Is auto init " + this.isAutoInitialization);
            BranchLogger.v("Will ignore intent " + this.ignoreIntent);
            BranchLogger.v("Is reinitializing " + this.isReInitializing);
            if (Branch.deferInitForPluginRuntime) {
                BranchLogger.v("Session init is deferred until signaled by plugin.");
                cacheSessionBuilder(this);
                return;
            }
            Branch instance = Branch.getInstance();
            if (instance == null) {
                BranchLogger.logAlways("Branch is not setup properly, make sure to call getAutoInstance in your application class or declare BranchApp in your manifest.");
                return;
            }
            Boolean bool = this.ignoreIntent;
            if (bool != null) {
                Branch.bypassWaitingForIntent(bool.booleanValue());
            }
            Activity currentActivity = instance.getCurrentActivity();
            Intent intent = currentActivity != null ? currentActivity.getIntent() : null;
            if (!(currentActivity == null || intent == null || ActivityCompat.getReferrer(currentActivity) == null)) {
                PrefHelper.getInstance(currentActivity).setInitialReferrer(ActivityCompat.getReferrer(currentActivity).toString());
            }
            Uri uri2 = this.uri;
            if (uri2 != null) {
                instance.readAndStripParam(uri2, currentActivity);
            } else if (this.isReInitializing && instance.isRestartSessionRequested(intent)) {
                instance.readAndStripParam(intent != null ? intent.getData() : null, currentActivity);
            } else if (this.isReInitializing) {
                BranchReferralInitListener branchReferralInitListener = this.callback;
                if (branchReferralInitListener != null) {
                    branchReferralInitListener.onInitFinished((JSONObject) null, new BranchError("", -119));
                    return;
                }
                return;
            }
            BranchLogger.v("isInstantDeepLinkPossible " + instance.isInstantDeepLinkPossible);
            if (instance.isInstantDeepLinkPossible) {
                boolean unused = instance.isInstantDeepLinkPossible = false;
                BranchReferralInitListener branchReferralInitListener2 = this.callback;
                if (branchReferralInitListener2 != null) {
                    branchReferralInitListener2.onInitFinished(instance.getLatestReferringParams(), (BranchError) null);
                }
                Branch.getInstance().requestQueue_.addExtraInstrumentationData(Defines$Jsonkey.InstantDeepLinkSession.getKey(), "true");
                instance.checkForAutoDeepLinkConfiguration();
                this.callback = null;
            }
            if (this.delay > 0) {
                Branch.expectDelayedSessionInitialization(true);
            }
            ServerRequestInitSession installOrOpenRequest = instance.getInstallOrOpenRequest(this.callback, this.isAutoInitialization);
            BranchLogger.d("Creating " + installOrOpenRequest + " from init on thread " + Thread.currentThread().getName());
            instance.initializeSession(installOrOpenRequest, this.delay);
        }

        private void cacheSessionBuilder(InitSessionBuilder initSessionBuilder) {
            InitSessionBuilder unused = Branch.getInstance().deferredSessionBuilder = this;
            BranchLogger.v("Session initialization deferred until plugin invokes notifyNativeToInit()\nCaching Session Builder " + Branch.getInstance().deferredSessionBuilder + "\nuri: " + Branch.getInstance().deferredSessionBuilder.uri + "\ncallback: " + Branch.getInstance().deferredSessionBuilder.callback + "\nisReInitializing: " + Branch.getInstance().deferredSessionBuilder.isReInitializing + "\ndelay: " + Branch.getInstance().deferredSessionBuilder.delay + "\nisAutoInitialization: " + Branch.getInstance().deferredSessionBuilder.isAutoInitialization + "\nignoreIntent: " + Branch.getInstance().deferredSessionBuilder.ignoreIntent);
        }

        public void reInit() {
            this.isReInitializing = true;
            init();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isIDLSession() {
        return Boolean.parseBoolean((String) getInstance().requestQueue_.instrumentationExtraData_.get(Defines$Jsonkey.InstantDeepLinkSession.getKey()));
    }

    public static InitSessionBuilder sessionBuilder(Activity activity) {
        return new InitSessionBuilder(activity);
    }

    public static String getSdkVersionNumber() {
        return BuildConfig.VERSION_NAME;
    }

    static void deferInitForPluginRuntime(boolean z) {
        BranchLogger.v("deferInitForPluginRuntime " + z);
        deferInitForPluginRuntime = z;
        if (z) {
            expectDelayedSessionInitialization(z);
        }
    }

    public static void notifyNativeToInit() {
        BranchLogger.v("notifyNativeToInit deferredSessionBuilder " + getInstance().deferredSessionBuilder);
        SESSION_STATE initState = getInstance().getInitState();
        if (initState == SESSION_STATE.UNINITIALISED) {
            deferInitForPluginRuntime = false;
            if (getInstance().deferredSessionBuilder != null) {
                getInstance().deferredSessionBuilder.init();
                return;
            }
            return;
        }
        BranchLogger.v("notifyNativeToInit session is not uninitialized. Session state is " + initState);
    }

    public static void setFBAppID(String str) {
        if (!TextUtils.isEmpty(str)) {
            PrefHelper.fbAppId_ = str;
            BranchLogger.v("setFBAppID to " + str);
            return;
        }
        BranchLogger.w("setFBAppID: fbAppID cannot be empty or null");
    }
}
