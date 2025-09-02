package com.airbnb.android.react.lottie;

import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import com.airbnb.lottie.FontAssetDelegate;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.RenderMode;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.TextDelegate;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.util.RNLog;
import com.facebook.react.views.text.ReactFontManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;
import java.util.zip.ZipInputStream;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

public final class LottieAnimationViewPropertyManager {
    private final String TAG = "lottie-react-native";
    private String animationJson;
    private String animationName;
    private boolean animationNameDirty;
    private String animationURL;
    private Boolean autoPlay;
    private ReadableArray colorFilters;
    private Boolean enableMergePaths;
    private Boolean enableSafeMode;
    private String imageAssetsFolder;
    private Integer layerType;
    private Boolean loop;
    private Float progress;
    private RenderMode renderMode;
    private ImageView.ScaleType scaleType;
    private String sourceDotLottie;
    private Float speed;
    private ReadableArray textFilters;
    private final WeakReference viewWeakReference;

    public LottieAnimationViewPropertyManager(final LottieAnimationView lottieAnimationView) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        this.viewWeakReference = new WeakReference(lottieAnimationView);
        lottieAnimationView.setFontAssetDelegate(new FontAssetDelegate() {
            public Typeface fetchFont(String str) {
                Intrinsics.checkNotNullParameter(str, ViewProps.FONT_FAMILY);
                ReactFontManager instance = ReactFontManager.Companion.getInstance();
                AssetManager assets = lottieAnimationView.getContext().getAssets();
                Intrinsics.checkNotNullExpressionValue(assets, "getAssets(...)");
                return instance.getTypeface(str, -1, -1, assets);
            }

            /* JADX WARNING: Can't fix incorrect switch cases order */
            /* JADX WARNING: Code restructure failed: missing block: B:15:0x004d, code lost:
                if (r5.equals("Regular") == false) goto L_0x0064;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:17:0x0056, code lost:
                if (r5.equals(com.facebook.react.views.progressbar.ReactProgressBarViewManager.DEFAULT_STYLE) == false) goto L_0x0064;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:18:0x0059, code lost:
                r4 = com.facebook.react.common.assets.ReactFontManager.TypefaceStyle.NORMAL;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:21:0x0064, code lost:
                r4 = -1;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:23:0x0068, code lost:
                r5 = com.facebook.react.views.text.ReactFontManager.Companion.getInstance();
                r1 = r2.getContext().getAssets();
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, "getAssets(...)");
             */
            /* JADX WARNING: Code restructure failed: missing block: B:24:0x0081, code lost:
                return r5.getTypeface(r6, -1, r4, r1);
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public android.graphics.Typeface fetchFont(java.lang.String r4, java.lang.String r5, java.lang.String r6) {
                /*
                    r3 = this;
                    java.lang.String r0 = "fontFamily"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                    java.lang.String r4 = "fontStyle"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r4)
                    java.lang.String r4 = "fontName"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r4)
                    int r4 = r5.hashCode()
                    r0 = -1
                    switch(r4) {
                        case -1994163307: goto L_0x005c;
                        case -1955878649: goto L_0x0050;
                        case -1543850116: goto L_0x0047;
                        case 2076325: goto L_0x003b;
                        case 2605753: goto L_0x0030;
                        case 64266207: goto L_0x0024;
                        case 73417974: goto L_0x0018;
                        default: goto L_0x0017;
                    }
                L_0x0017:
                    goto L_0x0064
                L_0x0018:
                    java.lang.String r4 = "Light"
                    boolean r4 = r5.equals(r4)
                    if (r4 != 0) goto L_0x0021
                    goto L_0x0064
                L_0x0021:
                    r4 = 200(0xc8, float:2.8E-43)
                    goto L_0x0068
                L_0x0024:
                    java.lang.String r4 = "Black"
                    boolean r4 = r5.equals(r4)
                    if (r4 != 0) goto L_0x002d
                    goto L_0x0064
                L_0x002d:
                    r4 = 900(0x384, float:1.261E-42)
                    goto L_0x0068
                L_0x0030:
                    java.lang.String r4 = "Thin"
                    boolean r4 = r5.equals(r4)
                    if (r4 == 0) goto L_0x0064
                    r4 = 100
                    goto L_0x0068
                L_0x003b:
                    java.lang.String r4 = "Bold"
                    boolean r4 = r5.equals(r4)
                    if (r4 != 0) goto L_0x0044
                    goto L_0x0064
                L_0x0044:
                    r4 = 700(0x2bc, float:9.81E-43)
                    goto L_0x0068
                L_0x0047:
                    java.lang.String r4 = "Regular"
                    boolean r4 = r5.equals(r4)
                    if (r4 != 0) goto L_0x0059
                    goto L_0x0064
                L_0x0050:
                    java.lang.String r4 = "Normal"
                    boolean r4 = r5.equals(r4)
                    if (r4 != 0) goto L_0x0059
                    goto L_0x0064
                L_0x0059:
                    r4 = 400(0x190, float:5.6E-43)
                    goto L_0x0068
                L_0x005c:
                    java.lang.String r4 = "Medium"
                    boolean r4 = r5.equals(r4)
                    if (r4 != 0) goto L_0x0066
                L_0x0064:
                    r4 = r0
                    goto L_0x0068
                L_0x0066:
                    r4 = 500(0x1f4, float:7.0E-43)
                L_0x0068:
                    com.facebook.react.views.text.ReactFontManager$Companion r5 = com.facebook.react.views.text.ReactFontManager.Companion
                    com.facebook.react.views.text.ReactFontManager r5 = r5.getInstance()
                    com.airbnb.lottie.LottieAnimationView r1 = r2
                    android.content.Context r1 = r1.getContext()
                    android.content.res.AssetManager r1 = r1.getAssets()
                    java.lang.String r2 = "getAssets(...)"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                    android.graphics.Typeface r4 = r5.getTypeface((java.lang.String) r6, (int) r0, (int) r4, (android.content.res.AssetManager) r1)
                    return r4
                */
                throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.react.lottie.LottieAnimationViewPropertyManager.AnonymousClass1.fetchFont(java.lang.String, java.lang.String, java.lang.String):android.graphics.Typeface");
            }
        });
    }

    public final void setAnimationName(String str) {
        this.animationName = str;
        this.animationNameDirty = true;
    }

    public final void setScaleType(ImageView.ScaleType scaleType2) {
        this.scaleType = scaleType2;
    }

    public final void setImageAssetsFolder(String str) {
        this.imageAssetsFolder = str;
    }

    public final void setEnableMergePaths(Boolean bool) {
        this.enableMergePaths = bool;
    }

    public final void setEnableSafeMode(Boolean bool) {
        this.enableSafeMode = bool;
    }

    public final void setColorFilters(ReadableArray readableArray) {
        this.colorFilters = readableArray;
    }

    public final void setTextFilters(ReadableArray readableArray) {
        this.textFilters = readableArray;
    }

    public final void setRenderMode(RenderMode renderMode2) {
        this.renderMode = renderMode2;
    }

    public final void setLayerType(Integer num) {
        this.layerType = num;
    }

    public final void setAnimationJson(String str) {
        this.animationJson = str;
    }

    public final void setAnimationURL(String str) {
        this.animationURL = str;
    }

    public final void setSourceDotLottie(String str) {
        this.sourceDotLottie = str;
    }

    public final void setProgress(Float f) {
        this.progress = f;
    }

    public final void setLoop(Boolean bool) {
        this.loop = bool;
    }

    public final void setAutoPlay(Boolean bool) {
        this.autoPlay = bool;
    }

    public final void setSpeed(Float f) {
        this.speed = f;
    }

    public final void commitChanges() {
        Object obj;
        LottieAnimationView lottieAnimationView = (LottieAnimationView) this.viewWeakReference.get();
        if (lottieAnimationView != null) {
            ReadableArray readableArray = this.textFilters;
            if (readableArray != null && readableArray.size() > 0) {
                TextDelegate textDelegate = new TextDelegate(lottieAnimationView);
                int size = readableArray.size();
                for (int i = 0; i < size; i++) {
                    ReadableMap map = readableArray.getMap(i);
                    if (map != null) {
                        textDelegate.setText(map.getString("find"), map.getString("replace"));
                    }
                }
                lottieAnimationView.setTextDelegate(textDelegate);
            }
            String str = this.animationJson;
            if (str != null) {
                lottieAnimationView.setAnimationFromJson(str, String.valueOf(str.hashCode()));
                this.animationJson = null;
            }
            String str2 = this.animationURL;
            if (str2 != null) {
                File file = new File(str2);
                if (file.exists()) {
                    lottieAnimationView.setAnimation((InputStream) new FileInputStream(file), String.valueOf(str2.hashCode()));
                } else {
                    lottieAnimationView.setAnimationFromUrl(str2, String.valueOf(str2.hashCode()));
                }
                this.animationURL = null;
            }
            String str3 = this.sourceDotLottie;
            if (str3 != null) {
                File file2 = new File(str3);
                if (file2.exists()) {
                    lottieAnimationView.setAnimation(new ZipInputStream(new FileInputStream(file2)), String.valueOf(str3.hashCode()));
                    this.sourceDotLottie = null;
                    return;
                }
                try {
                    Result.Companion companion = Result.Companion;
                    obj = Result.m866constructorimpl(Uri.parse(str3).getScheme());
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    obj = Result.m866constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m868isFailureimpl(obj)) {
                    obj = null;
                }
                String str4 = (String) obj;
                if (str4 != null) {
                    if (Intrinsics.areEqual((Object) str4, (Object) "file")) {
                        String path = Uri.parse(str3).getPath();
                        if (path != null) {
                            lottieAnimationView.setAnimation(new ZipInputStream(new FileInputStream(new File(path))), String.valueOf(str3.hashCode()));
                        } else {
                            Log.w(this.TAG, "URI path is null for asset: " + str3);
                        }
                    } else {
                        lottieAnimationView.setAnimationFromUrl(str3);
                    }
                    this.sourceDotLottie = null;
                    return;
                }
                int identifier = lottieAnimationView.getResources().getIdentifier(str3, "raw", lottieAnimationView.getContext().getPackageName());
                if (identifier == 0) {
                    RNLog.e("Animation for " + str3 + " was not found in raw resources");
                    return;
                }
                lottieAnimationView.setAnimation(identifier);
                this.animationNameDirty = false;
                this.sourceDotLottie = null;
            }
            if (this.animationNameDirty) {
                lottieAnimationView.setAnimation(this.animationName);
                this.animationNameDirty = false;
            }
            Float f = this.progress;
            if (f != null) {
                lottieAnimationView.setProgress(f.floatValue());
                this.progress = null;
            }
            Boolean bool = this.loop;
            if (bool != null) {
                lottieAnimationView.setRepeatCount(bool.booleanValue() ? -1 : 0);
                this.loop = null;
            }
            Boolean bool2 = this.autoPlay;
            if (bool2 != null && bool2.booleanValue() && !lottieAnimationView.isAnimating()) {
                lottieAnimationView.playAnimation();
            }
            Float f2 = this.speed;
            if (f2 != null) {
                lottieAnimationView.setSpeed(f2.floatValue());
                this.speed = null;
            }
            ImageView.ScaleType scaleType2 = this.scaleType;
            if (scaleType2 != null) {
                lottieAnimationView.setScaleType(scaleType2);
                this.scaleType = null;
            }
            RenderMode renderMode2 = this.renderMode;
            if (renderMode2 != null) {
                lottieAnimationView.setRenderMode(renderMode2);
                this.renderMode = null;
            }
            Integer num = this.layerType;
            if (num != null) {
                lottieAnimationView.setLayerType(num.intValue(), (Paint) null);
            }
            String str5 = this.imageAssetsFolder;
            if (str5 != null) {
                lottieAnimationView.setImageAssetsFolder(str5);
                this.imageAssetsFolder = null;
            }
            Boolean bool3 = this.enableMergePaths;
            if (bool3 != null) {
                lottieAnimationView.enableMergePathsForKitKatAndAbove(bool3.booleanValue());
                this.enableMergePaths = null;
            }
            Boolean bool4 = this.enableSafeMode;
            if (bool4 != null) {
                lottieAnimationView.setSafeMode(bool4.booleanValue());
                this.enableSafeMode = null;
            }
            ReadableArray readableArray2 = this.colorFilters;
            if (readableArray2 != null && readableArray2.size() > 0) {
                int size2 = readableArray2.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    ReadableMap map2 = readableArray2.getMap(i2);
                    if (map2 != null) {
                        parseColorFilter(map2, lottieAnimationView);
                    }
                }
            }
        }
    }

    private final void parseColorFilter(ReadableMap readableMap, LottieAnimationView lottieAnimationView) {
        int i;
        List list;
        if (readableMap.getType(ViewProps.COLOR) == ReadableType.Map) {
            Integer color = ColorPropConverter.getColor(readableMap.getMap(ViewProps.COLOR), lottieAnimationView.getContext());
            Intrinsics.checkNotNull(color);
            i = color.intValue();
        } else {
            i = readableMap.getInt(ViewProps.COLOR);
        }
        String quote = Pattern.quote(".");
        Intrinsics.checkNotNullExpressionValue(quote, "quote(...)");
        List split = new Regex(quote).split(readableMap.getString("keypath") + ".**", 0);
        if (!split.isEmpty()) {
            ListIterator listIterator = split.listIterator(split.size());
            while (true) {
                if (listIterator.hasPrevious()) {
                    if (((String) listIterator.previous()).length() != 0) {
                        list = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                        break;
                    }
                } else {
                    break;
                }
            }
            String[] strArr = (String[]) list.toArray(new String[0]);
            lottieAnimationView.addValueCallback(new KeyPath((String[]) Arrays.copyOf(strArr, strArr.length)), LottieProperty.COLOR_FILTER, new LottieValueCallback(new SimpleColorFilter(i)));
        }
        list = CollectionsKt.emptyList();
        String[] strArr2 = (String[]) list.toArray(new String[0]);
        lottieAnimationView.addValueCallback(new KeyPath((String[]) Arrays.copyOf(strArr2, strArr2.length)), LottieProperty.COLOR_FILTER, new LottieValueCallback(new SimpleColorFilter(i)));
    }
}
