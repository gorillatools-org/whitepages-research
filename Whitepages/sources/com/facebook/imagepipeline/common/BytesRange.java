package com.facebook.imagepipeline.common;

import com.facebook.common.internal.Preconditions;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

public final class BytesRange {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Lazy headerParsingRegEx$delegate = LazyKt.lazy(new BytesRange$$ExternalSyntheticLambda0());
    public final int from;
    public final int to;

    public static final BytesRange from(int i) {
        return Companion.from(i);
    }

    public static final BytesRange toMax(int i) {
        return Companion.toMax(i);
    }

    public BytesRange(int i, int i2) {
        this.from = i;
        this.to = i2;
    }

    public final String toHttpRangeHeaderValue() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Companion companion = Companion;
        String format = String.format((Locale) null, "bytes=%s-%s", Arrays.copyOf(new Object[]{companion.valueOrEmpty(this.from), companion.valueOrEmpty(this.to)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public final boolean contains(BytesRange bytesRange) {
        return bytesRange != null && this.from <= bytesRange.from && bytesRange.to <= this.to;
    }

    public String toString() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Companion companion = Companion;
        String format = String.format((Locale) null, "%s-%s", Arrays.copyOf(new Object[]{companion.valueOrEmpty(this.from), companion.valueOrEmpty(this.to)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) BytesRange.class, (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.facebook.imagepipeline.common.BytesRange");
        BytesRange bytesRange = (BytesRange) obj;
        if (this.from == bytesRange.from && this.to == bytesRange.to) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.from * 31) + this.to;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final Pattern getHeaderParsingRegEx() {
            Object value = BytesRange.headerParsingRegEx$delegate.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Pattern) value;
        }

        /* access modifiers changed from: private */
        public final String valueOrEmpty(int i) {
            return i == Integer.MAX_VALUE ? "" : String.valueOf(i);
        }

        public final BytesRange from(int i) {
            Preconditions.checkArgument(Boolean.valueOf(i >= 0));
            return new BytesRange(i, Integer.MAX_VALUE);
        }

        public final BytesRange toMax(int i) {
            Preconditions.checkArgument(Boolean.valueOf(i > 0));
            return new BytesRange(0, i);
        }

        public final BytesRange fromContentRangeHeader(String str) {
            if (str == null) {
                return null;
            }
            try {
                String[] split = getHeaderParsingRegEx().split(str);
                boolean z = false;
                Preconditions.checkArgument(Boolean.valueOf(split.length == 4));
                Preconditions.checkArgument(Boolean.valueOf(Intrinsics.areEqual((Object) split[0], (Object) "bytes")));
                String str2 = split[1];
                Intrinsics.checkNotNullExpressionValue(str2, "get(...)");
                int parseInt = Integer.parseInt(str2);
                String str3 = split[2];
                Intrinsics.checkNotNullExpressionValue(str3, "get(...)");
                int parseInt2 = Integer.parseInt(str3);
                String str4 = split[3];
                Intrinsics.checkNotNullExpressionValue(str4, "get(...)");
                int parseInt3 = Integer.parseInt(str4);
                Preconditions.checkArgument(Boolean.valueOf(parseInt2 > parseInt));
                if (parseInt3 > parseInt2) {
                    z = true;
                }
                Preconditions.checkArgument(Boolean.valueOf(z));
                if (parseInt2 < parseInt3 - 1) {
                    return new BytesRange(parseInt, parseInt2);
                }
                return new BytesRange(parseInt, Integer.MAX_VALUE);
            } catch (IllegalArgumentException e) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format((Locale) null, "Invalid Content-Range header value: \"%s\"", Arrays.copyOf(new Object[]{str}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                throw new IllegalArgumentException(format, e);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final Pattern headerParsingRegEx_delegate$lambda$0() {
        return Pattern.compile("[-/ ]");
    }
}
