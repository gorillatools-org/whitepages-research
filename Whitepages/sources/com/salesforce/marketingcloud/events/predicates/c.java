package com.salesforce.marketingcloud.events.predicates;

import android.annotation.SuppressLint;
import com.salesforce.marketingcloud.events.g;

@SuppressLint({"UnknownNullness"})
public class c extends h<Double> {

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.salesforce.marketingcloud.events.g$a[] r0 = com.salesforce.marketingcloud.events.g.a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.salesforce.marketingcloud.events.g$a r1 = com.salesforce.marketingcloud.events.g.a.EQ     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.salesforce.marketingcloud.events.g$a r1 = com.salesforce.marketingcloud.events.g.a.NEQ     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.salesforce.marketingcloud.events.g$a r1 = com.salesforce.marketingcloud.events.g.a.LT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.salesforce.marketingcloud.events.g$a r1 = com.salesforce.marketingcloud.events.g.a.GT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.salesforce.marketingcloud.events.g$a r1 = com.salesforce.marketingcloud.events.g.a.LTEQ     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.salesforce.marketingcloud.events.g$a r1 = com.salesforce.marketingcloud.events.g.a.GTEQ     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.events.predicates.c.a.<clinit>():void");
        }
    }

    public c(Object obj, g.a aVar, Object obj2) {
        super(obj, aVar, obj2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public Double a(Object obj) {
        if (obj instanceof Double) {
            return (Double) obj;
        }
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj instanceof String) {
            try {
                return Double.valueOf(Double.parseDouble((String) obj));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0047 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.Double r6, com.salesforce.marketingcloud.events.g.a r7, java.lang.Double r8) throws java.lang.UnsupportedOperationException {
        /*
            r5 = this;
            r0 = 0
            if (r6 == 0) goto L_0x0048
            if (r8 == 0) goto L_0x0048
            double r1 = r6.doubleValue()
            double r3 = r8.doubleValue()
            int[] r6 = com.salesforce.marketingcloud.events.predicates.c.a.a
            int r8 = r7.ordinal()
            r6 = r6[r8]
            switch(r6) {
                case 1: goto L_0x0043;
                case 2: goto L_0x003e;
                case 3: goto L_0x0039;
                case 4: goto L_0x0034;
                case 5: goto L_0x002f;
                case 6: goto L_0x002a;
                default: goto L_0x0018;
            }
        L_0x0018:
            java.lang.UnsupportedOperationException r6 = new java.lang.UnsupportedOperationException
            java.util.Locale r8 = java.util.Locale.ENGLISH
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            java.lang.String r0 = "Operator %s not supported for Double data types."
            java.lang.String r7 = java.lang.String.format(r8, r0, r7)
            r6.<init>(r7)
            throw r6
        L_0x002a:
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 < 0) goto L_0x0048
            goto L_0x0047
        L_0x002f:
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 > 0) goto L_0x0048
            goto L_0x0047
        L_0x0034:
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x0048
            goto L_0x0047
        L_0x0039:
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 >= 0) goto L_0x0048
            goto L_0x0047
        L_0x003e:
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 == 0) goto L_0x0048
            goto L_0x0047
        L_0x0043:
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 != 0) goto L_0x0048
        L_0x0047:
            r0 = 1
        L_0x0048:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.events.predicates.c.a(java.lang.Double, com.salesforce.marketingcloud.events.g$a, java.lang.Double):boolean");
    }
}
