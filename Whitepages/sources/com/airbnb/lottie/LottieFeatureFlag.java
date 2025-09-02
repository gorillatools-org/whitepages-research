package com.airbnb.lottie;

public enum LottieFeatureFlag {
    MergePathsApi19(19);
    
    public final int minRequiredSdkVersion;

    private LottieFeatureFlag(int i) {
        this.minRequiredSdkVersion = i;
    }
}
