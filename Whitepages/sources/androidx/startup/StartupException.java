package androidx.startup;

public final class StartupException extends RuntimeException {
    public StartupException(String str) {
        super(str);
    }

    public StartupException(Throwable th) {
        super(th);
    }
}
