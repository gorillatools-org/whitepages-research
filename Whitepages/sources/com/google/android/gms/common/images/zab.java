package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.SystemClock;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.internal.Asserts;

final class zab implements Runnable {
    final /* synthetic */ ImageManager zaa;
    private final zag zab;

    public zab(ImageManager imageManager, zag zag) {
        this.zaa = imageManager;
        this.zab = zag;
    }

    public final void run() {
        Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
        ImageManager.ImageReceiver imageReceiver = (ImageManager.ImageReceiver) this.zaa.zah.get(this.zab);
        if (imageReceiver != null) {
            ImageManager imageManager = this.zaa;
            imageManager.zah.remove(this.zab);
            imageReceiver.zac(this.zab);
        }
        zag zag = this.zab;
        zad zad = zag.zaa;
        Uri uri = zad.zaa;
        if (uri != null) {
            Long l = (Long) this.zaa.zaj.get(uri);
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                    zag zag2 = this.zab;
                    ImageManager imageManager2 = this.zaa;
                    zag2.zab(imageManager2.zad, imageManager2.zag, true);
                    return;
                }
                ImageManager imageManager3 = this.zaa;
                imageManager3.zaj.remove(zad.zaa);
            }
            this.zab.zaa((Drawable) null, false, true, false);
            ImageManager imageManager4 = this.zaa;
            ImageManager.ImageReceiver imageReceiver2 = (ImageManager.ImageReceiver) imageManager4.zai.get(zad.zaa);
            if (imageReceiver2 == null) {
                ImageManager.ImageReceiver imageReceiver3 = new ImageManager.ImageReceiver(zad.zaa);
                ImageManager imageManager5 = this.zaa;
                imageManager5.zai.put(zad.zaa, imageReceiver3);
                imageReceiver2 = imageReceiver3;
            }
            imageReceiver2.zab(this.zab);
            zag zag3 = this.zab;
            if (!(zag3 instanceof zaf)) {
                this.zaa.zah.put(zag3, imageReceiver2);
            }
            synchronized (ImageManager.zaa) {
                try {
                    if (!ImageManager.zab.contains(zad.zaa)) {
                        ImageManager.zab.add(zad.zaa);
                        imageReceiver2.zad();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return;
        }
        ImageManager imageManager6 = this.zaa;
        zag.zab(imageManager6.zad, imageManager6.zag, true);
    }
}
