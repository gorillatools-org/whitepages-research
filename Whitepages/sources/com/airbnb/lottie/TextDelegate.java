package com.airbnb.lottie;

import java.util.HashMap;
import java.util.Map;

public class TextDelegate {
    private final LottieAnimationView animationView;
    private boolean cacheText = true;
    private final LottieDrawable drawable;
    private final Map stringMap = new HashMap();

    public String getText(String str) {
        return str;
    }

    public TextDelegate(LottieAnimationView lottieAnimationView) {
        this.animationView = lottieAnimationView;
        this.drawable = null;
    }

    public String getText(String str, String str2) {
        return getText(str2);
    }

    public void setText(String str, String str2) {
        this.stringMap.put(str, str2);
        invalidate();
    }

    public final String getTextInternal(String str, String str2) {
        if (this.cacheText && this.stringMap.containsKey(str2)) {
            return (String) this.stringMap.get(str2);
        }
        String text = getText(str, str2);
        if (this.cacheText) {
            this.stringMap.put(str2, text);
        }
        return text;
    }

    private void invalidate() {
        LottieAnimationView lottieAnimationView = this.animationView;
        if (lottieAnimationView != null) {
            lottieAnimationView.invalidate();
        }
        LottieDrawable lottieDrawable = this.drawable;
        if (lottieDrawable != null) {
            lottieDrawable.invalidateSelf();
        }
    }
}
