package androidx.datastore.preferences;

import androidx.datastore.preferences.protobuf.AbstractMessageLite;
import androidx.datastore.preferences.protobuf.GeneratedMessageLite;
import androidx.datastore.preferences.protobuf.Internal;
import androidx.datastore.preferences.protobuf.MessageLiteOrBuilder;
import androidx.datastore.preferences.protobuf.Parser;
import java.util.List;

public final class PreferencesProto$StringSet extends GeneratedMessageLite implements MessageLiteOrBuilder {
    /* access modifiers changed from: private */
    public static final PreferencesProto$StringSet DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int STRINGS_FIELD_NUMBER = 1;
    private Internal.ProtobufList strings_ = GeneratedMessageLite.emptyProtobufList();

    private PreferencesProto$StringSet() {
    }

    public List getStringsList() {
        return this.strings_;
    }

    private void ensureStringsIsMutable() {
        Internal.ProtobufList protobufList = this.strings_;
        if (!protobufList.isModifiable()) {
            this.strings_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void addAllStrings(Iterable iterable) {
        ensureStringsIsMutable();
        AbstractMessageLite.addAll(iterable, this.strings_);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        /* synthetic */ Builder(PreferencesProto$1 preferencesProto$1) {
            this();
        }

        private Builder() {
            super(PreferencesProto$StringSet.DEFAULT_INSTANCE);
        }

        public Builder addAllStrings(Iterable iterable) {
            copyOnWrite();
            ((PreferencesProto$StringSet) this.instance).addAllStrings(iterable);
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (PreferencesProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new PreferencesProto$StringSet();
            case 2:
                return new Builder((PreferencesProto$1) null);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"strings_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (PreferencesProto$StringSet.class) {
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
        PreferencesProto$StringSet preferencesProto$StringSet = new PreferencesProto$StringSet();
        DEFAULT_INSTANCE = preferencesProto$StringSet;
        GeneratedMessageLite.registerDefaultInstance(PreferencesProto$StringSet.class, preferencesProto$StringSet);
    }

    public static PreferencesProto$StringSet getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }
}
