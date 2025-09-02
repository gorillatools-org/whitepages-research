package com.airbnb.lottie.manager;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.airbnb.lottie.FontAssetDelegate;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.MutablePair;
import com.airbnb.lottie.utils.Logger;
import java.util.HashMap;
import java.util.Map;

public class FontAssetManager {
    private final AssetManager assetManager;
    private String defaultFontFileExtension = ".ttf";
    private FontAssetDelegate delegate;
    private final Map fontFamilies = new HashMap();
    private final Map fontMap = new HashMap();
    private final MutablePair tempPair = new MutablePair();

    public FontAssetManager(Drawable.Callback callback, FontAssetDelegate fontAssetDelegate) {
        this.delegate = fontAssetDelegate;
        if (!(callback instanceof View)) {
            Logger.warning("LottieDrawable must be inside of a view for images to work.");
            this.assetManager = null;
            return;
        }
        this.assetManager = ((View) callback).getContext().getAssets();
    }

    public void setDelegate(FontAssetDelegate fontAssetDelegate) {
        this.delegate = fontAssetDelegate;
    }

    public void setDefaultFontFileExtension(String str) {
        this.defaultFontFileExtension = str;
    }

    public Typeface getTypeface(Font font) {
        this.tempPair.set(font.getFamily(), font.getStyle());
        Typeface typeface = (Typeface) this.fontMap.get(this.tempPair);
        if (typeface != null) {
            return typeface;
        }
        Typeface typefaceForStyle = typefaceForStyle(getFontFamily(font), font.getStyle());
        this.fontMap.put(this.tempPair, typefaceForStyle);
        return typefaceForStyle;
    }

    private Typeface getFontFamily(Font font) {
        Typeface typeface;
        String family = font.getFamily();
        Typeface typeface2 = (Typeface) this.fontFamilies.get(family);
        if (typeface2 != null) {
            return typeface2;
        }
        String style = font.getStyle();
        String name = font.getName();
        FontAssetDelegate fontAssetDelegate = this.delegate;
        if (fontAssetDelegate != null) {
            typeface = fontAssetDelegate.fetchFont(family, style, name);
            if (typeface == null) {
                typeface = this.delegate.fetchFont(family);
            }
        } else {
            typeface = null;
        }
        FontAssetDelegate fontAssetDelegate2 = this.delegate;
        if (fontAssetDelegate2 != null && typeface == null) {
            String fontPath = fontAssetDelegate2.getFontPath(family, style, name);
            if (fontPath == null) {
                fontPath = this.delegate.getFontPath(family);
            }
            if (fontPath != null) {
                typeface = Typeface.createFromAsset(this.assetManager, fontPath);
            }
        }
        if (font.getTypeface() != null) {
            return font.getTypeface();
        }
        if (typeface == null) {
            typeface = Typeface.createFromAsset(this.assetManager, "fonts/" + family + this.defaultFontFileExtension);
        }
        this.fontFamilies.put(family, typeface);
        return typeface;
    }

    private Typeface typefaceForStyle(Typeface typeface, String str) {
        boolean contains = str.contains("Italic");
        boolean contains2 = str.contains("Bold");
        int i = (!contains || !contains2) ? contains ? 2 : contains2 ? 1 : 0 : 3;
        if (typeface.getStyle() == i) {
            return typeface;
        }
        return Typeface.create(typeface, i);
    }
}
