package com.google.android.datatransport.runtime.dagger.internal;

public final class InstanceFactory implements Factory {
    private static final InstanceFactory NULL_INSTANCE_FACTORY = new InstanceFactory((Object) null);
    private final Object instance;

    public static Factory create(Object obj) {
        return new InstanceFactory(Preconditions.checkNotNull(obj, "instance cannot be null"));
    }

    private InstanceFactory(Object obj) {
        this.instance = obj;
    }

    public Object get() {
        return this.instance;
    }
}
