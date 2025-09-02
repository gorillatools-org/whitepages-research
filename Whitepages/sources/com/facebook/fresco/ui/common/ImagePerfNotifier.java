package com.facebook.fresco.ui.common;

public interface ImagePerfNotifier {
    void notifyStatusUpdated(ImagePerfState imagePerfState, ImageLoadStatus imageLoadStatus);

    void notifyVisibilityUpdated(ImagePerfState imagePerfState, VisibilityState visibilityState);
}
