package kotlin.random;

import java.io.Serializable;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public abstract class Random {
    public static final Default Default = new Default((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Random defaultRandom = PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();

    public abstract double nextDouble();

    public abstract int nextInt();

    public abstract int nextInt(int i);

    public static final class Default extends Random implements Serializable {
        public /* synthetic */ Default(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Default() {
        }

        private static final class Serialized implements Serializable {
            public static final Serialized INSTANCE = new Serialized();
            private static final long serialVersionUID = 0;

            private Serialized() {
            }

            private final Object readResolve() {
                return Random.Default;
            }
        }

        private final Object writeReplace() {
            return Serialized.INSTANCE;
        }

        public int nextInt() {
            return Random.defaultRandom.nextInt();
        }

        public int nextInt(int i) {
            return Random.defaultRandom.nextInt(i);
        }

        public double nextDouble() {
            return Random.defaultRandom.nextDouble();
        }
    }
}
