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

public final class NetworkRequestMetric extends GeneratedMessageLite implements NetworkRequestMetricOrBuilder {
    public static final int CLIENT_START_TIME_US_FIELD_NUMBER = 7;
    public static final int CUSTOM_ATTRIBUTES_FIELD_NUMBER = 12;
    /* access modifiers changed from: private */
    public static final NetworkRequestMetric DEFAULT_INSTANCE;
    public static final int HTTP_METHOD_FIELD_NUMBER = 2;
    public static final int HTTP_RESPONSE_CODE_FIELD_NUMBER = 5;
    public static final int NETWORK_CLIENT_ERROR_REASON_FIELD_NUMBER = 11;
    private static volatile Parser PARSER = null;
    public static final int PERF_SESSIONS_FIELD_NUMBER = 13;
    public static final int REQUEST_PAYLOAD_BYTES_FIELD_NUMBER = 3;
    public static final int RESPONSE_CONTENT_TYPE_FIELD_NUMBER = 6;
    public static final int RESPONSE_PAYLOAD_BYTES_FIELD_NUMBER = 4;
    public static final int TIME_TO_REQUEST_COMPLETED_US_FIELD_NUMBER = 8;
    public static final int TIME_TO_RESPONSE_COMPLETED_US_FIELD_NUMBER = 10;
    public static final int TIME_TO_RESPONSE_INITIATED_US_FIELD_NUMBER = 9;
    public static final int URL_FIELD_NUMBER = 1;
    private int bitField0_;
    private long clientStartTimeUs_;
    private MapFieldLite customAttributes_ = MapFieldLite.emptyMapField();
    private int httpMethod_;
    private int httpResponseCode_;
    private int networkClientErrorReason_;
    private Internal.ProtobufList perfSessions_ = GeneratedMessageLite.emptyProtobufList();
    private long requestPayloadBytes_;
    private String responseContentType_ = "";
    private long responsePayloadBytes_;
    private long timeToRequestCompletedUs_;
    private long timeToResponseCompletedUs_;
    private long timeToResponseInitiatedUs_;
    private String url_ = "";

    private NetworkRequestMetric() {
    }

    public enum HttpMethod implements Internal.EnumLite {
        HTTP_METHOD_UNKNOWN(0),
        GET(1),
        PUT(2),
        POST(3),
        DELETE(4),
        HEAD(5),
        PATCH(6),
        OPTIONS(7),
        TRACE(8),
        CONNECT(9);
        
