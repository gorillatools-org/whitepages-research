package com.facebook.react.bridge;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReactIgnorableMountingException extends RuntimeException {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public static final boolean isIgnorable(Throwable th) {
        return Companion.isIgnorable(th);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactIgnorableMountingException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "m");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactIgnorableMountingException(Throwable th) {
        super(th);
        Intrinsics.checkNotNullParameter(th, "e");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactIgnorableMountingException(String str, Throwable th) {
        super(str, th);
        Intrinsics.checkNotNullParameter(str, "m");
        Intrinsics.checkNotNullParameter(th, "e");
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isIgnorable(Throwable th) {
            Intrinsics.checkNotNullParameter(th, "e");
            while (th != null) {
                if (th instanceof ReactIgnorableMountingException) {
                    return true;
                }
                th = th.getCause();
            }
            return false;
        }
    }
}
