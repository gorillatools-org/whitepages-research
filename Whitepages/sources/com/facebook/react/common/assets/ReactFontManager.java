package com.facebook.react.common.assets;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.util.SparseArray;
import androidx.core.content.res.ResourcesCompat;
import com.facebook.react.uimanager.ViewProps;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReactFontManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String[] EXTENSIONS = {"", "_bold", "_italic", "_bold_italic"};
    /* access modifiers changed from: private */
    public static final String[] FILE_EXTENSIONS = {".ttf", ".otf"};
    private static final String FONTS_ASSET_PATH = "fonts/";
    /* access modifiers changed from: private */
    public static final ReactFontManager _instance = new ReactFontManager();
    private final Map<String, Typeface> customTypefaceCache = new LinkedHashMap();
    private final Map<String, AssetFontFamily> fontCache = new LinkedHashMap();

    public static final ReactFontManager getInstance() {
        return Companion.getInstance();
    }

    public final Typeface getTypeface(String str, int i, AssetManager assetManager) {
        Intrinsics.checkNotNullParameter(str, "fontFamilyName");
        return getTypeface(str, new TypefaceStyle(i, 0, 2, (DefaultConstructorMarker) null), assetManager);
    }

    public final Typeface getTypeface(String str, int i, boolean z, AssetManager assetManager) {
        Intrinsics.checkNotNullParameter(str, "fontFamilyName");
        return getTypeface(str, new TypefaceStyle(i, z), assetManager);
    }

    public final Typeface getTypeface(String str, int i, int i2, AssetManager assetManager) {
        Intrinsics.checkNotNullParameter(str, "fontFamilyName");
        return getTypeface(str, new TypefaceStyle(i, i2), assetManager);
    }

    public final Typeface getTypeface(String str, TypefaceStyle typefaceStyle, AssetManager assetManager) {
        Intrinsics.checkNotNullParameter(str, "fontFamilyName");
        Intrinsics.checkNotNullParameter(typefaceStyle, "typefaceStyle");
        if (this.customTypefaceCache.containsKey(str)) {
            return typefaceStyle.apply(this.customTypefaceCache.get(str));
        }
        Map<String, AssetFontFamily> map = this.fontCache;
        AssetFontFamily assetFontFamily = map.get(str);
        if (assetFontFamily == null) {
            assetFontFamily = new AssetFontFamily();
            map.put(str, assetFontFamily);
        }
        AssetFontFamily assetFontFamily2 = assetFontFamily;
        int nearestStyle = typefaceStyle.getNearestStyle();
        Typeface typefaceForStyle = assetFontFamily2.getTypefaceForStyle(nearestStyle);
        if (typefaceForStyle != null) {
            return typefaceForStyle;
        }
        Typeface access$createAssetTypeface = Companion.createAssetTypeface(str, nearestStyle, assetManager);
        assetFontFamily2.setTypefaceForStyle(nearestStyle, access$createAssetTypeface);
        return access$createAssetTypeface;
    }

    public final void addCustomFont(Context context, String str, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, ViewProps.FONT_FAMILY);
        addCustomFont(str, ResourcesCompat.getFont(context, i));
    }

    public final void addCustomFont(String str, Typeface typeface) {
        Intrinsics.checkNotNullParameter(str, ViewProps.FONT_FAMILY);
        if (typeface != null) {
            this.customTypefaceCache.put(str, typeface);
        }
    }

    public final void setTypeface(String str, int i, Typeface typeface) {
        Intrinsics.checkNotNullParameter(str, "fontFamilyName");
        if (typeface != null) {
            Map<String, AssetFontFamily> map = this.fontCache;
            AssetFontFamily assetFontFamily = map.get(str);
            if (assetFontFamily == null) {
                assetFontFamily = new AssetFontFamily();
                map.put(str, assetFontFamily);
            }
            assetFontFamily.setTypefaceForStyle(i, typeface);
        }
    }

    public static final class TypefaceStyle {
        public static final int BOLD = 700;
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public static final int NORMAL = 400;
        private final boolean italic;
        private final int weight;

        public TypefaceStyle(int i) {
            this(i, 0, 2, (DefaultConstructorMarker) null);
        }

        public TypefaceStyle(int i, boolean z) {
            this.italic = z;
            this.weight = i == -1 ? NORMAL : i;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ TypefaceStyle(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, (i3 & 2) != 0 ? -1 : i2);
        }

        public TypefaceStyle(int i, int i2) {
            boolean z = false;
            i = i == -1 ? 0 : i;
            this.italic = (i & 2) != 0 ? true : z;
            this.weight = i2 == -1 ? (i & 1) != 0 ? 700 : NORMAL : i2;
        }

        public final int getNearestStyle() {
            return this.weight < 700 ? this.italic ? 2 : 0 : this.italic ? 3 : 1;
        }

        public final Typeface apply(Typeface typeface) {
            if (Build.VERSION.SDK_INT < 28) {
                Typeface create = Typeface.create(typeface, getNearestStyle());
                Intrinsics.checkNotNull(create);
                return create;
            }
            Typeface m = Typeface.create(typeface, this.weight, this.italic);
            Intrinsics.checkNotNull(m);
            return m;
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ReactFontManager getInstance() {
            return ReactFontManager._instance;
        }

        /* access modifiers changed from: private */
        public final Typeface createAssetTypeface(String str, int i, AssetManager assetManager) {
            if (assetManager != null) {
                String str2 = ReactFontManager.EXTENSIONS[i];
                String[] access$getFILE_EXTENSIONS$cp = ReactFontManager.FILE_EXTENSIONS;
                int length = access$getFILE_EXTENSIONS$cp.length;
                int i2 = 0;
                while (i2 < length) {
                    String str3 = access$getFILE_EXTENSIONS$cp[i2];
                    try {
                        Typeface createFromAsset = Typeface.createFromAsset(assetManager, ReactFontManager.FONTS_ASSET_PATH + str + str2 + str3);
                        Intrinsics.checkNotNullExpressionValue(createFromAsset, "createFromAsset(...)");
                        return createFromAsset;
                    } catch (RuntimeException unused) {
                        i2++;
                    }
                }
            }
            Typeface create = Typeface.create(str, i);
            Intrinsics.checkNotNullExpressionValue(create, "create(...)");
            return create;
        }
    }

    private static final class AssetFontFamily {
        private final SparseArray<Typeface> typefaceSparseArray = new SparseArray<>(4);

        public final Typeface getTypefaceForStyle(int i) {
            return this.typefaceSparseArray.get(i);
        }

        public final void setTypefaceForStyle(int i, Typeface typeface) {
            this.typefaceSparseArray.put(i, typeface);
        }
    }
}
