package io.branch.rnbranch;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.image.ReactImageView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.storage.db.h;
import com.salesforce.marketingcloud.storage.db.k;
import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.QRCode.BranchQRCode;
import io.branch.referral.ServerRequestGetLATD;
import io.branch.referral.SharingHelper$SHARE_WITH;
import io.branch.referral.util.BRANCH_STANDARD_EVENT;
import io.branch.referral.util.BranchContentSchema;
import io.branch.referral.util.BranchEvent;
import io.branch.referral.util.ContentMetadata;
import io.branch.referral.util.CurrencyType;
import io.branch.referral.util.LinkProperties;
import io.branch.referral.util.ProductCategory;
import io.branch.referral.util.ShareSheetStyle;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ReactModule(name = "RNBranch")
public class RNBranchModule extends ReactContextBaseJavaModule {
    private static final long AGING_HASH_TTL = 3600000;
    public static final String GENERIC_ERROR = "RNBranch::Error";
    private static final String IDENT_FIELD_NAME = "ident";
    private static final String INIT_SESSION_ERROR = "INIT_SESSION_ERROR";
    private static final String INIT_SESSION_START = "INIT_SESSION_START";
    private static final String INIT_SESSION_SUCCESS = "INIT_SESSION_SUCCESS";
    public static final String NAME = "RNBranch";
    public static final String NATIVE_INIT_SESSION_FINISHED_EVENT = "io.branch.rnbranch.RNBranchModule.onInitSessionFinished";
    public static final String NATIVE_INIT_SESSION_FINISHED_EVENT_BRANCH_UNIVERSAL_OBJECT = "branch_universal_object";
    public static final String NATIVE_INIT_SESSION_FINISHED_EVENT_ERROR = "error";
    public static final String NATIVE_INIT_SESSION_FINISHED_EVENT_LINK_PROPERTIES = "link_properties";
    public static final String NATIVE_INIT_SESSION_FINISHED_EVENT_PARAMS = "params";
    public static final String NATIVE_INIT_SESSION_FINISHED_EVENT_URI = "uri";
    public static final String NATIVE_INIT_SESSION_STARTED_EVENT = "io.branch.rnbranch.RNBranchModule.onInitSessionStarted";
    public static final String NATIVE_INIT_SESSION_STARTED_EVENT_URI = "uri";
    private static final String PLUGIN_NAME = "ReactNative";
    public static final String REACT_CLASS = "RNBranch";
    private static final String RN_INIT_SESSION_ERROR_EVENT = "RNBranch.initSessionError";
    private static final String RN_INIT_SESSION_START_EVENT = "RNBranch.initSessionStart";
    private static final String RN_INIT_SESSION_SUCCESS_EVENT = "RNBranch.initSessionSuccess";
    private static final String STANDARD_EVENT_ACHIEVE_LEVEL = "STANDARD_EVENT_ACHIEVE_LEVEL";
    private static final String STANDARD_EVENT_ADD_PAYMENT_INFO = "STANDARD_EVENT_ADD_PAYMENT_INFO";
    private static final String STANDARD_EVENT_ADD_TO_CART = "STANDARD_EVENT_ADD_TO_CART";
    private static final String STANDARD_EVENT_ADD_TO_WISHLIST = "STANDARD_EVENT_ADD_TO_WISHLIST";
    private static final String STANDARD_EVENT_CLICK_AD = "STANDARD_EVENT_CLICK_AD";
    private static final String STANDARD_EVENT_COMPLETE_REGISTRATION = "STANDARD_EVENT_COMPLETE_REGISTRATION";
    private static final String STANDARD_EVENT_COMPLETE_TUTORIAL = "STANDARD_EVENT_COMPLETE_TUTORIAL";
    private static final String STANDARD_EVENT_INITIATE_PURCHASE = "STANDARD_EVENT_INITIATE_PURCHASE";
    private static final String STANDARD_EVENT_INVITE = "STANDARD_EVENT_INVITE";
    private static final String STANDARD_EVENT_LOGIN = "STANDARD_EVENT_LOGIN";
    private static final String STANDARD_EVENT_PURCHASE = "STANDARD_EVENT_PURCHASE";
    private static final String STANDARD_EVENT_RATE = "STANDARD_EVENT_RATE";
    private static final String STANDARD_EVENT_RESERVE = "STANDARD_EVENT_RESERVE";
    private static final String STANDARD_EVENT_SEARCH = "STANDARD_EVENT_SEARCH";
    private static final String STANDARD_EVENT_SHARE = "STANDARD_EVENT_SHARE";
    private static final String STANDARD_EVENT_START_TRIAL = "STANDARD_EVENT_START_TRIAL";
    private static final String STANDARD_EVENT_SUBSCRIBE = "STANDARD_EVENT_SUBSCRIBE";
    private static final String STANDARD_EVENT_UNLOCK_ACHIEVEMENT = "STANDARD_EVENT_UNLOCK_ACHIEVEMENT";
    private static final String STANDARD_EVENT_VIEW_AD = "STANDARD_EVENT_VIEW_AD";
    private static final String STANDARD_EVENT_VIEW_CART = "STANDARD_EVENT_VIEW_CART";
    private static final String STANDARD_EVENT_VIEW_ITEM = "STANDARD_EVENT_VIEW_ITEM";
    private static final String STANDARD_EVENT_VIEW_ITEMS = "STANDARD_EVENT_VIEW_ITEMS";
    public static final String UNIVERSAL_OBJECT_NOT_FOUND_ERROR_CODE = "RNBranch::Error::BUONotFound";
    private static Branch.BranchUniversalReferralInitListener initListener = null;
    /* access modifiers changed from: private */
    public static JSONObject initSessionResult = null;
    private static Activity mActivity = null;
    private static boolean mInitialized = false;
    private static volatile boolean mNewIntent = true;
    private static JSONObject mRequestMetadata = new JSONObject();
    private static Branch.BranchReferralInitListener referralInitListener = null;
    private BroadcastReceiver mInitSessionFinishedEventReceiver = null;
    private BroadcastReceiver mInitSessionStartedEventReceiver = null;
    private AgingHash mUniversalObjectMap = new AgingHash(AGING_HASH_TTL);

