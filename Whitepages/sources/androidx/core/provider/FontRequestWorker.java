package androidx.core.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.provider.FontsContractCompat;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

abstract class FontRequestWorker {
    private static final ExecutorService DEFAULT_EXECUTOR_SERVICE = RequestExecutor.createDefaultExecutor("fonts-androidx", 10, 10000);
    static final Object LOCK = new Object();
    static final SimpleArrayMap PENDING_REPLIES = new SimpleArrayMap();
    static final LruCache sTypefaceCache = new LruCache(16);

    static Typeface requestFontSync(final Context context, final FontRequest fontRequest, CallbackWithHandler callbackWithHandler, final int i, int i2) {
        final String createCacheId = createCacheId(fontRequest, i);
        Typeface typeface = (Typeface) sTypefaceCache.get(createCacheId);
        if (typeface != null) {
            callbackWithHandler.onTypefaceResult(new TypefaceResult(typeface));
            return typeface;
        } else if (i2 == -1) {
            TypefaceResult fontSync = getFontSync(createCacheId, context, fontRequest, i);
            callbackWithHandler.onTypefaceResult(fontSync);
            return fontSync.mTypeface;
        } else {
            try {
                TypefaceResult typefaceResult = (TypefaceResult) RequestExecutor.submit(DEFAULT_EXECUTOR_SERVICE, new Callable() {
                    public TypefaceResult call() {
                        return FontRequestWorker.getFontSync(createCacheId, context, fontRequest, i);
                    }
                }, i2);
                callbackWithHandler.onTypefaceResult(typefaceResult);
                return typefaceResult.mTypeface;
            } catch (InterruptedException unused) {
                callbackWithHandler.onTypefaceResult(new TypefaceResult(-3));
                return null;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003d, code lost:
        r9 = new androidx.core.provider.FontRequestWorker.AnonymousClass3();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0042, code lost:
        if (r8 != null) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        r8 = DEFAULT_EXECUTOR_SERVICE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0046, code lost:
        androidx.core.provider.RequestExecutor.execute(r8, r9, new androidx.core.provider.FontRequestWorker.AnonymousClass4());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004e, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.graphics.Typeface requestFontAsync(final android.content.Context r5, final androidx.core.provider.FontRequest r6, final int r7, java.util.concurrent.Executor r8, final androidx.core.provider.CallbackWithHandler r9) {
        /*
            java.lang.String r0 = createCacheId(r6, r7)
            androidx.collection.LruCache r1 = sTypefaceCache
            java.lang.Object r1 = r1.get(r0)
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1
            if (r1 == 0) goto L_0x0017
            androidx.core.provider.FontRequestWorker$TypefaceResult r5 = new androidx.core.provider.FontRequestWorker$TypefaceResult
            r5.<init>((android.graphics.Typeface) r1)
            r9.onTypefaceResult(r5)
            return r1
        L_0x0017:
            androidx.core.provider.FontRequestWorker$2 r1 = new androidx.core.provider.FontRequestWorker$2
            r1.<init>()
            java.lang.Object r9 = LOCK
            monitor-enter(r9)
            androidx.collection.SimpleArrayMap r2 = PENDING_REPLIES     // Catch:{ all -> 0x002f }
            java.lang.Object r3 = r2.get(r0)     // Catch:{ all -> 0x002f }
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch:{ all -> 0x002f }
            r4 = 0
            if (r3 == 0) goto L_0x0031
            r3.add(r1)     // Catch:{ all -> 0x002f }
            monitor-exit(r9)     // Catch:{ all -> 0x002f }
            return r4
        L_0x002f:
            r5 = move-exception
            goto L_0x004f
        L_0x0031:
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x002f }
            r3.<init>()     // Catch:{ all -> 0x002f }
            r3.add(r1)     // Catch:{ all -> 0x002f }
            r2.put(r0, r3)     // Catch:{ all -> 0x002f }
            monitor-exit(r9)     // Catch:{ all -> 0x002f }
            androidx.core.provider.FontRequestWorker$3 r9 = new androidx.core.provider.FontRequestWorker$3
            r9.<init>(r0, r5, r6, r7)
            if (r8 != 0) goto L_0x0046
            java.util.concurrent.ExecutorService r8 = DEFAULT_EXECUTOR_SERVICE
        L_0x0046:
            androidx.core.provider.FontRequestWorker$4 r5 = new androidx.core.provider.FontRequestWorker$4
            r5.<init>(r0)
            androidx.core.provider.RequestExecutor.execute(r8, r9, r5)
            return r4
        L_0x004f:
            monitor-exit(r9)     // Catch:{ all -> 0x002f }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontRequestWorker.requestFontAsync(android.content.Context, androidx.core.provider.FontRequest, int, java.util.concurrent.Executor, androidx.core.provider.CallbackWithHandler):android.graphics.Typeface");
    }

    private static String createCacheId(FontRequest fontRequest, int i) {
        return fontRequest.getId() + "-" + i;
    }

    static TypefaceResult getFontSync(String str, Context context, FontRequest fontRequest, int i) {
        LruCache lruCache = sTypefaceCache;
        Typeface typeface = (Typeface) lruCache.get(str);
        if (typeface != null) {
            return new TypefaceResult(typeface);
        }
        try {
            FontsContractCompat.FontFamilyResult fontFamilyResult = FontProvider.getFontFamilyResult(context, fontRequest, (CancellationSignal) null);
            int fontFamilyResultStatus = getFontFamilyResultStatus(fontFamilyResult);
            if (fontFamilyResultStatus != 0) {
                return new TypefaceResult(fontFamilyResultStatus);
            }
            Typeface createFromFontInfo = TypefaceCompat.createFromFontInfo(context, (CancellationSignal) null, fontFamilyResult.getFonts(), i);
            if (createFromFontInfo == null) {
                return new TypefaceResult(-3);
            }
            lruCache.put(str, createFromFontInfo);
            return new TypefaceResult(createFromFontInfo);
        } catch (PackageManager.NameNotFoundException unused) {
            return new TypefaceResult(-1);
        }
    }

    private static int getFontFamilyResultStatus(FontsContractCompat.FontFamilyResult fontFamilyResult) {
        int i = 1;
        if (fontFamilyResult.getStatusCode() == 0) {
            FontsContractCompat.FontInfo[] fonts = fontFamilyResult.getFonts();
            if (!(fonts == null || fonts.length == 0)) {
                int length = fonts.length;
                i = 0;
                int i2 = 0;
                while (i2 < length) {
                    int resultCode = fonts[i2].getResultCode();
                    if (resultCode == 0) {
                        i2++;
                    } else if (resultCode < 0) {
                        return -3;
                    } else {
                        return resultCode;
                    }
                }
            }
            return i;
        } else if (fontFamilyResult.getStatusCode() != 1) {
            return -3;
        } else {
            return -2;
        }
    }

    static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        TypefaceResult(int i) {
            this.mTypeface = null;
            this.mResult = i;
        }

        TypefaceResult(Typeface typeface) {
            this.mTypeface = typeface;
            this.mResult = 0;
        }

        /* access modifiers changed from: package-private */
        public boolean isSuccess() {
            return this.mResult == 0;
        }
    }
}
