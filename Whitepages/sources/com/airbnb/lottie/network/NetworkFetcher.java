package com.airbnb.lottie.network;

import android.content.Context;
import android.util.Pair;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieResult;
import com.airbnb.lottie.utils.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

public class NetworkFetcher {
    private final LottieNetworkFetcher fetcher;
    private final NetworkCache networkCache;

    public NetworkFetcher(NetworkCache networkCache2, LottieNetworkFetcher lottieNetworkFetcher) {
        this.networkCache = networkCache2;
        this.fetcher = lottieNetworkFetcher;
    }

    public LottieResult fetchSync(Context context, String str, String str2) {
        LottieComposition fetchFromCache = fetchFromCache(context, str, str2);
        if (fetchFromCache != null) {
            return new LottieResult((Object) fetchFromCache);
        }
        Logger.debug("Animation for " + str + " not found in cache. Fetching from network.");
        return fetchFromNetwork(context, str, str2);
    }

    private LottieComposition fetchFromCache(Context context, String str, String str2) {
        NetworkCache networkCache2;
        Pair fetch;
        LottieResult lottieResult;
        if (str2 == null || (networkCache2 = this.networkCache) == null || (fetch = networkCache2.fetch(str)) == null) {
            return null;
        }
        InputStream inputStream = (InputStream) fetch.second;
        int i = AnonymousClass1.$SwitchMap$com$airbnb$lottie$network$FileExtension[((FileExtension) fetch.first).ordinal()];
        if (i == 1) {
            lottieResult = LottieCompositionFactory.fromZipStreamSync(context, new ZipInputStream(inputStream), str2);
        } else if (i != 2) {
            lottieResult = LottieCompositionFactory.fromJsonInputStreamSync(inputStream, str2);
        } else {
            try {
                lottieResult = LottieCompositionFactory.fromJsonInputStreamSync(new GZIPInputStream(inputStream), str2);
            } catch (IOException e) {
                lottieResult = new LottieResult((Throwable) e);
            }
        }
        if (lottieResult.getValue() != null) {
            return (LottieComposition) lottieResult.getValue();
        }
        return null;
    }

    /* renamed from: com.airbnb.lottie.network.NetworkFetcher$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$network$FileExtension;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.airbnb.lottie.network.FileExtension[] r0 = com.airbnb.lottie.network.FileExtension.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$airbnb$lottie$network$FileExtension = r0
                com.airbnb.lottie.network.FileExtension r1 = com.airbnb.lottie.network.FileExtension.ZIP     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$airbnb$lottie$network$FileExtension     // Catch:{ NoSuchFieldError -> 0x001d }
                com.airbnb.lottie.network.FileExtension r1 = com.airbnb.lottie.network.FileExtension.GZIP     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.network.NetworkFetcher.AnonymousClass1.<clinit>():void");
        }
    }

    private LottieResult fetchFromNetwork(Context context, String str, String str2) {
        Logger.debug("Fetching " + str);
        LottieFetchResult lottieFetchResult = null;
        try {
            lottieFetchResult = this.fetcher.fetchSync(str);
            if (lottieFetchResult.isSuccessful()) {
                LottieResult fromInputStream = fromInputStream(context, str, lottieFetchResult.bodyByteStream(), lottieFetchResult.contentType(), str2);
                StringBuilder sb = new StringBuilder();
                sb.append("Completed fetch from network. Success: ");
                sb.append(fromInputStream.getValue() != null);
                Logger.debug(sb.toString());
                try {
                    lottieFetchResult.close();
                } catch (IOException e) {
                    Logger.warning("LottieFetchResult close failed ", e);
                }
                return fromInputStream;
            }
            LottieResult lottieResult = new LottieResult((Throwable) new IllegalArgumentException(lottieFetchResult.error()));
            try {
                lottieFetchResult.close();
            } catch (IOException e2) {
                Logger.warning("LottieFetchResult close failed ", e2);
            }
            return lottieResult;
        } catch (Exception e3) {
            LottieResult lottieResult2 = new LottieResult((Throwable) e3);
            if (lottieFetchResult != null) {
                try {
                    lottieFetchResult.close();
                } catch (IOException e4) {
                    Logger.warning("LottieFetchResult close failed ", e4);
                }
            }
            return lottieResult2;
        } catch (Throwable th) {
            if (lottieFetchResult != null) {
                try {
                    lottieFetchResult.close();
                } catch (IOException e5) {
                    Logger.warning("LottieFetchResult close failed ", e5);
                }
            }
            throw th;
        }
    }

    private LottieResult fromInputStream(Context context, String str, InputStream inputStream, String str2, String str3) {
        LottieResult lottieResult;
        FileExtension fileExtension;
        NetworkCache networkCache2;
        if (str2 == null) {
            str2 = "application/json";
        }
        if (str2.contains("application/zip") || str2.contains("application/x-zip") || str2.contains("application/x-zip-compressed") || str.split("\\?")[0].endsWith(".lottie")) {
            Logger.debug("Handling zip response.");
            FileExtension fileExtension2 = FileExtension.ZIP;
            lottieResult = fromZipStream(context, str, inputStream, str3);
            fileExtension = fileExtension2;
        } else if (str2.contains("application/gzip") || str2.contains("application/x-gzip") || str.split("\\?")[0].endsWith(".tgs")) {
            Logger.debug("Handling gzip response.");
            fileExtension = FileExtension.GZIP;
            lottieResult = fromGzipStream(str, inputStream, str3);
        } else {
            Logger.debug("Received json response.");
            fileExtension = FileExtension.JSON;
            lottieResult = fromJsonStream(str, inputStream, str3);
        }
        if (!(str3 == null || lottieResult.getValue() == null || (networkCache2 = this.networkCache) == null)) {
            networkCache2.renameTempFile(str, fileExtension);
        }
        return lottieResult;
    }

    private LottieResult fromZipStream(Context context, String str, InputStream inputStream, String str2) {
        NetworkCache networkCache2;
        if (str2 == null || (networkCache2 = this.networkCache) == null) {
            return LottieCompositionFactory.fromZipStreamSync(context, new ZipInputStream(inputStream), (String) null);
        }
        return LottieCompositionFactory.fromZipStreamSync(context, new ZipInputStream(new FileInputStream(networkCache2.writeTempCacheFile(str, inputStream, FileExtension.ZIP))), str);
    }

    private LottieResult fromGzipStream(String str, InputStream inputStream, String str2) {
        NetworkCache networkCache2;
        if (str2 == null || (networkCache2 = this.networkCache) == null) {
            return LottieCompositionFactory.fromJsonInputStreamSync(new GZIPInputStream(inputStream), (String) null);
        }
        return LottieCompositionFactory.fromJsonInputStreamSync(new GZIPInputStream(new FileInputStream(networkCache2.writeTempCacheFile(str, inputStream, FileExtension.GZIP))), str);
    }

    private LottieResult fromJsonStream(String str, InputStream inputStream, String str2) {
        NetworkCache networkCache2;
        if (str2 == null || (networkCache2 = this.networkCache) == null) {
            return LottieCompositionFactory.fromJsonInputStreamSync(inputStream, (String) null);
        }
        return LottieCompositionFactory.fromJsonInputStreamSync(new FileInputStream(networkCache2.writeTempCacheFile(str, inputStream, FileExtension.JSON).getAbsolutePath()), str);
    }
}
