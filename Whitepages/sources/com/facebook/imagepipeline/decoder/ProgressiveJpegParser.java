package com.facebook.imagepipeline.decoder;

import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteArrayBufferedInputStream;
import com.facebook.common.util.StreamUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.internal.http2.Http2;

public class ProgressiveJpegParser {
    private int mBestScanEndOffset = 0;
    private int mBestScanNumber = 0;
    private final ByteArrayPool mByteArrayPool;
    private int mBytesParsed = 0;
    private boolean mEndMarkerRead;
    private int mLastByteRead = 0;
    private int mNextFullScanNumber = 0;
    private int mParserState = 0;

    private static boolean doesMarkerStartSegment(int i) {
        if (i == 1) {
            return false;
        }
        return ((i >= 208 && i <= 215) || i == 217 || i == 216) ? false : true;
    }

    public ProgressiveJpegParser(ByteArrayPool byteArrayPool) {
        this.mByteArrayPool = (ByteArrayPool) Preconditions.checkNotNull(byteArrayPool);
    }

    public boolean parseMoreData(EncodedImage encodedImage) {
        if (this.mParserState == 6 || encodedImage.getSize() <= this.mBytesParsed) {
            return false;
        }
        PooledByteArrayBufferedInputStream pooledByteArrayBufferedInputStream = new PooledByteArrayBufferedInputStream(encodedImage.getInputStreamOrThrow(), (byte[]) this.mByteArrayPool.get(Http2.INITIAL_MAX_FRAME_SIZE), this.mByteArrayPool);
        try {
            StreamUtil.skip(pooledByteArrayBufferedInputStream, (long) this.mBytesParsed);
            return doParseMoreData(pooledByteArrayBufferedInputStream);
        } catch (IOException e) {
            Throwables.propagate(e);
            return false;
        } finally {
            Closeables.closeQuietly(pooledByteArrayBufferedInputStream);
        }
    }

    private boolean doParseMoreData(InputStream inputStream) {
        int read;
        int i = this.mBestScanNumber;
        while (this.mParserState != 6 && (read = inputStream.read()) != -1) {
            try {
                int i2 = this.mBytesParsed;
                this.mBytesParsed = i2 + 1;
                if (this.mEndMarkerRead) {
                    this.mParserState = 6;
                    this.mEndMarkerRead = false;
                    return false;
                }
                int i3 = this.mParserState;
                if (i3 != 0) {
                    if (i3 != 1) {
                        if (i3 != 2) {
                            if (i3 != 3) {
                                if (i3 == 4) {
                                    this.mParserState = 5;
                                } else if (i3 != 5) {
                                    Preconditions.checkState(false);
                                } else {
                                    int i4 = ((this.mLastByteRead << 8) + read) - 2;
                                    StreamUtil.skip(inputStream, (long) i4);
                                    this.mBytesParsed += i4;
                                    this.mParserState = 2;
                                }
                            } else if (read == 255) {
                                this.mParserState = 3;
                            } else if (read == 0) {
                                this.mParserState = 2;
                            } else if (read == 217) {
                                this.mEndMarkerRead = true;
                                newScanOrImageEndFound(i2 - 1);
                                this.mParserState = 2;
                            } else {
                                if (read == 218) {
                                    newScanOrImageEndFound(i2 - 1);
                                }
                                if (doesMarkerStartSegment(read)) {
                                    this.mParserState = 4;
                                } else {
                                    this.mParserState = 2;
                                }
                            }
                        } else if (read == 255) {
                            this.mParserState = 3;
                        }
                    } else if (read == 216) {
                        this.mParserState = 2;
                    } else {
                        this.mParserState = 6;
                    }
                } else if (read == 255) {
                    this.mParserState = 1;
                } else {
                    this.mParserState = 6;
                }
                this.mLastByteRead = read;
            } catch (IOException e) {
                Throwables.propagate(e);
            }
        }
        if (this.mParserState == 6 || this.mBestScanNumber == i) {
            return false;
        }
        return true;
    }

    private void newScanOrImageEndFound(int i) {
        int i2 = this.mNextFullScanNumber;
        if (i2 > 0) {
            this.mBestScanEndOffset = i;
        }
        this.mNextFullScanNumber = i2 + 1;
        this.mBestScanNumber = i2;
    }

    public int getBestScanEndOffset() {
        return this.mBestScanEndOffset;
    }

    public int getBestScanNumber() {
        return this.mBestScanNumber;
    }

    public boolean isEndMarkerRead() {
        return this.mEndMarkerRead;
    }
}
