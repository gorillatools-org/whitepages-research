package com.salesforce.marketingcloud.events.predicates;

import android.annotation.SuppressLint;
import com.facebook.hermes.intl.Constants;
import com.salesforce.marketingcloud.events.g;
import java.util.Locale;

@SuppressLint({"UnknownNullness"})
public class b extends h<Boolean> {

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
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
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.events.predicates.b.a.<clinit>():void");
        }
    }

    public b(Object obj, g.a aVar, Object obj2) {
        super(obj, aVar, obj2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public Boolean a(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if ("true".equalsIgnoreCase(str)) {
                return Boolean.TRUE;
            }
            if (Constants.CASEFIRST_FALSE.equalsIgnoreCase(str)) {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean a(Boolean bool, g.a aVar, Boolean bool2) throws UnsupportedOperationException {
        if (bool == null || bool2 == null) {
            return false;
        }
        int i = a.a[aVar.ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new UnsupportedOperationException(String.format(Locale.ENGLISH, "Operator %s not supported for Boolean data types.", new Object[]{aVar}));
            } else if (bool == bool2) {
                return false;
            }
        } else if (bool != bool2) {
            return false;
        }
        return true;
    }
}
