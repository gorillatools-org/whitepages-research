package com.facebook.drawee.backends.pipeline.info.internal;

import com.facebook.common.time.MonotonicClock;
import com.facebook.drawee.drawable.VisibilityCallback;
import com.facebook.fresco.ui.common.BaseControllerListener2;
import com.facebook.fresco.ui.common.ControllerListener2;
import com.facebook.fresco.ui.common.ImageLoadStatus;
import com.facebook.fresco.ui.common.ImagePerfNotifier;
import com.facebook.fresco.ui.common.ImagePerfState;
import com.facebook.fresco.ui.common.VisibilityState;
import com.facebook.imagepipeline.image.ImageInfo;
import java.io.Closeable;

public class ImagePerfStateManager extends BaseControllerListener2 implements Closeable, VisibilityCallback {
    private final MonotonicClock mClock;
    private final ImagePerfNotifier mImagePerfNotifier;
    private final ImagePerfState mImagePerfState;
    private ImagePerfNotifier mLocalImagePerfNotifier;
    private final boolean mReportVisibleOnSubmitAndRelease;

    public void onDraw() {
    }

    public ImagePerfStateManager(MonotonicClock monotonicClock, ImagePerfState imagePerfState, ImagePerfNotifier imagePerfNotifier) {
        this(monotonicClock, imagePerfState, imagePerfNotifier, true);
    }

    public ImagePerfStateManager(MonotonicClock monotonicClock, ImagePerfState imagePerfState, ImagePerfNotifier imagePerfNotifier, boolean z) {
        this.mLocalImagePerfNotifier = null;
        this.mClock = monotonicClock;
        this.mImagePerfState = imagePerfState;
        this.mImagePerfNotifier = imagePerfNotifier;
        this.mReportVisibleOnSubmitAndRelease = z;
    }

    public void onSubmit(String str, Object obj, ControllerListener2.Extras extras) {
        long now = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.resetPointsTimestamps();
        imagePerfState.setControllerSubmitTimeMs(now);
        imagePerfState.setControllerId(str);
        imagePerfState.setCallerContext(obj);
        imagePerfState.setExtraData(extras);
        updateStatus(imagePerfState, ImageLoadStatus.REQUESTED);
        if (this.mReportVisibleOnSubmitAndRelease) {
            reportViewVisible(imagePerfState, now);
        }
    }

    public void onIntermediateImageSet(String str, ImageInfo imageInfo) {
        long now = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.setControllerIntermediateImageSetTimeMs(now);
        imagePerfState.setControllerId(str);
        imagePerfState.setImageInfo(imageInfo);
        updateStatus(imagePerfState, ImageLoadStatus.INTERMEDIATE_AVAILABLE);
    }

    public void onFinalImageSet(String str, ImageInfo imageInfo, ControllerListener2.Extras extras) {
        long now = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.setExtraData(extras);
        imagePerfState.setControllerFinalImageSetTimeMs(now);
        imagePerfState.setImageRequestEndTimeMs(now);
        imagePerfState.setControllerId(str);
        imagePerfState.setImageInfo(imageInfo);
        updateStatus(imagePerfState, ImageLoadStatus.SUCCESS);
    }

    public void onFailure(String str, Throwable th, ControllerListener2.Extras extras) {
        long now = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.setExtraData(extras);
        imagePerfState.setControllerFailureTimeMs(now);
        imagePerfState.setControllerId(str);
        imagePerfState.setErrorThrowable(th);
        updateStatus(imagePerfState, ImageLoadStatus.ERROR);
        reportViewInvisible(imagePerfState, now);
    }

    public void onRelease(String str, ControllerListener2.Extras extras) {
        long now = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.setExtraData(extras);
        imagePerfState.setControllerId(str);
        updateStatus(imagePerfState, ImageLoadStatus.RELEASED);
        if (this.mReportVisibleOnSubmitAndRelease) {
            reportViewInvisible(imagePerfState, now);
        }
    }

    public void reportViewVisible(ImagePerfState imagePerfState, long j) {
        imagePerfState.setVisible(true);
        imagePerfState.setVisibilityEventTimeMs(j);
        updateVisibility(imagePerfState, VisibilityState.VISIBLE);
    }

    public void resetState() {
        this.mImagePerfState.reset();
    }

    public void close() {
        resetState();
    }

    private void reportViewInvisible(ImagePerfState imagePerfState, long j) {
        imagePerfState.setVisible(false);
        imagePerfState.setInvisibilityEventTimeMs(j);
        updateVisibility(imagePerfState, VisibilityState.INVISIBLE);
    }

    private void updateStatus(ImagePerfState imagePerfState, ImageLoadStatus imageLoadStatus) {
        imagePerfState.setImageLoadStatus(imageLoadStatus);
        this.mImagePerfNotifier.notifyStatusUpdated(imagePerfState, imageLoadStatus);
        ImagePerfNotifier imagePerfNotifier = this.mLocalImagePerfNotifier;
        if (imagePerfNotifier != null) {
            imagePerfNotifier.notifyStatusUpdated(imagePerfState, imageLoadStatus);
        }
    }

    private void updateVisibility(ImagePerfState imagePerfState, VisibilityState visibilityState) {
        this.mImagePerfNotifier.notifyVisibilityUpdated(imagePerfState, visibilityState);
        ImagePerfNotifier imagePerfNotifier = this.mLocalImagePerfNotifier;
        if (imagePerfNotifier != null) {
            imagePerfNotifier.notifyVisibilityUpdated(imagePerfState, visibilityState);
        }
    }

    public void onVisibilityChange(boolean z) {
        if (z) {
            reportViewVisible(this.mImagePerfState, this.mClock.now());
        } else {
            reportViewInvisible(this.mImagePerfState, this.mClock.now());
        }
    }
}
