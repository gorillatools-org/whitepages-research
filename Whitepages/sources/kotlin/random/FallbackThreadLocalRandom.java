package kotlin.random;

import java.util.Random;
import kotlin.jvm.internal.Intrinsics;

public final class FallbackThreadLocalRandom extends AbstractPlatformRandom {
    private final FallbackThreadLocalRandom$implStorage$1 implStorage = new FallbackThreadLocalRandom$implStorage$1();

    public Random getImpl() {
        Object obj = this.implStorage.get();
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return (Random) obj;
    }
}
