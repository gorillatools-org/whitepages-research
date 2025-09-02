package com.google.firebase.perf.v1;

import com.google.firebase.perf.v1.PerfSession;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class TraceMetric extends GeneratedMessageLite implements TraceMetricOrBuilder {
    public static final int CLIENT_START_TIME_US_FIELD_NUMBER = 4;
    public static final int COUNTERS_FIELD_NUMBER = 6;
    public static final int CUSTOM_ATTRIBUTES_FIELD_NUMBER = 8;
    /* access modifiers changed from: private */
    public static final TraceMetric DEFAULT_INSTANCE;
    public static final int DURATION_US_FIELD_NUMBER = 5;
    public static final int IS_AUTO_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int PERF_SESSIONS_FIELD_NUMBER = 9;
    public static final int SUBTRACES_FIELD_NUMBER = 7;
    private int bitField0_;
    private long clientStartTimeUs_;
    private MapFieldLite counters_ = MapFieldLite.emptyMapField();
    private MapFieldLite customAttributes_ = MapFieldLite.emptyMapField();
    private long durationUs_;
    private boolean isAuto_;
    private String name_ = "";
    private Internal.ProtobufList perfSessions_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList subtraces_ = GeneratedMessageLite.emptyProtobufList();

    private TraceMetric() {
    }

    public boolean hasName() {
        return (this.bitField0_ & 1) != 0;
    }

    public String getName() {
        return this.name_;
    }

    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    public void setName(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.name_ = str;
    }

    /* access modifiers changed from: private */
    public void clearName() {
        this.bitField0_ &= -2;
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    public void setNameBytes(ByteString byteString) {
        this.name_ = byteString.toStringUtf8();
        this.bitField0_ |= 1;
    }

    public boolean hasIsAuto() {
        return (this.bitField0_ & 2) != 0;
    }

    public boolean getIsAuto() {
        return this.isAuto_;
    }

    /* access modifiers changed from: private */
    public void setIsAuto(boolean z) {
        this.bitField0_ |= 2;
        this.isAuto_ = z;
    }

    /* access modifiers changed from: private */
    public void clearIsAuto() {
        this.bitField0_ &= -3;
        this.isAuto_ = false;
    }

    public boolean hasClientStartTimeUs() {
        return (this.bitField0_ & 4) != 0;
    }

    public long getClientStartTimeUs() {
        return this.clientStartTimeUs_;
    }

    /* access modifiers changed from: private */
    public void setClientStartTimeUs(long j) {
        this.bitField0_ |= 4;
        this.clientStartTimeUs_ = j;
    }

    /* access modifiers changed from: private */
    public void clearClientStartTimeUs() {
        this.bitField0_ &= -5;
        this.clientStartTimeUs_ = 0;
    }

    public boolean hasDurationUs() {
        return (this.bitField0_ & 8) != 0;
    }

    public long getDurationUs() {
        return this.durationUs_;
    }

    /* access modifiers changed from: private */
    public void setDurationUs(long j) {
        this.bitField0_ |= 8;
        this.durationUs_ = j;
    }

    /* access modifiers changed from: private */
    public void clearDurationUs() {
        this.bitField0_ &= -9;
        this.durationUs_ = 0;
    }

    private static final class CountersDefaultEntryHolder {
        static final MapEntryLite defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, "", WireFormat.FieldType.INT64, 0L);

        private CountersDefaultEntryHolder() {
        }
    }

    private MapFieldLite internalGetCounters() {
        return this.counters_;
    }

    private MapFieldLite internalGetMutableCounters() {
        if (!this.counters_.isMutable()) {
            this.counters_ = this.counters_.mutableCopy();
        }
        return this.counters_;
    }

    public int getCountersCount() {
        return internalGetCounters().size();
    }

    public boolean containsCounters(String str) {
        str.getClass();
        return internalGetCounters().containsKey(str);
    }

    @Deprecated
    public Map<String, Long> getCounters() {
        return getCountersMap();
    }

    public Map<String, Long> getCountersMap() {
        return Collections.unmodifiableMap(internalGetCounters());
    }

    public long getCountersOrDefault(String str, long j) {
        str.getClass();
        MapFieldLite internalGetCounters = internalGetCounters();
        return internalGetCounters.containsKey(str) ? ((Long) internalGetCounters.get(str)).longValue() : j;
    }

    public long getCountersOrThrow(String str) {
        str.getClass();
        MapFieldLite internalGetCounters = internalGetCounters();
        if (internalGetCounters.containsKey(str)) {
            return ((Long) internalGetCounters.get(str)).longValue();
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: private */
    public Map<String, Long> getMutableCountersMap() {
        return internalGetMutableCounters();
    }

    public List<TraceMetric> getSubtracesList() {
        return this.subtraces_;
    }

    public List<? extends TraceMetricOrBuilder> getSubtracesOrBuilderList() {
        return this.subtraces_;
    }

    public int getSubtracesCount() {
        return this.subtraces_.size();
    }

    public TraceMetric getSubtraces(int i) {
        return (TraceMetric) this.subtraces_.get(i);
    }

    public TraceMetricOrBuilder getSubtracesOrBuilder(int i) {
        return (TraceMetricOrBuilder) this.subtraces_.get(i);
    }

    private void ensureSubtracesIsMutable() {
        Internal.ProtobufList protobufList = this.subtraces_;
        if (!protobufList.isModifiable()) {
            this.subtraces_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setSubtraces(int i, TraceMetric traceMetric) {
        traceMetric.getClass();
        ensureSubtracesIsMutable();
        this.subtraces_.set(i, traceMetric);
    }

    /* access modifiers changed from: private */
    public void addSubtraces(TraceMetric traceMetric) {
        traceMetric.getClass();
        ensureSubtracesIsMutable();
        this.subtraces_.add(traceMetric);
    }

    /* access modifiers changed from: private */
    public void addSubtraces(int i, TraceMetric traceMetric) {
        traceMetric.getClass();
        ensureSubtracesIsMutable();
        this.subtraces_.add(i, traceMetric);
    }

    /* access modifiers changed from: private */
    public void addAllSubtraces(Iterable<? extends TraceMetric> iterable) {
        ensureSubtracesIsMutable();
        AbstractMessageLite.addAll(iterable, this.subtraces_);
    }

    /* access modifiers changed from: private */
    public void clearSubtraces() {
        this.subtraces_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeSubtraces(int i) {
        ensureSubtracesIsMutable();
        this.subtraces_.remove(i);
    }

    private static final class CustomAttributesDefaultEntryHolder {
        static final MapEntryLite defaultEntry;

        private CustomAttributesDefaultEntryHolder() {
        }

        static {
            WireFormat.FieldType fieldType = WireFormat.FieldType.STRING;
            defaultEntry = MapEntryLite.newDefaultInstance(fieldType, "", fieldType, "");
        }
    }

    private MapFieldLite internalGetCustomAttributes() {
        return this.customAttributes_;
    }

    private MapFieldLite internalGetMutableCustomAttributes() {
        if (!this.customAttributes_.isMutable()) {
            this.customAttributes_ = this.customAttributes_.mutableCopy();
        }
        return this.customAttributes_;
    }

    public int getCustomAttributesCount() {
        return internalGetCustomAttributes().size();
    }

    public boolean containsCustomAttributes(String str) {
        str.getClass();
        return internalGetCustomAttributes().containsKey(str);
    }

    @Deprecated
    public Map<String, String> getCustomAttributes() {
        return getCustomAttributesMap();
    }

    public Map<String, String> getCustomAttributesMap() {
        return Collections.unmodifiableMap(internalGetCustomAttributes());
    }

    public String getCustomAttributesOrDefault(String str, String str2) {
        str.getClass();
        MapFieldLite internalGetCustomAttributes = internalGetCustomAttributes();
        return internalGetCustomAttributes.containsKey(str) ? (String) internalGetCustomAttributes.get(str) : str2;
    }

    public String getCustomAttributesOrThrow(String str) {
        str.getClass();
        MapFieldLite internalGetCustomAttributes = internalGetCustomAttributes();
        if (internalGetCustomAttributes.containsKey(str)) {
            return (String) internalGetCustomAttributes.get(str);
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: private */
    public Map<String, String> getMutableCustomAttributesMap() {
        return internalGetMutableCustomAttributes();
    }

    public List<PerfSession> getPerfSessionsList() {
        return this.perfSessions_;
    }

    public List<? extends PerfSessionOrBuilder> getPerfSessionsOrBuilderList() {
        return this.perfSessions_;
    }

    public int getPerfSessionsCount() {
        return this.perfSessions_.size();
    }

    public PerfSession getPerfSessions(int i) {
        return (PerfSession) this.perfSessions_.get(i);
    }

    public PerfSessionOrBuilder getPerfSessionsOrBuilder(int i) {
        return (PerfSessionOrBuilder) this.perfSessions_.get(i);
    }

    private void ensurePerfSessionsIsMutable() {
        Internal.ProtobufList protobufList = this.perfSessions_;
        if (!protobufList.isModifiable()) {
            this.perfSessions_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setPerfSessions(int i, PerfSession perfSession) {
        perfSession.getClass();
        ensurePerfSessionsIsMutable();
        this.perfSessions_.set(i, perfSession);
    }

    /* access modifiers changed from: private */
    public void addPerfSessions(PerfSession perfSession) {
        perfSession.getClass();
        ensurePerfSessionsIsMutable();
        this.perfSessions_.add(perfSession);
    }

    /* access modifiers changed from: private */
    public void addPerfSessions(int i, PerfSession perfSession) {
        perfSession.getClass();
        ensurePerfSessionsIsMutable();
        this.perfSessions_.add(i, perfSession);
    }

    /* access modifiers changed from: private */
    public void addAllPerfSessions(Iterable<? extends PerfSession> iterable) {
        ensurePerfSessionsIsMutable();
        AbstractMessageLite.addAll(iterable, this.perfSessions_);
    }

    /* access modifiers changed from: private */
    public void clearPerfSessions() {
        this.perfSessions_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removePerfSessions(int i) {
        ensurePerfSessionsIsMutable();
        this.perfSessions_.remove(i);
    }

    public static TraceMetric parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (TraceMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static TraceMetric parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TraceMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static TraceMetric parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (TraceMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static TraceMetric parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TraceMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static TraceMetric parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (TraceMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TraceMetric parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TraceMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static TraceMetric parseFrom(InputStream inputStream) throws IOException {
        return (TraceMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TraceMetric parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TraceMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TraceMetric parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (TraceMetric) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TraceMetric parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TraceMetric) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TraceMetric parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (TraceMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static TraceMetric parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TraceMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(TraceMetric traceMetric) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(traceMetric);
    }

    public static final class Builder extends GeneratedMessageLite.Builder implements TraceMetricOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(TraceMetric.DEFAULT_INSTANCE);
        }

        public boolean hasName() {
            return ((TraceMetric) this.instance).hasName();
        }

        public String getName() {
            return ((TraceMetric) this.instance).getName();
        }

        public ByteString getNameBytes() {
            return ((TraceMetric) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((TraceMetric) this.instance).setName(str);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((TraceMetric) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((TraceMetric) this.instance).setNameBytes(byteString);
            return this;
        }

        public boolean hasIsAuto() {
            return ((TraceMetric) this.instance).hasIsAuto();
        }

        public boolean getIsAuto() {
            return ((TraceMetric) this.instance).getIsAuto();
        }

        public Builder setIsAuto(boolean z) {
            copyOnWrite();
            ((TraceMetric) this.instance).setIsAuto(z);
            return this;
        }

        public Builder clearIsAuto() {
            copyOnWrite();
            ((TraceMetric) this.instance).clearIsAuto();
            return this;
        }

        public boolean hasClientStartTimeUs() {
            return ((TraceMetric) this.instance).hasClientStartTimeUs();
        }

        public long getClientStartTimeUs() {
            return ((TraceMetric) this.instance).getClientStartTimeUs();
        }

        public Builder setClientStartTimeUs(long j) {
            copyOnWrite();
            ((TraceMetric) this.instance).setClientStartTimeUs(j);
            return this;
        }

        public Builder clearClientStartTimeUs() {
            copyOnWrite();
            ((TraceMetric) this.instance).clearClientStartTimeUs();
            return this;
        }

        public boolean hasDurationUs() {
            return ((TraceMetric) this.instance).hasDurationUs();
        }

        public long getDurationUs() {
            return ((TraceMetric) this.instance).getDurationUs();
        }

        public Builder setDurationUs(long j) {
            copyOnWrite();
            ((TraceMetric) this.instance).setDurationUs(j);
            return this;
        }

        public Builder clearDurationUs() {
            copyOnWrite();
            ((TraceMetric) this.instance).clearDurationUs();
            return this;
        }

        public int getCountersCount() {
            return ((TraceMetric) this.instance).getCountersMap().size();
        }

        public boolean containsCounters(String str) {
            str.getClass();
            return ((TraceMetric) this.instance).getCountersMap().containsKey(str);
        }

        public Builder clearCounters() {
            copyOnWrite();
            ((TraceMetric) this.instance).getMutableCountersMap().clear();
            return this;
        }

        public Builder removeCounters(String str) {
            str.getClass();
            copyOnWrite();
            ((TraceMetric) this.instance).getMutableCountersMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, Long> getCounters() {
            return getCountersMap();
        }

        public Map<String, Long> getCountersMap() {
            return Collections.unmodifiableMap(((TraceMetric) this.instance).getCountersMap());
        }

        public long getCountersOrDefault(String str, long j) {
            str.getClass();
            Map<String, Long> countersMap = ((TraceMetric) this.instance).getCountersMap();
            return countersMap.containsKey(str) ? countersMap.get(str).longValue() : j;
        }

        public long getCountersOrThrow(String str) {
            str.getClass();
            Map<String, Long> countersMap = ((TraceMetric) this.instance).getCountersMap();
            if (countersMap.containsKey(str)) {
                return countersMap.get(str).longValue();
            }
            throw new IllegalArgumentException();
        }

        public Builder putCounters(String str, long j) {
            str.getClass();
            copyOnWrite();
            ((TraceMetric) this.instance).getMutableCountersMap().put(str, Long.valueOf(j));
            return this;
        }

        public Builder putAllCounters(Map<String, Long> map) {
            copyOnWrite();
            ((TraceMetric) this.instance).getMutableCountersMap().putAll(map);
            return this;
        }

        public List<TraceMetric> getSubtracesList() {
            return Collections.unmodifiableList(((TraceMetric) this.instance).getSubtracesList());
        }

        public int getSubtracesCount() {
            return ((TraceMetric) this.instance).getSubtracesCount();
        }

        public TraceMetric getSubtraces(int i) {
            return ((TraceMetric) this.instance).getSubtraces(i);
        }

        public Builder setSubtraces(int i, TraceMetric traceMetric) {
            copyOnWrite();
            ((TraceMetric) this.instance).setSubtraces(i, traceMetric);
            return this;
        }

        public Builder setSubtraces(int i, Builder builder) {
            copyOnWrite();
            ((TraceMetric) this.instance).setSubtraces(i, (TraceMetric) builder.build());
            return this;
        }

        public Builder addSubtraces(TraceMetric traceMetric) {
            copyOnWrite();
            ((TraceMetric) this.instance).addSubtraces(traceMetric);
            return this;
        }

        public Builder addSubtraces(int i, TraceMetric traceMetric) {
            copyOnWrite();
            ((TraceMetric) this.instance).addSubtraces(i, traceMetric);
            return this;
        }

        public Builder addSubtraces(Builder builder) {
            copyOnWrite();
            ((TraceMetric) this.instance).addSubtraces((TraceMetric) builder.build());
            return this;
        }

        public Builder addSubtraces(int i, Builder builder) {
            copyOnWrite();
            ((TraceMetric) this.instance).addSubtraces(i, (TraceMetric) builder.build());
            return this;
        }

        public Builder addAllSubtraces(Iterable<? extends TraceMetric> iterable) {
            copyOnWrite();
            ((TraceMetric) this.instance).addAllSubtraces(iterable);
            return this;
        }

        public Builder clearSubtraces() {
            copyOnWrite();
            ((TraceMetric) this.instance).clearSubtraces();
            return this;
        }

        public Builder removeSubtraces(int i) {
            copyOnWrite();
            ((TraceMetric) this.instance).removeSubtraces(i);
            return this;
        }

        public int getCustomAttributesCount() {
            return ((TraceMetric) this.instance).getCustomAttributesMap().size();
        }

        public boolean containsCustomAttributes(String str) {
            str.getClass();
            return ((TraceMetric) this.instance).getCustomAttributesMap().containsKey(str);
        }

        public Builder clearCustomAttributes() {
            copyOnWrite();
            ((TraceMetric) this.instance).getMutableCustomAttributesMap().clear();
            return this;
        }

        public Builder removeCustomAttributes(String str) {
            str.getClass();
            copyOnWrite();
            ((TraceMetric) this.instance).getMutableCustomAttributesMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, String> getCustomAttributes() {
            return getCustomAttributesMap();
        }

        public Map<String, String> getCustomAttributesMap() {
            return Collections.unmodifiableMap(((TraceMetric) this.instance).getCustomAttributesMap());
        }

        public String getCustomAttributesOrDefault(String str, String str2) {
            str.getClass();
            Map<String, String> customAttributesMap = ((TraceMetric) this.instance).getCustomAttributesMap();
            return customAttributesMap.containsKey(str) ? customAttributesMap.get(str) : str2;
        }

        public String getCustomAttributesOrThrow(String str) {
            str.getClass();
            Map<String, String> customAttributesMap = ((TraceMetric) this.instance).getCustomAttributesMap();
            if (customAttributesMap.containsKey(str)) {
                return customAttributesMap.get(str);
            }
            throw new IllegalArgumentException();
        }

        public Builder putCustomAttributes(String str, String str2) {
            str.getClass();
            str2.getClass();
            copyOnWrite();
            ((TraceMetric) this.instance).getMutableCustomAttributesMap().put(str, str2);
            return this;
        }

        public Builder putAllCustomAttributes(Map<String, String> map) {
            copyOnWrite();
            ((TraceMetric) this.instance).getMutableCustomAttributesMap().putAll(map);
            return this;
        }

        public List<PerfSession> getPerfSessionsList() {
            return Collections.unmodifiableList(((TraceMetric) this.instance).getPerfSessionsList());
        }

        public int getPerfSessionsCount() {
            return ((TraceMetric) this.instance).getPerfSessionsCount();
        }

        public PerfSession getPerfSessions(int i) {
            return ((TraceMetric) this.instance).getPerfSessions(i);
        }

        public Builder setPerfSessions(int i, PerfSession perfSession) {
            copyOnWrite();
            ((TraceMetric) this.instance).setPerfSessions(i, perfSession);
            return this;
        }

        public Builder setPerfSessions(int i, PerfSession.Builder builder) {
            copyOnWrite();
            ((TraceMetric) this.instance).setPerfSessions(i, (PerfSession) builder.build());
            return this;
        }

        public Builder addPerfSessions(PerfSession perfSession) {
            copyOnWrite();
            ((TraceMetric) this.instance).addPerfSessions(perfSession);
            return this;
        }

        public Builder addPerfSessions(int i, PerfSession perfSession) {
            copyOnWrite();
            ((TraceMetric) this.instance).addPerfSessions(i, perfSession);
            return this;
        }

        public Builder addPerfSessions(PerfSession.Builder builder) {
            copyOnWrite();
            ((TraceMetric) this.instance).addPerfSessions((PerfSession) builder.build());
            return this;
        }

        public Builder addPerfSessions(int i, PerfSession.Builder builder) {
            copyOnWrite();
            ((TraceMetric) this.instance).addPerfSessions(i, (PerfSession) builder.build());
            return this;
        }

        public Builder addAllPerfSessions(Iterable<? extends PerfSession> iterable) {
            copyOnWrite();
            ((TraceMetric) this.instance).addAllPerfSessions(iterable);
            return this;
        }

        public Builder clearPerfSessions() {
            copyOnWrite();
            ((TraceMetric) this.instance).clearPerfSessions();
            return this;
        }

        public Builder removePerfSessions(int i) {
            copyOnWrite();
            ((TraceMetric) this.instance).removePerfSessions(i);
            return this;
        }
    }

    /* renamed from: com.google.firebase.perf.v1.TraceMetric$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r0 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.v1.TraceMetric.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new TraceMetric();
            case 2:
                return new Builder((AnonymousClass1) null);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\b\u0000\u0001\u0001\t\b\u0002\u0002\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0004ဂ\u0002\u0005ဂ\u0003\u00062\u0007\u001b\b2\t\u001b", new Object[]{"bitField0_", "name_", "isAuto_", "clientStartTimeUs_", "durationUs_", "counters_", CountersDefaultEntryHolder.defaultEntry, "subtraces_", TraceMetric.class, "customAttributes_", CustomAttributesDefaultEntryHolder.defaultEntry, "perfSessions_", PerfSession.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (TraceMetric.class) {
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
        TraceMetric traceMetric = new TraceMetric();
        DEFAULT_INSTANCE = traceMetric;
        GeneratedMessageLite.registerDefaultInstance(TraceMetric.class, traceMetric);
    }

    public static TraceMetric getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
