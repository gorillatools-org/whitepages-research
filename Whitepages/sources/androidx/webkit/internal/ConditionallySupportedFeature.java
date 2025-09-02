package androidx.webkit.internal;

public interface ConditionallySupportedFeature {
    String getPublicFeatureName();

    boolean isSupported();
}
