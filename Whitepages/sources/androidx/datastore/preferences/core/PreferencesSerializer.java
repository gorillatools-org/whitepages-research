package androidx.datastore.preferences.core;

import androidx.datastore.core.CorruptionException;
import androidx.datastore.core.okio.OkioSerializer;
import androidx.datastore.preferences.PreferencesMapCompat;
import androidx.datastore.preferences.PreferencesProto$PreferenceMap;
import androidx.datastore.preferences.PreferencesProto$StringSet;
import androidx.datastore.preferences.PreferencesProto$Value;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.protobuf.ByteString;
import androidx.datastore.preferences.protobuf.GeneratedMessageLite;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;
import okio.BufferedSource;

public final class PreferencesSerializer implements OkioSerializer {
    public static final PreferencesSerializer INSTANCE = new PreferencesSerializer();

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|(2:17|18)|19|21) */
        /* JADX WARNING: Can't wrap try/catch for region: R(21:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|21) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0046 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0050 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                androidx.datastore.preferences.PreferencesProto$Value$ValueCase[] r0 = androidx.datastore.preferences.PreferencesProto$Value.ValueCase.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                androidx.datastore.preferences.PreferencesProto$Value$ValueCase r1 = androidx.datastore.preferences.PreferencesProto$Value.ValueCase.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                androidx.datastore.preferences.PreferencesProto$Value$ValueCase r1 = androidx.datastore.preferences.PreferencesProto$Value.ValueCase.FLOAT     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                androidx.datastore.preferences.PreferencesProto$Value$ValueCase r1 = androidx.datastore.preferences.PreferencesProto$Value.ValueCase.DOUBLE     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                androidx.datastore.preferences.PreferencesProto$Value$ValueCase r1 = androidx.datastore.preferences.PreferencesProto$Value.ValueCase.INTEGER     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                androidx.datastore.preferences.PreferencesProto$Value$ValueCase r1 = androidx.datastore.preferences.PreferencesProto$Value.ValueCase.LONG     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                androidx.datastore.preferences.PreferencesProto$Value$ValueCase r1 = androidx.datastore.preferences.PreferencesProto$Value.ValueCase.STRING     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                androidx.datastore.preferences.PreferencesProto$Value$ValueCase r1 = androidx.datastore.preferences.PreferencesProto$Value.ValueCase.STRING_SET     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                androidx.datastore.preferences.PreferencesProto$Value$ValueCase r1 = androidx.datastore.preferences.PreferencesProto$Value.ValueCase.BYTES     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                androidx.datastore.preferences.PreferencesProto$Value$ValueCase r1 = androidx.datastore.preferences.PreferencesProto$Value.ValueCase.VALUE_NOT_SET     // Catch:{ NoSuchFieldError -> 0x005a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.core.PreferencesSerializer.WhenMappings.<clinit>():void");
        }
    }

    private PreferencesSerializer() {
    }

    public Preferences getDefaultValue() {
        return PreferencesFactory.createEmpty();
    }

    public Object readFrom(BufferedSource bufferedSource, Continuation continuation) {
        PreferencesProto$PreferenceMap readFrom = PreferencesMapCompat.Companion.readFrom(bufferedSource.inputStream());
        MutablePreferences createMutable = PreferencesFactory.createMutable(new Preferences.Pair[0]);
        Map preferencesMap = readFrom.getPreferencesMap();
        Intrinsics.checkNotNullExpressionValue(preferencesMap, "preferencesProto.preferencesMap");
        for (Map.Entry entry : preferencesMap.entrySet()) {
            String str = (String) entry.getKey();
            PreferencesProto$Value preferencesProto$Value = (PreferencesProto$Value) entry.getValue();
            PreferencesSerializer preferencesSerializer = INSTANCE;
            Intrinsics.checkNotNullExpressionValue(str, "name");
            Intrinsics.checkNotNullExpressionValue(preferencesProto$Value, "value");
            preferencesSerializer.addProtoEntryToPreferences(str, preferencesProto$Value, createMutable);
        }
        return createMutable.toPreferences();
    }

    public Object writeTo(Preferences preferences, BufferedSink bufferedSink, Continuation continuation) {
        Map asMap = preferences.asMap();
        PreferencesProto$PreferenceMap.Builder newBuilder = PreferencesProto$PreferenceMap.newBuilder();
        for (Map.Entry entry : asMap.entrySet()) {
            newBuilder.putPreferences(((Preferences.Key) entry.getKey()).getName(), getValueProto(entry.getValue()));
        }
        ((PreferencesProto$PreferenceMap) newBuilder.build()).writeTo(bufferedSink.outputStream());
        return Unit.INSTANCE;
    }

    private final PreferencesProto$Value getValueProto(Object obj) {
        if (obj instanceof Boolean) {
            GeneratedMessageLite build = PreferencesProto$Value.newBuilder().setBoolean(((Boolean) obj).booleanValue()).build();
            Intrinsics.checkNotNullExpressionValue(build, "newBuilder().setBoolean(value).build()");
            return (PreferencesProto$Value) build;
        } else if (obj instanceof Float) {
            GeneratedMessageLite build2 = PreferencesProto$Value.newBuilder().setFloat(((Number) obj).floatValue()).build();
            Intrinsics.checkNotNullExpressionValue(build2, "newBuilder().setFloat(value).build()");
            return (PreferencesProto$Value) build2;
        } else if (obj instanceof Double) {
            GeneratedMessageLite build3 = PreferencesProto$Value.newBuilder().setDouble(((Number) obj).doubleValue()).build();
            Intrinsics.checkNotNullExpressionValue(build3, "newBuilder().setDouble(value).build()");
            return (PreferencesProto$Value) build3;
        } else if (obj instanceof Integer) {
            GeneratedMessageLite build4 = PreferencesProto$Value.newBuilder().setInteger(((Number) obj).intValue()).build();
            Intrinsics.checkNotNullExpressionValue(build4, "newBuilder().setInteger(value).build()");
            return (PreferencesProto$Value) build4;
        } else if (obj instanceof Long) {
            GeneratedMessageLite build5 = PreferencesProto$Value.newBuilder().setLong(((Number) obj).longValue()).build();
            Intrinsics.checkNotNullExpressionValue(build5, "newBuilder().setLong(value).build()");
            return (PreferencesProto$Value) build5;
        } else if (obj instanceof String) {
            GeneratedMessageLite build6 = PreferencesProto$Value.newBuilder().setString((String) obj).build();
            Intrinsics.checkNotNullExpressionValue(build6, "newBuilder().setString(value).build()");
            return (PreferencesProto$Value) build6;
        } else if (obj instanceof Set) {
            PreferencesProto$Value.Builder newBuilder = PreferencesProto$Value.newBuilder();
            PreferencesProto$StringSet.Builder newBuilder2 = PreferencesProto$StringSet.newBuilder();
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.Set<kotlin.String>");
            GeneratedMessageLite build7 = newBuilder.setStringSet(newBuilder2.addAllStrings((Set) obj)).build();
            Intrinsics.checkNotNullExpressionValue(build7, "newBuilder().setStringSe…                ).build()");
            return (PreferencesProto$Value) build7;
        } else if (obj instanceof byte[]) {
            GeneratedMessageLite build8 = PreferencesProto$Value.newBuilder().setBytes(ByteString.copyFrom((byte[]) obj)).build();
            Intrinsics.checkNotNullExpressionValue(build8, "newBuilder().setBytes(By….copyFrom(value)).build()");
            return (PreferencesProto$Value) build8;
        } else {
            throw new IllegalStateException("PreferencesSerializer does not support type: " + obj.getClass().getName());
        }
    }

    private final void addProtoEntryToPreferences(String str, PreferencesProto$Value preferencesProto$Value, MutablePreferences mutablePreferences) {
        PreferencesProto$Value.ValueCase valueCase = preferencesProto$Value.getValueCase();
        switch (valueCase == null ? -1 : WhenMappings.$EnumSwitchMapping$0[valueCase.ordinal()]) {
            case -1:
                throw new CorruptionException("Value case is null.", (Throwable) null, 2, (DefaultConstructorMarker) null);
            case 1:
                mutablePreferences.set(PreferencesKeys.booleanKey(str), Boolean.valueOf(preferencesProto$Value.getBoolean()));
                return;
            case 2:
                mutablePreferences.set(PreferencesKeys.floatKey(str), Float.valueOf(preferencesProto$Value.getFloat()));
                return;
            case 3:
                mutablePreferences.set(PreferencesKeys.doubleKey(str), Double.valueOf(preferencesProto$Value.getDouble()));
                return;
            case 4:
                mutablePreferences.set(PreferencesKeys.intKey(str), Integer.valueOf(preferencesProto$Value.getInteger()));
                return;
            case 5:
                mutablePreferences.set(PreferencesKeys.longKey(str), Long.valueOf(preferencesProto$Value.getLong()));
                return;
            case 6:
                Preferences.Key stringKey = PreferencesKeys.stringKey(str);
                String string = preferencesProto$Value.getString();
                Intrinsics.checkNotNullExpressionValue(string, "value.string");
                mutablePreferences.set(stringKey, string);
                return;
            case 7:
                Preferences.Key stringSetKey = PreferencesKeys.stringSetKey(str);
                List stringsList = preferencesProto$Value.getStringSet().getStringsList();
                Intrinsics.checkNotNullExpressionValue(stringsList, "value.stringSet.stringsList");
                mutablePreferences.set(stringSetKey, CollectionsKt.toSet(stringsList));
                return;
            case 8:
                Preferences.Key byteArrayKey = PreferencesKeys.byteArrayKey(str);
                byte[] byteArray = preferencesProto$Value.getBytes().toByteArray();
                Intrinsics.checkNotNullExpressionValue(byteArray, "value.bytes.toByteArray()");
                mutablePreferences.set(byteArrayKey, byteArray);
                return;
            case 9:
                throw new CorruptionException("Value not set.", (Throwable) null, 2, (DefaultConstructorMarker) null);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
