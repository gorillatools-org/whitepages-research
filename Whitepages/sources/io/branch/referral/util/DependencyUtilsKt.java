package io.branch.referral.util;

import io.branch.referral.BranchLogger;
import kotlin.jvm.internal.Intrinsics;

public abstract class DependencyUtilsKt {
    public static final boolean classExists(String str) {
        Intrinsics.checkNotNullParameter(str, "className");
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            BranchLogger.v("Could not find " + str + ". If expected, import the dependency into your app.");
            return false;
        }
    }
}
