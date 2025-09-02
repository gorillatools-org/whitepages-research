package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import java.util.Collections;
import java.util.Map;
import okhttp3.internal.http2.Settings;

public class ExtensionRegistryLite {
    static final ExtensionRegistryLite EMPTY_REGISTRY_LITE = new ExtensionRegistryLite(true);
    private static boolean doFullRuntimeInheritanceCheck = true;
    private static volatile ExtensionRegistryLite emptyRegistry;
    private final Map extensionsByNumber = Collections.emptyMap();

    public static ExtensionRegistryLite getEmptyRegistry() {
        if (!doFullRuntimeInheritanceCheck) {
            return EMPTY_REGISTRY_LITE;
        }
        ExtensionRegistryLite extensionRegistryLite = emptyRegistry;
        if (extensionRegistryLite == null) {
            synchronized (ExtensionRegistryLite.class) {
                try {
                    extensionRegistryLite = emptyRegistry;
                    if (extensionRegistryLite == null) {
                        extensionRegistryLite = ExtensionRegistryFactory.createEmpty();
                        emptyRegistry = extensionRegistryLite;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return extensionRegistryLite;
    }

    public GeneratedMessageLite.GeneratedExtension findLiteExtensionByNumber(MessageLite messageLite, int i) {
        return (GeneratedMessageLite.GeneratedExtension) this.extensionsByNumber.get(new ObjectIntPair(messageLite, i));
    }

    ExtensionRegistryLite(boolean z) {
    }

    private static final class ObjectIntPair {
        private final int number;
        private final Object object;

        ObjectIntPair(Object obj, int i) {
            this.object = obj;
            this.number = i;
        }

        public int hashCode() {
            return (System.identityHashCode(this.object) * Settings.DEFAULT_INITIAL_WINDOW_SIZE) + this.number;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ObjectIntPair)) {
                return false;
            }
            ObjectIntPair objectIntPair = (ObjectIntPair) obj;
            if (this.object == objectIntPair.object && this.number == objectIntPair.number) {
                return true;
            }
            return false;
        }
    }
}
