package com.google.firebase.messaging;

import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class ImageDownload$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ImageDownload f$0;
    public final /* synthetic */ TaskCompletionSource f$1;

    public /* synthetic */ ImageDownload$$ExternalSyntheticLambda0(ImageDownload imageDownload, TaskCompletionSource taskCompletionSource) {
        this.f$0 = imageDownload;
        this.f$1 = taskCompletionSource;
    }

    public final void run() {
        this.f$0.lambda$start$0(this.f$1);
    }
}
