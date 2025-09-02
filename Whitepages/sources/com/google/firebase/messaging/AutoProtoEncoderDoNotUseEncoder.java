package com.google.firebase.messaging;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.encoders.proto.AtProtobuf;
import com.google.firebase.messaging.reporting.MessagingClientEvent;
import com.google.firebase.messaging.reporting.MessagingClientEventExtension;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;

public final class AutoProtoEncoderDoNotUseEncoder implements Configurator {
    public static final int CODEGEN_VERSION = 2;
    public static final Configurator CONFIG = new AutoProtoEncoderDoNotUseEncoder();

    private AutoProtoEncoderDoNotUseEncoder() {
    }

    public void configure(EncoderConfig<?> encoderConfig) {
        encoderConfig.registerEncoder((Class<U>) ProtoEncoderDoNotUse.class, (ObjectEncoder<? super U>) ProtoEncoderDoNotUseEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) MessagingClientEventExtension.class, (ObjectEncoder<? super U>) MessagingClientEventExtensionEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) MessagingClientEvent.class, (ObjectEncoder<? super U>) MessagingClientEventEncoder.INSTANCE);
    }

    private static final class ProtoEncoderDoNotUseEncoder implements ObjectEncoder<ProtoEncoderDoNotUse> {
        static final ProtoEncoderDoNotUseEncoder INSTANCE = new ProtoEncoderDoNotUseEncoder();
        private static final FieldDescriptor MESSAGINGCLIENTEVENTEXTENSION_DESCRIPTOR = FieldDescriptor.of("messagingClientEventExtension");

        private ProtoEncoderDoNotUseEncoder() {
        }

        public void encode(ProtoEncoderDoNotUse protoEncoderDoNotUse, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(MESSAGINGCLIENTEVENTEXTENSION_DESCRIPTOR, (Object) protoEncoderDoNotUse.getMessagingClientEventExtension());
        }
    }

    private static final class MessagingClientEventExtensionEncoder implements ObjectEncoder<MessagingClientEventExtension> {
        static final MessagingClientEventExtensionEncoder INSTANCE = new MessagingClientEventExtensionEncoder();
        private static final FieldDescriptor MESSAGINGCLIENTEVENT_DESCRIPTOR = FieldDescriptor.builder("messagingClientEvent").withProperty(AtProtobuf.builder().tag(1).build()).build();

        private MessagingClientEventExtensionEncoder() {
        }

        public void encode(MessagingClientEventExtension messagingClientEventExtension, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(MESSAGINGCLIENTEVENT_DESCRIPTOR, (Object) messagingClientEventExtension.getMessagingClientEventInternal());
        }
    }

    private static final class MessagingClientEventEncoder implements ObjectEncoder<MessagingClientEvent> {
        private static final FieldDescriptor ANALYTICSLABEL_DESCRIPTOR = FieldDescriptor.builder("analyticsLabel").withProperty(AtProtobuf.builder().tag(13).build()).build();
        private static final FieldDescriptor BULKID_DESCRIPTOR = FieldDescriptor.builder("bulkId").withProperty(AtProtobuf.builder().tag(11).build()).build();
        private static final FieldDescriptor CAMPAIGNID_DESCRIPTOR = FieldDescriptor.builder("campaignId").withProperty(AtProtobuf.builder().tag(14).build()).build();
        private static final FieldDescriptor COLLAPSEKEY_DESCRIPTOR = FieldDescriptor.builder("collapseKey").withProperty(AtProtobuf.builder().tag(7).build()).build();
        private static final FieldDescriptor COMPOSERLABEL_DESCRIPTOR = FieldDescriptor.builder("composerLabel").withProperty(AtProtobuf.builder().tag(15).build()).build();
        private static final FieldDescriptor EVENT_DESCRIPTOR = FieldDescriptor.builder("event").withProperty(AtProtobuf.builder().tag(12).build()).build();
        static final MessagingClientEventEncoder INSTANCE = new MessagingClientEventEncoder();
        private static final FieldDescriptor INSTANCEID_DESCRIPTOR = FieldDescriptor.builder("instanceId").withProperty(AtProtobuf.builder().tag(3).build()).build();
        private static final FieldDescriptor MESSAGEID_DESCRIPTOR = FieldDescriptor.builder("messageId").withProperty(AtProtobuf.builder().tag(2).build()).build();
        private static final FieldDescriptor MESSAGETYPE_DESCRIPTOR = FieldDescriptor.builder("messageType").withProperty(AtProtobuf.builder().tag(4).build()).build();
        private static final FieldDescriptor PACKAGENAME_DESCRIPTOR = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME).withProperty(AtProtobuf.builder().tag(6).build()).build();
        private static final FieldDescriptor PRIORITY_DESCRIPTOR = FieldDescriptor.builder("priority").withProperty(AtProtobuf.builder().tag(8).build()).build();
        private static final FieldDescriptor PROJECTNUMBER_DESCRIPTOR = FieldDescriptor.builder("projectNumber").withProperty(AtProtobuf.builder().tag(1).build()).build();
        private static final FieldDescriptor SDKPLATFORM_DESCRIPTOR = FieldDescriptor.builder("sdkPlatform").withProperty(AtProtobuf.builder().tag(5).build()).build();
        private static final FieldDescriptor TOPIC_DESCRIPTOR = FieldDescriptor.builder("topic").withProperty(AtProtobuf.builder().tag(10).build()).build();
        private static final FieldDescriptor TTL_DESCRIPTOR = FieldDescriptor.builder("ttl").withProperty(AtProtobuf.builder().tag(9).build()).build();

        private MessagingClientEventEncoder() {
        }

        public void encode(MessagingClientEvent messagingClientEvent, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(PROJECTNUMBER_DESCRIPTOR, messagingClientEvent.getProjectNumber());
            objectEncoderContext.add(MESSAGEID_DESCRIPTOR, (Object) messagingClientEvent.getMessageId());
            objectEncoderContext.add(INSTANCEID_DESCRIPTOR, (Object) messagingClientEvent.getInstanceId());
            objectEncoderContext.add(MESSAGETYPE_DESCRIPTOR, (Object) messagingClientEvent.getMessageType());
            objectEncoderContext.add(SDKPLATFORM_DESCRIPTOR, (Object) messagingClientEvent.getSdkPlatform());
            objectEncoderContext.add(PACKAGENAME_DESCRIPTOR, (Object) messagingClientEvent.getPackageName());
            objectEncoderContext.add(COLLAPSEKEY_DESCRIPTOR, (Object) messagingClientEvent.getCollapseKey());
            objectEncoderContext.add(PRIORITY_DESCRIPTOR, messagingClientEvent.getPriority());
            objectEncoderContext.add(TTL_DESCRIPTOR, messagingClientEvent.getTtl());
            objectEncoderContext.add(TOPIC_DESCRIPTOR, (Object) messagingClientEvent.getTopic());
            objectEncoderContext.add(BULKID_DESCRIPTOR, messagingClientEvent.getBulkId());
            objectEncoderContext.add(EVENT_DESCRIPTOR, (Object) messagingClientEvent.getEvent());
            objectEncoderContext.add(ANALYTICSLABEL_DESCRIPTOR, (Object) messagingClientEvent.getAnalyticsLabel());
            objectEncoderContext.add(CAMPAIGNID_DESCRIPTOR, messagingClientEvent.getCampaignId());
            objectEncoderContext.add(COMPOSERLABEL_DESCRIPTOR, (Object) messagingClientEvent.getComposerLabel());
        }
    }
}
