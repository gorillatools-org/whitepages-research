package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public abstract class ViewModel {
    private final Map mBagOfTags = new HashMap();
    private volatile boolean mCleared = false;
    private final Set mCloseables = new LinkedHashSet();

    /* access modifiers changed from: protected */
    public void onCleared() {
    }

    /* access modifiers changed from: package-private */
    public final void clear() {
        this.mCleared = true;
        Map map = this.mBagOfTags;
        if (map != null) {
            synchronized (map) {
                try {
                    for (Object closeWithRuntimeException : this.mBagOfTags.values()) {
                        closeWithRuntimeException(closeWithRuntimeException);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        Set set = this.mCloseables;
        if (set != null) {
            synchronized (set) {
                try {
                    for (Closeable closeWithRuntimeException2 : this.mCloseables) {
                        closeWithRuntimeException(closeWithRuntimeException2);
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
        onCleared();
    }

    /* access modifiers changed from: package-private */
    public Object setTagIfAbsent(String str, Object obj) {
        Object obj2;
        synchronized (this.mBagOfTags) {
            try {
                obj2 = this.mBagOfTags.get(str);
                if (obj2 == null) {
                    this.mBagOfTags.put(str, obj);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (obj2 != null) {
            obj = obj2;
        }
        if (this.mCleared) {
            closeWithRuntimeException(obj);
        }
        return obj;
    }

    /* access modifiers changed from: package-private */
    public Object getTag(String str) {
        Object obj;
        Map map = this.mBagOfTags;
        if (map == null) {
            return null;
        }
        synchronized (map) {
            obj = this.mBagOfTags.get(str);
        }
        return obj;
    }

    private static void closeWithRuntimeException(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
