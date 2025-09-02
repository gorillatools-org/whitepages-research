package androidx.lifecycle.viewmodel;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

public abstract class CreationExtras {
    private final Map map = new LinkedHashMap();

    public interface Key {
    }

    public abstract Object get(Key key);

    public final Map getMap$lifecycle_viewmodel_release() {
        return this.map;
    }

    public static final class Empty extends CreationExtras {
        public static final Empty INSTANCE = new Empty();

        public Object get(Key key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return null;
        }

        private Empty() {
        }
    }
}
