package okio;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.messaging.Constants;
import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class Segment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public final byte[] data;
    public int limit;
    public Segment next;
    public boolean owner;
    public int pos;
    public Segment prev;
    public boolean shared;

    public Segment() {
        this.data = new byte[UserMetadata.MAX_INTERNAL_KEY_SIZE];
        this.owner = true;
        this.shared = false;
    }

    public Segment(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(bArr, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        this.data = bArr;
        this.pos = i;
        this.limit = i2;
        this.shared = z;
        this.owner = z2;
    }

    public final Segment sharedCopy() {
        this.shared = true;
        return new Segment(this.data, this.pos, this.limit, true, false);
    }

    public final Segment unsharedCopy() {
        byte[] bArr = this.data;
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        return new Segment(copyOf, this.pos, this.limit, false, true);
    }

    public final Segment pop() {
        Segment segment = this.next;
        if (segment == this) {
            segment = null;
        }
        Segment segment2 = this.prev;
        Intrinsics.checkNotNull(segment2);
        segment2.next = this.next;
        Segment segment3 = this.next;
        Intrinsics.checkNotNull(segment3);
        segment3.prev = this.prev;
        this.next = null;
        this.prev = null;
        return segment;
    }

    public final Segment push(Segment segment) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        segment.prev = this;
        segment.next = this.next;
        Segment segment2 = this.next;
        Intrinsics.checkNotNull(segment2);
        segment2.prev = segment;
        this.next = segment;
        return segment;
    }

    public final Segment split(int i) {
        Segment segment;
        if (i <= 0 || i > this.limit - this.pos) {
            throw new IllegalArgumentException("byteCount out of range");
        }
        if (i >= 1024) {
            segment = sharedCopy();
        } else {
            segment = SegmentPool.take();
            byte[] bArr = this.data;
            byte[] bArr2 = segment.data;
            int i2 = this.pos;
            ArraysKt.copyInto$default(bArr, bArr2, 0, i2, i2 + i, 2, (Object) null);
        }
        segment.limit = segment.pos + i;
        this.pos += i;
        Segment segment2 = this.prev;
        Intrinsics.checkNotNull(segment2);
        segment2.push(segment);
        return segment;
    }

    public final void compact() {
        int i;
        Segment segment = this.prev;
        if (segment != this) {
            Intrinsics.checkNotNull(segment);
            if (segment.owner) {
                int i2 = this.limit - this.pos;
                Segment segment2 = this.prev;
                Intrinsics.checkNotNull(segment2);
                int i3 = 8192 - segment2.limit;
                Segment segment3 = this.prev;
                Intrinsics.checkNotNull(segment3);
                if (segment3.shared) {
                    i = 0;
                } else {
                    Segment segment4 = this.prev;
                    Intrinsics.checkNotNull(segment4);
                    i = segment4.pos;
                }
                if (i2 <= i3 + i) {
                    Segment segment5 = this.prev;
                    Intrinsics.checkNotNull(segment5);
                    writeTo(segment5, i2);
                    pop();
                    SegmentPool.recycle(this);
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalStateException("cannot compact");
    }

    public final void writeTo(Segment segment, int i) {
        Intrinsics.checkNotNullParameter(segment, "sink");
        if (segment.owner) {
            int i2 = segment.limit;
            if (i2 + i > 8192) {
                if (!segment.shared) {
                    int i3 = segment.pos;
                    if ((i2 + i) - i3 <= 8192) {
                        byte[] bArr = segment.data;
                        ArraysKt.copyInto$default(bArr, bArr, 0, i3, i2, 2, (Object) null);
                        segment.limit -= segment.pos;
                        segment.pos = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            byte[] bArr2 = this.data;
            byte[] bArr3 = segment.data;
            int i4 = segment.limit;
            int i5 = this.pos;
            ArraysKt.copyInto(bArr2, bArr3, i4, i5, i5 + i);
            segment.limit += i;
            this.pos += i;
            return;
        }
        throw new IllegalStateException("only owner can write");
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
