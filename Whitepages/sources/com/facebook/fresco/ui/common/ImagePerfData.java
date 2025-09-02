package com.facebook.fresco.ui.common;

import com.facebook.fresco.ui.common.ControllerListener2;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ImagePerfData {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Object callerContext;
    private final String callingClassNameOnVisible;
    private final String contentIdOnVisible;
    private final String[] contextChainArrayOnVisible;
    private final String contextChainExtrasOnVisible;
    private final long controllerFailureTimeMs;
    private final long controllerFinalImageSetTimeMs;
    private final String controllerId;
    private final long controllerSubmitTimeMs;
    private final Integer densityDpiOnSuccess;
    private final Long emptyEventTimestampNs;
    private final Integer errorCodeOnFailure;
    private final String errorMessageOnFailure;
    private final String errorStacktraceStringOnFailure;
    private final Throwable errorThrowable;
    private final ControllerListener2.Extras extraData;
    private final Object imageInfo;
    private final Object imageRequest;
    private final long imageRequestEndTimeMs;
    private final long imageRequestStartTimeMs;
    private final ImageRenderingInfra infra;
    private final int instanceId;
    private final long intermediateImageLoadTimeMs;
    private final List intermediateImageSetTimes;
    private final long invisibilityEventTimeMs;
    private final boolean isPrefetch;
    private final Long msSinceLastNavigationOnVisible;
    private final boolean newIntermediateImageSetPointAvailable;
    private final int onScreenHeightPx;
    private final int onScreenWidthPx;
    private final Long releasedEventTimestampNs;
    private final String requestId;
    private final String rootContextNameOnVisible;
    private final String startupStatusOnVisible;
    private final String subSurfaceOnVisible;
    private final String surfaceOnVisible;
    private final long visibilityEventTimeMs;
    private final VisibilityState visibilityState;

    public ImagePerfData(ImageRenderingInfra imageRenderingInfra, String str, String str2, Object obj, Object obj2, Object obj3, long j, long j2, long j3, long j4, long j5, long j6, Long l, Long l2, boolean z, int i, int i2, Throwable th, VisibilityState visibilityState2, long j7, long j8, DimensionsInfo dimensionsInfo, ControllerListener2.Extras extras, String str3, String str4, String[] strArr, String str5, String str6, String str7, String str8, Long l3, String str9, List list, boolean z2, String str10, String str11, Integer num, Integer num2) {
        String str12 = str;
        VisibilityState visibilityState3 = visibilityState2;
        List list2 = list;
        Intrinsics.checkNotNullParameter(imageRenderingInfra, "infra");
        Intrinsics.checkNotNullParameter(visibilityState3, "visibilityState");
        Intrinsics.checkNotNullParameter(list2, "intermediateImageSetTimes");
        this.infra = imageRenderingInfra;
        this.controllerId = str12;
        this.requestId = str2;
        this.imageRequest = obj;
        this.callerContext = obj2;
        this.imageInfo = obj3;
        this.controllerSubmitTimeMs = j;
        this.intermediateImageLoadTimeMs = j2;
        this.controllerFinalImageSetTimeMs = j3;
        this.controllerFailureTimeMs = j4;
        this.imageRequestStartTimeMs = j5;
        this.imageRequestEndTimeMs = j6;
        this.emptyEventTimestampNs = l;
        this.releasedEventTimestampNs = l2;
        this.isPrefetch = z;
        this.onScreenWidthPx = i;
        this.onScreenHeightPx = i2;
        this.errorThrowable = th;
        this.visibilityState = visibilityState3;
        this.visibilityEventTimeMs = j7;
        this.invisibilityEventTimeMs = j8;
        this.extraData = extras;
        this.callingClassNameOnVisible = str3;
        this.rootContextNameOnVisible = str4;
        this.contextChainArrayOnVisible = strArr;
        this.contextChainExtrasOnVisible = str5;
        this.contentIdOnVisible = str6;
        this.surfaceOnVisible = str7;
        this.subSurfaceOnVisible = str8;
        this.msSinceLastNavigationOnVisible = l3;
        this.startupStatusOnVisible = str9;
        this.intermediateImageSetTimes = list2;
        this.newIntermediateImageSetPointAvailable = z2;
        this.errorMessageOnFailure = str10;
        this.errorStacktraceStringOnFailure = str11;
        this.errorCodeOnFailure = num;
        this.densityDpiOnSuccess = num2;
        this.instanceId = str12 != null ? str.hashCode() : 0;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
