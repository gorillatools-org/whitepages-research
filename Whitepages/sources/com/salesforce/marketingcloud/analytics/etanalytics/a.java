package com.salesforce.marketingcloud.analytics.etanalytics;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.salesforce.marketingcloud.analytics.b;
import com.salesforce.marketingcloud.analytics.i;
import com.salesforce.marketingcloud.internal.g;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.messages.inbox.InboxMessage;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.storage.j;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@SuppressLint({"UnknownNullness"})
public class a extends i {
    private static final int f = 0;
    private final j d;
    private final l e;

    /* renamed from: com.salesforce.marketingcloud.analytics.etanalytics.a$a  reason: collision with other inner class name */
    class C0005a extends g {
        final /* synthetic */ j b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        C0005a(String str, Object[] objArr, j jVar) {
            super(str, objArr);
            this.b = jVar;
        }

        /* access modifiers changed from: protected */
        public void a() {
            this.b.m().b(0);
        }
    }

    public a(j jVar, l lVar) {
        this.d = jVar;
        this.e = lVar;
    }

    private static void a(l lVar, j jVar) {
        lVar.b().execute(new C0005a("delete_analytics", new Object[0], jVar));
    }

    public void b(NotificationMessage notificationMessage) {
        Region region = notificationMessage.region();
        if (!TextUtils.isEmpty(notificationMessage.id()) && region != null) {
            this.e.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.m(), this.d.b(), b.a(new Date(), 0, 3, Arrays.asList(new String[]{notificationMessage.id(), region.id()}), notificationMessage.requestId(), true)));
        }
    }

    public void a(InboxMessage inboxMessage) {
        this.e.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.m(), this.d.b(), b.a(new Date(), 0, 14, Collections.singletonList(inboxMessage.id()), com.salesforce.marketingcloud.internal.b.b(inboxMessage), true)));
    }

    public void a(NotificationMessage notificationMessage, boolean z) {
        if (notificationMessage.region() != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(notificationMessage.id());
            arrayList.add(notificationMessage.region().id());
            b a = b.a(new Date(), 0, 17, arrayList, notificationMessage.requestId(), true);
            a.b(z ? 1 : 0);
            this.e.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.m(), this.d.b(), a));
        }
    }

    public static void a(j jVar, l lVar, boolean z) {
        if (z) {
            a(lVar, jVar);
        }
    }

    public void a(boolean z) {
        if (z) {
            a(this.e, this.d);
        }
    }
}
