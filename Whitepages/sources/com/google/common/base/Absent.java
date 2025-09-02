package com.google.common.base;

final class Absent extends Optional {
    static final Absent INSTANCE = new Absent();
    private static final long serialVersionUID = 0;

    public boolean equals(Object obj) {
        return obj == this;
    }

    public int hashCode() {
        return 2040732332;
    }

    public boolean isPresent() {
        return false;
    }

    static Optional withType() {
        return INSTANCE;
    }

    private Absent() {
    }

    public Object get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    public String toString() {
        return "Optional.absent()";
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
