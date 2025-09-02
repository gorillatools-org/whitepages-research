package com.salesforce.marketingcloud.media;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.media.u;
import java.io.IOException;

class i extends u {
    private static final String c = "drawable";
    private final Context a;
    private int b;

    i(Context context) {
        this.a = context;
    }

    public boolean a(s sVar) {
        if (c.equalsIgnoreCase(sVar.a.getScheme())) {
            this.b = this.a.getResources().getIdentifier(sVar.a.getHost(), c, this.a.getPackageName());
        }
        return this.b > 0;
    }

    public void a(o oVar, s sVar, u.a aVar) throws IOException {
        Drawable drawable = ContextCompat.getDrawable(this.a, this.b);
        if (drawable == null) {
            aVar.a((Throwable) new IllegalStateException("Invalid res id for drawable"));
        } else {
            aVar.a(new u.b(drawable, o.b.MEMORY));
        }
    }
}
