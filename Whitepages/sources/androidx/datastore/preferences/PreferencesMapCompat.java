package androidx.datastore.preferences;

import androidx.datastore.core.CorruptionException;
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException;
import java.io.InputStream;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class PreferencesMapCompat {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PreferencesProto$PreferenceMap readFrom(InputStream inputStream) {
            Intrinsics.checkNotNullParameter(inputStream, "input");
            try {
                PreferencesProto$PreferenceMap parseFrom = PreferencesProto$PreferenceMap.parseFrom(inputStream);
                Intrinsics.checkNotNullExpressionValue(parseFrom, "{\n                Prefer…From(input)\n            }");
                return parseFrom;
            } catch (InvalidProtocolBufferException e) {
                throw new CorruptionException("Unable to parse preferences proto.", e);
            }
        }
    }
}
