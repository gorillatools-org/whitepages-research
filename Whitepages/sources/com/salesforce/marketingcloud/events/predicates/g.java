package com.salesforce.marketingcloud.events.predicates;

import android.annotation.SuppressLint;
import com.salesforce.marketingcloud.events.g;
import java.util.Locale;

@SuppressLint({"UnknownNullness"})
public class g extends h<String> {

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
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
                com.salesforce.marketingcloud.events.g$a r1 = com.salesforce.marketingcloud.events.g.a.REGEX     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.events.predicates.g.a.<clinit>():void");
        }
    }

    public g(Object obj, g.a aVar, Object obj2) {
        super(obj, aVar, obj2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public String a(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean a(String str, g.a aVar, String str2) throws UnsupportedOperationException {
        if (str == null || str2 == null) {
            return false;
        }
        int i = a.a[aVar.ordinal()];
        if (i == 1) {
            return str.equalsIgnoreCase(str2);
        }
        if (i == 2) {
            return !str.equalsIgnoreCase(str2);
        }
        if (i == 3) {
            return str.matches(str2);
        }
        throw new UnsupportedOperationException(String.format(Locale.ENGLISH, "Operator %s not supported for String data types.", new Object[]{aVar}));
    }
}