    static /* synthetic */ Branch.BranchUniversalReferralInitListener access$100() {
        return null;
    }

    public static void getAutoInstance(Context context) {
        Branch.registerPlugin("ReactNative", BuildConfig.RNBRANCH_VERSION);
        Branch.getAutoInstance(context);
    }

    public static void reInitSession(Activity activity) {
        Branch.getInstance();
        Intent intent = activity.getIntent();
        Log.d("RNBranch", "reInitSession intent " + intent);
        if (intent != null) {
            intent.putExtra("branch_force_new_session", true);
            notifyJSOfInitSessionStart(activity, intent.getData());
            Branch.sessionBuilder(activity).withCallback(referralInitListener).reInit();
            return;
        }
        Log.w("RNBranch", "reInitSession was called but the Intent is null");
    }

    public static void initSession(Uri uri, Activity activity, Branch.BranchUniversalReferralInitListener branchUniversalReferralInitListener) {
        Log.d("RNBranch", "initSession uri " + uri + " reactActivity " + activity + " anInitListener" + branchUniversalReferralInitListener);
        initSession(uri, activity);
    }

    public static void initSession(final Uri uri, Activity activity) {
        Log.d("RNBranch", "initSession uri " + uri + " reactActivity " + activity);
        setupBranch(activity.getApplicationContext());
        mActivity = activity;
        final boolean z = mNewIntent;
        referralInitListener = new Branch.BranchReferralInitListener() {
            private Activity mmActivity = null;

            public void onInitFinished(JSONObject jSONObject, BranchError branchError) {
                Uri uri;
                Log.d("RNBranch", "onInitFinished referringParams " + jSONObject);
                if (jSONObject == null) {
                    jSONObject = new JSONObject();
                }
                JSONObject jSONObject2 = jSONObject;
                Log.d("RNBranch", "onInitFinished");
                JSONObject jSONObject3 = new JSONObject();
                try {
                    jSONObject3.put(RNBranchModule.NATIVE_INIT_SESSION_FINISHED_EVENT_PARAMS, jSONObject2);
                    jSONObject3.put("error", branchError != null ? branchError.getMessage() : JSONObject.NULL);
                    jSONObject3.put("uri", (!z || (uri = uri) == null) ? JSONObject.NULL : uri.toString());
                } catch (JSONException e) {
                    Log.e("RNBranch", e.getMessage());
                }
                JSONObject unused = RNBranchModule.initSessionResult = jSONObject3;
                BranchUniversalObject referredBranchUniversalObject = BranchUniversalObject.getReferredBranchUniversalObject();
                LinkProperties referredLinkProperties = LinkProperties.getReferredLinkProperties();
                RNBranchModule.access$100();
                generateLocalBroadcast(jSONObject2, uri, referredBranchUniversalObject, referredLinkProperties, branchError);
            }

            /* access modifiers changed from: private */
            public Branch.BranchReferralInitListener init(Activity activity) {
                this.mmActivity = activity;
                return this;
            }

            private void generateLocalBroadcast(JSONObject jSONObject, Uri uri, BranchUniversalObject branchUniversalObject, LinkProperties linkProperties, BranchError branchError) {
                Intent intent = new Intent(RNBranchModule.NATIVE_INIT_SESSION_FINISHED_EVENT);
                if (jSONObject != null) {
                    intent.putExtra(RNBranchModule.NATIVE_INIT_SESSION_FINISHED_EVENT_PARAMS, jSONObject.toString());
                }
                if (branchUniversalObject != null) {
                    intent.putExtra(RNBranchModule.NATIVE_INIT_SESSION_FINISHED_EVENT_BRANCH_UNIVERSAL_OBJECT, branchUniversalObject);
                }
                if (linkProperties != null) {
                    intent.putExtra(RNBranchModule.NATIVE_INIT_SESSION_FINISHED_EVENT_LINK_PROPERTIES, linkProperties);
                }
                if (z && uri != null) {
                    intent.putExtra("uri", uri.toString());
                }
                if (branchError != null) {
                    intent.putExtra("error", branchError.getMessage());
                }
                LocalBroadcastManager.getInstance(this.mmActivity).sendBroadcast(intent);
            }
        }.init(activity);
        notifyJSOfInitSessionStart(activity, uri);
        Branch.InitSessionBuilder withData = Branch.sessionBuilder(activity).withCallback(referralInitListener).withData(uri);
        Log.d("RNBranch", "sessionBuilder " + withData);
        withData.init();
    }

    public static void onNewIntent(Intent intent) {
        Log.d("RNBranch", "onNewIntent " + intent);
        mActivity.setIntent(intent);
        mNewIntent = true;
        reInitSession(mActivity);
    }

