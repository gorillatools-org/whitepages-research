package com.salesforce.marketingcloud.internal;

import com.salesforce.marketingcloud.InitializationStatus;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class c {
    public static final a a = new a((DefaultConstructorMarker) null);

    public static final class a {
        private a() {
        }

        public final InitializationStatus a() {
            return InitializationStatus.Companion.a();
        }

        public final InitializationStatus.a b() {
            return InitializationStatus.Companion.b();
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static final InitializationStatus a() {
        return a.a();
    }

    public static final InitializationStatus.a b() {
        return a.b();
    }
}