        public static final int CONNECT_VALUE = 9;
        public static final int DELETE_VALUE = 4;
        public static final int GET_VALUE = 1;
        public static final int HEAD_VALUE = 5;
        public static final int HTTP_METHOD_UNKNOWN_VALUE = 0;
        public static final int OPTIONS_VALUE = 7;
        public static final int PATCH_VALUE = 6;
        public static final int POST_VALUE = 3;
        public static final int PUT_VALUE = 2;
        public static final int TRACE_VALUE = 8;
        private static final Internal.EnumLiteMap internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap() {
                public HttpMethod findValueByNumber(int i) {
                    return HttpMethod.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static HttpMethod valueOf(int i) {
            return forNumber(i);
        }

        public static HttpMethod forNumber(int i) {
            switch (i) {
                case 0:
                    return HTTP_METHOD_UNKNOWN;
                case 1:
                    return GET;
                case 2:
                    return PUT;
                case 3:
                    return POST;
                case 4:
                    return DELETE;
                case 5:
                    return HEAD;
                case 6:
                    return PATCH;
                case 7:
                    return OPTIONS;
                case 8:
                    return TRACE;
                case 9:
                    return CONNECT;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return HttpMethodVerifier.INSTANCE;
        }

        private static final class HttpMethodVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = null;

            private HttpMethodVerifier() {
            }

            static {
                INSTANCE = new HttpMethodVerifier();
            }

            public boolean isInRange(int i) {
                return HttpMethod.forNumber(i) != null;
            }
        }

        private HttpMethod(int i) {
            this.value = i;
        }
    }

    public enum NetworkClientErrorReason implements Internal.EnumLite {
        NETWORK_CLIENT_ERROR_REASON_UNKNOWN(0),
        GENERIC_CLIENT_ERROR(1);
        
        public static final int GENERIC_CLIENT_ERROR_VALUE = 1;
        public static final int NETWORK_CLIENT_ERROR_REASON_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap() {
                public NetworkClientErrorReason findValueByNumber(int i) {
                    return NetworkClientErrorReason.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static NetworkClientErrorReason valueOf(int i) {
            return forNumber(i);
        }

        public static NetworkClientErrorReason forNumber(int i) {
            if (i == 0) {
                return NETWORK_CLIENT_ERROR_REASON_UNKNOWN;
            }
            if (i != 1) {
                return null;
            }
            return GENERIC_CLIENT_ERROR;
        }

        public static Internal.EnumLiteMap internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return NetworkClientErrorReasonVerifier.INSTANCE;
        }

        private static final class NetworkClientErrorReasonVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = null;

            private NetworkClientErrorReasonVerifier() {
            }

            static {
                INSTANCE = new NetworkClientErrorReasonVerifier();
            }

            public boolean isInRange(int i) {
                return NetworkClientErrorReason.forNumber(i) != null;
            }
        }

        private NetworkClientErrorReason(int i) {
            this.value = i;
        }
    }

    public boolean hasUrl() {
        return (this.bitField0_ & 1) != 0;
    }

    public String getUrl() {
        return this.url_;
    }

    public ByteString getUrlBytes() {
        return ByteString.copyFromUtf8(this.url_);
    }

    /* access modifiers changed from: private */
    public void setUrl(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.url_ = str;
    }

    /* access modifiers changed from: private */
    public void clearUrl() {
        this.bitField0_ &= -2;
        this.url_ = getDefaultInstance().getUrl();
    }

    /* access modifiers changed from: private */
    public void setUrlBytes(ByteString byteString) {
        this.url_ = byteString.toStringUtf8();
        this.bitField0_ |= 1;
    }

    public boolean hasHttpMethod() {
        return (this.bitField0_ & 2) != 0;
    }

    public HttpMethod getHttpMethod() {
        HttpMethod forNumber = HttpMethod.forNumber(this.httpMethod_);
        return forNumber == null ? HttpMethod.HTTP_METHOD_UNKNOWN : forNumber;
    }

    /* access modifiers changed from: private */
    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod_ = httpMethod.getNumber();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    public void clearHttpMethod() {
        this.bitField0_ &= -3;
        this.httpMethod_ = 0;
    }

    public boolean hasRequestPayloadBytes() {
        return (this.bitField0_ & 4) != 0;
    }

    public long getRequestPayloadBytes() {
        return this.requestPayloadBytes_;
    }

    /* access modifiers changed from: private */
    public void setRequestPayloadBytes(long j) {
        this.bitField0_ |= 4;
        this.requestPayloadBytes_ = j;
    }

    /* access modifiers changed from: private */
    public void clearRequestPayloadBytes() {
        this.bitField0_ &= -5;
        this.requestPayloadBytes_ = 0;
    }

    public boolean hasResponsePayloadBytes() {
        return (this.bitField0_ & 8) != 0;
    }

    public long getResponsePayloadBytes() {
        return this.responsePayloadBytes_;
    }

    /* access modifiers changed from: private */
    public void setResponsePayloadBytes(long j) {
        this.bitField0_ |= 8;
        this.responsePayloadBytes_ = j;
    }

    /* access modifiers changed from: private */
    public void clearResponsePayloadBytes() {
        this.bitField0_ &= -9;
        this.responsePayloadBytes_ = 0;
    }

    public boolean hasNetworkClientErrorReason() {
        return (this.bitField0_ & 16) != 0;
    }

    public NetworkClientErrorReason getNetworkClientErrorReason() {
        NetworkClientErrorReason forNumber = NetworkClientErrorReason.forNumber(this.networkClientErrorReason_);
        return forNumber == null ? NetworkClientErrorReason.NETWORK_CLIENT_ERROR_REASON_UNKNOWN : forNumber;
    }

    /* access modifiers changed from: private */
    public void setNetworkClientErrorReason(NetworkClientErrorReason networkClientErrorReason) {
        this.networkClientErrorReason_ = networkClientErrorReason.getNumber();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    public void clearNetworkClientErrorReason() {
        this.bitField0_ &= -17;
        this.networkClientErrorReason_ = 0;
    }

    public boolean hasHttpResponseCode() {
        return (this.bitField0_ & 32) != 0;
    }

    public int getHttpResponseCode() {
        return this.httpResponseCode_;
    }

    /* access modifiers changed from: private */
    public void setHttpResponseCode(int i) {
        this.bitField0_ |= 32;
        this.httpResponseCode_ = i;
    }

    /* access modifiers changed from: private */
    public void clearHttpResponseCode() {
        this.bitField0_ &= -33;
        this.httpResponseCode_ = 0;
    }

    public boolean hasResponseContentType() {
        return (this.bitField0_ & 64) != 0;
    }

    public String getResponseContentType() {
        return this.responseContentType_;
    }

    public ByteString getResponseContentTypeBytes() {
        return ByteString.copyFromUtf8(this.responseContentType_);
    }

    /* access modifiers changed from: private */
    public void setResponseContentType(String str) {
        str.getClass();
        this.bitField0_ |= 64;
        this.responseContentType_ = str;
    }

    /* access modifiers changed from: private */
    public void clearResponseContentType() {
        this.bitField0_ &= -65;
        this.responseContentType_ = getDefaultInstance().getResponseContentType();
    }

    /* access modifiers changed from: private */
    public void setResponseContentTypeBytes(ByteString byteString) {
        this.responseContentType_ = byteString.toStringUtf8();
        this.bitField0_ |= 64;
    }

    public boolean hasClientStartTimeUs() {
        return (this.bitField0_ & 128) != 0;
    }

    public long getClientStartTimeUs() {
        return this.clientStartTimeUs_;
    }

    /* access modifiers changed from: private */
    public void setClientStartTimeUs(long j) {
        this.bitField0_ |= 128;
        this.clientStartTimeUs_ = j;
    }

    /* access modifiers changed from: private */
    public void clearClientStartTimeUs() {
        this.bitField0_ &= -129;
        this.clientStartTimeUs_ = 0;
    }

    public boolean hasTimeToRequestCompletedUs() {
        return (this.bitField0_ & 256) != 0;
    }

    public long getTimeToRequestCompletedUs() {
        return this.timeToRequestCompletedUs_;
    }

    /* access modifiers changed from: private */
    public void setTimeToRequestCompletedUs(long j) {
        this.bitField0_ |= 256;
        this.timeToRequestCompletedUs_ = j;
    }

    /* access modifiers changed from: private */
    public void clearTimeToRequestCompletedUs() {
        this.bitField0_ &= -257;
        this.timeToRequestCompletedUs_ = 0;
    }

    public boolean hasTimeToResponseInitiatedUs() {
        return (this.bitField0_ & 512) != 0;
    }

    public long getTimeToResponseInitiatedUs() {
        return this.timeToResponseInitiatedUs_;
    }

    /* access modifiers changed from: private */
    public void setTimeToResponseInitiatedUs(long j) {
        this.bitField0_ |= 512;
        this.timeToResponseInitiatedUs_ = j;
    }

    /* access modifiers changed from: private */
    public void clearTimeToResponseInitiatedUs() {
        this.bitField0_ &= -513;
        this.timeToResponseInitiatedUs_ = 0;
    }

    public boolean hasTimeToResponseCompletedUs() {
        return (this.bitField0_ & 1024) != 0;
    }

    public long getTimeToResponseCompletedUs() {
        return this.timeToResponseCompletedUs_;
    }

    /* access modifiers changed from: private */
    public void setTimeToResponseCompletedUs(long j) {
        this.bitField0_ |= 1024;
        this.timeToResponseCompletedUs_ = j;
    }

    /* access modifiers changed from: private */
    public void clearTimeToResponseCompletedUs() {
        this.bitField0_ &= -1025;
        this.timeToResponseCompletedUs_ = 0;
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

    public static NetworkRequestMetric parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (NetworkRequestMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static NetworkRequestMetric parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (NetworkRequestMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static NetworkRequestMetric parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (NetworkRequestMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static NetworkRequestMetric parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (NetworkRequestMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static NetworkRequestMetric parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (NetworkRequestMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static NetworkRequestMetric parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (NetworkRequestMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static NetworkRequestMetric parseFrom(InputStream inputStream) throws IOException {
        return (NetworkRequestMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static NetworkRequestMetric parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (NetworkRequestMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static NetworkRequestMetric parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (NetworkRequestMetric) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static NetworkRequestMetric parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (NetworkRequestMetric) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static NetworkRequestMetric parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (NetworkRequestMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static NetworkRequestMetric parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (NetworkRequestMetric) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(NetworkRequestMetric networkRequestMetric) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(networkRequestMetric);
    }

    public static final class Builder extends GeneratedMessageLite.Builder implements NetworkRequestMetricOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(NetworkRequestMetric.DEFAULT_INSTANCE);
        }

        public boolean hasUrl() {
            return ((NetworkRequestMetric) this.instance).hasUrl();
        }

        public String getUrl() {
            return ((NetworkRequestMetric) this.instance).getUrl();
        }

        public ByteString getUrlBytes() {
            return ((NetworkRequestMetric) this.instance).getUrlBytes();
        }

        public Builder setUrl(String str) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).setUrl(str);
            return this;
        }

        public Builder clearUrl() {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).clearUrl();
            return this;
        }

        public Builder setUrlBytes(ByteString byteString) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).setUrlBytes(byteString);
            return this;
        }

        public boolean hasHttpMethod() {
            return ((NetworkRequestMetric) this.instance).hasHttpMethod();
        }

        public HttpMethod getHttpMethod() {
            return ((NetworkRequestMetric) this.instance).getHttpMethod();
        }

        public Builder setHttpMethod(HttpMethod httpMethod) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).setHttpMethod(httpMethod);
            return this;
        }

        public Builder clearHttpMethod() {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).clearHttpMethod();
            return this;
        }

        public boolean hasRequestPayloadBytes() {
            return ((NetworkRequestMetric) this.instance).hasRequestPayloadBytes();
        }

        public long getRequestPayloadBytes() {
            return ((NetworkRequestMetric) this.instance).getRequestPayloadBytes();
        }

        public Builder setRequestPayloadBytes(long j) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).setRequestPayloadBytes(j);
            return this;
        }

        public Builder clearRequestPayloadBytes() {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).clearRequestPayloadBytes();
            return this;
        }

