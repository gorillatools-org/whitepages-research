package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
import kotlin.jvm.internal.Intrinsics;

@DoNotStrip
public interface JavaJSExecutor {

    public interface Factory {
        JavaJSExecutor create() throws Exception;
    }

    void close();

    @DoNotStrip
    String executeJSCall(String str, String str2) throws ProxyExecutorException;

    @DoNotStrip
    void loadBundle(String str) throws ProxyExecutorException;

    @DoNotStrip
    void setGlobalVariable(String str, String str2);

    public static final class ProxyExecutorException extends Exception {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ProxyExecutorException(Throwable th) {
            super(th);
            Intrinsics.checkNotNullParameter(th, "cause");
        }
    }
}
