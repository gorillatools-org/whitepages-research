package com.google.common.base;

import java.io.Serializable;
import java.util.Objects;

public abstract class Suppliers {
    public static Supplier memoize(Supplier supplier) {
        if ((supplier instanceof NonSerializableMemoizingSupplier) || (supplier instanceof MemoizingSupplier)) {
            return supplier;
        }
        if (supplier instanceof Serializable) {
            return new MemoizingSupplier(supplier);
        }
        return new NonSerializableMemoizingSupplier(supplier);
    }

    static class MemoizingSupplier implements Supplier, Serializable {
        private static final long serialVersionUID = 0;
        final Supplier delegate;
        volatile transient boolean initialized;
        transient Object value;

        MemoizingSupplier(Supplier supplier) {
            this.delegate = (Supplier) Preconditions.checkNotNull(supplier);
        }

        public Object get() {
            if (!this.initialized) {
                synchronized (this) {
                    try {
                        if (!this.initialized) {
                            Object obj = this.delegate.get();
                            this.value = obj;
                            this.initialized = true;
                            return obj;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            return NullnessCasts.uncheckedCastNullableTToT(this.value);
        }

        public String toString() {
            Object obj;
            if (this.initialized) {
                String valueOf = String.valueOf(this.value);
                StringBuilder sb = new StringBuilder(valueOf.length() + 25);
                sb.append("<supplier that returned ");
                sb.append(valueOf);
                sb.append(">");
                obj = sb.toString();
            } else {
                obj = this.delegate;
            }
            String valueOf2 = String.valueOf(obj);
            StringBuilder sb2 = new StringBuilder(valueOf2.length() + 19);
            sb2.append("Suppliers.memoize(");
            sb2.append(valueOf2);
            sb2.append(")");
            return sb2.toString();
        }
    }

    static class NonSerializableMemoizingSupplier implements Supplier {
        volatile Supplier delegate;
        volatile boolean initialized;
        Object value;

        NonSerializableMemoizingSupplier(Supplier supplier) {
            this.delegate = (Supplier) Preconditions.checkNotNull(supplier);
        }

        public Object get() {
            if (!this.initialized) {
                synchronized (this) {
                    try {
                        if (!this.initialized) {
                            Supplier supplier = this.delegate;
                            Objects.requireNonNull(supplier);
                            Object obj = supplier.get();
                            this.value = obj;
                            this.initialized = true;
                            this.delegate = null;
                            return obj;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            return NullnessCasts.uncheckedCastNullableTToT(this.value);
        }

        public String toString() {
            Object obj = this.delegate;
            if (obj == null) {
                String valueOf = String.valueOf(this.value);
                StringBuilder sb = new StringBuilder(valueOf.length() + 25);
                sb.append("<supplier that returned ");
                sb.append(valueOf);
                sb.append(">");
                obj = sb.toString();
            }
            String valueOf2 = String.valueOf(obj);
            StringBuilder sb2 = new StringBuilder(valueOf2.length() + 19);
            sb2.append("Suppliers.memoize(");
            sb2.append(valueOf2);
            sb2.append(")");
            return sb2.toString();
        }
    }

    public static Supplier ofInstance(Object obj) {
        return new SupplierOfInstance(obj);
    }

    private static class SupplierOfInstance implements Supplier, Serializable {
        private static final long serialVersionUID = 0;
        final Object instance;

        SupplierOfInstance(Object obj) {
            this.instance = obj;
        }

        public Object get() {
            return this.instance;
        }

        public boolean equals(Object obj) {
            if (obj instanceof SupplierOfInstance) {
                return Objects.equal(this.instance, ((SupplierOfInstance) obj).instance);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(this.instance);
        }

        public String toString() {
            String valueOf = String.valueOf(this.instance);
            StringBuilder sb = new StringBuilder(valueOf.length() + 22);
            sb.append("Suppliers.ofInstance(");
            sb.append(valueOf);
            sb.append(")");
            return sb.toString();
        }
    }
}
