package kotlin.reflect;

public interface KProperty extends KCallable {

    public interface Getter extends KFunction {
    }

    boolean isConst();

    boolean isLateinit();
}
