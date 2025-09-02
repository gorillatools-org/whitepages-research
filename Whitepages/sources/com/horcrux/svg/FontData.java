package com.horcrux.svg;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.assets.ReactFontManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.image.ReactImageView;

class FontData {
    static final FontData Defaults = new FontData();
    int absoluteFontWeight;
    final ReadableMap fontData;
    final String fontFamily;
    final String fontFeatureSettings;
    final double fontSize;
    final TextProperties$FontStyle fontStyle;
    final TextProperties$FontVariantLigatures fontVariantLigatures;
    final String fontVariationSettings;
    TextProperties$FontWeight fontWeight;
    final double kerning;
    final double letterSpacing;
    final boolean manualKerning;
    final TextProperties$TextAnchor textAnchor;
    private final TextProperties$TextDecoration textDecoration;
    final double wordSpacing;

    static class AbsoluteFontWeight {
        private static final TextProperties$FontWeight[] WEIGHTS;
        private static final int[] absoluteFontWeights = {ReactFontManager.TypefaceStyle.NORMAL, 700, 100, 200, ReactImageView.REMOTE_IMAGE_FADE_DURATION_MS, ReactFontManager.TypefaceStyle.NORMAL, 500, 600, 700, 800, 900};

        private static int bolder(int i) {
            if (i < 350) {
                return ReactFontManager.TypefaceStyle.NORMAL;
            }
            if (i < 550) {
                return 700;
            }
            if (i < 900) {
                return 900;
            }
            return i;
        }

        private static int lighter(int i) {
            if (i < 100) {
                return i;
            }
            if (i < 550) {
                return 100;
            }
            if (i < 750) {
                return ReactFontManager.TypefaceStyle.NORMAL;
            }
            return 700;
        }

        static {
            TextProperties$FontWeight textProperties$FontWeight = TextProperties$FontWeight.w100;
            TextProperties$FontWeight textProperties$FontWeight2 = TextProperties$FontWeight.w200;
            TextProperties$FontWeight textProperties$FontWeight3 = TextProperties$FontWeight.w300;
            TextProperties$FontWeight textProperties$FontWeight4 = TextProperties$FontWeight.Normal;
            TextProperties$FontWeight textProperties$FontWeight5 = TextProperties$FontWeight.w500;
            TextProperties$FontWeight textProperties$FontWeight6 = TextProperties$FontWeight.w600;
            TextProperties$FontWeight textProperties$FontWeight7 = TextProperties$FontWeight.Bold;
            TextProperties$FontWeight textProperties$FontWeight8 = TextProperties$FontWeight.w800;
            TextProperties$FontWeight textProperties$FontWeight9 = TextProperties$FontWeight.w900;
            WEIGHTS = new TextProperties$FontWeight[]{textProperties$FontWeight, textProperties$FontWeight, textProperties$FontWeight2, textProperties$FontWeight3, textProperties$FontWeight4, textProperties$FontWeight5, textProperties$FontWeight6, textProperties$FontWeight7, textProperties$FontWeight8, textProperties$FontWeight9, textProperties$FontWeight9};
        }

        static TextProperties$FontWeight nearestFontWeight(int i) {
            return WEIGHTS[Math.round(((float) i) / 100.0f)];
        }

        static int from(TextProperties$FontWeight textProperties$FontWeight, FontData fontData) {
            if (textProperties$FontWeight == TextProperties$FontWeight.Bolder) {
                return bolder(fontData.absoluteFontWeight);
            }
            if (textProperties$FontWeight == TextProperties$FontWeight.Lighter) {
                return lighter(fontData.absoluteFontWeight);
            }
            return absoluteFontWeights[textProperties$FontWeight.ordinal()];
        }
    }

    private FontData() {
        this.fontData = null;
        this.fontFamily = "";
        this.fontStyle = TextProperties$FontStyle.normal;
        this.fontWeight = TextProperties$FontWeight.Normal;
        this.absoluteFontWeight = ReactFontManager.TypefaceStyle.NORMAL;
        this.fontFeatureSettings = "";
        this.fontVariationSettings = "";
        this.fontVariantLigatures = TextProperties$FontVariantLigatures.normal;
        this.textAnchor = TextProperties$TextAnchor.start;
        this.textDecoration = TextProperties$TextDecoration.None;
        this.manualKerning = false;
        this.kerning = 0.0d;
        this.fontSize = 12.0d;
        this.wordSpacing = 0.0d;
        this.letterSpacing = 0.0d;
    }

    private double toAbsolute(ReadableMap readableMap, String str, double d, double d2, double d3) {
        if (readableMap.getType(str) == ReadableType.Number) {
            return readableMap.getDouble(str);
        }
        return PropHelper.fromRelative(readableMap.getString(str), d3, d, d2);
    }

