package com.salesforce.marketingcloud.messages;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.internal.f;
import com.salesforce.marketingcloud.storage.k;
import com.salesforce.marketingcloud.util.c;
import java.util.Date;

@SuppressLint({"UnknownNullness"})
public final class b {
    private static final String a = d.C;

    private b() {
    }

    public static boolean a(Message message) {
        Date endDateUtc = message.endDateUtc();
        return endDateUtc == null || endDateUtc.getTime() >= System.currentTimeMillis();
    }

    private static int b(Message message) {
        int messagesPerPeriod = message.messagesPerPeriod();
        if (messagesPerPeriod > 0 || message.numberOfPeriods() <= 0 || message.periodType() == 0) {
            return messagesPerPeriod;
        }
        return 1;
    }

    static boolean c(Message message) {
        try {
            if (TextUtils.isEmpty(message.alert().trim())) {
                g.a(a, "Message (%s) was tripped, but does not have an alert message", message.id());
                return false;
            }
            Date date = new Date();
            if (message.endDateUtc() != null && message.endDateUtc().before(date)) {
                g.a(a, "Message (%s) was tripped, but has expired.", message.id());
                return false;
            } else if (message.startDateUtc() != null && message.startDateUtc().after(date)) {
                g.a(a, "Message (%s) was tripped, but has not started", message.id());
                return false;
            } else if (message.messageLimit() <= -1 || f.e(message) < message.messageLimit()) {
                int b = b(message);
                if (b > -1 && f.d(message) >= b && f.b(message) != null && date.before(f.b(message))) {
                    g.a(a, "Message (%s) was tripped, but has met its message per period limit", message.id());
                    return false;
                } else if (f.b(message) == null || !date.before(f.b(message))) {
                    return true;
                } else {
                    g.a(a, "Message (%s) was tripped, but was before its next allowed show time.", message.id());
                    return false;
                }
            } else {
                g.a(a, "Message (%s) was tripped, but has met its message limit.", message.id());
                return false;
            }
        } catch (Exception e) {
            g.b(a, e, "Failed to determine is message should be shown.", new Object[0]);
            return false;
        }
    }

