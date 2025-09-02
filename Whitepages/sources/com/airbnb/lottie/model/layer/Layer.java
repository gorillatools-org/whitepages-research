package com.airbnb.lottie.model.layer;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.LBlendMode;
import com.airbnb.lottie.parser.DropShadowEffect;
import java.util.List;
import java.util.Locale;

public class Layer {
    private final LBlendMode blendMode;
    private final BlurEffect blurEffect;
    private final LottieComposition composition;
    private final DropShadowEffect dropShadowEffect;
    private final boolean hidden;
    private final List inOutKeyframes;
    private final long layerId;
    private final String layerName;
    private final LayerType layerType;
    private final List masks;
    private final MatteType matteType;
    private final long parentId;
    private final float preCompHeight;
    private final float preCompWidth;
    private final String refId;
    private final List shapes;
    private final int solidColor;
    private final int solidHeight;
    private final int solidWidth;
    private final float startFrame;
    private final AnimatableTextFrame text;
    private final AnimatableTextProperties textProperties;
    private final AnimatableFloatValue timeRemapping;
    private final float timeStretch;
    private final AnimatableTransform transform;

    public enum LayerType {
        PRE_COMP,
        SOLID,
        IMAGE,
        NULL,
        SHAPE,
        TEXT,
        UNKNOWN
    }

    public enum MatteType {
        NONE,
        ADD,
        INVERT,
        LUMA,
        LUMA_INVERTED,
        UNKNOWN
    }

    public Layer(List list, LottieComposition lottieComposition, String str, long j, LayerType layerType2, long j2, String str2, List list2, AnimatableTransform animatableTransform, int i, int i2, int i3, float f, float f2, float f3, float f4, AnimatableTextFrame animatableTextFrame, AnimatableTextProperties animatableTextProperties, List list3, MatteType matteType2, AnimatableFloatValue animatableFloatValue, boolean z, BlurEffect blurEffect2, DropShadowEffect dropShadowEffect2, LBlendMode lBlendMode) {
        this.shapes = list;
        this.composition = lottieComposition;
        this.layerName = str;
        this.layerId = j;
        this.layerType = layerType2;
        this.parentId = j2;
        this.refId = str2;
        this.masks = list2;
        this.transform = animatableTransform;
        this.solidWidth = i;
        this.solidHeight = i2;
        this.solidColor = i3;
        this.timeStretch = f;
        this.startFrame = f2;
        this.preCompWidth = f3;
        this.preCompHeight = f4;
        this.text = animatableTextFrame;
        this.textProperties = animatableTextProperties;
        this.inOutKeyframes = list3;
        this.matteType = matteType2;
        this.timeRemapping = animatableFloatValue;
        this.hidden = z;
        this.blurEffect = blurEffect2;
        this.dropShadowEffect = dropShadowEffect2;
        this.blendMode = lBlendMode;
    }

    /* access modifiers changed from: package-private */
    public LottieComposition getComposition() {
        return this.composition;
    }

    /* access modifiers changed from: package-private */
    public float getTimeStretch() {
        return this.timeStretch;
    }

    /* access modifiers changed from: package-private */
    public float getStartProgress() {
        return this.startFrame / this.composition.getDurationFrames();
    }

    /* access modifiers changed from: package-private */
    public List getInOutKeyframes() {
        return this.inOutKeyframes;
    }

    public long getId() {
        return this.layerId;
    }

    public String getName() {
        return this.layerName;
    }

    public String getRefId() {
        return this.refId;
    }

    /* access modifiers changed from: package-private */
    public float getPreCompWidth() {
        return this.preCompWidth;
    }

    /* access modifiers changed from: package-private */
    public float getPreCompHeight() {
        return this.preCompHeight;
    }

    /* access modifiers changed from: package-private */
    public List getMasks() {
        return this.masks;
    }

    public LayerType getLayerType() {
        return this.layerType;
    }

    /* access modifiers changed from: package-private */
    public MatteType getMatteType() {
        return this.matteType;
    }

    /* access modifiers changed from: package-private */
    public long getParentId() {
        return this.parentId;
    }

    /* access modifiers changed from: package-private */
    public List getShapes() {
        return this.shapes;
    }

    /* access modifiers changed from: package-private */
    public AnimatableTransform getTransform() {
        return this.transform;
    }

    /* access modifiers changed from: package-private */
    public int getSolidColor() {
        return this.solidColor;
    }

    /* access modifiers changed from: package-private */
    public int getSolidHeight() {
        return this.solidHeight;
    }

    /* access modifiers changed from: package-private */
    public int getSolidWidth() {
        return this.solidWidth;
    }

    /* access modifiers changed from: package-private */
    public AnimatableTextFrame getText() {
        return this.text;
    }

    /* access modifiers changed from: package-private */
    public AnimatableTextProperties getTextProperties() {
        return this.textProperties;
    }

    /* access modifiers changed from: package-private */
    public AnimatableFloatValue getTimeRemapping() {
        return this.timeRemapping;
    }

    public String toString() {
        return toString("");
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public LBlendMode getBlendMode() {
        return this.blendMode;
    }

    public BlurEffect getBlurEffect() {
        return this.blurEffect;
    }

    public DropShadowEffect getDropShadowEffect() {
        return this.dropShadowEffect;
    }

    public String toString(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(getName());
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        Layer layerModelForId = this.composition.layerModelForId(getParentId());
        if (layerModelForId != null) {
            sb.append("\t\tParents: ");
            sb.append(layerModelForId.getName());
            Layer layerModelForId2 = this.composition.layerModelForId(layerModelForId.getParentId());
            while (layerModelForId2 != null) {
                sb.append("->");
                sb.append(layerModelForId2.getName());
                layerModelForId2 = this.composition.layerModelForId(layerModelForId2.getParentId());
            }
            sb.append(str);
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (!getMasks().isEmpty()) {
            sb.append(str);
            sb.append("\tMasks: ");
            sb.append(getMasks().size());
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (!(getSolidWidth() == 0 || getSolidHeight() == 0)) {
            sb.append(str);
            sb.append("\tBackground: ");
            sb.append(String.format(Locale.US, "%dx%d %X\n", new Object[]{Integer.valueOf(getSolidWidth()), Integer.valueOf(getSolidHeight()), Integer.valueOf(getSolidColor())}));
        }
        if (!this.shapes.isEmpty()) {
            sb.append(str);
            sb.append("\tShapes:\n");
            for (Object next : this.shapes) {
                sb.append(str);
                sb.append("\t\t");
                sb.append(next);
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        }
        return sb.toString();
    }
}
