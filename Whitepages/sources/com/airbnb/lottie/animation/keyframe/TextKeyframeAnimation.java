package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class TextKeyframeAnimation extends KeyframeAnimation {
    public TextKeyframeAnimation(List list) {
        super(list);
    }

    /* access modifiers changed from: package-private */
    public DocumentData getValue(Keyframe keyframe, float f) {
        Object obj;
        LottieValueCallback lottieValueCallback = this.valueCallback;
        if (lottieValueCallback != null) {
            float f2 = keyframe.startFrame;
            Float f3 = keyframe.endFrame;
            float floatValue = f3 == null ? Float.MAX_VALUE : f3.floatValue();
            Object obj2 = keyframe.startValue;
            DocumentData documentData = (DocumentData) obj2;
            Object obj3 = keyframe.endValue;
            return (DocumentData) lottieValueCallback.getValueInternal(f2, floatValue, documentData, obj3 == null ? (DocumentData) obj2 : (DocumentData) obj3, f, getInterpolatedCurrentKeyframeProgress(), getProgress());
        } else if (f != 1.0f || (obj = keyframe.endValue) == null) {
            return (DocumentData) keyframe.startValue;
        } else {
            return (DocumentData) obj;
        }
    }

    public void setStringValueCallback(final LottieValueCallback lottieValueCallback) {
        final LottieFrameInfo lottieFrameInfo = new LottieFrameInfo();
        final DocumentData documentData = new DocumentData();
        super.setValueCallback(new LottieValueCallback() {
            public DocumentData getValue(LottieFrameInfo lottieFrameInfo) {
                lottieFrameInfo.set(lottieFrameInfo.getStartFrame(), lottieFrameInfo.getEndFrame(), ((DocumentData) lottieFrameInfo.getStartValue()).text, ((DocumentData) lottieFrameInfo.getEndValue()).text, lottieFrameInfo.getLinearKeyframeProgress(), lottieFrameInfo.getInterpolatedKeyframeProgress(), lottieFrameInfo.getOverallProgress());
                String str = (String) lottieValueCallback.getValue(lottieFrameInfo);
                DocumentData documentData = (DocumentData) (lottieFrameInfo.getInterpolatedKeyframeProgress() == 1.0f ? lottieFrameInfo.getEndValue() : lottieFrameInfo.getStartValue());
                documentData.set(str, documentData.fontName, documentData.size, documentData.justification, documentData.tracking, documentData.lineHeight, documentData.baselineShift, documentData.color, documentData.strokeColor, documentData.strokeWidth, documentData.strokeOverFill, documentData.boxPosition, documentData.boxSize);
                return documentData;
            }
        });
    }
}
