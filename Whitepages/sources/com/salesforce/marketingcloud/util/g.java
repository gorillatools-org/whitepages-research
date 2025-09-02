package com.salesforce.marketingcloud.util;

import android.annotation.SuppressLint;
import com.salesforce.marketingcloud.b;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;

@SuppressLint({"UnknownNullness"})
public final class g {
    public static final Charset a = Charset.forName("US-ASCII");
    public static final Charset b = Charset.forName("ISO-8859-1");
    public static final Charset c = Charset.forName("UTF-8");

    private g() {
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void b(File file) {
        try {
            file.delete();
        } catch (Exception unused) {
        }
    }

    public static void a(File file, OutputStream outputStream) throws IOException {
        a((InputStream) new FileInputStream(file), outputStream);
    }

    public static void a(InputStream inputStream, File file) throws IOException {
        a(inputStream, (OutputStream) new FileOutputStream(file));
    }

    public static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        try {
            byte[] bArr = new byte[b.v];
            while (true) {
                int read = inputStream.read(bArr);
                if (-1 != read) {
                    outputStream.write(bArr, 0, read);
                } else {
                    outputStream.flush();
                    a((Closeable) inputStream);
                    a((Closeable) outputStream);
                    return;
                }
            }
        } catch (Throwable th) {
            a((Closeable) inputStream);
            a((Closeable) outputStream);
            throw th;
        }
    }

    public static void a(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            int length = listFiles.length;
            int i = 0;
            while (i < length) {
                File file2 = listFiles[i];
                if (file2.isDirectory()) {
                    a(file2);
                }
                if (file2.delete()) {
                    i++;
                } else {
                    throw new IOException("failed to delete file: " + file2);
                }
            }
            return;
        }
        throw new IOException("not a readable directory: " + file);
    }

    public static String a(Reader reader) throws IOException {
        try {
            StringWriter stringWriter = new StringWriter();
            char[] cArr = new char[1024];
            while (true) {
                int read = reader.read(cArr);
                if (read != -1) {
                    stringWriter.write(cArr, 0, read);
                } else {
                    String stringWriter2 = stringWriter.toString();
                    reader.close();
                    return stringWriter2;
                }
            }
        } catch (Throwable th) {
            reader.close();
            throw th;
        }
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(inputStream, (OutputStream) byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        a((Closeable) byteArrayOutputStream);
        return byteArray;
    }
}
