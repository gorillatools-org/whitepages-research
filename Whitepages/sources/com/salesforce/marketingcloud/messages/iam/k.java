package com.salesforce.marketingcloud.messages.iam;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.UrlHandler;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import java.util.Date;

class k implements Parcelable {
    public static final Parcelable.Creator<k> CREATOR = new a();
    private static final String g = g.a("MessageHandler");
    private final InAppMessage a;
    private i b;
    private long c;
    private long d;
    private long e;
    private boolean f;

    class a implements Parcelable.Creator<k> {
        a() {
        }

        /* renamed from: a */
        public k createFromParcel(Parcel parcel) {
            return new k(parcel);
        }

        /* renamed from: a */
        public k[] newArray(int i) {
            return new k[i];
        }
    }

    protected k(Parcel parcel) {
        this((InAppMessage) parcel.readParcelable(InAppMessage.class.getClassLoader()));
        this.c = parcel.readLong();
        this.d = parcel.readLong();
        this.f = parcel.readInt() != 1 ? false : true;
    }

    private void h() {
        if (this.f) {
            this.d += SystemClock.elapsedRealtime() - this.e;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r2.b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a() {
        /*
            r2 = this;
            com.salesforce.marketingcloud.messages.iam.InAppMessage r0 = r2.a
            if (r0 == 0) goto L_0x0010
            com.salesforce.marketingcloud.messages.iam.i r1 = r2.b
            if (r1 == 0) goto L_0x0010
            boolean r0 = r1.canDisplay(r0)
            if (r0 == 0) goto L_0x0010
            r0 = 1
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.iam.k.a():boolean");
    }

    public long b() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public Date c() {
        return new Date(this.c);
    }

    public InAppMessage d() {
        return this.a;
    }

    public int describeContents() {
        return 0;
    }

    public o e() {
        i iVar = this.b;
        if (iVar != null) {
            return iVar.imageHandler();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void f() {
        h();
    }

    /* access modifiers changed from: package-private */
    public void g() {
        if (this.c == -1) {
            this.c = System.currentTimeMillis();
        }
        this.e = SystemClock.elapsedRealtime();
    }

    /* access modifiers changed from: package-private */
    public int i() {
        i iVar = this.b;
        if (iVar != null) {
            return iVar.getStatusBarColor();
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void j() {
        h();
        this.f = false;
    }

    public Typeface k() {
        i iVar = this.b;
        if (iVar != null) {
            return iVar.getTypeface();
        }
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
        parcel.writeLong(this.c);
        parcel.writeLong(this.d);
        parcel.writeInt(this.f ? 1 : 0);
    }

    k(InAppMessage inAppMessage) {
        MarketingCloudSdk instance;
        this.c = -1;
        this.f = true;
        this.a = inAppMessage;
        if ((MarketingCloudSdk.isInitializing() || MarketingCloudSdk.isReady()) && (instance = MarketingCloudSdk.getInstance()) != null) {
            this.b = (i) instance.getInAppMessageManager();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(j jVar) {
        i iVar = this.b;
        if (iVar != null) {
            InAppMessage inAppMessage = this.a;
            if (jVar == null) {
                jVar = j.e();
            }
            iVar.handleMessageFinished(inAppMessage, jVar);
        }
    }

    /* access modifiers changed from: package-private */
    public PendingIntent a(Context context, InAppMessage.Button button) {
        UrlHandler urlHandler;
        String action = button.action();
        if (!(button.actionType() != InAppMessage.Button.ActionType.url || action == null || (urlHandler = this.b.urlHandler()) == null)) {
            try {
                return urlHandler.handleUrl(context, action, UrlHandler.ACTION);
            } catch (Exception e2) {
                g.b(g, e2, "Exception thrown by %s while handling url", urlHandler.getClass().getName());
            }
        }
        return null;
    }
}
