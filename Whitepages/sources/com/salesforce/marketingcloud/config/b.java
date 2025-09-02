package com.salesforce.marketingcloud.config;

import android.net.Uri;
import com.google.firebase.sessions.settings.RemoteSettings;
import kotlin.collections.ArraysKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;

public final class b {
    public static final a d = new a((DefaultConstructorMarker) null);
    private final String a;
    private final String b;
    private final Integer c;

    public static final class a {
        private a() {
        }

        public final b a(String str) {
            Intrinsics.checkNotNullParameter(str, "endpointIn");
            return a(this, str, (String) null, (Integer) null, 6, (Object) null);
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final b a(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "endpointIn");
            return a(this, str, str2, (Integer) null, 4, (Object) null);
        }

        public final b a(String str, String str2, Integer num) {
            String str3;
            Intrinsics.checkNotNullParameter(str, "endpointIn");
            String obj = StringsKt.trim(str).toString();
            if (obj.length() == 0 || !ArraysKt.contains(C0011b.values(), C0011b.valueOf(obj))) {
                throw new IllegalArgumentException("Invalid 'endpoint' for endpoint config.");
            }
            if (str2 == null || (str3 = StringsKt.trim(str2).toString()) == null) {
                str3 = null;
            } else if (str3.length() == 0 || !StringsKt.startsWith$default(str3, RemoteSettings.FORWARD_SLASH_STRING, false, 2, (Object) null) || !Intrinsics.areEqual((Object) str3, (Object) Uri.parse(str3).getPath())) {
                throw new IllegalArgumentException("Invalid 'path' for " + obj + " endpoint config.");
            }
            if (num != null && !new IntRange(10, Integer.MAX_VALUE).contains(num.intValue())) {
                throw new IllegalArgumentException("Invalid 'maxBatchSize' for " + obj + " endpoint config.");
            } else if (str3 != null || num != null) {
                return new b(obj, str3, num, (DefaultConstructorMarker) null);
            } else {
                throw new IllegalArgumentException("Empty endpoint config for " + obj + " is pointless.");
            }
        }

        public static /* synthetic */ b a(a aVar, String str, String str2, Integer num, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = null;
            }
            if ((i & 4) != 0) {
                num = null;
            }
            return aVar.a(str, str2, num);
        }
    }

    /* renamed from: com.salesforce.marketingcloud.config.b$b  reason: collision with other inner class name */
    public enum C0011b {
        EVENTS;

        static {
            C0011b[] a;
            c = EnumEntriesKt.enumEntries(a);
        }

        public static EnumEntries b() {
            return c;
        }
    }

    private b(String str, String str2, Integer num) {
        this.a = str;
        this.b = str2;
        this.c = num;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final Integer c() {
        return this.c;
    }

    public final String d() {
        return this.a;
    }

    public final Integer e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return Intrinsics.areEqual((Object) this.a, (Object) bVar.a) && Intrinsics.areEqual((Object) this.b, (Object) bVar.b) && Intrinsics.areEqual((Object) this.c, (Object) bVar.c);
    }

    public final String f() {
        return this.b;
    }

    public int hashCode() {
        int hashCode = this.a.hashCode() * 31;
        String str = this.b;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.c;
        if (num != null) {
            i = num.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        String str = this.a;
        String str2 = this.b;
        Integer num = this.c;
        return "EndpointConfig(endpoint=" + str + ", path=" + str2 + ", maxBatchSize=" + num + ")";
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ b(String str, String str2, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : num);
    }

    public final b a(String str, String str2, Integer num) {
        Intrinsics.checkNotNullParameter(str, a.i);
        return new b(str, str2, num);
    }

    public /* synthetic */ b(String str, String str2, Integer num, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, num);
    }

    public static /* synthetic */ b a(b bVar, String str, String str2, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bVar.a;
        }
        if ((i & 2) != 0) {
            str2 = bVar.b;
        }
        if ((i & 4) != 0) {
            num = bVar.c;
        }
        return bVar.a(str, str2, num);
    }
}
