package com.salesforce.marketingcloud.internal;

import com.salesforce.marketingcloud.registration.Registration;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public final class k {
    public static final a a = new a((DefaultConstructorMarker) null);

    public static final class a {
        private a() {
        }

        public final Registration a(JSONObject jSONObject) {
            Intrinsics.checkNotNullParameter(jSONObject, "json");
            return new Registration(jSONObject);
        }

        public final JSONObject b(Registration registration) {
            Intrinsics.checkNotNullParameter(registration, com.salesforce.marketingcloud.storage.db.k.e);
            return registration.toJson$sdk_release();
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int a(Registration registration) {
            Intrinsics.checkNotNullParameter(registration, com.salesforce.marketingcloud.storage.db.k.e);
            return registration.m805id();
        }

        public final void a(Registration registration, int i) {
            Intrinsics.checkNotNullParameter(registration, com.salesforce.marketingcloud.storage.db.k.e);
            registration.setId$sdk_release(i);
        }
    }

    public static final Registration a(JSONObject jSONObject) {
        return a.a(jSONObject);
    }

    public static final JSONObject b(Registration registration) {
        return a.b(registration);
    }

    public static final int a(Registration registration) {
        return a.a(registration);
    }

    public static final void a(Registration registration, int i) {
        a.a(registration, i);
    }
}
