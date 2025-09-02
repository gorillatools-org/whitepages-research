package com.facebook.imagepipeline.request;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.core.NativeCodeSetup;
import com.facebook.imagepipeline.nativecode.Bitmaps;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BasePostprocessor implements Postprocessor {
    public static final Bitmap.Config FALLBACK_BITMAP_CONFIGURATION = Bitmap.Config.ARGB_8888;
    private static Method sCopyBitmap;

    public CacheKey getPostprocessorCacheKey() {
        return null;
    }

    public void process(Bitmap bitmap) {
    }

    public String getName() {
        return "Unknown postprocessor";
    }

    public CloseableReference process(Bitmap bitmap, PlatformBitmapFactory platformBitmapFactory) {
        Bitmap.Config config = bitmap.getConfig();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (config == null) {
            config = FALLBACK_BITMAP_CONFIGURATION;
        }
        CloseableReference createBitmapInternal = platformBitmapFactory.createBitmapInternal(width, height, config);
        try {
            process((Bitmap) createBitmapInternal.get(), bitmap);
            return createBitmapInternal.clone();
        } finally {
            CloseableReference.closeSafely(createBitmapInternal);
        }
    }

    public void process(Bitmap bitmap, Bitmap bitmap2) {
        internalCopyBitmap(bitmap, bitmap2);
        process(bitmap);
    }

    private static void internalCopyBitmap(Bitmap bitmap, Bitmap bitmap2) {
        Class<Bitmap> cls = Bitmap.class;
        if (!NativeCodeSetup.getUseNativeCode() || bitmap.getConfig() != bitmap2.getConfig()) {
            new Canvas(bitmap).drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) null);
            return;
        }
        try {
            if (sCopyBitmap == null) {
                int i = Bitmaps.$r8$clinit;
                sCopyBitmap = Bitmaps.class.getDeclaredMethod("copyBitmap", new Class[]{cls, cls});
            }
            sCopyBitmap.invoke((Object) null, new Object[]{bitmap, bitmap2});
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Wrong Native code setup, reflection failed.", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Wrong Native code setup, reflection failed.", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("Wrong Native code setup, reflection failed.", e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException("Wrong Native code setup, reflection failed.", e4);
        }
    }
}
