package com.facebook.react.views.image;

import android.graphics.drawable.Animatable;
import android.view.View;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.image.ImageLoadEvent;
import com.facebook.react.views.imagehelper.ImageSource;
import kotlin.jvm.internal.Intrinsics;

public final class ReactImageView$setShouldNotifyLoadEvents$1 extends ReactImageDownloadListener<ImageInfo> {
    final /* synthetic */ EventDispatcher $eventDispatcher;
    final /* synthetic */ ReactImageView this$0;

    ReactImageView$setShouldNotifyLoadEvents$1(EventDispatcher eventDispatcher, ReactImageView reactImageView) {
        this.$eventDispatcher = eventDispatcher;
        this.this$0 = reactImageView;
    }

    public void onProgressChange(int i, int i2) {
        if (this.$eventDispatcher != null && this.this$0.getImageSource$ReactAndroid_release() != null) {
            EventDispatcher eventDispatcher = this.$eventDispatcher;
            ImageLoadEvent.Companion companion = ImageLoadEvent.Companion;
            int surfaceId = UIManagerHelper.getSurfaceId((View) this.this$0);
            int id = this.this$0.getId();
            ImageSource imageSource$ReactAndroid_release = this.this$0.getImageSource$ReactAndroid_release();
            eventDispatcher.dispatchEvent(companion.createProgressEvent(surfaceId, id, imageSource$ReactAndroid_release != null ? imageSource$ReactAndroid_release.getSource() : null, i, i2));
        }
    }

    public void onSubmit(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, "id");
        EventDispatcher eventDispatcher = this.$eventDispatcher;
        if (eventDispatcher != null) {
            eventDispatcher.dispatchEvent(ImageLoadEvent.Companion.createLoadStartEvent(UIManagerHelper.getSurfaceId((View) this.this$0), this.this$0.getId()));
        }
    }

    public void onFinalImageSet(String str, ImageInfo imageInfo, Animatable animatable) {
        EventDispatcher eventDispatcher;
        Intrinsics.checkNotNullParameter(str, "id");
        if (imageInfo != null && this.this$0.getImageSource$ReactAndroid_release() != null && (eventDispatcher = this.$eventDispatcher) != null) {
            ImageLoadEvent.Companion companion = ImageLoadEvent.Companion;
            int surfaceId = UIManagerHelper.getSurfaceId((View) this.this$0);
            int id = this.this$0.getId();
            ImageSource imageSource$ReactAndroid_release = this.this$0.getImageSource$ReactAndroid_release();
            eventDispatcher.dispatchEvent(companion.createLoadEvent(surfaceId, id, imageSource$ReactAndroid_release != null ? imageSource$ReactAndroid_release.getSource() : null, imageInfo.getWidth(), imageInfo.getHeight()));
            this.$eventDispatcher.dispatchEvent(companion.createLoadEndEvent(UIManagerHelper.getSurfaceId((View) this.this$0), this.this$0.getId()));
        }
    }

    public void onFailure(String str, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(th, "throwable");
        EventDispatcher eventDispatcher = this.$eventDispatcher;
        if (eventDispatcher != null) {
            eventDispatcher.dispatchEvent(ImageLoadEvent.Companion.createErrorEvent(UIManagerHelper.getSurfaceId((View) this.this$0), this.this$0.getId(), th));
        }
    }
}
