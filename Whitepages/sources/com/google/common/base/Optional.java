package com.google.common.base;

import java.io.Serializable;

public abstract class Optional implements Serializable {
    private static final long serialVersionUID = 0;

    public abstract Object get();

    public abstract boolean isPresent();

    public static Optional absent() {
        return Absent.withType();
    }

    public static Optional of(Object obj) {
        return new Present(Preconditions.checkNotNull(obj));
    }

    Optional() {
    }
}
