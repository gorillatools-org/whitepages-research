package com.salesforce.marketingcloud.media;

import android.annotation.SuppressLint;
import android.widget.ImageView;
import com.salesforce.marketingcloud.media.u;
import java.lang.ref.WeakReference;

@SuppressLint({"UnknownNullness"})
public class p extends a<ImageView> {
    f f;

    public p(o oVar, v<ImageView> vVar, s sVar, f fVar) {
        super(oVar, vVar, sVar);
        this.f = fVar;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        super.a();
        this.f = null;
    }

    /* access modifiers changed from: package-private */
    public void a(u.b bVar) {
        WeakReference<T> weakReference = this.b;
        ImageView imageView = weakReference != null ? (ImageView) weakReference.get() : null;
        if (imageView != null) {
            l.a(imageView, b().a, bVar);
            f fVar = this.f;
            if (fVar != null) {
                fVar.a();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Exception exc) {
        f fVar = this.f;
        if (fVar != null) {
            fVar.a(exc);
        }
    }
}
