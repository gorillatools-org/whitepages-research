package kotlin;

import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class Result implements Serializable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    /* renamed from: constructor-impl  reason: not valid java name */
    public static Object m866constructorimpl(Object obj) {
        return obj;
    }

    /* renamed from: isSuccess-impl  reason: not valid java name */
    public static final boolean m869isSuccessimpl(Object obj) {
        return !(obj instanceof Failure);
    }

    /* renamed from: isFailure-impl  reason: not valid java name */
    public static final boolean m868isFailureimpl(Object obj) {
        return obj instanceof Failure;
    }

    /* renamed from: exceptionOrNull-impl  reason: not valid java name */
    public static final Throwable m867exceptionOrNullimpl(Object obj) {
        if (obj instanceof Failure) {
            return ((Failure) obj).exception;
        }
        return null;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class Failure implements Serializable {
        public final Throwable exception;

        public Failure(Throwable th) {
            Intrinsics.checkNotNullParameter(th, "exception");
            this.exception = th;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Failure) && Intrinsics.areEqual((Object) this.exception, (Object) ((Failure) obj).exception);
        }

        public int hashCode() {
            return this.exception.hashCode();
        }

        public String toString() {
            return "Failure(" + this.exception + ')';
        }
    }
}
