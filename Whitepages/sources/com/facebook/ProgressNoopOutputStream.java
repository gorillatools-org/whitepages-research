package com.facebook;

import android.os.Handler;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

public final class ProgressNoopOutputStream extends OutputStream implements RequestOutputStream {
    private final Handler callbackHandler;
    private GraphRequest currentRequest;
    private RequestProgress currentRequestProgress;
    private int maxProgress;
    private final Map progressMap = new HashMap();

    public ProgressNoopOutputStream(Handler handler) {
        this.callbackHandler = handler;
    }

    public final int getMaxProgress() {
        return this.maxProgress;
    }

    public void setCurrentRequest(GraphRequest graphRequest) {
        this.currentRequest = graphRequest;
        this.currentRequestProgress = graphRequest != null ? (RequestProgress) this.progressMap.get(graphRequest) : null;
    }

    public final Map getProgressMap() {
        return this.progressMap;
    }

    public final void addProgress(long j) {
        GraphRequest graphRequest = this.currentRequest;
        if (graphRequest != null) {
            if (this.currentRequestProgress == null) {
                RequestProgress requestProgress = new RequestProgress(this.callbackHandler, graphRequest);
                this.currentRequestProgress = requestProgress;
                this.progressMap.put(graphRequest, requestProgress);
            }
            RequestProgress requestProgress2 = this.currentRequestProgress;
            if (requestProgress2 != null) {
                requestProgress2.addToMax(j);
            }
            this.maxProgress += (int) j;
        }
    }

    public void write(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "buffer");
        addProgress((long) bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "buffer");
        addProgress((long) i2);
    }

    public void write(int i) {
        addProgress(1);
    }
}
