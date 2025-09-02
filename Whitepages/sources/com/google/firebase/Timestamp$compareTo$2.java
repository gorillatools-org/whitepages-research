package com.google.firebase;

import kotlin.jvm.internal.PropertyReference1Impl;

/* synthetic */ class Timestamp$compareTo$2 extends PropertyReference1Impl {
    public static final Timestamp$compareTo$2 INSTANCE = new Timestamp$compareTo$2();

    Timestamp$compareTo$2() {
        super(Timestamp.class, "nanoseconds", "getNanoseconds()I", 0);
    }

    public Object get(Object obj) {
        return Integer.valueOf(((Timestamp) obj).getNanoseconds());
    }
}
