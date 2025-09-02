package kotlinx.coroutines.internal;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.MainCoroutineDispatcher;

public final class MainDispatcherLoader {
    public static final MainDispatcherLoader INSTANCE;
    public static final MainCoroutineDispatcher dispatcher;

    private MainDispatcherLoader() {
    }

    static {
        MainDispatcherLoader mainDispatcherLoader = new MainDispatcherLoader();
        INSTANCE = mainDispatcherLoader;
        SystemPropsKt.systemProp("kotlinx.coroutines.fast.service.loader", true);
        dispatcher = mainDispatcherLoader.loadMainDispatcher();
    }

    private final MainCoroutineDispatcher loadMainDispatcher() {
        Object obj;
        Class<MainDispatcherFactory> cls = MainDispatcherFactory.class;
        try {
            List list = SequencesKt.toList(SequencesKt.asSequence(ServiceLoader.load(cls, cls.getClassLoader()).iterator()));
            Iterator it = list.iterator();
            if (!it.hasNext()) {
                obj = null;
            } else {
                obj = it.next();
                if (it.hasNext()) {
                    int loadPriority = ((MainDispatcherFactory) obj).getLoadPriority();
                    do {
                        Object next = it.next();
                        int loadPriority2 = ((MainDispatcherFactory) next).getLoadPriority();
                        if (loadPriority < loadPriority2) {
                            obj = next;
                            loadPriority = loadPriority2;
                        }
                    } while (it.hasNext());
                }
            }
            MainDispatcherFactory mainDispatcherFactory = (MainDispatcherFactory) obj;
            if (mainDispatcherFactory != null) {
                MainCoroutineDispatcher tryCreateDispatcher = MainDispatchersKt.tryCreateDispatcher(mainDispatcherFactory, list);
                if (tryCreateDispatcher != null) {
                    return tryCreateDispatcher;
                }
            }
            MainDispatchersKt.createMissingDispatcher$default((Throwable) null, (String) null, 3, (Object) null);
            return null;
        } catch (Throwable th) {
            MainDispatchersKt.createMissingDispatcher$default(th, (String) null, 2, (Object) null);
            return null;
        }
    }
}