    private void setInheritedWeight(FontData fontData2) {
        this.absoluteFontWeight = fontData2.absoluteFontWeight;
        this.fontWeight = fontData2.fontWeight;
    }

    private void handleNumericWeight(FontData fontData2, double d) {
        long round = Math.round(d);
        if (round < 1 || round > 1000) {
            setInheritedWeight(fontData2);
            return;
        }
        int i = (int) round;
        this.absoluteFontWeight = i;
        this.fontWeight = AbsoluteFontWeight.nearestFontWeight(i);
    }

    FontData(ReadableMap readableMap, FontData fontData2, double d) {
        String str;
        String str2;
        TextProperties$FontVariantLigatures textProperties$FontVariantLigatures;
        TextProperties$TextAnchor textProperties$TextAnchor;
        TextProperties$TextDecoration textProperties$TextDecoration;
        double d2;
        double d3;
        double d4 = fontData2.fontSize;
        if (readableMap.hasKey(ViewProps.FONT_SIZE)) {
            this.fontSize = toAbsolute(readableMap, ViewProps.FONT_SIZE, 1.0d, d4, d4);
        } else {
            this.fontSize = d4;
        }
        if (!readableMap.hasKey(ViewProps.FONT_WEIGHT)) {
            setInheritedWeight(fontData2);
        } else if (readableMap.getType(ViewProps.FONT_WEIGHT) == ReadableType.Number) {
            handleNumericWeight(fontData2, readableMap.getDouble(ViewProps.FONT_WEIGHT));
        } else {
            String string = readableMap.getString(ViewProps.FONT_WEIGHT);
            if (TextProperties$FontWeight.hasEnum(string)) {
                int from = AbsoluteFontWeight.from(TextProperties$FontWeight.get(string), fontData2);
                this.absoluteFontWeight = from;
                this.fontWeight = AbsoluteFontWeight.nearestFontWeight(from);
            } else if (string != null) {
                handleNumericWeight(fontData2, Double.parseDouble(string));
            } else {
                setInheritedWeight(fontData2);
            }
        }
        this.fontData = readableMap.hasKey("fontData") ? readableMap.getMap("fontData") : fontData2.fontData;
        this.fontFamily = readableMap.hasKey(ViewProps.FONT_FAMILY) ? readableMap.getString(ViewProps.FONT_FAMILY) : fontData2.fontFamily;
        this.fontStyle = readableMap.hasKey(ViewProps.FONT_STYLE) ? TextProperties$FontStyle.valueOf(readableMap.getString(ViewProps.FONT_STYLE)) : fontData2.fontStyle;
        if (readableMap.hasKey("fontFeatureSettings")) {
            str = readableMap.getString("fontFeatureSettings");
        } else {
            str = fontData2.fontFeatureSettings;
        }
        this.fontFeatureSettings = str;
        if (readableMap.hasKey("fontVariationSettings")) {
            str2 = readableMap.getString("fontVariationSettings");
        } else {
            str2 = fontData2.fontVariationSettings;
        }
        this.fontVariationSettings = str2;
        if (readableMap.hasKey("fontVariantLigatures")) {
            textProperties$FontVariantLigatures = TextProperties$FontVariantLigatures.valueOf(readableMap.getString("fontVariantLigatures"));
        } else {
            textProperties$FontVariantLigatures = fontData2.fontVariantLigatures;
        }
        this.fontVariantLigatures = textProperties$FontVariantLigatures;
        if (readableMap.hasKey("textAnchor")) {
            textProperties$TextAnchor = TextProperties$TextAnchor.valueOf(readableMap.getString("textAnchor"));
        } else {
            textProperties$TextAnchor = fontData2.textAnchor;
        }
        this.textAnchor = textProperties$TextAnchor;
        if (readableMap.hasKey("textDecoration")) {
            textProperties$TextDecoration = TextProperties$TextDecoration.getEnum(readableMap.getString("textDecoration"));
        } else {
            textProperties$TextDecoration = fontData2.textDecoration;
        }
        this.textDecoration = textProperties$TextDecoration;
        boolean hasKey = readableMap.hasKey("kerning");
        this.manualKerning = hasKey || fontData2.manualKerning;
        this.kerning = hasKey ? toAbsolute(readableMap, "kerning", d, this.fontSize, 0.0d) : fontData2.kerning;
        if (readableMap.hasKey("wordSpacing")) {
            d2 = toAbsolute(readableMap, "wordSpacing", d, this.fontSize, 0.0d);
        } else {
            d2 = fontData2.wordSpacing;
        }
        this.wordSpacing = d2;
        if (readableMap.hasKey(ViewProps.LETTER_SPACING)) {
            d3 = toAbsolute(readableMap, ViewProps.LETTER_SPACING, d, this.fontSize, 0.0d);
        } else {
            d3 = fontData2.letterSpacing;
        }
        this.letterSpacing = d3;
    }
}
