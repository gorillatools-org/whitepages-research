package kotlin.random;

import java.util.Random;

public abstract class AbstractPlatformRandom extends Random {
    public abstract Random getImpl();

    public int nextInt() {
        return getImpl().nextInt();
    }

    public int nextInt(int i) {
        return getImpl().nextInt(i);
    }

    public double nextDouble() {
        return getImpl().nextDouble();
    }
}
