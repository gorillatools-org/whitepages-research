package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;
import kotlin.jvm.internal.DefaultConstructorMarker;

public class ValueAnimatedNode extends AnimatedNode {
    public double nodeValue;
    public double offset;
    private AnimatedNodeValueListener valueListener;

    public ValueAnimatedNode() {
        this((ReadableMap) null, 1, (DefaultConstructorMarker) null);
    }

    public Object getAnimatedObject() {
        return null;
    }

    public ValueAnimatedNode(ReadableMap readableMap) {
        this.nodeValue = readableMap != null ? readableMap.getDouble("value") : Double.NaN;
        this.offset = readableMap != null ? readableMap.getDouble("offset") : 0.0d;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ValueAnimatedNode(ReadableMap readableMap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : readableMap);
    }

    public final double getValue() {
        if (Double.isNaN(this.offset + this.nodeValue)) {
            update();
        }
        return this.offset + this.nodeValue;
    }

    public final void flattenOffset() {
        this.nodeValue += this.offset;
        this.offset = 0.0d;
    }

    public final void extractOffset() {
        this.offset += this.nodeValue;
        this.nodeValue = 0.0d;
    }

    public final void onValueUpdate() {
        AnimatedNodeValueListener animatedNodeValueListener = this.valueListener;
        if (animatedNodeValueListener != null) {
            animatedNodeValueListener.onValueUpdate(getValue());
        }
    }

    public final void setValueListener(AnimatedNodeValueListener animatedNodeValueListener) {
        this.valueListener = animatedNodeValueListener;
    }

    public String prettyPrint() {
        int i = this.tag;
        double d = this.nodeValue;
        double d2 = this.offset;
        return "ValueAnimatedNode[" + i + "]: value: " + d + " offset: " + d2;
    }
}
