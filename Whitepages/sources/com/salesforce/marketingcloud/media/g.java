package com.salesforce.marketingcloud.media;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

@SuppressLint({"UnknownNullness"})
public class g implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
    final WeakReference<ImageView> a;
    private final t b;
    f c;

    public g(t tVar, ImageView imageView, f fVar) {
        this.b = tVar;
        this.a = new WeakReference<>(imageView);
        this.c = fVar;
        imageView.addOnAttachStateChangeListener(this);
        if (imageView.getWindowToken() != null) {
            imageView.getViewTreeObserver().addOnPreDrawListener(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.c = null;
        ImageView imageView = this.a.get();
        if (imageView != null) {
            this.a.clear();
            imageView.removeOnAttachStateChangeListener(this);
            ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this);
            }
        }
    }

    public boolean onPreDraw() {
        ImageView imageView = this.a.get();
        if (imageView == null) {
            return true;
        }
        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if (!viewTreeObserver.isAlive()) {
            return true;
        }
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        if (width > 0 && height > 0) {
            imageView.removeOnAttachStateChangeListener(this);
            viewTreeObserver.removeOnPreDrawListener(this);
            this.a.clear();
            this.b.a(width, height).a(imageView, this.c);
        }
        return true;
    }

    public void onViewAttachedToWindow(View view) {
        view.getViewTreeObserver().addOnPreDrawListener(this);
    }

    public void onViewDetachedFromWindow(View view) {
        view.getViewTreeObserver().removeOnPreDrawListener(this);
    }
}
