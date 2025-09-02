package kotlin.random;

import java.util.Random;

public final class FallbackThreadLocalRandom$implStorage$1 extends ThreadLocal {
    FallbackThreadLocalRandom$implStorage$1() {
    }

    /* access modifiers changed from: protected */
    public Random initialValue() {
        return new Random();
    }
}
