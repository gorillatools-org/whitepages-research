package okio;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.Intrinsics;

public final class SegmentPool {
    private static final int HASH_BUCKET_COUNT;
    public static final SegmentPool INSTANCE = new SegmentPool();
    private static final Segment LOCK = new Segment(new byte[0], 0, 0, false, false);
    private static final int MAX_SIZE = 65536;
    private static final AtomicReference[] hashBuckets;

    private SegmentPool() {
    }

    static {
        int highestOneBit = Integer.highestOneBit((Runtime.getRuntime().availableProcessors() * 2) - 1);
        HASH_BUCKET_COUNT = highestOneBit;
        AtomicReference[] atomicReferenceArr = new AtomicReference[highestOneBit];
        for (int i = 0; i < highestOneBit; i++) {
            atomicReferenceArr[i] = new AtomicReference();
        }
        hashBuckets = atomicReferenceArr;
    }

    public static final Segment take() {
        AtomicReference firstRef = INSTANCE.firstRef();
        Segment segment = LOCK;
        Segment segment2 = (Segment) firstRef.getAndSet(segment);
        if (segment2 == segment) {
            return new Segment();
        }
        if (segment2 == null) {
            firstRef.set((Object) null);
            return new Segment();
        }
        firstRef.set(segment2.next);
        segment2.next = null;
        segment2.limit = 0;
        return segment2;
    }

    public static final void recycle(Segment segment) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        if (segment.next != null || segment.prev != null) {
            throw new IllegalArgumentException("Failed requirement.");
        } else if (!segment.shared) {
            AtomicReference firstRef = INSTANCE.firstRef();
            Segment segment2 = LOCK;
            Segment segment3 = (Segment) firstRef.getAndSet(segment2);
            if (segment3 != segment2) {
                int i = segment3 != null ? segment3.limit : 0;
                if (i >= MAX_SIZE) {
                    firstRef.set(segment3);
                    return;
                }
                segment.next = segment3;
                segment.pos = 0;
                segment.limit = i + UserMetadata.MAX_INTERNAL_KEY_SIZE;
                firstRef.set(segment);
            }
        }
    }

    private final AtomicReference firstRef() {
        return hashBuckets[(int) (Thread.currentThread().getId() & (((long) HASH_BUCKET_COUNT) - 1))];
    }
}
