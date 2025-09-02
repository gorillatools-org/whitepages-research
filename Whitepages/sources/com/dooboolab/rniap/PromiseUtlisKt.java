package com.dooboolab.rniap;

import android.util.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.devsupport.StackTraceHelper;
import kotlin.jvm.internal.Intrinsics;

public abstract class PromiseUtlisKt {
    public static final void safeResolve(Promise promise, Object obj) {
        Intrinsics.checkNotNullParameter(promise, "<this>");
        try {
            promise.resolve(obj);
        } catch (RuntimeException e) {
            Log.d("IapPromises", "Already consumed " + e.getMessage());
        }
    }

    public static final void safeReject(Promise promise, String str) {
        Intrinsics.checkNotNullParameter(promise, "<this>");
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        safeReject(promise, str, (String) null, (Throwable) null);
    }

    public static final void safeReject(Promise promise, String str, String str2) {
        Intrinsics.checkNotNullParameter(promise, "<this>");
        Intrinsics.checkNotNullParameter(str, "code");
        safeReject(promise, str, str2, (Throwable) null);
    }

    public static final void safeReject(Promise promise, String str, String str2, Throwable th) {
        Intrinsics.checkNotNullParameter(promise, "<this>");
        Intrinsics.checkNotNullParameter(str, "code");
        try {
            promise.reject(str, str2, th);
        } catch (RuntimeException e) {
            Log.d("IapPromises", "Already consumed " + e.getMessage());
        }
    }
}