    private static void notifyJSOfInitSessionStart(Context context, Uri uri) {
        Log.d("RNBranch", "notifyJSOfInitSessionStart  " + uri);
        if (mNewIntent) {
            mNewIntent = false;
            Intent intent = new Intent(NATIVE_INIT_SESSION_STARTED_EVENT);
            if (uri != null) {
                intent.putExtra("uri", uri);
            }
            Log.d("RNBranch", "Broadcasting NATIVE_INIT_SESSION_STARTED_EVENT");
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }

    public static void enableLogging() {
        Branch.enableLogging();
    }

    public static void setRequestMetadata(String str, String str2) {
        if (str != null) {
            if (mRequestMetadata.has(str) && str2 == null) {
                mRequestMetadata.remove(str);
            }
            try {
                mRequestMetadata.put(str, str2);
            } catch (JSONException unused) {
            }
        }
    }

    public RNBranchModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        listenForInitSessionEventsToReactNative(reactApplicationContext);
    }

    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put(INIT_SESSION_SUCCESS, RN_INIT_SESSION_SUCCESS_EVENT);
        hashMap.put(INIT_SESSION_ERROR, RN_INIT_SESSION_ERROR_EVENT);
        hashMap.put(INIT_SESSION_START, RN_INIT_SESSION_START_EVENT);
        hashMap.put(STANDARD_EVENT_ADD_TO_CART, BRANCH_STANDARD_EVENT.ADD_TO_CART.getName());
        hashMap.put(STANDARD_EVENT_ADD_TO_WISHLIST, BRANCH_STANDARD_EVENT.ADD_TO_WISHLIST.getName());
        hashMap.put(STANDARD_EVENT_VIEW_CART, BRANCH_STANDARD_EVENT.VIEW_CART.getName());
        hashMap.put(STANDARD_EVENT_INITIATE_PURCHASE, BRANCH_STANDARD_EVENT.INITIATE_PURCHASE.getName());
        hashMap.put(STANDARD_EVENT_ADD_PAYMENT_INFO, BRANCH_STANDARD_EVENT.ADD_PAYMENT_INFO.getName());
        hashMap.put(STANDARD_EVENT_PURCHASE, BRANCH_STANDARD_EVENT.PURCHASE.getName());
        hashMap.put(STANDARD_EVENT_VIEW_AD, BRANCH_STANDARD_EVENT.VIEW_AD.getName());
        hashMap.put(STANDARD_EVENT_CLICK_AD, BRANCH_STANDARD_EVENT.CLICK_AD.getName());
        hashMap.put(STANDARD_EVENT_SEARCH, BRANCH_STANDARD_EVENT.SEARCH.getName());
        hashMap.put(STANDARD_EVENT_VIEW_ITEM, BRANCH_STANDARD_EVENT.VIEW_ITEM.getName());
        hashMap.put(STANDARD_EVENT_VIEW_ITEMS, BRANCH_STANDARD_EVENT.VIEW_ITEMS.getName());
        hashMap.put(STANDARD_EVENT_RATE, BRANCH_STANDARD_EVENT.RATE.getName());
        hashMap.put(STANDARD_EVENT_SHARE, BRANCH_STANDARD_EVENT.SHARE.getName());
        hashMap.put(STANDARD_EVENT_COMPLETE_REGISTRATION, BRANCH_STANDARD_EVENT.COMPLETE_REGISTRATION.getName());
        hashMap.put(STANDARD_EVENT_COMPLETE_TUTORIAL, BRANCH_STANDARD_EVENT.COMPLETE_TUTORIAL.getName());
        hashMap.put(STANDARD_EVENT_ACHIEVE_LEVEL, BRANCH_STANDARD_EVENT.ACHIEVE_LEVEL.getName());
        hashMap.put(STANDARD_EVENT_UNLOCK_ACHIEVEMENT, BRANCH_STANDARD_EVENT.UNLOCK_ACHIEVEMENT.getName());
        hashMap.put(STANDARD_EVENT_INVITE, BRANCH_STANDARD_EVENT.INVITE.getName());
        hashMap.put(STANDARD_EVENT_LOGIN, BRANCH_STANDARD_EVENT.LOGIN.getName());
        hashMap.put(STANDARD_EVENT_RESERVE, BRANCH_STANDARD_EVENT.RESERVE.getName());
        hashMap.put(STANDARD_EVENT_SUBSCRIBE, BRANCH_STANDARD_EVENT.SUBSCRIBE.getName());
        hashMap.put(STANDARD_EVENT_START_TRIAL, BRANCH_STANDARD_EVENT.START_TRIAL.getName());
        return hashMap;
    }

    private void listenForInitSessionEventsToReactNative(ReactApplicationContext reactApplicationContext) {
        this.mInitSessionFinishedEventReceiver = new BroadcastReceiver() {
            RNBranchModule mBranchModule;

            public void onReceive(Context context, Intent intent) {
                this.mBranchModule.sendRNEvent((!RNBranchModule.initSessionResult.has("error") || RNBranchModule.initSessionResult.isNull("error")) ? RNBranchModule.RN_INIT_SESSION_SUCCESS_EVENT : RNBranchModule.RN_INIT_SESSION_ERROR_EVENT, RNBranchModule.convertJsonToMap(RNBranchModule.initSessionResult));
            }

            /* access modifiers changed from: private */
            public BroadcastReceiver init(RNBranchModule rNBranchModule) {
                this.mBranchModule = rNBranchModule;
                return this;
            }
        }.init(this);
        LocalBroadcastManager.getInstance(reactApplicationContext).registerReceiver(this.mInitSessionFinishedEventReceiver, new IntentFilter(NATIVE_INIT_SESSION_FINISHED_EVENT));
        this.mInitSessionStartedEventReceiver = new BroadcastReceiver() {
            RNBranchModule mBranchModule;

            public void onReceive(Context context, Intent intent) {
                Uri uri = (Uri) intent.getParcelableExtra("uri");
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                if (uri != null) {
                    writableNativeMap.putString("uri", uri.toString());
                } else {
                    writableNativeMap.putNull("uri");
                }
                this.mBranchModule.sendRNEvent(RNBranchModule.RN_INIT_SESSION_START_EVENT, writableNativeMap);
            }

            /* access modifiers changed from: private */
            public BroadcastReceiver init(RNBranchModule rNBranchModule) {
                this.mBranchModule = rNBranchModule;
                return this;
            }
        }.init(this);
        LocalBroadcastManager.getInstance(reactApplicationContext).registerReceiver(this.mInitSessionStartedEventReceiver, new IntentFilter(NATIVE_INIT_SESSION_STARTED_EVENT));
    }

    public void onCatalystInstanceDestroy() {
        Log.d("RNBranch", "onCatalystInstanceDestroy ");
        LocalBroadcastManager.getInstance(getReactApplicationContext()).unregisterReceiver(this.mInitSessionFinishedEventReceiver);
        LocalBroadcastManager.getInstance(getReactApplicationContext()).unregisterReceiver(this.mInitSessionStartedEventReceiver);
    }

    public String getName() {
        return "RNBranch";
    }

    @ReactMethod
    public void notifyNativeToInit() {
        Log.d("RNBranch", "notifyNativeToInit");
        Branch.notifyNativeToInit();
    }

    @ReactMethod
    public void disableTracking(boolean z) {
        Branch.getInstance().disableTracking(z);
    }

    @ReactMethod
    public void isTrackingDisabled(Promise promise) {
        promise.resolve(Boolean.valueOf(Branch.getInstance().isTrackingDisabled()));
    }

    @ReactMethod
    public void createUniversalObject(ReadableMap readableMap, Promise promise) {
        String uuid = UUID.randomUUID().toString();
        this.mUniversalObjectMap.put(uuid, createBranchUniversalObject(readableMap));
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString(IDENT_FIELD_NAME, uuid);
        promise.resolve(writableNativeMap);
    }

    @ReactMethod
    public void releaseUniversalObject(String str) {
        this.mUniversalObjectMap.remove(str);
    }

    @ReactMethod
    public void redeemInitSessionResult(Promise promise) {
        promise.resolve(convertJsonToMap(initSessionResult));
    }

    @ReactMethod
    public void getLatestReferringParams(boolean z, Promise promise) {
        Branch instance = Branch.getInstance();
        if (z) {
            promise.resolve(convertJsonToMap(instance.getLatestReferringParamsSync()));
        } else {
            promise.resolve(convertJsonToMap(instance.getLatestReferringParams()));
        }
    }

    @ReactMethod
    public void getFirstReferringParams(Promise promise) {
        promise.resolve(convertJsonToMap(Branch.getInstance().getFirstReferringParams()));
    }

    @ReactMethod
    public void lastAttributedTouchData(int i, final Promise promise) {
        Branch.getInstance().getLastAttributedTouchData(new ServerRequestGetLATD.BranchLastAttributedTouchDataListener() {
            public void onDataFetched(JSONObject jSONObject, BranchError branchError) {
                if (branchError == null) {
                    promise.resolve(RNBranchModule.convertJsonToMap(jSONObject));
                } else {
                    promise.reject(RNBranchModule.GENERIC_ERROR, branchError.getMessage());
                }
            }
        }, i);
    }

    @ReactMethod
    public void setIdentity(String str) {
        Branch.getInstance().setIdentity(str);
    }

    @ReactMethod
    public void setIdentityAsync(String str, final Promise promise) {
        Branch.getInstance().setIdentity(str, new Branch.BranchReferralInitListener() {
            public void onInitFinished(JSONObject jSONObject, BranchError branchError) {
                if (branchError != null) {
                    promise.reject("RNBranch::Error::setIdentityAsync failed", branchError.getMessage());
                } else {
                    promise.resolve(RNBranchModule.convertJsonToMap(jSONObject));
                }
            }
        });
    }

    @ReactMethod
    public void setRequestMetadataKey(String str, String str2) {
        Branch.getInstance().setRequestMetadata(str, str2);
    }

    @ReactMethod
    public void addFacebookPartnerParameter(String str, String str2) {
        Branch.getInstance().addFacebookPartnerParameterWithName(str, str2);
    }

    @ReactMethod
    public void addSnapPartnerParameter(String str, String str2) {
        Branch.getInstance().addSnapPartnerParameterWithName(str, str2);
    }

    @ReactMethod
    public void clearPartnerParameters() {
        Branch.getInstance().clearPartnerParameters();
    }

    @ReactMethod
    public void logout() {
        Branch.getInstance().logout();
    }

    @ReactMethod
    public static void setPreinstallCampaign(String str) {
        Branch.getInstance().setPreinstallCampaign(str);
    }

    @ReactMethod
    public static void setPreinstallPartner(String str) {
        Branch.getInstance().setPreinstallPartner(str);
    }

    @ReactMethod
    public void logEvent(ReadableArray readableArray, String str, ReadableMap readableMap, Promise promise) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < readableArray.size()) {
            BranchUniversalObject findUniversalObjectOrReject = findUniversalObjectOrReject(readableArray.getString(i), promise);
            if (findUniversalObjectOrReject != null) {
                arrayList.add(findUniversalObjectOrReject);
                i++;
            } else {
                return;
            }
        }
        BranchEvent createBranchEvent = createBranchEvent(str, readableMap);
        createBranchEvent.addContentItems((List) arrayList);
        createBranchEvent.logEvent(mActivity);
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void showShareSheet(String str, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, Promise promise) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        new Handler(reactApplicationContext.getMainLooper()).post(new Runnable() {
            ReadableMap controlParamsMap;
            String ident;
            ReadableMap linkPropertiesMap;
            Context mContext;
            Promise mPm;
            ReadableMap shareOptionsMap;

            /* access modifiers changed from: private */
            public Runnable init(ReadableMap readableMap, String str, ReadableMap readableMap2, ReadableMap readableMap3, Promise promise, Context context) {
                this.mPm = promise;
                this.mContext = context;
                this.shareOptionsMap = readableMap;
                this.ident = str;
                this.linkPropertiesMap = readableMap2;
                this.controlParamsMap = readableMap3;
                return this;
            }

            public void run() {
                String str = "";
                String string = this.shareOptionsMap.hasKey("messageHeader") ? this.shareOptionsMap.getString("messageHeader") : str;
                if (this.shareOptionsMap.hasKey("messageBody")) {
                    str = this.shareOptionsMap.getString("messageBody");
                }
                ShareSheetStyle addPreferredSharingOption = new ShareSheetStyle(this.mContext, string, str).setCopyUrlStyle(this.mContext.getResources().getDrawable(17301584), "Copy", "Added to clipboard").setMoreOptionStyle(this.mContext.getResources().getDrawable(17301583), "Show more").addPreferredSharingOption(SharingHelper$SHARE_WITH.EMAIL).addPreferredSharingOption(SharingHelper$SHARE_WITH.TWITTER).addPreferredSharingOption(SharingHelper$SHARE_WITH.MESSAGE).addPreferredSharingOption(SharingHelper$SHARE_WITH.FACEBOOK);
                BranchUniversalObject access$600 = RNBranchModule.this.findUniversalObjectOrReject(this.ident, this.mPm);
                if (access$600 != null) {
                    access$600.showShareSheet(RNBranchModule.this.getCurrentActivity(), RNBranchModule.createLinkProperties(this.linkPropertiesMap, this.controlParamsMap), addPreferredSharingOption, new Branch.BranchLinkShareListener() {
                        private Promise mPromise = null;

                        public void onChannelSelected(String str) {
                        }

                        public void onShareLinkDialogLaunched() {
                        }

                        public void onShareLinkDialogDismissed() {
                            if (this.mPromise != null) {
                                WritableNativeMap writableNativeMap = new WritableNativeMap();
                                writableNativeMap.putString("channel", (String) null);
                                writableNativeMap.putBoolean("completed", false);
                                writableNativeMap.putString("error", (String) null);
                                this.mPromise.resolve(writableNativeMap);
                                this.mPromise = null;
                            }
                        }

                        public void onLinkShareResponse(String str, String str2, BranchError branchError) {
                            if (this.mPromise != null) {
                                WritableNativeMap writableNativeMap = new WritableNativeMap();
                                writableNativeMap.putString("channel", str2);
                                writableNativeMap.putBoolean("completed", true);
                                writableNativeMap.putString("error", branchError != null ? branchError.getMessage() : null);
                                this.mPromise.resolve(writableNativeMap);
                                this.mPromise = null;
                            }
                        }

                        /* access modifiers changed from: private */
                        public Branch.BranchLinkShareListener init(Promise promise) {
                            this.mPromise = promise;
                            return this;
                        }
                    }.init(this.mPm));
                }
            }
        }.init(readableMap, str, readableMap2, readableMap3, promise, reactApplicationContext));
    }

    @ReactMethod
    public void registerView(String str, Promise promise) {
        BranchUniversalObject findUniversalObjectOrReject = findUniversalObjectOrReject(str, promise);
        if (findUniversalObjectOrReject != null) {
            findUniversalObjectOrReject.registerView();
            promise.resolve((Object) null);
        }
    }

    @ReactMethod
    public void generateShortUrl(String str, ReadableMap readableMap, ReadableMap readableMap2, final Promise promise) {
        LinkProperties createLinkProperties = createLinkProperties(readableMap, readableMap2);
        BranchUniversalObject findUniversalObjectOrReject = findUniversalObjectOrReject(str, promise);
        if (findUniversalObjectOrReject != null) {
            findUniversalObjectOrReject.generateShortUrl(mActivity, createLinkProperties, new Branch.BranchLinkCreateListener() {
                public void onLinkCreate(String str, BranchError branchError) {
                    Log.d("RNBranch", "onLinkCreate " + str);
                    if (branchError == null) {
                        WritableNativeMap writableNativeMap = new WritableNativeMap();
                        writableNativeMap.putString("url", str);
                        promise.resolve(writableNativeMap);
                    } else if (branchError.getErrorCode() == -105) {
                        promise.reject("RNBranch::Error::DuplicateResourceError", branchError.getMessage());
                    } else {
                        promise.reject(RNBranchModule.GENERIC_ERROR, branchError.getMessage());
                    }
                }
            });
        }
    }

    @ReactMethod
    public void openURL(String str, ReadableMap readableMap) {
        Log.d("RNBranch", "openURL url: " + str);
        if (mActivity == null) {
            Log.e("RNBranch", "Branch native Android SDK not initialized in openURL");
            return;
        }
        Activity activity = mActivity;
        Intent intent = new Intent(activity, activity.getClass());
        intent.setData(Uri.parse(str));
        intent.putExtra("branch_force_new_session", true);
        mActivity.startActivity(intent);
    }

    @ReactMethod
    public void getBranchQRCode(ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, ReadableMap readableMap4, final Promise promise) {
        BranchUniversalObject createBranchUniversalObject = createBranchUniversalObject(readableMap2);
        LinkProperties createLinkProperties = createLinkProperties(readableMap3, readableMap4);
        try {
            createBranchQRCode(readableMap).getQRCodeAsData(getReactApplicationContext().getCurrentActivity(), createBranchUniversalObject, createLinkProperties, new BranchQRCode.BranchQRCodeDataHandler() {
                public void onSuccess(byte[] bArr) {
                    promise.resolve(Base64.encodeToString(bArr, 0));
                }

                public void onFailure(Exception exc) {
                    Log.d("Failed to get QR Code", exc.getMessage());
                    promise.reject("Failed to get QR Code", exc.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Failed to get QR Code", e.getMessage());
            promise.reject("Failed to get QR Code", e.getMessage());
        }
    }

    public BranchQRCode createBranchQRCode(ReadableMap readableMap) {
        String string;
        BranchQRCode branchQRCode = new BranchQRCode();
        if (readableMap.hasKey("codeColor")) {
            branchQRCode.setCodeColor(readableMap.getString("codeColor"));
        }
        if (readableMap.hasKey(ViewProps.BACKGROUND_COLOR)) {
            branchQRCode.setBackgroundColor(readableMap.getString(ViewProps.BACKGROUND_COLOR));
        }
        if (readableMap.hasKey("centerLogo")) {
            branchQRCode.setCenterLogo(readableMap.getString("centerLogo"));
        }
        if (readableMap.hasKey("width")) {
            branchQRCode.setWidth(Integer.valueOf(readableMap.getInt("width")));
        }
        if (readableMap.hasKey(ViewProps.MARGIN)) {
            branchQRCode.setMargin(Integer.valueOf(readableMap.getInt(ViewProps.MARGIN)));
        }
        if (readableMap.hasKey("imageFormat") && (string = readableMap.getString("imageFormat")) != null) {
            if (string.equals("JPEG")) {
                branchQRCode.setImageFormat(BranchQRCode.BranchImageFormat.JPEG);
            } else {
                branchQRCode.setImageFormat(BranchQRCode.BranchImageFormat.PNG);
            }
        }
        return branchQRCode;
    }

    public static BranchEvent createBranchEvent(String str, ReadableMap readableMap) {
        BranchEvent branchEvent;
        try {
            branchEvent = new BranchEvent(BRANCH_STANDARD_EVENT.valueOf(str));
        } catch (IllegalArgumentException unused) {
            branchEvent = new BranchEvent(str);
        }
        if (readableMap.hasKey(FirebaseAnalytics.Param.CURRENCY)) {
            String string = readableMap.getString(FirebaseAnalytics.Param.CURRENCY);
            CurrencyType value = CurrencyType.getValue(string);
            if (value != null) {
                branchEvent.setCurrency(value);
            } else {
                Log.w("RNBranch", "Invalid currency " + string);
            }
        }
        if (readableMap.hasKey("transactionID")) {
            branchEvent.setTransactionID(readableMap.getString("transactionID"));
        }
        if (readableMap.hasKey("revenue")) {
            branchEvent.setRevenue(Double.parseDouble(readableMap.getString("revenue")));
        }
        if (readableMap.hasKey(FirebaseAnalytics.Param.SHIPPING)) {
            branchEvent.setShipping(Double.parseDouble(readableMap.getString(FirebaseAnalytics.Param.SHIPPING)));
        }
        if (readableMap.hasKey(FirebaseAnalytics.Param.TAX)) {
            branchEvent.setTax(Double.parseDouble(readableMap.getString(FirebaseAnalytics.Param.TAX)));
        }
        if (readableMap.hasKey(FirebaseAnalytics.Param.COUPON)) {
            branchEvent.setCoupon(readableMap.getString(FirebaseAnalytics.Param.COUPON));
        }
        if (readableMap.hasKey(FirebaseAnalytics.Param.AFFILIATION)) {
            branchEvent.setAffiliation(readableMap.getString(FirebaseAnalytics.Param.AFFILIATION));
        }
        if (readableMap.hasKey("description")) {
            branchEvent.setDescription(readableMap.getString("description"));
        }
        if (readableMap.hasKey("searchQuery")) {
            branchEvent.setSearchQuery(readableMap.getString("searchQuery"));
        }
        if (readableMap.hasKey("alias")) {
            branchEvent.setCustomerEventAlias(readableMap.getString("alias"));
        }
        if (readableMap.hasKey("customData")) {
            ReadableMap map = readableMap.getMap("customData");
            ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                if (map.getType(nextKey) == ReadableType.String) {
                    branchEvent.addCustomDataProperty(nextKey, map.getString(nextKey));
                } else {
                    Log.w("RNBranch", "customData values must be strings. Value for property " + nextKey + " is not a string type.");
                }
            }
        }
        return branchEvent;
    }

    public static LinkProperties createLinkProperties(ReadableMap readableMap, ReadableMap readableMap2) {
        LinkProperties linkProperties = new LinkProperties();
        if (readableMap.hasKey("alias")) {
            linkProperties.setAlias(readableMap.getString("alias"));
        }
        if (readableMap.hasKey("campaign")) {
            linkProperties.setCampaign(readableMap.getString("campaign"));
        }
        if (readableMap.hasKey("channel")) {
            linkProperties.setChannel(readableMap.getString("channel"));
        }
        if (readableMap.hasKey("feature")) {
            linkProperties.setFeature(readableMap.getString("feature"));
        }
        if (readableMap.hasKey("stage")) {
            linkProperties.setStage(readableMap.getString("stage"));
        }
        if (readableMap.hasKey(k.a.g)) {
            ReadableArray array = readableMap.getArray(k.a.g);
            for (int i = 0; i < array.size(); i++) {
                linkProperties.addTag(array.getString(i));
            }
        }
        if (readableMap2 != null) {
            ReadableMapKeySetIterator keySetIterator = readableMap2.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                linkProperties.addControlParameter(nextKey, getReadableMapObjectForKey(readableMap2, nextKey).toString());
            }
        }
        return linkProperties;
    }

    private static Branch setupBranch(Context context) {
        Branch autoInstance = Branch.getAutoInstance(context);
        if (!mInitialized) {
            Log.i("RNBranch", "Initializing Branch SDK v. 5.12.4");
            JSONObject jSONObject = mRequestMetadata;
            if (jSONObject != null) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    try {
                        autoInstance.setRequestMetadata(next, mRequestMetadata.getString(next));
                    } catch (JSONException unused) {
                    }
                }
            }
            mInitialized = true;
        }
        return autoInstance;
    }

    /* access modifiers changed from: private */
    public BranchUniversalObject findUniversalObjectOrReject(String str, Promise promise) {
        BranchUniversalObject branchUniversalObject = (BranchUniversalObject) this.mUniversalObjectMap.get(str);
        if (branchUniversalObject == null) {
            promise.reject(UNIVERSAL_OBJECT_NOT_FOUND_ERROR_CODE, "BranchUniversalObject not found for ident " + str + ".");
        }
        return branchUniversalObject;
    }

    public ContentMetadata createContentMetadata(ReadableMap readableMap) {
        ProductCategory productCategory;
        ContentMetadata contentMetadata = new ContentMetadata();
        if (readableMap.hasKey("contentSchema")) {
            contentMetadata.setContentSchema(BranchContentSchema.valueOf(readableMap.getString("contentSchema")));
        }
        if (readableMap.hasKey(FirebaseAnalytics.Param.QUANTITY)) {
            contentMetadata.setQuantity(Double.valueOf(readableMap.getDouble(FirebaseAnalytics.Param.QUANTITY)));
        }
        Double d = null;
        if (readableMap.hasKey(FirebaseAnalytics.Param.PRICE)) {
            contentMetadata.setPrice(Double.valueOf(Double.parseDouble(readableMap.getString(FirebaseAnalytics.Param.PRICE))), readableMap.hasKey(FirebaseAnalytics.Param.CURRENCY) ? CurrencyType.valueOf(readableMap.getString(FirebaseAnalytics.Param.CURRENCY)) : null);
        }
        if (readableMap.hasKey("sku")) {
            contentMetadata.setSku(readableMap.getString("sku"));
        }
        if (readableMap.hasKey("productName")) {
            contentMetadata.setProductName(readableMap.getString("productName"));
        }
        if (readableMap.hasKey("productBrand")) {
            contentMetadata.setProductBrand(readableMap.getString("productBrand"));
        }
        if (readableMap.hasKey("productCategory") && (productCategory = getProductCategory(readableMap.getString("productCategory"))) != null) {
            contentMetadata.setProductCategory(productCategory);
        }
        if (readableMap.hasKey("productVariant")) {
            contentMetadata.setProductVariant(readableMap.getString("productVariant"));
        }
        if (readableMap.hasKey("condition")) {
            contentMetadata.setProductCondition(ContentMetadata.CONDITION.valueOf(readableMap.getString("condition")));
        }
        if (readableMap.hasKey("ratingAverage") || readableMap.hasKey("ratingMax") || readableMap.hasKey("ratingCount")) {
            contentMetadata.setRating(readableMap.hasKey("ratingAverage") ? Double.valueOf(readableMap.getDouble("ratingAverage")) : null, readableMap.hasKey("ratingMax") ? Double.valueOf(readableMap.getDouble("ratingMax")) : null, readableMap.hasKey("ratingCount") ? Integer.valueOf(readableMap.getInt("ratingCount")) : null);
        }
        if (readableMap.hasKey("addressStreet") || readableMap.hasKey("addressCity") || readableMap.hasKey("addressRegion") || readableMap.hasKey("addressCountry") || readableMap.hasKey("addressPostalCode")) {
            String string = readableMap.hasKey("addressStreet") ? readableMap.getString("addressStreet") : null;
            if (readableMap.hasKey("addressCity")) {
                string = readableMap.getString("addressCity");
            }
            if (readableMap.hasKey("addressRegion")) {
                string = readableMap.getString("addressRegion");
            }
            if (readableMap.hasKey("addressCountry")) {
                string = readableMap.getString("addressCountry");
            }
            if (readableMap.hasKey("addressPostalCode")) {
                string = readableMap.getString("addressPostalCode");
            }
            contentMetadata.setAddress(string, (String) null, (String) null, (String) null, (String) null);
        }
        if (readableMap.hasKey(h.a.b) || readableMap.hasKey(h.a.c)) {
            Double valueOf = readableMap.hasKey(h.a.b) ? Double.valueOf(readableMap.getDouble(h.a.b)) : null;
            if (readableMap.hasKey(h.a.c)) {
                d = Double.valueOf(readableMap.getDouble(h.a.c));
            }
            contentMetadata.setLocation(valueOf, d);
        }
        if (readableMap.hasKey("imageCaptions")) {
            ReadableArray array = readableMap.getArray("imageCaptions");
            for (int i = 0; i < array.size(); i++) {
                contentMetadata.addImageCaptions(array.getString(i));
            }
        }
        if (readableMap.hasKey("customMetadata")) {
            ReadableMap map = readableMap.getMap("customMetadata");
            ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                contentMetadata.addCustomMetadata(nextKey, map.getString(nextKey));
            }
        }
        return contentMetadata;
    }

    public BranchUniversalObject createBranchUniversalObject(ReadableMap readableMap) {
        BranchUniversalObject canonicalIdentifier = new BranchUniversalObject().setCanonicalIdentifier(readableMap.getString("canonicalIdentifier"));
        if (readableMap.hasKey("title")) {
            canonicalIdentifier.setTitle(readableMap.getString("title"));
        }
        if (readableMap.hasKey("canonicalUrl")) {
            canonicalIdentifier.setCanonicalUrl(readableMap.getString("canonicalUrl"));
        }
        if (readableMap.hasKey("contentDescription")) {
            canonicalIdentifier.setContentDescription(readableMap.getString("contentDescription"));
        }
        if (readableMap.hasKey("contentImageUrl")) {
            canonicalIdentifier.setContentImageUrl(readableMap.getString("contentImageUrl"));
        }
        if (readableMap.hasKey("locallyIndex")) {
            if (readableMap.getBoolean("locallyIndex")) {
                canonicalIdentifier.setLocalIndexMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC);
            } else {
                canonicalIdentifier.setLocalIndexMode(BranchUniversalObject.CONTENT_INDEX_MODE.PRIVATE);
            }
        }
        if (readableMap.hasKey("publiclyIndex")) {
            if (readableMap.getBoolean("publiclyIndex")) {
                canonicalIdentifier.setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC);
            } else {
                canonicalIdentifier.setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PRIVATE);
            }
        }
        if (readableMap.hasKey("contentIndexingMode")) {
            if (AnonymousClass10.$SwitchMap$com$facebook$react$bridge$ReadableType[readableMap.getType("contentIndexingMode").ordinal()] != 1) {
                Log.w("RNBranch", "contentIndexingMode must be a String");
            } else {
                String string = readableMap.getString("contentIndexingMode");
                if (string.equals("private")) {
                    canonicalIdentifier.setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PRIVATE);
                } else if (string.equals("public")) {
                    canonicalIdentifier.setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC);
                } else {
                    Log.w("RNBranch", "Unsupported value for contentIndexingMode: " + string + ". Supported values are \"public\" and \"private\"");
                }
            }
        }
        if (readableMap.hasKey(FirebaseAnalytics.Param.CURRENCY) && readableMap.hasKey(FirebaseAnalytics.Param.PRICE)) {
            canonicalIdentifier.setPrice(readableMap.getDouble(FirebaseAnalytics.Param.PRICE), CurrencyType.valueOf(readableMap.getString(FirebaseAnalytics.Param.CURRENCY)));
        }
        if (readableMap.hasKey("expirationDate")) {
            String string2 = readableMap.getString("expirationDate");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                Date parse = simpleDateFormat.parse(string2);
                Log.d("RNBranch", "Expiration date is " + parse.toString());
                canonicalIdentifier.setContentExpiration(parse);
            } catch (ParseException unused) {
                Log.w("RNBranch", "Invalid expiration date format. Valid format is YYYY-mm-ddTHH:MM:SS, e.g. 2017-02-01T00:00:00. All times UTC.");
            }
        }
        if (readableMap.hasKey("keywords")) {
            ReadableArray array = readableMap.getArray("keywords");
            for (int i = 0; i < array.size(); i++) {
                canonicalIdentifier.addKeyWord(array.getString(i));
            }
        }
        if (readableMap.hasKey("metadata")) {
            ReadableMap map = readableMap.getMap("metadata");
            ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                canonicalIdentifier.addContentMetadata(nextKey, getReadableMapObjectForKey(map, nextKey).toString());
                canonicalIdentifier.getMetadata();
            }
        }
        if (readableMap.hasKey("type")) {
            canonicalIdentifier.setContentType(readableMap.getString("type"));
        }
        if (readableMap.hasKey("contentMetadata")) {
            canonicalIdentifier.setContentMetadata(createContentMetadata(readableMap.getMap("contentMetadata")));
        }
        return canonicalIdentifier;
    }

    /* renamed from: io.branch.rnbranch.RNBranchModule$10  reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$react$bridge$ReadableType = r0
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Null     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Boolean     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Map     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Array     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.branch.rnbranch.RNBranchModule.AnonymousClass10.<clinit>():void");
        }
    }

    public ProductCategory getProductCategory(String str) {
        for (ProductCategory productCategory : (ProductCategory[]) ProductCategory.class.getEnumConstants()) {
            if (str.equals(productCategory.getName())) {
                return productCategory;
            }
        }
        Log.w("RNBranch", "Could not find product category " + str);
        return null;
    }

    public void sendRNEvent(String str, WritableMap writableMap) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Handler handler = new Handler(reactApplicationContext.getMainLooper());
        Runnable access$1000 = new Runnable() {
            ReactApplicationContext mContext;
            String mEventName;
            Handler mMainHandler;
            WritableMap mParams;
            final int maxTries = ReactImageView.REMOTE_IMAGE_FADE_DURATION_MS;
            final int pollDelayInMs = 100;
            int tries = 1;

            /* access modifiers changed from: private */
            public Runnable init(ReactApplicationContext reactApplicationContext, Handler handler, String str, WritableMap writableMap) {
                this.mMainHandler = handler;
                this.mEventName = str;
                this.mContext = reactApplicationContext;
                this.mParams = writableMap;
                return this;
            }

            public void run() {
                try {
                    Log.d("RNBranch", "Catalyst instance poller try " + Integer.toString(this.tries));
                    if (this.mContext.hasActiveCatalystInstance()) {
                        Log.d("RNBranch", "Catalyst instance active");
                        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(this.mEventName, this.mParams);
                        return;
                    }
                    int i = this.tries + 1;
                    this.tries = i;
                    if (i <= 300) {
                        this.mMainHandler.postDelayed(this, 100);
                    } else {
                        Log.e("RNBranch", "Could not get Catalyst instance");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.init(reactApplicationContext, handler, str, writableMap);
        Log.d("RNBranch", "sendRNEvent");
        handler.post(access$1000);
    }

    private static Object getReadableMapObjectForKey(ReadableMap readableMap, String str) {
        int i = AnonymousClass10.$SwitchMap$com$facebook$react$bridge$ReadableType[readableMap.getType(str).ordinal()];
        if (i == 1) {
            return readableMap.getString(str);
        }
        if (i == 2) {
            return "Null";
        }
        if (i == 3) {
            return Boolean.valueOf(readableMap.getBoolean(str));
        }
        if (i != 4) {
            return "Unsupported Type";
        }
        if (readableMap.getDouble(str) % 1.0d == 0.0d) {
            return Integer.valueOf(readableMap.getInt(str));
        }
        return Double.valueOf(readableMap.getDouble(str));
    }

    private static JSONObject convertMapToJson(ReadableMap readableMap) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            switch (AnonymousClass10.$SwitchMap$com$facebook$react$bridge$ReadableType[readableMap.getType(nextKey).ordinal()]) {
                case 1:
                    jSONObject.put(nextKey, readableMap.getString(nextKey));
                    break;
                case 2:
                    jSONObject.put(nextKey, JSONObject.NULL);
                    break;
                case 3:
                    jSONObject.put(nextKey, readableMap.getBoolean(nextKey));
                    break;
                case 4:
                    jSONObject.put(nextKey, readableMap.getDouble(nextKey));
                    break;
                case 5:
                    jSONObject.put(nextKey, convertMapToJson(readableMap.getMap(nextKey)));
                    break;
                case 6:
                    jSONObject.put(nextKey, convertArrayToJson(readableMap.getArray(nextKey)));
                    break;
            }
        }
        return jSONObject;
    }

    private static JSONArray convertArrayToJson(ReadableArray readableArray) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < readableArray.size(); i++) {
            int i2 = AnonymousClass10.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i).ordinal()];
            if (i2 == 1) {
                jSONArray.put(readableArray.getString(i));
            } else if (i2 == 3) {
                jSONArray.put(readableArray.getBoolean(i));
            } else if (i2 == 4) {
                jSONArray.put(readableArray.getDouble(i));
            } else if (i2 == 5) {
                jSONArray.put(convertMapToJson(readableArray.getMap(i)));
            } else if (i2 == 6) {
                jSONArray.put(convertArrayToJson(readableArray.getArray(i)));
            }
        }
        return jSONArray;
    }

    /* access modifiers changed from: private */
    public static WritableMap convertJsonToMap(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = jSONObject.get(next);
                if (obj instanceof JSONObject) {
                    writableNativeMap.putMap(next, convertJsonToMap((JSONObject) obj));
                } else if (obj instanceof JSONArray) {
                    writableNativeMap.putArray(next, convertJsonToArray((JSONArray) obj));
                } else if (obj instanceof Boolean) {
                    writableNativeMap.putBoolean(next, ((Boolean) obj).booleanValue());
                } else if (obj instanceof Integer) {
                    writableNativeMap.putInt(next, ((Integer) obj).intValue());
                } else if (obj instanceof Double) {
                    writableNativeMap.putDouble(next, ((Double) obj).doubleValue());
                } else if (obj instanceof String) {
                    writableNativeMap.putString(next, (String) obj);
                } else {
                    if (obj != null) {
                        if (obj != JSONObject.NULL) {
                            writableNativeMap.putString(next, obj.toString());
                        }
                    }
                    writableNativeMap.putNull(next);
                }
            }
        } catch (JSONException e) {
            writableNativeMap.putString("error", "Failed to convert JSONObject to WriteableMap: " + e.getMessage());
        }
        return writableNativeMap;
    }

    private static WritableArray convertJsonToArray(JSONArray jSONArray) throws JSONException {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONObject) {
                writableNativeArray.pushMap(convertJsonToMap((JSONObject) obj));
            } else if (obj instanceof JSONArray) {
                writableNativeArray.pushArray(convertJsonToArray((JSONArray) obj));
            } else if (obj instanceof Boolean) {
                writableNativeArray.pushBoolean(((Boolean) obj).booleanValue());
            } else if (obj instanceof Integer) {
                writableNativeArray.pushInt(((Integer) obj).intValue());
            } else if (obj instanceof Double) {
                writableNativeArray.pushDouble(((Double) obj).doubleValue());
            } else if (obj instanceof String) {
                writableNativeArray.pushString((String) obj);
            } else {
                writableNativeArray.pushString(obj.toString());
            }
        }
        return writableNativeArray;
    }

    @ReactMethod
    public void setDMAParamsForEEA(boolean z, boolean z2, boolean z3) {
        Branch.getInstance().setDMAParamsForEEA(z, z2, z3);
    }
}
