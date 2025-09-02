package com.salesforce.marketingcloud.http;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.react.devsupport.StackTraceHelper;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class d implements Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new c();
    public static final b g = new b((DefaultConstructorMarker) null);
    private final int a;
    private final String b;
    private final String c;
    private final long d;
    private final long e;
    private final Map<String, List<String>> f;

    public static final class a {
        private int a;
        private String b;
        private String c;
        private long d;
        private long e;
        private Map<String, ? extends List<String>> f;

        public final a a(String str) {
            Intrinsics.checkNotNullParameter(str, "body");
            this.b = str;
            return this;
        }

        public final a b(String str) {
            Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
            this.c = str;
            return this;
        }

        public final d a() {
            int i = this.a;
            String str = this.b;
            String str2 = this.c;
            long j = this.d;
            long j2 = this.e;
            Map<String, ? extends List<String>> map = this.f;
            if (map == null) {
                map = MapsKt.emptyMap();
            }
            return new d(i, str, str2, j, j2, map);
        }

        public final a b(long j) {
            this.d = j;
            return this;
        }

        public final a a(int i) {
            this.a = i;
            return this;
        }

        public final a a(long j) {
            this.e = j;
            return this;
        }

        public final a a(Map<String, ? extends List<String>> map) {
            Intrinsics.checkNotNullParameter(map, "headers");
            this.f = map;
            return this;
        }
    }

    public static final class b {
        private b() {
        }

        public final a a() {
            return new a();
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final d a(String str, int i) {
            Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
            long currentTimeMillis = System.currentTimeMillis();
            return a().a(i).b(str).b(currentTimeMillis).a(currentTimeMillis).a();
        }
    }

    public static final class c implements Parcelable.Creator<d> {
        /* renamed from: a */
        public final d createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            int readInt = parcel.readInt();
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            long readLong = parcel.readLong();
            long readLong2 = parcel.readLong();
            int readInt2 = parcel.readInt();
            LinkedHashMap linkedHashMap = new LinkedHashMap(readInt2);
            for (int i = 0; i != readInt2; i++) {
                linkedHashMap.put(parcel.readString(), parcel.createStringArrayList());
            }
            return new d(readInt, readString, readString2, readLong, readLong2, linkedHashMap);
        }

        /* renamed from: a */
        public final d[] newArray(int i) {
            return new d[i];
        }
    }

    public d(int i, String str, String str2, long j, long j2, Map<String, ? extends List<String>> map) {
        Intrinsics.checkNotNullParameter(map, "headers");
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = j;
        this.e = j2;
        this.f = map;
    }

    public static final d a(String str, int i) {
        return g.a(str, i);
    }

    public final int b() {
        return this.a;
    }

    public final long c() {
        return this.e;
    }

    public final Map<String, List<String>> d() {
        return this.f;
    }

    public int describeContents() {
        return 0;
    }

    public final String e() {
        return this.c;
    }

    public final long f() {
        return this.d;
    }

    public final boolean g() {
        int i = this.a;
        return 200 <= i && i < 300;
    }

    public final long h() {
        return this.e - this.d;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeLong(this.d);
        parcel.writeLong(this.e);
        Map<String, List<String>> map = this.f;
        parcel.writeInt(map.size());
        for (Map.Entry next : map.entrySet()) {
            parcel.writeString((String) next.getKey());
            parcel.writeStringList((List) next.getValue());
        }
    }

    public final String a() {
        return this.b;
    }
}
