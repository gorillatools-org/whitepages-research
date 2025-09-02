package com.facebook.internal;

import java.util.EnumSet;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public enum SmartLoginOption {
    None(0),
    Enabled(1),
    RequireConfirm(2);
    
    /* access modifiers changed from: private */
    public static final EnumSet ALL = null;
    public static final Companion Companion = null;
    private final long value;

    private SmartLoginOption(long j) {
        this.value = j;
    }

    public final long getValue() {
        return this.value;
    }

    static {
        Companion = new Companion((DefaultConstructorMarker) null);
        EnumSet<E> allOf = EnumSet.allOf(SmartLoginOption.class);
        Intrinsics.checkNotNullExpressionValue(allOf, "allOf(SmartLoginOption::class.java)");
        ALL = allOf;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EnumSet parseOptions(long j) {
            EnumSet<E> noneOf = EnumSet.noneOf(SmartLoginOption.class);
            Iterator it = SmartLoginOption.ALL.iterator();
            while (it.hasNext()) {
                SmartLoginOption smartLoginOption = (SmartLoginOption) it.next();
                if ((smartLoginOption.getValue() & j) != 0) {
                    noneOf.add(smartLoginOption);
                }
            }
            Intrinsics.checkNotNullExpressionValue(noneOf, "result");
            return noneOf;
        }
    }
}
