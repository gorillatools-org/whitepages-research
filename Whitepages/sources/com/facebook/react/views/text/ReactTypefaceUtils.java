package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.TextUtils;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.assets.ReactFontManager;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class ReactTypefaceUtils {
    public static final ReactTypefaceUtils INSTANCE = new ReactTypefaceUtils();

    private ReactTypefaceUtils() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003a, code lost:
        return 700;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008b, code lost:
        return com.facebook.react.common.assets.ReactFontManager.TypefaceStyle.NORMAL;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008e A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int parseFontWeight(java.lang.String r1) {
        /*
            if (r1 == 0) goto L_0x008e
            int r0 = r1.hashCode()
            switch(r0) {
                case -1039745817: goto L_0x0082;
                case 48625: goto L_0x0076;
                case 49586: goto L_0x006a;
                case 50547: goto L_0x005e;
                case 51508: goto L_0x0055;
                case 52469: goto L_0x0049;
                case 53430: goto L_0x003d;
                case 54391: goto L_0x0031;
                case 55352: goto L_0x0023;
                case 56313: goto L_0x0015;
                case 3029637: goto L_0x000b;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x008e
        L_0x000b:
            java.lang.String r0 = "bold"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x003a
            goto L_0x008e
        L_0x0015:
            java.lang.String r0 = "900"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x001f
            goto L_0x008e
        L_0x001f:
            r1 = 900(0x384, float:1.261E-42)
            goto L_0x008f
        L_0x0023:
            java.lang.String r0 = "800"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x002d
            goto L_0x008e
        L_0x002d:
            r1 = 800(0x320, float:1.121E-42)
            goto L_0x008f
        L_0x0031:
            java.lang.String r0 = "700"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x003a
            goto L_0x008e
        L_0x003a:
            r1 = 700(0x2bc, float:9.81E-43)
            goto L_0x008f
        L_0x003d:
            java.lang.String r0 = "600"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0046
            goto L_0x008e
        L_0x0046:
            r1 = 600(0x258, float:8.41E-43)
            goto L_0x008f
        L_0x0049:
            java.lang.String r0 = "500"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0052
            goto L_0x008e
        L_0x0052:
            r1 = 500(0x1f4, float:7.0E-43)
            goto L_0x008f
        L_0x0055:
            java.lang.String r0 = "400"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x008b
            goto L_0x008e
        L_0x005e:
            java.lang.String r0 = "300"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0067
            goto L_0x008e
        L_0x0067:
            r1 = 300(0x12c, float:4.2E-43)
            goto L_0x008f
        L_0x006a:
            java.lang.String r0 = "200"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0073
            goto L_0x008e
        L_0x0073:
            r1 = 200(0xc8, float:2.8E-43)
            goto L_0x008f
        L_0x0076:
            java.lang.String r0 = "100"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x007f
            goto L_0x008e
        L_0x007f:
            r1 = 100
            goto L_0x008f
        L_0x0082:
            java.lang.String r0 = "normal"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x008b
            goto L_0x008e
        L_0x008b:
            r1 = 400(0x190, float:5.6E-43)
            goto L_0x008f
        L_0x008e:
            r1 = -1
        L_0x008f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactTypefaceUtils.parseFontWeight(java.lang.String):int");
    }

    public static final int parseFontStyle(String str) {
        if (Intrinsics.areEqual((Object) str, (Object) "italic")) {
            return 2;
        }
        return Intrinsics.areEqual((Object) str, (Object) "normal") ? 0 : -1;
    }

    public static final String parseFontVariant(ReadableArray readableArray) {
        if (readableArray == null || readableArray.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int size = readableArray.size();
        for (int i = 0; i < size; i++) {
            String string = readableArray.getString(i);
            if (string != null) {
                switch (string.hashCode()) {
                    case -1983120972:
                        if (string.equals("stylistic-thirteen")) {
                            arrayList.add("'ss13'");
                            break;
                        } else {
                            break;
                        }
                    case -1933522176:
                        if (string.equals("stylistic-fifteen")) {
                            arrayList.add("'ss15'");
                            break;
                        } else {
                            break;
                        }
                    case -1534462052:
                        if (string.equals("stylistic-eighteen")) {
                            arrayList.add("'ss18'");
                            break;
                        } else {
                            break;
                        }
                    case -1195362251:
                        if (string.equals("proportional-nums")) {
                            arrayList.add("'pnum'");
                            break;
                        } else {
                            break;
                        }
                    case -1061392823:
                        if (string.equals("lining-nums")) {
                            arrayList.add("'lnum'");
                            break;
                        } else {
                            break;
                        }
                    case -899039099:
                        if (string.equals("historical-ligatures")) {
                            arrayList.add("'hlig'");
                            break;
                        } else {
                            break;
                        }
                    case -771984547:
                        if (string.equals("tabular-nums")) {
                            arrayList.add("'tnum'");
                            break;
                        } else {
                            break;
                        }
                    case -672279417:
                        if (string.equals("discretionary-ligatures")) {
                            arrayList.add("'dlig'");
                            break;
                        } else {
                            break;
                        }
                    case -659678800:
                        if (string.equals("oldstyle-nums")) {
                            arrayList.add("'onum'");
                            break;
                        } else {
                            break;
                        }
                    case 249095901:
                        if (string.equals("no-contextual")) {
                            arrayList.add("'calt' off");
                            break;
                        } else {
                            break;
                        }
                    case 273808209:
                        if (string.equals("contextual")) {
                            arrayList.add("'calt'");
                            break;
                        } else {
                            break;
                        }
                    case 289909490:
                        if (string.equals("no-common-ligatures")) {
                            arrayList.add("'liga' off");
                            arrayList.add("'clig' off");
                            break;
                        } else {
                            break;
                        }
                    case 296506098:
                        if (string.equals("stylistic-eight")) {
                            arrayList.add("'ss08'");
                            break;
                        } else {
                            break;
                        }
                    case 309330544:
                        if (string.equals("stylistic-seven")) {
                            arrayList.add("'ss07'");
                            break;
                        } else {
                            break;
                        }
                    case 310339585:
                        if (string.equals("stylistic-three")) {
                            arrayList.add("'ss03'");
                            break;
                        } else {
                            break;
                        }
                    case 604478526:
                        if (string.equals("stylistic-eleven")) {
                            arrayList.add("'ss11'");
                            break;
                        } else {
                            break;
                        }
                    case 915975441:
                        if (string.equals("no-historical-ligatures")) {
                            arrayList.add("'hlig' off");
                            break;
                        } else {
                            break;
                        }
                    case 979426287:
                        if (string.equals("stylistic-five")) {
                            arrayList.add("'ss05'");
                            break;
                        } else {
                            break;
                        }
                    case 979432035:
                        if (string.equals("stylistic-four")) {
                            arrayList.add("'ss04'");
                            break;
                        } else {
                            break;
                        }
                    case 979664367:
                        if (string.equals("stylistic-nine")) {
                            arrayList.add("'ss09'");
                            break;
                        } else {
                            break;
                        }
                    case 1001434505:
                        if (string.equals("stylistic-one")) {
                            arrayList.add("'ss01'");
                            break;
                        } else {
                            break;
                        }
                    case 1001438213:
                        if (string.equals("stylistic-six")) {
                            arrayList.add("'ss06'");
                            break;
                        } else {
                            break;
                        }
                    case 1001439040:
                        if (string.equals("stylistic-ten")) {
                            arrayList.add("'ss10'");
                            break;
                        } else {
                            break;
                        }
                    case 1001439599:
                        if (string.equals("stylistic-two")) {
                            arrayList.add("'ss02'");
                            break;
                        } else {
                            break;
                        }
                    case 1030714463:
                        if (string.equals("stylistic-sixteen")) {
                            arrayList.add("'ss16'");
                            break;
                        } else {
                            break;
                        }
                    case 1044065430:
                        if (string.equals("stylistic-twelve")) {
                            arrayList.add("'ss12'");
                            break;
                        } else {
                            break;
                        }
                    case 1044067310:
                        if (string.equals("stylistic-twenty")) {
                            arrayList.add("'ss20'");
                            break;
                        } else {
                            break;
                        }
                    case 1082592379:
                        if (string.equals("no-discretionary-ligatures")) {
                            arrayList.add("'dlig' off");
                            break;
                        } else {
                            break;
                        }
                    case 1183323111:
                        if (string.equals("small-caps")) {
                            arrayList.add("'smcp'");
                            break;
                        } else {
                            break;
                        }
                    case 1223989350:
                        if (string.equals("common-ligatures")) {
                            arrayList.add("'liga'");
                            arrayList.add("'clig'");
                            break;
                        } else {
                            break;
                        }
                    case 1463562569:
                        if (string.equals("stylistic-nineteen")) {
                            arrayList.add("'ss19'");
                            break;
                        } else {
                            break;
                        }
                    case 1648446397:
                        if (string.equals("stylistic-fourteen")) {
                            arrayList.add("'ss14'");
                            break;
                        } else {
                            break;
                        }
                    case 2097122634:
                        if (string.equals("stylistic-seventeen")) {
                            arrayList.add("'ss17'");
                            break;
                        } else {
                            break;
                        }
                }
            }
        }
        return TextUtils.join(", ", arrayList);
    }

    public static final Typeface applyStyles(Typeface typeface, int i, int i2, String str, AssetManager assetManager) {
        Intrinsics.checkNotNullParameter(assetManager, "assetManager");
        ReactFontManager.TypefaceStyle typefaceStyle = new ReactFontManager.TypefaceStyle(i, i2);
        if (str != null) {
            return ReactFontManager.Companion.getInstance().getTypeface(str, typefaceStyle, assetManager);
        }
        if (typeface == null) {
            typeface = Typeface.DEFAULT;
        }
        return typefaceStyle.apply(typeface);
    }
}
