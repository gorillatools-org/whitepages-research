package com.airbnb.lottie;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import com.airbnb.lottie.model.LottieCompositionCache;
import com.airbnb.lottie.parser.LottieCompositionMoshiParser;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

public abstract class LottieCompositionFactory {
    private static final byte[] GZIP_MAGIC = {31, -117, 8};
    private static final byte[] ZIP_MAGIC = {80, 75, 3, 4};
    private static final Map taskCache = new HashMap();
    private static final Set taskIdleListeners = new HashSet();

    public static LottieTask fromUrl(Context context, String str) {
        return fromUrl(context, str, "url_" + str);
    }

    public static LottieTask fromUrl(Context context, String str, String str2) {
        return cache(str2, new LottieCompositionFactory$$ExternalSyntheticLambda0(context, str, str2), (Runnable) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ LottieResult lambda$fromUrl$0(Context context, String str, String str2) {
        LottieResult fetchSync = L.networkFetcher(context).fetchSync(context, str, str2);
        if (!(str2 == null || fetchSync.getValue() == null)) {
            LottieCompositionCache.getInstance().put(str2, (LottieComposition) fetchSync.getValue());
        }
        return fetchSync;
    }

    public static LottieTask fromAsset(Context context, String str) {
        return fromAsset(context, str, "asset_" + str);
    }

    public static LottieTask fromAsset(Context context, String str, String str2) {
        return cache(str2, new LottieCompositionFactory$$ExternalSyntheticLambda5(context.getApplicationContext(), str, str2), (Runnable) null);
    }

    public static LottieResult fromAssetSync(Context context, String str) {
        return fromAssetSync(context, str, "asset_" + str);
    }

    public static LottieResult fromAssetSync(Context context, String str, String str2) {
        LottieComposition lottieComposition = str2 == null ? null : LottieCompositionCache.getInstance().get(str2);
        if (lottieComposition != null) {
            return new LottieResult((Object) lottieComposition);
        }
        try {
            BufferedSource buffer = Okio.buffer(Okio.source(context.getAssets().open(str)));
            if (isZipCompressed(buffer).booleanValue()) {
                return fromZipStreamSync(context, new ZipInputStream(buffer.inputStream()), str2);
            }
            if (isGzipCompressed(buffer).booleanValue()) {
                return fromJsonInputStreamSync(new GZIPInputStream(buffer.inputStream()), str2);
            }
            return fromJsonReaderSync(JsonReader.of(buffer), str2);
        } catch (IOException e) {
            return new LottieResult((Throwable) e);
        }
    }

    public static LottieTask fromRawRes(Context context, int i) {
        return fromRawRes(context, i, rawResCacheKey(context, i));
    }

    public static LottieTask fromRawRes(Context context, int i, String str) {
        return cache(str, new LottieCompositionFactory$$ExternalSyntheticLambda8(new WeakReference(context), context.getApplicationContext(), i, str), (Runnable) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ LottieResult lambda$fromRawRes$2(WeakReference weakReference, Context context, int i, String str) {
        Context context2 = (Context) weakReference.get();
        if (context2 != null) {
            context = context2;
        }
        return fromRawResSync(context, i, str);
    }

    public static LottieResult fromRawResSync(Context context, int i) {
        return fromRawResSync(context, i, rawResCacheKey(context, i));
    }

    public static LottieResult fromRawResSync(Context context, int i, String str) {
        LottieComposition lottieComposition = str == null ? null : LottieCompositionCache.getInstance().get(str);
        if (lottieComposition != null) {
            return new LottieResult((Object) lottieComposition);
        }
        try {
            BufferedSource buffer = Okio.buffer(Okio.source(context.getResources().openRawResource(i)));
            if (isZipCompressed(buffer).booleanValue()) {
                return fromZipStreamSync(context, new ZipInputStream(buffer.inputStream()), str);
            }
            if (!isGzipCompressed(buffer).booleanValue()) {
                return fromJsonReaderSync(JsonReader.of(buffer), str);
            }
            try {
                return fromJsonInputStreamSync(new GZIPInputStream(buffer.inputStream()), str);
            } catch (IOException e) {
                return new LottieResult((Throwable) e);
            }
        } catch (Resources.NotFoundException e2) {
            return new LottieResult((Throwable) e2);
        }
    }

    private static String rawResCacheKey(Context context, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("rawRes");
        sb.append(isNightMode(context) ? "_night_" : "_day_");
        sb.append(i);
        return sb.toString();
    }

    private static boolean isNightMode(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static LottieTask fromJsonInputStream(InputStream inputStream, String str) {
        return cache(str, new LottieCompositionFactory$$ExternalSyntheticLambda1(inputStream, str), new LottieCompositionFactory$$ExternalSyntheticLambda2(inputStream));
    }

    public static LottieResult fromJsonInputStreamSync(InputStream inputStream, String str) {
        return fromJsonInputStreamSync(inputStream, str, true);
    }

    public static LottieResult fromJsonInputStreamSync(InputStream inputStream, String str, boolean z) {
        return fromJsonSourceSync(Okio.source(inputStream), str, z);
    }

    public static LottieResult fromJsonSourceSync(Source source, String str, boolean z) {
        return fromJsonReaderSyncInternal(JsonReader.of(Okio.buffer(source)), str, z);
    }

    public static LottieResult fromJsonReaderSync(JsonReader jsonReader, String str) {
        return fromJsonReaderSync(jsonReader, str, true);
    }

    public static LottieResult fromJsonReaderSync(JsonReader jsonReader, String str, boolean z) {
        return fromJsonReaderSyncInternal(jsonReader, str, z);
    }

    private static LottieResult fromJsonReaderSyncInternal(JsonReader jsonReader, String str, boolean z) {
        LottieComposition lottieComposition;
        if (str == null) {
            lottieComposition = null;
        } else {
            try {
                lottieComposition = LottieCompositionCache.getInstance().get(str);
            } catch (Exception e) {
                LottieResult lottieResult = new LottieResult((Throwable) e);
                if (z) {
                    Utils.closeQuietly(jsonReader);
                }
                return lottieResult;
            } catch (Throwable th) {
                if (z) {
                    Utils.closeQuietly(jsonReader);
                }
                throw th;
            }
        }
        if (lottieComposition != null) {
            LottieResult lottieResult2 = new LottieResult((Object) lottieComposition);
            if (z) {
                Utils.closeQuietly(jsonReader);
            }
            return lottieResult2;
        }
        LottieComposition parse = LottieCompositionMoshiParser.parse(jsonReader);
        if (str != null) {
            LottieCompositionCache.getInstance().put(str, parse);
        }
        LottieResult lottieResult3 = new LottieResult((Object) parse);
        if (z) {
            Utils.closeQuietly(jsonReader);
        }
        return lottieResult3;
    }

    public static LottieTask fromZipStream(ZipInputStream zipInputStream, String str) {
        return fromZipStream((Context) null, zipInputStream, str);
    }

    public static LottieTask fromZipStream(Context context, ZipInputStream zipInputStream, String str) {
        return cache(str, new LottieCompositionFactory$$ExternalSyntheticLambda3(context, zipInputStream, str), new LottieCompositionFactory$$ExternalSyntheticLambda4(zipInputStream));
    }

    public static LottieResult fromZipStreamSync(Context context, ZipInputStream zipInputStream, String str) {
        return fromZipStreamSync(context, zipInputStream, str, true);
    }

    public static LottieResult fromZipStreamSync(Context context, ZipInputStream zipInputStream, String str, boolean z) {
        try {
            return fromZipStreamSyncInternal(context, zipInputStream, str);
        } finally {
            if (z) {
                Utils.closeQuietly(zipInputStream);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v49, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: com.airbnb.lottie.LottieComposition} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.airbnb.lottie.LottieResult fromZipStreamSyncInternal(android.content.Context r13, java.util.zip.ZipInputStream r14, java.lang.String r15) {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r2 = 0
            if (r15 != 0) goto L_0x000f
            r3 = r2
            goto L_0x0017
        L_0x000f:
            com.airbnb.lottie.model.LottieCompositionCache r3 = com.airbnb.lottie.model.LottieCompositionCache.getInstance()     // Catch:{ IOException -> 0x001f }
            com.airbnb.lottie.LottieComposition r3 = r3.get(r15)     // Catch:{ IOException -> 0x001f }
        L_0x0017:
            if (r3 == 0) goto L_0x0022
            com.airbnb.lottie.LottieResult r13 = new com.airbnb.lottie.LottieResult     // Catch:{ IOException -> 0x001f }
            r13.<init>((java.lang.Object) r3)     // Catch:{ IOException -> 0x001f }
            return r13
        L_0x001f:
            r13 = move-exception
            goto L_0x02b1
        L_0x0022:
            java.util.zip.ZipEntry r3 = r14.getNextEntry()     // Catch:{ IOException -> 0x001f }
            r4 = r2
        L_0x0027:
            r5 = 1
            r6 = 0
            if (r3 == 0) goto L_0x0184
            java.lang.String r7 = r3.getName()     // Catch:{ IOException -> 0x001f }
            java.lang.String r8 = "__MACOSX"
            boolean r8 = r7.contains(r8)     // Catch:{ IOException -> 0x001f }
            if (r8 == 0) goto L_0x003c
            r14.closeEntry()     // Catch:{ IOException -> 0x001f }
            goto L_0x017e
        L_0x003c:
            java.lang.String r8 = r3.getName()     // Catch:{ IOException -> 0x001f }
            java.lang.String r9 = "manifest.json"
            boolean r8 = r8.equalsIgnoreCase(r9)     // Catch:{ IOException -> 0x001f }
            if (r8 == 0) goto L_0x004d
            r14.closeEntry()     // Catch:{ IOException -> 0x001f }
            goto L_0x017e
        L_0x004d:
            java.lang.String r3 = r3.getName()     // Catch:{ IOException -> 0x001f }
            java.lang.String r8 = ".json"
            boolean r3 = r3.contains(r8)     // Catch:{ IOException -> 0x001f }
            if (r3 == 0) goto L_0x0072
            okio.Source r3 = okio.Okio.source((java.io.InputStream) r14)     // Catch:{ IOException -> 0x001f }
            okio.BufferedSource r3 = okio.Okio.buffer((okio.Source) r3)     // Catch:{ IOException -> 0x001f }
            com.airbnb.lottie.parser.moshi.JsonReader r3 = com.airbnb.lottie.parser.moshi.JsonReader.of(r3)     // Catch:{ IOException -> 0x001f }
            com.airbnb.lottie.LottieResult r3 = fromJsonReaderSyncInternal(r3, r2, r6)     // Catch:{ IOException -> 0x001f }
            java.lang.Object r3 = r3.getValue()     // Catch:{ IOException -> 0x001f }
            r4 = r3
            com.airbnb.lottie.LottieComposition r4 = (com.airbnb.lottie.LottieComposition) r4     // Catch:{ IOException -> 0x001f }
            goto L_0x017e
        L_0x0072:
            java.lang.String r3 = ".png"
            boolean r3 = r7.contains(r3)     // Catch:{ IOException -> 0x001f }
            java.lang.String r8 = "/"
            if (r3 != 0) goto L_0x016f
            java.lang.String r3 = ".webp"
            boolean r3 = r7.contains(r3)     // Catch:{ IOException -> 0x001f }
            if (r3 != 0) goto L_0x016f
            java.lang.String r3 = ".jpg"
            boolean r3 = r7.contains(r3)     // Catch:{ IOException -> 0x001f }
            if (r3 != 0) goto L_0x016f
            java.lang.String r3 = ".jpeg"
            boolean r3 = r7.contains(r3)     // Catch:{ IOException -> 0x001f }
            if (r3 == 0) goto L_0x0096
            goto L_0x016f
        L_0x0096:
            java.lang.String r3 = ".ttf"
            boolean r3 = r7.contains(r3)     // Catch:{ IOException -> 0x001f }
            if (r3 != 0) goto L_0x00ac
            java.lang.String r3 = ".otf"
            boolean r3 = r7.contains(r3)     // Catch:{ IOException -> 0x001f }
            if (r3 == 0) goto L_0x00a7
            goto L_0x00ac
        L_0x00a7:
            r14.closeEntry()     // Catch:{ IOException -> 0x001f }
            goto L_0x017e
        L_0x00ac:
            java.lang.String[] r3 = r7.split(r8)     // Catch:{ IOException -> 0x001f }
            int r7 = r3.length     // Catch:{ IOException -> 0x001f }
            int r7 = r7 - r5
            r3 = r3[r7]     // Catch:{ IOException -> 0x001f }
            java.lang.String r5 = "\\."
            java.lang.String[] r5 = r3.split(r5)     // Catch:{ IOException -> 0x001f }
            r5 = r5[r6]     // Catch:{ IOException -> 0x001f }
            if (r13 != 0) goto L_0x00df
            com.airbnb.lottie.LottieResult r13 = new com.airbnb.lottie.LottieResult     // Catch:{ IOException -> 0x001f }
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException     // Catch:{ IOException -> 0x001f }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x001f }
            r15.<init>()     // Catch:{ IOException -> 0x001f }
            java.lang.String r0 = "Unable to extract font "
            r15.append(r0)     // Catch:{ IOException -> 0x001f }
            r15.append(r5)     // Catch:{ IOException -> 0x001f }
            java.lang.String r0 = " please pass a non-null Context parameter"
            r15.append(r0)     // Catch:{ IOException -> 0x001f }
            java.lang.String r15 = r15.toString()     // Catch:{ IOException -> 0x001f }
            r14.<init>(r15)     // Catch:{ IOException -> 0x001f }
            r13.<init>((java.lang.Throwable) r14)     // Catch:{ IOException -> 0x001f }
            return r13
        L_0x00df:
            java.io.File r7 = new java.io.File     // Catch:{ IOException -> 0x001f }
            java.io.File r8 = r13.getCacheDir()     // Catch:{ IOException -> 0x001f }
            r7.<init>(r8, r3)     // Catch:{ IOException -> 0x001f }
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ all -> 0x010d }
            r8.<init>(r7)     // Catch:{ all -> 0x010d }
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch:{ all -> 0x010f }
            r9.<init>(r7)     // Catch:{ all -> 0x010f }
            r10 = 4096(0x1000, float:5.74E-42)
            byte[] r10 = new byte[r10]     // Catch:{ all -> 0x0101 }
        L_0x00f6:
            int r11 = r14.read(r10)     // Catch:{ all -> 0x0101 }
            r12 = -1
            if (r11 == r12) goto L_0x0103
            r9.write(r10, r6, r11)     // Catch:{ all -> 0x0101 }
            goto L_0x00f6
        L_0x0101:
            r6 = move-exception
            goto L_0x0111
        L_0x0103:
            r9.flush()     // Catch:{ all -> 0x0101 }
            r9.close()     // Catch:{ all -> 0x010f }
            r8.close()     // Catch:{ all -> 0x010d }
            goto L_0x0144
        L_0x010d:
            r6 = move-exception
            goto L_0x0123
        L_0x010f:
            r6 = move-exception
            goto L_0x011a
        L_0x0111:
            r9.close()     // Catch:{ all -> 0x0115 }
            goto L_0x0119
        L_0x0115:
            r9 = move-exception
            r6.addSuppressed(r9)     // Catch:{ all -> 0x010f }
        L_0x0119:
            throw r6     // Catch:{ all -> 0x010f }
        L_0x011a:
            r8.close()     // Catch:{ all -> 0x011e }
            goto L_0x0122
        L_0x011e:
            r8 = move-exception
            r6.addSuppressed(r8)     // Catch:{ all -> 0x010d }
        L_0x0122:
            throw r6     // Catch:{ all -> 0x010d }
        L_0x0123:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x001f }
            r8.<init>()     // Catch:{ IOException -> 0x001f }
            java.lang.String r9 = "Unable to save font "
            r8.append(r9)     // Catch:{ IOException -> 0x001f }
            r8.append(r5)     // Catch:{ IOException -> 0x001f }
            java.lang.String r9 = " to the temporary file: "
            r8.append(r9)     // Catch:{ IOException -> 0x001f }
            r8.append(r3)     // Catch:{ IOException -> 0x001f }
            java.lang.String r3 = ". "
            r8.append(r3)     // Catch:{ IOException -> 0x001f }
            java.lang.String r3 = r8.toString()     // Catch:{ IOException -> 0x001f }
            com.airbnb.lottie.utils.Logger.warning(r3, r6)     // Catch:{ IOException -> 0x001f }
        L_0x0144:
            android.graphics.Typeface r3 = android.graphics.Typeface.createFromFile(r7)     // Catch:{ IOException -> 0x001f }
            boolean r6 = r7.delete()     // Catch:{ IOException -> 0x001f }
            if (r6 != 0) goto L_0x016b
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x001f }
            r6.<init>()     // Catch:{ IOException -> 0x001f }
            java.lang.String r8 = "Failed to delete temp font file "
            r6.append(r8)     // Catch:{ IOException -> 0x001f }
            java.lang.String r7 = r7.getAbsolutePath()     // Catch:{ IOException -> 0x001f }
            r6.append(r7)     // Catch:{ IOException -> 0x001f }
            java.lang.String r7 = "."
            r6.append(r7)     // Catch:{ IOException -> 0x001f }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x001f }
            com.airbnb.lottie.utils.Logger.warning(r6)     // Catch:{ IOException -> 0x001f }
        L_0x016b:
            r1.put(r5, r3)     // Catch:{ IOException -> 0x001f }
            goto L_0x017e
        L_0x016f:
            java.lang.String[] r3 = r7.split(r8)     // Catch:{ IOException -> 0x001f }
            int r6 = r3.length     // Catch:{ IOException -> 0x001f }
            int r6 = r6 - r5
            r3 = r3[r6]     // Catch:{ IOException -> 0x001f }
            android.graphics.Bitmap r5 = android.graphics.BitmapFactory.decodeStream(r14)     // Catch:{ IOException -> 0x001f }
            r0.put(r3, r5)     // Catch:{ IOException -> 0x001f }
        L_0x017e:
            java.util.zip.ZipEntry r3 = r14.getNextEntry()     // Catch:{ IOException -> 0x001f }
            goto L_0x0027
        L_0x0184:
            if (r4 != 0) goto L_0x0193
            com.airbnb.lottie.LottieResult r13 = new com.airbnb.lottie.LottieResult
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r15 = "Unable to parse composition"
            r14.<init>(r15)
            r13.<init>((java.lang.Throwable) r14)
            return r13
        L_0x0193:
            java.util.Set r13 = r0.entrySet()
            java.util.Iterator r13 = r13.iterator()
        L_0x019b:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x01c9
            java.lang.Object r14 = r13.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            java.lang.Object r3 = r14.getKey()
            java.lang.String r3 = (java.lang.String) r3
            com.airbnb.lottie.LottieImageAsset r3 = findImageAssetForFileName(r4, r3)
            if (r3 == 0) goto L_0x019b
            java.lang.Object r14 = r14.getValue()
            android.graphics.Bitmap r14 = (android.graphics.Bitmap) r14
            int r7 = r3.getWidth()
            int r8 = r3.getHeight()
            android.graphics.Bitmap r14 = com.airbnb.lottie.utils.Utils.resizeBitmapIfNeeded(r14, r7, r8)
            r3.setBitmap(r14)
            goto L_0x019b
        L_0x01c9:
            java.util.Set r13 = r1.entrySet()
            java.util.Iterator r13 = r13.iterator()
        L_0x01d1:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x0231
            java.lang.Object r14 = r13.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            java.util.Map r1 = r4.getFonts()
            java.util.Collection r1 = r1.values()
            java.util.Iterator r1 = r1.iterator()
            r3 = r6
        L_0x01ea:
            boolean r7 = r1.hasNext()
            if (r7 == 0) goto L_0x020f
            java.lang.Object r7 = r1.next()
            com.airbnb.lottie.model.Font r7 = (com.airbnb.lottie.model.Font) r7
            java.lang.String r8 = r7.getFamily()
            java.lang.Object r9 = r14.getKey()
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x01ea
            java.lang.Object r3 = r14.getValue()
            android.graphics.Typeface r3 = (android.graphics.Typeface) r3
            r7.setTypeface(r3)
            r3 = r5
            goto L_0x01ea
        L_0x020f:
            if (r3 != 0) goto L_0x01d1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Parsed font for "
            r1.append(r3)
            java.lang.Object r14 = r14.getKey()
            java.lang.String r14 = (java.lang.String) r14
            r1.append(r14)
            java.lang.String r14 = " however it was not found in the animation."
            r1.append(r14)
            java.lang.String r14 = r1.toString()
            com.airbnb.lottie.utils.Logger.warning(r14)
            goto L_0x01d1
        L_0x0231:
            boolean r13 = r0.isEmpty()
            if (r13 == 0) goto L_0x02a2
            java.util.Map r13 = r4.getImages()
            java.util.Set r13 = r13.entrySet()
            java.util.Iterator r13 = r13.iterator()
        L_0x0243:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x02a2
            java.lang.Object r14 = r13.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            java.lang.Object r14 = r14.getValue()
            com.airbnb.lottie.LottieImageAsset r14 = (com.airbnb.lottie.LottieImageAsset) r14
            if (r14 != 0) goto L_0x0258
            return r2
        L_0x0258:
            java.lang.String r0 = r14.getFileName()
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options
            r1.<init>()
            r1.inScaled = r5
            r3 = 160(0xa0, float:2.24E-43)
            r1.inDensity = r3
            java.lang.String r3 = "data:"
            boolean r3 = r0.startsWith(r3)
            if (r3 == 0) goto L_0x0243
            java.lang.String r3 = "base64,"
            int r3 = r0.indexOf(r3)
            if (r3 <= 0) goto L_0x0243
            r3 = 44
            int r3 = r0.indexOf(r3)     // Catch:{ IllegalArgumentException -> 0x029b }
            int r3 = r3 + r5
            java.lang.String r0 = r0.substring(r3)     // Catch:{ IllegalArgumentException -> 0x029b }
            byte[] r0 = android.util.Base64.decode(r0, r6)     // Catch:{ IllegalArgumentException -> 0x029b }
            int r3 = r0.length
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeByteArray(r0, r6, r3, r1)
            int r1 = r14.getWidth()
            int r3 = r14.getHeight()
            android.graphics.Bitmap r0 = com.airbnb.lottie.utils.Utils.resizeBitmapIfNeeded(r0, r1, r3)
            r14.setBitmap(r0)
            goto L_0x0243
        L_0x029b:
            r13 = move-exception
            java.lang.String r14 = "data URL did not have correct base64 format."
            com.airbnb.lottie.utils.Logger.warning(r14, r13)
            return r2
        L_0x02a2:
            if (r15 == 0) goto L_0x02ab
            com.airbnb.lottie.model.LottieCompositionCache r13 = com.airbnb.lottie.model.LottieCompositionCache.getInstance()
            r13.put(r15, r4)
        L_0x02ab:
            com.airbnb.lottie.LottieResult r13 = new com.airbnb.lottie.LottieResult
            r13.<init>((java.lang.Object) r4)
            return r13
        L_0x02b1:
            com.airbnb.lottie.LottieResult r14 = new com.airbnb.lottie.LottieResult
            r14.<init>((java.lang.Throwable) r13)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.LottieCompositionFactory.fromZipStreamSyncInternal(android.content.Context, java.util.zip.ZipInputStream, java.lang.String):com.airbnb.lottie.LottieResult");
    }

    private static Boolean isZipCompressed(BufferedSource bufferedSource) {
        return matchesMagicBytes(bufferedSource, ZIP_MAGIC);
    }

    private static Boolean isGzipCompressed(BufferedSource bufferedSource) {
        return matchesMagicBytes(bufferedSource, GZIP_MAGIC);
    }

    private static Boolean matchesMagicBytes(BufferedSource bufferedSource, byte[] bArr) {
        try {
            BufferedSource peek = bufferedSource.peek();
            for (byte b : bArr) {
                if (peek.readByte() != b) {
                    return Boolean.FALSE;
                }
            }
            peek.close();
            return Boolean.TRUE;
        } catch (NoSuchMethodError unused) {
            return Boolean.FALSE;
        } catch (Exception e) {
            Logger.error("Failed to check zip file header", e);
            return Boolean.FALSE;
        }
    }

    private static LottieImageAsset findImageAssetForFileName(LottieComposition lottieComposition, String str) {
        for (LottieImageAsset lottieImageAsset : lottieComposition.getImages().values()) {
            if (lottieImageAsset.getFileName().equals(str)) {
                return lottieImageAsset;
            }
        }
        return null;
    }

    private static LottieTask cache(String str, Callable callable, Runnable runnable) {
        LottieTask lottieTask = null;
        LottieComposition lottieComposition = str == null ? null : LottieCompositionCache.getInstance().get(str);
        if (lottieComposition != null) {
            lottieTask = new LottieTask((Object) lottieComposition);
        }
        if (str != null) {
            Map map = taskCache;
            if (map.containsKey(str)) {
                lottieTask = (LottieTask) map.get(str);
            }
        }
        if (lottieTask != null) {
            if (runnable != null) {
                runnable.run();
            }
            return lottieTask;
        }
        LottieTask lottieTask2 = new LottieTask(callable);
        if (str != null) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            lottieTask2.addListener(new LottieCompositionFactory$$ExternalSyntheticLambda6(str, atomicBoolean));
            lottieTask2.addFailureListener(new LottieCompositionFactory$$ExternalSyntheticLambda7(str, atomicBoolean));
            if (!atomicBoolean.get()) {
                Map map2 = taskCache;
                map2.put(str, lottieTask2);
                if (map2.size() == 1) {
                    notifyTaskCacheIdleListeners(false);
                }
            }
        }
        return lottieTask2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$cache$17(String str, AtomicBoolean atomicBoolean, LottieComposition lottieComposition) {
        Map map = taskCache;
        map.remove(str);
        atomicBoolean.set(true);
        if (map.size() == 0) {
            notifyTaskCacheIdleListeners(true);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$cache$18(String str, AtomicBoolean atomicBoolean, Throwable th) {
        Map map = taskCache;
        map.remove(str);
        atomicBoolean.set(true);
        if (map.size() == 0) {
            notifyTaskCacheIdleListeners(true);
        }
    }

    private static void notifyTaskCacheIdleListeners(boolean z) {
        ArrayList arrayList = new ArrayList(taskIdleListeners);
        if (arrayList.size() > 0) {
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(arrayList.get(0));
            throw null;
        }
    }
}
