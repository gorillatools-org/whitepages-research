package android.support.v4.media.session;

import android.os.Bundle;
import android.os.ResultReceiver;
import java.lang.ref.WeakReference;

class MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver extends ResultReceiver {
    private WeakReference mMediaControllerImpl;

    /* access modifiers changed from: protected */
    public void onReceiveResult(int i, Bundle bundle) {
        MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(this.mMediaControllerImpl.get());
    }
}
