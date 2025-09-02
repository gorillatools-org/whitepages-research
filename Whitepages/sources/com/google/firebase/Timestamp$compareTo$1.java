package com.google.firebase;

import kotlin.jvm.internal.PropertyReference1Impl;

/* synthetic */ class Timestamp$compareTo$1 extends PropertyReference1Impl {
    public static final Timestamp$compareTo$1 INSTANCE = new Timestamp$compareTo$1();

    Timestamp$compareTo$1() {
        super(Timestamp.class, "seconds", "getSeconds()J", 0);
    }

    public Object get(Object obj) {
        return Long.valueOf(((Timestamp) obj).getSeconds());
    }
}
