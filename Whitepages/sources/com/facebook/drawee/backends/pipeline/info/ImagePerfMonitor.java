package com.facebook.drawee.backends.pipeline.info;

import android.graphics.Rect;
import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import com.facebook.common.time.MonotonicClock;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.backends.pipeline.info.internal.ImagePerfRequestListener;
import com.facebook.drawee.backends.pipeline.info.internal.ImagePerfStateManager;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.fresco.ui.common.ImageLoadStatus;
import com.facebook.fresco.ui.common.ImagePerfDataListener;
import com.facebook.fresco.ui.common.ImagePerfNotifier;
import com.facebook.fresco.ui.common.ImagePerfState;
import com.facebook.fresco.ui.common.ImageRenderingInfra;
import com.facebook.fresco.ui.common.VisibilityState;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ImagePerfMonitor implements ImagePerfNotifier {
    private boolean mEnabled;
    private ForwardingRequestListener mForwardingRequestListener;
    private List mImagePerfDataListeners;
    private ImagePerfRequestListener mImagePerfRequestListener;
    private final ImagePerfState mImagePerfState = new ImagePerfState(ImageRenderingInfra.DRAWEE);
    private ImagePerfStateManager mImagePerfStateManager;
    private final MonotonicClock mMonotonicClock;
    private final PipelineDraweeController mPipelineDraweeController;

    public ImagePerfMonitor(MonotonicClock monotonicClock, PipelineDraweeController pipelineDraweeController) {
        this.mMonotonicClock = monotonicClock;
        this.mPipelineDraweeController = pipelineDraweeController;
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
        if (z) {
            setupListeners();
            ImagePerfStateManager imagePerfStateManager = this.mImagePerfStateManager;
            if (imagePerfStateManager != null) {
                this.mPipelineDraweeController.addControllerListener2(imagePerfStateManager);
            }
            ForwardingRequestListener forwardingRequestListener = this.mForwardingRequestListener;
            if (forwardingRequestListener != null) {
                this.mPipelineDraweeController.addRequestListener(forwardingRequestListener);
                return;
            }
            return;
        }
        ImagePerfStateManager imagePerfStateManager2 = this.mImagePerfStateManager;
        if (imagePerfStateManager2 != null) {
            this.mPipelineDraweeController.removeControllerListener2(imagePerfStateManager2);
        }
        ForwardingRequestListener forwardingRequestListener2 = this.mForwardingRequestListener;
        if (forwardingRequestListener2 != null) {
            this.mPipelineDraweeController.removeRequestListener(forwardingRequestListener2);
        }
    }

    public void addImagePerfDataListener(ImagePerfDataListener imagePerfDataListener) {
        if (imagePerfDataListener != null) {
            if (this.mImagePerfDataListeners == null) {
                this.mImagePerfDataListeners = new CopyOnWriteArrayList();
            }
            this.mImagePerfDataListeners.add(imagePerfDataListener);
        }
    }

    public void clearImagePerfDataListeners() {
        List list = this.mImagePerfDataListeners;
        if (list != null) {
            list.clear();
        }
    }

    public void notifyStatusUpdated(ImagePerfState imagePerfState, ImageLoadStatus imageLoadStatus) {
        List list;
        imagePerfState.setImageLoadStatus(imageLoadStatus);
        if (this.mEnabled && (list = this.mImagePerfDataListeners) != null && !list.isEmpty()) {
            if (imageLoadStatus == ImageLoadStatus.SUCCESS) {
                addViewportData();
            }
            imagePerfState.snapshot();
            Iterator it = this.mImagePerfDataListeners.iterator();
            if (it.hasNext()) {
                MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
                throw null;
            }
        }
    }

    public void notifyVisibilityUpdated(ImagePerfState imagePerfState, VisibilityState visibilityState) {
        List list;
        if (this.mEnabled && (list = this.mImagePerfDataListeners) != null && !list.isEmpty()) {
            imagePerfState.snapshot();
            Iterator it = this.mImagePerfDataListeners.iterator();
            if (it.hasNext()) {
                MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
                throw null;
            }
        }
    }

    public void addViewportData() {
        DraweeHierarchy hierarchy = this.mPipelineDraweeController.getHierarchy();
        if (hierarchy != null && hierarchy.getTopLevelDrawable() != null) {
            Rect bounds = hierarchy.getTopLevelDrawable().getBounds();
            this.mImagePerfState.setOnScreenWidth(bounds.width());
            this.mImagePerfState.setOnScreenHeight(bounds.height());
        }
    }

    private void setupListeners() {
        if (this.mImagePerfStateManager == null) {
            this.mImagePerfStateManager = new ImagePerfStateManager(this.mMonotonicClock, this.mImagePerfState, this);
        }
        if (this.mImagePerfRequestListener == null) {
            this.mImagePerfRequestListener = new ImagePerfRequestListener(this.mMonotonicClock, this.mImagePerfState);
        }
        if (this.mForwardingRequestListener == null) {
            this.mForwardingRequestListener = new ForwardingRequestListener(this.mImagePerfRequestListener);
        }
    }

    public void reset() {
        clearImagePerfDataListeners();
        setEnabled(false);
        this.mImagePerfState.reset();
    }
}
