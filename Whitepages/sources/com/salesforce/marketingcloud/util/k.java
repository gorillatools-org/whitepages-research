package com.salesforce.marketingcloud.util;

import android.annotation.SuppressLint;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@SuppressLint({"UnknownNullness"})
public class k implements Closeable {
    private static final byte f = 13;
    private static final byte g = 10;
    private final InputStream a;
    final Charset b;
    private byte[] c;
    private int d;
    private int e;

    class a extends ByteArrayOutputStream {
        a(int i) {
            super(i);
        }

        public String toString() {
            int i = this.count;
            if (i > 0) {
                int i2 = i - 1;
                if (this.buf[i2] == 13) {
                    i = i2;
                }
            }
            return new String(this.buf, 0, i, k.this.b);
        }
    }

    public k(InputStream inputStream) {
        this(inputStream, (int) UserMetadata.MAX_INTERNAL_KEY_SIZE);
    }

    private void a() throws IOException {
        InputStream inputStream = this.a;
        byte[] bArr = this.c;
        int read = inputStream.read(bArr, 0, bArr.length);
        if (read != -1) {
            this.d = 0;
            this.e = read;
            return;
        }
        throw new EOFException();
    }

    public boolean b() {
        return this.e == -1;
    }

    public int c() throws IOException {
        String d2 = d();
        try {
            return Integer.parseInt(d2);
        } catch (NumberFormatException unused) {
            throw new IOException("expected an int but was \"" + d2 + "\"");
        }
    }

    public void close() throws IOException {
        synchronized (this.a) {
            try {
                if (this.c != null) {
                    this.c = null;
                    this.a.close();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public String d() throws IOException {
        int i;
        byte[] bArr;
        int i2;
        synchronized (this.a) {
            try {
                if (this.c != null) {
                    if (this.d >= this.e) {
                        a();
                    }
                    for (int i3 = this.d; i3 != this.e; i3++) {
                        byte[] bArr2 = this.c;
                        if (bArr2[i3] == 10) {
                            int i4 = this.d;
                            if (i3 != i4) {
                                i2 = i3 - 1;
                                if (bArr2[i2] == 13) {
                                    String str = new String(bArr2, i4, i2 - i4, this.b);
                                    this.d = i3 + 1;
                                    return str;
                                }
                            }
                            i2 = i3;
                            String str2 = new String(bArr2, i4, i2 - i4, this.b);
                            this.d = i3 + 1;
                            return str2;
                        }
                    }
                    a aVar = new a((this.e - this.d) + 80);
                    loop1:
                    while (true) {
                        byte[] bArr3 = this.c;
                        int i5 = this.d;
                        aVar.write(bArr3, i5, this.e - i5);
                        this.e = -1;
                        a();
                        i = this.d;
                        while (true) {
                            if (i != this.e) {
                                bArr = this.c;
                                if (bArr[i] == 10) {
                                    break loop1;
                                }
                                i++;
                            }
                        }
                    }
                    int i6 = this.d;
                    if (i != i6) {
                        aVar.write(bArr, i6, i - i6);
                    }
                    this.d = i + 1;
                    String byteArrayOutputStream = aVar.toString();
                    return byteArrayOutputStream;
                }
                throw new IOException("LineReader is closed");
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public k(InputStream inputStream, int i) {
        this(inputStream, i, g.a);
    }

    public k(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null) {
            throw new NullPointerException("in == null");
        } else if (charset == null) {
            throw new NullPointerException("charset == null");
        } else if (i < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        } else if (charset.equals(g.a) || charset.equals(g.c) || charset.equals(g.b)) {
            this.a = inputStream;
            this.b = charset;
            this.c = new byte[i];
        } else {
            throw new IllegalArgumentException("Unsupported encoding");
        }
    }

    public k(InputStream inputStream, Charset charset) {
        this(inputStream, UserMetadata.MAX_INTERNAL_KEY_SIZE, charset);
    }
}
