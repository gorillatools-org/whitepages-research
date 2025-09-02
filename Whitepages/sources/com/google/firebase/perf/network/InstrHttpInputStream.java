package com.google.firebase.perf.network;

import com.google.firebase.perf.metrics.NetworkRequestMetricBuilder;
import com.google.firebase.perf.util.Timer;
import java.io.IOException;
import java.io.InputStream;

public final class InstrHttpInputStream extends InputStream {
    private long bytesRead = -1;
    private final InputStream inputStream;
    private final NetworkRequestMetricBuilder networkMetricBuilder;
    private long timeToResponseInitiated;
    private long timeToResponseLastRead = -1;
    private final Timer timer;

    public InstrHttpInputStream(InputStream inputStream2, NetworkRequestMetricBuilder networkRequestMetricBuilder, Timer timer2) {
        this.timer = timer2;
        this.inputStream = inputStream2;
        this.networkMetricBuilder = networkRequestMetricBuilder;
        this.timeToResponseInitiated = networkRequestMetricBuilder.getTimeToResponseInitiatedMicros();
    }

    public int available() throws IOException {
        try {
            return this.inputStream.available();
        } catch (IOException e) {
            this.networkMetricBuilder.setTimeToResponseCompletedMicros(this.timer.getDurationMicros());
            NetworkRequestMetricBuilderUtil.logError(this.networkMetricBuilder);
            throw e;
        }
    }

    public void close() throws IOException {
        long durationMicros = this.timer.getDurationMicros();
        if (this.timeToResponseLastRead == -1) {
            this.timeToResponseLastRead = durationMicros;
        }
        try {
            this.inputStream.close();
            long j = this.bytesRead;
            if (j != -1) {
                this.networkMetricBuilder.setResponsePayloadBytes(j);
            }
            long j2 = this.timeToResponseInitiated;
            if (j2 != -1) {
                this.networkMetricBuilder.setTimeToResponseInitiatedMicros(j2);
            }
            this.networkMetricBuilder.setTimeToResponseCompletedMicros(this.timeToResponseLastRead);
            this.networkMetricBuilder.build();
        } catch (IOException e) {
            this.networkMetricBuilder.setTimeToResponseCompletedMicros(this.timer.getDurationMicros());
            NetworkRequestMetricBuilderUtil.logError(this.networkMetricBuilder);
            throw e;
        }
    }

    public void mark(int i) {
        this.inputStream.mark(i);
    }

    public boolean markSupported() {
        return this.inputStream.markSupported();
    }

    public int read() throws IOException {
        try {
            int read = this.inputStream.read();
            long durationMicros = this.timer.getDurationMicros();
            if (this.timeToResponseInitiated == -1) {
                this.timeToResponseInitiated = durationMicros;
            }
            if (read == -1 && this.timeToResponseLastRead == -1) {
                this.timeToResponseLastRead = durationMicros;
                this.networkMetricBuilder.setTimeToResponseCompletedMicros(durationMicros);
                this.networkMetricBuilder.build();
            } else {
                incrementBytesRead(1);
                this.networkMetricBuilder.setResponsePayloadBytes(this.bytesRead);
            }
            return read;
        } catch (IOException e) {
            this.networkMetricBuilder.setTimeToResponseCompletedMicros(this.timer.getDurationMicros());
            NetworkRequestMetricBuilderUtil.logError(this.networkMetricBuilder);
            throw e;
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        try {
            int read = this.inputStream.read(bArr, i, i2);
            long durationMicros = this.timer.getDurationMicros();
            if (this.timeToResponseInitiated == -1) {
                this.timeToResponseInitiated = durationMicros;
            }
            if (read == -1 && this.timeToResponseLastRead == -1) {
                this.timeToResponseLastRead = durationMicros;
                this.networkMetricBuilder.setTimeToResponseCompletedMicros(durationMicros);
                this.networkMetricBuilder.build();
            } else {
                incrementBytesRead((long) read);
                this.networkMetricBuilder.setResponsePayloadBytes(this.bytesRead);
            }
            return read;
        } catch (IOException e) {
            this.networkMetricBuilder.setTimeToResponseCompletedMicros(this.timer.getDurationMicros());
            NetworkRequestMetricBuilderUtil.logError(this.networkMetricBuilder);
            throw e;
        }
    }

    public int read(byte[] bArr) throws IOException {
        try {
            int read = this.inputStream.read(bArr);
            long durationMicros = this.timer.getDurationMicros();
            if (this.timeToResponseInitiated == -1) {
                this.timeToResponseInitiated = durationMicros;
            }
            if (read == -1 && this.timeToResponseLastRead == -1) {
                this.timeToResponseLastRead = durationMicros;
                this.networkMetricBuilder.setTimeToResponseCompletedMicros(durationMicros);
                this.networkMetricBuilder.build();
            } else {
                incrementBytesRead((long) read);
                this.networkMetricBuilder.setResponsePayloadBytes(this.bytesRead);
            }
            return read;
        } catch (IOException e) {
            this.networkMetricBuilder.setTimeToResponseCompletedMicros(this.timer.getDurationMicros());
            NetworkRequestMetricBuilderUtil.logError(this.networkMetricBuilder);
            throw e;
        }
    }

    public void reset() throws IOException {
        try {
            this.inputStream.reset();
        } catch (IOException e) {
            this.networkMetricBuilder.setTimeToResponseCompletedMicros(this.timer.getDurationMicros());
            NetworkRequestMetricBuilderUtil.logError(this.networkMetricBuilder);
            throw e;
        }
    }

    public long skip(long j) throws IOException {
        try {
            long skip = this.inputStream.skip(j);
            long durationMicros = this.timer.getDurationMicros();
            if (this.timeToResponseInitiated == -1) {
                this.timeToResponseInitiated = durationMicros;
            }
            if (skip == 0 && j != 0 && this.timeToResponseLastRead == -1) {
                this.timeToResponseLastRead = durationMicros;
                this.networkMetricBuilder.setTimeToResponseCompletedMicros(durationMicros);
            } else {
                incrementBytesRead(skip);
                this.networkMetricBuilder.setResponsePayloadBytes(this.bytesRead);
            }
            return skip;
        } catch (IOException e) {
            this.networkMetricBuilder.setTimeToResponseCompletedMicros(this.timer.getDurationMicros());
            NetworkRequestMetricBuilderUtil.logError(this.networkMetricBuilder);
            throw e;
        }
    }

    private void incrementBytesRead(long j) {
        long j2 = this.bytesRead;
        if (j2 == -1) {
            this.bytesRead = j;
        } else {
            this.bytesRead = j2 + j;
        }
    }
}
