package com.facebook.internal.gatekeeper;

import kotlin.jvm.internal.Intrinsics;

public final class GateKeeper {
    private final String name;
    private final boolean value;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GateKeeper)) {
            return false;
        }
        GateKeeper gateKeeper = (GateKeeper) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) gateKeeper.name) && this.value == gateKeeper.value;
    }

    public int hashCode() {
        int hashCode = this.name.hashCode() * 31;
        boolean z = this.value;
        if (z) {
            z = true;
        }
        return hashCode + (z ? 1 : 0);
    }

    public String toString() {
        return "GateKeeper(name=" + this.name + ", value=" + this.value + ')';
    }

    public GateKeeper(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "name");
        this.name = str;
        this.value = z;
    }

    public final String getName() {
        return this.name;
    }

    public final boolean getValue() {
        return this.value;
    }
}