    public static void a(Message message, k kVar, c cVar) {
        Message a2 = kVar.a(message.id(), cVar);
        if (a2 != null) {
            f.a(message, f.a(a2));
            f.c(message, f.e(a2));
            if (message.periodType() == a2.periodType()) {
                f.b(message, f.d(a2));
                f.b(message, f.b(a2));
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00ba, code lost:
        if (r6 != 5) goto L_0x00d2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void a(com.salesforce.marketingcloud.messages.Message r15, com.salesforce.marketingcloud.storage.j r16) throws java.lang.Exception {
        /*
            r0 = r15
            java.util.Date r1 = new java.util.Date
            r1.<init>()
            com.salesforce.marketingcloud.internal.f.a((com.salesforce.marketingcloud.messages.Message) r15, (java.util.Date) r1)
            int r2 = com.salesforce.marketingcloud.internal.f.e(r15)
            r3 = 1
            int r2 = r2 + r3
            com.salesforce.marketingcloud.internal.f.c(r15, r2)
            int r2 = b(r15)
            r4 = 0
            r5 = -1
            if (r2 <= r5) goto L_0x00d9
            int r6 = r15.numberOfPeriods()
            if (r6 <= r5) goto L_0x00d9
            int r6 = r15.periodType()
            if (r6 == 0) goto L_0x00d9
            int r6 = com.salesforce.marketingcloud.internal.f.d(r15)
            int r6 = r6 + r3
            com.salesforce.marketingcloud.internal.f.b((com.salesforce.marketingcloud.messages.Message) r15, (int) r6)
            int r6 = com.salesforce.marketingcloud.internal.f.d(r15)
            int r7 = r15.messagesPerPeriod()
            if (r6 < r7) goto L_0x00d9
            int r6 = r15.periodType()
            r7 = 4
            r8 = 3
            r9 = 2
            r10 = 5
            if (r6 == r3) goto L_0x006a
            if (r6 == r9) goto L_0x005e
            if (r6 == r8) goto L_0x0059
            r11 = 1
            if (r6 == r7) goto L_0x0056
            if (r6 == r10) goto L_0x004f
            r11 = 0
            goto L_0x0076
        L_0x004f:
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.HOURS
        L_0x0051:
            long r11 = r6.toMillis(r11)
            goto L_0x0076
        L_0x0056:
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.DAYS
            goto L_0x0051
        L_0x0059:
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.DAYS
            r11 = 7
            goto L_0x0051
        L_0x005e:
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.DAYS
            java.util.Calendar r11 = java.util.Calendar.getInstance()
            int r11 = r11.getActualMaximum(r10)
        L_0x0068:
            long r11 = (long) r11
            goto L_0x0051
        L_0x006a:
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.DAYS
            java.util.Calendar r11 = java.util.Calendar.getInstance()
            r12 = 6
            int r11 = r11.getActualMaximum(r12)
            goto L_0x0068
        L_0x0076:
            java.util.Date r6 = new java.util.Date
            long r13 = r1.getTime()
            int r1 = r15.numberOfPeriods()
            long r7 = (long) r1
            long r7 = r7 * r11
            long r13 = r13 + r7
            r6.<init>(r13)
            com.salesforce.marketingcloud.internal.f.b((com.salesforce.marketingcloud.messages.Message) r15, (java.util.Date) r6)
            boolean r1 = r15.isRollingPeriod()
            if (r1 != 0) goto L_0x00d9
            java.util.Calendar r1 = java.util.Calendar.getInstance()
            java.util.Date r6 = com.salesforce.marketingcloud.internal.f.b(r15)
            long r6 = r6.getTime()
            r1.setTimeInMillis(r6)
            r6 = 14
            r1.set(r6, r4)
            r6 = 13
            r1.set(r6, r4)
            int r6 = r15.periodType()
            r7 = 10
            r8 = 12
            if (r6 == r3) goto L_0x00ce
            if (r6 == r9) goto L_0x00ca
            r9 = 3
            if (r6 == r9) goto L_0x00c5
            r9 = 4
            if (r6 == r9) goto L_0x00c1
            if (r6 == r10) goto L_0x00bd
            goto L_0x00d2
        L_0x00bd:
            r1.set(r8, r4)
            goto L_0x00d2
        L_0x00c1:
            r1.set(r7, r4)
            goto L_0x00bd
        L_0x00c5:
            r6 = 7
            r1.set(r6, r3)
            goto L_0x00c1
        L_0x00ca:
            r1.set(r10, r3)
            goto L_0x00c1
        L_0x00ce:
            r1.set(r9, r4)
            goto L_0x00ca
        L_0x00d2:
            java.util.Date r1 = r1.getTime()
            com.salesforce.marketingcloud.internal.f.b((com.salesforce.marketingcloud.messages.Message) r15, (java.util.Date) r1)
        L_0x00d9:
            int r1 = com.salesforce.marketingcloud.internal.f.d(r15)
            if (r1 <= r5) goto L_0x00ea
            if (r2 <= r5) goto L_0x00ea
            int r1 = com.salesforce.marketingcloud.internal.f.d(r15)
            if (r1 <= r2) goto L_0x00ea
            com.salesforce.marketingcloud.internal.f.b((com.salesforce.marketingcloud.messages.Message) r15, (int) r4)
        L_0x00ea:
            com.salesforce.marketingcloud.storage.k r1 = r16.s()
            com.salesforce.marketingcloud.util.c r2 = r16.b()
            r1.a((com.salesforce.marketingcloud.messages.Message) r15, (com.salesforce.marketingcloud.util.c) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.b.a(com.salesforce.marketingcloud.messages.Message, com.salesforce.marketingcloud.storage.j):void");
    }
}
