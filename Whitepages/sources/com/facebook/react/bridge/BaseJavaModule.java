package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.build.ReactBuildConfig;
import java.util.Map;

public abstract class BaseJavaModule implements NativeModule {
    public static final String METHOD_TYPE_ASYNC = "async";
    public static final String METHOD_TYPE_PROMISE = "promise";
    public static final String METHOD_TYPE_SYNC = "sync";
    protected CxxCallbackImpl mEventEmitterCallback;
    private final ReactApplicationContext mReactApplicationContext;

    public boolean canOverrideExistingModule() {
        return false;
    }

    public Map<String, Object> getConstants() {
        return null;
    }

    public void initialize() {
    }

    public void invalidate() {
    }

    public BaseJavaModule() {
        this((ReactApplicationContext) null);
    }

    public BaseJavaModule(ReactApplicationContext reactApplicationContext) {
        this.mReactApplicationContext = reactApplicationContext;
    }

    /* access modifiers changed from: protected */
    public final ReactApplicationContext getReactApplicationContext() {
        return (ReactApplicationContext) Assertions.assertNotNull(this.mReactApplicationContext, "Tried to get ReactApplicationContext even though NativeModule wasn't instantiated with one");
    }

    /* access modifiers changed from: protected */
    public final ReactApplicationContext getReactApplicationContextIfActiveOrWarn() {
        if (this.mReactApplicationContext.hasActiveReactInstance()) {
            return this.mReactApplicationContext;
        }
        String str = "React Native Instance has already disappeared: requested by " + getName();
        if (ReactBuildConfig.DEBUG) {
            FLog.w(ReactConstants.TAG, str);
            return null;
        }
        ReactSoftExceptionLogger.logSoftException(ReactConstants.TAG, new RuntimeException(str));
        return null;
    }

    /* access modifiers changed from: protected */
    @DoNotStrip
    public void setEventEmitterCallback(CxxCallbackImpl cxxCallbackImpl) {
        this.mEventEmitterCallback = cxxCallbackImpl;
    }
}
