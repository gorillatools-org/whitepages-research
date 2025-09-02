package com.facebook.fresco.ui.common;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public abstract class ImagePerfLoggingState {
    private String callingClassNameOnVisible;
    private String contentIdOnVisible;
    private String[] contextChainArrayOnVisible;
    private String contextChainExtrasOnVisible;
    private Integer densityDpiOnSuccess;
    private Long emptyEventTimestampNs;
    private Integer errorCodeOnFailure;
    private String errorMessageOnFailure;
    private String errorStacktraceStringOnFailure;
    private final ImageRenderingInfra infra;
    private final List intermediateImageSetTimes = new ArrayList();
    private Long msSinceLastNavigationOnVisible;
    private boolean newIntermediateImageSetPointAvailable;
    private Long releasedEventTimestampNs;
    private String rootContextNameOnVisible;
    private String startupStatusOnVisible;
    private String subSurfaceOnVisible;
    private String surfaceOnVisible;

    public ImagePerfLoggingState(ImageRenderingInfra imageRenderingInfra) {
        Intrinsics.checkNotNullParameter(imageRenderingInfra, "infra");
        this.infra = imageRenderingInfra;
    }

    public final ImageRenderingInfra getInfra() {
        return this.infra;
    }

    public final List getIntermediateImageSetTimes() {
        return this.intermediateImageSetTimes;
    }

    public final boolean getNewIntermediateImageSetPointAvailable() {
        return this.newIntermediateImageSetPointAvailable;
    }

    public final void setNewIntermediateImageSetPointAvailable(boolean z) {
        this.newIntermediateImageSetPointAvailable = z;
    }

    public final Long getEmptyEventTimestampNs() {
        return this.emptyEventTimestampNs;
    }

    public final void setEmptyEventTimestampNs(Long l) {
        this.emptyEventTimestampNs = l;
    }

    public final Long getReleasedEventTimestampNs() {
        return this.releasedEventTimestampNs;
    }

    public final void setReleasedEventTimestampNs(Long l) {
        this.releasedEventTimestampNs = l;
    }

    public final String getCallingClassNameOnVisible() {
        return this.callingClassNameOnVisible;
    }

    public final String getRootContextNameOnVisible() {
        return this.rootContextNameOnVisible;
    }

    public final String[] getContextChainArrayOnVisible() {
        return this.contextChainArrayOnVisible;
    }

    public final String getContextChainExtrasOnVisible() {
        return this.contextChainExtrasOnVisible;
    }

    public final String getContentIdOnVisible() {
        return this.contentIdOnVisible;
    }

    public final String getSurfaceOnVisible() {
        return this.surfaceOnVisible;
    }

    public final String getSubSurfaceOnVisible() {
        return this.subSurfaceOnVisible;
    }

    public final Long getMsSinceLastNavigationOnVisible() {
        return this.msSinceLastNavigationOnVisible;
    }

    public final String getStartupStatusOnVisible() {
        return this.startupStatusOnVisible;
    }

    public final String getErrorMessageOnFailure() {
        return this.errorMessageOnFailure;
    }

    public final String getErrorStacktraceStringOnFailure() {
        return this.errorStacktraceStringOnFailure;
    }

    public final Integer getErrorCodeOnFailure() {
        return this.errorCodeOnFailure;
    }

    public final Integer getDensityDpiOnSuccess() {
        return this.densityDpiOnSuccess;
    }

    public final void resetLoggingState$ui_common_release() {
        this.intermediateImageSetTimes.clear();
        this.newIntermediateImageSetPointAvailable = false;
        this.emptyEventTimestampNs = null;
        this.releasedEventTimestampNs = null;
        this.callingClassNameOnVisible = null;
        this.rootContextNameOnVisible = null;
        this.contextChainArrayOnVisible = null;
        this.contextChainExtrasOnVisible = null;
        this.contentIdOnVisible = null;
        this.surfaceOnVisible = null;
        this.subSurfaceOnVisible = null;
        this.msSinceLastNavigationOnVisible = null;
        this.startupStatusOnVisible = null;
        this.errorMessageOnFailure = null;
        this.errorStacktraceStringOnFailure = null;
        this.errorCodeOnFailure = null;
        this.densityDpiOnSuccess = null;
    }
}
