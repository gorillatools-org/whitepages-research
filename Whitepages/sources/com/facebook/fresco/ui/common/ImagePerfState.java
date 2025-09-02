package com.facebook.fresco.ui.common;

import com.facebook.fresco.ui.common.ControllerListener2;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class ImagePerfState extends ImagePerfLoggingState {
    private ControllerListener2.Extras _extraData;
    private Object callerContext;
    private long controllerFailureTimeMs = -1;
    private long controllerFinalImageSetTimeMs = -1;
    private String controllerId;
    private long controllerIntermediateImageSetTimeMs = -1;
    private long controllerSubmitTimeMs = -1;
    private Throwable errorThrowable;
    private Object imageInfo;
    private ImageLoadStatus imageLoadStatus = ImageLoadStatus.UNKNOWN;
    private Object imageRequest;
    private long imageRequestEndTimeMs = -1;
    private long imageRequestStartTimeMs = -1;
    private long invisibilityEventTimeMs = -1;
    private boolean isPrefetch;
    private int onScreenHeightPx = -1;
    private int onScreenWidthPx = -1;
    private String requestId;
    private long visibilityEventTimeMs = -1;
    private VisibilityState visibilityState = VisibilityState.UNKNOWN;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ImagePerfState(ImageRenderingInfra imageRenderingInfra) {
        super(imageRenderingInfra);
        Intrinsics.checkNotNullParameter(imageRenderingInfra, "infra");
    }

    public final void setCallerContext(Object obj) {
        this.callerContext = obj;
    }

    public final void setImageLoadStatus(ImageLoadStatus imageLoadStatus2) {
        Intrinsics.checkNotNullParameter(imageLoadStatus2, "<set-?>");
        this.imageLoadStatus = imageLoadStatus2;
    }

    public final void setVisibilityEventTimeMs(long j) {
        this.visibilityEventTimeMs = j;
    }

    public final void reset() {
        this.requestId = null;
        this.imageRequest = null;
        this.callerContext = null;
        this.imageInfo = null;
        this.isPrefetch = false;
        this.onScreenWidthPx = -1;
        this.onScreenHeightPx = -1;
        this.errorThrowable = null;
        this.imageLoadStatus = ImageLoadStatus.UNKNOWN;
        this.visibilityState = VisibilityState.UNKNOWN;
        this._extraData = null;
        resetPointsTimestamps();
        resetLoggingState$ui_common_release();
    }

    public final void resetPointsTimestamps() {
        this.imageRequestStartTimeMs = -1;
        this.imageRequestEndTimeMs = -1;
        this.controllerSubmitTimeMs = -1;
        this.controllerFinalImageSetTimeMs = -1;
        this.controllerFailureTimeMs = -1;
        this.visibilityEventTimeMs = -1;
        this.invisibilityEventTimeMs = -1;
        getIntermediateImageSetTimes().clear();
        setNewIntermediateImageSetPointAvailable(false);
        setEmptyEventTimestampNs((Long) null);
        setReleasedEventTimestampNs((Long) null);
    }

    public final void setControllerId(String str) {
        this.controllerId = str;
    }

    public final void setRequestId(String str) {
        this.requestId = str;
    }

    public final void setImageRequest(Object obj) {
        this.imageRequest = obj;
    }

    public final void setControllerSubmitTimeMs(long j) {
        this.controllerSubmitTimeMs = j;
    }

    public final void setControllerIntermediateImageSetTimeMs(long j) {
        this.controllerIntermediateImageSetTimeMs = j;
    }

    public final void setControllerFinalImageSetTimeMs(long j) {
        this.controllerFinalImageSetTimeMs = j;
    }

    public final void setControllerFailureTimeMs(long j) {
        this.controllerFailureTimeMs = j;
    }

    public final void setImageRequestStartTimeMs(long j) {
        this.imageRequestStartTimeMs = j;
    }

    public final void setImageRequestEndTimeMs(long j) {
        this.imageRequestEndTimeMs = j;
    }

    public final void setInvisibilityEventTimeMs(long j) {
        this.invisibilityEventTimeMs = j;
    }

    public final void setPrefetch(boolean z) {
        this.isPrefetch = z;
    }

    public final void setImageInfo(Object obj) {
        this.imageInfo = obj;
    }

    public final void setOnScreenWidth(int i) {
        this.onScreenWidthPx = i;
    }

    public final void setOnScreenHeight(int i) {
        this.onScreenHeightPx = i;
    }

    public final void setErrorThrowable(Throwable th) {
        this.errorThrowable = th;
    }

    public final void setVisible(boolean z) {
        this.visibilityState = z ? VisibilityState.VISIBLE : VisibilityState.INVISIBLE;
    }

    public final ImagePerfData snapshot() {
        return new ImagePerfData(getInfra(), this.controllerId, this.requestId, this.imageRequest, this.callerContext, this.imageInfo, this.controllerSubmitTimeMs, this.controllerIntermediateImageSetTimeMs, this.controllerFinalImageSetTimeMs, this.controllerFailureTimeMs, this.imageRequestStartTimeMs, this.imageRequestEndTimeMs, getEmptyEventTimestampNs(), getReleasedEventTimestampNs(), this.isPrefetch, this.onScreenWidthPx, this.onScreenHeightPx, this.errorThrowable, this.visibilityState, this.visibilityEventTimeMs, this.invisibilityEventTimeMs, (DimensionsInfo) null, this._extraData, getCallingClassNameOnVisible(), getRootContextNameOnVisible(), getContextChainArrayOnVisible(), getContextChainExtrasOnVisible(), getContentIdOnVisible(), getSurfaceOnVisible(), getSubSurfaceOnVisible(), getMsSinceLastNavigationOnVisible(), getStartupStatusOnVisible(), CollectionsKt.toList(getIntermediateImageSetTimes()), getNewIntermediateImageSetPointAvailable(), getErrorMessageOnFailure(), getErrorStacktraceStringOnFailure(), getErrorCodeOnFailure(), getDensityDpiOnSuccess());
    }

    public final void setExtraData(ControllerListener2.Extras extras) {
        this._extraData = extras;
    }
}
