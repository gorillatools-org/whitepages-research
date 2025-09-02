package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public abstract class ResourcesCompat {
    private static final Object sColorStateCacheLock = new Object();
    private static final WeakHashMap sColorStateCaches = new WeakHashMap(0);
    private static final ThreadLocal sTempTypedValue = new ThreadLocal();

    public static Drawable getDrawable(Resources resources, int i, Resources.Theme theme) {
        return Api21Impl.getDrawable(resources, i, theme);
    }

    public static Drawable getDrawableForDensity(Resources resources, int i, int i2, Resources.Theme theme) {
        return Api21Impl.getDrawableForDensity(resources, i, i2, theme);
    }

    public static int getColor(Resources resources, int i, Resources.Theme theme) {
        return Api23Impl.getColor(resources, i, theme);
    }

    public static ColorStateList getColorStateList(Resources resources, int i, Resources.Theme theme) {
        ColorStateListCacheKey colorStateListCacheKey = new ColorStateListCacheKey(resources, theme);
        ColorStateList cachedColorStateList = getCachedColorStateList(colorStateListCacheKey, i);
        if (cachedColorStateList != null) {
            return cachedColorStateList;
        }
        ColorStateList inflateColorStateList = inflateColorStateList(resources, i, theme);
        if (inflateColorStateList == null) {
            return Api23Impl.getColorStateList(resources, i, theme);
        }
        addColorStateListToCache(colorStateListCacheKey, i, inflateColorStateList, theme);
        return inflateColorStateList;
    }

    private static ColorStateList inflateColorStateList(Resources resources, int i, Resources.Theme theme) {
        if (isColorInt(resources, i)) {
            return null;
        }
        try {
            return ColorStateListInflaterCompat.createFromXml(resources, resources.getXml(i), theme);
        } catch (Exception e) {
            Log.w("ResourcesCompat", "Failed to inflate ColorStateList, leaving it to the framework", e);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003c, code lost:
        if (r2.mThemeHash == r5.hashCode()) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0046, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.content.res.ColorStateList getCachedColorStateList(androidx.core.content.res.ResourcesCompat.ColorStateListCacheKey r5, int r6) {
        /*
            java.lang.Object r0 = sColorStateCacheLock
            monitor-enter(r0)
            java.util.WeakHashMap r1 = sColorStateCaches     // Catch:{ all -> 0x0032 }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x0032 }
            android.util.SparseArray r1 = (android.util.SparseArray) r1     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0045
            int r2 = r1.size()     // Catch:{ all -> 0x0032 }
            if (r2 <= 0) goto L_0x0045
            java.lang.Object r2 = r1.get(r6)     // Catch:{ all -> 0x0032 }
            androidx.core.content.res.ResourcesCompat$ColorStateListCacheEntry r2 = (androidx.core.content.res.ResourcesCompat.ColorStateListCacheEntry) r2     // Catch:{ all -> 0x0032 }
            if (r2 == 0) goto L_0x0045
            android.content.res.Configuration r3 = r2.mConfiguration     // Catch:{ all -> 0x0032 }
            android.content.res.Resources r4 = r5.mResources     // Catch:{ all -> 0x0032 }
            android.content.res.Configuration r4 = r4.getConfiguration()     // Catch:{ all -> 0x0032 }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x0032 }
            if (r3 == 0) goto L_0x0042
            android.content.res.Resources$Theme r5 = r5.mTheme     // Catch:{ all -> 0x0032 }
            if (r5 != 0) goto L_0x0034
            int r3 = r2.mThemeHash     // Catch:{ all -> 0x0032 }
            if (r3 == 0) goto L_0x003e
            goto L_0x0034
        L_0x0032:
            r5 = move-exception
            goto L_0x0048
        L_0x0034:
            if (r5 == 0) goto L_0x0042
            int r3 = r2.mThemeHash     // Catch:{ all -> 0x0032 }
            int r5 = r5.hashCode()     // Catch:{ all -> 0x0032 }
            if (r3 != r5) goto L_0x0042
        L_0x003e:
            android.content.res.ColorStateList r5 = r2.mValue     // Catch:{ all -> 0x0032 }
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return r5
        L_0x0042:
            r1.remove(r6)     // Catch:{ all -> 0x0032 }
        L_0x0045:
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            r5 = 0
            return r5
        L_0x0048:
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.ResourcesCompat.getCachedColorStateList(androidx.core.content.res.ResourcesCompat$ColorStateListCacheKey, int):android.content.res.ColorStateList");
    }

    private static void addColorStateListToCache(ColorStateListCacheKey colorStateListCacheKey, int i, ColorStateList colorStateList, Resources.Theme theme) {
        synchronized (sColorStateCacheLock) {
            try {
                WeakHashMap weakHashMap = sColorStateCaches;
                SparseArray sparseArray = (SparseArray) weakHashMap.get(colorStateListCacheKey);
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                    weakHashMap.put(colorStateListCacheKey, sparseArray);
                }
                sparseArray.append(i, new ColorStateListCacheEntry(colorStateList, colorStateListCacheKey.mResources.getConfiguration(), theme));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static boolean isColorInt(Resources resources, int i) {
        TypedValue typedValue = getTypedValue();
        resources.getValue(i, typedValue, true);
        int i2 = typedValue.type;
        if (i2 < 28 || i2 > 31) {
            return false;
        }
        return true;
    }

    private static TypedValue getTypedValue() {
        ThreadLocal threadLocal = sTempTypedValue;
        TypedValue typedValue = (TypedValue) threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }

    private static final class ColorStateListCacheKey {
        final Resources mResources;
        final Resources.Theme mTheme;

        ColorStateListCacheKey(Resources resources, Resources.Theme theme) {
            this.mResources = resources;
            this.mTheme = theme;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ColorStateListCacheKey.class != obj.getClass()) {
                return false;
            }
            ColorStateListCacheKey colorStateListCacheKey = (ColorStateListCacheKey) obj;
            if (!this.mResources.equals(colorStateListCacheKey.mResources) || !ObjectsCompat.equals(this.mTheme, colorStateListCacheKey.mTheme)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.mResources, this.mTheme);
        }
    }

    private static class ColorStateListCacheEntry {
        final Configuration mConfiguration;
        final int mThemeHash;
        final ColorStateList mValue;

        ColorStateListCacheEntry(ColorStateList colorStateList, Configuration configuration, Resources.Theme theme) {
            int i;
            this.mValue = colorStateList;
            this.mConfiguration = configuration;
            if (theme == null) {
                i = 0;
            } else {
                i = theme.hashCode();
            }
            this.mThemeHash = i;
        }
    }

    public static Typeface getFont(Context context, int i) {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, i, new TypedValue(), 0, (FontCallback) null, (Handler) null, false, false);
    }

    public static Typeface getCachedFont(Context context, int i) {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, i, new TypedValue(), 0, (FontCallback) null, (Handler) null, false, true);
    }

    public static abstract class FontCallback {
        /* renamed from: onFontRetrievalFailed */
        public abstract void lambda$callbackFailAsync$1(int i);

        /* renamed from: onFontRetrieved */
        public abstract void lambda$callbackSuccessAsync$0(Typeface typeface);

        public final void callbackSuccessAsync(Typeface typeface, Handler handler) {
            getHandler(handler).post(new ResourcesCompat$FontCallback$$ExternalSyntheticLambda0(this, typeface));
        }

        public final void callbackFailAsync(int i, Handler handler) {
            getHandler(handler).post(new ResourcesCompat$FontCallback$$ExternalSyntheticLambda1(this, i));
        }

        public static Handler getHandler(Handler handler) {
            return handler == null ? new Handler(Looper.getMainLooper()) : handler;
        }
    }

    public static void getFont(Context context, int i, FontCallback fontCallback, Handler handler) {
        Preconditions.checkNotNull(fontCallback);
        if (context.isRestricted()) {
            fontCallback.callbackFailAsync(-4, handler);
            return;
        }
        loadFont(context, i, new TypedValue(), 0, fontCallback, handler, false, false);
    }

    public static Typeface getFont(Context context, int i, TypedValue typedValue, int i2, FontCallback fontCallback) {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, i, typedValue, i2, fontCallback, (Handler) null, true, false);
    }

    private static Typeface loadFont(Context context, int i, TypedValue typedValue, int i2, FontCallback fontCallback, Handler handler, boolean z, boolean z2) {
        Resources resources = context.getResources();
        int i3 = i;
        resources.getValue(i, typedValue, true);
        Typeface loadFont = loadFont(context, resources, typedValue, i, i2, fontCallback, handler, z, z2);
        if (loadFont != null || fontCallback != null || z2) {
            return loadFont;
        }
        throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i) + " could not be retrieved.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Typeface loadFont(android.content.Context r16, android.content.res.Resources r17, android.util.TypedValue r18, int r19, int r20, androidx.core.content.res.ResourcesCompat.FontCallback r21, android.os.Handler r22, boolean r23, boolean r24) {
        /*
            r0 = r17
            r1 = r18
            r4 = r19
            r11 = r21
            r12 = r22
            java.lang.String r13 = "ResourcesCompat"
            java.lang.CharSequence r2 = r1.string
            if (r2 == 0) goto L_0x00c6
            java.lang.String r14 = r2.toString()
            java.lang.String r2 = "res/"
            boolean r2 = r14.startsWith(r2)
            r15 = 0
            r10 = -3
            if (r2 != 0) goto L_0x0024
            if (r11 == 0) goto L_0x0023
            r11.callbackFailAsync(r10, r12)
        L_0x0023:
            return r15
        L_0x0024:
            int r2 = r1.assetCookie
            r7 = r20
            android.graphics.Typeface r2 = androidx.core.graphics.TypefaceCompat.findFromCache(r0, r4, r14, r2, r7)
            if (r2 == 0) goto L_0x0034
            if (r11 == 0) goto L_0x0033
            r11.callbackSuccessAsync(r2, r12)
        L_0x0033:
            return r2
        L_0x0034:
            if (r24 == 0) goto L_0x0037
            return r15
        L_0x0037:
            java.lang.String r2 = r14.toLowerCase()     // Catch:{ XmlPullParserException -> 0x005b, IOException -> 0x0058 }
            java.lang.String r3 = ".xml"
            boolean r2 = r2.endsWith(r3)     // Catch:{ XmlPullParserException -> 0x005b, IOException -> 0x0058 }
            if (r2 == 0) goto L_0x007a
            android.content.res.XmlResourceParser r2 = r0.getXml(r4)     // Catch:{ XmlPullParserException -> 0x005b, IOException -> 0x0058 }
            androidx.core.content.res.FontResourcesParserCompat$FamilyResourceEntry r2 = androidx.core.content.res.FontResourcesParserCompat.parse(r2, r0)     // Catch:{ XmlPullParserException -> 0x005b, IOException -> 0x0058 }
            if (r2 != 0) goto L_0x005f
            java.lang.String r0 = "Failed to find font-family tag"
            android.util.Log.e(r13, r0)     // Catch:{ XmlPullParserException -> 0x005b, IOException -> 0x0058 }
            if (r11 == 0) goto L_0x005e
            r11.callbackFailAsync(r10, r12)     // Catch:{ XmlPullParserException -> 0x005b, IOException -> 0x0058 }
            goto L_0x005e
        L_0x0058:
            r0 = move-exception
            r15 = r10
            goto L_0x0096
        L_0x005b:
            r0 = move-exception
            r15 = r10
            goto L_0x00ab
        L_0x005e:
            return r15
        L_0x005f:
            int r6 = r1.assetCookie     // Catch:{ XmlPullParserException -> 0x005b, IOException -> 0x0058 }
            r1 = r16
            r3 = r17
            r4 = r19
            r5 = r14
            r7 = r20
            r8 = r21
            r9 = r22
            r15 = r10
            r10 = r23
            android.graphics.Typeface r0 = androidx.core.graphics.TypefaceCompat.createFromResourcesFamilyXml(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ XmlPullParserException -> 0x0078, IOException -> 0x0076 }
            return r0
        L_0x0076:
            r0 = move-exception
            goto L_0x0096
        L_0x0078:
            r0 = move-exception
            goto L_0x00ab
        L_0x007a:
            r15 = r10
            int r5 = r1.assetCookie     // Catch:{ XmlPullParserException -> 0x0078, IOException -> 0x0076 }
            r1 = r16
            r2 = r17
            r3 = r19
            r4 = r14
            r6 = r20
            android.graphics.Typeface r0 = androidx.core.graphics.TypefaceCompat.createFromResourcesFontFile(r1, r2, r3, r4, r5, r6)     // Catch:{ XmlPullParserException -> 0x0078, IOException -> 0x0076 }
            if (r11 == 0) goto L_0x0095
            if (r0 == 0) goto L_0x0092
            r11.callbackSuccessAsync(r0, r12)     // Catch:{ XmlPullParserException -> 0x0078, IOException -> 0x0076 }
            goto L_0x0095
        L_0x0092:
            r11.callbackFailAsync(r15, r12)     // Catch:{ XmlPullParserException -> 0x0078, IOException -> 0x0076 }
        L_0x0095:
            return r0
        L_0x0096:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Failed to read xml resource "
            r1.append(r2)
            r1.append(r14)
            java.lang.String r1 = r1.toString()
            android.util.Log.e(r13, r1, r0)
            goto L_0x00bf
        L_0x00ab:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Failed to parse xml resource "
            r1.append(r2)
            r1.append(r14)
            java.lang.String r1 = r1.toString()
            android.util.Log.e(r13, r1, r0)
        L_0x00bf:
            if (r11 == 0) goto L_0x00c4
            r11.callbackFailAsync(r15, r12)
        L_0x00c4:
            r1 = 0
            return r1
        L_0x00c6:
            android.content.res.Resources$NotFoundException r2 = new android.content.res.Resources$NotFoundException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "Resource \""
            r3.append(r5)
            java.lang.String r0 = r0.getResourceName(r4)
            r3.append(r0)
            java.lang.String r0 = "\" ("
            r3.append(r0)
            java.lang.String r0 = java.lang.Integer.toHexString(r19)
            r3.append(r0)
            java.lang.String r0 = ") is not a Font: "
            r3.append(r0)
            r3.append(r1)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.ResourcesCompat.loadFont(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, androidx.core.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean, boolean):android.graphics.Typeface");
    }

    static class Api23Impl {
        static ColorStateList getColorStateList(Resources resources, int i, Resources.Theme theme) {
            return resources.getColorStateList(i, theme);
        }

        static int getColor(Resources resources, int i, Resources.Theme theme) {
            return resources.getColor(i, theme);
        }
    }

    static class Api21Impl {
        static Drawable getDrawable(Resources resources, int i, Resources.Theme theme) {
            return resources.getDrawable(i, theme);
        }

        static Drawable getDrawableForDensity(Resources resources, int i, int i2, Resources.Theme theme) {
            return resources.getDrawableForDensity(i, i2, theme);
        }
    }

    public static final class ThemeCompat {
        public static void rebase(Resources.Theme theme) {
            if (Build.VERSION.SDK_INT >= 29) {
                Api29Impl.rebase(theme);
            } else {
                Api23Impl.rebase(theme);
            }
        }

        static class Api29Impl {
            static void rebase(Resources.Theme theme) {
                theme.rebase();
            }
        }

        static class Api23Impl {
            private static Method sRebaseMethod;
            private static boolean sRebaseMethodFetched;
            private static final Object sRebaseMethodLock = new Object();

            static void rebase(Resources.Theme theme) {
                synchronized (sRebaseMethodLock) {
                    if (!sRebaseMethodFetched) {
                        try {
                            Method declaredMethod = Resources.Theme.class.getDeclaredMethod("rebase", (Class[]) null);
                            sRebaseMethod = declaredMethod;
                            declaredMethod.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            Log.i("ResourcesCompat", "Failed to retrieve rebase() method", e);
                        }
                        sRebaseMethodFetched = true;
                    }
                    Method method = sRebaseMethod;
                    if (method != null) {
                        try {
                            method.invoke(theme, (Object[]) null);
                        } catch (IllegalAccessException | InvocationTargetException e2) {
                            Log.i("ResourcesCompat", "Failed to invoke rebase() method via reflection", e2);
                            sRebaseMethod = null;
                        }
                    }
                }
            }
        }
    }
}
