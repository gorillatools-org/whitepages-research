package com.google.firebase.perf.v1;

import com.google.firebase.perf.v1.AndroidApplicationInfo;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;

public final class ApplicationInfo extends GeneratedMessageLite implements ApplicationInfoOrBuilder {
    public static final int ANDROID_APP_INFO_FIELD_NUMBER = 3;
    public static final int APPLICATION_PROCESS_STATE_FIELD_NUMBER = 5;
    public static final int APP_INSTANCE_ID_FIELD_NUMBER = 2;
    public static final int CUSTOM_ATTRIBUTES_FIELD_NUMBER = 6;
    /* access modifiers changed from: private */
    public static final ApplicationInfo DEFAULT_INSTANCE;
    public static final int GOOGLE_APP_ID_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private AndroidApplicationInfo androidAppInfo_;
    private String appInstanceId_ = "";
    private int applicationProcessState_;
    private int bitField0_;
    private MapFieldLite customAttributes_ = MapFieldLite.emptyMapField();
    private String googleAppId_ = "";

    private ApplicationInfo() {
    }

    public boolean hasGoogleAppId() {
        return (this.bitField0_ & 1) != 0;
    }

    public String getGoogleAppId() {
        return this.googleAppId_;
    }

    public ByteString getGoogleAppIdBytes() {
        return ByteString.copyFromUtf8(this.googleAppId_);
    }

    /* access modifiers changed from: private */
    public void setGoogleAppId(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.googleAppId_ = str;
    }

    /* access modifiers changed from: private */
    public void clearGoogleAppId() {
        this.bitField0_ &= -2;
        this.googleAppId_ = getDefaultInstance().getGoogleAppId();
    }

    /* access modifiers changed from: private */
    public void setGoogleAppIdBytes(ByteString byteString) {
        this.googleAppId_ = byteString.toStringUtf8();
        this.bitField0_ |= 1;
    }

    public boolean hasAppInstanceId() {
        return (this.bitField0_ & 2) != 0;
    }

    public String getAppInstanceId() {
        return this.appInstanceId_;
    }

    public ByteString getAppInstanceIdBytes() {
        return ByteString.copyFromUtf8(this.appInstanceId_);
    }

    /* access modifiers changed from: private */
    public void setAppInstanceId(String str) {
        str.getClass();
        this.bitField0_ |= 2;
        this.appInstanceId_ = str;
    }

    /* access modifiers changed from: private */
    public void clearAppInstanceId() {
        this.bitField0_ &= -3;
        this.appInstanceId_ = getDefaultInstance().getAppInstanceId();
    }

    /* access modifiers changed from: private */
    public void setAppInstanceIdBytes(ByteString byteString) {
        this.appInstanceId_ = byteString.toStringUtf8();
        this.bitField0_ |= 2;
    }

    public boolean hasAndroidAppInfo() {
        return (this.bitField0_ & 4) != 0;
    }

    public AndroidApplicationInfo getAndroidAppInfo() {
        AndroidApplicationInfo androidApplicationInfo = this.androidAppInfo_;
        return androidApplicationInfo == null ? AndroidApplicationInfo.getDefaultInstance() : androidApplicationInfo;
    }

