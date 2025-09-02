package com.facebook.appevents.ml;

import com.facebook.appevents.internal.FileDownloadTask;
import com.facebook.appevents.ml.ModelManager;
import java.io.File;
import java.util.List;

public final /* synthetic */ class ModelManager$TaskHandler$Companion$$ExternalSyntheticLambda0 implements FileDownloadTask.Callback {
    public final /* synthetic */ List f$0;

    public /* synthetic */ ModelManager$TaskHandler$Companion$$ExternalSyntheticLambda0(List list) {
        this.f$0 = list;
    }

    public final void onComplete(File file) {
        ModelManager.TaskHandler.Companion.execute$lambda$1(this.f$0, file);
    }
}
