package com.airbnb.lottie;

import android.content.Context;
import com.airbnb.lottie.configurations.reducemotion.ReducedMotionOption;
import com.airbnb.lottie.configurations.reducemotion.SystemReducedMotionOption;
import com.airbnb.lottie.network.DefaultLottieNetworkFetcher;
import com.airbnb.lottie.network.LottieNetworkCacheProvider;
import com.airbnb.lottie.network.LottieNetworkFetcher;
import com.airbnb.lottie.network.NetworkCache;
import com.airbnb.lottie.network.NetworkFetcher;
import com.airbnb.lottie.utils.LottieTrace;
import java.io.File;

public abstract class L {
    public static boolean DBG = false;
    private static LottieNetworkCacheProvider cacheProvider = null;
    private static AsyncUpdates defaultAsyncUpdates = AsyncUpdates.AUTOMATIC;
    private static boolean disablePathInterpolatorCache = true;
    private static LottieNetworkFetcher fetcher = null;
    private static ThreadLocal lottieTrace = null;
    private static volatile NetworkCache networkCache = null;
    private static boolean networkCacheEnabled = true;
    private static volatile NetworkFetcher networkFetcher = null;
    private static ReducedMotionOption reducedMotionOption = new SystemReducedMotionOption();
    private static boolean traceEnabled = false;

    public static boolean isTraceEnabled() {
        return traceEnabled;
    }

    public static void beginSection(String str) {
        if (traceEnabled) {
            getTrace().beginSection(str);
        }
    }

    public static float endSection(String str) {
        if (!traceEnabled) {
            return 0.0f;
        }
        return getTrace().endSection(str);
    }

    private static LottieTrace getTrace() {
        LottieTrace lottieTrace2 = (LottieTrace) lottieTrace.get();
        if (lottieTrace2 != null) {
            return lottieTrace2;
        }
        LottieTrace lottieTrace3 = new LottieTrace();
        lottieTrace.set(lottieTrace3);
        return lottieTrace3;
    }

    public static NetworkFetcher networkFetcher(Context context) {
        NetworkFetcher networkFetcher2 = networkFetcher;
        if (networkFetcher2 == null) {
            synchronized (NetworkFetcher.class) {
                try {
                    networkFetcher2 = networkFetcher;
                    if (networkFetcher2 == null) {
                        NetworkCache networkCache2 = networkCache(context);
                        LottieNetworkFetcher lottieNetworkFetcher = fetcher;
                        if (lottieNetworkFetcher == null) {
                            lottieNetworkFetcher = new DefaultLottieNetworkFetcher();
                        }
                        networkFetcher2 = new NetworkFetcher(networkCache2, lottieNetworkFetcher);
                        networkFetcher = networkFetcher2;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return networkFetcher2;
    }

    public static NetworkCache networkCache(Context context) {
        if (!networkCacheEnabled) {
            return null;
        }
        Context applicationContext = context.getApplicationContext();
        NetworkCache networkCache2 = networkCache;
        if (networkCache2 == null) {
            synchronized (NetworkCache.class) {
                try {
                    networkCache2 = networkCache;
                    if (networkCache2 == null) {
                        LottieNetworkCacheProvider lottieNetworkCacheProvider = cacheProvider;
                        if (lottieNetworkCacheProvider == null) {
                            lottieNetworkCacheProvider = new L$$ExternalSyntheticLambda0(applicationContext);
                        }
                        networkCache2 = new NetworkCache(lottieNetworkCacheProvider);
                        networkCache = networkCache2;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return networkCache2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ File lambda$networkCache$0(Context context) {
        return new File(context.getCacheDir(), "lottie_network_cache");
    }

    public static boolean getDisablePathInterpolatorCache() {
        return disablePathInterpolatorCache;
    }

    public static AsyncUpdates getDefaultAsyncUpdates() {
        return defaultAsyncUpdates;
    }

    public static ReducedMotionOption getReducedMotionOption() {
        return reducedMotionOption;
    }
}
