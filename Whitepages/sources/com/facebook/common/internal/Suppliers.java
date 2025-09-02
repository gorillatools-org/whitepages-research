package com.facebook.common.internal;

public abstract class Suppliers {
    public static final Supplier BOOLEAN_FALSE = new Supplier() {
        public Boolean get() {
            return Boolean.FALSE;
        }
    };
    public static final Supplier BOOLEAN_TRUE = new Supplier() {
        public Boolean get() {
            return Boolean.TRUE;
        }
    };
    public static final Supplier STRING_EMPTY = new Supplier() {
        public String get() {
            return "";
        }
    };

    public static Supplier of(final Object obj) {
        return new Supplier() {
            public Object get() {
                return obj;
            }
        };
    }
}
