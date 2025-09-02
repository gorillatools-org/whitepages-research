package com.airbnb.lottie;

import android.os.Build;
import com.airbnb.lottie.utils.Logger;
import java.util.HashSet;

class LottieFeatureFlags {
    private final HashSet enabledFlags = new HashSet();

    LottieFeatureFlags() {
    }

    public boolean enableFlag(LottieFeatureFlag lottieFeatureFlag, boolean z) {
        if (!z) {
            return this.enabledFlags.remove(lottieFeatureFlag);
        }
        if (Build.VERSION.SDK_INT >= lottieFeatureFlag.minRequiredSdkVersion) {
            return this.enabledFlags.add(lottieFeatureFlag);
        }
        Logger.warning(String.format("%s is not supported pre SDK %d", new Object[]{lottieFeatureFlag.name(), Integer.valueOf(lottieFeatureFlag.minRequiredSdkVersion)}));
        return false;
    }

    public boolean isFlagEnabled(LottieFeatureFlag lottieFeatureFlag) {
        return this.enabledFlags.contains(lottieFeatureFlag);
    }
}
