package androidx.datastore.preferences.core;

import androidx.datastore.preferences.core.Preferences;
import java.util.Map;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class MutablePreferences$toString$1 extends Lambda implements Function1 {
    public static final MutablePreferences$toString$1 INSTANCE = new MutablePreferences$toString$1();

    MutablePreferences$toString$1() {
        super(1);
    }

    public final CharSequence invoke(Map.Entry entry) {
        String str;
        Intrinsics.checkNotNullParameter(entry, "entry");
        Object value = entry.getValue();
        if (value instanceof byte[]) {
            str = ArraysKt.joinToString$default((byte[]) value, (CharSequence) ", ", (CharSequence) "[", (CharSequence) "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
        } else {
            str = String.valueOf(entry.getValue());
        }
        return "  " + ((Preferences.Key) entry.getKey()).getName() + " = " + str;
    }
}