        public boolean hasResponsePayloadBytes() {
            return ((NetworkRequestMetric) this.instance).hasResponsePayloadBytes();
        }

        public long getResponsePayloadBytes() {
            return ((NetworkRequestMetric) this.instance).getResponsePayloadBytes();
        }

        public Builder setResponsePayloadBytes(long j) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).setResponsePayloadBytes(j);
            return this;
        }

        public Builder clearResponsePayloadBytes() {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).clearResponsePayloadBytes();
            return this;
        }

        public boolean hasNetworkClientErrorReason() {
            return ((NetworkRequestMetric) this.instance).hasNetworkClientErrorReason();
        }

        public NetworkClientErrorReason getNetworkClientErrorReason() {
            return ((NetworkRequestMetric) this.instance).getNetworkClientErrorReason();
        }

        public Builder setNetworkClientErrorReason(NetworkClientErrorReason networkClientErrorReason) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).setNetworkClientErrorReason(networkClientErrorReason);
            return this;
        }

        public Builder clearNetworkClientErrorReason() {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).clearNetworkClientErrorReason();
            return this;
        }

        public boolean hasHttpResponseCode() {
            return ((NetworkRequestMetric) this.instance).hasHttpResponseCode();
        }

        public int getHttpResponseCode() {
            return ((NetworkRequestMetric) this.instance).getHttpResponseCode();
        }

        public Builder setHttpResponseCode(int i) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).setHttpResponseCode(i);
            return this;
        }

        public Builder clearHttpResponseCode() {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).clearHttpResponseCode();
            return this;
        }

        public boolean hasResponseContentType() {
            return ((NetworkRequestMetric) this.instance).hasResponseContentType();
        }

        public String getResponseContentType() {
            return ((NetworkRequestMetric) this.instance).getResponseContentType();
        }

        public ByteString getResponseContentTypeBytes() {
            return ((NetworkRequestMetric) this.instance).getResponseContentTypeBytes();
        }

        public Builder setResponseContentType(String str) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).setResponseContentType(str);
            return this;
        }

        public Builder clearResponseContentType() {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).clearResponseContentType();
            return this;
        }

        public Builder setResponseContentTypeBytes(ByteString byteString) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).setResponseContentTypeBytes(byteString);
            return this;
        }

        public boolean hasClientStartTimeUs() {
            return ((NetworkRequestMetric) this.instance).hasClientStartTimeUs();
        }

        public long getClientStartTimeUs() {
            return ((NetworkRequestMetric) this.instance).getClientStartTimeUs();
        }

        public Builder setClientStartTimeUs(long j) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).setClientStartTimeUs(j);
            return this;
        }

        public Builder clearClientStartTimeUs() {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).clearClientStartTimeUs();
            return this;
        }

        public boolean hasTimeToRequestCompletedUs() {
            return ((NetworkRequestMetric) this.instance).hasTimeToRequestCompletedUs();
        }

        public long getTimeToRequestCompletedUs() {
            return ((NetworkRequestMetric) this.instance).getTimeToRequestCompletedUs();
        }

        public Builder setTimeToRequestCompletedUs(long j) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).setTimeToRequestCompletedUs(j);
            return this;
        }

        public Builder clearTimeToRequestCompletedUs() {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).clearTimeToRequestCompletedUs();
            return this;
        }

        public boolean hasTimeToResponseInitiatedUs() {
            return ((NetworkRequestMetric) this.instance).hasTimeToResponseInitiatedUs();
        }

        public long getTimeToResponseInitiatedUs() {
            return ((NetworkRequestMetric) this.instance).getTimeToResponseInitiatedUs();
        }

        public Builder setTimeToResponseInitiatedUs(long j) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).setTimeToResponseInitiatedUs(j);
            return this;
        }

        public Builder clearTimeToResponseInitiatedUs() {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).clearTimeToResponseInitiatedUs();
            return this;
        }

        public boolean hasTimeToResponseCompletedUs() {
            return ((NetworkRequestMetric) this.instance).hasTimeToResponseCompletedUs();
        }

        public long getTimeToResponseCompletedUs() {
            return ((NetworkRequestMetric) this.instance).getTimeToResponseCompletedUs();
        }

        public Builder setTimeToResponseCompletedUs(long j) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).setTimeToResponseCompletedUs(j);
            return this;
        }

        public Builder clearTimeToResponseCompletedUs() {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).clearTimeToResponseCompletedUs();
            return this;
        }

        public int getCustomAttributesCount() {
            return ((NetworkRequestMetric) this.instance).getCustomAttributesMap().size();
        }

        public boolean containsCustomAttributes(String str) {
            str.getClass();
            return ((NetworkRequestMetric) this.instance).getCustomAttributesMap().containsKey(str);
        }

        public Builder clearCustomAttributes() {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).getMutableCustomAttributesMap().clear();
            return this;
        }

        public Builder removeCustomAttributes(String str) {
            str.getClass();
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).getMutableCustomAttributesMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, String> getCustomAttributes() {
            return getCustomAttributesMap();
        }

        public Map<String, String> getCustomAttributesMap() {
            return Collections.unmodifiableMap(((NetworkRequestMetric) this.instance).getCustomAttributesMap());
        }

        public String getCustomAttributesOrDefault(String str, String str2) {
            str.getClass();
            Map<String, String> customAttributesMap = ((NetworkRequestMetric) this.instance).getCustomAttributesMap();
            return customAttributesMap.containsKey(str) ? customAttributesMap.get(str) : str2;
        }

        public String getCustomAttributesOrThrow(String str) {
            str.getClass();
            Map<String, String> customAttributesMap = ((NetworkRequestMetric) this.instance).getCustomAttributesMap();
            if (customAttributesMap.containsKey(str)) {
                return customAttributesMap.get(str);
            }
            throw new IllegalArgumentException();
        }

        public Builder putCustomAttributes(String str, String str2) {
            str.getClass();
            str2.getClass();
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).getMutableCustomAttributesMap().put(str, str2);
            return this;
        }

        public Builder putAllCustomAttributes(Map<String, String> map) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).getMutableCustomAttributesMap().putAll(map);
            return this;
        }

        public List<PerfSession> getPerfSessionsList() {
            return Collections.unmodifiableList(((NetworkRequestMetric) this.instance).getPerfSessionsList());
        }

        public int getPerfSessionsCount() {
            return ((NetworkRequestMetric) this.instance).getPerfSessionsCount();
        }

        public PerfSession getPerfSessions(int i) {
            return ((NetworkRequestMetric) this.instance).getPerfSessions(i);
        }

        public Builder setPerfSessions(int i, PerfSession perfSession) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).setPerfSessions(i, perfSession);
            return this;
        }

        public Builder setPerfSessions(int i, PerfSession.Builder builder) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).setPerfSessions(i, (PerfSession) builder.build());
            return this;
        }

        public Builder addPerfSessions(PerfSession perfSession) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).addPerfSessions(perfSession);
            return this;
        }

        public Builder addPerfSessions(int i, PerfSession perfSession) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).addPerfSessions(i, perfSession);
            return this;
        }

        public Builder addPerfSessions(PerfSession.Builder builder) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).addPerfSessions((PerfSession) builder.build());
            return this;
        }

        public Builder addPerfSessions(int i, PerfSession.Builder builder) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).addPerfSessions(i, (PerfSession) builder.build());
            return this;
        }

        public Builder addAllPerfSessions(Iterable<? extends PerfSession> iterable) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).addAllPerfSessions(iterable);
            return this;
        }

        public Builder clearPerfSessions() {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).clearPerfSessions();
            return this;
        }

        public Builder removePerfSessions(int i) {
            copyOnWrite();
            ((NetworkRequestMetric) this.instance).removePerfSessions(i);
            return this;
        }
    }

    /* renamed from: com.google.firebase.perf.v1.NetworkRequestMetric$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.v1.NetworkRequestMetric.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new NetworkRequestMetric();
            case 2:
                return new Builder((AnonymousClass1) null);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\r\u0000\u0001\u0001\r\r\u0001\u0001\u0000\u0001ဈ\u0000\u0002᠌\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005င\u0005\u0006ဈ\u0006\u0007ဂ\u0007\bဂ\b\tဂ\t\nဂ\n\u000b᠌\u0004\f2\r\u001b", new Object[]{"bitField0_", "url_", "httpMethod_", HttpMethod.internalGetVerifier(), "requestPayloadBytes_", "responsePayloadBytes_", "httpResponseCode_", "responseContentType_", "clientStartTimeUs_", "timeToRequestCompletedUs_", "timeToResponseInitiatedUs_", "timeToResponseCompletedUs_", "networkClientErrorReason_", NetworkClientErrorReason.internalGetVerifier(), "customAttributes_", CustomAttributesDefaultEntryHolder.defaultEntry, "perfSessions_", PerfSession.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (NetworkRequestMetric.class) {
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
        NetworkRequestMetric networkRequestMetric = new NetworkRequestMetric();
        DEFAULT_INSTANCE = networkRequestMetric;
        GeneratedMessageLite.registerDefaultInstance(NetworkRequestMetric.class, networkRequestMetric);
    }

    public static NetworkRequestMetric getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
