package com.facebook.drawee.view;

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchyInflater;
import com.facebook.imagepipeline.systrace.FrescoSystrace;

public abstract class GenericDraweeView extends DraweeView {
    public GenericDraweeView(Context context, GenericDraweeHierarchy genericDraweeHierarchy) {
        super(context);
        setHierarchy(genericDraweeHierarchy);
    }

    /* access modifiers changed from: protected */
    public void inflateHierarchy(Context context, AttributeSet attributeSet) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("GenericDraweeView#inflateHierarchy");
        }
        GenericDraweeHierarchyBuilder inflateBuilder = GenericDraweeHierarchyInflater.inflateBuilder(context, attributeSet);
        setAspectRatio(inflateBuilder.getDesiredAspectRatio());
        setHierarchy(inflateBuilder.build());
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }
}
