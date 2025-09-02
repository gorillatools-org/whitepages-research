package kotlinx.coroutines.channels;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ChannelResult {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Failed failed = new Failed();
    private final Object holder;

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ ChannelResult m905boximpl(Object obj) {
        return new ChannelResult(obj);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static Object m906constructorimpl(Object obj) {
        return obj;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m907equalsimpl(Object obj, Object obj2) {
        return (obj2 instanceof ChannelResult) && Intrinsics.areEqual(obj, ((ChannelResult) obj2).m914unboximpl());
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m910hashCodeimpl(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public boolean equals(Object obj) {
        return m907equalsimpl(this.holder, obj);
    }

    public int hashCode() {
        return m910hashCodeimpl(this.holder);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ Object m914unboximpl() {
        return this.holder;
    }

    private /* synthetic */ ChannelResult(Object obj) {
        this.holder = obj;
    }

    /* renamed from: isSuccess-impl  reason: not valid java name */
    public static final boolean m912isSuccessimpl(Object obj) {
        return !(obj instanceof Failed);
    }

    /* renamed from: isClosed-impl  reason: not valid java name */
    public static final boolean m911isClosedimpl(Object obj) {
        return obj instanceof Closed;
    }

    /* renamed from: getOrNull-impl  reason: not valid java name */
    public static final Object m909getOrNullimpl(Object obj) {
        if (!(obj instanceof Failed)) {
            return obj;
        }
        return null;
    }

    /* renamed from: exceptionOrNull-impl  reason: not valid java name */
    public static final Throwable m908exceptionOrNullimpl(Object obj) {
        Closed closed = obj instanceof Closed ? (Closed) obj : null;
        if (closed != null) {
            return closed.cause;
        }
        return null;
    }

    public static class Failed {
        public String toString() {
            return "Failed";
        }
    }

    public static final class Closed extends Failed {
        public final Throwable cause;

        public Closed(Throwable th) {
            this.cause = th;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Closed) && Intrinsics.areEqual((Object) this.cause, (Object) ((Closed) obj).cause);
        }

        public int hashCode() {
            Throwable th = this.cause;
            if (th != null) {
                return th.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "Closed(" + this.cause + ')';
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* renamed from: success-JP2dKIU  reason: not valid java name */
        public final Object m917successJP2dKIU(Object obj) {
            return ChannelResult.m906constructorimpl(obj);
        }

        /* renamed from: failure-PtdJZtk  reason: not valid java name */
        public final Object m916failurePtdJZtk() {
            return ChannelResult.m906constructorimpl(ChannelResult.failed);
        }

        /* renamed from: closed-JP2dKIU  reason: not valid java name */
        public final Object m915closedJP2dKIU(Throwable th) {
            return ChannelResult.m906constructorimpl(new Closed(th));
        }
    }

    public String toString() {
        return m913toStringimpl(this.holder);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m913toStringimpl(Object obj) {
        if (obj instanceof Closed) {
            return ((Closed) obj).toString();
        }
        return "Value(" + obj + ')';
    }
}
