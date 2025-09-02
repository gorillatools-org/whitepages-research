package com.facebook.react.modules.blob;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.facebook.react.ReactApplication;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class BlobProvider extends ContentProvider {
    private static final int PIPE_CAPACITY = 65536;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
        if (str.equals("r")) {
            Context applicationContext = getContext().getApplicationContext();
            BlobModule blobModule = applicationContext instanceof ReactApplication ? (BlobModule) ((ReactApplication) applicationContext).getReactNativeHost().getReactInstanceManager().getCurrentReactContext().getNativeModule(BlobModule.class) : null;
            if (blobModule != null) {
                final byte[] resolve = blobModule.resolve(uri);
                if (resolve != null) {
                    try {
                        ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
                        ParcelFileDescriptor parcelFileDescriptor = createPipe[0];
                        final ParcelFileDescriptor parcelFileDescriptor2 = createPipe[1];
                        if (resolve.length <= PIPE_CAPACITY) {
                            try {
                                autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptor2);
                                autoCloseOutputStream.write(resolve);
                                autoCloseOutputStream.close();
                            } catch (IOException unused) {
                                return null;
                            } catch (Throwable th) {
                                th.addSuppressed(th);
                            }
                        } else {
                            this.executor.submit(new Runnable() {
                                public void run() {
                                    ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
                                    try {
                                        autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptor2);
                                        autoCloseOutputStream.write(resolve);
                                        autoCloseOutputStream.close();
                                        return;
                                    } catch (IOException unused) {
                                        return;
                                    } catch (Throwable th) {
                                        th.addSuppressed(th);
                                    }
                                    throw th;
                                }
                            });
                        }
                        return parcelFileDescriptor;
                    } catch (IOException unused2) {
                        return null;
                    }
                } else {
                    throw new FileNotFoundException("Cannot open " + uri + ", blob not found.");
                }
            } else {
                throw new RuntimeException("No blob module associated with BlobProvider");
            }
        } else {
            throw new FileNotFoundException("Cannot open " + uri.toString() + " in mode '" + str + "'");
        }
        throw th;
    }
}
