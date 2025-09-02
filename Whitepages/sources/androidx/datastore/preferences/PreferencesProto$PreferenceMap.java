package androidx.datastore.preferences;

import androidx.datastore.preferences.protobuf.GeneratedMessageLite;
import androidx.datastore.preferences.protobuf.MapEntryLite;
import androidx.datastore.preferences.protobuf.MapFieldLite;
import androidx.datastore.preferences.protobuf.MessageLiteOrBuilder;
import androidx.datastore.preferences.protobuf.Parser;
import androidx.datastore.preferences.protobuf.WireFormat;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

public final class PreferencesProto$PreferenceMap extends GeneratedMessageLite implements MessageLiteOrBuilder {
    /* access modifiers changed from: private */
    public static final PreferencesProto$PreferenceMap DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int PREFERENCES_FIELD_NUMBER = 1;
    private MapFieldLite preferences_ = MapFieldLite.emptyMapField();

    private static final class PreferencesDefaultEntryHolder {
        static final MapEntryLite defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, PreferencesProto$Value.getDefaultInstance());
    }

    private PreferencesProto$PreferenceMap() {
    }

    private MapFieldLite internalGetPreferences() {
        return this.preferences_;
    }

    private MapFieldLite internalGetMutablePreferences() {
        if (!this.preferences_.isMutable()) {
            this.preferences_ = this.preferences_.mutableCopy();
        }
        return this.preferences_;
    }

    public Map getPreferencesMap() {
        return Collections.unmodifiableMap(internalGetPreferences());
    }

    /* access modifiers changed from: private */
    public Map getMutablePreferencesMap() {
        return internalGetMutablePreferences();
    }

    public static PreferencesProto$PreferenceMap parseFrom(InputStream inputStream) {
        return (PreferencesProto$PreferenceMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        /* synthetic */ Builder(PreferencesProto$1 preferencesProto$1) {
            this();
        }

        private Builder() {
            super(PreferencesProto$PreferenceMap.DEFAULT_INSTANCE);
        }

        public Builder putPreferences(String str, PreferencesProto$Value preferencesProto$Value) {
            str.getClass();
            preferencesProto$Value.getClass();
            copyOnWrite();
            ((PreferencesProto$PreferenceMap) this.instance).getMutablePreferencesMap().put(str, preferencesProto$Value);
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (PreferencesProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new PreferencesProto$PreferenceMap();
            case 2:
                return new Builder((PreferencesProto$1) null);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"preferences_", PreferencesDefaultEntryHolder.defaultEntry});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (PreferencesProto$PreferenceMap.class) {
                        try {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        PreferencesProto$PreferenceMap preferencesProto$PreferenceMap = new PreferencesProto$PreferenceMap();
        DEFAULT_INSTANCE = preferencesProto$PreferenceMap;
        GeneratedMessageLite.registerDefaultInstance(PreferencesProto$PreferenceMap.class, preferencesProto$PreferenceMap);
    }
}
