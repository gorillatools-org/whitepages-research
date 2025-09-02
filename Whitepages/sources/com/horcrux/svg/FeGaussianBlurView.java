package com.horcrux.svg;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import com.facebook.react.bridge.ReactContext;
import java.util.HashMap;

class FeGaussianBlurView extends FilterPrimitiveView {
    FilterProperties$EdgeMode mEdgeMode;
    String mIn1;
    float mStdDeviationX;
    float mStdDeviationY;

    public FeGaussianBlurView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setIn1(String str) {
        this.mIn1 = str;
        invalidate();
    }

    public void setStdDeviationX(float f) {
        this.mStdDeviationX = f;
        invalidate();
    }

    public void setStdDeviationY(float f) {
        this.mStdDeviationY = f;
        invalidate();
    }

    public void setEdgeMode(String str) {
        this.mEdgeMode = FilterProperties$EdgeMode.getEnum(str);
        invalidate();
    }

    public Bitmap applyFilter(HashMap hashMap, Bitmap bitmap) {
        return blur(getContext(), FilterPrimitiveView.getSource(hashMap, bitmap, this.mIn1));
    }

    private Bitmap blur(Context context, Bitmap bitmap) {
        float max = Math.max(this.mStdDeviationX, this.mStdDeviationY) * 2.0f;
        if (max <= 0.0f) {
            return bitmap;
        }
        float min = Math.min(max, 25.0f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap);
        RenderScript create = RenderScript.create(context);
        ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
        Allocation createFromBitmap = Allocation.createFromBitmap(create, bitmap);
        Allocation createFromBitmap2 = Allocation.createFromBitmap(create, createBitmap);
        create2.setRadius(min);
        create2.setInput(createFromBitmap);
        create2.forEach(createFromBitmap2);
        createFromBitmap2.copyTo(createBitmap);
        createFromBitmap.destroy();
        createFromBitmap2.destroy();
        create.destroy();
        return Bitmap.createScaledBitmap(createBitmap, bitmap.getWidth(), bitmap.getHeight(), false);
    }
}
