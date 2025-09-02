package com.airbnb.lottie.manager;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import java.io.IOException;
import java.util.Map;

public class ImageAssetManager {
    private static final Object bitmapHashLock = new Object();
    private final Context context;
    private final Map imageAssets;
    private final String imagesFolder;

    public void setDelegate(ImageAssetDelegate imageAssetDelegate) {
    }

    public ImageAssetManager(Drawable.Callback callback, String str, ImageAssetDelegate imageAssetDelegate, Map map) {
        if (TextUtils.isEmpty(str) || str.charAt(str.length() - 1) == '/') {
            this.imagesFolder = str;
        } else {
            this.imagesFolder = str + '/';
        }
        this.imageAssets = map;
        setDelegate(imageAssetDelegate);
        if (!(callback instanceof View)) {
            this.context = null;
        } else {
            this.context = ((View) callback).getContext().getApplicationContext();
        }
    }

    public Bitmap bitmapForId(String str) {
        LottieImageAsset lottieImageAsset = (LottieImageAsset) this.imageAssets.get(str);
        if (lottieImageAsset == null) {
            return null;
        }
        Bitmap bitmap = lottieImageAsset.getBitmap();
        if (bitmap != null) {
            return bitmap;
        }
        Context context2 = this.context;
        if (context2 == null) {
            return null;
        }
        String fileName = lottieImageAsset.getFileName();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        options.inDensity = 160;
        if (!fileName.startsWith("data:") || fileName.indexOf("base64,") <= 0) {
            try {
                if (!TextUtils.isEmpty(this.imagesFolder)) {
                    AssetManager assets = context2.getAssets();
                    try {
                        Bitmap decodeStream = BitmapFactory.decodeStream(assets.open(this.imagesFolder + fileName), (Rect) null, options);
                        if (decodeStream != null) {
                            return putBitmap(str, Utils.resizeBitmapIfNeeded(decodeStream, lottieImageAsset.getWidth(), lottieImageAsset.getHeight()));
                        }
                        Logger.warning("Decoded image `" + str + "` is null.");
                        return null;
                    } catch (IllegalArgumentException e) {
                        Logger.warning("Unable to decode image `" + str + "`.", e);
                        return null;
                    }
                } else {
                    throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
                }
            } catch (IOException e2) {
                Logger.warning("Unable to open asset.", e2);
                return null;
            }
        } else {
            try {
                byte[] decode = Base64.decode(fileName.substring(fileName.indexOf(44) + 1), 0);
                return putBitmap(str, Utils.resizeBitmapIfNeeded(BitmapFactory.decodeByteArray(decode, 0, decode.length, options), lottieImageAsset.getWidth(), lottieImageAsset.getHeight()));
            } catch (IllegalArgumentException e3) {
                Logger.warning("data URL did not have correct base64 format.", e3);
                return null;
            }
        }
    }

    public boolean hasSameContext(Context context2) {
        if (context2 == null) {
            return this.context == null;
        }
        if (this.context instanceof Application) {
            context2 = context2.getApplicationContext();
        }
        if (context2 == this.context) {
            return true;
        }
        return false;
    }

    private Bitmap putBitmap(String str, Bitmap bitmap) {
        synchronized (bitmapHashLock) {
            ((LottieImageAsset) this.imageAssets.get(str)).setBitmap(bitmap);
        }
        return bitmap;
    }
}