    /* access modifiers changed from: private */
    public void setAndroidAppInfo(AndroidApplicationInfo androidApplicationInfo) {
        androidApplicationInfo.getClass();
        this.androidAppInfo_ = androidApplicationInfo;
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    public void mergeAndroidAppInfo(AndroidApplicationInfo androidApplicationInfo) {
        androidApplicationInfo.getClass();
        AndroidApplicationInfo androidApplicationInfo2 = this.androidAppInfo_;
        if (androidApplicationInfo2 == null || androidApplicationInfo2 == AndroidApplicationInfo.getDefaultInstance()) {
            this.androidAppInfo_ = androidApplicationInfo;
        } else {
            this.androidAppInfo_ = (AndroidApplicationInfo) ((AndroidApplicationInfo.Builder) AndroidApplicationInfo.newBuilder(this.androidAppInfo_).mergeFrom(androidApplicationInfo)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    public void clearAndroidAppInfo() {
        this.androidAppInfo_ = null;
        this.bitField0_ &= -5;
    }

    public boolean hasApplicationProcessState() {
        return (this.bitField0_ & 8) != 0;
    }

    public ApplicationProcessState getApplicationProcessState() {
        ApplicationProcessState forNumber = ApplicationProcessState.forNumber(this.applicationProcessState_);
        return forNumber == null ? ApplicationProcessState.APPLICATION_PROCESS_STATE_UNKNOWN : forNumber;
    }

    /* access modifiers changed from: private */
    public void setApplicationProcessState(ApplicationProcessState applicationProcessState) {
        this.applicationProcessState_ = applicationProcessState.getNumber();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    public void clearApplicationProcessState() {
        this.bitField0_ &= -9;
        this.applicationProcessState_ = 0;
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

    public static ApplicationInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ApplicationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ApplicationInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ApplicationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ApplicationInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ApplicationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ApplicationInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ApplicationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ApplicationInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ApplicationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ApplicationInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ApplicationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ApplicationInfo parseFrom(InputStream inputStream) throws IOException {
        return (ApplicationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ApplicationInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ApplicationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ApplicationInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ApplicationInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ApplicationInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ApplicationInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ApplicationInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ApplicationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ApplicationInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ApplicationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ApplicationInfo applicationInfo) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(applicationInfo);
    }

    public static final class Builder extends GeneratedMessageLite.Builder implements ApplicationInfoOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(ApplicationInfo.DEFAULT_INSTANCE);
        }

        public boolean hasGoogleAppId() {
            return ((ApplicationInfo) this.instance).hasGoogleAppId();
        }

        public String getGoogleAppId() {
            return ((ApplicationInfo) this.instance).getGoogleAppId();
        }

        public ByteString getGoogleAppIdBytes() {
            return ((ApplicationInfo) this.instance).getGoogleAppIdBytes();
        }

        public Builder setGoogleAppId(String str) {
            copyOnWrite();
            ((ApplicationInfo) this.instance).setGoogleAppId(str);
            return this;
        }

        public Builder clearGoogleAppId() {
            copyOnWrite();
            ((ApplicationInfo) this.instance).clearGoogleAppId();
            return this;
        }

        public Builder setGoogleAppIdBytes(ByteString byteString) {
            copyOnWrite();
            ((ApplicationInfo) this.instance).setGoogleAppIdBytes(byteString);
            return this;
        }

        public boolean hasAppInstanceId() {
            return ((ApplicationInfo) this.instance).hasAppInstanceId();
        }

        public String getAppInstanceId() {
            return ((ApplicationInfo) this.instance).getAppInstanceId();
        }

        public ByteString getAppInstanceIdBytes() {
            return ((ApplicationInfo) this.instance).getAppInstanceIdBytes();
        }

        public Builder setAppInstanceId(String str) {
            copyOnWrite();
            ((ApplicationInfo) this.instance).setAppInstanceId(str);
            return this;
        }

        public Builder clearAppInstanceId() {
            copyOnWrite();
            ((ApplicationInfo) this.instance).clearAppInstanceId();
            return this;
        }

        public Builder setAppInstanceIdBytes(ByteString byteString) {
            copyOnWrite();
            ((ApplicationInfo) this.instance).setAppInstanceIdBytes(byteString);
            return this;
        }

        public boolean hasAndroidAppInfo() {
            return ((ApplicationInfo) this.instance).hasAndroidAppInfo();
        }

        public AndroidApplicationInfo getAndroidAppInfo() {
            return ((ApplicationInfo) this.instance).getAndroidAppInfo();
        }

        public Builder setAndroidAppInfo(AndroidApplicationInfo androidApplicationInfo) {
            copyOnWrite();
            ((ApplicationInfo) this.instance).setAndroidAppInfo(androidApplicationInfo);
            return this;
        }

        public Builder setAndroidAppInfo(AndroidApplicationInfo.Builder builder) {
            copyOnWrite();
            ((ApplicationInfo) this.instance).setAndroidAppInfo((AndroidApplicationInfo) builder.build());
            return this;
        }

        public Builder mergeAndroidAppInfo(AndroidApplicationInfo androidApplicationInfo) {
            copyOnWrite();
            ((ApplicationInfo) this.instance).mergeAndroidAppInfo(androidApplicationInfo);
            return this;
        }

        public Builder clearAndroidAppInfo() {
            copyOnWrite();
            ((ApplicationInfo) this.instance).clearAndroidAppInfo();
            return this;
        }

        public boolean hasApplicationProcessState() {
            return ((ApplicationInfo) this.instance).hasApplicationProcessState();
        }

        public ApplicationProcessState getApplicationProcessState() {
            return ((ApplicationInfo) this.instance).getApplicationProcessState();
        }

        public Builder setApplicationProcessState(ApplicationProcessState applicationProcessState) {
            copyOnWrite();
            ((ApplicationInfo) this.instance).setApplicationProcessState(applicationProcessState);
            return this;
        }

        public Builder clearApplicationProcessState() {
            copyOnWrite();
            ((ApplicationInfo) this.instance).clearApplicationProcessState();
            return this;
        }

        public int getCustomAttributesCount() {
            return ((ApplicationInfo) this.instance).getCustomAttributesMap().size();
        }

        public boolean containsCustomAttributes(String str) {
            str.getClass();
            return ((ApplicationInfo) this.instance).getCustomAttributesMap().containsKey(str);
        }

        public Builder clearCustomAttributes() {
            copyOnWrite();
            ((ApplicationInfo) this.instance).getMutableCustomAttributesMap().clear();
            return this;
        }

        public Builder removeCustomAttributes(String str) {
            str.getClass();
            copyOnWrite();
            ((ApplicationInfo) this.instance).getMutableCustomAttributesMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, String> getCustomAttributes() {
            return getCustomAttributesMap();
        }

        public Map<String, String> getCustomAttributesMap() {
            return Collections.unmodifiableMap(((ApplicationInfo) this.instance).getCustomAttributesMap());
        }

        public String getCustomAttributesOrDefault(String str, String str2) {
            str.getClass();
            Map<String, String> customAttributesMap = ((ApplicationInfo) this.instance).getCustomAttributesMap();
            return customAttributesMap.containsKey(str) ? customAttributesMap.get(str) : str2;
        }

        public String getCustomAttributesOrThrow(String str) {
            str.getClass();
            Map<String, String> customAttributesMap = ((ApplicationInfo) this.instance).getCustomAttributesMap();
            if (customAttributesMap.containsKey(str)) {
                return customAttributesMap.get(str);
            }
            throw new IllegalArgumentException();
        }

        public Builder putCustomAttributes(String str, String str2) {
            str.getClass();
            str2.getClass();
            copyOnWrite();
            ((ApplicationInfo) this.instance).getMutableCustomAttributesMap().put(str, str2);
            return this;
        }

        public Builder putAllCustomAttributes(Map<String, String> map) {
            copyOnWrite();
            ((ApplicationInfo) this.instance).getMutableCustomAttributesMap().putAll(map);
            return this;
        }
    }

    /* renamed from: com.google.firebase.perf.v1.ApplicationInfo$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.v1.ApplicationInfo.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ApplicationInfo();
            case 2:
                return new Builder((AnonymousClass1) null);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0006\u0005\u0001\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဉ\u0002\u0005᠌\u0003\u00062", new Object[]{"bitField0_", "googleAppId_", "appInstanceId_", "androidAppInfo_", "applicationProcessState_", ApplicationProcessState.internalGetVerifier(), "customAttributes_", CustomAttributesDefaultEntryHolder.defaultEntry});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ApplicationInfo.class) {
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
        ApplicationInfo applicationInfo = new ApplicationInfo();
        DEFAULT_INSTANCE = applicationInfo;
        GeneratedMessageLite.registerDefaultInstance(ApplicationInfo.class, applicationInfo);
    }

    public static ApplicationInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
