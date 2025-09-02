package okhttp3;

import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class CacheControl {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final CacheControl FORCE_CACHE = new Builder().onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS).build();
    public static final CacheControl FORCE_NETWORK = new Builder().noCache().build();
    private String headerValue;
    private final boolean immutable;
    private final boolean isPrivate;
    private final boolean isPublic;
    private final int maxAgeSeconds;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean mustRevalidate;
    private final boolean noCache;
    private final boolean noStore;
    private final boolean noTransform;
    private final boolean onlyIfCached;
    private final int sMaxAgeSeconds;

    public static final CacheControl parse(Headers headers) {
        return Companion.parse(headers);
    }

    private CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8, String str) {
        this.noCache = z;
        this.noStore = z2;
        this.maxAgeSeconds = i;
        this.sMaxAgeSeconds = i2;
        this.isPrivate = z3;
        this.isPublic = z4;
        this.mustRevalidate = z5;
        this.maxStaleSeconds = i3;
        this.minFreshSeconds = i4;
        this.onlyIfCached = z6;
        this.noTransform = z7;
        this.immutable = z8;
        this.headerValue = str;
    }

    public /* synthetic */ CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, z2, i, i2, z3, z4, z5, i3, i4, z6, z7, z8, str);
    }

    public final boolean noCache() {
        return this.noCache;
    }

    public final boolean noStore() {
        return this.noStore;
    }

    public final int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public final int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    public final boolean isPrivate() {
        return this.isPrivate;
    }

    public final boolean isPublic() {
        return this.isPublic;
    }

    public final boolean mustRevalidate() {
        return this.mustRevalidate;
    }

    public final int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    public final int minFreshSeconds() {
        return this.minFreshSeconds;
    }

    public final boolean onlyIfCached() {
        return this.onlyIfCached;
    }

    public final boolean noTransform() {
        return this.noTransform;
    }

    public final boolean immutable() {
        return this.immutable;
    }

    /* renamed from: -deprecated_noCache  reason: not valid java name */
    public final boolean m949deprecated_noCache() {
        return this.noCache;
    }

    /* renamed from: -deprecated_noStore  reason: not valid java name */
    public final boolean m950deprecated_noStore() {
        return this.noStore;
    }

    /* renamed from: -deprecated_maxAgeSeconds  reason: not valid java name */
    public final int m945deprecated_maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    /* renamed from: -deprecated_sMaxAgeSeconds  reason: not valid java name */
    public final int m953deprecated_sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    /* renamed from: -deprecated_mustRevalidate  reason: not valid java name */
    public final boolean m948deprecated_mustRevalidate() {
        return this.mustRevalidate;
    }

    /* renamed from: -deprecated_maxStaleSeconds  reason: not valid java name */
    public final int m946deprecated_maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    /* renamed from: -deprecated_minFreshSeconds  reason: not valid java name */
    public final int m947deprecated_minFreshSeconds() {
        return this.minFreshSeconds;
    }

    /* renamed from: -deprecated_onlyIfCached  reason: not valid java name */
    public final boolean m952deprecated_onlyIfCached() {
        return this.onlyIfCached;
    }

    /* renamed from: -deprecated_noTransform  reason: not valid java name */
    public final boolean m951deprecated_noTransform() {
        return this.noTransform;
    }

    /* renamed from: -deprecated_immutable  reason: not valid java name */
    public final boolean m944deprecated_immutable() {
        return this.immutable;
    }

    public String toString() {
        String str = this.headerValue;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        if (this.noCache) {
            sb.append("no-cache, ");
        }
        if (this.noStore) {
            sb.append("no-store, ");
        }
        if (this.maxAgeSeconds != -1) {
            sb.append("max-age=");
            sb.append(this.maxAgeSeconds);
            sb.append(", ");
        }
        if (this.sMaxAgeSeconds != -1) {
            sb.append("s-maxage=");
            sb.append(this.sMaxAgeSeconds);
            sb.append(", ");
        }
        if (this.isPrivate) {
            sb.append("private, ");
        }
        if (this.isPublic) {
            sb.append("public, ");
        }
        if (this.mustRevalidate) {
            sb.append("must-revalidate, ");
        }
        if (this.maxStaleSeconds != -1) {
            sb.append("max-stale=");
            sb.append(this.maxStaleSeconds);
            sb.append(", ");
        }
        if (this.minFreshSeconds != -1) {
            sb.append("min-fresh=");
            sb.append(this.minFreshSeconds);
            sb.append(", ");
        }
        if (this.onlyIfCached) {
            sb.append("only-if-cached, ");
        }
        if (this.noTransform) {
            sb.append("no-transform, ");
        }
        if (this.immutable) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        this.headerValue = sb2;
        return sb2;
    }

    public static final class Builder {
        private boolean immutable;
        private int maxAgeSeconds = -1;
        private int maxStaleSeconds = -1;
        private int minFreshSeconds = -1;
        private boolean noCache;
        private boolean noStore;
        private boolean noTransform;
        private boolean onlyIfCached;

        private final int clampToInt(long j) {
            if (j > ((long) Integer.MAX_VALUE)) {
                return Integer.MAX_VALUE;
            }
            return (int) j;
        }

        public final Builder noCache() {
            this.noCache = true;
            return this;
        }

        public final Builder noStore() {
            this.noStore = true;
            return this;
        }

        public final Builder maxAge(int i, TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
            if (i >= 0) {
                this.maxAgeSeconds = clampToInt(timeUnit.toSeconds((long) i));
                return this;
            }
            throw new IllegalArgumentException(("maxAge < 0: " + i).toString());
        }

        public final Builder maxStale(int i, TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
            if (i >= 0) {
                this.maxStaleSeconds = clampToInt(timeUnit.toSeconds((long) i));
                return this;
            }
            throw new IllegalArgumentException(("maxStale < 0: " + i).toString());
        }

        public final Builder minFresh(int i, TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
            if (i >= 0) {
                this.minFreshSeconds = clampToInt(timeUnit.toSeconds((long) i));
                return this;
            }
            throw new IllegalArgumentException(("minFresh < 0: " + i).toString());
        }

        public final Builder onlyIfCached() {
            this.onlyIfCached = true;
            return this;
        }

        public final Builder noTransform() {
            this.noTransform = true;
            return this;
        }

        public final Builder immutable() {
            this.immutable = true;
            return this;
        }

        public final CacheControl build() {
            return new CacheControl(this.noCache, this.noStore, this.maxAgeSeconds, -1, false, false, false, this.maxStaleSeconds, this.minFreshSeconds, this.onlyIfCached, this.noTransform, this.immutable, (String) null, (DefaultConstructorMarker) null);
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x004b  */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x00de  */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x00e2  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final okhttp3.CacheControl parse(okhttp3.Headers r32) {
            /*
                r31 = this;
                r0 = r31
                r1 = r32
                java.lang.String r2 = "headers"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
                int r2 = r32.size()
                r5 = 1
                r8 = r5
                r7 = 0
                r9 = 0
                r10 = 0
                r11 = 0
                r12 = -1
                r13 = -1
                r14 = 0
                r15 = 0
                r16 = 0
                r17 = -1
                r18 = -1
                r19 = 0
                r20 = 0
                r21 = 0
            L_0x0023:
                if (r7 >= r2) goto L_0x0190
                java.lang.String r3 = r1.name(r7)
                java.lang.String r6 = r1.value(r7)
                java.lang.String r4 = "Cache-Control"
                boolean r4 = kotlin.text.StringsKt.equals(r3, r4, r5)
                if (r4 == 0) goto L_0x003b
                if (r9 == 0) goto L_0x0039
            L_0x0037:
                r8 = 0
                goto L_0x0044
            L_0x0039:
                r9 = r6
                goto L_0x0044
            L_0x003b:
                java.lang.String r4 = "Pragma"
                boolean r3 = kotlin.text.StringsKt.equals(r3, r4, r5)
                if (r3 == 0) goto L_0x0185
                goto L_0x0037
            L_0x0044:
                r3 = 0
            L_0x0045:
                int r4 = r6.length()
                if (r3 >= r4) goto L_0x017f
                java.lang.String r4 = "=,;"
                int r4 = r0.indexOfElement(r6, r4, r3)
                java.lang.String r3 = r6.substring(r3, r4)
                java.lang.String r5 = "(this as java.lang.Strin…ing(startIndex, endIndex)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)
                java.lang.String r1 = "null cannot be cast to non-null type kotlin.CharSequence"
                if (r3 == 0) goto L_0x0179
                java.lang.CharSequence r3 = kotlin.text.StringsKt.trim(r3)
                java.lang.String r3 = r3.toString()
                r29 = r2
                int r2 = r6.length()
                if (r4 == r2) goto L_0x00d0
                char r2 = r6.charAt(r4)
                r30 = r8
                r8 = 44
                if (r2 == r8) goto L_0x00d2
                char r2 = r6.charAt(r4)
                r8 = 59
                if (r2 != r8) goto L_0x0081
                goto L_0x00d2
            L_0x0081:
                int r4 = r4 + 1
                int r2 = okhttp3.internal.Util.indexOfNonWhitespace(r6, r4)
                int r4 = r6.length()
                if (r2 >= r4) goto L_0x00b2
                char r4 = r6.charAt(r2)
                r8 = 34
                if (r4 != r8) goto L_0x00b2
                int r2 = r2 + 1
                r27 = 4
                r28 = 0
                r24 = 34
                r26 = 0
                r23 = r6
                r25 = r2
                int r1 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r23, (char) r24, (int) r25, (boolean) r26, (int) r27, (java.lang.Object) r28)
                java.lang.String r2 = r6.substring(r2, r1)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)
                r4 = 1
                int r1 = r1 + r4
                r4 = r1
                goto L_0x00d5
            L_0x00b2:
                java.lang.String r4 = ",;"
                int r4 = r0.indexOfElement(r6, r4, r2)
                java.lang.String r2 = r6.substring(r2, r4)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)
                if (r2 == 0) goto L_0x00ca
                java.lang.CharSequence r1 = kotlin.text.StringsKt.trim(r2)
                java.lang.String r2 = r1.toString()
                goto L_0x00d5
            L_0x00ca:
                java.lang.NullPointerException r2 = new java.lang.NullPointerException
                r2.<init>(r1)
                throw r2
            L_0x00d0:
                r30 = r8
            L_0x00d2:
                int r4 = r4 + 1
                r2 = 0
            L_0x00d5:
                java.lang.String r1 = "no-cache"
                r5 = 1
                boolean r1 = kotlin.text.StringsKt.equals(r1, r3, r5)
                if (r1 == 0) goto L_0x00e2
                r10 = r5
            L_0x00df:
                r8 = -1
                goto L_0x0170
            L_0x00e2:
                java.lang.String r1 = "no-store"
                boolean r1 = kotlin.text.StringsKt.equals(r1, r3, r5)
                if (r1 == 0) goto L_0x00ec
                r11 = r5
                goto L_0x00df
            L_0x00ec:
                java.lang.String r1 = "max-age"
                boolean r1 = kotlin.text.StringsKt.equals(r1, r3, r5)
                if (r1 == 0) goto L_0x00fd
                r1 = -1
                int r2 = okhttp3.internal.Util.toNonNegativeInt(r2, r1)
                r8 = r1
                r12 = r2
                goto L_0x0170
            L_0x00fd:
                r1 = -1
                java.lang.String r8 = "s-maxage"
                boolean r8 = kotlin.text.StringsKt.equals(r8, r3, r5)
                if (r8 == 0) goto L_0x010e
                int r2 = okhttp3.internal.Util.toNonNegativeInt(r2, r1)
                r8 = r1
                r13 = r2
                goto L_0x0170
            L_0x010e:
                java.lang.String r1 = "private"
                boolean r1 = kotlin.text.StringsKt.equals(r1, r3, r5)
                if (r1 == 0) goto L_0x0118
                r14 = r5
                goto L_0x00df
            L_0x0118:
                java.lang.String r1 = "public"
                boolean r1 = kotlin.text.StringsKt.equals(r1, r3, r5)
                if (r1 == 0) goto L_0x0122
                r15 = r5
                goto L_0x00df
            L_0x0122:
                java.lang.String r1 = "must-revalidate"
                boolean r1 = kotlin.text.StringsKt.equals(r1, r3, r5)
                if (r1 == 0) goto L_0x012d
                r16 = r5
                goto L_0x00df
            L_0x012d:
                java.lang.String r1 = "max-stale"
                boolean r1 = kotlin.text.StringsKt.equals(r1, r3, r5)
                if (r1 == 0) goto L_0x013f
                r1 = 2147483647(0x7fffffff, float:NaN)
                int r1 = okhttp3.internal.Util.toNonNegativeInt(r2, r1)
                r17 = r1
                goto L_0x00df
            L_0x013f:
                java.lang.String r1 = "min-fresh"
                boolean r1 = kotlin.text.StringsKt.equals(r1, r3, r5)
                if (r1 == 0) goto L_0x014f
                r8 = -1
                int r1 = okhttp3.internal.Util.toNonNegativeInt(r2, r8)
                r18 = r1
                goto L_0x0170
            L_0x014f:
                r8 = -1
                java.lang.String r1 = "only-if-cached"
                boolean r1 = kotlin.text.StringsKt.equals(r1, r3, r5)
                if (r1 == 0) goto L_0x015b
                r19 = r5
                goto L_0x0170
            L_0x015b:
                java.lang.String r1 = "no-transform"
                boolean r1 = kotlin.text.StringsKt.equals(r1, r3, r5)
                if (r1 == 0) goto L_0x0166
                r20 = r5
                goto L_0x0170
            L_0x0166:
                java.lang.String r1 = "immutable"
                boolean r1 = kotlin.text.StringsKt.equals(r1, r3, r5)
                if (r1 == 0) goto L_0x0170
                r21 = r5
            L_0x0170:
                r1 = r32
                r3 = r4
                r2 = r29
                r8 = r30
                goto L_0x0045
            L_0x0179:
                java.lang.NullPointerException r2 = new java.lang.NullPointerException
                r2.<init>(r1)
                throw r2
            L_0x017f:
                r29 = r2
                r30 = r8
            L_0x0183:
                r1 = -1
                goto L_0x0188
            L_0x0185:
                r29 = r2
                goto L_0x0183
            L_0x0188:
                int r7 = r7 + 1
                r1 = r32
                r2 = r29
                goto L_0x0023
            L_0x0190:
                if (r8 != 0) goto L_0x0195
                r22 = 0
                goto L_0x0197
            L_0x0195:
                r22 = r9
            L_0x0197:
                okhttp3.CacheControl r1 = new okhttp3.CacheControl
                r23 = 0
                r9 = r1
                r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.CacheControl.Companion.parse(okhttp3.Headers):okhttp3.CacheControl");
        }

        static /* synthetic */ int indexOfElement$default(Companion companion, String str, String str2, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 0;
            }
            return companion.indexOfElement(str, str2, i);
        }

        private final int indexOfElement(String str, String str2, int i) {
            int length = str.length();
            while (i < length) {
                if (StringsKt.contains$default((CharSequence) str2, str.charAt(i), false, 2, (Object) null)) {
                    return i;
                }
                i++;
            }
            return str.length();
        }
    }
}
