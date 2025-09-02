package com.google.common.base;

final class Present extends Optional {
    private static final long serialVersionUID = 0;
    private final Object reference;

    public boolean isPresent() {
        return true;
    }

    Present(Object obj) {
        this.reference = obj;
    }

    public Object get() {
        return this.reference;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Present) {
            return this.reference.equals(((Present) obj).reference);
        }
        return false;
    }

    public int hashCode() {
        return this.reference.hashCode() + 1502476572;
    }

    public String toString() {
        String valueOf = String.valueOf(this.reference);
        StringBuilder sb = new StringBuilder(valueOf.length() + 13);
        sb.append("Optional.of(");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
