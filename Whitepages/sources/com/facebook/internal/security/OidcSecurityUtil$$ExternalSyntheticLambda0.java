package com.facebook.internal.security;

import java.net.URL;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.jvm.internal.Ref$ObjectRef;

public final /* synthetic */ class OidcSecurityUtil$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ URL f$0;
    public final /* synthetic */ Ref$ObjectRef f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ ReentrantLock f$3;
    public final /* synthetic */ Condition f$4;

    public /* synthetic */ OidcSecurityUtil$$ExternalSyntheticLambda0(URL url, Ref$ObjectRef ref$ObjectRef, String str, ReentrantLock reentrantLock, Condition condition) {
        this.f$0 = url;
        this.f$1 = ref$ObjectRef;
        this.f$2 = str;
        this.f$3 = reentrantLock;
        this.f$4 = condition;
    }

    public final void run() {
        OidcSecurityUtil.getRawKeyFromEndPoint$lambda$1(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
