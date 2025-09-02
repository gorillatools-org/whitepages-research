package com.salesforce.marketingcloud.media;

import android.annotation.SuppressLint;
import com.salesforce.marketingcloud.media.u;

@SuppressLint({"UnknownNullness"})
public class j extends a<Object> {
    private f f;

    j(o oVar, s sVar, f fVar) {
        super(oVar, (v) null, sVar);
        this.f = fVar;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        super.a();
        this.f = null;
    }

    /* access modifiers changed from: package-private */
    public void a(u.b bVar) {
        f fVar = this.f;
        if (fVar != null) {
            fVar.a();
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
