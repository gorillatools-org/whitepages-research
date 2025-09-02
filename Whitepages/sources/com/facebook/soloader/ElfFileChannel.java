package com.facebook.soloader;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ElfFileChannel implements ElfByteChannel {
    private FileChannel mFc;
    private File mFile;
    private FileInputStream mIs;

    public ElfFileChannel(File file) {
        this.mFile = file;
        openChannel();
    }

    public void openChannel() {
        FileInputStream fileInputStream = new FileInputStream(this.mFile);
        this.mIs = fileInputStream;
        this.mFc = fileInputStream.getChannel();
    }

    public int read(ByteBuffer byteBuffer) {
        return this.mFc.read(byteBuffer);
    }

    public int read(ByteBuffer byteBuffer, long j) {
        return this.mFc.read(byteBuffer, j);
    }

    public int write(ByteBuffer byteBuffer) {
        return this.mFc.write(byteBuffer);
    }

    public void close() {
        this.mIs.close();
    }

    public boolean isOpen() {
        return this.mFc.isOpen();
    }
}
