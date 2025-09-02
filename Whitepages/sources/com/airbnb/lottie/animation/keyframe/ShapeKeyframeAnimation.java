package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import com.airbnb.lottie.animation.content.ShapeModifierContent;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class ShapeKeyframeAnimation extends BaseKeyframeAnimation {
    private List shapeModifiers;
    private final Path tempPath = new Path();
    private final ShapeData tempShapeData = new ShapeData();
    private Path valueCallbackEndPath;
    private Path valueCallbackStartPath;

    public ShapeKeyframeAnimation(List list) {
        super(list);
    }

    public Path getValue(Keyframe keyframe, float f) {
        Path path;
        ShapeData shapeData = (ShapeData) keyframe.startValue;
        ShapeData shapeData2 = (ShapeData) keyframe.endValue;
        this.tempShapeData.interpolateBetween(shapeData, shapeData2 == null ? shapeData : shapeData2, f);
        ShapeData shapeData3 = this.tempShapeData;
        List list = this.shapeModifiers;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                shapeData3 = ((ShapeModifierContent) this.shapeModifiers.get(size)).modifyShape(shapeData3);
            }
        }
        MiscUtils.getPathFromData(shapeData3, this.tempPath);
        if (this.valueCallback == null) {
            return this.tempPath;
        }
        if (this.valueCallbackStartPath == null) {
            this.valueCallbackStartPath = new Path();
            this.valueCallbackEndPath = new Path();
        }
        MiscUtils.getPathFromData(shapeData, this.valueCallbackStartPath);
        if (shapeData2 != null) {
            MiscUtils.getPathFromData(shapeData2, this.valueCallbackEndPath);
        }
        LottieValueCallback lottieValueCallback = this.valueCallback;
        float f2 = keyframe.startFrame;
        float floatValue = keyframe.endFrame.floatValue();
        Path path2 = this.valueCallbackStartPath;
        if (shapeData2 == null) {
            path = path2;
        } else {
            path = this.valueCallbackEndPath;
        }
        return (Path) lottieValueCallback.getValueInternal(f2, floatValue, path2, path, f, getLinearCurrentKeyframeProgress(), getProgress());
    }

    public void setShapeModifiers(List list) {
        this.shapeModifiers = list;
    }
}
