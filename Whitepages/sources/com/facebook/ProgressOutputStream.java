package com.facebook;

import com.facebook.GraphRequestBatch;
import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

public final class ProgressOutputStream extends FilterOutputStream implements RequestOutputStream {
    private long batchProgress;
    private RequestProgress currentRequestProgress;
    private long lastReportedProgress;
    private final long maxProgress;
    private final Map progressMap;
    private final GraphRequestBatch requests;
    private final long threshold = FacebookSdk.getOnProgressThreshold();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ProgressOutputStream(OutputStream outputStream, GraphRequestBatch graphRequestBatch, Map map, long j) {
        super(outputStream);
        Intrinsics.checkNotNullParameter(outputStream, "out");
        Intrinsics.checkNotNullParameter(graphRequestBatch, "requests");
        Intrinsics.checkNotNullParameter(map, "progressMap");
        this.requests = graphRequestBatch;
        this.progressMap = map;
        this.maxProgress = j;
    }

    private final void addProgress(long j) {
        RequestProgress requestProgress = this.currentRequestProgress;
        if (requestProgress != null) {
            requestProgress.addProgress(j);
        }
        long j2 = this.batchProgress + j;
        this.batchProgress = j2;
        if (j2 >= this.lastReportedProgress + this.threshold || j2 >= this.maxProgress) {
            reportBatchProgress();
        }
    }

    private final void reportBatchProgress() {
        if (this.batchProgress > this.lastReportedProgress) {
            for (GraphRequestBatch.Callback callback : this.requests.getCallbacks()) {
            }
            this.lastReportedProgress = this.batchProgress;
        }
    }

    public void setCurrentRequest(GraphRequest graphRequest) {
        this.currentRequestProgress = graphRequest != null ? (RequestProgress) this.progressMap.get(graphRequest) : null;
    }

    public void write(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "buffer");
        this.out.write(bArr);
        addProgress((long) bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "buffer");
        this.out.write(bArr, i, i2);
        addProgress((long) i2);
    }

    public void write(int i) {
        this.out.write(i);
        addProgress(1);
    }

    public void close() {
        super.close();
        for (RequestProgress reportProgress : this.progressMap.values()) {
            reportProgress.reportProgress();
        }
        reportBatchProgress();
    }
}
