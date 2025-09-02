package com.salesforce.marketingcloud.reactnative;

import android.util.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.salesforce.marketingcloud.MCLogListener;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdk;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReadyListener;
import com.salesforce.marketingcloud.sfmcsdk.components.identity.Identity;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.LogLevel;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.LogListener;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleInterface;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleInterface;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener;
import java.util.Map;
import java.util.Set;

public class RNSFMCSdkModule extends ReactContextBaseJavaModule {
    private static int MAX_LOG_LENGTH = 4000;

    public RNSFMCSdkModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        handlePushAction(new MCPushAction() {
            /* access modifiers changed from: package-private */
            public void execute(PushModuleInterface pushModuleInterface) {
                pushModuleInterface.getRegistrationManager().edit().addTag("React").commit();
            }
        });
    }

    public String getName() {
        return "RNSFMCSdk";
    }

    @ReactMethod
    public void logSdkState() {
        handleAction(new SFMCAction() {
            /* access modifiers changed from: package-private */
            public void execute(SFMCSdk sFMCSdk) {
                try {
                    RNSFMCSdkModule.log("~#RNMCSdkModule", "SDK State: " + sFMCSdk.getSdkState().toString(2));
                } catch (Exception unused) {
                }
            }
        });
    }

    @ReactMethod
    public void enableLogging() {
        SFMCSdk.Companion.setLogging(LogLevel.DEBUG, new LogListener.AndroidLogger());
        MarketingCloudSdk.setLogLevel(2);
        MarketingCloudSdk.setLogListener(new MCLogListener.AndroidLogListener());
    }

    @ReactMethod
    public void disableLogging() {
        SFMCSdk.Companion.setLogging(LogLevel.NONE, (LogListener) null);
        MarketingCloudSdk.setLogListener((MCLogListener) null);
    }

    @ReactMethod
    public void getSystemToken(final Promise promise) {
        handlePushAction(new MCPushAction() {
            /* access modifiers changed from: package-private */
            public void execute(PushModuleInterface pushModuleInterface) {
                promise.resolve(pushModuleInterface.getPushMessageManager().getPushToken());
            }
        });
    }

    @ReactMethod
    public void isPushEnabled(final Promise promise) {
        handlePushAction(new MCPushAction() {
            /* access modifiers changed from: package-private */
            public void execute(PushModuleInterface pushModuleInterface) {
                promise.resolve(Boolean.valueOf(pushModuleInterface.getPushMessageManager().isPushEnabled()));
            }
        });
    }

    @ReactMethod
    public void enablePush() {
        handlePushAction(new MCPushAction() {
            /* access modifiers changed from: package-private */
            public void execute(PushModuleInterface pushModuleInterface) {
                pushModuleInterface.getPushMessageManager().enablePush();
            }
        });
    }

    @ReactMethod
    public void disablePush() {
        handlePushAction(new MCPushAction() {
            /* access modifiers changed from: package-private */
            public void execute(PushModuleInterface pushModuleInterface) {
                pushModuleInterface.getPushMessageManager().disablePush();
            }
        });
    }

    @ReactMethod
    public void getDeviceId(final Promise promise) {
        handlePushAction(new MCPushAction() {
            /* access modifiers changed from: package-private */
            public void execute(PushModuleInterface pushModuleInterface) {
                promise.resolve(pushModuleInterface.getRegistrationManager().getDeviceId());
            }
        });
    }

    @ReactMethod
    public void getTags(final Promise promise) {
        handlePushAction(new MCPushAction() {
            /* access modifiers changed from: package-private */
            public void execute(PushModuleInterface pushModuleInterface) {
                Set<String> tags = pushModuleInterface.getRegistrationManager().getTags();
                WritableNativeArray writableNativeArray = new WritableNativeArray();
                if (!tags.isEmpty()) {
                    for (String pushString : tags) {
                        writableNativeArray.pushString(pushString);
                    }
                }
                promise.resolve(writableNativeArray);
            }
        });
    }

    @ReactMethod
    public void addTag(final String str) {
        handlePushAction(new MCPushAction() {
            /* access modifiers changed from: package-private */
            public void execute(PushModuleInterface pushModuleInterface) {
                pushModuleInterface.getRegistrationManager().edit().addTag(str).commit();
            }
        });
    }

    @ReactMethod
    public void removeTag(final String str) {
        handlePushAction(new MCPushAction() {
            /* access modifiers changed from: package-private */
            public void execute(PushModuleInterface pushModuleInterface) {
                pushModuleInterface.getRegistrationManager().edit().removeTag(str).commit();
            }
        });
    }

    @ReactMethod
    public void getContactKey(final Promise promise) {
        handlePushAction(new MCPushAction() {
            /* access modifiers changed from: package-private */
            public void execute(PushModuleInterface pushModuleInterface) {
                promise.resolve(pushModuleInterface.getRegistrationManager().getContactKey());
            }
        });
    }

    @ReactMethod
    public void setContactKey(final String str) {
        handleIdentityAction(new SFMCIdentityAction() {
            /* access modifiers changed from: package-private */
            public void execute(Identity identity) {
                identity.setProfileId(str);
            }
        });
    }

    @ReactMethod
    public void getAttributes(final Promise promise) {
        handlePushAction(new MCPushAction() {
            /* access modifiers changed from: package-private */
            public void execute(PushModuleInterface pushModuleInterface) {
                Map<String, String> attributes = pushModuleInterface.getRegistrationManager().getAttributes();
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                if (!attributes.isEmpty()) {
                    for (Map.Entry next : attributes.entrySet()) {
                        writableNativeMap.putString((String) next.getKey(), (String) next.getValue());
                    }
                }
                promise.resolve(writableNativeMap);
            }
        });
    }

    @ReactMethod
    public void setAttribute(final String str, final String str2) {
        handleIdentityAction(new SFMCIdentityAction() {
            /* access modifiers changed from: package-private */
            public void execute(Identity identity) {
                identity.setProfileAttribute(str, str2);
            }
        });
    }

    @ReactMethod
    public void clearAttribute(final String str) {
        handleIdentityAction(new SFMCIdentityAction() {
            /* access modifiers changed from: package-private */
            public void execute(Identity identity) {
                identity.clearProfileAttribute(str);
            }
        });
    }

    @ReactMethod
    public void track(ReadableMap readableMap) {
        SFMCSdk.track(EventUtility.toEvent(readableMap));
    }

    @ReactMethod
    public void isAnalyticsEnabled(final Promise promise) {
        handlePushAction(new MCPushAction() {
            /* access modifiers changed from: package-private */
            public void execute(PushModuleInterface pushModuleInterface) {
                promise.resolve(Boolean.valueOf(pushModuleInterface.getAnalyticsManager().areAnalyticsEnabled()));
            }
        });
    }

    @ReactMethod
    public void setAnalyticsEnabled(final Boolean bool) {
        handlePushAction(new MCPushAction() {
            /* access modifiers changed from: package-private */
            public void execute(PushModuleInterface pushModuleInterface) {
                if (bool.booleanValue()) {
                    pushModuleInterface.getAnalyticsManager().enableAnalytics();
                } else {
                    pushModuleInterface.getAnalyticsManager().disableAnalytics();
                }
            }
        });
    }

    @ReactMethod
    public void isPiAnalyticsEnabled(final Promise promise) {
        handlePushAction(new MCPushAction() {
            /* access modifiers changed from: package-private */
            public void execute(PushModuleInterface pushModuleInterface) {
                promise.resolve(Boolean.valueOf(pushModuleInterface.getAnalyticsManager().arePiAnalyticsEnabled()));
            }
        });
    }

    @ReactMethod
    public void setPiAnalyticsEnabled(final Boolean bool) {
        handlePushAction(new MCPushAction() {
            /* access modifiers changed from: package-private */
            public void execute(PushModuleInterface pushModuleInterface) {
                if (bool.booleanValue()) {
                    pushModuleInterface.getAnalyticsManager().enablePiAnalytics();
                } else {
                    pushModuleInterface.getAnalyticsManager().disablePiAnalytics();
                }
            }
        });
    }

    private void handleAction(final SFMCAction sFMCAction) {
        SFMCSdk.requestSdk(new SFMCSdkReadyListener() {
            public void ready(SFMCSdk sFMCSdk) {
                sFMCAction.execute(sFMCSdk);
            }
        });
    }

    private void handlePushAction(final MCPushAction mCPushAction) {
        SFMCSdk.requestSdk(new SFMCSdkReadyListener() {
            public void ready(SFMCSdk sFMCSdk) {
                sFMCSdk.mp(new PushModuleReadyListener() {
                    public void ready(PushModuleInterface pushModuleInterface) {
                        mCPushAction.execute(pushModuleInterface);
                    }

                    public void ready(ModuleInterface moduleInterface) {
                        ready((PushModuleInterface) moduleInterface);
                    }
                });
            }
        });
    }

    private void handleIdentityAction(final SFMCIdentityAction sFMCIdentityAction) {
        SFMCSdk.requestSdk(new SFMCSdkReadyListener() {
            public void ready(SFMCSdk sFMCSdk) {
                sFMCIdentityAction.execute(sFMCSdk.identity);
            }
        });
    }

    /* access modifiers changed from: private */
    public static void log(String str, String str2) {
        int length = str2.length();
        for (int i = 0; i < length; i += MAX_LOG_LENGTH) {
            Log.println(3, str, str2.substring(i, Math.min(length, MAX_LOG_LENGTH + i)));
        }
    }

    abstract class SFMCAction {
        /* access modifiers changed from: package-private */
        public void err() {
        }

        /* access modifiers changed from: package-private */
        public abstract void execute(SFMCSdk sFMCSdk);

        SFMCAction() {
        }
    }

    abstract class SFMCPromiseAction extends SFMCAction {
        private final Promise promise;

        /* access modifiers changed from: package-private */
        public abstract void execute(SFMCSdk sFMCSdk, Promise promise2);

        SFMCPromiseAction(Promise promise2) {
            super();
            this.promise = promise2;
        }

        /* access modifiers changed from: package-private */
        public final void execute(SFMCSdk sFMCSdk) {
            execute(sFMCSdk, this.promise);
        }

        /* access modifiers changed from: package-private */
        public void err() {
            this.promise.reject("SFMCSDK-INIT", "The MarketingCloudSdk#init method must be called in the Application's onCreate.");
        }
    }

    abstract class MCPushAction {
        /* access modifiers changed from: package-private */
        public void err() {
        }

        /* access modifiers changed from: package-private */
        public abstract void execute(PushModuleInterface pushModuleInterface);

        MCPushAction() {
        }
    }

    abstract class MCPushPromiseAction extends MCPushAction {
        private final Promise promise;

        /* access modifiers changed from: package-private */
        public abstract void execute(PushModuleInterface pushModuleInterface, Promise promise2);

        MCPushPromiseAction(Promise promise2) {
            super();
            this.promise = promise2;
        }

        /* access modifiers changed from: package-private */
        public final void execute(PushModuleInterface pushModuleInterface) {
            execute(pushModuleInterface, this.promise);
        }

        /* access modifiers changed from: package-private */
        public void err() {
            this.promise.reject("SFMCSDK-INIT", "The MarketingCloudSdk#init method must be called in the Application's onCreate.");
        }
    }

    abstract class SFMCIdentityAction {
        /* access modifiers changed from: package-private */
        public void err() {
        }

        /* access modifiers changed from: package-private */
        public abstract void execute(Identity identity);

        SFMCIdentityAction() {
        }
    }

    abstract class SFMCIdentityPromiseAction extends SFMCIdentityAction {
        private final Promise promise;

        /* access modifiers changed from: package-private */
        public abstract void execute(Identity identity, Promise promise2);

        SFMCIdentityPromiseAction(Promise promise2) {
            super();
            this.promise = promise2;
        }

        /* access modifiers changed from: package-private */
        public final void execute(Identity identity) {
            execute(identity, this.promise);
        }

        /* access modifiers changed from: package-private */
        public void err() {
            this.promise.reject("SFMCSDK-INIT", "The SFMCSdk#configure method must be called in the Application's onCreate.");
        }
    }
}
